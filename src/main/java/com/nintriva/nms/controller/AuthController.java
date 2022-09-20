    package com.nintriva.nms.controller;

    import java.util.HashMap;
    import java.util.Map;

    import com.nintriva.nms.dto.UserDTO;
    import com.nintriva.nms.response.Response;
    import org.keycloak.authorization.client.AuthzClient;
    import org.keycloak.authorization.client.Configuration;
    import org.keycloak.representations.AccessTokenResponse;
    import org.springframework.http.ResponseEntity;
    import org.springframework.web.bind.annotation.*;


    @RequestMapping(value = "/users")
    @RestController
    @CrossOrigin(origins = "http://localhost:4200")
    public class AuthController {

        private String authServerUrl = "http://localhost:8080";
        private String realm = "nms";
        private String clientId = "springboot-keycloak";
        private String role = "student";
        //Get client secret from the Keycloak admin console (in the credential tab)
        private String clientSecret = "s8geyzovDwvJ9JfV7rcFhbFQTmu9bnSn";



        @PostMapping(path = "/signin")
        public ResponseEntity<?> signin(@RequestBody  UserDTO userDTO) {

            try {

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
            catch (Exception e){
                Response response1= Response.builder().success(false).message("invalid username or password").build();
                return  ResponseEntity.ok(response1);
            }
        }
    }
