package com.example.sop_final_63070076.Service;

import org.springframework.stereotype.Service;

@Service
public class CalculatorPriceService {
    public double getPrice(double cost, double profit) {
        return cost + profit;
    }
}