package database;

import java.sql.*;
import java.util.*;

public class CollectionModel {
	private DBConnection connection;
	
    public CollectionModel(DBConnection conn) {
        connection = conn;
    }
    
    public List<CollectionInfo> getCollectionsByID(int ID) throws SQLException {
        ResultSet rs = connection.query("SELECT * FROM collection where user_id=" + ID);
        ArrayList<CollectionInfo> res = new ArrayList<>();
        while (rs.next()) {
            res.add(new CollectionInfo(rs));
        }
        rs.close();
        return res;
    }
    
    public int addUserToCollection(int ID, int coll_id) throws SQLException {
        return connection.update(String.format("INSERT INTO collection_entry (user_id, collection_id) VALUES (%d, %d)", ID, coll_id));
    }
    
    public CollectionInfo createCollection(int ID, String collName) throws SQLException {
        String sql = String.format("INSERT INTO collection (name, user_id) VALUES ('%s', %d)", collName, ID);
        int id = connection.insertAndGet(sql);
        return getCollection(id);
    }
    
    public CollectionInfo getCollection(int id) throws SQLException {
        String sql = String.format("SELECT * FROM collection where collection_id=%d", id);
        ResultSet rs = connection.query(sql);
        if (!rs.next()) {
            return null;
        }
        CollectionInfo result = new CollectionInfo(rs);
        rs.close();
        return result;
    }
    
    public boolean isUserInCollection(int userID, int collectionId) throws SQLException {
        ResultSet rs = connection.query(String.format("SELECT * FROM collection_entry where user_id=%d AND collection_id=%d", userID, collectionId));
        return rs.next();
    }
    
    public CollectionInfo getCollectionByNameAndOwner(String name, int ID) throws SQLException {
        String sql = String.format("SELECT * FROM collection where user_id=%d AND name='%s'", ID, name);
        ResultSet rs = connection.query(sql);
        if (!rs.next()) {
            return null;
        }
        CollectionInfo result = new CollectionInfo(rs);
        rs.close();
        return result;
    }
    
    public int removeCollection(int id) throws SQLException {
        String sql = String.format("DELETE FROM collection WHERE collection_id=%d", id);
        return connection.update(sql);
    }
    
    public List<CollectionInfo> getCollectionsByUser(UserInfo user) throws Exception {
        int ID = user.getIDNum();
        return getCollectionsByID(ID);
    }
    
    public List<CollectionInfo> getCollectionsByUser(int ID) throws SQLException {
        return getCollectionsByID(ID);
    }
    
}
