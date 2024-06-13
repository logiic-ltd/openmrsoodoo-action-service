package com.logiic.openmrsoodooactionservice.action;

import java.util.Map;

public interface ActionInterface {
    void execute(Map<String, Object> data) throws Exception;
}