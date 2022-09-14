	package com.nintriva.nms;


	import org.keycloak.adapters.springboot.KeycloakSpringBootConfigResolver;
	import org.springframework.boot.SpringApplication;
	import org.springframework.boot.autoconfigure.SpringBootApplication;
	import org.springframework.context.annotation.Bean;


	@SpringBootApplication
	public class NintrivanmsApplication {

//		@GetMapping
//		public ResponseEntity<List<User>> findUsers(){
//			return (ResponseEntity<List<User>>) ResponseEntity.ok(AuthService.getUsers());
//		}

		public static void main(String[] args) {
			SpringApplication.run(NintrivanmsApplication.class, args);
		}

		@Bean
		public KeycloakSpringBootConfigResolver keycloakConfigResolver() {
			return new KeycloakSpringBootConfigResolver();
		}

	}
