package com.rbc.stock.exception;


import lombok.*;

/**
 * @author Manimaran Palani
 * @since 16-Feb-2023
 */

@EqualsAndHashCode(callSuper = true)
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class NoRecordExistException extends RuntimeException {

    private String stockTicker;

    @Override
    public String getMessage() {
        return "no record exists for stockTicker : " + stockTicker;
    }
}
