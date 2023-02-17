package com.rbc.stock.util;

import com.rbc.stock.exception.RBCStockException;
import com.rbc.stock.model.StockPrediction;
import com.rbc.stock.model.StockPredictionDTO;
import lombok.extern.slf4j.Slf4j;
import org.beanio.BeanReader;
import org.beanio.StreamFactory;
import org.beanio.builder.DelimitedParserBuilder;
import org.beanio.builder.StreamBuilder;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Manimaran Palani
 * @since 16-Feb-2023
 */

@Component
@Slf4j
public class DelimitedFileAdapter {

    private final StreamBuilder predictionDataStream = new StreamBuilder("stockStream")
            .format("delimited")
            .parser(new DelimitedParserBuilder(','))
            .addRecord(StockPredictionDTO.class)
            .ignoreUnidentifiedRecords();


    public List<StockPrediction> mapPredictionData(MultipartFile stockFile) {

        StreamFactory factory = StreamFactory.newInstance();
        factory.define(this.predictionDataStream);
        List<StockPrediction> processedInputList = new ArrayList<>();

        try {
            BeanReader inputReader = factory.createReader("stockStream", new InputStreamReader(stockFile.getInputStream()));
            inputReader.skip(1);
            Object recordRow = null;

            while ((recordRow = inputReader.read()) != null) {
                StockPredictionDTO predictionInput = (StockPredictionDTO) recordRow;
                processedInputList.add(new StockPrediction(predictionInput));
            }
        } catch (IllegalArgumentException ie) {
            log.error("Error in parsing input file. Manually check if the file format is correct  : " + ie.getMessage());
            throw new RBCStockException(stockFile.getOriginalFilename());
        } catch (Exception ex) {
            log.error("Error reading file / data is inconsistent : " + ex.getMessage());
            throw new RBCStockException(stockFile.getOriginalFilename());
        }
        return processedInputList;
    }
}

