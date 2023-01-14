package com.SOCprojet.productservice.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;

import com.SOCprojet.productservice.dto.ProductRequest;
import com.SOCprojet.productservice.dto.ProductResponse;
import com.SOCprojet.productservice.repository.ProductRepository;
import com.SOCprojet.productservice.service.ProductService;

import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import lombok.RequiredArgsConstructor;


@Controller
@RequestMapping("/api/product")
@RequiredArgsConstructor
public class ProductController {

	private final ProductService productService;
    private final ProductRepository productRepository;

	
	
    @GetMapping("/prods")
	public String index(Model model) {
		model.addAttribute("products",productRepository.findAll());
		return "prods";
		
	}
    
	@GetMapping("/")
	public String index() {
		return "index";
		
	}
	
	
    @PostMapping("/product")
    @ResponseStatus(HttpStatus.CREATED)
    public void createProduct(@RequestBody ProductRequest productRequest) {
        productService.createProduct(productRequest);
    }
    
    @GetMapping("/productGet")
    @ResponseStatus(HttpStatus.OK)
    public List<ProductResponse> getAllProducts() {
        return productService.getAllProducts();
    }
}
