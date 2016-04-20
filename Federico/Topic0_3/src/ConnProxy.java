import java.util.HashMap;

/**
 * Created by root on 4/19/16.
 */
public class ConnProxy {

    private SingDBConn conn;
    private AccessControl access;
    private String user;
    private boolean islogged = false;

    public ConnProxy(){
        conn = SingDBConn.getSingDBConn();

        HashMap<String,String> pass = new HashMap<String,String>();
        HashMap<String,String> rights = new HashMap<String,String>();
        /* Both maps maybe would be taken from files or other objects. In this case, I work with empty maps */

        access = new AccessControl(pass,rights);
    }

    public boolean login(String user, String pass) throws Exception {
        if(access.checkPass(user, pass)){
            islogged = true;
            this.user = user;
            return true;
        }

        return false;
    }

    public String query(String q) throws Exception {
        if(!islogged)
            throw new Exception("Not logged in");

        if(!access.canExec(user))
            throw new Exception("Permission denied");

        return (conn.query(q));
    }

    public int insert(String q) throws Exception {
        if(!islogged)
            throw new Exception("Not logged in");

        if(!access.canWrite(user))
            throw new Exception("Permission denied");

        return (conn.insert(q));
    }
}
