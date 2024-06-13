package com.logiic.openmrsoodooactionservice.service;

import com.logiic.openmrsoodooactionservice.model.Event;
import com.logiic.openmrsoodooactionservice.repository.EventRepository;
import com.logiic.openmrsoodooactionservice.service.ActionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Service;

import java.sql.PreparedStatement;
import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

@Service
public class FeedService {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private ActionService actionService;

    @Autowired
    private EventRepository eventRepository;

    public void processNewFeeds(String feedType) {
        String sql = "SELECT * FROM feeds WHERE type = ? AND processed = false";
        List<Map<String, Object>> feeds = jdbcTemplate.queryForList(sql, feedType);

        for (Map<String, Object> feed : feeds) {
            try {
                String feedContent = (String) feed.get("content");
                Map<String, Object> eventData = parseFeedContent(feedContent);

                // Insert the event into the events table
                int eventId = insertEvent(feed, feedType);

                // Process the event
                processEvent(eventData, eventId, feedType);

                // Mark the feed as processed
                markFeedAsProcessed((Integer) feed.get("id"));
            } catch (Exception e) {
                // Handle exception and possibly log it
                e.printStackTrace();
            }
        }
    }

    private Map<String, Object> parseFeedContent(String feedContent) {
        // Implement parsing logic to extract event data from feed content
        return new HashMap<>();
    }

    private int insertEvent(Map<String, Object> feed, String source) {
        Event event = new Event();
        event.setName(feed.get("type").toString());
        event.setSource(source);
        event.setPayload((String) feed.get("content"));
        event.setCreatedAt(new Timestamp(System.currentTimeMillis()));
        event = eventRepository.save(event);
        return event.getId();
    }

    private void markFeedAsProcessed(int feedId) {
        String sql = "UPDATE feeds SET processed = true WHERE id = ?";
        jdbcTemplate.update(sql, feedId);
    }

    private void processEvent(Map<String, Object> eventData, int eventId, String feedType) throws Exception {
        switch (feedType) {
            case "encounter":
                actionService.executeAction("HandleEncounterAction", eventData, eventId);
                break;
            case "obs":
                actionService.executeAction("HandleObservationAction", eventData, eventId);
                break;
            case "admission":
                actionService.executeAction("HandleAdmissionAction", eventData, eventId);
                break;
            default:
                throw new IllegalArgumentException("Unknown feed type: " + feedType);
        }
    }
}