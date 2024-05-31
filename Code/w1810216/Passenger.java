/**
 * Passenger class represents a passenger in the ticketing system.
 * Each passenger requests to purchase a ticket, and once purchased,
 * the passenger requests to print the ticket using a Ticket Machine.
 * The class implements the Runnable interface for concurrent execution
 * in a multi-threaded environment.
 *
 * @author [Your Name]
 * @version 1.0
 * @since [Date]
 */
package iit.concurrentAssignment.w1810216;

import iit.concurrentAssignment.w1810216.TicketInfo.Ticket;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

import static java.lang.Thread.sleep;

public class Passenger implements Runnable {

    private final Machine machine;
    private final Ticket ticket;

    // Date and time formatting variables
    Date date = new Date(System.currentTimeMillis());
    DateFormat formatter = new SimpleDateFormat("HH:mm:ss.SSS");
    String logTime = formatter.format(date);

    // Random generator for sleep duration
    Random random = new Random();

    /**
     * Constructs a Passenger with the specified Ticket Machine and Ticket.
     *
     * @param machine The Ticket Machine for ticket-related operations.
     * @param ticket  The ticket associated with the passenger.
     */
    public Passenger(Machine machine, Ticket ticket) {
        this.machine = machine;
        this.ticket = ticket;
    }

    /**
     * Run method to be executed concurrently when a Passenger thread is started.
     * Initiates the process of purchasing and printing a ticket.
     */
    @Override
    public void run() {
        // Request to purchase a ticket
        System.out.println(
                "[" + logTime + "] [Passenger " + this.ticket.getPassengerInfo().getPassengerName() + "] is requesting to purchase ticket: " + ticket
        );
        machine.purchasingTicket(ticket);
        System.out.println(
                "[" + logTime + "] [Passenger " + this.ticket.getPassengerInfo().getPassengerName() + "] Purchased ticket: " + ticket
        );

        // Iterate through the purchased ticket list to find the current passenger's ticket
        for (int i = 0; i < TicketMachine.ticketPurchasedList.size(); i++) {
            if (TicketMachine.ticketPurchasedList.get(i).equalsIgnoreCase(Thread.currentThread().getName())) {
                // Request to print the purchased ticket
                System.out.println(
                        "[" + logTime + "] [Passenger " + this.ticket.getPassengerInfo().getPassengerName() + "] Requesting to print ticket: " + ticket
                );

                try {
                    // Perform printing and simulate a delay
                    machine.printTicket(ticket);
                    System.out.println(
                            "[" + logTime + "] [Passenger " + this.ticket.getPassengerInfo().getPassengerName() + "] Printed ticket: " + ticket
                    );
                    sleep(random.nextInt(1000) + 1000);

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
