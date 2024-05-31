package iit.concurrentAssignment.w1810216.Technician;

import iit.concurrentAssignment.w1810216.Machine;
import iit.concurrentAssignment.w1810216.TicketMachine;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

/**
 * TicketPaperTechnician is a class that represents a technician responsible for refilling ticket paper in a TicketMachine.
 *
 * This class extends the Technician class and overrides the run method to implement the specific behavior of a ticket paper technician.
 */
public class TicketPaperTechnician extends Technician {

    // Constants
    public static final int NUMBER_OF_RETRY = 3;

    // Instance variables
    Date date = new Date(System.currentTimeMillis());
    DateFormat formatter = new SimpleDateFormat("HH:mm:ss.SSS");
    String logTime = formatter.format(date);
    Random random = new Random();

    /**
     * Constructs a TicketPaperTechnician with the specified name, thread group, and associated machine.
     *
     * @param name         the name of the technician
     * @param threadGroup  the thread group to which the technician belongs
     * @param machine      the machine associated with the technician
     */
    public TicketPaperTechnician(String name, ThreadGroup threadGroup, Machine machine) {
        super(name, threadGroup, machine);
    }

    /**
     * Overrides the run method to implement the specific behavior of a ticket paper technician.
     */
    @Override
    public void run() {
        for(int i = 0; i < NUMBER_OF_RETRY; i++){
            try {
                System.out.println(
                        "[" + logTime + "] [Ticket Paper Technician] Requested to refill ticket paper"
                );

                // Refill ticket paper in the associated TicketMachine
                ((TicketMachine) machine).refillTicketPaper();

                System.out.println(
                        "[" + logTime + "] [Ticket Paper Technician] Ticket Machine status. " + machine.toString()
                );

                // Sleep for a random duration between 1000 and 2000 milliseconds
                sleep(random.nextInt(1000) + 1000);
            } catch (InterruptedException e){
                // Exit the loop if interrupted
                break;
            }
        }

        System.out.println("Paper Technician Finished, packs of paper used: " + TicketMachine.refillPaperPacksCount);
    }
}
