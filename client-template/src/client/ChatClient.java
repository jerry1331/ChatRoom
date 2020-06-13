package client;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

import javax.swing.JOptionPane;

import dataBase.Figures;
import dataBase.ListInfo;
import msg.*;
import tools.DialogTool;
import tools.PackageTool;
import tools.ParseTool;


//此类用于与服务器进行通信，不停地处理消息
public class ChatClient extends Thread {
private String ServerIP;
private int port;
private Socket client;
private static int OwnIDNum;// 当登陆成功后，为此ChatClient的唯一ID
private InputStream ins;
private OutputStream ous;

public ChatClient(String ServerIP, int port) {
	this.ServerIP = ServerIP;
	this.port = port;
}

//不停地处理消息
public void run() {
	while (true) {
		try {
			processMsg();
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("脱离主进程与服务器断开连接");
			System.exit(0);
		}
	}
}

//连接服务器
public boolean ConnectServer() {
	try {
		client = new Socket(ServerIP, port);
		System.out.println("服务器已连接");
		ins = client.getInputStream();
		ous = client.getOutputStream();// 获取该连接的输入输出流
		return true;
	} catch (IOException e) {
		// e.printStackTrace();
	}
	return false;
}

//注册
public boolean Reg(String NikeName, String PassWord) {
	try {
		MsgReg mr = new MsgReg();
		int len = 33; // MsgReg的长度为固定的33
		byte type = 0x01; // MsgReg类型为0x01

		// 设置MsgReg的参数
		mr.setTotalLen(len);
		mr.setType(type);
		mr.setDest(Figures.ServerID); // 服务器的ID
		mr.setSrc(Figures.LoginID);
		mr.setNikeName(NikeName);
		mr.setPwd(PassWord);

		// 打包MsgReg
		byte[] sendMsg = PackageTool.packMsg(mr);
		ous.write(sendMsg);

		// 接收服务器的反馈信息
		byte[] data = receiveMsg();

		// 将数组转换为类
		MsgHead recMsg = ParseTool.parseMsg(data);

		if (recMsg.getType() != 0x11) {// 不是回应注册消息
			System.out.println("通讯协议错误");
			return false;
		}

		MsgRegResp mrr = (MsgRegResp) recMsg;
		if (mrr.getState() == 0 && mr.getPwd().length()>=6) {
			/*
			 * 注册成功
			 */
			JOptionPane.showMessageDialog(null, "注册成功\n用户ID为" + mrr.getDest()+"\n"
					+ "用户ID是登录唯一凭证，请妥善保管！");
//			System.out.println("注册成功！注册的ID为" + mrr.getDest());
			return true;
		} else {
			/*
			 * 注册失败
			 */
			return false;
		}
	} catch (IOException e) {
		e.printStackTrace();
	}
	System.out.println("与服务器断开连接");
	return false;
}

public int Login(int id, String pwd) {
	try {
		MsgLogin ml = new MsgLogin();
		int len = 23;
		byte type = 0x02;

		// 设置MsgLogin的各种东西ID
		ml.setTotalLen(len);
		ml.setType(type);
		ml.setDest(Figures.ServerID);
		ml.setSrc(id);
		ml.setPwd(pwd);
		// 打包MsgLogin
		byte[] sendmsg = PackageTool.packMsg(ml);
		ous.write(sendmsg);
		// 接收服务器的反馈信息
		byte[] data = receiveMsg();
		// 将数组转换为类
		MsgHead recMsg = ParseTool.parseMsg(data);
		if (recMsg.getType() != 0x22) {	// 不是登陆反馈信息
			System.out.println("通讯协议错误");
			return 5;
		}
		MsgLoginResp mlr = (MsgLoginResp) recMsg;
		byte resp = mlr.getState();
		if (resp == 0) {
			System.out.println("登陆成功");
			OwnIDNum = id;
			return 0;
		} else if (resp == 1) {
			System.out.println("账号或密码错误");
			return 1;
		} else if(resp == 2){
			System.out.println("这个账号已经登陆");
			return 2;
		} else {
			System.out.println("未知错误");
			return 3;
		}
	} catch (Exception e) {
		e.printStackTrace();
	}

	System.out.println("与服务器断开连接");
	return 4;
}

//接收好友列表
public ListInfo getlist() throws IOException {
	byte[] data = receiveMsg();
	MsgHead recMsg = ParseTool.parseMsg(data);
	if (recMsg.getType() != 0x03) {
		System.out.println("通讯协议错误");
		System.exit(0);
	}
	return packlist(recMsg);
}

//将列表信息打包到MsgTeamList中
public ListInfo packlist(MsgHead recMsg){
	ListInfo list = new ListInfo();
	MsgTeamList mtl = (MsgTeamList) recMsg;
	list.setNickName(mtl.getUserName());
	list.setIDNum(mtl.getDest());
	list.setAvatar(mtl.getPic());
	list.setCollectionCount(mtl.getListCount());
	list.setListName(mtl.getListName());
	list.setBodyCount(mtl.getBodyCount());
	list.setBodyNum(mtl.getBodyNum());
	list.setBodypic(mtl.getBodyPic());
	list.setBodyName(mtl.getNikeName());
	list.setBodyState(mtl.getBodyState());
	return list;
}

public void SendaddFriend(int add_id, String list_name) throws IOException {
	MsgAddFriend maf = new MsgAddFriend();
	byte data[] = list_name.getBytes();
	int TotalLen = 17;
	TotalLen += data.length;
	byte type = 0x05;
	maf.setTotalLen(TotalLen);
	maf.setType(type);
	maf.setDest(Figures.ServerID);
	maf.setSrc(OwnIDNum);
	maf.setAdd_ID(add_id);
	maf.setList_name(list_name);
	byte[] sendMsg = PackageTool.packMsg(maf);
	ous.write(sendMsg);
	ous.flush();
}

public void sendMsg(int to, String Msg) throws IOException {
	MsgChatText mct = new MsgChatText();
	byte data[] = Msg.getBytes();
	int TotalLen = 13;
	TotalLen += data.length;
	byte type = 0x04;
	mct.setTotalLen(TotalLen);
	mct.setType(type);
	mct.setDest(to);
	mct.setSrc(OwnIDNum);
	mct.setMsgText(Msg);

	byte[] sendMsg = PackageTool.packMsg(mct);
	ous.write(sendMsg);
	ous.flush();
}

//这个方法用于从输入流中间读取一定长度的信息 信息长度为最前面的一个整数
public byte[] receiveMsg() throws IOException {
	DataInputStream dis = new DataInputStream(ins);
	int totalLen = dis.readInt();
	System.out.println("TotalLen"+totalLen);
	byte[] data = new byte[totalLen - 4];
	dis.readFully(data);
	return data;
}

//接受服务端传来的消息
public void processMsg() throws IOException {
	byte[] data = receiveMsg();
	// 将数组转换为类
	MsgHead recMsg = ParseTool.parseMsg(data);
	byte MsgType = recMsg.getType();

	// 根据不同的信息进行处理
	if (MsgType == 0x04) {
		MsgChatText mct = (MsgChatText) recMsg;
		int from = mct.getSrc();
		String Msg = mct.getMsgText();
		DialogTool.ShowMessage(from, Msg);
	}
	else if(MsgType == 0x03){	//更新好友列表
		System.out.println("Refresh list");
		ListInfo list = packlist(recMsg);
		Figures.list.Refresh_List(list);
	}
	if (MsgType == 0x55){
		MsgAddFriendResp mafr = (MsgAddFriendResp) recMsg;
		byte result = mafr.getState();
		System.out.println("Add Friend Result "+result);
		if(Figures.afu != null){
			Figures.afu.showResult(result);
		}
	}
}
}

