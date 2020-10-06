package com.staxrt.tutorial;

import com.staxrt.tutorial.model.Resource;
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
public class ResourceControllerUnitTests {

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
    public void testGetAllResources() {
        HttpHeaders headers = new HttpHeaders();
        HttpEntity<String> entity = new HttpEntity<String>(null, headers);

        ResponseEntity<String> response = restTemplate.exchange(getRootUrl() + "/resources",
                HttpMethod.GET, entity, String.class);

        Assert.assertNotNull(response.getBody());
    }

    @Test
    public void testGetResourceById() {
        Resource resource = restTemplate.getForObject(getRootUrl() + "/resources/1", Resource.class);
        System.out.println(resource.getName());
        Assert.assertNotNull(resource);
    }

    @Test
    public void testCreateResource() {
        Resource resource = new Resource();
        resource.setUid(1);
        resource.setName("Download Information");
        resource.setWebsite("https://www.google.com.au/");



        ResponseEntity<Resource> postResponse = restTemplate.postForEntity(getRootUrl() + "/resources", resource, Resource.class);
        Assert.assertNotNull(postResponse);
        Assert.assertNotNull(postResponse.getBody());
    }

    @Test
    public void testUpdatePost() {
        int id = 1;
        Resource resource = restTemplate.getForObject(getRootUrl() + "/resources/" + id, Resource.class);
        resource.setName("Title1");

        restTemplate.put(getRootUrl() + "/resources/" + id, resource);

        Resource updatedResource = restTemplate.getForObject(getRootUrl() + "/resources/" + id, Resource.class);
        Assert.assertNotNull(updatedResource);
    }

    @Test
    public void testDeletePost() {
        int id = 2;
        Resource resource = restTemplate.getForObject(getRootUrl() + "/resources/" + id, Resource.class);
        Assert.assertNotNull(resource);

        restTemplate.delete(getRootUrl() + "/resources/" + id);

        try {
            resource = restTemplate.getForObject(getRootUrl() + "/resources/" + id, Resource.class);
        } catch (final HttpClientErrorException e) {
            Assert.assertEquals(e.getStatusCode(), HttpStatus.NOT_FOUND);
        }
    }

}


