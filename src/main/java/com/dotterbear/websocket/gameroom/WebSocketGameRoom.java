package com.dotterbear.websocket.gameroom;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan(basePackages = "com.dotterbear")
@ConfigurationProperties
public class WebSocketGameRoom {
}
