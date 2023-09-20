package com.example.customerrewardproject.util;

public class RewardPointsCalculator {

    public static Double getPointsBySpent(double spent){
        double points=0;
        if(spent>100){
            points+=(spent-100)*2;
        }
        if(spent>50){
            points+=spent-50;
        }

        return  points;
    }
}
