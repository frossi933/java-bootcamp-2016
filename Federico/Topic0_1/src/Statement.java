/**
 * Created by root on 4/20/16.
 */
public interface Statement {
    // ...

    /* for simplicity's sake, executeQuery just returns a String, instead of an object like ResultSet */
    public String executeQuery(String query);
    public int executeUpdate(String query);

    // ...
}
