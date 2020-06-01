package message;

public class MsgLoginResp extends MsgHead{
	public byte getState() {
		return state;
	}

	public void setState(byte state) {
		this.state = state;
	}

	private byte state;

}
