package database;

import java.sql.*;
import java.util.*;

public class CollectionInfo {
	private int ownerID;
    private List<UserInfo> members;
    private String name;
    private int id;
    
    CollectionInfo(ResultSet rs) throws SQLException {
        ownerID = rs.getInt("user_id");
        name = rs.getString("name");
        id = rs.getInt("collection_id");
    }
    
    public List<UserInfo> getMembers() throws Exception {
        if (members == null) {
            DBConnection conn = DBConnection.getInstance();
            UserModel userModel = new UserModel(conn);
            members = userModel.getUsersInCollection(id);
            conn.close();
        }
        return members;
    }
    
    public int getOwnerID() {
        return ownerID;
    }

    public void setOwnerID(int ownerID) {
        this.ownerID = ownerID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    public String toString() {
        return String.format("Collection: %s, id: %d, ownerID: %d", name, id, ownerID);
    }
}
