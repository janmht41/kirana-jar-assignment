package com.assignment.KiranaService;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.reactive.function.client.WebClient;

@SpringBootApplication
/*todo
1) refactor and rename
 --controller(ERROR response + validations),config for webclient, method separation for api call and failure scenario ,
  constants
2) tests
3) transaction / locks
4) Documentation / javadoc
5) ENHANCEMENTS ??
 */
public class KiranaServiceApplication {
	public static void main(String[] args) {
		SpringApplication.run(KiranaServiceApplication.class, args);
	}

}
