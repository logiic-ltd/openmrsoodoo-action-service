package com.logiic.openmrsoodooactionservice.repository;

import com.logiic.openmrsoodooactionservice.model.Criteria;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CriteriaRepository extends JpaRepository<Criteria, Integer> {
    List<Criteria> findByActionId(int actionId);
}