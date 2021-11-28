package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class MenuPage {
	
	@FindBy(id = "com.example.android.architecture.blueprints.master.mock:id/statistics_fragment_dest")
	private WebElement statisticsTab;
	
	@FindBy(id = "com.example.android.architecture.blueprints.master.mock:id/tasks_fragment_dest")
	private WebElement tasksListTab;
	
	public MenuPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}
	
	public void goToStatisticsTab() {
		statisticsTab.click();
	}
	
	public void goToTasksListTab() {
		tasksListTab.click();
	}

}
