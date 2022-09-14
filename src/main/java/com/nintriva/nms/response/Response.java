    package com.nintriva.nms.response;

    import lombok.Data;
    import lombok.experimental.SuperBuilder;
    import org.springframework.web.bind.annotation.ResponseBody;

    @ResponseBody
    @SuperBuilder
    @Data
    public class Response {

        private boolean success;
        private String message;

    }
