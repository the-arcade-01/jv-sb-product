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

    public Optional<ProductModel> getOneProduct(Long id) {
        return repository.findById(id);
    }

    public ProductModel createProduct(ProductModel product, MultipartFile file) throws Exception {
        String imageName = StringUtils.cleanPath(file.getOriginalFilename());
        try {
            if (imageName.contains("..")) {
                throw new Exception("Filename contains invalid path sequence " + imageName);
            }
            product.setImageName(imageName);
            product.setImageType(file.getContentType());
            product.setData(file.getBytes());
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
