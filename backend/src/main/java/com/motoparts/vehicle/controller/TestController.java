package com.motoparts.vehicle.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.motoparts.response.ApiResponse;

@RestController
public class TestController {

    @GetMapping("/test")
    public ApiResponse<String> test() {

    	return new ApiResponse<String>(
    	        true,
    	        "Backend is running successfully",
    	        "MotoParts Hub API"
    	);
    }
    
    @GetMapping("/test-error")
    public String testError() {
        throw new RuntimeException("Test exception generated");
    }
}