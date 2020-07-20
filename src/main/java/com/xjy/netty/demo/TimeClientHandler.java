package com.xjy.netty.demo;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;

/**
 * @author xjy
 * @Date 2020/7/14 13:53
 */
public class TimeClientHandler extends ChannelHandlerAdapter {

//    private final ByteBuf firstMessage;

    private byte[] req;

    private int counter;

    public TimeClientHandler() {
        req = ("QUERY TIME ORDER" +  System.getProperty("line.separator")).getBytes();
//        firstMessage = Unpooled.buffer(req.length);
//        firstMessage.writeBytes(req);
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
       ByteBuf message = null;
       for (int i = 0; i < 100; i++){
           message = Unpooled.buffer(req.length);
           message.writeBytes(req);
           ctx.writeAndFlush(message);
       }
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
//        ByteBuf byteBuf = (ByteBuf) msg;
//        byte[] req = new byte[byteBuf.readableBytes()];
//        byteBuf.readBytes(req);
//        String body = new String(req,"UTF-8");
        String body = (String) msg;
        System.err.println("now is : " + body + " ; the counter is : " + ++counter);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        //释放资源
        ctx.close();
    }
}
