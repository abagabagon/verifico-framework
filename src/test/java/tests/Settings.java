package tests;

import java.net.MalformedURLException;
import java.net.URL;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.ITestContext;

import com.github.abagabagon.verifico.enums.Browser;
import com.github.abagabagon.verifico.enums.Device;
import com.github.abagabagon.verifico.enums.Mobile;
import com.github.abagabagon.verifico.enums.SQL;

public class Settings {
	
	private static Logger log;
	private static String organization;
	private static Device deviceType;
	private static String baseUrl;
	private static Browser browser;
	private static boolean isHeadless;
	private static Mobile mobilePlatform;
	private static String mobileVersion;
	private static String mobileDeviceName;
	private static URL appiumUrl;
	private static SQL sqlType;
	private static String sqlServer;
	private static String sqlDatabaseName;
	private static String sqlUser;
	private static String sqlPassword;
	private static String baseURI;
	private static String testManagementServer;
	private static String testManagementUser;
	private static String testManagementPassword;
	private static String adminUser;
	private static String adminPassword;
	private static boolean isBulkUpdate;
	private static int maxRetry;
	private static int runId;
	private static ITestContext arg0;
	
	static {
		log = LogManager.getLogger(Settings.class);
	}
	
	public static void initializeSettings(ITestContext arg0) {
		Settings.arg0 = arg0;
		setOrganization();
		setDeviceType();
		setBaseUrl();
		setAdminUser();
		setAdminPassword();
		setAppiumUrl();
		setBaseURI();
		setBrowser();
		setHeadless();
		setMobileDeviceName();
		setMobilePlatform();
		setMobileVersion();
		setSqlType();
		setSqlServer();
		setSqlDatabaseName();
		setSqlUser();
		setSqlPassword();
		setTestManagementServer();
		setTestManagementUser();
		setTestManagementPassword();
		setBulkUpdate();
		setMaxRetry();
		setRunId();
	}
	
	public static String getOrganization() {
		return organization;
	}
	
	public static Device getDeviceType() {
		return deviceType;
	}

	public static String getBaseUrl() {
		return baseUrl;
	}

	public static Browser getBrowser() {
		return browser;
	}
	
	public static boolean isHeadless() {
		return isHeadless;
	}

	public static Mobile getMobilePlatform() {
		return mobilePlatform;
	}

	public static String getMobileVersion() {
		return mobileVersion;
	}

	public static String getMobileDeviceName() {
		return mobileDeviceName;
	}

	public static URL getAppiumUrl() {
		return appiumUrl;
	}

	public static SQL getSqlType() {
		return sqlType;
	}

	public static String getSqlServer() {
		return sqlServer;
	}

	public static String getSqlDatabaseName() {
		return sqlDatabaseName;
	}

	public static String getSqlUser() {
		return sqlUser;
	}

	public static String getSqlPassword() {
		return sqlPassword;
	}

	public static String getBaseURI() {
		return baseURI;
	}

	public static String getTestManagementServer() {
		return testManagementServer;
	}

	public static String getTestManagementUser() {
		return testManagementUser;
	}

	public static String getTestManagementPassword() {
		return testManagementPassword;
	}

	public static boolean isBulkUpdate() {
		return isBulkUpdate;
	}
	
	public static String getAdminUser() {
		return adminUser;
	}

	public static String getAdminPassword() {
		return adminPassword;
	}

	public static int getMaxRetry() {
		return maxRetry;
	}
	
	public static int getRunId() {
		return runId;
	}
	
	public static void setOrganization() {
		Settings.organization = getSuiteParameter("organization", arg0);
		log.debug("Setting Organization to \"" + organization + "\".");
	}

	public static void setDeviceType() {
		try {
			deviceType = Device.valueOf(getSuiteParameter("device.type", arg0));
			log.debug("Setting Device Type to \"" + deviceType + "\".");
		} catch (IllegalArgumentException e) {
			log.fatal("Encountered IllegalArgumentException while checking value of Device Type Setting.");
			System.exit(1);
		} catch (NullPointerException e) {
			log.fatal("Encountered NullPointerException while checking value of Device Type Setting.");
			System.exit(1);
		}
		
	}
	
	public static void setBaseUrl() {
		baseUrl = getSuiteParameter("url.base", arg0);
		log.debug("Setting Base URL to \"" + baseUrl + "\".");
	}
	
	public static void setBrowser() {
		try {
			browser = Browser.valueOf(getSuiteParameter("browser", arg0));
			log.debug("Setting Browser to \"" + browser + "\".");
		} catch (IllegalArgumentException e) {
			log.fatal("Encountered IllegalArgumentException while checking value of Browser Setting.");
			System.exit(1);
		} catch (NullPointerException e) {
			log.fatal("Encountered NullPointerException while checking value of Browser Setting.");
			System.exit(1);
		}
	}
	
	public static void setHeadless() {
		isHeadless = Boolean.parseBoolean(getSuiteParameter("web.headless", arg0));
		log.debug("Setting Headless Browsing to \"" + isHeadless + "\".");
	}
	
	public static void setMobilePlatform() {
		try {
			mobilePlatform = Mobile.valueOf(getSuiteParameter("mobile.platform", arg0));
			log.debug("Setting Mobile Platform to \"" + mobilePlatform + "\".");
		} catch (IllegalArgumentException e) {
			log.fatal("Encountered IllegalArgumentException while checking value of Mobile Platform Setting.");
			System.exit(1);
		} catch (NullPointerException e) {
			log.fatal("Encountered NullPointerException while checking value of Mobile Platform Setting.");
			System.exit(1);
		}
		
	}
	
	public static void setMobileVersion() {
		mobileVersion = getSuiteParameter("mobile.version", arg0);
		log.debug("Setting Mobile Version to \"" + mobileVersion + "\".");
	}
	
	public static void setMobileDeviceName() {
		mobileDeviceName = getSuiteParameter("device.name", arg0);
		log.debug("Setting Mobile Device Name to \"" + mobileDeviceName + "\".");
	}
	
	public static void setAppiumUrl() {
		try {
			appiumUrl = new URL(getSuiteParameter("url.appium", arg0));
			log.debug("Setting Appium URL to \"" + appiumUrl + "\".");
		} catch (MalformedURLException e) {
			log.fatal("Encountered MalformedURLException while getting Appium URL Setting.");
		} catch (Exception e) {
			log.fatal("Encountered Exception while getting Appium URL Setting.");
			e.printStackTrace();
		}
		
	}
	
	public static void setSqlType() {
		try {
			sqlType = SQL.valueOf(getSuiteParameter("sql.type", arg0));
			log.debug("Setting SQL Type to \"" + sqlType + "\".");
		} catch (IllegalArgumentException e) {
			log.fatal("Encountered IllegalArgumentException while checking value of SQL Type Setting.");
			System.exit(1);
		} catch (NullPointerException e) {
			log.fatal("Encountered NullPointerException while checking value of SQL Type Setting.");
			System.exit(1);
		}
		
	}
	
	public static void setSqlServer() {
		sqlServer = getSuiteParameter("sql.server", arg0);
		log.debug("Setting SQL Server to \"" + sqlServer + "\".");
	}
	
	public static void setSqlDatabaseName() {
		sqlDatabaseName = getSuiteParameter("sql.dbname", arg0);
		log.debug("Setting SQL Database Name to \"" + sqlDatabaseName + "\".");
	}
	
	public static void setSqlUser() {
		sqlUser = getSuiteParameter("sql.user", arg0);
		log.debug("Setting SQL User to \"" + sqlUser + "\".");
	}
	
	public static void setSqlPassword() {
		sqlPassword = getSuiteParameter("sql.password", arg0);
		log.debug("Setting SQL Password to \"" + sqlPassword + "\".");
	}
	
	public static void setBaseURI() {
		baseURI = getSuiteParameter("api.baseuri", arg0);
		log.debug("Setting Base URI to \"" + baseURI + "\".");
	}
	
	public static void setTestManagementServer() {
		testManagementServer = getSuiteParameter("testmanagement.server", arg0);
		log.debug("Setting Test Management Server to \"" + testManagementServer + "\".");
	}
	
	public static void setTestManagementUser() {
		testManagementUser = getSuiteParameter("testmanagement.user", arg0);
		log.debug("Setting Test Management User to \"" + testManagementUser + "\".");
	}
	
	public static void setTestManagementPassword() {
		testManagementPassword = getSuiteParameter("testmanagement.password", arg0);
		log.debug("Setting Test Management Password to \"" + testManagementPassword + "\".");
	}
	
	public static void setBulkUpdate() {
		isBulkUpdate = Boolean.parseBoolean(getSuiteParameter("testmanagement.bulkupdate", arg0));
		log.debug("Setting Test Management Bulk Update to \"" + isBulkUpdate + "\".");
	}
	
	public static void setAdminUser() {
		adminUser = getSuiteParameter("admin.user", arg0);
		log.debug("Setting Admin User to \"" + adminUser + "\".");
	}
	
	public static void setAdminPassword() {
		adminPassword = getSuiteParameter("admin.password", arg0);
		log.debug("Setting Admin Password to \"" + adminPassword + "\".");
	}
	
	public static void setMaxRetry() {
		maxRetry = Integer.parseInt(getSuiteParameter("max.retry", arg0));
		log.debug("Setting Max Test Execution Retry to \"" + maxRetry + "\".");
	}
	
	public static void setRunId() {
		runId = Integer.parseInt(getTestParameter("testmanagement.runid", arg0));
		log.debug("Setting Run ID to \"" + runId + "\".");
	}
	
	private static String getSuiteParameter(String parameter, ITestContext arg0) {
		String value = arg0.getSuite().getParameter(parameter);
		if (value == null) {
			log.fatal("The Suite Parameter: \"" + parameter + "\" does not exist. Check if TestNG Configuration has the following parameter.");
			System.exit(1);
		}
		return value;
	}
	
	private static String getTestParameter(String parameter, ITestContext arg0) {
		String value = arg0.getCurrentXmlTest().getParameter(parameter);
		if (value == null) {
			log.fatal("The Test Parameter: \"" + parameter + "\" does not exist. Check if TestNG Configuration has the following parameter.");
			System.exit(1);
		}
		return value;
	}
	
}
