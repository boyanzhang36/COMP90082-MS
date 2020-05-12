import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'package:frontend/components/doctor.dart';

class doctordetail extends StatelessWidget{

  final Doctor _doctor;

  const doctordetail(this._doctor);

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      //extendBodyBehindAppBaorder: true,
      appBar: PreferredSize(
          preferredSize: Size.fromHeight(60.0),
          child: AppBar(
            leading: BackButton(color: Colors.black),
            title: Text("Doctors Details", style: TextStyle(color: Colors.black)),
            backgroundColor: Colors.transparent,
            elevation: 0,
          )
      ),
      body:   new Builder(
          builder:(BuildContext context){
            return new Column(
                children:<Widget>[
                  Container(
                      height: 80.0,
                      child: Wrap(
                        spacing: 4.0,
                        runSpacing: 4.0,
                        alignment: WrapAlignment.start,
                        children: <Widget>[
                          Icon(Icons.person,color: Colors.green, size: 45,),
                          Text("Dr. "+_doctor.name,
                            style: TextStyle(fontSize: 25.0, fontFamily: "Arial",color:Colors.black, height: 1.5 ),
                          ),
                        ],
                      ),
                  ),
                  new Expanded(
                        child: Container(

                          padding: const EdgeInsets.all(8.0),
                          color: Color.fromARGB(255, 196, 218, 234),
                              child: Table(
                                columnWidths: const {
                                  0: FixedColumnWidth(130.0),
                                  1: FixedColumnWidth(300.0),
                                  },
                                children: [
                                    TableRow(
                                      children: [
                                        SizedBox(
                                          height: 40.0,
                                          child:
                                          Text("ID:",
                                            style: TextStyle(fontSize: 25.0, fontFamily: "Arial", fontWeight: FontWeight.bold),),
                                        ),
                                          Text(_doctor.id,
                                          style: TextStyle(fontSize: 25.0, fontFamily: "Arial", ),
                                        )]
                                  ),
                                    TableRow(
                                      children: [
                                        SizedBox(
                                          height: 40.0,
                                          child: Text("Address:",
                                            style: TextStyle(fontSize: 25.0, fontFamily: "Arial", fontWeight: FontWeight.bold),),
                                        ),
                                        Text(_doctor.address,
                                          style: TextStyle(fontSize: 25.0, fontFamily: "Arial", ),
                                        )
                                      ]
                                  ),
                                    TableRow(
                                      children: [
                                        SizedBox(
                                          height: 40.0,
                                          child: Text("Contact:",
                                            style: TextStyle(fontSize: 25.0, fontFamily: "Arial", fontWeight: FontWeight.bold),),
                                        ),
                                        Text(_doctor.contact,
                                          style: TextStyle(fontSize: 25.0, fontFamily: "Arial", ),
                                        )]
                                  ),
                                    TableRow(
                                      children: [
                                        SizedBox(
                                          height: 40.0,
                                          child: Text("Email:",
                                            style: TextStyle(fontSize: 25.0, fontFamily: "Arial", fontWeight: FontWeight.bold),),
                                        ),
                                        Text(_doctor.email,
                                          style: TextStyle(fontSize: 25.0, fontFamily: "Arial", ),
                                        )
                                      ]
                                  ),
                                    TableRow(
                                      children: [
                                        SizedBox(
                                          height: 40.0,
                                          child: Text("Expertise:",
                                            style: TextStyle(fontSize: 25.0, fontFamily: "Arial", fontWeight: FontWeight.bold),),
                                        ),
                                        Text(_doctor.expertise,
                                          style: TextStyle(fontSize: 25.0, fontFamily: "Arial", ),
                                        )]
                                  ),

                          ],
                        ),
                  ),
                  ),
                ]);
          }),
    );
  }


}