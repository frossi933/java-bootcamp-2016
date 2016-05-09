import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Created by root on 4/26/16.
 */
public class BuilderClient {
    public static void main(String[] args){

        BuilderConnection builder = new BuilderMySQL();
        DirectorConn dir = new DirectorConn(builder);
        dir.constructConn();

        Connection myconn = dir.getConnection();

        try {
            Statement st = myconn.createStatement();
            // ...
        } catch (SQLException e){
            e.printStackTrace();
        }
    }
}
