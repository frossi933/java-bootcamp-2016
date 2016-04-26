/**
 * Created by root on 4/25/16.
 */
public class NOSQLConnFactory extends AbstractFactory {
    @Override
    public NOSQLConn getNOSQLConn(String type) {
        if(type == null)
            return null;
        if(type.equalsIgnoreCase("mongo"))
            return new MongoConn();
        if(type.equalsIgnoreCase("maria"))
            return new MariaConn();

        return null;
    }

    @Override
    public SQLConn getSQLConn(String type) {
        return null;
    }
}
