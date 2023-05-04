package com.example.demo.COntroller;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import org.hibernate.boot.jaxb.hbm.internal.CacheAccessTypeConverter;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.Repo.CategoryService;
import com.example.demo.Repo.ProductService;
import com.example.demo.dao.Category;
import com.example.demo.dao.Product;
import com.example.demo.dao.ProductDTO;

@Controller
public class ProductsController {
	
	ProductService productService;
	CategoryService categoryService;
	
	public ProductsController(ProductService theproductService,CategoryService thecategoryService) {
		
		productService = theproductService;
		categoryService = thecategoryService;
	}
	@GetMapping("admin/products")
	public String showproducts(Model theModel) {
		
		List<Product> theCategories = productService.findAll();
		theModel.addAttribute("products", theCategories);
		
		return "products";
	}
	
	@GetMapping("/admin/products/add")
	public String addCategories(Model theModel) {
		
		ProductDTO products = new ProductDTO();
		List<Category> theCategories = categoryService.findAll();
		theModel.addAttribute("productDTO",products);
		theModel.addAttribute("categories",theCategories);
		
		
		return "productsAdd";
	}
	
	@PostMapping("/admin/products/add")
	public String saveCategories(@ModelAttribute("productDTO") ProductDTO productDTO,
									@RequestParam("productImage") MultipartFile file,
										@RequestParam("imgName") String imgName) throws IOException {
		
		Product product = new Product();
		product.setId(productDTO.getId());
		product.setName(productDTO.getName());
		product.setCategory(categoryService.findById(productDTO.getCategoryId()).get());
		product.setPrice(productDTO.getPrice());
		product.setWeight(productDTO.getWeight());
		product.setDescription(productDTO.getDescription());
		String imageUUID;
		
		
		
		productService.save(product);
		
		return "redirect:/admin/products";
	}
	
	@GetMapping("/admin/products/delete/{id}")
	public String delproducts(@PathVariable int id) {
		
		productService.deleteById(id);
		
		return "redirect:/admin/products";
	}
	
	@GetMapping("/admin/products/update/{id}")
	public String updateproducts(@PathVariable int id,Model theModel) {
		
		Optional<Product> product = productService.findById(id);
		if(product.isPresent()) {
			
			theModel.addAttribute("product",product.get());
			return "productsAdd";
			
		}else {
			return "404";
		}
				
	}
	
	
	
}
