package com.staxrt.tutorial;

import com.staxrt.tutorial.model.Doctor;
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
public class DoctorControllerUnitTests {

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
    public void testGetAllDoctors() {
        HttpHeaders headers = new HttpHeaders();
        HttpEntity<String> entity = new HttpEntity<String>(null, headers);

        ResponseEntity<String> response = restTemplate.exchange(getRootUrl() + "/doctors",
                HttpMethod.GET, entity, String.class);

        Assert.assertNotNull(response.getBody());
    }

    @Test
    public void testGetDoctorById() {
        Doctor doctor = restTemplate.getForObject(getRootUrl() + "/doctors/1", Doctor.class);
        System.out.println(doctor.getName());
        Assert.assertNotNull(doctor);
    }

    @Test
    public void testCreateDoctor() {
        Doctor doctor = new Doctor();
        doctor.setName("Callum");
        doctor.setBio("NA");
        doctor.setAddress("14 fake street");
        doctor.setPhone("555");
        doctor.setFax("NA");
        doctor.setEmail("doctor@doctor.com");
        doctor.setWebsite("NA");
        doctor.setExpertise("Radiology");

        ResponseEntity<Doctor> postResponse = restTemplate.postForEntity(getRootUrl() + "/doctors", doctor, Doctor.class);
        Assert.assertNotNull(postResponse);
        Assert.assertNotNull(postResponse.getBody());
    }

    @Test
    public void testUpdatePost() {
        int id = 1;
        Doctor doctor = restTemplate.getForObject(getRootUrl() + "/doctors/" + id, Doctor.class);
        doctor.setName("admin1");

        restTemplate.put(getRootUrl() + "/doctors/" + id, doctor);

        Doctor updatedDoctor = restTemplate.getForObject(getRootUrl() + "/doctors/" + id, Doctor.class);
        Assert.assertNotNull(updatedDoctor);
    }

    @Test
    public void testDeletePost() {
        int id = 2;
        Doctor doctor = restTemplate.getForObject(getRootUrl() + "/doctors/" + id, Doctor.class);
        Assert.assertNotNull(doctor);

        restTemplate.delete(getRootUrl() + "/doctors/" + id);

        try {
            doctor = restTemplate.getForObject(getRootUrl() + "/doctors/" + id, Doctor.class);
        } catch (final HttpClientErrorException e) {
            Assert.assertEquals(e.getStatusCode(), HttpStatus.NOT_FOUND);
        }
    }

}