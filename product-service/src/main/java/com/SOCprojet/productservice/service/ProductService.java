package com.SOCprojet.productservice.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import com.SOCprojet.productservice.dto.ProductRequest;
import com.SOCprojet.productservice.dto.ProductResponse;
import com.SOCprojet.productservice.model.Product;
import com.SOCprojet.productservice.repository.ProductRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductService {
	
    private final ProductRepository productRepository;

	public void createProduct(@RequestBody ProductRequest ProductRequest) {
		
		 Product product = Product.builder()
				 .name(ProductRequest.getName())
				 .description(ProductRequest.getDescription())
				 .price(ProductRequest.getPrice())
				 .build();

		 productRepository.save(product);
	     log.info("Product {} is saved", product.getId());        

    
	}

	public List<ProductResponse> getAllProducts() {
		 List<Product> products = productRepository.findAll();

	        return products.stream().map(this::mapToProductResponse).toList();
	}
	
	 private ProductResponse mapToProductResponse(Product product) {
	        return ProductResponse.builder()
	                .id(product.getId())
	                .name(product.getName())
	                .description(product.getDescription())
	                .price(product.getPrice())
	                .build();
	    }


}
