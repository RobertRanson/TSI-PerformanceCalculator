package EntitiesDataSourceMapping;

import AppDataSource.DBSetup;
import AppDataSource.DataSourceConstants;
import AppDataSource.DataSourceConstants.*;
import AppDataSource.ReadDelimitedFile;
import Entities.Customer;

import java.util.List;


public class CustomerDatabaseMapping{

    final private String customerTableName = Customer.dataSourceName;

    final private int emailAddressPosition = 0;
    final private int firstNamePosition = 1;
    final private int lastNamePosition = 2;
    final private int passwordPosition = 3;

    final private String[] dataSourceFields = {"emailAddress","firstName","lastName","password"};

    private DBSetup dbSetup;

    private String customerSqlInsert;

    CustomerDatabaseMapping(DBSetup dbSetup){
        this.dbSetup = dbSetup;
        this.customerSqlInsert = dbSetup.generateInsertStatement(customerTableName,dataSourceFields);
    }

    public void createCustomer(String[] customerDetails){
        Customer customer = new Customer(
            customerDetails[this.emailAddressPosition],
            customerDetails[this.firstNamePosition],
            customerDetails[this.lastNamePosition],
            customerDetails[this.passwordPosition]
        );
    }

    private List<String[]> getCustomerData(){
        ReadDelimitedFile customerFileReader = new ReadDelimitedFile();
        return customerFileReader.getFileData(DataSourceConstants.ENTITIES_FOLDER,this.customerTableName + DataSourceConstants.FILE_TYPE);
    }

    public void customerDataBaseSetup(){
        dbSetup.dropTable(customerTableName,dataSourceFields);
        dbSetup.createTable(customerTableName,dataSourceFields);
        dbSetup.populateEntity(this.customerSqlInsert,getCustomerData());
    }

}
