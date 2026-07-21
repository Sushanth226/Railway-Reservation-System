package com.railway.model;

/**
 * Represents the status of a railway ticket.
 * Enums are implicitly serializable in Java, so we do not need to implement Serializable here.
 */
public enum TicketStatus {
    
    /**
     * The ticket is confirmed and active.
     */
    BOOKED, 
    
    /**
     * The ticket has been cancelled by the passenger.
     */
    CANCELLED,

    /**
     * The ticket is on the waiting list.
     */
    WAITLISTED
}
