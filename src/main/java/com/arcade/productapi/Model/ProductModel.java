package com.arcade.productapi.Model;

import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class ProductModel {

    @Id
    @GeneratedValue()
    private Long id;

    private String name;
    private String description;
    private Integer price;
    private String imageName;
    private String imageType;
    private String imageUrl;

    @Lob
    private byte[] data;

    public ProductModel() {
    }

    public ProductModel(String name, String description, Integer price, String imageName, String imageType,
            String imageUrl, byte[] data) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.imageName = imageName;
        this.imageType = imageType;
        this.imageUrl = imageUrl;
        this.data = data;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getPrice() {
        return this.price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public String getImageName() {
        return this.imageName;
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
    }

    public String getImageType() {
        return this.imageType;
    }

    public void setImageType(String imageType) {
        this.imageType = imageType;
    }

    public byte[] getData() {
        return this.data;
    }

    public void setData(byte[] data) {
        this.data = data;
    }

    public ProductModel id(Long id) {
        setId(id);
        return this;
    }

    public ProductModel name(String name) {
        setName(name);
        return this;
    }

    public ProductModel description(String description) {
        setDescription(description);
        return this;
    }

    public ProductModel price(Integer price) {
        setPrice(price);
        return this;
    }

    public ProductModel imageName(String imageName) {
        setImageName(imageName);
        return this;
    }

    public ProductModel imageType(String imageType) {
        setImageType(imageType);
        return this;
    }

    public ProductModel data(byte[] data) {
        setData(data);
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof ProductModel)) {
            return false;
        }
        ProductModel productModel = (ProductModel) o;
        return Objects.equals(id, productModel.id) && Objects.equals(name, productModel.name)
                && Objects.equals(description, productModel.description) && Objects.equals(price, productModel.price)
                && Objects.equals(imageName, productModel.imageName)
                && Objects.equals(imageType, productModel.imageType) && Objects.equals(data, productModel.data);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, description, price, imageName, imageType, data);
    }

    @Override
    public String toString() {
        return "{" +
                " id='" + getId() + "'" +
                ", name='" + getName() + "'" +
                ", description='" + getDescription() + "'" +
                ", price='" + getPrice() + "'" +
                ", imageName='" + getImageName() + "'" +
                ", imageType='" + getImageType() + "'" +
                ", data='" + getData() + "'" +
                "}";
    }

}
