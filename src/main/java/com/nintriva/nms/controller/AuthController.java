    package com.nintriva.nms.controller;

    import java.util.HashMap;
    import java.util.Map;

    import com.nintriva.nms.dto.LoginDto;
    import com.nintriva.nms.dto.UserDTO;
    import com.nintriva.nms.response.Response;
    import com.nintriva.nms.security.AuthenticationFailureHandler;
    import com.nintriva.nms.service.Service;
    import org.keycloak.authorization.client.AuthzClient;
    import org.keycloak.authorization.client.Configuration;
    import org.keycloak.representations.AccessTokenResponse;
    import org.springframework.beans.factory.annotation.Autowired;
    import org.springframework.http.HttpStatus;
    import org.springframework.http.ResponseEntity;
    import org.springframework.web.bind.annotation.*;


    @RequestMapping(value = "/users")
    @RestController
    @CrossOrigin(origins = "http://localhost:4200")
    public class AuthController {


        @Autowired
        private Service service;
        private String authServerUrl = "http://localhost:8080";
        private String realm = "nms";
        private String clientId = "springboot-keycloak";
        private String role = "student";
        //Get client secret from the Keycloak admin console (in the credential tab)
        private String clientSecret = "s8geyzovDwvJ9JfV7rcFhbFQTmu9bnSn";


        @PostMapping(path = "/signin")
        public ResponseEntity<?> signin(@RequestBody LoginDto loginDto) {

            try {
                ResponseEntity<?> s= service.authenticate(loginDto);
                return s;

            } catch (Exception e) {
              Response response = service.getAuthenticationFailureHandler();
              return new ResponseEntity<>(response,HttpStatus.UNAUTHORIZED);


            }

        }
    }