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
public class RecordAlreadyExistsException extends RuntimeException {
    private String stock;
    private String date;

    @Override
    public String getMessage() {
        return stock + "-" + date + " stock already exists";
    }
}
