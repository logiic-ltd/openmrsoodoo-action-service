package com.logiic.openmrsoodooactionservice.action;

import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@Component
public class HandleObservationAction implements ActionInterface {

    private final RestTemplate restTemplate;

    public HandleObservationAction(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public void execute(Map<String, Object> data) {
        String observationId = (String) data.get("observationId");
        // Implement logic to handle observation
        // Example: Update Odoo based on observation data
    }
}