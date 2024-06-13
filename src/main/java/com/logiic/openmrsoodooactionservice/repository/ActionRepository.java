package com.logiic.openmrsoodooactionservice.repository;

import com.logiic.openmrsoodooactionservice.model.Action;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ActionRepository extends JpaRepository<Action, Integer> {
	Optional<Action> findByName(String name);
}