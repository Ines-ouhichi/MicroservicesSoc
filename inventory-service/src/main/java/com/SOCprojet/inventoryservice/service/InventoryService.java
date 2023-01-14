package com.SOCprojet.inventoryservice.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;

import com.SOCprojet.inventoryservice.dto.InventoryResponse;
import com.SOCprojet.inventoryservice.repository.InventoryRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class InventoryService {
	
    private final InventoryRepository inventoryRepository;
    
    @Transactional(readOnly=true)
    public List<InventoryResponse> IsInStock(List<String> skuCode) {
    	
    	return inventoryRepository.findBySkuCodeIn(skuCode).stream()
    			.map(inventory ->
                InventoryResponse.builder()
                        .skuCode(inventory.getSkuCode())
                        .isInStock(inventory.getQuantity() > 0)
                        .build()
        ).toList();
    }

}
