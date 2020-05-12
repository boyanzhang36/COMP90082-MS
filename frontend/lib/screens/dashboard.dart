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
      //extendBodyBehindAppBaorder: true,
      appBar: PreferredSize(
          preferredSize: Size.fromHeight(1.0),
          child: AppBar(
            //backgroundColor: Colors.transparent,
            backgroundColor: Color(0x44000000),
            elevation: 0,
            //title: Text("Medical secretary", style: TextStyle(color: Colors.white)),
            // actions: <Widget>[
            //  FlatButton(
            //   onPressed: () {
            //    Authentication.logout();
            //      Navigator.of(context).pushAndRemoveUntil(MaterialPageRoute(
            //          builder: (BuildContext context) => LoginPage()), (
            //          Route<dynamic> route) => false);
            //    },
            //    child: Text("Log Out", style: TextStyle(color: Colors.white)),
            //  ),
            //],
          )
      ),
      body:   new Builder(
          builder:(BuildContext context){
            return new Column(
                children:<Widget>[
                  Container(
                      height: 90.0,
                      child: _buildBar("APPOINTMENT", Icons.date_range, Colors.lightBlue[400], onTap: () {
                        Navigator.pushNamed(context, '/appointments');//icon used to be event
                      })
                  ),
                  new Expanded(
                      child: GridView.count(
                        //shrinkWrap: true,
                        //padding: const EdgeInsets.all(8),
                        //crossAxisSpacing: 1,
                        //mainAxisSpacing: 5,
                        crossAxisCount: 2,
                        //gridDelegate:
                        //SliverGridDelegateWithFixedCrossAxisCount(crossAxisCount: 2),
                        children: <Widget>[
                          _buildCard("DOCTORS", Icons.people, Colors.blueGrey, onTap: () {
                            Navigator.pushNamed(context, '/doctors');
                          }),
                          _buildCard("HOSPITALS", Icons.local_hospital, Colors.tealAccent[700], onTap: () {
                            Navigator.pushNamed(context, '/hospitals');
                          }),
                          _buildCard("RADIOLOGY", Icons.healing, Colors.lightBlue[400], onTap: () {
                            Navigator.of(context).pushNamed("/radiologies");
                          }),
                          _buildCard("PATHOLOGY", Icons.loupe, Colors.blueGrey, onTap: () {
                            Navigator.of(context).pushNamed("/pathologies");//indigo[400]
                          }),
                          //_buildCard("PATHOLOGY", FontAwesomeIcons.ambulance, Colors.teal, onTap: (){Navigator.of(context).pushNamed("ambulance");}),

                          //          _buildCard("Medical History", Icons.library_books, Colors.deepPurpleAccent, onTap: (){Navigator.of(context).pushNamed("history");}),
                          //          _buildCard("Ask A doctor", Icons.chat, Colors.indigo, onTap: (){Navigator.of(context).pushNamed("support");}),
                        ],
                      )),
                  Container(
                      height: 90.0,
                      //padding: EdgeInsets.symmetric(horizontal: 30.0),
                      //margin: EdgeInsets.only(top: 15.0),
                      child: _buildBar("RESOURCE", Icons.book, Colors.tealAccent[700], onTap: () {
                        Navigator.of(context).pushNamed("resource");
                      })
                  ),
                  Container(
                      height: 70.0,
                      child:_buildLogout("LOG OUT", Icons.book, Colors.transparent, onTap: () {
                        Authentication.logout();
                        Navigator.of(context).pushAndRemoveUntil(MaterialPageRoute(
                            builder: (BuildContext context) => LoginPage()), (
                            Route<dynamic> route) => false);;
                      })
                  ),
                ]);
          }),
      //bottomNavigationBar: PreferredSize(
      //preferredSize: Size.fromHeight(100.0),
      //child:BottomAppBar(
      //color: Color(0x44000000),
      //elevation: 0,
      //child: Text("Log Out", style: TextStyle(color: Colors.white))
      //child: bottomAppBarContents,
      //)
      //),
    );
  }

  //Build Vertical Card
  Widget _buildCard(String title, IconData icon, Color backgroundColor,
      {GestureTapCallback onTap}) {
    return Padding(
      padding: const EdgeInsets.all(8.0),
      child: Card(
        shape: RoundedRectangleBorder(
          borderRadius: BorderRadius.circular(15.0),
        ),
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

  //Build Horizontal Bar
  Widget _buildBar(String title, IconData icon, Color backgroundColor,
      {GestureTapCallback onTap}) {
    return Padding(
      padding: const EdgeInsets.all(8.0),
      child: Card(
        shape: RoundedRectangleBorder(
          borderRadius: BorderRadius.circular(15.0),
        ),
        elevation: 4.0,
        color: backgroundColor,
        child: InkWell(
          onTap: onTap,
          child: Row(
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

  //Build Logout Bar
  Widget _buildLogout(String title, IconData icon, Color backgroundColor,
      {GestureTapCallback onTap}) {
    return Padding(
      padding: const EdgeInsets.all(6.0),
      child: Card(
        shape: RoundedRectangleBorder(
          borderRadius: BorderRadius.circular(15.0),
          side: new BorderSide(color: Colors.indigo[400], width: 2.0),
        ),
        elevation: 0,
        color: backgroundColor,
        child: InkWell(
          onTap: onTap,
          child: Row(
            mainAxisAlignment: MainAxisAlignment.spaceEvenly,
            children: <Widget>[
              Text(title, style: TextStyle(fontSize: 20.0,
                color: Colors.indigo[400],
                fontWeight: FontWeight.bold,),),
              // foreground: Paint()
              // ..style = PaintingStyle.stroke
              //..strokeWidth = 6
              //..color = Colors.indigo,
            ],
          ),
        ),
      ),
    );
  }
}