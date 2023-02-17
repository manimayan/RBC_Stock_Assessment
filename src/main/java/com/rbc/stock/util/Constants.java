package com.rbc.stock.util;

/**
 * @author Manimaran Palani
 * @since 16-Feb-2023
 */

public final class Constants {

    // STATUS MESSAGES
    public static final String SUCCESSFULLY_CREATED = "stock created successfully";
    public static final String SUCCESSFULLY_UPLOADED = "stock uploaded successfully";

    // CUSTOM RBC STOCK RESPONSE STATUS CODES
    public static final Integer SUCCESSFULLY_CREATED_CD = 2001;

    // CUSTOM RBC STOCK RESPONSE EXCEPTION CODES
    public static final Integer CANNOT_ADD_RECORD_BECAUSE_IT_ALREADY_EXISTS_CD = 4041;
    public static final Integer NO_RECORD_EXISTS_CD = 4042;

    private Constants() {
    }
}
