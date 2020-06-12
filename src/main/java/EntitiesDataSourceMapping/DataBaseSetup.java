package EntitiesDataSourceMapping;

import AppDataSource.DBSetup;

public class DataBaseSetup {

    private DBSetup dbSetup;

    private CustomerDatabaseMapping customerDatabaseMapping;

    public DataBaseSetup(){
        this.dbSetup = new DBSetup();
        this.customerDatabaseMapping = new CustomerDatabaseMapping(this.dbSetup);
    }

    public void setup() {
        this.customerDatabaseMapping.customerDataBaseSetup();
    }
}
