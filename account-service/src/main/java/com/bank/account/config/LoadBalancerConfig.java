package com.bank.account.config;

import java.util.Arrays;
import java.util.List;

import org.springframework.cloud.client.DefaultServiceInstance;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.loadbalancer.core.ServiceInstanceListSupplier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;

@Component
public class LoadBalancerConfig {

	@Bean
	@Primary
	ServiceInstanceListSupplier serviceInstanceListSupplier() {
		return new ServiceInstanceListSupplierImplementation("TransactionService");
	}

}

@Slf4j
class ServiceInstanceListSupplierImplementation implements ServiceInstanceListSupplier {

	private final String serviceId;

	public ServiceInstanceListSupplierImplementation(String serviceId) {
		log.info("Inside ServiceInstanceListSupplierImplementation constructor");
		this.serviceId = serviceId;
	}

	@Override
	public Flux<List<ServiceInstance>> get() {
		log.info("Generating instance list");
		return Flux.just(Arrays.asList(new DefaultServiceInstance(serviceId + "1", "localhost", 8200, false),
				new DefaultServiceInstance(serviceId + "2", "localhost", 8201, false)));
	}

	@Override
	public String getServiceId() {
		return serviceId;
	}

}
