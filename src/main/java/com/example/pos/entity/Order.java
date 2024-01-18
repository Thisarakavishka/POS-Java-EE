package com.example.pos.entity;

import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Order {
    private String orderId;
    private String date;
    private double total;
    private double discount;
    private double balance;
    private Customer customer;
    private List<Item> items;
}
