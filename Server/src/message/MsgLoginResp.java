package message;

import java.io.*;

import database.Figure;

// µÇÂ½ÏûÏ¢»ØÖ´
public class MsgLoginResp extends MsgHead {
	public byte getState() {
		return state;
	}

	public void setState(byte state) {
		this.state = state;
	}

	private byte state;

	public MsgLoginResp() {}
	public MsgLoginResp(byte checkmsg) {
		setTotalLen(14);
		setType((byte)0x22);
		setDest(Figure.LoginID);
		setSrc(Figure.ServerID);
		setState(checkmsg);
	}
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
