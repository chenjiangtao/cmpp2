package common.msg.domain;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;

import org.apache.log4j.Logger;

/**
 * 
 * 链路检查消息结构定义
 * @author 张科伟
 * 2011-08-22 17:30
 */
public class MsgActiveTestResp extends MsgHead {
	private static Logger logger=Logger.getLogger(MsgActiveTestResp.class);
	private byte reserved;//
	public MsgActiveTestResp(byte[] data){
		if(data.length==8+1){
			ByteArrayInputStream bins=new ByteArrayInputStream(data);
			DataInputStream dins=new DataInputStream(bins);
			try {
				this.setTotalLength(data.length+4);
				this.setCommandId(dins.readInt());
				this.setSequenceId(dins.readInt());
				this.reserved=dins.readByte();
				dins.close();
				bins.close();
			} catch (IOException e){}
		}else{
			logger.info("链路检查,解析数据包出错，包长度不一致。长度为:"+data.length);
		}
	}
	public byte getReserved() {
		return reserved;
	}

	public void setReserved(byte reserved) {
		this.reserved = reserved;
	}
}
