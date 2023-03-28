package com.example.appdataserver.Client;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.serialization.ClassResolvers;
import io.netty.handler.codec.serialization.ObjectDecoder;
import io.netty.handler.codec.serialization.ObjectEncoder;

import java.util.Random;



public class Client {
    public static int MB_20 = 20 * 1_000_000;
    public static  void main(String[] args) {
        EventLoopGroup workerGroup = new NioEventLoopGroup();
        Bootstrap bootstrap = new Bootstrap()
                .group(workerGroup)
                .channel(NioServerSocketChannel.class)
                .remoteAddress("localhost",8899)
                .handler(new ChannelInitializer<SocketChannel>(){
                    @Override
                    public void initChannel(SocketChannel socketChannel) {
                        socketChannel.pipeline().addLast(
                                new ObjectDecoder(MB_20, ClassResolvers.cacheDisabled(null)),
                                new ObjectEncoder(),
                                new ClientHandler()

                        );
                    }

                });
        try {
            ChannelFuture channelFuture = bootstrap.connect().sync();
            Channel channel = channelFuture.channel();
            AuthRequest authRequest = new AuthRequest("log","pass");
            channel.writeAndFlush(authRequest);
            channelFuture.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

}
