package com.dotterbear.websocket.gameroom.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.AbstractWebSocketMessageBrokerConfigurer;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;

@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig extends AbstractWebSocketMessageBrokerConfigurer {

	@Value(value = "${websocket.destination.prefix.broker}")
	String[] brokerDestinationPrefixs;

	@Value(value = "${websocket.destination.prefix.application}")
	String[] applicationDestinationPrefixs;

	@Value(value = "${websocket.stomp.endpoint}")
	String[] stompEndpoints;

	@Override
	public void configureMessageBroker(MessageBrokerRegistry config) {
		config.enableSimpleBroker(brokerDestinationPrefixs);
		config.setApplicationDestinationPrefixes(applicationDestinationPrefixs);
	}

	@Override
	public void registerStompEndpoints(StompEndpointRegistry registry) {
		registry.addEndpoint(stompEndpoints).withSockJS();
	}

}
