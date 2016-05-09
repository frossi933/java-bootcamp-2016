/* Can I use this? */
import java.sql.Connection;


/**
 * Created by root on 4/20/16.
 */
public interface BuilderConnection {

    BuilderConnection buildUser(String user);
    BuilderConnection buildPassword(String pass);
    BuilderConnection buildUrl(String url);
    BuilderConnection buildDriver();

    Connection getConnection();
}
