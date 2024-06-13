package com.logiic.openmrsoodooactionservice.action;

import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@Component
public class HandleAdmissionAction implements ActionInterface {

    private final RestTemplate restTemplate;

    public HandleAdmissionAction(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public void execute(Map<String, Object> data) {
        String admissionId = (String) data.get("admissionId");
        // Implement logic to handle admission
        // Example: Update Odoo based on admission data
    }
}