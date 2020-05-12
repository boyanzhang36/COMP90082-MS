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
                      height: 90.0,
                      child: _buildCard(_resourceState.name, _resourceState.website, Color.fromARGB(255, 196, 218, 234))
                  ),
                ]);
          }),
    );
  }

  Widget _buildCard(String name, String url, Color backgroundColor) {
    return Padding(
      padding: const EdgeInsets.all(8.0),
      child: Card(
        shape: RoundedRectangleBorder(
          borderRadius: BorderRadius.circular(1.0),
        ),

        elevation: 4.0,
        color: backgroundColor,
        child: InkWell(
          child: Column(
            mainAxisAlignment: MainAxisAlignment.spaceEvenly,
            crossAxisAlignment: CrossAxisAlignment.stretch,
            children: <Widget>[
              //Icon(Icons.bookmark),
              Text(name, style: TextStyle(fontSize: 25.0, fontFamily: "Arial", fontWeight: FontWeight.bold),),
              Text(url, style: TextStyle(fontSize: 15.0),),
            ],
          ),
        ),
      ),
    );
  }

}
