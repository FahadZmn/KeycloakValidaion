package com.nintriva.nms.service;

import com.nintriva.nms.dto.LoginDto;
import com.nintriva.nms.response.Response;
import com.nintriva.nms.security.AuthenticationFailureHandler;
import org.keycloak.authorization.client.AuthzClient;
import org.keycloak.authorization.client.Configuration;
import org.keycloak.representations.AccessTokenResponse;
import org.springframework.context.annotation.Bean;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.HashMap;
import java.util.Map;
@org.springframework.stereotype.Service

public class Service {

    int MAX_FAILED_ATTEMPT=5;

    private String authServerUrl = "http://localhost:8080";
    private String realm = "nms";
    private String clientId = "springboot-keycloak";
    private String role = "student";
    //Get client secret from the Keycloak admin console (in the credential tab)
    private String clientSecret = "s8geyzovDwvJ9JfV7rcFhbFQTmu9bnSn";

    public boolean flag=true;

    private AuthenticationManager authenticationManager;

    public ResponseEntity<?> authenticate(LoginDto userDto){

        Map<String, Object> clientCredentials = new HashMap<>();
        clientCredentials.put("secret", clientSecret);
        clientCredentials.put("grant_type", "password");

        Configuration configuration =
                new Configuration(authServerUrl, realm, clientId, clientCredentials, null);
        AuthzClient authzClient = AuthzClient.create(configuration);

        AccessTokenResponse response =
                authzClient.obtainAccessToken(userDto.getUsername(),userDto.getPassword());
        if(this.flag=true){
            this.MAX_FAILED_ATTEMPT=4;
        }
        return ResponseEntity.ok(response);

    }

    @Bean
        public Response getAuthenticationFailureHandler() {

        new AuthenticationFailureHandler();

        MAX_FAILED_ATTEMPT--;

        System.out.println(MAX_FAILED_ATTEMPT);

        if(MAX_FAILED_ATTEMPT <= 0){
            this.flag=false;
            return Response.builder().success(false).message("Kindly reset your password using the reset link send to your registered mail id").build();
        }

    return Response.builder().success(false).message("Incorrect Username or Password,You have "+MAX_FAILED_ATTEMPT+" more  attempts left").build();
}


}