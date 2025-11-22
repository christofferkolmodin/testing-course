
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

@SuppressWarnings("ALL")
public class SetRequiredNumberTests {
    /*
        Input space for all methods:
        0 <= nemployee and  0 <= starttime and  0 <=endtime
    */

    @Test
    public void starttimeBiggerThanEndtimeTest() {
        int workingLength = 4;

        // Working hours between hour 0 and hour 7
        WorkSchedule schedule = new WorkSchedule(workingLength);
        // Allow 2 employees to work between hour 0 and hour 3
        schedule.setRequiredNumber(2, 0, 3);
        // Add Hannah as an employee between hour 0 and hour 2
        schedule.addWorkingPeriod("Hannah", 0, 2);

        // Save the current schedule's hours and workers during those hours
        WorkSchedule.Hour[] scheduleBefore = new WorkSchedule.Hour[workingLength];
        for (int i = 0; i < workingLength; i++) {
            scheduleBefore[i] = schedule.readSchedule(i);
        }

        // Block #1: starttime > endtime

        // Attempt to change the schedule such that starttime > endtime
        schedule.setRequiredNumber(3, 3, 1);
        // Ensure the schedule wasn't actually changed
        for (int i = 0; i < workingLength; i++) {
            WorkSchedule.Hour scheduleAfter = schedule.readSchedule(i);
            assertEquals(scheduleBefore[i].requiredNumber, scheduleAfter.requiredNumber);
            assertArrayEquals(scheduleBefore[i].workingEmployees, scheduleAfter.workingEmployees);
        }
    }

    @Test
    public void starttimeSmallerThanEndtimeTest() {
        int workingLength = 4;

        // Working hours between hour 0 and hour 3
        WorkSchedule schedule = new WorkSchedule(workingLength);
        // Allow 3 employees to work between hour 0 and hour 3
        schedule.setRequiredNumber(3, 0, 3);
        // Add 3 workers during for the allowed duration
        schedule.addWorkingPeriod("Maral", 0, 3);
        schedule.addWorkingPeriod("Sam", 0, 3);
        schedule.addWorkingPeriod("Kevin", 0, 3);

        // Block #2: nEmployee > workingEmployees should discard excess worker
        schedule.setRequiredNumber(2, 0, 3);

        for (int i = 0; i < workingLength; i++) {
            WorkSchedule.Hour hour = schedule.readSchedule(i);
            // Required workers should be lowered to 2
            assertEquals(2, hour.requiredNumber);
            // Working employees should be lowered to 2
            assertEquals(2, hour.workingEmployees.length);
        }
    }

    @Test
    public void nEmployeeSameAsRequiredNumberMeansUnchangedWorkersTest() {
        WorkSchedule schedule = new WorkSchedule(6);
        // Setup: Allow 4 workers during shift
        schedule.setRequiredNumber(5, 0, 5);
        // Add 3 workers
        schedule.addWorkingPeriod("Maral", 0, 5);
        schedule.addWorkingPeriod("Sam", 0, 5);
        schedule.addWorkingPeriod("Kevin", 0, 5);

        // Block #3: if nemployee doesn't exceed requiredNumber, then requiredNumber and workingEmployees
        // should both be unchanged

        schedule.setRequiredNumber(4, 0, 2);
        WorkSchedule.Hour hour = schedule.readSchedule(2);
        // Required workers should be lowered to 4 and workers remain at 3
        assertEquals(4, hour.requiredNumber);
        assertEquals(3, hour.workingEmployees.length);

        schedule.setRequiredNumber(3, 0, 2);
        WorkSchedule.Hour hour2 = schedule.readSchedule(2);
        // Required workers should be lowered to 3 and workers remain at 3
        assertEquals(3, hour2.requiredNumber);
        assertEquals(3, hour2.workingEmployees.length);
    }

    // Border case: alternating amount of workers yields the expected result
    @Test
    public void alternatingActiveWorkers() {
        int workingLength = 4;

        // Working hours between hour 0 and hour 3
        WorkSchedule schedule = new WorkSchedule(workingLength);
        // Allow 3 employees to work between hour 0 and hour 3
        schedule.setRequiredNumber(4, 0, 3);
        // Add 3 workers during for the allowed duration
        schedule.addWorkingPeriod("Maral", 0, 3);
        schedule.addWorkingPeriod("Sam", 1, 3);
        schedule.addWorkingPeriod("Kevin", 2, 3);
        schedule.addWorkingPeriod("Hannah", 3, 3);

        for (int i = 0; i < workingLength; i++) {
            WorkSchedule.Hour hour = schedule.readSchedule(i);
            // Required workers should remain at 4 for all hours
            assertEquals(4, hour.requiredNumber);
            // Expected working employees should increase from 1->2->3->4 for each iteration
            assertEquals(i+1, hour.workingEmployees.length);
        }
    }

    // Border case: If adding workers beyond allowed amount, the amount of workers should stay at allowed amount
    @Test
    public void addMoreWorkersThanAllowedAmount() {
        int workingLength = 4;

        // Working hours between hour 0 and hour 3
        WorkSchedule schedule = new WorkSchedule(workingLength);
        // Allow 3 employees to work between hour 0 and hour 3
        schedule.setRequiredNumber(2, 0, 3);
        // Add 3 workers during for the allowed duration
        schedule.addWorkingPeriod("Maral", 0, 3);
        schedule.addWorkingPeriod("Sam", 0, 3);
        schedule.addWorkingPeriod("Kevin", 0, 3);

        for (int i = 0; i < workingLength; i++) {
            WorkSchedule.Hour hour = schedule.readSchedule(i);
            // Required workers should remain at 2 for all hours
            assertEquals(2, hour.requiredNumber);
            // Amount of workers should stay at 2 for all hours
            assertEquals(2, hour.workingEmployees.length);
        }
    }

}

