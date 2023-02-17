package com.rbc.stock;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

/**
 * @author Manimaran Palani
 * @since 16-Feb-2023
 */

@SpringBootApplication
@EntityScan(basePackages = {"com.rbc.stock.model"})
public class RBCAssessmentApplication {

    public static void main(String[] args) {
        SpringApplication.run(RBCAssessmentApplication.class, args);
    }

}
