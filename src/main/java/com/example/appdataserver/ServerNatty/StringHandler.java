package com.example.appdataserver.ServerNatty;
import com.example.appdataserver.Bd.BdUsers;
import com.example.appdataserver.Client.*;
import com.example.appdataserver.HelloController;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.buffer.ByteBuf;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;


public class StringHandler extends ChannelInboundHandlerAdapter{



   // private static final Map<Class<? extends BasicRequest>, Consumer<ChannelHandlerContext>> REQUEST_HANDLERS = new HashMap<>();
//
   // static {
   //     REQUEST_HANDLERS.put(AuthRequest.class, channelHandlerContext ->  {
   //         channelHandlerContext.writeAndFlush(new BasicResponse("Login Ok"));
//
   //     });
   //     REQUEST_HANDLERS.put(GetFileListRequest.class,channelHandlerContext -> {
   //         channelHandlerContext.writeAndFlush(new BasicResponse("File list ....."));
//
//
   //     });
   // }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println(ctx.channel().remoteAddress());

    }
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {

        if(msg instanceof BasicResponse response){
            String[] UserData = response.getResponse().split(" ",2);
          if (BdUsers.isReg(UserData[0],UserData[1])){
              System.out.println(UserData[0]+ "  "+ UserData[1]);
              ctx.writeAndFlush(new BooleanResponse(true));


          }

        }
    }
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable e) throws Exception {
        e.printStackTrace();

    }
}
