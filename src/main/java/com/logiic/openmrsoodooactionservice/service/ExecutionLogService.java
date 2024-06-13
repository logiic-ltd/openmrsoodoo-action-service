package com.logiic.openmrsoodooactionservice.service;

import com.logiic.openmrsoodooactionservice.model.Action;
import com.logiic.openmrsoodooactionservice.model.Event;
import com.logiic.openmrsoodooactionservice.model.ExecutionLog;
import com.logiic.openmrsoodooactionservice.repository.ActionRepository;
import com.logiic.openmrsoodooactionservice.repository.EventRepository;
import com.logiic.openmrsoodooactionservice.repository.ExecutionLogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;

@Service
public class ExecutionLogService {

    @Autowired
    private ExecutionLogRepository executionLogRepository;

    @Autowired
    private ActionRepository actionRepository;

    @Autowired
    private EventRepository eventRepository;

    public void logExecution(int actionId, int eventId, String status, String message) {
        Action action = actionRepository.findById(actionId).orElseThrow(() -> new IllegalArgumentException("Invalid action ID: " + actionId));
        Event event = eventRepository.findById(eventId).orElseThrow(() -> new IllegalArgumentException("Invalid event ID: " + eventId));

        ExecutionLog log = new ExecutionLog();
        log.setAction(action);
        log.setEvent(event);
        log.setStatus(status);
        log.setMessage(message);
        log.setTimestamp(new Timestamp(System.currentTimeMillis()));

        executionLogRepository.save(log);
    }
}