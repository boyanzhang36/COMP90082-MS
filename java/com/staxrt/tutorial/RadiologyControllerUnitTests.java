package com.staxrt.tutorial;
import com.staxrt.tutorial.model.Hospital;
import com.staxrt.tutorial.model.Radiology;
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
public class RadiologyControllerUnitTests {

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
    public void testGetAllRadiologies() {
        HttpHeaders headers = new HttpHeaders();
        HttpEntity<String> entity = new HttpEntity<String>(null, headers);

        ResponseEntity<String> response = restTemplate.exchange(getRootUrl() + "/radiologies",
                HttpMethod.GET, entity, String.class);

        Assert.assertNotNull(response.getBody());
    }

    @Test
    public void testGetRadiologyById() {
        Radiology radiology = restTemplate.getForObject(getRootUrl() + "/radiologies/1", Radiology.class);
        System.out.println(radiology.getName());
        Assert.assertNotNull(radiology);
    }

    @Test
    public void testCreateRadiology() {
        Radiology radiology = new Radiology();
        radiology.setName("I-MED ");
        radiology.setAddress("Level 1/10 Martin St, Heidelberg VIC 3084");
        radiology.setPhone("(03) 9450 1800");
        radiology.setFax("(03) 9450 1888");
        radiology.setHours("Monday - Friday, 8:30am - 5:30pm");
        radiology.setEmail("radiology@radiology.com");
        radiology.setWebsite("https://i-med.com.au/clinics/clinic/Heidelberg");


        ResponseEntity<Radiology> postResponse = restTemplate.postForEntity(getRootUrl() + "/radiologies", radiology, Radiology.class);
        Assert.assertNotNull(postResponse);
        Assert.assertNotNull(postResponse.getBody());
    }

    @Test
    public void testUpdatePost() {
        int id = 1;
        Radiology radiology = restTemplate.getForObject(getRootUrl() + "/radiologies/" + id, Radiology.class);
        radiology.setName("radiology1");

        restTemplate.put(getRootUrl() + "/radiologies/" + id, radiology);

        Radiology updatedRadiology = restTemplate.getForObject(getRootUrl() + "/radiologies/" + id, Radiology.class);
        Assert.assertNotNull(updatedRadiology);
    }

    @Test
    public void testDeletePost() {
        int id = 2;
        Radiology radiology = restTemplate.getForObject(getRootUrl() + "/radiologies/" + id, Radiology.class);
        Assert.assertNotNull(radiology);

        restTemplate.delete(getRootUrl() + "/radiologies/" + id);

        try {
            radiology = restTemplate.getForObject(getRootUrl() + "/radiologies/" + id, Radiology.class);
        } catch (final HttpClientErrorException e) {
            Assert.assertEquals(e.getStatusCode(), HttpStatus.NOT_FOUND);
        }
    }

}

