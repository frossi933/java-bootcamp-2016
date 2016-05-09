import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Created by root on 4/20/16.
 */
public class SQLServerConn extends SQLConn {

    private String user="chino";
    private String pass="chino";
    private String url="jdbc:sqlserver://localhost:3066/db";
    private MySQLConn instance=null;
    private Connection conn=null;

    @Override
    public MySQLConn getInstance(){
        if(instance==null)
            instance = new MySQLConn();

        return instance;
    }

    @Override
    public Connection getConnection(){
        try{
            if(conn==null || conn.isClosed())
                conn = DriverManager.getConnection(user,pass,url);
        } catch (SQLException e){
            e.printStackTrace();
        }

        return conn;
    }

    @Override
    public void releaseConnection(){
        conn=null;
    }
}
