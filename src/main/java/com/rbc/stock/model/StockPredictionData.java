package com.rbc.stock.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.Hibernate;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Entity
@Table(name = "stock_prediction_data")
public class StockPredictionData implements Serializable {

    private int quarter;
    @EmbeddedId
    private StockId stockId;

    private BigDecimal open;

    private BigDecimal high;

    private BigDecimal low;

    private BigDecimal close;

    private Long volume;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        StockPredictionData that = (StockPredictionData) o;
        return stockId != null && Objects.equals(stockId, that.stockId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(stockId);
    }
}
