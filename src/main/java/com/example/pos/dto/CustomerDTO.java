package com.example.pos.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class CustomerDTO {
    private String customerId;
    private String customerName;
    private String address;
    private double salary;
}
