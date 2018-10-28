package org.winwin.controller;

import org.junit.Test;
import org.junit.runner.RunWith;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.junit4.SpringRunner;
import org.winwin.model.ApplicationUser;
import org.winwin.restservice.RestServiceApplication;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT, classes = RestServiceApplication.class)
public class UserControllerTest {
    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void postShouldCreateAUser() throws Exception {
        ApplicationUser user = new ApplicationUser();
        String emailId = "email";
        String userName = "userName";
        user.setEmail(emailId);
        user.setUsername(userName);
        user.setFirstname("firstName");
        user.setLastname("lastName");
        user.setRole("role");
        user.setLocation("location");
        user.setPassword("password");

        //create user
//        this.restTemplate.postForEntity("http://localhost:" + port + "/user", user, ApplicationUser.class);
//
//        //search user by email
//        assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/user/?email=" + emailId, ApplicationUser.class).getUsername())
//                .isEqualTo(userName);
//
//        //search user by username
//        user = this.restTemplate.getForObject("http://localhost:" + port + "/user/?userName=" + userName, ApplicationUser.class);
//        assertThat(user.getUsername()).isEqualTo(userName);
//
//        //get user by id
//        assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/user/" + user.getId(), ApplicationUser.class).getUsername())
//                .isEqualTo(userName);
    }
}
