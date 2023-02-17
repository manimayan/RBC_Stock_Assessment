package com.rbc.stock.service;


import com.rbc.stock.model.StockPrediction;
import com.rbc.stock.model.StockPredictionDTO;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * @author Manimaran Palani
 * @since 16-Feb-2023
 */

public interface StockService {

    int uploadStockData(MultipartFile stockData);

    void addStockData(StockPredictionDTO stockInputData);

    List<StockPrediction> getStockByTicker(String stockTicker);
}
