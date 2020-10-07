package com.staxrt.tutorial;

import com.staxrt.tutorial.model.NotificationToken;
import com.staxrt.tutorial.model.User;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.*;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.HttpClientErrorException;


@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class NotificationTokenControllerUnitTests {

    @Autowired
    private TestRestTemplate restTemplate;

    @LocalServerPort
    private int port;

    private String getRootUrl() {
        return "http://localhost:" + port;
    }

    @Test
    public void contextLoads() {
    }

    @Test
    public void testGetAllNotificationTokens() {
        HttpHeaders headers = new HttpHeaders();
        HttpEntity<String> entity = new HttpEntity<String>(null, headers);

        ResponseEntity<String> response = restTemplate.exchange(getRootUrl() + "/notificationTokens",
                HttpMethod.GET, entity, String.class);

        Assert.assertNotNull(response.getBody());
    }

    @Test
    public void testGetNotificationTokenById() {
        NotificationToken notificationToken = restTemplate.getForObject(getRootUrl() + "/notificationTokens/1", NotificationToken.class);
        System.out.println(notificationToken.getFcm_token());
        Assert.assertNotNull(notificationToken);
    }

    @Test
    public void testCreateNotificationToken() {
        NotificationToken notificationToken = new NotificationToken();
        notificationToken.setUid(1);
        notificationToken.setFcm_token("abc1234");



        ResponseEntity<NotificationToken> postResponse = restTemplate.postForEntity(getRootUrl() + "/notificationTokens", notificationToken, NotificationToken.class);
        Assert.assertNotNull(postResponse);
        Assert.assertNotNull(postResponse.getBody());
    }

    @Test
    public void testUpdatePost() {
        int id = 114;
        NotificationToken notificationToken = restTemplate.getForObject(getRootUrl() + "/notificationTokens/" + id, NotificationToken.class);
        notificationToken.setFcm_token("12345fgr");

        restTemplate.put(getRootUrl() + "/notificationTokens/" + id, notificationToken);

        NotificationToken updatedNotificationToken = restTemplate.getForObject(getRootUrl() + "/notificationTokens/" + id, NotificationToken.class);
        Assert.assertNotNull(updatedNotificationToken);
    }

    @Test
    public void testDeletePost() {
        int id = 2;
        NotificationToken notificationToken = restTemplate.getForObject(getRootUrl() + "/notificationTokens/" + id, NotificationToken.class);
        Assert.assertNotNull(notificationToken);

        restTemplate.delete(getRootUrl() + "/notificationTokens/" + id);

        try {
            notificationToken = restTemplate.getForObject(getRootUrl() + "/notificationTokens/" + id, NotificationToken.class);
        } catch (final HttpClientErrorException e) {
            Assert.assertEquals(e.getStatusCode(), HttpStatus.NOT_FOUND);
        }
    }

}



