import java.sql.Connection;

/**
 * Created by root on 4/25/16.
 */
public abstract class SQLConn {

    public abstract MySQLConn getInstance();
    public abstract Connection getConnection();
    public abstract void releaseConnection();

}
