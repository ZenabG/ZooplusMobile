package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class AddTaskPage {
	
	@FindBy(id = "com.example.android.architecture.blueprints.master.mock:id/add_task_title_edit_text")
	private WebElement addTaskTitle;
	
	@FindBy(id = "com.example.android.architecture.blueprints.master.mock:id/add_task_description_edit_text")
	private WebElement addTaskDescription;
	
	@FindBy(id = "com.example.android.architecture.blueprints.master.mock:id/save_task_fab")
	private WebElement saveTaskButton;
	
	public AddTaskPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}
	
	public void addTaskTitle(String title) {
		addTaskTitle.sendKeys(title);
	}
	
	public void addTaskDescription(String description) {
		addTaskDescription.sendKeys(description);
	}
	
	public void clickSaveTask() {
		saveTaskButton.click();
	}

}
