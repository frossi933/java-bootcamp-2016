public class ProxyClient {

    public static void main(String[] args){
        SQLConnectionAccessProxy conn = new SQLConnectionAccessProxy();
        try{
            conn.connect("chino","chino","jdbc:mysql://localhost:3306/db");
        } catch (Exception e){
            e.printStackTrace();
            return;
        }

        String result = conn.select("SELECT * FROM *");
        conn.insert("INSERT INTO T1 VALUES (2,3)");
    }
}
