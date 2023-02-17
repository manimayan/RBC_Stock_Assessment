package com.rbc.stock.service.implementation;

import com.rbc.stock.exception.NoRecordExistException;
import com.rbc.stock.exception.RecordAlreadyExistsException;
import com.rbc.stock.model.StockPrediction;
import com.rbc.stock.model.StockPredictionDTO;
import com.rbc.stock.repository.StockRepository;
import com.rbc.stock.service.StockService;
import com.rbc.stock.util.DelimitedFileAdapter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * @author Manimaran Palani
 * @since 16-Feb-2023
 */

@Service
@Slf4j
public class StockServiceImpl implements StockService {

    private final DelimitedFileAdapter fileAdapter;
    private final StockRepository repository;

    @Autowired
    public StockServiceImpl(DelimitedFileAdapter adapter, StockRepository repository) {
        this.fileAdapter = adapter;
        this.repository = repository;
    }

    @Override
    public int uploadStockData(MultipartFile stockFile) {
        List<StockPrediction> processedInput = fileAdapter.mapPredictionData(stockFile);
        return persistStock(processedInput);
    }

    @Override
    public void addStockData(StockPredictionDTO stockInputData) {
        if (repository.findByStockAndDate(new StockPrediction(stockInputData)).isEmpty()) {
            repository.save(new StockPrediction(stockInputData));
        } else {
            throw new RecordAlreadyExistsException();
        }
    }

    @Override
    public List<StockPrediction> getStockByTicker(String stockTicker) {
        List<StockPrediction> tickerData = repository.getStockByTicker(stockTicker);
        if (tickerData.isEmpty()) {
            throw new NoRecordExistException();
        }
        return tickerData;
    }

    private int persistStock(List<StockPrediction> processedInput) {
        int count = 0;
        for (StockPrediction stock : processedInput) {
            try {
                repository.save(stock);
                count++;
            } catch (DataIntegrityViolationException div) {
                log.info(stock.getStockId().getStock() + " - " + stock.getStockId().getDate() + " is already existing");
            }
        }
        return count;
    }
}