package io.gfcn.transaction.model;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
public class Transaction {
    
    @Id
    @GeneratedValue
    Long transactionId;
    
    @Column
    Long accountId;
    
    @Column
    Long operationTypeId;
    
    @Column
    BigDecimal amount;
    
    
    
}
