/**
 * Created by root on 4/20/16.
 */
public abstract class NOSQLConn {
    public abstract NOSQLConn getInstance();
    public abstract OtherConnection getConnection();
    public abstract void releaseConnection();
}
