/**
 * Created by root on 4/20/16.
 */
public abstract class AbstractFactory {
    public abstract SQLConn getSQLConn(String type);
    public abstract NOSQLConn getNOSQLConn(String type);
}
