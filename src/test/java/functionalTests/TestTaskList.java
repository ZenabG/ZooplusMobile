package functionalTests;

import static org.testng.Assert.assertTrue;

import java.net.MalformedURLException;

import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import base.AppiumSetUp;

import pages.AddTaskPage;
import pages.MenuPage;
import pages.StatisticsPage;
import pages.TaskHomePage;

public class TestTaskList extends AppiumSetUp {

	private TaskHomePage taskPage;
	private AddTaskPage addTaskPage;
	private StatisticsPage statisticsPage;
	private MenuPage menuPage;

	@BeforeSuite
	public void startAppiumServer() throws MalformedURLException {
		initialiseAppium();
	}

	@BeforeTest
	public void beforeTestMethod() {
		taskPage = new TaskHomePage(driver);
		addTaskPage = new AddTaskPage(driver);
		statisticsPage = new StatisticsPage(driver);
		menuPage = new MenuPage(driver);
	}

	@Test
	public void verifyToDoTaskListFeature() throws MalformedURLException, InterruptedException {

		// Create two tasks in the TODO list
		taskPage.clickAddToDoTask();

		// Add 2 tasks
		for (int i = 0; i < 2; i++) {
			taskPage.clickAddToDoTask();
			addTaskPage.addTaskTitle("task " + i);
			addTaskPage.addTaskDescription("task description " + i);
			addTaskPage.clickSaveTask();
		}

		// Mark one task as completed
		taskPage.clickTaskCompletedCheckbox(0);

		// Verify the statistics for Active Tasks is 50% and Completed Tasks is 50%
		taskPage.clickHamburgerMenu();
		menuPage.goToStatisticsTab();
		assertTrue(statisticsPage.getActiveTasksStatictics().contains("50.0%"));
		assertTrue(statisticsPage.getCompletedTasksStatistics().contains("50.0%"));

		// Clear completed task in TODO list and verify completed task is no longer
		// listed
		taskPage.clickHamburgerMenu();
		menuPage.goToTasksListTab();
		assertTrue(taskPage.verifyCompletedTaskIsPresent(0));
		taskPage.clickThreeDotMenu();
		taskPage.clickClearCompletedLink();
		assertTrue(taskPage.verifyCompletedTaskIsNotPresent(0));
	}

	@AfterSuite
	public void stopAppiumServer() {
		killAppiumServer();
	}
}
