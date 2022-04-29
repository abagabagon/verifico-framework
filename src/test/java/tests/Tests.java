
package tests;

import java.net.MalformedURLException;
import java.net.URL;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;

import com.github.abagabagon.verifico.Verifico;
import com.github.abagabagon.verifico.automation.web.WebAutomation;
import com.github.abagabagon.verifico.data.sql.SQLData;
import com.github.abagabagon.verifico.enums.Browser;
import com.github.abagabagon.verifico.enums.Device;
import com.github.abagabagon.verifico.enums.Mobile;
import com.github.abagabagon.verifico.enums.SQL;
import com.github.abagabagon.verifico.report.Reporter;
import com.github.abagabagon.verifico.testmanagement.TestManagement;

import data.api.APIRequest;
import data.sql.SQLQuery;
import pages.Pages;

public class Tests implements ITestListener {
	
	protected static Logger log;
	protected static Reporter report;
	protected static Verifico verifico;
	protected static WebAutomation I;
	protected static String baseURI;
	protected static SQLData sqlData;
	protected static TestManagement testManagement;
	protected static boolean bulkUpdate;
	
	static {
		log = LogManager.getLogger(Tests.class);
	}
	
	@BeforeSuite(alwaysRun = true)
	public static void setupGeneral(ITestContext arg0) {
		Settings.initializeSettings(arg0);
		verifico = new Verifico();
	}
	
	@BeforeSuite(alwaysRun = true, dependsOnMethods = {"setupGeneral"})
	public static void setupAutomation(ITestContext arg0) throws MalformedURLException {
		Device deviceType = Settings.getDeviceType();
		switch(deviceType) {
		case PC:
			Browser browser = Settings.getBrowser();
			boolean isHeadless = Settings.isHeadless();
			I = verifico.getWebAutomation(browser, isHeadless);
			break;
		case MOBILE:
			Mobile mobilePlatform = Settings.getMobilePlatform();
			Browser mobileBrowser = Settings.getBrowser();
			String deviceName = Settings.getMobileDeviceName();
			String mobileVersion = Settings.getMobileVersion();
			URL appiumUrl = Settings.getAppiumUrl();
			I = verifico.getWebAutomation(deviceName, mobilePlatform, mobileVersion, mobileBrowser, appiumUrl);
			break;
		default:
			log.fatal("Unsupported Device Type. Supported Device Types are \"DESKTOP\" and \"MOBILE\".");
			System.exit(1);
		}

	}
	
	@BeforeSuite(alwaysRun = true, dependsOnMethods = {"setupGeneral"})
	public static void setupReporter(ITestContext arg0) {
		String organization = Settings.getOrganization();
		String testName = arg0.getSuite().getName();
		report = verifico.getReporter();
		report.setReport(testName, organization);
	}
	
	@BeforeTest(alwaysRun = true)
	public static void setupSQL() {
		SQL sqlType = Settings.getSqlType();
		String sqlServer = Settings.getSqlServer();
		String sqlDatabaseName = Settings.getSqlDatabaseName();
		String sqlUser = Settings.getSqlUser();
		String sqlPassword = Settings.getSqlPassword();
		sqlData = verifico.getSQLData(sqlType, sqlServer, sqlDatabaseName, sqlUser, sqlPassword, false);
		SQLQuery.setSQLData(sqlData);
	}
	
	@BeforeTest(alwaysRun = true)
	public static void setupAPI() {
		baseURI = Settings.getBaseURI();
		APIRequest.setBaseURI(baseURI);
		APIRequest.prepareAdminUser(Settings.getAdminUser(), Settings.getAdminPassword());
	}
	
	@BeforeTest(alwaysRun = true)
	public static void setupPages() {
		I.openBrowser();
		String baseUrl = Settings.getBaseUrl();
		Pages.setBaseUrl(baseUrl);
		Pages.setWebAutomation(I);
		Pages.setReporter(report);
	}
	
	@BeforeTest(alwaysRun = true)
	public static void setupTestManagement() {
		String testManagementServer = Settings.getTestManagementServer();
		String testManagementUser = Settings.getTestManagementUser();
		String testManagementPassword = Settings.getTestManagementPassword();
		int testManagementRunId = Settings.getRunId();
		bulkUpdate = Settings.isBulkUpdate();
		testManagement = verifico.getTestManagement(testManagementServer, testManagementUser, testManagementPassword, testManagementRunId);
	}
	
	@AfterTest(alwaysRun = true)
	public static void teardownSQL(ITestContext arg0) {
		SQLQuery.teardownSQLData();
	}
	
	@AfterTest(alwaysRun = true)
	public static void terminateTest(ITestContext arg0) {
		I.closeBrowser();
	}
	
	@AfterSuite(alwaysRun = true)
	public static void generateReport(ITestContext arg0) {
		report.generateReport();
	}

}