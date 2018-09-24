package org.winwin.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.winwin.lib.Environment;

@RestController
@Slf4j
public class AWSCognitoController {

    final static String AWS_COGNITO_APP_CLIENT_ID_ENV_VARIABLE = "AWS_COGNITO_APP_CLIENT_ID";
    final static String AWS_COGNITO_APP_SECRET_ENV_VARIABLE = "AWS_COGNITO_APP_SECRET";

    final static String AWS_COGNITO_APP_CLIENT_ID = Environment.getEnvironmentVariable(AWS_COGNITO_APP_CLIENT_ID_ENV_VARIABLE).orElse("");
    final static String AWS_COGNITO_APP_SECRET = Environment.getEnvironmentVariable(AWS_COGNITO_APP_SECRET_ENV_VARIABLE).orElse("");

    private static String SUCCESS = "OK";

    @GetMapping("/aws-cognito-callback")
    public String callback(@RequestParam("code") String code) {
        log.info("AWSCognitoController callback code = " + code );
        return SUCCESS;
    }

    @GetMapping("/aws-cognito-signout")
    public String signout() {
        return SUCCESS;
    }

}
