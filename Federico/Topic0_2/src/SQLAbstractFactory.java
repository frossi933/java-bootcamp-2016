/**
 * Created by root on 4/20/16.
 */
public abstract class SQLAbstractFactory {
    public abstract MySQLConn getMySQLConn();
    public abstract OracleConn getOracleConn();
    public abstract SQLServerConn getSQLServerConn();
}
