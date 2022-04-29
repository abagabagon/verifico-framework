package data.sql;

import java.sql.Connection;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.github.abagabagon.verifico.data.sql.SQLData;

public class SQLQuery {
	
	protected static Logger log;
	protected static SQLData sqlData;
	protected static Connection connection;
	
	static {
		log = LogManager.getLogger(SQLQuery.class);
	}

	public static void setSQLData(SQLData sqlData) {
		if (sqlData == null) {
			log.fatal("SQLData Instance is NULL.");
		} else {
			SQLQuery.sqlData = sqlData;
			connection = sqlData.openConnection();
			sqlData.closeConnection();
		}
	}
	
	public static void teardownSQLData() {
		if (sqlData == null) {
			log.fatal("SQLData Instance is NULL.");
		} else {
			sqlData.closeConnection();
		}
	}

}
