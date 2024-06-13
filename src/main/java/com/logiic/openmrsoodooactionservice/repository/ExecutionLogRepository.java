package com.logiic.openmrsoodooactionservice.repository;

import com.logiic.openmrsoodooactionservice.model.ExecutionLog;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExecutionLogRepository extends JpaRepository<ExecutionLog, Integer> {
}