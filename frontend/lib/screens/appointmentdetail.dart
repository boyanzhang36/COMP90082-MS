import 'package:flutter/cupertino.dart';
import 'package:frontend/components/appointment.dart';
import 'package:flutter/material.dart';


class AppointmentDetail extends StatelessWidget {
  final Appointment _appointment;

  const AppointmentDetail(this._appointment);

  @override

  Widget build(BuildContext context) {
    return Scaffold(appBar: PreferredSize(
        preferredSize: Size.fromHeight(60.0),
        child: AppBar(
          leading: BackButton(color: Colors.black),
          title: Text("Appointment Details", style: TextStyle(color: Colors.black)),
          backgroundColor: Colors.transparent,
//          backgroundColor: Color(0x44000000),
          elevation: 0,
        )
        ),

        body: Container( color: Color.fromARGB(255, 196, 218, 234),

                  child:ListView(
//                    crossAxisAlignment: CrossAxisAlignment.start,
                    shrinkWrap: true,
                    padding: const EdgeInsets.all(4.0),
                    children: <Widget>[
                      Row(
                            mainAxisAlignment: MainAxisAlignment.start,
                            children: <Widget>[
                              Text("Appts:", style: TextStyle(fontSize: 25.0, fontFamily: "Arial",color:Colors.black, height: 1.5),
                                  textAlign: TextAlign.left,
                                  overflow: TextOverflow.ellipsis),
                            ],
                          ),
                      Wrap(
                        spacing: 4.0,
                        runSpacing: 4.0,
                        alignment: WrapAlignment.start,
                        children: <Widget>[
                          Text(_appointment.title.toString(), style: TextStyle(fontSize: 15.0, fontFamily: "Arial",color:Colors.grey, height: 1.5 ),
                              textAlign: TextAlign.left,
//                              overflow: TextOverflow.ellipsis
                          ),
                        ],
                      ),
                      Row(
                        mainAxisAlignment: MainAxisAlignment.start,
                        children: <Widget>[
                          Text("Details:", style: TextStyle(fontSize: 25.0, fontFamily: "Arial",color:Colors.black, height: 1.5),
                              textAlign: TextAlign.left,
                              overflow: TextOverflow.ellipsis),
                        ],
                      ),
                      Wrap(
                        spacing: 4.0,
                        runSpacing: 4.0,
                        alignment: WrapAlignment.start,
                        children: <Widget>[
                          Text(_appointment.note.toString(), style: TextStyle(fontSize: 15.0, fontFamily: "Arial",color:Colors.grey, height: 1.5 ),
                              textAlign: TextAlign.left,
//
                          ),
                        ],
                      ),
                      Row(
                        mainAxisAlignment: MainAxisAlignment.start,
                        children: <Widget>[
                          Text("Date/Time:", style: TextStyle(fontSize: 25.0, fontFamily: "Arial",color:Colors.black, height: 1.5),
                              textAlign: TextAlign.left,
                              overflow: TextOverflow.ellipsis),
                        ],
                      ),
                      Wrap(
                        spacing: 4.0,
                        runSpacing: 4.0,
                        alignment: WrapAlignment.start,
                        children: <Widget>[
                          Text(_appointment.date.toString(), style: TextStyle(fontSize: 15.0, fontFamily: "Arial",color:Colors.grey, height: 1.5 ),
                              textAlign: TextAlign.left,
                              ),
                        ],
                      ),
                      Row(
                        mainAxisAlignment: MainAxisAlignment.start,
                        children: <Widget>[
                          Text("Notes:", style: TextStyle(fontSize: 25.0, fontFamily: "Arial",color:Colors.black, height: 1.5),
                              textAlign: TextAlign.left,
                              overflow: TextOverflow.ellipsis),
                        ],
                      ),
                      Wrap(
                        spacing: 4.0,
                        runSpacing: 4.0,
                        alignment: WrapAlignment.start,
                        children: <Widget>[
                          Text(_appointment.note.toString(), style: TextStyle(fontSize: 15.0, fontFamily: "Arial",color:Colors.grey, height: 1.5 ),
                              textAlign: TextAlign.left,
                              ),
                        ],
                      ),
                      Row(
                        mainAxisAlignment: MainAxisAlignment.start,
                        children: <Widget>[
                          Text("Location:", style: TextStyle(fontSize: 25.0, fontFamily: "Arial",color:Colors.black, height: 1.5),
                              textAlign: TextAlign.left,
                              overflow: TextOverflow.ellipsis),
                        ],
                      ),
                      Wrap(
                        spacing: 4.0,
                        runSpacing: 4.0,
                        alignment: WrapAlignment.start,
                        children: <Widget>[
                          Text(_appointment.note.toString(), style: TextStyle(fontSize: 15.0, fontFamily: "Arial",color:Colors.grey, height: 1.5 ),
                              textAlign: TextAlign.left,
                              ),
                        ],
                      ),
                      Row(
                        mainAxisSize: MainAxisSize.min,
                        mainAxisAlignment: MainAxisAlignment.start,
                        children: <Widget>[
                          Text("Forms", style: TextStyle(fontSize: 25.0, fontFamily: "Arial",color:Colors.black ),),
                        ],
                      ),
                      Row(
                        mainAxisSize: MainAxisSize.min,
                        mainAxisAlignment: MainAxisAlignment.start,
                        children: <Widget>[
                          Text("pdflink", style: TextStyle(fontSize: 15.0, fontFamily: "Arial",color:Colors.grey, height:1.5 ),),
                        ],
                      ),
                      TextField(
//                        autofocus: true,
                        decoration: InputDecoration(
//                            labelText: "Note",
                            hintText: "Add your personal note here..",
                        ),
                      ),
                      ],
                    ),

              margin: const EdgeInsets.all(10.0),
              padding: const EdgeInsets.all(10.0),
            )

    );

  }
}
