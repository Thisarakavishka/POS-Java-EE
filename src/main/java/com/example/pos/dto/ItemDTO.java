package com.example.pos.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ItemDTO {
    private String itemCode;
    private String itemName;
    private int qty;
    private double price;
}
