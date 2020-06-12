import Engine.LogIn;
import EntitiesDataSourceMapping.DataBaseSetup;

public class Main {

    private static void databaseSetup(){
        DataBaseSetup dataBaseSetup = new DataBaseSetup();
        dataBaseSetup.setup();
    }

    public static void main(String[ ] args) {
//        String result = this.input.getInputString();
//        this.output.output(result);
        databaseSetup();
        Engine.LogIn userLogIn = new Engine.LogIn();
        userLogIn.logIn();
}

}
