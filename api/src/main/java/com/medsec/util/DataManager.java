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
        String firstName = (String) user.get("FirstName");
        String middleName = (String) user.get("MiddleName");
        String surname = (String) user.get("Surname");
        LocalDate dob = LocalDate.parse((String) user.get("DOB"));
        String email = (String) user.get("Email");
        String street = (String) user.get("Street");
        String suburb = (String) user.get("Suburb");
        String state = (String) user.get("State");
        User patient = new User().id(id).firstname(firstName).middlename(middleName).surname(surname).dob(dob)
                .email(email).street(street).suburb(suburb).state(state).role(UserRole.PATIENT);
        return patient;
    }

    /** create appointment instance from json object */
    public Appointment processAppt(JSONObject appt) {
        String id = (String) appt.get("Id");
        String uid = (String) appt.get("PT_Id_Fk");
        String did = (String) appt.get("ProviderID");
        String title = (String) appt.get("Name");
        String detail = (String) appt.get("Reason");
        Instant dateCreate = Instant.parse((String) appt.get("CreationDate"));
        String test = (String) appt.get("StartTime");
        long startTime = Long.parseLong(test);
        Instant dateChange = updateTimeConvert((String) appt.get("LastUpdated"));
        Instant dateStart = Instant.parse((String) appt.get("StartDate"));
        Instant date = startDateConvert(dateStart, startTime);
        int duration = Integer.parseInt((String) appt.get("ApptDuration"));
        String note = (String) appt.get("Note");
        Appointment appointment = new Appointment().id(id).uid(uid).did(did).title(title).detail(detail)
                .date_create(dateCreate).date_change(dateChange).date(date).duration(duration).note(note).status(AppointmentStatus.UNCONFIRMED);
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

    /** create doctor instance from json object */
    public Doctor processDoctor(JSONObject dctor) {
        String id = (String) dctor.get("Id");
        String name = (String) dctor.get("Name");
        String bio = (String) dctor.get("Bio");
        String address = (String) dctor.get("Address");
        String phone = (String) dctor.get("Phone");
        String fax = (String) dctor.get("Fax");
        String email = (String) dctor.get("Email");
        String website = (String) dctor.get("Website");
        String expertise = (String) dctor.get("Expertise");
        Doctor doctor = new Doctor().id(id).name(name).bio(bio).address(address).phone(phone)
                .fax(fax).email(email).website(website).expertise(expertise);
        return doctor;
    }

    /** create hospital instance from json object */
    public Hospital processHospital(JSONObject hspital) {
        String id = (String) hspital.get("Id");
        String name = (String) hspital.get("Name");
        String address = (String) hspital.get("Address");
        String emergencyDept = (String) hspital.get("EmergencyDept");
        String phone = (String) hspital.get("Phone");
        String aftPhone = (String) hspital.get("AftPhone");
        String fax = (String) hspital.get("Fax");
        String email = (String) hspital.get("Email");
        String website = (String) hspital.get("Website");
        Hospital hospital = new Hospital().id(id).name(name).address(address).emergencyDept(emergencyDept)
                .phone(phone).aftPhone(aftPhone).fax(fax).email(email).website(website);
        return hospital;
    }

    /** create pathology instance from json object */
    public Pathology processPathology(JSONObject pthology) {
        String id = (String) pthology.get("Id");
        String name = (String) pthology.get("Name");
        String address = (String) pthology.get("Address");
        String phone = (String) pthology.get("Phone");
        String hours = (String) pthology.get("Hours");
        if (hours != null){ hours = hours.replaceAll("\u2013", "-"); }
        String website = (String) pthology.get("Website");
        Pathology pathology = new Pathology().id(id).name(name).address(address).phone(phone)
                .hours(hours).website(website);
        return pathology;
    }

    /** create radiology instance from json object */
    public Radiology processRadiology(JSONObject rdiology) {
        String id = (String) rdiology.get("Id");
        String name = (String) rdiology.get("Name");
        String address = (String) rdiology.get("Address");
        String phone = (String) rdiology.get("Phone");
        String fax = (String) rdiology.get("Fax");
        String hours = (String) rdiology.get("Hours");
        String email = (String) rdiology.get("Email");
        String website = (String) rdiology.get("Website");
        Radiology radiology = new Radiology().id(id).name(name).address(address).phone(phone)
                .fax(fax).hours(hours).email(email).website(website);
        return radiology;
    }

    /** create resource instance from json object */
    public Resource processResource(JSONObject rsource) {
        String id = (String) rsource.get("Id");
        String uid = (String) rsource.get("Uid");
        String name = (String) rsource.get("Name");
        String website = (String) rsource.get("Website");
        Resource resource = new Resource().id(id).uid(uid).name(name).website(website);
        return resource;
    }
}
