package com.example.customerrewardproject.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Entity
@Table(name = "transaction")
@Data
public class TransactionEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column
    private Long id;
    @Column
    private Long customerId;
    @Column
    private Double rewardPointsChange;
    @Column
    private Date transactionDate;


}
