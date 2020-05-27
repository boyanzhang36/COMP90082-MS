import 'package:flutter/cupertino.dart';
import 'package:flutter/foundation.dart';
import 'package:frontend/components/appointment.dart';
import 'package:flutter/material.dart';
import 'package:frontend/screens/appointmentfile.dart';
import 'package:frontend/util/authentication.dart';
import 'package:frontend/util/serverDetails.dart';
import 'package:http/http.dart' as http;
import 'dart:convert';
import 'package:shared_preferences/shared_preferences.dart';
import 'package:share/share.dart';

import 'dart:async';
import 'dart:io';

import 'package:flutter/foundation.dart';
import 'package:flutter/material.dart';
import 'package:flutter_full_pdf_viewer/full_pdf_viewer_scaffold.dart';
import 'package:path_provider/path_provider.dart';

//flutter_full_pdf_viewer Source: https://pub.dev/packages/flutter_full_pdf_viewer#-example-tab-

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
  var pdfTitle;
  var pdfLink;
  String pathPDF = "";
  var _file;
  Animation<double> animation;
  AnimationController controller;

  initState() {
    super.initState();

    saveController = TextEditingController();
    getAppointmentDetails();
    if (_appointmentState1.id.toString() != null)
      {
        pdfTitle = "Click this link to see the form";
//        getPdfLink();
      }
    else{
      pdfTitle = "No file at present";
    }

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

  Future<File> getPdfLink() async{
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
          'file/' +
          _appointmentState1.pid.toString();
      print(url);
      Map<String, String> headers = {"Authorization": auth};
      final filename = url.substring(url.lastIndexOf("/") + 1);
      var request = await HttpClient().getUrl(Uri.parse(url));
      request.headers.add("Authorization", auth);
      var response = await request.close();
//      var response = await http.get(url, headers: headers);
//      print(response.body);
      print(response.statusCode);
      if (response.statusCode == 200) {
        var bytes = await consolidateHttpClientResponseBytes(response);
        String dir = (await getApplicationDocumentsDirectory()).path;
        File file = new File('$dir/$filename');
        if (file != null) {
          await file.writeAsBytes(bytes);
          return file;
        }
      } else {
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
          centerTitle: true,
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
      Container(

        child: (_appointmentState is Appointment) ? _detailListView() : null,
        margin: const EdgeInsets.all(8.0),
        padding: const EdgeInsets.all(8.0),
        decoration: new BoxDecoration(
          color: Color.fromARGB(255, 196, 218, 234),
          borderRadius: BorderRadius.all(Radius.circular(4.0)),
          ),
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
              Container(child:
                _appointmentState1.pid != null?
                GestureDetector(
                  child: Text(pdfTitle.toString(), style: TextStyle(fontSize: 20.0, fontFamily: "Arial", decoration: TextDecoration.underline,color:Colors.grey, height:1.5 ),),
                  onTap: (){
                    getPdfLink().then((f) {
                      setState(() {
                        pathPDF = f.path;
                        print(pathPDF);
                      });
                    });
                    Navigator.push(
                        context,
                        MaterialPageRoute(
                            builder: (context) =>
                                appointmentfile(pathPDF)));
                  },
                )
                : Text(pdfTitle.toString(), style: TextStyle(fontSize: 20.0, fontFamily: "Arial",color:Colors.grey, height:1.5 ),),
                ),

//              Text("pdflink", style: TextStyle(fontSize: 20.0, fontFamily: "Arial",color:Colors.grey, height:1.5 ),),
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
                            hintText: "Add your personal note here...",
                          ),
                        ),
                        RaisedButton(
                          textColor: Colors.white,
                          color: Color.fromARGB(255, 135, 193, 218),
                          onPressed: _save,
                          child: Text('SAVE'),
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
                          mainAxisAlignment: MainAxisAlignment.spaceEvenly,
                          children: <Widget>[
                            RaisedButton(
                                textColor: Colors.white,
                                color: Color.fromARGB(255, 135, 193, 218),
                                onPressed: _edit,
                                child: Text('EDIT')

                            ),
                            RaisedButton(
                                textColor: Colors.white,
                                color: Color.fromARGB(255, 135, 193, 218),
                                onPressed: _delete,
                                child: Text('DELETE')
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
                            hintText: "Add your personal note here...",
                          ),
                        ),
                        Row(
                          mainAxisAlignment: MainAxisAlignment.spaceEvenly,
                          children: <Widget>[
                          RaisedButton(
                            textColor: Colors.white,
                            color: Color.fromARGB(255, 135, 193, 218),
                            onPressed: _save,
                            child: Text('SAVE'),
                          ),
                          RaisedButton(
                            textColor: Colors.white,
                            color: Color.fromARGB(255, 135, 193, 218),
                            onPressed: _cancel,
                            child: Text('CANCEL'),
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
                  Row(
                    mainAxisAlignment: MainAxisAlignment.spaceBetween,
                    children: <Widget>[
                      Container(
                          color: Colors.white,
                          child: Text(_appointmentState.status.toString(),
                        style: TextStyle(fontSize: 20.0, fontFamily: "Arial",color:Colors.grey, height: 1.5 ),
                        textAlign: TextAlign.left,)),

                      RaisedButton(
                          textColor: Colors.white,
                          color: Color.fromARGB(255, 135, 193, 218),
                          onPressed: _confirm,
                          child: Text('CONFIRM')
                      ),
                    ],)

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
              content: Text("Can't save empty user note!"),

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