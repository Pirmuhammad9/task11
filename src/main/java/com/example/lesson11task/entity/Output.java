package com.example.lesson11task.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.ManyToOne;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Output {
    @javax.persistence.Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer Id;
    private String date;
    @ManyToOne
    private Warehouse warehouse;

    @ManyToOne
    private Currency currency;

    private String factureNumber;

    private String code;

    @ManyToOne
    private Client client;
}
