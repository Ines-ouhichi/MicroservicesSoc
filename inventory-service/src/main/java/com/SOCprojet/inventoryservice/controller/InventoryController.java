package com.SOCprojet.inventoryservice.controller;

import java.util.List;

import javax.net.ssl.SSLEngineResult.Status;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.SOCprojet.inventoryservice.dto.InventoryResponse;
import com.SOCprojet.inventoryservice.service.InventoryService;

import org.springframework.http.HttpStatus;


import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/inventory")
@RequiredArgsConstructor
public class InventoryController {
	
    private final InventoryService inventoryService;

	
	@GetMapping
	@ResponseStatus(HttpStatus.OK)
	public List<InventoryResponse> IsInStock(@RequestParam List<String> skuCode) {
		return inventoryService.IsInStock(skuCode);
	}

}
