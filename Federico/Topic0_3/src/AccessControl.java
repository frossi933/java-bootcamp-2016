import java.util.HashMap;

/**
 * Created by root on 4/19/16.
 */
public class AccessControl {
    // key: user, value: pass
    private HashMap<String, String> pass;
    // key: user, value: rights
    private HashMap<String, String> rights;

    public AccessControl(HashMap pass, HashMap rights){
        this.pass = pass;
        this.rights = rights;
    }

    public boolean isUser(String u){
        return (pass.containsKey(u));
    }

    public boolean checkPass(String u, String p) throws Exception {
        if(isUser(u))
            return (p.equals(pass.get(u)));
        else
            throw new Exception("No User");
    }

    public String getRightsOf(String u) throws Exception{
        if(isUser(u))
            return (rights.get(u));
        else
            throw new Exception("No User");
    }

    public boolean canRead(String u) throws Exception {
        if(!isUser(u))
            throw new Exception("No User");

        String r = rights.get(u);
        return (r.contains("r"));
    }

    public boolean canWrite(String u) throws Exception {
        if(!isUser(u))
            throw new Exception("No User");

        String r = rights.get(u);
        return (r.contains("w"));
    }

    public boolean canExec(String u) throws Exception {
        if(!isUser(u))
            throw new Exception("No User");

        String r = rights.get(u);
        return (r.contains("x"));
    }

}
