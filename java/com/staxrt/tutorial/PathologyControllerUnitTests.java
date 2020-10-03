package com.staxrt.tutorial;

import com.staxrt.tutorial.model.Pathology;
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
public class PathologyControllerUnitTests {

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
    public void testGetAllPathologies() {
        HttpHeaders headers = new HttpHeaders();
        HttpEntity<String> entity = new HttpEntity<String>(null, headers);

        ResponseEntity<String> response = restTemplate.exchange(getRootUrl() + "/pathologies",
                HttpMethod.GET, entity, String.class);

        Assert.assertNotNull(response.getBody());
    }

    @Test
    public void testGetPathologyById() {
        Pathology pathology = restTemplate.getForObject(getRootUrl() + "/pathologies/1", Pathology.class);
        System.out.println(pathology.getName());
        Assert.assertNotNull(pathology);
    }

    @Test
    public void testCreatePathology() {
        Pathology pathology = new Pathology();
        pathology.setName("Dorevitch Pathology");
        pathology.setAddress("66 Darebin Street, HEIDELBERG VIC 3084");
        pathology.setPhone("(03) 9457 2200");

        pathology.setHours("Monday - Friday, 8:30am - 5:30pm");

        pathology.setWebsite("https://www.dorevitch.com.au/patients/find-a-collection-centre/");


        ResponseEntity<Pathology> postResponse = restTemplate.postForEntity(getRootUrl() + "/pathologies", pathology, Pathology.class);
        Assert.assertNotNull(postResponse);
        Assert.assertNotNull(postResponse.getBody());
    }

    @Test
    public void testUpdatePost() {
        int id = 1;
        Pathology pathology = restTemplate.getForObject(getRootUrl() + "/pathologies/" + id, Pathology.class);
        pathology.setName("pathology1");

        restTemplate.put(getRootUrl() + "/pathologies/" + id, pathology);

        Pathology updatedPathology = restTemplate.getForObject(getRootUrl() + "/pathologies/" + id, Pathology.class);
        Assert.assertNotNull(updatedPathology);
    }

    @Test
    public void testDeletePost() {
        int id = 2;
        Pathology pathology = restTemplate.getForObject(getRootUrl() + "/pathologies/" + id, Pathology.class);
        Assert.assertNotNull(pathology);

        restTemplate.delete(getRootUrl() + "/pathologies/" + id);

        try {
            pathology = restTemplate.getForObject(getRootUrl() + "/pathologies/" + id, Pathology.class);
        } catch (final HttpClientErrorException e) {
            Assert.assertEquals(e.getStatusCode(), HttpStatus.NOT_FOUND);
        }
    }

}

