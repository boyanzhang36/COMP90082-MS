import 'package:flutter/material.dart';
import 'dart:convert';
import 'package:frontend/util/serverDetails.dart';
import 'package:http/http.dart' as http;
import 'package:frontend/util/authentication.dart';
import 'package:frontend/components/doctor.dart';

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
        body: Column(
            children: _doctors
                .map((element) => Padding(
                      padding: const EdgeInsets.all(8.0),
                      child: Card(
                        elevation: 4.0,
                        color: Color.fromRGBO(100, 80, 255, 0.5),
                        child: InkWell(
                          onTap: () {
                            Navigator.push(
                                context,
                                MaterialPageRoute(
                                    builder: (context) => Scaffold(
                                        appBar:
                                            AppBar(title: Text(element.name)),
                                        body: Column(
                                            children: <Widget>[
                                          Text("Address " + element.address),
                                          Text("Contact " +element.contact),
                                          Text("Email " + element.email),
                                          Text("Expertise" + element.expertise),
                                          Text("ID" + element.id)
                                        ])
                                    )
                                )
                            );
                          },
                          child: Row(
                            mainAxisAlignment: MainAxisAlignment.spaceEvenly,
                            children: <Widget>[
                              Icon(
                                Icons.person,
                                size: 16.0,
                                color: Colors.white,
                              ),
                              Column(children: <Widget>[
                                Text(
                                  element.name,
                                  style: TextStyle(
                                      fontSize: 16.0,
                                      color: Colors.white,
                                      fontWeight: FontWeight.bold),
                                ),
                                Text(
                                  element.email,
                                  style: TextStyle(
                                      fontSize: 16.0,
                                      color: Colors.white,
                                      fontWeight: FontWeight.bold),
                                )
                              ])
                            ],
                          ),
                        ),
                      ),
                    ))
                .toList()));
  }
}
