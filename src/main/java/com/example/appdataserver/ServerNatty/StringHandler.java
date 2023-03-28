package com.example.appdataserver.ServerNatty;
import com.example.appdataserver.Client.*;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.buffer.ByteBuf;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;


public class StringHandler extends ChannelInboundHandlerAdapter{

    private static final Map<Class<? extends BasicRequest>, Consumer<ChannelHandlerContext>> REQUEST_HANDLERS = new HashMap<>();

    static {
        REQUEST_HANDLERS.put(AuthRequest.class, channelHandlerContext ->  {
            channelHandlerContext.writeAndFlush(new BasicResponse("Login Ok"));

        });
        REQUEST_HANDLERS.put(GetFileListRequest.class,channelHandlerContext -> {
            channelHandlerContext.writeAndFlush(new BasicResponse("File list ....."));


        });
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println(ctx.channel().remoteAddress());

    }
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        BasicRequest request = (BasicRequest) msg;
        System.out.println(request.getType());
        Consumer<ChannelHandlerContext> channelHandlerContextConsumer = REQUEST_HANDLERS.get(request.getClass());
        channelHandlerContextConsumer.accept(ctx);


    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable e) throws Exception {
        e.printStackTrace();

    }
}
