// This package contains the class representing ticket information
package iit.concurrentAssignment.w1810216.TicketInfo;

// Importing necessary classes from other packages
import iit.concurrentAssignment.w1810216.PassengerInfo.PassengerInfo;
import iit.concurrentAssignment.w1810216.TravelInfo.TravelInfo;

import java.math.BigDecimal;

// Ticket class represents information about a ticket
public class Ticket {

    // Attributes of the Ticket class
    private final int ticketNUmber;          // Unique ticket number
    private final BigDecimal ticketPrice;    // Price of the ticket
    private final PassengerInfo passengerInfo;  // Information about the passenger
    private final TravelInfo travelInfo;    // Information about the travel

    // Constructor for creating a Ticket object with specified parameters
    public Ticket(int ticketNUmber, BigDecimal ticketPrice, PassengerInfo passengerInfo, TravelInfo travelInfo) {
        this.ticketNUmber = ticketNUmber;
        this.ticketPrice = ticketPrice;
        this.passengerInfo = passengerInfo;
        this.travelInfo = travelInfo;
    }

    // Getter method to retrieve the ticket number
    public int getTicketNUmber() {
        return ticketNUmber;
    }

    // Getter method to retrieve the ticket price
    public BigDecimal getTicketPrice() {
        return ticketPrice;
    }

    // Getter method to retrieve passenger information
    public PassengerInfo getPassengerInfo() {
        return passengerInfo;
    }

    // Getter method to retrieve travel information
    public TravelInfo getTravelInfo() {
        return travelInfo;
    }

    // Overridden toString method to provide a string representation of the Ticket object
    @Override
    public String toString() {
        return "Ticket{" +
                "ticketNUmber=" + ticketNUmber +
                ", ticketPrice=" + ticketPrice +
                ", passengerInfo=" + passengerInfo +
                ", travelInfo=" + travelInfo +
                '}';
    }
}
