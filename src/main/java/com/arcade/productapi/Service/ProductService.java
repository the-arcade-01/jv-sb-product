package com.arcade.productapi.Service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import com.arcade.productapi.Model.ProductModel;
import com.arcade.productapi.Repository.ProductRepo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@Service
public class ProductService {
    private final ProductRepo repository;

    @Autowired
    public ProductService(ProductRepo repository) {
        this.repository = repository;
    }

    public List<ProductModel> getAllProducts() {
        return repository.findAll();
    }

    public ProductModel getOneProduct(Long id) throws Exception {
        return repository.findById(id).orElseThrow(() -> new Exception("Product not found"));
    }

    public ProductModel createProduct(ProductModel product, MultipartFile file) throws Exception {
        String imageName = StringUtils.cleanPath(file.getOriginalFilename());
        String imageUrl = "";
        try {
            if (imageName.contains("..")) {
                throw new Exception("Filename contains invalid path sequence " + imageName);
            }
            product.setImageName(imageName);
            product.setImageType(file.getContentType());
            product.setData(file.getBytes());

            imageUrl = ServletUriComponentsBuilder.fromCurrentContextPath().path("/download/")
                    .path(Long.toString(product.getId()))
                    .toUriString();
            product.setImageUrl(imageUrl);

            return repository.save(product);
        } catch (Exception e) {
            throw new Exception("Could not save file " + imageName);
        }
    }

    public Optional<Object> updateProduct(ProductModel newProduct, MultipartFile file, Long id) throws Exception {
        String imageName = StringUtils.cleanPath(file.getOriginalFilename());
        try {
            if (imageName.contains("..")) {
                throw new Exception("Filename contains invalid path sequence " + imageName);
            }
            return repository.findById(id).map((product) -> {
                product.setName(newProduct.getName());
                product.setDescription(newProduct.getDescription());
                product.setPrice(newProduct.getPrice());
                product.setImageName(imageName);
                product.setImageType(file.getContentType());
                try {
                    product.setData(file.getBytes());
                } catch (IOException e) {
                    e.printStackTrace();
                }

                String imageUrl = ServletUriComponentsBuilder.fromCurrentContextPath().path("/download/")
                        .path(Long.toString(product.getId()))
                        .toUriString();
                product.setImageUrl(imageUrl);

                return repository.save(product);
            });
        } catch (Exception e) {
            throw new Exception("Could not save file " + imageName);
        }
    }

    public void deleteProduct(Long id) {
        repository.deleteById(id);
    }
}
