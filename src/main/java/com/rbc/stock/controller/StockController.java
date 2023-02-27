package com.rbc.stock.controller;


import com.rbc.stock.exception.NoRecordExistException;
import com.rbc.stock.exception.RBCStockException;
import com.rbc.stock.exception.RecordAlreadyExistsException;
import com.rbc.stock.exception.ServiceResponse;
import com.rbc.stock.model.StockPrediction;
import com.rbc.stock.model.StockPredictionDTO;
import com.rbc.stock.service.StockService;
import com.rbc.stock.util.Constants;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.util.List;


/**
 * @author Manimaran Palani
 * @since 16-Feb-2023
 */

@RestController
@Slf4j
@Validated
@Api(tags = "RBC Stock API")
@RequestMapping("/rbc/stock")
public class StockController {

    private final StockService service;

    @Autowired
    public StockController(StockService service) {
        this.service = service;
    }

    @PostMapping(value = "/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @ApiOperation(value = "The API upload a batch of stock record to RBC Stock Service.", response = ServiceResponse.class)
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Data created Successfully"),
            @ApiResponse(code = 500, message = "Internal server error")})
    public ResponseEntity<ServiceResponse> uploadStockData(@ApiParam(value = "stock data file", required = true)
                                                           @RequestPart("file") MultipartFile stockFile) {
        ServiceResponse response;
        try {
            StockPrediction.validate(stockFile);
            log.info("uploading stock data: " + stockFile.getOriginalFilename());
            int count = service.uploadStockData(stockFile);
            response = ServiceResponse.builder()
                    .rbcServiceCode(Constants.SUCCESSFULLY_CREATED_CD)
                    .message(Constants.SUCCESSFULLY_UPLOADED + ", uploaded record count : " + count)
                    .build();
        } catch (Exception ex) {
            log.error("exception occurred uploading stock data: " + ex.getMessage());
            throw new RBCStockException(stockFile.getOriginalFilename());
        }
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @ApiOperation(value = "The API adds a single stock record to RBC Stock Service.", response = ServiceResponse.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Data already exists"),
            @ApiResponse(code = 201, message = "Data created Successfully"),
            @ApiResponse(code = 400, message = "Bad Request"),
            @ApiResponse(code = 500, message = "Internal server error")})
    @PostMapping(value = "/add")
    public ResponseEntity<ServiceResponse> addStockData(@ApiParam(value = "stock record", required = true)
                                                        @RequestBody @Valid StockPredictionDTO stockInputData) {
        ServiceResponse response;
        log.info("adding stock data: " + stockInputData.getStock() + " - " + stockInputData.getDate());
        try {
            service.addStockData(stockInputData);
            response = ServiceResponse.builder()
                    .rbcServiceCode(Constants.SUCCESSFULLY_CREATED_CD)
                    .message(Constants.SUCCESSFULLY_CREATED)
                    .build();
        } catch (RecordAlreadyExistsException ex) {
            throw new RecordAlreadyExistsException(stockInputData.getStock(), stockInputData.getDate());
        } catch (Exception ex) {
            log.error("exception occurred while adding stock: " + ex.getMessage());
            throw new RBCStockException(stockInputData.getStock(), stockInputData.getDate());
        }
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @ApiOperation(value = "The API fetches list of stock for a corresponding stock ticker.", response = Iterable.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Data fetch successful", response = Iterable.class),
            @ApiResponse(code = 500, message = "Internal server error")})
    @GetMapping(value = "/get/{stockTicker}")
    public List<StockPrediction> getStockByTicker(@ApiParam(value = "stock ticker", example = "AA", required = true)
                                                  @PathVariable("stockTicker") @NotBlank String stockTicker) {
        log.info("getting stock data for ticker : " + stockTicker);
        try {
            return service.getStockByTicker(stockTicker);
        } catch (NoRecordExistException ex) {
            throw new NoRecordExistException(stockTicker);
        } catch (Exception ex) {
            log.error("exception occurred while retrieving stock data : " + ex.getMessage());
            throw new RBCStockException(stockTicker);
        }
    }
}
