import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'package:frontend/components/hospital.dart';
import 'package:frontend/util/url_launch_wrapper.dart';
import 'package:url_launcher/url_launcher.dart';

class hospitaldetail extends StatefulWidget {

  final Hospital _hospital;
  const hospitaldetail(this._hospital);
  @override
  _hospitaldetailState createState() => _hospitaldetailState(_hospital);
}

class _hospitaldetailState extends State<hospitaldetail>
{
  var _hospitalState;

  _hospitaldetailState(this._hospitalState);

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      //extendBodyBehindAppBaorder: true,
      appBar: PreferredSize(
          preferredSize: Size.fromHeight(60.0),
          child: AppBar(
            leading: BackButton(color: Colors.black),
            centerTitle: true,
            title: Text("Hospital Details", style: TextStyle(color: Colors.black)),
            backgroundColor: Colors.white,
            brightness: Brightness.light,
//            backgroundColor: Colors.transparent,
            elevation: 0.5,
          )
      ),
      body:   new Builder(
          builder:(BuildContext context){
            return new Column(
                children:<Widget>[
                  Container(
                    height: 80.0,
                    child: Center(child: Wrap(
                      spacing: 4.0,
                      runSpacing: 4.0,
                      alignment: WrapAlignment.start,
                      children: <Widget>[
                        Icon(Icons.local_hospital,color: Colors.green, size: 45,),
                        Text(_hospitalState.name,
                          style: TextStyle(fontSize: 25.0, fontFamily: "Arial",color:Colors.black, height: 1.5 ),
                        ),
                      ],
                    ))
                  ),
                  Container(
                    margin: const EdgeInsets.all(8.0),
                    padding: const EdgeInsets.all(8.0),
                    decoration: new BoxDecoration(
                      color: Color.fromARGB(255, 196, 218, 234),
                      borderRadius: BorderRadius.all(Radius.circular(4.0)),),
                    child: Table(
                      columnWidths: const {
                        0: FixedColumnWidth(140.0),
                        1: FixedColumnWidth(225.0),
                      },
                      children: [
                        //address
                        TableRow(
                            children: [
                              ListTile(
                                title: Text("Address:",
                                    style: TextStyle(fontSize: 25.0, fontFamily: "Arial", fontWeight: FontWeight.bold)),

                              ),
                              ListTile(
                                  title: Text(_hospitalState.address,
                                      style: TextStyle(fontSize: 25.0, fontFamily: "Arial")),
                                  trailing: Icon(Icons.location_on),
                                  onTap: () {
                                    launchURL("https://www.google.com/maps/search/"+_hospitalState.address);
                                  }
                              )
                            ]
                        ),
                        //phone
                        TableRow(
                            children: [
                              ListTile(
                                title: Text("Phone:",
                                    style: TextStyle(fontSize: 25.0, fontFamily: "Arial", fontWeight: FontWeight.bold)),

                              ),
                              ListTile(
                                  title: Text(_hospitalState.contact,
                                      style: TextStyle(fontSize: 25.0, fontFamily: "Arial")),
                                  trailing: Icon(Icons.phone),
                                  onTap: () {
                                    _callPhone();
                                  }
                              )


                            ]
                        ),
                        //fax
                        TableRow(
                            children: [
                              ListTile(
                                title: Text("Fax:",
                                    style: TextStyle(fontSize: 25.0, fontFamily: "Arial", fontWeight: FontWeight.bold)),
                              ),
                              ListTile(
                                title: Text(_hospitalState.fax,
                                    style: TextStyle(fontSize: 25.0, fontFamily: "Arial")),
                              )
                            ]
                        ),
                        //website
                        TableRow(
                            children: [
                              ListTile(
                                title: Text("Website:",
                                    style: TextStyle(fontSize: 25.0, fontFamily: "Arial", fontWeight: FontWeight.bold)),
                              ),
                              ListTile(
                                  title: Text(_hospitalState.website,
                                      style: TextStyle(fontSize: 25.0, fontFamily: "Arial")),
                                  trailing: Icon(Icons.public),
                                  onTap: () {
                                    launchURL('https://'+_hospitalState.website);
                                  }
                              )


                            ]
                        ),
                        //type
                        TableRow(
                            children: [
                              ListTile(
                                title: Text("Type:",
                                    style: TextStyle(fontSize: 25.0, fontFamily: "Arial", fontWeight: FontWeight.bold)),
                              ),
                              ListTile(
                                title: Text(_hospitalState.type,
                                    style: TextStyle(fontSize: 25.0, fontFamily: "Arial")),
                              )
                            ]
                        ),

                      ],
                    ),
                  )
//                  new Expanded(
//                    child: ,
//                  ),
                ]);
          }),
    );
  }

  _callPhone() async {
    var url = 'tel:' + _hospitalState.contact;
    if (await canLaunch(url)) {
      await launch(url);
    } else {
      throw 'Could not launch $url';
    }
  }
}
