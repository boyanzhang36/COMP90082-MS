import 'package:flutter/material.dart';
import 'package:frontend/screens/login.dart';
import 'package:frontend/screens/appointments.dart';
import 'package:frontend/screens/doctors.dart';
import 'package:shared_preferences/shared_preferences.dart';
import 'package:frontend/util/authentication.dart';

class DashBoard extends StatefulWidget {
  @override
  _DashBoardState createState() => _DashBoardState();
}

class _DashBoardState extends State<DashBoard> {

  SharedPreferences sharedPreferences;

  @override
  void initState() {
    super.initState();

  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Text("Medical secretary", style: TextStyle(color: Colors.white)),
        actions: <Widget>[
          FlatButton(
            onPressed: () {
              Authentication.logout();
              Navigator.of(context).pushAndRemoveUntil(MaterialPageRoute(
                  builder: (BuildContext context) => LoginPage()), (
                  Route<dynamic> route) => false);
            },
            child: Text("Log Out", style: TextStyle(color: Colors.white)),
          ),
        ],
      ),
      body: GridView(
        gridDelegate:
        SliverGridDelegateWithFixedCrossAxisCount(crossAxisCount: 2),
        children: <Widget>[
          _buildCard("APPOINTMENT", Icons.event, Colors.teal, onTap: () {
            Navigator.pushNamed(context, '/appointments');
          }),
          _buildCard("DOCTORS", Icons.people, Colors.lightBlue, onTap: () {
            Navigator.pushNamed(context, '/doctors');
          }),
          _buildCard(
              "HOSPITALS", Icons.local_hospital, Colors.lightBlue, onTap: () {
            Navigator.pushNamed(context, '/hospitals');
          }),
          _buildCard("RADIOLOGY", Icons.healing, Colors.teal, onTap: () {
            Navigator.of(context).pushNamed("alerts");
          }),
          //_buildCard("PATHOLOGY", FontAwesomeIcons.ambulance, Colors.teal, onTap: (){Navigator.of(context).pushNamed("ambulance");}),
          _buildCard("RESOURCE", Icons.book, Colors.lightBlue, onTap: () {
            Navigator.of(context).pushNamed("donate");
          }),
//          _buildCard("Medical History", Icons.library_books, Colors.deepPurpleAccent, onTap: (){Navigator.of(context).pushNamed("history");}),
//          _buildCard("Ask A doctor", Icons.chat, Colors.indigo, onTap: (){Navigator.of(context).pushNamed("support");}),
        ],
      ),
    );
  }


  Widget _buildCard(String title, IconData icon, Color backgroundColor,
      {GestureTapCallback onTap}) {
    return Padding(
      padding: const EdgeInsets.all(8.0),
      child: Card(
        elevation: 4.0,
        color: backgroundColor,
        child: InkWell(
          onTap: onTap,
          child: Column(
            mainAxisAlignment: MainAxisAlignment.spaceEvenly,
            children: <Widget>[
              Icon(icon, size: 50.0, color: Colors.white,),
              Text(title, style: TextStyle(fontSize: 16.0,
                  color: Colors.white,
                  fontWeight: FontWeight.bold),),
            ],
          ),
        ),
      ),
    );
  }
}