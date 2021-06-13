package com.bank.account.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;

import com.netflix.client.config.IClientConfig;
import com.netflix.loadbalancer.IPing;
import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.PingUrl;
import com.netflix.loadbalancer.RoundRobinRule;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class RibbonConfig {
	
	@Autowired
	IClientConfig ribbonClient;
	
	@Bean
	public IPing ribbonPing(IClientConfig ribbonClient) {
		String pingPath = "/actuator/health";
		  IPing ping = new PingUrl(false, pingPath);        
		  log.info("Configuring ping URI to [{}]", pingPath);
		  log.info("ribbonClient properties.! name: {} namespace: {}",ribbonClient.getClientName(), ribbonClient.getNameSpace());
		  return ping;
	}
	
	@Bean
	public IRule ribbonRule(IClientConfig ribbonClient) {
		return new RoundRobinRule();
	}

}
