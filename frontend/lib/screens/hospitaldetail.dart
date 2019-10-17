import 'package:frontend/components/hospital.dart';
import 'package:flutter/material.dart';
import 'package:frontend/util/url_launch_wrapper.dart';

class HospitalDetail extends StatelessWidget {
  final Hospital _hospital;

  const HospitalDetail(this._hospital);

  @override
  Widget build(BuildContext context) {

    return Scaffold(
        appBar: AppBar(title: Text(_hospital.name)),
        body: ListView(
            children: <Widget>[ Container(
                decoration: BoxDecoration(
                  border: Border.all(width: 0.8),
                  borderRadius: BorderRadius.circular(12.0),
                ),
                margin:
                const EdgeInsets.symmetric(horizontal: 8.0, vertical: 4.0),
                child: ListTile(
                  title: Text("Address: \n" + _hospital.address),
                  trailing: Icon(Icons.arrow_right),
                  onTap: () {
                    launchURL("https://www.google.com/maps/search/"+_hospital.address);
                  }
                )
            ),
              Container(
                  decoration: BoxDecoration(
                    border: Border.all(width: 0.8),
                    borderRadius: BorderRadius.circular(12.0),
                  ),
                  margin:
                  const EdgeInsets.symmetric(horizontal: 8.0, vertical: 4.0),
                  child: ListTile(
                      title: Text("Website: \n" +_hospital.website),
                      trailing: Icon(Icons.arrow_right),
                      onTap: () {
                         launchURL(_hospital.website);
                      }
                  )
              ),
              Container(
                  decoration: BoxDecoration(
                    border: Border.all(width: 0.8),
                    borderRadius: BorderRadius.circular(12.0),
                  ),
                  margin:
                  const EdgeInsets.symmetric(horizontal: 8.0, vertical: 4.0),
                  child: ListTile(
                      title: Text("Contact: \n" +_hospital.contact),
                      trailing: Icon(Icons.arrow_right),
                      onTap: () {
                        Navigator.push(
                            context,
                            MaterialPageRoute(
                                builder: (context) =>
                                    Text(_hospital.contact)));
                      }
                  )
              ),
              Container(
                  decoration: BoxDecoration(
                    border: Border.all(width: 0.8),
                    borderRadius: BorderRadius.circular(12.0),
                  ),
                  margin:
                  const EdgeInsets.symmetric(horizontal: 8.0, vertical: 4.0),
                  child: ListTile(
                      title: Text("Type: \n" + _hospital.type),
                      trailing: Icon(Icons.arrow_right),
                  )
              ),
              Container(
                  decoration: BoxDecoration(
                    border: Border.all(width: 0.8),
                    borderRadius: BorderRadius.circular(12.0),
                  ),
                  margin:
                  const EdgeInsets.symmetric(horizontal: 8.0, vertical: 4.0),
                  child: ListTile(
                      title: Text("Fax: \n" +_hospital.fax),
                      trailing: Icon(Icons.arrow_right),
                      onTap: () {}
                  )
              )
          ]
        )
    );
  }

}