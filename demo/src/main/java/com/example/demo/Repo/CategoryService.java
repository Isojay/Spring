package com.example.demo.Repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.dao.Category;

public interface CategoryService extends JpaRepository<Category, Integer> {

}
