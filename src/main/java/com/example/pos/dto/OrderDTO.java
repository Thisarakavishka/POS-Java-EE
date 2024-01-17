package com.example.pos.dto;

import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class OrderDTO {
    private String orderId;
    private String date;
    private double total;
    private double discount;
    private double balance;
    private CustomerDTO customer;
    private List<ItemDTO> items;
}
