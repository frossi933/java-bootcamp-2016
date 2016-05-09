/**
 * Created by root on 4/20/16.
 */
public class SQLConnFactory extends AbstractFactory {

    @Override
    public SQLConn getSQLConn(String type) {
        if(type==null)
            return null;
        if(type.equalsIgnoreCase("mysql"))
            return new MySQLConn();
        if(type.equalsIgnoreCase("oracle"))
            return new OracleConn();
        if(type.equalsIgnoreCase("sqlserver"))
            return new SQLServerConn();

        return null;
    }

    @Override
    public NOSQLConn getNOSQLConn(String type) {
        return null;
    }
}
