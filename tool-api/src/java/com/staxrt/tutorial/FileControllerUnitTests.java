package com.staxrt.tutorial;


import com.staxrt.tutorial.model.File;
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
public class FileControllerUnitTests {

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
    public void testGetAllFiles() {
        HttpHeaders headers = new HttpHeaders();
        HttpEntity<String> entity = new HttpEntity<String>(null, headers);

        ResponseEntity<String> response = restTemplate.exchange(getRootUrl() + "/files",
                HttpMethod.GET, entity, String.class);

        Assert.assertNotNull(response.getBody());
    }

    @Test
    public void testGetFileById() {
        File file = restTemplate.getForObject(getRootUrl() + "/files/1", File.class);
        System.out.println(file.getTitle());
        Assert.assertNotNull(file);
    }

    @Test
    public void testCreateFile() {
        File file = new File();
        file.setApptid(1);
        file.setTitle("abc");
        file.setLink("Link");



        ResponseEntity<File> postResponse = restTemplate.postForEntity(getRootUrl() + "/files", file, File.class);
        Assert.assertNotNull(postResponse);
        Assert.assertNotNull(postResponse.getBody());
    }

    @Test
    public void testUpdatePost() {
        int id = 1;
        File file = restTemplate.getForObject(getRootUrl() + "/files/" + id, File.class);
        file.setTitle("Title1");

        restTemplate.put(getRootUrl() + "/files/" + id, file);

        File updatedFile = restTemplate.getForObject(getRootUrl() + "/files/" + id, File.class);
        Assert.assertNotNull(updatedFile);
    }

    @Test
    public void testDeletePost() {
        int id = 2;
        File file = restTemplate.getForObject(getRootUrl() + "/files/" + id, File.class);
        Assert.assertNotNull(file);

        restTemplate.delete(getRootUrl() + "/files/" + id);

        try {
            file = restTemplate.getForObject(getRootUrl() + "/files/" + id, File.class);
        } catch (final HttpClientErrorException e) {
            Assert.assertEquals(e.getStatusCode(), HttpStatus.NOT_FOUND);
        }
    }

}


