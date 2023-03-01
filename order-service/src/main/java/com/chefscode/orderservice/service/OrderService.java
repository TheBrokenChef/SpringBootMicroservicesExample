package com.chefscode.orderservice.service;

import com.chefscode.orderservice.dto.OrderLineItemsDto;
import com.chefscode.orderservice.dto.OrderRequest;
import com.chefscode.orderservice.model.Order;
import com.chefscode.orderservice.model.OrderLineItems;
import com.chefscode.orderservice.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional
public class OrderService {

    private final OrderRepository orderRepository;

    public void placeOrder(OrderRequest orderRequest) {
        Order order = new Order();
        order.setOrderNumber(UUID.randomUUID().toString());
        order.setOrderLineItemsList(getOrderLineItemsList(orderRequest.getOrderLineItemsDtoList()));
        orderRepository.save(order);
    }

    private List<OrderLineItems> getOrderLineItemsList(List<OrderLineItemsDto> orderLineItemsDtoList) {
        List<OrderLineItems> orderLineItemsList = new ArrayList<OrderLineItems>();
        for(OrderLineItemsDto orderLineItemsDto : orderLineItemsDtoList) {
            OrderLineItems orderLineItems = new OrderLineItems();
            orderLineItems.setQuantity(orderLineItemsDto.getQuantity());
            orderLineItems.setPrice(orderLineItemsDto.getPrice());
            orderLineItems.setSkuCode(orderLineItemsDto.getSkuCode());
            orderLineItemsList.add(orderLineItems);
        }
        return orderLineItemsList;
    }
}
