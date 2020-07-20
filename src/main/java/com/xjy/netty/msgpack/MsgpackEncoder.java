package com.xjy.netty.msgpack;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;
import org.msgpack.MessagePack;

/**  编码器
 * @author xjy
 * @Date 2020/7/20 16:45
 */
public class MsgpackEncoder extends MessageToByteEncoder<Object> {

    @Override
    protected void encode(ChannelHandlerContext ctx, Object msg, ByteBuf out) throws Exception {
            MessagePack messagePack = new MessagePack();
            byte[] raw = messagePack.write(msg);
            out.writeBytes(raw);
    }
}
