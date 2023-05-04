package com.example.demo.Repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.dao.Product;

public interface ProductService extends JpaRepository<Product, Integer> {


}
