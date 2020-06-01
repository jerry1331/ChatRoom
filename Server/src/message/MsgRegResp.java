package message;

import java.io.*;

import database.Figure;

// 服务端注册消息回执
public class MsgRegResp extends MsgHead {
	private byte state;

	public byte getState() {
		return state;
	}

	public void setState(byte state) {
		this.state = state;
	}

	public MsgRegResp() {}

	public MsgRegResp(int IDDest, byte state) {
		setTotalLen(14);
		setType((byte)0x11);
		setDest(IDDest);
		setSrc(Figure.ServerID);
		setState(state);
	}
	
	@Override
	public byte[] packMessage() throws IOException {
		ByteArrayOutputStream bous = new ByteArrayOutputStream();
		DataOutputStream dous = new DataOutputStream(bous);
		packMessageHead(dous);
		dous.write(getState());
		dous.flush();
		byte[] data = bous.toByteArray();
		return data;
	}
}
