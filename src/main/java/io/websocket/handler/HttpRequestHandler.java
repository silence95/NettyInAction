package io.websocket.handler;

import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.DefaultFullHttpResponse;
import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.handler.codec.http.FullHttpResponse;
import io.netty.handler.codec.http.HttpHeaders;
import io.netty.handler.codec.http.HttpResponseStatus;
import io.netty.handler.codec.http.HttpVersion;
import io.netty.handler.codec.http.LastHttpContent;

public class HttpRequestHandler extends SimpleChannelInboundHandler<FullHttpRequest> {

  private final String wsUrl;
  
  public HttpRequestHandler(String path) {
    this.wsUrl = path;
  }

  @Override
  protected void channelRead0(ChannelHandlerContext ctx, FullHttpRequest request) throws Exception {
    if(wsUrl.equalsIgnoreCase(request.getUri())) {
      ctx.fireChannelRead(request.retain());
    } else {
      FullHttpResponse response1 = new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, HttpResponseStatus.CONTINUE);
      ctx.writeAndFlush(response1);
      
      FullHttpResponse response2 = new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, HttpResponseStatus.OK, Unpooled.wrappedBuffer("success".getBytes("UTF-8")));
      response2.headers().set(HttpHeaders.Names.CONTENT_TYPE, "text/plain;charset=utf-8");
      response2.headers().set(HttpHeaders.Names.CONTENT_LENGTH, response2.content().readableBytes());
      ctx.writeAndFlush(response2);
//      
//      ctx.writeAndFlush(LastHttpContent.EMPTY_LAST_CONTENT);
    }
    
  }
  
}
