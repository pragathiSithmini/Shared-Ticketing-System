/**
 * Technician is an abstract class representing a thread that interacts with a Machine.
 * It extends the Thread class and is designed to be subclassed for specific types of technicians.
 *
 * @author Pragthi Sithmini
 * @version 1.0
 */
package iit.concurrentAssignment.w1810216.Technician;

import iit.concurrentAssignment.w1810216.Machine;

public abstract class Technician extends Thread{

    protected Machine machine;

    /**
     * Constructor for creating a Technician thread with a specified name, thread group, and associated Machine.
     *
     * @param name         the name of the technician
     * @param threadGroup  the thread group to which the technician belongs
     * @param machine      the Machine with which the technician interacts
     */
    public Technician(String name, ThreadGroup threadGroup, Machine machine) {
        super(threadGroup, name);
        this.machine = machine;
    }
}
