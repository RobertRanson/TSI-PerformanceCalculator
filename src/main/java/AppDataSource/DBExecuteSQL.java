package AppDataSource;
import Utilities.ErrorLogging;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

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
                                this.errorLogging.writeToLog("DBExecuteSQL.getConnection", sqlExp.getMessage());
                        }
                }
                return this.connection;
        }

        public void executeSQLCommand(String sqlCommand) {
                //this.errorLogging.writeToLog("DBExecuteSQL.executeSQLCommand", "To attempt:" + sqlCommand);
                try {
                        Statement statement = this.getConnection().createStatement();
                        statement.execute(sqlCommand);
                } catch (Exception sqlExp) {
                        this.errorLogging.writeToLog("DBExecuteSQL.executeSQLCommand", "An error occurred{" + sqlExp.getMessage());
                        this.errorLogging.writeToLog("DBExecuteSQL.executeSQLCommand", "With command{" + sqlCommand);
                }
        }

        private ResultSet executeSQLQuery(String sqlCommand) {
                ResultSet resultSet = null;
                try {
                        Statement statement = this.getConnection().createStatement();
                        resultSet = statement.executeQuery(sqlCommand);
                } catch (Exception sqlExp) {
                        this.errorLogging.writeToLog("DBExecuteSQL.executeSQLCommand", "An error occurred{" + sqlExp.getMessage());
                        this.errorLogging.writeToLog("DBExecuteSQL.executeSQLCommand", "With command{" + sqlCommand);
                }
                return resultSet;
        }

        private List<List<String>> getDataFromTable(ResultSet resultSet, String tableName, String[] columnNames) {
                List<List<String>> queryData = new ArrayList<List<String>>();
                ArrayList<String> queryRow = new ArrayList<String>();
                try {
                        while (resultSet.next()) {
                                queryRow = new ArrayList<String>();;
                                for (String columnName:columnNames){
                                        queryRow.add(resultSet.getString(columnName));
                                }
                                queryData.add(queryRow);
                        }
                } catch (SQLException e) {
                        throw new Error("Problem", e);
                }
                return queryData;
        }

        private String getSelectQuery(String tableName, String[] columnNames, String filter){
                String query = "select ";
                int counter = 0;
                String primaryKey = "";
                for (String column : columnNames){
                        if (counter == 0) {
                                primaryKey = column;
                                query += column;
                        } else {
                                query += ", " + column;
                        }
                        counter += 1;
                }
                query += " from " + tableName + " where " + primaryKey + " = \"" + filter + "\"";
                return query;
        }

        public List<List<String>> getDataFromTable( String tableName, String[] columnNames, String filter) {
                ResultSet resultSet = executeSQLQuery(getSelectQuery(tableName,columnNames,filter));
                List<List<String>> queryData = getDataFromTable(resultSet,tableName,columnNames);
                return queryData;
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
                        this.errorLogging.writeToLog("DBExecuteSQL.prepareSqlStatementFromList", "An error occurred:" + sqlExp.getMessage());
                        this.errorLogging.writeToLog("DBExecuteSQL.prepareSqlStatementFromList", "With command:" + sqlCommand);
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
                        this.errorLogging.writeToLog("DBExecuteSQL.prepareSqlStatement", "An error occurred:" + sqlExp.getMessage());
                }
                return preparedStatement;
        }

        public void insertData(String sqlCommand, List<String[]> dataRows) {
                String duplicatedSQLCommand = this.duplicateSqlCommandForList(sqlCommand, dataRows.size());
                try {
                        this.prepareSqlStatementFromList(sqlCommand, dataRows);
                } catch (Exception sqlExp) {
                        this.errorLogging.writeToLog("DBExecuteSQL.insertData", "An error occurred:" + sqlExp.getMessage());
                        throw sqlExp;
                }
        }

        //private String[] getListOfTables() {
        //        return this.executeSqlSelect("SELECT name FROM sqlite_master WHERE type='table';");
        //}

        //private void switchToInMemory() {
        //        this.dbConnector.switchToInMemory();
        //        this.connection = null;
        //}
}

