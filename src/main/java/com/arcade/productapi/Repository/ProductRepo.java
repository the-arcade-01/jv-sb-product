package com.arcade.productapi.Repository;

import com.arcade.productapi.Model.ProductModel;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepo extends JpaRepository<ProductModel, Long> {

}