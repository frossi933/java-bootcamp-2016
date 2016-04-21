/* Can I use this? */
import java.sql.Connection;


/**
 * Created by root on 4/20/16.
 */
public interface BuilderConnection {

    void buildUser(String user);
    void buildPassword(String pass);
    void buildUrl(String url);
    void buildDriver();

    Connection getConnection();
}
