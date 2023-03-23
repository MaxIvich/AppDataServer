package com.example.appdataserver.ServerNatty;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;



public class Server {
    public static  void main(String[] args) throws InterruptedException {
        EventLoopGroup bossGroup = new NioEventLoopGroup( 1);
        EventLoopGroup workerGroup = new NioEventLoopGroup();

        try {
            ServerBootstrap serverBootstrap = new ServerBootstrap();
            serverBootstrap.group(bossGroup,workerGroup)
                    .channel(NioServerSocketChannel.class)
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        public void initChannel(SocketChannel socketChannel){
                            ChannelPipeline pipeline = socketChannel.pipeline()
                                    .addLast(
                                            new StringHandler()
                                    );
                        }
                    });
            ChannelFuture channelFuture = serverBootstrap.bind(8899).sync();
            channelFuture.channel().closeFuture().sync();// Блокирующая операция
        }finally {
            workerGroup.shutdownGracefully();
            bossGroup.shutdownGracefully();

        }

    }
}
