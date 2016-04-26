import org.easymock.EasyMock;
import java.util.logging.Logger;

public class SQLConnectionReal implements SQLConnection {

    private DBConnection conn;
    private Logger log;

    @Override
    public void connect(String user, String pass, String url) {
        log = Logger.getLogger(getClass().getName());
        log.info("Getting connection...");
        try {
            conn = EasyMock.createMock(DBConnection.class);
            conn.connect(url, user, pass);
        } catch (Exception e) {
            log.severe(e.getMessage());
        }
    }

    @Override
    public String select(String query){
        log.info("Executing select...");
        return "SELECT " + query;
    }

    @Override
    public int insert(String query){
        log.info("Executing insert...");
        return 0;
    }

    @Override
    public void release(){
        log.info("Connection closed");
        return;
    }
}
