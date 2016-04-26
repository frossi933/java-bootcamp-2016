/**
 * Created by root on 4/18/16.
 */

import java.util.logging.Logger;
import org.easymock.EasyMock;

public class SingDBConn {

    private DBConnection conn;
    private static SingDBConn db = null;

    private static String url = "jdbc:mysql://localhost:3306/db";
    private static String user = "username";
    private static String pass = "password";

    private SingDBConn(){
        try {
            conn = EasyMock.createMock(DBConnection.class);
            conn.connect(url, user, pass);
        } catch (Exception e) {
            Logger l = Logger.getLogger(getClass().getName());
            l.severe(e.getMessage());
        }
    }

    public static SingDBConn getSingDBConn(){
        if(db == null)
            db = new SingDBConn();

        return db;
    }

    public String query(String q) {
        Statement st = EasyMock.createMock(Statement.class);
        String res = st.executeQuery(q);
        return res;
    }

    public int insert(String q) {
        Statement st = EasyMock.createMock(Statement.class);
        int res = st.executeUpdate(q);
        return res;
    }

    /*
    *
    * More operations...
    *
     */
}