package AppDataSource;
import Utilities.ErrorLogging;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

#New Test Comment
public class DBExecuteSQL {

        private DBConnection dbConnector = DBConnection.getInstance();
        private ErrorLogging errorLogging = ErrorLogging.getInstance();
        private Connection connection = null;
        private static DBExecuteSQL singleInstance = null;

        public static DBExecuteSQL getInstance(){
                if (singleInstance == null) {
                        singleInstance = new DBExecuteSQL();
                }
                return singleInstance;
        }

        public void setDbConnector(DBConnection dbConnector) {
                this.dbConnector = dbConnector;
                this.connection = null;
        }

        private Connection getConnection() {
                if (this.connection == null) {
                        try {
                                this.connection = this.dbConnector.create_connection();
                        } catch (Exception sqlExp) {
                                this.errorLogging.writeToLog("DBExecureSQL.getConnection", sqlExp.getMessage());
                        }
                }
                return this.connection;
        }

        public void executeSQLCommand(String sqlCommand) {
                try {
                        Statement statement = this.getConnection().createStatement();
                        statement.execute(sqlCommand);
                } catch (Exception sqlExp) {
                        this.errorLogging.writeToLog("DBExecuteSQL.executeSQLCommand", "An error occurred{" + sqlExp.getMessage());
                        this.errorLogging.writeToLog("DBExecuteSQL.executeSQLCommand", "With command{" + sqlCommand);
                }
        }

        private PreparedStatement prepareSqlStatementFromList(String sqlCommand, List<String[]> dataRows) {
                PreparedStatement preparedStatement = null;
                try {
                        preparedStatement = this.connection.prepareStatement(sqlCommand);
                        for (String[] fieldValues : dataRows) {
                                prepareSqlStatement(preparedStatement, fieldValues);
                        }
                        preparedStatement.executeUpdate();
                } catch (Exception sqlExp) {
                        this.errorLogging.writeToLog("DBExecuteSQL.prepareSqlStatementFromList", "An error occurred{" + sqlExp.getMessage());
                        this.errorLogging.writeToLog("DBExecuteSQL.prepareSqlStatementFromList", "With command{" + sqlCommand);
                }
                return preparedStatement;
        }

        private String duplicateSqlCommandForList(String sqlCommand, int sizeOfList) {
                String newSqlCommand = "";
                for (int counter = 1; counter < sizeOfList; counter++) {
                        newSqlCommand += sqlCommand;
                }
                return newSqlCommand;
        }

        private PreparedStatement prepareSqlStatement(PreparedStatement preparedStatement, String[] fieldValues) {
                int counter = 1;
                int numberOfFields = fieldValues.length;
                try {
                        for (String fieldValue : fieldValues) {
                                preparedStatement.setString(counter, fieldValue);
                                counter += 1;
                        }
                } catch (Exception sqlExp) {
                        this.errorLogging.writeToLog("DBExecuteSQL.prepareSqlStatement", "An error occurred{" + sqlExp.getMessage());
                }
                return preparedStatement;
        }

        public void insertData(String sqlCommand, List<String[]> dataRows) {
                String duplicatedSQLCommand = this.duplicateSqlCommandForList(sqlCommand, dataRows.size());
                try {
                        this.prepareSqlStatementFromList(sqlCommand, dataRows);
                } catch (Exception sqlExp) {
                        this.errorLogging.writeToLog("DBExecuteSQL.insertData", "An error occurred{" + sqlExp.getMessage());
                        throw sqlExp;
                }
        }

        //private String{] getListOfTables() {
        //        return this.executeSqlSelect("SELECT name FROM sqlite_master WHERE type='table';");
        //}

        //private void switchToInMemory() {
        //        this.dbConnector.switchToInMemory();
        //        this.connection = null;
        //}
}

