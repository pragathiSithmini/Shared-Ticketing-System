/**
 * Machine interface defines the contract for any class that represents a ticket vending or processing machine.
 * It includes methods for printing a ticket and handling the process of purchasing a ticket.
 * Implementing classes should provide concrete implementations for these methods based on the specific requirements
 * and functionality of the ticket machine they represent.
 */
package iit.concurrentAssignment.w1810216;

import iit.concurrentAssignment.w1810216.TicketInfo.Ticket;

public interface Machine {

    /**
     * Prints the provided ticket.
     *
     * @param ticket The ticket to be printed.
     */
    public void printTicket(Ticket ticket);

    /**
     * Handles the process of purchasing the provided ticket.
     *
     * @param ticket The ticket to be purchased.
     */
    public void purchasingTicket(Ticket ticket);
}
