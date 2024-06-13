package com.logiic.openmrsoodooactionservice.action;

import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@Component
public class HandleOrderAction implements ActionInterface {

    private final RestTemplate restTemplate;

    public HandleOrderAction(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public void execute(Map<String, Object> data) {
        String orderId = (String) data.get("orderId");
        String bedType = (String) data.get("bedType");
        Double basePrice = (Double) data.get("basePrice");

        // Define multipliers for bed types
        Map<String, Double> multipliers = Map.of(
            "Common bed", 1.0,
            "Deluxe bed", 1.5,
            "Shared bed", 1.2
        );

        // Calculate new price based on bed type multiplier
        Double multiplier = multipliers.getOrDefault(bedType, 1.0);
        Double updatedPrice = basePrice * multiplier;

        // Construct payload for Odoo API
        Map<String, Object> payload = Map.of(
            "orderId", orderId,
            "updatedPrice", updatedPrice
        );

        // Update the quotation in Odoo
        String odooApiUrl = "https://odoo-instance/api/quotations/" + orderId;
        restTemplate.put(odooApiUrl, payload);
    }
}