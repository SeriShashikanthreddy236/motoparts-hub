package com.motoparts.vehicle.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.motoparts.exception.BusinessException;
import com.motoparts.response.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@Tag(name = "Service Health", description = "Simple endpoints for checking API availability and exception handling")
public class TestController {

    @GetMapping("/test")
    @Operation(summary = "Check API availability", description = "Returns a success response confirming that the backend API is running.")
    public ApiResponse<String> test() {

    	return new ApiResponse<String>(
    	        true,
    	        "Backend is running successfully",
    	        "MotoParts Hub API"
    	);
    }
    
    @GetMapping("/test-error")
    @Operation(summary = "Generate a test error", description = "Throws a business exception to verify API error-response handling.")
    public String testError() {
        throw new BusinessException("Test exception generated");
    }
}