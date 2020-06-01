package message;

import java.io.*;

public class MsgAddFriendResp extends MsgHead{
	private byte state;

	public byte getState() {
		return state;
	}

	public void setState(byte state) {
		this.state = state;
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
