package iit.concurrentAssignment.w1810216;

import iit.concurrentAssignment.w1810216.TicketInfo.Ticket;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * The TicketMachine class represents a ticket vending machine that manages paper and toner levels
 * to print tickets for passengers.
 */
public class TicketMachine implements ServiceTicketMachine {

    private String ticketMachineId;
    private int currentPaperLevel;
    private int currentTonerLevel;
    private int numberOfTicketsPrinted;
    private ThreadGroup passengerThreadGroup;
    public static int replaceTonerCartridgeCount = 0;
    public static int refillPaperPacksCount = 0;
    public static ArrayList<String> ticketPurchasedList = new ArrayList<>();

    private final ReentrantLock lock = new ReentrantLock();
    private final Condition haveTonerLevel = lock.newCondition();
    private final Condition havePaperLevel = lock.newCondition();
    private final Condition noResource = lock.newCondition();

    Date date = new Date(System.currentTimeMillis());
    DateFormat formatter = new SimpleDateFormat("HH:mm:ss.SSS");
    String logTime = formatter.format(date);

    /**
     * Constructor for TicketMachine class.
     *
     * @param ticketMachineId       The unique identifier for the ticket machine.
     * @param passengerThreadGroup The ThreadGroup containing passenger threads.
     */
    public TicketMachine(String ticketMachineId, ThreadGroup passengerThreadGroup) {
        this.ticketMachineId = ticketMachineId;
        this.currentPaperLevel = FULL_PAPER_TRAY;
        this.currentTonerLevel = FULL_TONER_LEVEL;
        this.numberOfTicketsPrinted = 0;
        this.passengerThreadGroup = passengerThreadGroup;
    }

    @Override
    public void replaceTonerCartridge() {
        lock.lock();
        try {
            while (currentTonerLevel > MINIMUM_TONER_LEVEL) {
                if (isPrinterUsageComplete()) {
                    System.out.println(
                            "[" + logTime + "] [MACHINE] Usage of the ticket machine is complete. No need to replace toner cartridge "
                    );

                    Thread.currentThread().interrupt();
                    break;
                } else {
                    System.out.println("[" + logTime + "] [MACHINE] Toner has not reached the minimum level to be refilled. Waiting to check again ");
                    haveTonerLevel.await();
                }
            }

            if (currentTonerLevel <= MINIMUM_TONER_LEVEL) {
                currentTonerLevel = FULL_TONER_LEVEL;
                System.out.println("[" + logTime + "] [MACHINE] Replaced toner cartridge ");

                replaceTonerCartridgeCount++;
                System.out.println("[" + logTime + "] [MACHINE] " + this);
            }

            noResource.signalAll();
        } catch (InterruptedException e) {
            System.out.println("[" + logTime + "] [MACHINE] " + e.toString());
        } finally {
            lock.unlock();
        }
    }

    @Override
    public void refillTicketPaper() {
        lock.lock();
        try {
            while ((currentPaperLevel + SHEETS_PER_PACK) > FULL_PAPER_TRAY) {
                if (isPrinterUsageComplete()) {
                    System.out.println("[" + logTime + "] [MACHINE] Usage of the ticket machine is complete. No need to refill paper ");
                    Thread.currentThread().interrupt();
                    break;
                } else {
                    System.out.println("[" + logTime + "] [MACHINE] Refilling paper will exceed the capacity. Waiting to check again ");
                    havePaperLevel.await();
                }
            }

            if ((currentPaperLevel + SHEETS_PER_PACK) <= FULL_PAPER_TRAY) {
                currentPaperLevel += SHEETS_PER_PACK;
                System.out.println("[" + logTime + "] [MACHINE] Paper Technician refilled the paper of ticket machine " + ticketMachineId);

                refillPaperPacksCount++;
                System.out.println("[" + logTime + "] [MACHINE] " + this);
            }

            noResource.signalAll();

        } catch (InterruptedException e) {
            System.out.println("[" + logTime + "] [MACHINE] " + e.toString());
        } finally {
            lock.unlock();
        }
    }

    @Override
    public void printTicket(Ticket ticket) {
        lock.lock();
        try {
            while (currentPaperLevel == 0 || currentTonerLevel < MINIMUM_TONER_LEVEL) {
                String message;
                if (currentPaperLevel == 0) {
                    message = "Insufficient Papers. Waiting until refilled";
                } else if (currentTonerLevel < MINIMUM_TONER_LEVEL) {
                    message = "Insufficient Toner. Waiting until refilled";
                } else {
                    message = "Insufficient Papers and Toner. Waiting until refilled";
                }
                System.out.println("[" + logTime + "] [MACHINE] " + message);
                noResource.await();
            }

            // Print ticket if resources are still sufficient after waiting
            if (currentPaperLevel > 0 && currentTonerLevel >= MINIMUM_TONER_LEVEL) {
                printTicket();
                numberOfTicketsPrinted++;
                System.out.println(
                        "[" + logTime + "] [MACHINE] Printed ticket: " + ticket);

                System.out.println(
                        "[" + logTime + "] [MACHINE] " + this);
            }

            havePaperLevel.signalAll();
            haveTonerLevel.signalAll();
        } catch (InterruptedException e) {
            System.out.println("[" + logTime + "] [MACHINE] " + e.toString());
        } finally {
            lock.unlock();
        }
    }

    @Override
    public void purchasingTicket(Ticket ticket) {
        lock.lock();
        try {
            ticketPurchasedList.add(Thread.currentThread().getName());
            System.out.println("[" + logTime + "] [MACHINE] Ticket purchased by " + Thread.currentThread().getName());
            System.out.println("[" + logTime + "] [MACHINE] " + this);

            havePaperLevel.signalAll();
            haveTonerLevel.signalAll();
        } catch (Exception e) {
            System.out.println("[" + logTime + "] [MACHINE] " + e.toString());
        } finally {
            lock.unlock();
        }
    }

    @Override
    public int getPaperLevel() {
        return this.currentPaperLevel;
    }

    @Override
    public int getTonerLevel() {
        return this.currentTonerLevel;
    }

    /**
     * Checks whether the usage of the printer is over,
     * in order to avoid waiting in time intervals and re-checking for replacing toner cartridge / refilling paper
     *
     * @return Whether the usage of the Ticket Machine is over or not
     */
    private boolean isPrinterUsageComplete() {
        return passengerThreadGroup.activeCount() == 0;
    }

    /**
     * Reduces toner and paper levels when a ticket is printed.
     */
    private void printTicket() {
        currentTonerLevel--;
        currentPaperLevel--;
    }

    @Override
    public String toString() {
        return "TicketMachine{" +
                "ticketMachineId='" + ticketMachineId + '\'' +
                ", currentPaperLevel=" + currentPaperLevel +
                ", currentTonerLevel=" + currentTonerLevel +
                ", numberOfTicketsPrinted=" + numberOfTicketsPrinted +
                '}';
    }
}
