package data.api;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.github.abagabagon.verifico.api.RestAPI;

public class APIRequest {
	
	protected static Logger log;
	protected static RestAPI rest;
	protected static String adminUser;
	protected static String adminPassword;
	
	static {
		log = LogManager.getLogger(APIRequest.class);
		rest = new RestAPI();
	}

	public static void setBaseURI(String baseURI) {
		rest.setBaseURI(baseURI);
	}
	
	public static final void prepareAdminUser(String adminUser, String adminPassword) {
		APIRequest.adminUser = adminUser;
		APIRequest.adminPassword = adminPassword;
	}
	
	public static final String getAdminUser() {
		return APIRequest.adminUser;
	}
	
	public static final String getAdminPassword() {
		return APIRequest.adminPassword;
	}

}
