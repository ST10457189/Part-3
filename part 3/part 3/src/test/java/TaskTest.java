import com.mycompany.part3.Task;
import java.util.ArrayList;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


public class TaskTest {

    private ArrayList<Task> tasks;

    public TaskTest() {
        tasks = new ArrayList<>();
        tasks.add(new Task("Create Login", "Create Login to authenticate users", "Mike Smith", 5, "To Do", 0));
        tasks.add(new Task("Create Add Features", "Add features to the system", "Edward Harrison", 8, "Doing", 1));
        tasks.add(new Task("Create Reports", "Generate reports for the system", "Samantha Paulson", 2, "Done", 2));
        tasks.add(new Task("Add Arrays", "Add array data structure", "Glenda Oberholzer", 11, "To Do", 3));
    }

    @Test
    public void testTasksAddedCorrectly() {
        assertEquals(4, tasks.size(), "There should be 4 tasks in the list.");

        assertEquals("Mike Smith", tasks.get(0).getDeveloperName(), "First task developer should be Mike Smith.");
        assertEquals("Edward Harrison", tasks.get(1).getDeveloperName(), "Second task developer should be Edward Harrison.");
        assertEquals("Samantha Paulson", tasks.get(2).getDeveloperName(), "Third task developer should be Samantha Paulson.");
        assertEquals("Glenda Oberholzer", tasks.get(3).getDeveloperName(), "Fourth task developer should be Glenda Oberholzer.");
    }

    @Test
    public void testTaskDurations() {
        assertEquals(5, tasks.get(0).getTaskDuration(), "First task duration should be 5 hours.");
        assertEquals(8, tasks.get(1).getTaskDuration(), "Second task duration should be 8 hours.");
        assertEquals(2, tasks.get(2).getTaskDuration(), "Third task duration should be 2 hours.");
        assertEquals(11, tasks.get(3).getTaskDuration(), "Fourth task duration should be 11 hours.");
    }

    @Test
    public void testTaskStatus() {
        assertEquals("To Do", tasks.get(0).getTaskStatus(), "First task status should be 'To Do'.");
        assertEquals("Doing", tasks.get(1).getTaskStatus(), "Second task status should be 'Doing'.");
        assertEquals("Done", tasks.get(2).getTaskStatus(), "Third task status should be 'Done'.");
        assertEquals("To Do", tasks.get(3).getTaskStatus(), "Fourth task status should be 'To Do'.");
    }

    @Test
    public void testSearchByDeveloperName() {
        String developerToSearch = "Samantha Paulson";
        Task foundTask = null;

        // Normal for loop to search task
        for (int i = 0; i < tasks.size(); i++) {
            if (tasks.get(i).getDeveloperName().equalsIgnoreCase(developerToSearch)) {
                foundTask = tasks.get(i);
                break;
            }
        }

        assertNotNull(foundTask, "Task assigned to 'Samantha Paulson' should be found.");
        assertEquals("Create Reports", foundTask.getTaskName(), "The task assigned to Samantha Paulson should be 'Create Reports'.");
    }

    @Test
    public void testFindTaskWithLongestDuration() {
        Task longestTask = tasks.get(0);

        // Normal for loop to find the task with the longest duration
        for (int i = 1; i < tasks.size(); i++) {
            if (tasks.get(i).getTaskDuration() > longestTask.getTaskDuration()) {
                longestTask = tasks.get(i);
            }
        }

        assertEquals("Glenda Oberholzer", longestTask.getDeveloperName(), "The developer for the task with the longest duration should be Glenda Oberholzer.");
        assertEquals(11, longestTask.getTaskDuration(), "The longest task duration should be 11 hours.");
    }

    @Test
    public void testDeleteTask() {
        String taskToDelete = "Create Reports";
        boolean deleted = false;

        // Normal for loop to delete the task
        for (int i = 0; i < tasks.size(); i++) {
            if (tasks.get(i).getTaskName().equalsIgnoreCase(taskToDelete)) {
                tasks.remove(i);
                deleted = true;
                break;
            }
        }

        assertTrue(deleted, "Task 'Create Reports' should be deleted.");

        // Check if task is deleted
        boolean found = false;
        for (int i = 0; i < tasks.size(); i++) {
            if (tasks.get(i).getTaskName().equalsIgnoreCase(taskToDelete)) {
                found = true;
                break;
            }
        }

        assertFalse(found, "Task 'Create Reports' should no longer exist in the task list.");
    }

    @Test
    public void testDisplayTaskReport() {
        StringBuilder report = new StringBuilder("Task Report:\n");

        // Normal for loop to generate the task report
        for (int i = 0; i < tasks.size(); i++) {
            report.append(tasks.get(i).printTaskDetails()).append("\n\n");
        }

        String expectedReport = "Task Report:\n" +
                "Task Status: To Do\nDeveloper Details: Mike Smith\nTask Number: 0\nTask Name: Create Login\nTask Description: Create Login to authenticate users\nTask ID: CR:0:ITH\nTask Duration: 5 hours\n\n" +
                "Task Status: Doing\nDeveloper Details: Edward Harrison\nTask Number: 1\nTask Name: Create Add Features\nTask Description: Add features to the system\nTask ID: CR:1:SON\nTask Duration: 8 hours\n\n" +
                "Task Status: To Do\nDeveloper Details: Glenda Oberholzer\nTask Number: 3\nTask Name: Add Arrays\nTask Description: Add array data structure\nTask ID: AD:3:ZER\nTask Duration: 11 hours\n\n";

        assertEquals(expectedReport, report.toString().trim(), "The task report should match the expected output.");
    }
}
