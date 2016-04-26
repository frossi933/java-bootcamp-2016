/**
 * Interface for No SQL connections, like mongo, maria, etc.
 * It is just to complete the topic implementation
 */
public interface OtherConnection {
    public void close();
    public boolean isClosed();
    public String createStatement(String st);
    // ...
}
