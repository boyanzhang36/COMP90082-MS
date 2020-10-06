package com.staxrt.tutorial;

import com.staxrt.tutorial.model.Appointment;
import com.staxrt.tutorial.model.User;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.*;
import org.springframework.util.MultiValueMap;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.HttpClientErrorException;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class AppointmentControllerUnitTests {

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
    public void testGetAllAppointments() {
        HttpHeaders headers = new HttpHeaders();
        HttpEntity<String> entity = new HttpEntity<String>(null, headers);

        ResponseEntity<String> response = restTemplate.exchange(getRootUrl() + "/appointments",
                HttpMethod.GET, entity, String.class);

        Assert.assertNotNull(response.getBody());
    }

    @Test
    public void testGetAppointmentById() {
        Appointment appointment = restTemplate.getForObject(getRootUrl() + "/appointments/1", Appointment.class);
        System.out.println(appointment.getTitle());
        Assert.assertNotNull(appointment);
    }

    @Test
    public void testCreateAppointment() {
        Appointment appointment = new Appointment();
        appointment.setUid(1);
        appointment.setDid(1);
        appointment.setTitle("Day Oncology Unit");

        appointment.setDetail("Education session");
        appointment.setDate_create("2020-05-01 00:00:00");
        appointment.setDate_change("2020-05-18 04:05:16");
        appointment.setDate("2020-06-18 14:15:00");
        appointment.setDuration(15);
        appointment.setNote("Looking after yourself during chemotherapy");
        appointment.setUser_note("NA");
        appointment.setStatus("UNCONFIRMED");



        ResponseEntity<Appointment> postResponse = restTemplate.postForEntity(getRootUrl() + "/appointments", appointment, Appointment.class);
        Assert.assertNotNull(postResponse);
        Assert.assertNotNull(postResponse.getBody());
    }

    @Test
    public void testUpdatePost() {
        int id = 1;
        Appointment appointment = restTemplate.getForObject(getRootUrl() + "/appointments/" + id, Appointment.class);
        appointment.setTitle("Title1");

        restTemplate.put(getRootUrl() + "/appointments/" + id, appointment);

        Appointment updatedAppointment = restTemplate.getForObject(getRootUrl() + "/appointments/" + id, Appointment.class);
        Assert.assertNotNull(updatedAppointment);
    }

    @Test
    public void testDeletePost() {
        int id = 2;
        Appointment appointment = restTemplate.getForObject(getRootUrl() + "/appointments/" + id, Appointment.class);
        Assert.assertNotNull(appointment);

        restTemplate.delete(getRootUrl() + "/appointments/" + id);

        try {
            appointment = restTemplate.getForObject(getRootUrl() + "/appointments/" + id, Appointment.class);
        } catch (final HttpClientErrorException e) {
            Assert.assertEquals(e.getStatusCode(), HttpStatus.NOT_FOUND);
        }
    }

}


