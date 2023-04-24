package com.example.appdataserver.Client;

import com.example.appdataserver.HelloController;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

public class ClientHandler extends ChannelInboundHandlerAdapter {
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        super.channelActive(ctx);
        System.out.println("Канал Клиента активен");
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {

        if(msg instanceof BooleanResponse response){
            System.out.println(msg);
            System.out.println(response.isResponse());
            if(response.isResponse()){
                HelloController.isAuth = true;
            };
        }
    }

}
