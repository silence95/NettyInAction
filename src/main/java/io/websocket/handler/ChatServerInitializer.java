package io.websocket.handler;

import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.codec.http.websocketx.WebSocketServerProtocolHandler;
import io.netty.handler.stream.ChunkedWriteHandler;

public class ChatServerInitializer extends ChannelInitializer<Channel> {

  @Override
  protected void initChannel(Channel ch) throws Exception {
    ChannelPipeline channelPipeline = ch.pipeline();
    channelPipeline.addLast(new HttpServerCodec());
    channelPipeline.addLast(new ChunkedWriteHandler());
    channelPipeline.addLast(new HttpObjectAggregator(64*1024));
    channelPipeline.addLast(new HttpRequestHandler("/ws"));
//    channelPipeline.addLast(new WebSocketServerProtocolHandler("/ws")); // 处理web socket 升级握手和ping/pong/close的socketFrame
//    channelPipeline.addLast(new TextWebSocketFrameHandler());
  }

}
