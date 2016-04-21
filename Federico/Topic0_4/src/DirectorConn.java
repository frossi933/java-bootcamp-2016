import java.sql.Connection;

/**
 * Created by root on 4/20/16.
 */
public class DirectorConn {
    private BuilderConnection builder = null;

    private String user = "username";
    private String pass = "password";
    private String url = "localhost:3066";
    public DirectorConn(BuilderConnection builder){
        this.builder = builder;
    }

    public void constructConn(){
        if(builder!=null){
            builder.buildDriver();
            builder.buildUser(user);
            builder.buildPassword(pass);
            builder.buildUrl(url);
        }
    }

    public Connection getConnection(){
        return builder.getConnection();
    }
}
