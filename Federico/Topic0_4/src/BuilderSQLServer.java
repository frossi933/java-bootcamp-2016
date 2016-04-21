import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Created by root on 4/20/16.
 */
public class BuilderSQLServer implements BuilderConnection {

    private String url;
    private String user;
    private String pass;

    @Override
    public void buildUser(String user) {
        this.user = user;
    }

    @Override
    public void buildPassword(String pass) {
        this.pass = pass;
    }

    @Override
    public void buildDriver() {
        /* It's empty because applications no longer need to explicitly load JDBC drivers since JDBC 4.0 (JDK 6.0)*/
    }

    @Override
    public void buildUrl(String url) {
        this.url = "jdbc:sqlserver://" + url;
    }

    @Override
    public Connection getConnection() {
        try {
            return DriverManager.getConnection(url, user,pass);
        } catch (SQLException e) {
            return null;
        }
    }
}
