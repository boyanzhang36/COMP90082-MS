import 'package:flutter/material.dart';
import 'package:table_calendar/table_calendar.dart';
import 'package:frontend/util/authentication.dart';
import 'package:frontend/util/serverDetails.dart';
import 'package:http/http.dart' as http;
import 'package:frontend/components/appointment.dart';
import 'dart:convert';

class Appointments extends StatefulWidget {
  @override
  _AppointmentsState createState() => _AppointmentsState();
}

class _AppointmentsState extends State<Appointments> {
  var _calendarController;
  List<Appointment> _appointments = List<Appointment>();

  @override
  void initState() {
    super.initState();
    _calendarController = CalendarController();
    getAppointments();
  }

  @override
  void dispose() {
    _calendarController.dispose();
    super.dispose();
  }

  getAppointments() async {
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
          'me/appointments';
      print(url);
      Map<String, String> headers = {"Authorization": auth};
      print(headers);
      var jsonResponse = null;
      var response = await http.get(url, headers: headers);
      print(response.body);
      if (response.statusCode == 200) {
        print("200" + response.body);
        jsonResponse = json.decode(response.body);
        if (jsonResponse != null) {
          setState(() {
            for (var doc in jsonResponse) {
              _appointments.add(Appointment.fromJson(doc));
            }
          });
        }
      } else {print(response.body);}
    }
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
        appBar: AppBar(title: Text("Appointments")),
        body: Column(children: <Widget>[
          TableCalendar(calendarController: _calendarController,

          ),
          Column(
              children:
                  _appointments.map((element) => Text(element.title + element.date)).toList())
        ]));
  }
}
