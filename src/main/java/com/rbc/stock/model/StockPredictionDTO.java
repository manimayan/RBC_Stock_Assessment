package com.rbc.stock.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.beanio.annotation.Field;
import org.beanio.annotation.Record;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;

/**
 * @author Manimaran Palani
 * @since 16-Feb-2023
 */

@Data
@Record
@Validated
@AllArgsConstructor
@NoArgsConstructor
public class StockPredictionDTO implements Serializable {

    @Field(maxOccurs = 1, minOccurs = 0)
    private int quarter;

    @NotBlank(message = "Invalid stock")
    @Field(required = true)
    private String stock;

    @NotBlank(message = "Invalid date")
    @Field(required = true)
    private String date;

    @Field(maxOccurs = 1, minOccurs = 0)
    private String open;

    @Field(maxOccurs = 1, minOccurs = 0)
    private String high;

    @Field(maxOccurs = 1, minOccurs = 0)
    private String low;

    @Field(maxOccurs = 1, minOccurs = 0)
    private String close;

    @Valid
    @Field(maxOccurs = 1, minOccurs = 0)
    private long volume;

    @Field(maxOccurs = 1, minOccurs = 0)
    private double percentChangePrice;

    @Field(maxOccurs = 1, minOccurs = 0)
    private double percentChangeVolumeOverLastWeek;

    @Field(maxOccurs = 1, minOccurs = 0)
    private long previousWeeksVolume;

    @Field(maxOccurs = 1, minOccurs = 0)
    private String nextWeeksOpen;

    @Field(maxOccurs = 1, minOccurs = 0)
    private String nextWeeksClose;

    @Field(maxOccurs = 1, minOccurs = 0)
    private double percentChangeNextWeeksPrice;

    @Field(maxOccurs = 1, minOccurs = 0)
    private int daysToNextDividend;

    @Field(maxOccurs = 1, minOccurs = 0)
    private double percentReturnNextDividend;
}
