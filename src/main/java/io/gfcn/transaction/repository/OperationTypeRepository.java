package io.gfcn.transaction.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import io.gfcn.transaction.model.OperationType;

@Repository
public interface OperationTypeRepository extends JpaRepository<OperationType, Long> {

}