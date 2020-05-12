import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'package:frontend/components/resource.dart';
import 'package:frontend/util/url_launch_wrapper.dart';
import 'package:url_launcher/url_launcher.dart';

class resourcedetail extends StatefulWidget {

  final Resource _resource;
  const resourcedetail(this._resource);
  @override
  _resourcedetailState createState() => _resourcedetailState(_resource);
}

class _resourcedetailState extends State<resourcedetail>
{
  var _resourceState;

  _resourcedetailState(this._resourceState);

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      //extendBodyBehindAppBaorder: true,
      appBar: PreferredSize(
          preferredSize: Size.fromHeight(60.0),
          child: AppBar(
            leading: BackButton(color: Colors.black),
            title: Text("Resource Details", style: TextStyle(color: Colors.black)),
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
                        Icon(Icons.book,color: Colors.green, size: 45,),
                        Text(_resourceState.name,
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
                          0: FixedColumnWidth(140.0),
                          1: FixedColumnWidth(300.0),
                        },
                        children: [
                          //url
                          TableRow(
                              children: [
                                ListTile(
                                  title: Text("",
                                      style: TextStyle(fontSize: 25.0, fontFamily: "Arial", fontWeight: FontWeight.bold)),

                                ),
                                ListTile(
                                    title: Text(_resourceState.website,
                                        style: TextStyle(fontSize: 25.0, fontFamily: "Arial")),
                                   // trailing: Icon(Icons.location_on),
                                    onTap: () {
                                      launchURL("https:"+_resourceState.website);
                                    }
                                )

                              ]
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
