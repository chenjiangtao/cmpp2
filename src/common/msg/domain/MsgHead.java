package common.msg.domain;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.io.*;

/**
 * 所有请求的消息头<br/>
 * totalLength 消息总长度<br/>
 * commandId 命令类型<br/>
 * sequenceId 消息流水号,顺序累加,步长为1,循环使用（一对请求和应答消息的流水号必须相同）<br/>
 * Unsigned Integer  	无符号整数<br/>
 * Integer	整数，可为正整数、负整数或零<br/>
 * Octet String	定长字符串，位数不足时，如果左补0则补ASCII表示的零以填充，如果右补0则补二进制的零以表示字符串的结束符
 *
 * @author 张科伟
 * 2011-08-22 11:44
 */
public class MsgHead {
    private Log log = LogFactory.getLog(MsgHead.class);
    private int totalLength;//Unsigned Integer 消息总长度
    private int commandId;//Unsigned Integer 命令类型
    private int sequenceId;//Unsigned Integer 消息流水号,顺序累加,步长为1,循环使用（一对请求和应答消息的流水号必须相同）

    public byte[] toByteArray() {
        ByteArrayOutputStream bous = new ByteArrayOutputStream();
        DataOutputStream dous = new DataOutputStream(bous);
        try {
            dous.writeInt(this.getTotalLength());
            dous.writeInt(this.getCommandId());
            dous.writeInt(this.getSequenceId());
            dous.close();
        } catch (IOException e) {
            log.error("封装CMPP消息头二进制数组失败。");
        }
        return bous.toByteArray();
    }

    public MsgHead(byte[] data) {
        ByteArrayInputStream bins = new ByteArrayInputStream(data);
        DataInputStream dins = new DataInputStream(bins);
        try {
            this.setTotalLength(data.length + 4);
            this.setCommandId(dins.readInt());
            this.setSequenceId(dins.readInt());
            dins.close();
            bins.close();
        } catch (IOException e) {
        }
    }

    public MsgHead() {
        super();
    }

    public int getTotalLength() {
        return totalLength;
    }

    public void setTotalLength(int totalLength) {
        this.totalLength = totalLength;
    }

    public int getCommandId() {
        return commandId;
    }

    public void setCommandId(int commandId) {
        this.commandId = commandId;
    }

    public int getSequenceId() {
        return sequenceId;
    }

    public void setSequenceId(int sequenceId) {
        this.sequenceId = sequenceId;
    }
}
