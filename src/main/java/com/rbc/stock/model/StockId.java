package com.rbc.stock.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;
import java.io.Serializable;
import java.time.LocalDate;

/**
 * @author Manimaran Palani
 * @since 16-Feb-2023
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
public class StockId implements Serializable {

    private String stock;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate date;
}