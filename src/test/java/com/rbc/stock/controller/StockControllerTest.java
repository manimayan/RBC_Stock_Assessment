package com.rbc.stock.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rbc.stock.model.StockPrediction;
import com.rbc.stock.model.StockPredictionDTO;
import com.rbc.stock.service.StockService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(MockitoJUnitRunner.Silent.class)
public class StockControllerTest {

    @InjectMocks
    StockController stockController;
    @Mock
    StockService stockService;
    private MockMvc mockMvc;
    private RequestBuilder requestBuilder;

    @Before
    public void setUp() throws Exception {
        mockMvc = MockMvcBuilders.standaloneSetup(stockController).build();
    }

    @Test
    public void uploadStockDataSuccess() throws Exception {
        buildRequest("/rbc/stock/upload", null, new MockMultipartFile("file", "uploadStockDataSuccess".getBytes()), "FILE");
        when(stockService.uploadStockData(any())).thenReturn((1));
        mockMvc.perform(requestBuilder).andExpect(status().is2xxSuccessful());
    }

    @Test
    public void uploadStockDataAlreadyExists() throws Exception {
        buildRequest("/rbc/stock/upload", null, new MockMultipartFile("file", "uploadStockDataSuccess".getBytes()), "FILE");
        when(stockService.uploadStockData(any())).thenReturn((0));
        mockMvc.perform(requestBuilder).andExpect(status().is2xxSuccessful());
    }

    @Test
    public void addStockDataSuccess() throws Exception {
        StockPredictionDTO inputData = buildStockPredictionData();
        ObjectMapper mapper = new ObjectMapper();
        buildRequest("/rbc/stock/add", mapper.writeValueAsString(inputData), null, "POST");
        doNothing().when(stockService).addStockData(any());
        mockMvc.perform(requestBuilder).andExpect(status().is2xxSuccessful());
    }

    @Test
    public void addStockDataFailed() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        buildRequest("/rbc/stock/add", mapper.writeValueAsString(null), null, "POST");
        doNothing().when(stockService).addStockData(any());
        mockMvc.perform(requestBuilder).andExpect(status().isBadRequest());
    }

    @Test
    public void getStockByTickerSuccess() throws Exception {
        String stockTicker = "AA";
        buildRequest("/rbc/stock/get/{stockTicker}", stockTicker, null, "GET");
        when(stockService.getStockByTicker(any())).thenReturn((new ArrayList<StockPrediction>()));
        mockMvc.perform(requestBuilder).andExpect(status().is2xxSuccessful());
    }

    @Test
    public void getStockByTickerFailed() throws Exception {
        buildRequest("/rbc/stock/get/{stockTicker}", null, null, "GET");
        when(stockService.getStockByTicker(any())).thenReturn((new ArrayList<>()));
        mockMvc.perform(requestBuilder).andExpect(status().is4xxClientError());
    }

    public void buildRequest(String path, String payload, MockMultipartFile file, String requestType) {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.set("Content-Type", MediaType.APPLICATION_JSON.toString());
        if (requestType.equalsIgnoreCase("GET"))
            requestBuilder = MockMvcRequestBuilders.get(path, payload);
        else if (requestType.equalsIgnoreCase("POST")) {
            requestBuilder = MockMvcRequestBuilders.post(path).accept("application/json").headers(httpHeaders).content(payload);
        } else if (requestType.equalsIgnoreCase("FILE")) {
            requestBuilder = MockMvcRequestBuilders.multipart(path).file(file);
        }
    }

    private StockPredictionDTO buildStockPredictionData() {
        return new StockPredictionDTO(1, "AA", "1/2/2011", "16",
                "16", "16", "16", 138428495, -2.47066,
                -43.02495926, 242963398, "16",
                "16", 1.63831, 12, 0.189994);
    }
}
