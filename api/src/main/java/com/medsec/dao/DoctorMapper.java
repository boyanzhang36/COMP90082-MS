package com.medsec.dao;

import com.medsec.entity.Doctor;
import com.medsec.util.AppointmentStatus;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface DoctorMapper {
    List<Doctor> selectAllDoctors();
    Doctor selectOneDoctor(String doctorID);
	
    List<Doctor> getDoctorsByUserId(
            @Param("uid")  String uid,
            @Param("since")  String since,
            @Param("until")  String until,
            @Param("status") AppointmentStatus status);	//new doctor list
			
    void deleteDoctor(String doctorID);
    void updateDoctor(Doctor doctor);
    void addDoctor(Doctor doctor);
}
