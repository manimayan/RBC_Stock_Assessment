package com.rbc.stock.repository;

import com.rbc.stock.model.StockPrediction;

import java.util.List;
import java.util.Optional;

/**
 * @author Manimaran Palani
 * @since 16-Feb-2023
 */

public interface StockRepository {

    void save(StockPrediction stock);

    List<StockPrediction> getStockByTicker(String stockTicker);

    Optional<StockPrediction> findByStockAndDate(StockPrediction stockInputData);
}
