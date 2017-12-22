package com.smspai.sms.domain;

import org.apache.log4j.Logger;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;

/**
 * Submit消息结构应答定义
 *
 * @author 张科伟 2011-08-22 17:30
 */
public class MsgSubmitResp extends MsgHead {
    private static Logger logger = Logger.getLogger(MsgSubmitResp.class);
    private long msgId;
    /**
     * 结果
     * 0：正确
     * 1：消息结构错
     * 2：命令字错
     * 3：消息序号重复
     * 4：消息长度错
     * 5：资费代码错
     * 6：超过最大信息长
     * 7：业务代码错
     * 8：流量控制错
     * 9：本网关不负责服务此计费号码
     * 10：Src_Id错误
     * 11：Msg_src错误
     * 12：Fee_terminal_Id错误
     * 13：Dest_terminal_Id错误
     */
    private byte result;

    public MsgSubmitResp(byte[] data) {
        if (data.length == 8 + 8 + 1) {
            ByteArrayInputStream bins = new ByteArrayInputStream(data);
            DataInputStream dins = new DataInputStream(bins);
            try {
                this.setTotalLength(data.length + 4);
                this.setCommandId(dins.readInt());
                this.setSequenceId(dins.readInt());
                this.msgId = dins.readLong();
                this.result = dins.readByte();
                dins.close();
                bins.close();
            } catch (IOException e) {
            }
        } else {
            logger.info("发送短信IMSP回复,解析数据包出错，包长度不一致。长度为:" + data.length);
        }
    }

    public long getMsgId() {
        return msgId;
    }

    public void setMsgId(long msgId) {
        this.msgId = msgId;
    }

    public int getResult() {
        return result;
    }

    public void setResult(byte result) {
        this.result = result;
    }
}
