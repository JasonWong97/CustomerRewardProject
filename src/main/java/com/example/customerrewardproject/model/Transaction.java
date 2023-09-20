package com.example.customerrewardproject.model;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

import java.util.Date;
@Data
public class Transaction {


    private Long id;

    private Long customerId;

    private Double rewardPointsChange;

    private Date transactionDate;


}