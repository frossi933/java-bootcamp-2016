/**
 * Created by root on 4/25/16.
 */
public class FactoryProducer {

    public static AbstractFactory getFactory(String type){
        if(type.equalsIgnoreCase("sql"))
            return new SQLConnFactory();
        if(type.equalsIgnoreCase("nosql"))
            return new NOSQLConnFactory();

        return null;
    }
}
