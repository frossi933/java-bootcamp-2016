import java.util.HashMap;

public class SQLConnectionAccessProxy implements SQLConnection {

    private SQLConnectionReal connReal;
    private AccessControl access;
    private String user;
    private boolean islogged = false;

    public SQLConnectionAccessProxy(){
        HashMap<String,String> pass = new HashMap<String,String>();
        HashMap<String,String> rights = new HashMap<String,String>();
        /* Both maps maybe would be taken from files or other objects. In this case, I work with empty maps */

        access = new AccessControl(pass,rights);
        connReal = new SQLConnectionReal();
    }

    @Override
    public void connect(String user, String pass, String url) throws Exception {
        try {
            if (access.checkPass(user, pass)){
                this.user = user;
                islogged = true;
            } else
                throw new Exception("Wrong password");
        } catch (Exception e) {
            throw new Exception("No user");
        }
        connReal.connect(user, pass, url);
    }

    @Override
    public String select(String query) {
        try {
            if(islogged && access.canRead(user)){
                return connReal.select(query);
            } else
                return "Validation error";
        } catch (Exception e){
            return "Validation error";
        }
    }

    @Override
    public int insert(String query) {
        try {
            if(islogged && access.canWrite(user)){
                return connReal.insert(query);
            } else
                return -1;
        } catch (Exception e){
            return -1;
        }
    }

    @Override
    public void release() {
        user=null;
        islogged=false;
        access=null;
        connReal.release();
    }
}
