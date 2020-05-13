import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'package:frontend/components/radiology.dart';
import 'package:frontend/util/url_launch_wrapper.dart';
import 'package:url_launcher/url_launcher.dart';

class radiologydetail extends StatefulWidget {

  final Radiology _radiology;
  const radiologydetail(this._radiology);
  @override
  _radiologydetailState createState() => _radiologydetailState(_radiology);
}

class _radiologydetailState extends State<radiologydetail>
{
  var _radiologyState;

  _radiologydetailState(this._radiologyState);

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      //extendBodyBehindAppBaorder: true,
      appBar: PreferredSize(
          preferredSize: Size.fromHeight(60.0),
          child: AppBar(
            leading: BackButton(color: Colors.black),
            centerTitle: true,
            title: Text("Radiology Details", style: TextStyle(color: Colors.black)),
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
                        Text(_radiologyState.name,
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
                                  title: Text(_radiologyState.address,
                                      style: TextStyle(fontSize: 25.0, fontFamily: "Arial")),
                                  trailing: Icon(Icons.location_on),
                                  onTap: () {
                                    launchURL("https://www.google.com/maps/search/"+_radiologyState.address);
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
                                  title: Text(_radiologyState.contact,
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
                                title: Text(_radiologyState.fax,
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
                                  title: Text(_radiologyState.website,
                                      style: TextStyle(fontSize: 25.0, fontFamily: "Arial")),
                                  trailing: Icon(Icons.public),
                                  onTap: () {
                                    launchURL('https://'+_radiologyState.website);
                                  }
                              )


                            ]
                        ),
                        //type


                      ],
                    ),
                  ),
//                  new Expanded(
//                    child:
//                  ),
                ]);
          }),
    );
  }

  _callPhone() async {
    var url = 'tel:' + _radiologyState.contact;
    if (await canLaunch(url)) {
      await launch(url);
    } else {
      throw 'Could not launch $url';
    }
  }
}
