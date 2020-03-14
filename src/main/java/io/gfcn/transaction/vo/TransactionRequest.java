package io.gfcn.transaction.vo;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class TransactionRequest {

  Long accountId;

  Long operationTypeId;

  BigDecimal amount;
  
}
