import 'package:frontend/components/appointment.dart';
import 'package:flutter/material.dart';


class AppointmentDetail extends StatelessWidget {
  final Appointment _appointment;

  const AppointmentDetail(this._appointment);

  @override
  Widget build(BuildContext context) {
    return Scaffold(
        appBar: AppBar(title: Text("Your appointment")),
        body: Padding(
            padding: const EdgeInsets.all(8.0),
            child: Column(children: <Widget>[
              Card(
                  elevation: 4.0,
                  color: Color.fromRGBO(100, 80, 255, 0.5),
                  child: InkWell(
                      onTap: () {}, child: Text(_appointment.title.toString()
                  )
                  )
              ),
              Card(
                  elevation: 4.0,
                  color: Color.fromRGBO(100, 80, 255, 0.5),
                  child: InkWell(
                      onTap: () {}, child: Text(_appointment.date.toString()
                  )
                  )
              ),
              Card(
                  elevation: 4.0,
                  color: Color.fromRGBO(100, 80, 255, 0.5),
                  child: InkWell(
                      onTap: () {}, child: Text(_appointment.note.toString()
                  )
                  )
              ),
            ]
            )
        )
    );
  }
}
