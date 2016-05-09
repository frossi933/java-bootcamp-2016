/**
 * Created by root on 4/25/16.
 */
public class MariaConn extends NOSQLConn {
    private MariaConn instance=null;
    private OtherConnection conn = null;

    @Override
    public MariaConn getInstance() {
        if(instance==null)
            instance = new MariaConn();

        return instance;
    }

    @Override
    public OtherConnection getConnection() {
        /* OtherConnection is just an interface, we cannot instantiate it. For that reason, this method returns conn to complete the implementation. */
        return conn;
    }

    @Override
    public void releaseConnection() {
        conn = null;
    }
}
