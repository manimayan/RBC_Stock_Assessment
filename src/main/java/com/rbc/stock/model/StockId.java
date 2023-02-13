package com.rbc.stock.model;

import lombok.Data;

import javax.persistence.Embeddable;
import java.io.Serializable;
import java.time.LocalDate;


@Data
@Embeddable
public class StockId implements Serializable {

    private String stock;
    private LocalDate date;
}