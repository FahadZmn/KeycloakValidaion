    package com.nintriva.nms.dto;

    import com.fasterxml.jackson.annotation.JsonAutoDetect;
    import lombok.AllArgsConstructor;
    import lombok.Builder;
    import lombok.Data;
    import lombok.NoArgsConstructor;

    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    @JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
    public class LoginDto {
        private String username;
        private String password;
        private String client_id;
        private String grant_type;

    }