package pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.Keys;
import org.testng.asserts.SoftAssert;

import com.github.abagabagon.verifico.automation.web.WebAutomation;
import com.github.abagabagon.verifico.report.Reporter;

public class Pages {
	
	protected static String baseUrl;
	protected static WebAutomation I;
	protected static Reporter report;
	protected static Logger log;
	protected static SoftAssert softAssert;
	
	static {
		log = LogManager.getLogger(Pages.class);
	}
	
	public static void setBaseUrl(String baseUrl) {
		if (baseUrl == null) {
			log.fatal("Base URL value is NULL.");
		} else {
			Pages.baseUrl = baseUrl;
		}
	}
	
	public static void setWebAutomation(WebAutomation webAutomation) {
		if (webAutomation == null) {
			log.fatal("WebAutomation Instance is NULL.");
		} else {
			Pages.I = webAutomation;
		}
	}
	
	public static void setReporter(Reporter report) {
		if (report == null) {
			log.fatal("Reporter Instance is NULL.");
		} else {
			Pages.report = report;
		}
	}
	
	public static void setSoftAssert() {
		softAssert = new SoftAssert();
	}
	
	public static void resetSoftAssert() {
		if (softAssert != null) {
			softAssert = null;
		} 
	}
	
	public static void press_key(Keys keyButton) {
		report.info("Pressing \"" + keyButton.toString() + "\".");
		I.press(keyButton);
	}

}
