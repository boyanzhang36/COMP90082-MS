package com.medsec.util;

import com.medsec.entity.*;
import org.json.simple.JSONObject;

import java.time.Duration;
import java.time.Instant;
import java.time.LocalDate;

/**
 * DataManager that process JSON and return entity
 */
public class DataManager {

    private static com.medsec.util.DataManager instance = null;

    public static com.medsec.util.DataManager getInstance() {
        if (instance == null) {
            instance = new com.medsec.util.DataManager();
        }
        return instance;
    }

    /** create patient instance from json object */
    public User processPatient(JSONObject user) {
        String id = (String) user.get("PatientId");
        String surname = (String) user.get("Surname");
        String firstName = (String) user.get("FirstName");
        String email = (String) user.get("Email");
        String street = (String) user.get("Street");
        String suburb = (String) user.get("Suburb");
        String state = (String) user.get("State");
        LocalDate dob = LocalDate.parse((String) user.get("DOB"));
        User patient = new User().id(id).surname(surname).firstname(firstName).email(email)
                .street(street).suburb(suburb).state(state).dob(dob).role(UserRole.PATIENT);
        return patient;
    }

    /** create appointment instance from json object */
    public Appointment processAppt(JSONObject appt) {
        String id = (String) appt.get("Id");
        String uid = (String) appt.get("PT_Id_Fk");
        String did = (String) appt.get("Doctor_Id");
        String title = (String) appt.get("Reason");
        String detail = (String) appt.get("Comment");
        String note = (String) appt.get("Note");
        Instant dateCreate = Instant.parse((String) appt.get("CreationDate"));
        String test = (String) appt.get("StartTime");
        long startTime = Long.parseLong(test);
        Instant dateChange = updateTimeConvert((String) appt.get("LastUpdated"));
        Instant dateStart = Instant.parse((String) appt.get("StartDate"));
        Instant date = startDateConvert(dateStart, startTime);
        int duration = Integer.parseInt((String) appt.get("ApptDuration"));
        String status = (String) appt.get("Status");
        Appointment appointment = new Appointment().id(id).uid(uid).did(did).title(title).detail(detail).note(note)
                .date_create(dateCreate).date_change(dateChange).date(date).duration(duration).status(status);
        return appointment;
    }

    /* get the correct start time of an appointment */
    public Instant startDateConvert(Instant startDate, long startTime) {
        startDate = startDate.minus(Duration.ofHours(10));
        startDate = startDate.plusMillis(startTime);
        return startDate;

    }

    /** get the correct update time from String */
    public Instant updateTimeConvert(String lastChnageDate) {
        String year = lastChnageDate.substring(0, 4);
        String month = lastChnageDate.substring(4, 6);
        String day = lastChnageDate.substring(6, 8);
        String hour = lastChnageDate.substring(8, 10);
        String minute = lastChnageDate.substring(10, 12);
        String second = lastChnageDate.substring(12);
        String updateTime = year + "-" + month + "-" + day + "T" + hour + ":" + minute + ":" + second
                + ".00Z";
        Instant instant = Instant.parse(updateTime);
        return instant;
    }

    public Doctor processDoctor(JSONObject dctor) {
        String id = (String) dctor.get("id");
        String name = (String) dctor.get("name");
        String address = (String) dctor.get("address");
        String contact = (String) dctor.get("contact");
        String email = (String) dctor.get("email");
        String website = (String) dctor.get("website");
        String expertise = (String) dctor.get("expertise");
        Doctor doctor = new Doctor().id(id).name(name).address(address).contact(contact)
                .email(email).website(website).expertise(expertise);
        return doctor;
    }

    public Hospital processHospital(JSONObject hspital) {
        String id = (String) hspital.get("id");
        String name = (String) hspital.get("name");
        String contact = (String) hspital.get("contact");
        String address = (String) hspital.get("address");
        String fax = (String) hspital.get("fax");
        String website = (String) hspital.get("website");
        String type = (String) hspital.get("type");
        Hospital hospital = new Hospital().id(id).name(name).contact(contact).address(address)
                .fax(fax).website(website).type(type);
        return hospital;
    }

    public Pathology processPathology(JSONObject pthology) {
        String id = (String) pthology.get("id");
        String name = (String) pthology.get("name");
        String contact = (String) pthology.get("contact");
        String address = (String) pthology.get("address");
        String fax = (String) pthology.get("fax");
        String website = (String) pthology.get("website");
        Pathology pathology = new Pathology().id(id).name(name).contact(contact).address(address)
                .fax(fax).website(website);
        return pathology;
    }

    public Radiology processRadiology(JSONObject rdiology) {
        String id = (String) rdiology.get("id");
        String name = (String) rdiology.get("name");
        String contact = (String) rdiology.get("contact");
        String address = (String) rdiology.get("address");
        String fax = (String) rdiology.get("fax");
        String website = (String) rdiology.get("website");
        Radiology radiology = new Radiology().id(id).name(name).contact(contact).address(address)
                .fax(fax).website(website);
        return radiology;
    }

    public Resource processResource(JSONObject rsource) {
        String id = (String) rsource.get("id");
        String uid = (String) rsource.get("uid");
        String name = (String) rsource.get("name");
        String website = (String) rsource.get("website");
        Resource resource = new Resource().id(id).uid(uid).name(name).website(website);
        return resource;
    }
}
