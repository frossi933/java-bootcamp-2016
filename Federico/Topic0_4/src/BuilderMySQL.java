import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Created by root on 4/20/16.
 */
public class BuilderMySQL implements BuilderConnection {

    private String user;
    private String pass;
    private String url;

    @Override
    public BuilderConnection buildUser(String user){
        this.user = user;
        return this;
    }

    @Override
    public BuilderConnection buildPassword(String pass) {
        this.pass = pass;
        return this;
    }

    @Override
    public BuilderConnection buildUrl(String url) {
        this.url = "jdbc:mysql://" + url;
        return this;
    }

    @Override
    public BuilderConnection buildDriver() {
        /* It's empty because applications no longer need to explicitly load JDBC drivers since JDBC 4.0 (JDK 6.0) */
        return this;
    }

    @Override
    public Connection getConnection() {
        try {
            return DriverManager.getConnection(url,user,pass);
        } catch (SQLException e) {
            return null;
        }
    }
}
