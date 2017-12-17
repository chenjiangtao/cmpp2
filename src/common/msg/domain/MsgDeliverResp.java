package common.msg.domain;

import org.apache.log4j.Logger;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;

/**
 * @author 张科伟
 */
public class MsgDeliverResp extends MsgHead {
    private static Logger logger = Logger.getLogger(MsgDeliverResp.class);
    private long msg_Id;//信息标识（CMPP_DELIVER中的Msg_Id字段）
    private int result;//结果 0：正确 1：消息结构错 2：命令字错 3：消息序号重复 4：消息长度错 5：资费代码错 6：超过最大信息长 7：业务代码错8: 流量控制错9~ ：其他错误

    public byte[] toByteArray() {
        ByteArrayOutputStream bous = new ByteArrayOutputStream();
        DataOutputStream dous = new DataOutputStream(bous);
        try {
            dous.writeInt(this.getTotalLength());
            dous.writeInt(this.getCommandId());
            dous.writeInt(this.getSequenceId());
            dous.writeLong(this.msg_Id);
            dous.writeInt(this.result);
            dous.close();
        } catch (IOException e) {
            logger.error("封装链接二进制数组失败。");
        }
        return bous.toByteArray();
    }

    public long getMsg_Id() {
        return msg_Id;
    }

    public void setMsg_Id(long msg_Id) {
        this.msg_Id = msg_Id;
    }

    public int getResult() {
        return result;
    }

    public void setResult(int result) {
        this.result = result;
    }
}
