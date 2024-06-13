package com.logiic.openmrsoodooactionservice.model;

import javax.persistence.*;

import lombok.Data;

import java.sql.Timestamp;

@Entity
@Table(name = "execution_logs")
@Data
public class ExecutionLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "action_id", nullable = false)
    private Action action;

    @ManyToOne
    @JoinColumn(name = "event_id", nullable = false)
    private Event event;

    @Column(nullable = false)
    private String status;

    @Column(nullable = false)
    private String message;

    @Column(nullable = false)
    private Timestamp timestamp;

    // Getters and Setters

    @PrePersist
    protected void onCreate() {
        this.timestamp = new Timestamp(System.currentTimeMillis());
    }
}