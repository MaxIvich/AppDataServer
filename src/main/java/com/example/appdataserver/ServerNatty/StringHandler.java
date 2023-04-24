package com.example.appdataserver.ServerNatty;
import com.example.appdataserver.Bd.BdUsers;
import com.example.appdataserver.Client.*;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

public class StringHandler extends ChannelInboundHandlerAdapter{

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
        if(msg instanceof RegResponse response){
            String[] UserData = response.getResponse().split(" ",2);
            BdUsers.reg(UserData[0],UserData[1]);
            if(BdUsers.isReg(UserData[0],UserData[1])){
            ctx.writeAndFlush(new BooleanResponse(true));
            }
        }
    }
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable e) throws Exception {
        e.printStackTrace();

    }
}
