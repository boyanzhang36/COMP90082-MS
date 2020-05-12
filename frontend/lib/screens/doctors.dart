import 'package:flutter/material.dart';
import 'package:frontend/components/doctor.dart';
import 'package:frontend/screens/doctordetail.dart';
import 'dart:convert';
import 'package:frontend/util/serverDetails.dart';
import 'package:http/http.dart' as http;
import 'package:frontend/util/authentication.dart';

class Doctors extends StatefulWidget {
  @override
  _DoctorsState createState() => _DoctorsState();
}

class _DoctorsState extends State<Doctors> {
  List<Doctor> _doctors = List<Doctor>();

  @override
  void initState() {
    super.initState();
    getDoctors();
  }

  getDoctors() async {
    String currentToken = await Authentication.getCurrentToken();
    print(currentToken);
    if (currentToken == null) {
      print('bouncing');
      Authentication.bounceUser(context);
    } else {
      String auth = "Bearer " + currentToken;
      String url = ServerDetails.ip +
          ':' +
          ServerDetails.port +
          ServerDetails.api +
          'generalInformation/doctors';
      print(url);
      Map<String, String> headers = {"Authorization": auth};
      print(headers);
      var jsonResponse = null;
      var response = await http.get(url, headers: headers);
      print(response.body);
      if (response.statusCode == 200) {
        jsonResponse = json.decode(response.body);
        if (jsonResponse != null) {
          setState(() {
            for (var doc in jsonResponse) {
              _doctors.add(Doctor.fromJson(doc));
            }
          });
        }
      } else {}
    }
  }

  @override
  build(context) {
    return Scaffold(
        appBar: AppBar(title: Text("Doctors")),
        body: ListView(
            children: _doctors.map((element) => Container(
                decoration: BoxDecoration(
                  border: Border.all(width: 0.8),
                  borderRadius: BorderRadius.circular(12.0),
                ),
                margin:
                const EdgeInsets.symmetric(horizontal: 8.0, vertical: 4.0),
                child: ListTile(
                    leading: Icon(Icons.person),
                    title: Text(element.name),
                    trailing: Icon(Icons.arrow_right),
                    onTap: () {
                      Navigator.push(
                          context,
                          MaterialPageRoute(
                              builder: (context) =>
                                  doctordetail(element)));
                    }
                )
            )).toList()
        )

    );

  }
}

