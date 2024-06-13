package com.logiic.openmrsoodooactionservice.repository;

import com.logiic.openmrsoodooactionservice.model.Action;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ActionRepository extends JpaRepository<Action, Integer> {
}