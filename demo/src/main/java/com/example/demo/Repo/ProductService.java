package com.example.demo.Repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.dao.Product;
import com.example.demo.dao.ProductDTO;

public interface ProductService extends JpaRepository<Product, Integer> {


}
