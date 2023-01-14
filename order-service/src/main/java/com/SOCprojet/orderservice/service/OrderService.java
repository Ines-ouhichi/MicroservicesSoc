package com.SOCprojet.orderservice.service;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.SOCprojet.orderservice.dto.InventoryResponse;
import com.SOCprojet.orderservice.dto.OrderLineItemsDto;
import com.SOCprojet.orderservice.dto.OrderRequest;
import com.SOCprojet.orderservice.model.Order;
import com.SOCprojet.orderservice.model.OrderLineItems;
import com.SOCprojet.orderservice.repository.OrderRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class OrderService {
	
	 private final OrderRepository orderRepository;
	    private final WebClient webClient;

	 
	 public void placeOrder(OrderRequest orderRequest) {
	        Order order = new Order();
	        order.setOrderNumber(UUID.randomUUID().toString());

	        List<OrderLineItems> orderLineItems = orderRequest.getOrderLineItemsDtoList()
	                .stream()
	                .map(this::mapToDto)
	                .toList();

	        order.setOrderLineItemsList(orderLineItems);
	        
	        List<String> skuCodes = order.getOrderLineItemsList().stream()
	                .map(OrderLineItems::getSkuCode)
	                .toList();
	        
	        //call inventory service and place order if the product is in stock
	        
	        InventoryResponse[]  InventoryResponses = webClient.get()
	        		.uri("http://localhost:8082/api/inventory",uriBuilder -> uriBuilder.queryParam("skuCode", skuCodes).build())
	        		.retrieve()
	        		.bodyToMono(InventoryResponse[] .class)
	        		.block();
	        
	        boolean allProductsInStock = Arrays.stream(InventoryResponses)
                    .allMatch(InventoryResponse::isInStock);

	        
	        if(allProductsInStock) {
		        orderRepository.save(order);
	        }else {
	        	throw new IllegalArgumentException("Product is not in stock,please try later");
	        }
	        

	 
	 }
	 
	 
	 private OrderLineItems mapToDto(OrderLineItemsDto orderLineItemsDto) {
	        OrderLineItems orderLineItems = new OrderLineItems();
	        orderLineItems.setPrice(orderLineItemsDto.getPrice());
	        orderLineItems.setQuantity(orderLineItemsDto.getQuantity());
	        orderLineItems.setSkuCode(orderLineItemsDto.getSkuCode());
	        return orderLineItems;
	    }

}
