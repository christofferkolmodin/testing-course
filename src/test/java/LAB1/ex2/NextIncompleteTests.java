
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

@SuppressWarnings("ALL")
public class NextIncompleteTests {

    /*
        Input space for all methods:
        currenttime >= 0 and currenttime < size
    */

    @Test
    public void testIncompleteWorkerAmountWithNoWorkers() {
        WorkSchedule schedule = new WorkSchedule(3);

        // Block 1: (workingEmployees < requiredNumbers) -> returns hour nearest to currentTime
        schedule.setRequiredNumber(1, 0, 2);

        int nextIncomplete = schedule.nextIncomplete(0);
        // Hour 0 has 0 workers and requiredNumber = 1, so nextIncomplete should be 0
        assertEquals(0,  nextIncomplete);

        // Schedule should remain unchanged
        for (int i = 0; i < 3; i++) {
            WorkSchedule.Hour hour = schedule.readSchedule(i);
            assertEquals(1, hour.requiredNumber);
            assertEquals(0, hour.workingEmployees.length);
        }
    }

    @Test
    public void testIncompleteWorkerAmountWithMoreThan0Workers() {
        WorkSchedule schedule = new WorkSchedule(4);

        // Block 1: (workingEmployees < requiredNumbers) -> returns hour nearest to currentTime
        schedule.setRequiredNumber(2, 0, 3);
        schedule.addWorkingPeriod("Hannah", 1, 3);

        int nextIncomplete = schedule.nextIncomplete(1);
        // Hour 1 has 1 workers and requiredNumber = 2, so nextIncomplete should be 1
        assertEquals(1,  nextIncomplete);

        // Schedule should remain unchanged
        for (int i = 0, j = 1; i < 3; i++) {
            // Test requiredNumber for all hours
            WorkSchedule.Hour hourI = schedule.readSchedule(i);
            assertEquals(1, hourI.requiredNumber);

            // Test working employees for all working hours
            WorkSchedule.Hour hourJ = schedule.readSchedule(j);
            assertEquals(1, hourJ.workingEmployees.length);
        }

    }

    @Test
    public void testWorkerAmountEqualToRequiredNumber() {
        WorkSchedule schedule = new WorkSchedule(3);

        // Block 2: (workingEmployees >= requiredNumbers)
        schedule.setRequiredNumber(1, 0, 2);
        schedule.addWorkingPeriod("Hannah", 0, 2);

        int nextIncomplete = schedule.nextIncomplete(0);

        // When workingEmployees >= requiredNumbers, nextIncomplete should return -1
        assertEquals(-1, nextIncomplete);

        // Schedule should remain unchanged
        for (int i = 0; i < 3; i++) {
            WorkSchedule.Hour hour = schedule.readSchedule(i);
            assertEquals(1, hour.requiredNumber);
            assertEquals(1, hour.workingEmployees.length);
        }
    }

    @Test
    public void testWorkerAmountMoreThanRequiredNumber() {
        WorkSchedule schedule = new WorkSchedule(3);

        // Block 2: (workingEmployees >= requiredNumbers)
        schedule.setRequiredNumber(1, 0, 2);
        schedule.addWorkingPeriod("Hannah", 0, 2);
        schedule.addWorkingPeriod("Peter", 0, 2);
        schedule.addWorkingPeriod("Rob", 0, 2);

        int nextIncomplete = schedule.nextIncomplete(0);
        // When workingEmployees >= requiredNumbers, nextIncomplete should return -1
        assertEquals(-1, nextIncomplete);

        // Schedule should remain unchanged
        for (int i = 0; i < 3; i++) {
            WorkSchedule.Hour hour = schedule.readSchedule(i);
            assertEquals(1, hour.requiredNumber);
            assertEquals(1, hour.workingEmployees.length);
        }
    }


}
