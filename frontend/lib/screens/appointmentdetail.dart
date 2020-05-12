import 'package:flutter/cupertino.dart';
import 'package:frontend/components/appointment.dart';
import 'package:flutter/material.dart';
import 'package:frontend/util/authentication.dart';
import 'package:frontend/util/serverDetails.dart';
import 'package:http/http.dart' as http;
import 'dart:convert';
import 'package:shared_preferences/shared_preferences.dart';
import 'package:share/share.dart';

class AppointmentDetail extends StatefulWidget {

  final Appointment _appointment;
  const AppointmentDetail(this._appointment);

  @override
  _AppointmentDetailState createState() => _AppointmentDetailState(_appointment);
}

class _AppointmentDetailState extends State<AppointmentDetail>  with SingleTickerProviderStateMixin {
  final _appointmentState1;
  _AppointmentDetailState(this._appointmentState1);
  TextEditingController saveController;
  Appointment _appointmentState;
  var flag = false;
  var sendmsg;
  Animation<double> animation;
  AnimationController controller;

  initState() {
    super.initState();

    saveController = TextEditingController();
    getAppointmentDetails();
    controller = new AnimationController(
        duration: const Duration(milliseconds: 400), vsync: this);

    controller.forward();
  }

  dispose() {
    controller.dispose();
    super.dispose();
  }

  getAppointmentDetails() async {
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
          'appointments/'+
          _appointmentState1.id.toString();
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
            _appointmentState = Appointment.fromJson(jsonResponse);
            print("TEMP" + _appointmentState.date.toString());
            sendmsg = "Appointment Details" + "\n" + "\n" + "\n" +
                "Appts: "  + _appointmentState.title.toString() + "\n" +
                "Day/Time: " + _appointmentState.date.toString() + "\n" + "\n"
                +"\n" + "\n" + "From Medical Secretary App";
          });
        }
      } else {
        print(response.body);
      }
    }
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: PreferredSize(
        preferredSize: Size.fromHeight(60.0),
        child: AppBar(
          leading: BackButton(color: Colors.black),
//            IconButton(icon: Icon(Icons.arrow_back),
//            color: Colors.black,
//            onPressed: (){
//              Navigator.push(
//                  context,
//                  MaterialPageRoute(
//                      builder: (context) => Appointments()));
//            },
//  ),
          title: Text("Appointment Details", style: TextStyle(color: Colors.black)),
          backgroundColor: Colors.white,
          brightness: Brightness.light,
//          backgroundColor: Color(0x44000000),
          elevation: 0.5,
          actions: <Widget>[
            IconButton(
                color: Colors.black,
                icon: Icon(Icons.share), onPressed: () {
              if (sendmsg != null){
                DateTime now = new DateTime.now();
                Share.share(sendmsg, subject: 'Appointment details send on ' + "$now");
              }

            })
          ],
        )
    ),
      body:
      Container( color: Color.fromARGB(255, 196, 218, 234),
        child: (_appointmentState is Appointment) ? _detailListView() : null,
        margin: const EdgeInsets.all(5.0),
        padding: const EdgeInsets.all(5.0),
      ),
//        bottomNavigationBar:,
//        bottomSheet:  _buttonBar(),
//        Column(
//          children: <Widget>[
//
////            Expanded(child: _buttonView())
//          ],
//        )
    );
  }

  Widget _detailListView(){
    return ListView(
        shrinkWrap: true,
        padding: const EdgeInsets.fromLTRB(5.0, 5.0, 5.0, 5.0),
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
              Text(_appointmentState.title.toString(), style: TextStyle(fontSize: 25.0, fontFamily: "Arial",color:Colors.black, height: 1.5 ),
                textAlign: TextAlign.left,
//                              overflow: TextOverflow.ellipsis
              ),
            ],
          ),
          Row(
            mainAxisAlignment: MainAxisAlignment.start,
            children: <Widget>[
              Text("Details:", style: TextStyle(fontSize: 20.0, fontFamily: "Arial",color:Colors.black, height: 1.5),
                  textAlign: TextAlign.left,
                  overflow: TextOverflow.ellipsis),
            ],
          ),
          Wrap(
            spacing: 4.0,
            runSpacing: 4.0,
            alignment: WrapAlignment.start,
            children: <Widget>[
              Text(_appointmentState.note.toString(), style: TextStyle(fontSize: 20.0, fontFamily: "Arial",color:Colors.grey, height: 1.5 ),
                textAlign: TextAlign.left,
//
              ),
            ],
          ),
          Row(
            mainAxisAlignment: MainAxisAlignment.start,
            children: <Widget>[
              Text("Day/Time:", style: TextStyle(fontSize: 20.0, fontFamily: "Arial",color:Colors.black, height: 1.5),
                  textAlign: TextAlign.left,
                  overflow: TextOverflow.ellipsis),
            ],
          ),
          Wrap(
            spacing: 4.0,
            runSpacing: 4.0,
            alignment: WrapAlignment.start,
            children: <Widget>[
              Text(_appointmentState.date.toString(), style: TextStyle(fontSize: 20.0, fontFamily: "Arial",color:Colors.grey, height: 1.5 ),
                textAlign: TextAlign.left,
              ),
            ],
          ),
          Row(
            mainAxisAlignment: MainAxisAlignment.start,
            children: <Widget>[
              Text("Notes:", style: TextStyle(fontSize: 20.0, fontFamily: "Arial",color:Colors.black, height: 1.5),
                  textAlign: TextAlign.left,
                  overflow: TextOverflow.ellipsis),
            ],
          ),
          Wrap(
            spacing: 4.0,
            runSpacing: 4.0,
            alignment: WrapAlignment.start,
            children: <Widget>[
              Text(_appointmentState.note.toString(), style: TextStyle(fontSize: 20.0, fontFamily: "Arial",color:Colors.grey, height: 1.5 ),
                textAlign: TextAlign.left,
              ),
            ],
          ),
          Row(
            mainAxisAlignment: MainAxisAlignment.start,
            children: <Widget>[
              Text("Location:", style: TextStyle(fontSize: 20.0, fontFamily: "Arial",color:Colors.black, height: 1.5),
                  textAlign: TextAlign.left,
                  overflow: TextOverflow.ellipsis),
            ],
          ),
          Wrap(
            spacing: 4.0,
            runSpacing: 4.0,
            alignment: WrapAlignment.start,
            children: <Widget>[
              Text(_appointmentState.note.toString(), style: TextStyle(fontSize: 20.0, fontFamily: "Arial",color:Colors.grey, height: 1.5 ),
                textAlign: TextAlign.left,
              ),
            ],
          ),
          Row(
            mainAxisSize: MainAxisSize.min,
            mainAxisAlignment: MainAxisAlignment.start,
            children: <Widget>[
              Text("Forms:", style: TextStyle(fontSize: 20.0, fontFamily: "Arial",color:Colors.black , height:1.5),),
            ],
          ),
          Row(
            mainAxisSize: MainAxisSize.min,
            mainAxisAlignment: MainAxisAlignment.start,
            children: <Widget>[
              Text("pdflink", style: TextStyle(fontSize: 20.0, fontFamily: "Arial",color:Colors.grey, height:1.5 ),),
            ],
          ),
          Row(
            mainAxisSize: MainAxisSize.min,
            mainAxisAlignment: MainAxisAlignment.start,
            children: <Widget>[
              Text("User Note:", style: TextStyle(fontSize: 20.0, fontFamily: "Arial",color:Colors.black, height:1.5 ),),
            ],
          ),
          Wrap(
            spacing: 4.0,
            runSpacing: 4.0,
            alignment: WrapAlignment.start,
            children: <Widget>[
              Container(
                  padding: EdgeInsets.all(20.0),
                  color: Colors.white,
                  child:
                  (_appointmentState.userNote == null) ?
                  Column(
                      children: <Widget>[
                        TextField(
//                        autofocus: true,
                          controller: saveController,
                          decoration: InputDecoration(
//                            labelText: "Note",
                            hintText: "Add your personal user note here...",
                          ),
                        ),
                        RaisedButton(
                          onPressed: _save,
                          child: Text('Save'),
                        )
                      ])
                      :
                  (!flag)?
                  Column(
                      children: <Widget>[

                        Text(_appointmentState.userNote.toString(),
                            style: TextStyle(fontSize: 20.0, fontFamily: "Arial",color:Colors.black, height:1.5 )
                        ),
                        Row(
                          children: <Widget>[
                            RaisedButton(
                                onPressed: _edit,
                                child: Text('Edit')

                            ),
                            RaisedButton(
                                onPressed: _delete,
                                child: Text('Delete')
                            )
                          ],
                        )

                      ]
                  )
                      :
                  Column(
                      children: <Widget>[
                        TextField(
//                        autofocus: true,
                          controller: saveController,
                          decoration: InputDecoration(
//                            labelText: "Note",
                            hintText: "Add your personal user note here...",
                          ),
                        ),
                        Row(children: <Widget>[
                          RaisedButton(
                            onPressed: _save,
                            child: Text('Save'),
                          ),
                          RaisedButton(
                            onPressed: _cancel,
                            child: Text('Cancel'),
                          )
                        ],)

                      ])


              ),

            ],
          ),
          Row(
            mainAxisAlignment: MainAxisAlignment.start,
            children: <Widget>[
              Text("Status:", style: TextStyle(fontSize: 20.0, fontFamily: "Arial",color:Colors.black, height: 1.5),
                  textAlign: TextAlign.left,
                  overflow: TextOverflow.ellipsis),
            ],
          ),
          Wrap(
            spacing: 4.0,
            runSpacing: 4.0,
            alignment: WrapAlignment.start,
            children: <Widget>[
              Container(
                  child:(_appointmentState.status.toString() == "CONFIRMED")?
                  Text(_appointmentState.status.toString(),
                    style: TextStyle(fontSize: 20.0, fontFamily: "Arial",color:Colors.grey, height: 1.5 ),
                    textAlign: TextAlign.left,)
                      :
                  Column(
                    children: <Widget>[
                      Row(children: <Widget>[
                        Text(_appointmentState.status.toString(),
                          style: TextStyle(fontSize: 20.0, fontFamily: "Arial",color:Colors.grey, height: 1.5 ),
                          textAlign: TextAlign.left,)
                      ],),
                      Row(
                        children: <Widget>[
                          RaisedButton(
                              onPressed: _confirm,
                              child: Text('Confirm')
                          ),

                        ],
                      )

                    ],
                  )

              )
            ],
          ),
        ]
    );
  }

  _save() async{
    if (saveController.text == ""){
      showDialog(
          context: context,
          builder: (context) => AlertDialog(
              title: Text("Error message"),
              content: Text("Can't save empty user note!")
          )
      );
    }
    else{
      SharedPreferences sharedPreferences = await SharedPreferences.getInstance();
      String currentToken = await Authentication.getCurrentToken();
      print(currentToken);
      if (currentToken == null) {
        print('bouncing');
        Authentication.bounceUser(context);
      }else {
        String auth = "Bearer " + currentToken;
        String url = ServerDetails.ip +
            ':' +
            ServerDetails.port +
            ServerDetails.api +
            'appointments/' +
            _appointmentState1.id.toString() +
            '/usernote';
        print(url);
        Map<String, String> headers =
        {
          "Content-type": "application/json",
          "Authorization": auth
        };
        var data = jsonEncode({
          "user_note": saveController.text
        });
        var jsonResponse = null;
        var response = await http.post(url, headers: headers, body: data);
        print(response.body);
        var messageToUser;
        if (response.statusCode == 200) {
          jsonResponse = json.decode(response.body);
          if (jsonResponse != null) {
            messageToUser = response.body;
            setState(() {
              flag = false;
              getAppointmentDetails();
            });
          }
        } else {
          messageToUser = response.body;
//        setState(() {
//
//        });
        }
      }
    }

  }

  _edit() {
    setState(() {
      saveController.text = _appointmentState.userNote.toString();
      flag = true;
    });
  }

  _delete() async{
    SharedPreferences sharedPreferences = await SharedPreferences.getInstance();
    String currentToken = await Authentication.getCurrentToken();
    print(currentToken);
    if (currentToken == null) {
      print('bouncing');
      Authentication.bounceUser(context);
    }else {
      String auth = "Bearer " + currentToken;
      String url = ServerDetails.ip +
          ':' +
          ServerDetails.port +
          ServerDetails.api +
          'appointments/' +
          _appointmentState.id.toString() +
          '/usernote';
      print(url);
      Map<String, String> headers =
      {
        "Content-type": "application/json",
        "Authorization": auth
      };
      var data = jsonEncode({
        "user_note": saveController.text
      });
      var jsonResponse = null;
      var response = await http.delete(url, headers: headers);
      print(response.body);
      var messageToUser;
      if (response.statusCode == 200) {
        jsonResponse = json.decode(response.body);
        if (jsonResponse != null) {
          messageToUser = response.body;
          setState(() {
            getAppointmentDetails();
            saveController.clear();
          });
        }
      } else {
        messageToUser = response.body;
//        setState(() {
//
//        });
      }
    }
  }

  _cancel() {
    setState(() {
      flag = false;
    });
  }

  _confirm() async{
    SharedPreferences sharedPreferences = await SharedPreferences.getInstance();
    String currentToken = await Authentication.getCurrentToken();
    print(currentToken);
    if (currentToken == null) {
      print('bouncing');
      Authentication.bounceUser(context);
    }else {
      String auth = "Bearer " + currentToken;
      String url = ServerDetails.ip +
          ':' +
          ServerDetails.port +
          ServerDetails.api +
          'appointments/' +
          _appointmentState1.id.toString() +
          '/confirm';
      print(url);
      Map<String, String> headers =
      {
        "Content-type": "application/json",
        "Authorization": auth
      };
      var jsonResponse = null;
      var response = await http.post(url, headers: headers);
      print(response.body);
      var messageToUser;
      if (response.statusCode == 200) {
        jsonResponse = json.decode(response.body);
        if (jsonResponse != null) {
          messageToUser = response.body;
          setState(() {
            showDialog(
                context: context,
                builder: (context) => AlertDialog(
                    title: Text("Success message"),
                    content: Text("Confirmed!")
                )
            );
            getAppointmentDetails();
          });
        }
      } else {
        messageToUser = response.body;
//        setState(() {
//
//        });
      }
    }
  }

//  _unconfirmed(){
//
//  }

//  _cancelled(){
//
//  }
}