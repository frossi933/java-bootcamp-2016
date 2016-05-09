public interface SQLConnection {

    void connect(String user, String pass, String url) throws Exception;
    String select(String query);
    int insert(String query);
    void release();
}
