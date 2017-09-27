package io.websocket;

import java.net.InetSocketAddress;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import io.netty.bootstrap.Bootstrap;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelPromise;
import io.netty.channel.EventLoop;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.util.concurrent.EventExecutor;
import io.netty.util.concurrent.Future;
import io.netty.util.concurrent.ScheduledFuture;

public class ChatServer {

  private void start(InetSocketAddress inetPort) {
    ServerBootstrap serverBootstrap = new ServerBootstrap();
    EventLoopGroup eventLoopGroup = new NioEventLoopGroup();
    try {
      serverBootstrap.group(eventLoopGroup)
               .channel(NioServerSocketChannel.class)
               .childHandler(new ChatServerInitializer());
      
      ChannelFuture future = serverBootstrap.bind(inetPort);
      future.syncUninterruptibly();
      future.channel().closeFuture().sync();
    } catch (InterruptedException e) {
      eventLoopGroup.shutdownGracefully();
      e.printStackTrace();
    }
  }
  
  public static void main(String[] args) {
    
    ChatServer chatServer = new ChatServer();
    chatServer.start(new InetSocketAddress(9999));
    
  }
}
