package com.capgemini.login.social.providers;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.social.connect.ConnectionRepository;

import org.springframework.social.linkedin.api.LinkedIn;

/**
 * @author dimehta
 *
 */
@Configuration
@Scope(value = "request",  proxyMode = ScopedProxyMode.TARGET_CLASS)
public class BaseProvider {


	private LinkedIn linkedIn;
	private ConnectionRepository connectionRepository;
	
	public  BaseProvider(LinkedIn linkedIn, ConnectionRepository connectionRepository) {	
		this.connectionRepository = connectionRepository;	
		this.linkedIn= linkedIn;
	}

	public ConnectionRepository getConnectionRepository() {
		return connectionRepository;
	}

	public void setConnectionRepository(ConnectionRepository connectionRepository) {
		this.connectionRepository = connectionRepository;
	}


	public LinkedIn getLinkedIn() {
		return linkedIn;
	}

	public void setLinkedIn(LinkedIn linkedIn) {
		this.linkedIn = linkedIn;
	}
		

}
