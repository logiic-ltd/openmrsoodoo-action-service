package com.logiic.openmrsoodooactionservice.action;

import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@Component
public class HandleEncounterAction implements ActionInterface {

    private final RestTemplate restTemplate;

    public HandleEncounterAction(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public void execute(Map<String, Object> data) {
        String encounterId = (String) data.get("encounterId");
        // Implement logic to handle encounter
        // Example: Update Odoo based on encounter data
    }
}