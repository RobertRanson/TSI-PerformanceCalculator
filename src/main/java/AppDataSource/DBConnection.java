package AppDataSource;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import AppDataSource.DataSourceConstants.*;
import Utilities.ErrorLogging;

public class DBConnection implements DBConnInterface{

    private String dbFileName = DataSourceConstants.RESOURCE + "Database/applicationDB.db";
    private String inMemoryDatabase = ":memory:";
    private String sqlLiteUrl = this.dbFileName;
    private ErrorLogging errorLogging = ErrorLogging.getInstance();
    private static DBConnection singleInstance = null;

    private DBConnection(){

    }

    public static DBConnection getInstance(){
        if (singleInstance == null){
            singleInstance = new DBConnection();
        }
        return singleInstance;
    }

    public void switchToInMemory(){
        this.sqlLiteUrl = inMemoryDatabase;
    }


    public Connection create_connection(){
        String url = "jdbc:sqlite:" + this.sqlLiteUrl;

        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url);
        } catch (SQLException sqlExp) {
            this.errorLogging.writeToLog("DBConnection.create_connection",sqlExp.getMessage());
        }

        return conn;
    }
}
