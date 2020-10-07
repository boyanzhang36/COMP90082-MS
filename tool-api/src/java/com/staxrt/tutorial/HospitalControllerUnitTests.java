package com.staxrt.tutorial;

import com.staxrt.tutorial.model.Hospital;
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
public class HospitalControllerUnitTests {

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
    public void testGetAllHospitals() {
        HttpHeaders headers = new HttpHeaders();
        HttpEntity<String> entity = new HttpEntity<String>(null, headers);

        ResponseEntity<String> response = restTemplate.exchange(getRootUrl() + "/hospitals",
                HttpMethod.GET, entity, String.class);

        Assert.assertNotNull(response.getBody());
    }

    @Test
    public void testGetHospitalById() {
        Hospital hospital = restTemplate.getForObject(getRootUrl() + "/hospitals/1", Hospital.class);
        System.out.println(hospital.getName());
        Assert.assertNotNull(hospital);
    }

    @Test
    public void testCreateHospital() {
        Hospital hospital = new Hospital();
        hospital.setName("Warringal Private Hospital");
        hospital.setAddress("216 burgundy Street, Heidelberg VIC 3084");
        hospital.setPhone("(03) 9274 1300");
        hospital.setAftPhone("NA");
        hospital.setFax("(03) 9459 7606");
        hospital.setEmail("hospital@hospital.com");
        hospital.setWebsite("https://www.warringalprivate.com.au/");


        ResponseEntity<Hospital> postResponse = restTemplate.postForEntity(getRootUrl() + "/hospitals", hospital, Hospital.class);
        Assert.assertNotNull(postResponse);
        Assert.assertNotNull(postResponse.getBody());
    }

    @Test
    public void testUpdatePost() {
        int id = 1;
        Hospital hospital = restTemplate.getForObject(getRootUrl() + "/hospitals/" + id, Hospital.class);
        hospital.setName("hospital1");

        restTemplate.put(getRootUrl() + "/hospitals/" + id, hospital);

        Hospital updatedHospital = restTemplate.getForObject(getRootUrl() + "/hospitals/" + id, Hospital.class);
        Assert.assertNotNull(updatedHospital);
    }

    @Test
    public void testDeletePost() {
        int id = 2;
        Hospital hospital = restTemplate.getForObject(getRootUrl() + "/hospitals/" + id, Hospital.class);
        Assert.assertNotNull(hospital);

        restTemplate.delete(getRootUrl() + "/hospitals/" + id);

        try {
            hospital = restTemplate.getForObject(getRootUrl() + "/hospitals/" + id, Hospital.class);
        } catch (final HttpClientErrorException e) {
            Assert.assertEquals(e.getStatusCode(), HttpStatus.NOT_FOUND);
        }
    }

}
