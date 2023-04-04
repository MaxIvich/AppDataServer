package com.example.appdataserver.Client;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;

import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.serialization.ClassResolvers;
import io.netty.handler.codec.serialization.ObjectDecoder;
import io.netty.handler.codec.serialization.ObjectEncoder;



public class Client {

    private SocketChannel channel;
    public static int MB_20 = 20 * 1_000_000;

   public Client (){
       Thread t = new Thread(()-> {

           EventLoopGroup NioEventGroup = new NioEventLoopGroup();
           Bootstrap bootstrap = new Bootstrap();
           bootstrap.group(NioEventGroup);
           bootstrap.channel(NioSocketChannel.class);
           bootstrap.remoteAddress("localhost", 8899);
           bootstrap.handler(new ChannelInitializer<SocketChannel>() {
               @Override
               public void initChannel(SocketChannel socketChannel) {
                   channel = socketChannel;

                   socketChannel.pipeline().addLast(
                           new ObjectDecoder(MB_20, ClassResolvers.cacheDisabled(null)),
                           new ObjectEncoder(),
                           new ClientHandler()
                   );
               }
           });
           try {
               ChannelFuture channelFuture = bootstrap.connect().sync();
               channelFuture.channel().closeFuture().sync();
           } catch (InterruptedException e) {
               throw new RuntimeException(e);
           }
       });
       t.start();
   }
    public void sendMsg(BasicResponse msg){
        channel.writeAndFlush(msg);

    }


    public void closeConnection() {
        channel.close();
    }
}
