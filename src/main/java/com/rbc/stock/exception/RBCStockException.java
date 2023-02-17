package com.rbc.stock.exception;

import lombok.*;

/**
 * @author Manimaran Palani
 * @since 16-Feb-2023
 */

@Data
@Builder
@AllArgsConstructor
@RequiredArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class RBCStockException extends RuntimeException {
    private String stock;
    private String date;

    public RBCStockException(String stockDetails) {
        this.stock = stockDetails;
    }

    @Override
    public String getMessage() {
        return "exception occurred while processing stock " + stock + ", Please check logs for more details";
    }
}
