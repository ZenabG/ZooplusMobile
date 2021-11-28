package base;

import java.io.File;
import java.net.MalformedURLException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import io.appium.java_client.service.local.flags.GeneralServerFlag;

public class AppiumSetUp {

	protected AppiumDriver<WebElement> driver;
	protected WebDriverWait wait;
	private AppiumServiceBuilder builder;
	private AppiumDriverLocalService service;

	protected void initialiseAppium() throws MalformedURLException {

		DesiredCapabilities cap = new DesiredCapabilities();

		//Set Appium desired capabilities
		cap.setCapability(MobileCapabilityType.PLATFORM_NAME, AppiumConstants.PLATFORM_NAME);
		cap.setCapability(MobileCapabilityType.PLATFORM_VERSION, AppiumConstants.PLATFORM_VERSION);
		cap.setCapability(MobileCapabilityType.DEVICE_NAME, AppiumConstants.DEVICE_NAME);
		cap.setCapability("appPackage", AppiumConstants.APP_PACKAGE);
		cap.setCapability("appActivity", AppiumConstants.APP_ACTIVITY);
		cap.setCapability("noReset", "false");

		//Define AppiumServiceBuilder with appium ip, port and node, appiumJS paths
		builder = new AppiumServiceBuilder();
		builder.usingDriverExecutable(new File(AppiumConstants.NODE_JS_EXE_PATH));
		builder.withAppiumJS(new File(AppiumConstants.APPIUM_JS_PATH));
		builder.withIPAddress("0.0.0.0");
		builder.usingAnyFreePort();
		builder.withCapabilities(cap);
		builder.withArgument(GeneralServerFlag.SESSION_OVERRIDE);
		builder.withArgument(GeneralServerFlag.LOG_LEVEL, "debug");
		builder.withLogFile(new File(System.getProperty("user.dir") + "/Appium_Server_Logs/appium_server_logs"));

		// Start the server with the builder
		try {
			service = AppiumDriverLocalService.buildService(builder);
			service.start();
			service.clearOutPutStreams();
		} catch (NullPointerException e) {
			e.printStackTrace();
		}

		System.out.println("Appium server running : " + service.isRunning());
		System.out.println("Appium URL " + service.getUrl().toString());

		driver = new AppiumDriver<WebElement>(service.getUrl(), cap);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		
		wait = new WebDriverWait(driver, 20);

	}

	public void killAppiumServer() {
		service.stop();
	}

}
