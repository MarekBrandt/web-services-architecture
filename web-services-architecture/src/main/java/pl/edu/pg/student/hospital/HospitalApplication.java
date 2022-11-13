package pl.edu.pg.student.hospital;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class HospitalApplication {

	public static void main(String[] args) {
		SpringApplication.run(HospitalApplication.class, args);
	}

	@Bean
	public RouteLocator customRouteLocator(RouteLocatorBuilder builder) {
		return builder
				.routes()
				.route("wards", r -> r
						.host("localhost:8080")
						.and()
						.path("/api/wards/{name}", "/api/wards")
						.uri("http://localhost:8081"))
				.route("patients", r -> r
						.host("localhost:8080")
						.and()
						.path("/api/patients", "/api/patients/**", "/api/wards/{name}/patients", "/api/wards/{name}/patients/**")
						.uri("http://localhost:8082"))
				.build();
	}
}
