import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Created by root on 4/25/16.
 */
public class AbsFacClient {
    public static void main(String[] args){
        AbstractFactory fact = FactoryProducer.getFactory("sql");
        SQLConn conn = fact.getSQLConn("mysql");
        Connection innerConn = conn.getConnection();

        try {
            Statement st = innerConn.createStatement();
            st.execute("SELECT * FROM *");
        } catch (SQLException e){
            e.printStackTrace();
        }
    }
}
