package AppDataSource;

import Utilities.ErrorLogging;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class DBSetup {

    private DBExecuteSQL dbExecuteSQL = DBExecuteSQL.getInstance();
    private ErrorLogging errorLogging = ErrorLogging.getInstance();

    public DBSetup(){

    }

    private String generateDropTable(String tableName){
        return "DROP TABLE IF EXISTS " + tableName;
    }

    public void dropTable(String tableName, String[] fieldNames){
        String sqlDropTable = this.generateDropTable(tableName);
        try {
            this.dbExecuteSQL.executeSQLCommand(sqlDropTable);
        } catch (Exception sqlExp) {
            this.errorLogging.writeToLog("DBSetup.dropTable",sqlExp.getMessage());
        }
    }
    public String generateCreateTableStatement(String tableName, String[] fieldNames){
        String sqlCreateTable =  "CREATE TABLE IF NOT EXISTS " + tableName + "(\n";
        int counter = 0;
        boolean fieldUnique = false;
        for (String columnName : fieldNames){
            if (counter == 0){
                fieldUnique = true;
            } else {
                sqlCreateTable += ",";
            }
            sqlCreateTable += columnName + " TEXT NOT NULL ";
            if (fieldUnique) {
                sqlCreateTable += " UNIQUE ";
            }
            counter += 1;
            fieldUnique = false;
        }
        sqlCreateTable += ");";
        return sqlCreateTable;
    }

    public void createTable(String tableName, String[] fieldNames){
        String sqlCreateTable = this.generateCreateTableStatement(tableName,fieldNames);
        try {
            this.dbExecuteSQL.executeSQLCommand(sqlCreateTable);
        } catch (Exception sqlExp) {
            this.errorLogging.writeToLog("DBSetup.createTable",sqlExp.getMessage());
        }
    }

    public String generateInsertStatement(String tableName, String[] fieldNames){
        int counter = 0;
        String sqlColumns = "";
        String sqlValues = "";
        String sqlUniqueColumn = "";
        String sqlInsert = "";
        for (String columnName : fieldNames){
            if (counter == 0 ){
                sqlUniqueColumn = columnName;
            } else {
                sqlColumns += ", ";
                sqlValues += ", ";
            }
            sqlColumns += columnName;
            sqlValues += "?";
            counter += 1;
        }
        sqlInsert = "INSERT INTO " + tableName + "(" + sqlColumns + ") VALUES(" + sqlValues + "); \n";
        return sqlInsert;
    }

    public void populateEntity(String sqlCommand, List<String[]> dataRows){
        this.dbExecuteSQL.insertData(sqlCommand,dataRows);
    }

}
