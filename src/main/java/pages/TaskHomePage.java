package pages;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class TaskHomePage {

	@FindBy(id = "com.example.android.architecture.blueprints.master.mock:id/add_task_fab")
	private WebElement addTaskButton;

	@FindBy(id = "com.example.android.architecture.blueprints.master.mock:id/tasks_container_layout")
	private WebElement taskPageContainer;

	@FindBy(id = "com.example.android.architecture.blueprints.master.mock:id/complete_checkbox")
	private List<WebElement> checkBoxCompleteTask;

	@FindBy(xpath = "//android.widget.ImageButton[@content-desc='Open navigation drawer']")
	private WebElement hamburgerMenuButton;

	@FindBy(xpath = "//android.widget.ImageView[@content-desc='More options']")
	private WebElement threeDotMenu;

	@FindBy(id = "com.example.android.architecture.blueprints.master.mock:id/title")
	private WebElement clearCompletedLink;

	public TaskHomePage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}

	public void clickAddToDoTask() {
		addTaskButton.click();
	}

	public boolean verifyTaskPageIsLoaded() {
		return taskPageContainer.isEnabled();
	}

	public void clickTaskCompletedCheckbox(int taskNumber) {
		checkBoxCompleteTask.get(taskNumber).click();
	}

	public void clickHamburgerMenu() {
		hamburgerMenuButton.click();
	}

	public void clickThreeDotMenu() {
		threeDotMenu.click();
	}

	public void clickClearCompletedLink() {
		clearCompletedLink.click();
	}

	public boolean verifyCompletedTaskIsPresent(int taskCompletedNumber) {
		return checkBoxCompleteTask.get(taskCompletedNumber).getAttribute("checked").equals("true");
	}

	public boolean verifyCompletedTaskIsNotPresent(int taskNotCompletedNumber) {
		return checkBoxCompleteTask.get(taskNotCompletedNumber).getAttribute("checked").equals("false");
	}
}
