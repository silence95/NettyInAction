package io.websocket.handler;

import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.util.concurrent.ImmediateEventExecutor;

public class TextWebSocketFrameHandler implements ChannelHandler {

  private final ChannelGroup group = new DefaultChannelGroup(ImmediateEventExecutor.INSTANCE);
  
  public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
    // TODO Auto-generated method stub

  }

  public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
    // TODO Auto-generated method stub

  }

  public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
    // TODO Auto-generated method stub

  }

}
