package database;

import java.sql.*;

class ConnectionInstance extends DBConnection {
    ConnectionInstance() {
        try {
            Class.forName("org.sqlite.JDBC");
            conn = DriverManager.getConnection("jdbc:sqlite:db/test.db");
        } catch ( Exception e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            System.exit(0);
        }
    }
}

public class DBConnection {
	protected Connection conn;

    public static DBConnection getInstance() {
        return new ConnectionInstance();
    }

    public void close() throws SQLException {
        conn.close();
    }

//    Êý¾Ý¿â²Ù×÷
    public ResultSet query (String sql) throws SQLException {
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(sql);

        return rs;
    }

    public int update(String sql) throws SQLException {
        Statement stmt = conn.createStatement();
        int res = stmt.executeUpdate(sql);
//        conn.commit();
        return res;
    }
    
    public int insertAndGet(String sql) throws SQLException {
        PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        pstmt.executeUpdate();
        ResultSet rs = pstmt.getGeneratedKeys();
        if (rs.next()) {
            int id = rs.getInt(1);
            return id;
        }
        return -1;
    }
}
