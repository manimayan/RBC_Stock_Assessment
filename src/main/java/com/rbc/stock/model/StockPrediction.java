package com.rbc.stock.model;

import com.rbc.stock.exception.RBCStockException;
import lombok.*;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.Hibernate;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

/**
 * @author Manimaran Palani
 * @since 16-Feb-2023
 */

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Slf4j
@Entity
@Table(name = "stock_prediction_data")
public class StockPrediction implements Serializable {
    private int quarter;
    @EmbeddedId
    private StockId stockId;
    private BigDecimal open;
    private BigDecimal high;
    private BigDecimal low;
    private BigDecimal close;
    private long volume;
    @Column(name = "percent_change_price")
    private double percentChangePrice;
    @Column(name = "percent_change_volume_over_last_week")
    private double percentChangeVolumeOverLastWeek;
    @Column(name = "previous_weeks_volume")
    private long previousWeeksVolume;
    @Column(name = "next_weeks_open")
    private BigDecimal nextWeeksOpen;
    @Column(name = "next_weeks_close")
    private BigDecimal nextWeeksClose;
    @Column(name = "percent_change_next_weeks_price")
    private double percentChangeNextWeeksPrice;
    @Column(name = "days_to_next_dividend")
    private int daysToNextDividend;
    @Column(name = "percent_return_next_dividend")
    private double percentReturnNextDividend;

    public StockPrediction(StockPredictionDTO input) {
        this.quarter = input.getQuarter();
        this.stockId = new StockId(input.getStock(), LocalDate.parse(input.getDate(), DateTimeFormatter.ofPattern("M/d/yyyy")));
        this.open = new BigDecimal(input.getOpen().substring(1));
        this.high = new BigDecimal(input.getHigh().substring(1));
        this.low = new BigDecimal(input.getLow().substring(1));
        this.close = new BigDecimal(input.getClose().substring(1));
        this.volume = input.getVolume();
        this.percentChangePrice = input.getPercentChangePrice();
        this.percentChangeVolumeOverLastWeek = input.getPercentChangeVolumeOverLastWeek();
        this.previousWeeksVolume = input.getPreviousWeeksVolume();
        this.nextWeeksOpen = new BigDecimal(input.getNextWeeksOpen().substring(1));
        this.nextWeeksClose = new BigDecimal(input.getNextWeeksClose().substring(1));
        this.percentChangeNextWeeksPrice = input.getPercentChangeNextWeeksPrice();
        this.daysToNextDividend = input.getDaysToNextDividend();
        this.percentReturnNextDividend = input.getPercentReturnNextDividend();
    }

    public static void validate(MultipartFile stockFile) {
        if (Objects.requireNonNull(stockFile.getOriginalFilename()).isEmpty()) {
            throw new RBCStockException("not a valid file");
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        StockPrediction that = (StockPrediction) o;
        return stockId != null && Objects.equals(stockId, that.stockId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(stockId);
    }
}
