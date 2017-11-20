package com.educarso.simulador;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = { "com.educarso.simulador","resources", "domain","view" })
public class SimuladorsoApplication {

	public static void main(String[] args) {
		SpringApplication.run(SimuladorsoApplication.class, args);
	}	

}
