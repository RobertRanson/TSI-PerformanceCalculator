package EntitiesDataSourceMapping;

import AppDataSource.DBExecuteSQL;
import AppDataSource.DBSetup;
import AppDataSource.DataSourceConstants;
import AppDataSource.ReadDelimitedFile;
import Entities.Customer;

import java.util.ArrayList;
import java.util.List;


public class CustomerDatabaseMapping{

    final private String customerTableName = Customer.dataSourceName;

    final private int emailAddressPosition = 0;
    final private int firstNamePosition = 1;
    final private int lastNamePosition = 2;
    final private int passwordPosition = 3;

    final private String[] dataSourceFields = {"emailAddress","firstName","lastName","password"};

    private DBSetup dbSetup = new DBSetup();

    private String customerSqlInsert;

    public CustomerDatabaseMapping(){
        this.customerSqlInsert = dbSetup.generateInsertStatement(customerTableName,dataSourceFields);
    }

    public Customer createCustomer(List<String> customerDetails){
        Customer customer = new Customer(
            customerDetails.get(this.emailAddressPosition),
            customerDetails.get(this.firstNamePosition),
            customerDetails.get(this.lastNamePosition),
            customerDetails.get(this.passwordPosition)
        );
        return customer;
    }

    private List<String[]> getCustomerRawData(){
        ReadDelimitedFile customerFileReader = new ReadDelimitedFile();
        return customerFileReader.getFileData(DataSourceConstants.ENTITIES_FOLDER,this.customerTableName + DataSourceConstants.FILE_TYPE);
    }

    public Customer getCustomerFromEmail(String email){
        DBExecuteSQL dbExecuteSQL = new DBExecuteSQL();
        Customer customer = null;
        List<List<String>> queryData = dbExecuteSQL.getDataFromTable(customerTableName,dataSourceFields,email);
        System.out.println("Customer Size:" + queryData.size());
        if (queryData.size() == 1) {
            customer = createCustomer(queryData.get(0));
        }
        return customer;
    }

    public void customerDataBaseSetup(){
        dbSetup.dropTable(customerTableName,dataSourceFields);
        dbSetup.createTable(customerTableName,dataSourceFields);
        dbSetup.populateEntity(this.customerSqlInsert, getCustomerRawData());
    }

}
