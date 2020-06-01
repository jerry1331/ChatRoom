package database;

import java.sql.*;
import java.util.*;

public class UserModel {
	DBConnection db;

	public UserModel(DBConnection conn) {
		this.db = conn;
	}
	
	public UserInfo getUserByID(int ID) throws SQLException {
        ResultSet rs = db.query("SELECT * FROM users where user_id=" + ID);
        if (!rs.next()) return null;
        UserInfo user = new UserInfo(rs);

        //Get Friend List
        CollectionModel collectionModel = new CollectionModel(db);
        List<CollectionInfo> coll = collectionModel.getCollectionsByID(ID);
        CollectionInfo collection;
        List<UserInfo> memberlist;
        UserInfo member;

        int collectionCount = coll.size();
        int memberCount = 0;
        user.setCollectionCount((byte) collectionCount);

        String[] ListName = new String[collectionCount];
        byte[] bodyCount = new byte[collectionCount];		
        int bodyNum[][] = new int[collectionCount][];		
        int bodypic[][] = new int[collectionCount][];
        String bodyName[][] = new String[collectionCount][];
        
        for (int j = 0; j < coll.size(); j++) {
            try {
                collection = coll.get(j);
                ListName[j] = collection.getName();
                memberlist = collection.getMembers();

                memberCount = memberlist.size();
                bodyCount[j] = (byte) memberCount;

                bodyNum[j] = new int[memberCount];
                bodyName[j] = new String[memberCount];
                bodypic[j] = new int[memberCount];

                for (int i = 0; i < memberlist.size(); i++) {
                    member = memberlist.get(i);
                    bodyNum[j][i] = member.getIDNum();
                    bodyName[j][i] = member.getNickName();
                    bodypic[j][i] = member.getAvatar();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        //set friend list
        user.setBodyName(bodyName);
        user.setListName(ListName);
        user.setBodyCount(bodyCount);
        user.setBodyNum(bodyNum);
        user.setBodypic(bodypic);

        rs.close();
        return user;
    }
	
	public boolean userAuthorization(int ID, String passwd) throws SQLException {
        ResultSet rs = db.query(String.format("SELECT * FROM users WHERE user_id=%d AND password='%s'", ID, passwd));
        if (!rs.next()) return false;
        rs.close();
        return true;
    }
	
	public boolean isFriendsOfUser(int target, int ID) throws SQLException {
        String sql = String.format(
                "SELECT\n" +
                        "	*\n" +
                        "FROM\n" +
                        "	users\n" +
                        "WHERE\n" +
                        "	user_id = %d\n" +
                        "AND users.user_id IN (\n" +
                        "	SELECT\n" +
                        "		user_id\n" +
                        "	FROM\n" +
                        "		collection_entry\n" +
                        "	WHERE\n" +
                        "		collection_id IN (\n" +
                        "			SELECT\n" +
                        "				collection_id\n" +
                        "			FROM\n" +
                        "				collection\n" +
                        "			WHERE\n" +
                        "				user_id = %d\n" +
                        "		)\n" +
                        ")", target, ID);
        ResultSet rs = db.query(sql);
        return rs.next();
    }
	
	public List<UserInfo> getUsersInCollection(int coll_id) throws SQLException {
        ResultSet rs = db.query("SELECT * FROM users WHERE user_id IN (SELECT user_id FROM collection_entry WHERE collection_id = " + coll_id + ")");
        ArrayList<UserInfo> res = new ArrayList<>();
        while (rs.next()) {
            res.add(new UserInfo(rs));
        }
        rs.close();
        return res;
    }
	
	public UserInfo createUser(String passwd, String nick, int avatar) throws SQLException {
        String sql = String.format("INSERT INTO users (nickname, password, avatar) VALUES ('%s', '%s', %d)", nick, passwd, avatar);
        int res = db.insertAndGet(sql);
        return getUserByID(res);
    }
	
	public int removeUser(int ID) throws SQLException {
        String sql = String.format("DELETE FROM users WHERE user_id=%d", ID);
        int res = db.update(sql);
        return res;
    }
	
	public int addFriend(int addID, int ownID, String listName) throws Exception {
        //check add_ID
        UserInfo dest = getUserByID(addID);
        if (dest == null) {
            return 1;
        }
        CollectionModel collectionModel = new CollectionModel(db);
        CollectionInfo collection = collectionModel.getCollectionByNameAndOwner(listName, ownID);
        if (collection == null) {
            collection = collectionModel.createCollection(ownID, listName);
        } else if (isFriendsOfUser(addID, ownID)) {
            return 2;
        }
        collectionModel.addUserToCollection(addID, collection.getId());
        return 0;
    }
}
