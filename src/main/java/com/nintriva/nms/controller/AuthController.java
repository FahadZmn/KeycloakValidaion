    package com.nintriva.nms.controller;

    import java.util.HashMap;
    import java.util.Map;

    import com.nintriva.nms.dto.UserDTO;
    import org.keycloak.authorization.client.AuthzClient;
    import org.keycloak.authorization.client.Configuration;
    import org.keycloak.representations.AccessTokenResponse;
    import org.springframework.http.ResponseEntity;
    import org.springframework.web.bind.annotation.PostMapping;
    import org.springframework.web.bind.annotation.RequestBody;
    import org.springframework.web.bind.annotation.RequestMapping;
    import org.springframework.web.bind.annotation.RestController;



    @RequestMapping(value = "/users")
    @RestController
    public class AuthController {

        private String authServerUrl = "http://localhost:8080/auth/";
        private String realm = "nms";
        private String clientId = "springboot-keycloak";
        private String role = "student";
        //Get client secret from the Keycloak admin console (in the credential tab)
        private String clientSecret = "qbLGbRnwZgDKlrGBMcMar5KvENxcHHhE";



        @PostMapping(path = "/signin")
        public ResponseEntity<?> signin(@RequestBody  UserDTO userDTO) {

            Map<String, Object> clientCredentials = new HashMap<>();
            clientCredentials.put("secret", clientSecret);
            clientCredentials.put("grant_type", "password");

            Configuration configuration =
                    new Configuration(authServerUrl, realm, clientId, clientCredentials, null);
            AuthzClient authzClient = AuthzClient.create(configuration);

            AccessTokenResponse response =
                    authzClient.obtainAccessToken(userDTO.getEmail(), userDTO.getPassword());

            return ResponseEntity.ok(response);
        }
    }
