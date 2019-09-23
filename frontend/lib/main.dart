import 'package:flutter/material.dart';
import 'package:frontend/login.dart';
import 'package:frontend/appointments.dart';
import 'package:shared_preferences/shared_preferences.dart';

void main() => runApp(MyApp());

class MyApp extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      title: "Medical heal app",
      debugShowCheckedModeBanner: false,
      home: new MainPage(),
      theme: ThemeData(
        accentColor: Colors.white70
      ),
      routes: {

      }
    );
  }
}

class MainPage extends StatefulWidget {
  @override
  _MainPageState createState() => _MainPageState();
}

class _MainPageState extends State<MainPage> {

  SharedPreferences sharedPreferences;

  @override
  void initState() {
    super.initState();
    checkLoginStatus();
  }

  checkLoginStatus() async {
    sharedPreferences = await SharedPreferences.getInstance();
    if(sharedPreferences.getString("token") != null
        && sharedPreferences.getString("token_expire_date")!= null
        && DateTime.parse(sharedPreferences.getString("token_expire_date")).isAfter(DateTime.now())
    ){
        //Success!!
    }
    //Go back to login page
    else {
      Navigator.of(context).pushAndRemoveUntil(MaterialPageRoute(builder: (BuildContext context) => LoginPage()), (Route<dynamic> route) => false);
    }
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Text("Medical secretary", style: TextStyle(color: Colors.white)),
        actions: <Widget>[
          FlatButton(
            onPressed: () {
              sharedPreferences.clear();
              Navigator.of(context).pushAndRemoveUntil(MaterialPageRoute(builder: (BuildContext context) => LoginPage()), (Route<dynamic> route) => false);
            },
            child: Text("Log Out", style: TextStyle(color: Colors.white)),
          ),
        ],
      ),
      body: GridView(
        gridDelegate:
        SliverGridDelegateWithFixedCrossAxisCount(crossAxisCount: 2),
        children: <Widget>[
          _buildCard("APPOINTMENT", Icons.event, Colors.teal, onTap: (){Navigator.of(context).push(MaterialPageRoute(builder: (context) => AppointmentPage()));}),
          _buildCard("DOCTORS", Icons.people, Colors.lightBlue, onTap: (){}),
          _buildCard("HOSPITALS", Icons.local_hospital, Colors.lightBlue, onTap: (){Navigator.of(context).pushNamed("home");}),
          _buildCard("RADIOLOGY", Icons.healing, Colors.teal, onTap: (){Navigator.of(context).pushNamed("alerts");}),
    //_buildCard("PATHOLOGY", FontAwesomeIcons.ambulance, Colors.teal, onTap: (){Navigator.of(context).pushNamed("ambulance");}),
          _buildCard("RESOURCE", Icons.book, Colors.lightBlue, onTap: (){Navigator.of(context).pushNamed("donate");}),
//          _buildCard("Medical History", Icons.library_books, Colors.deepPurpleAccent, onTap: (){Navigator.of(context).pushNamed("history");}),
//          _buildCard("Ask A doctor", Icons.chat, Colors.indigo, onTap: (){Navigator.of(context).pushNamed("support");}),
        ],
      ),
    );
  }


  Widget _buildCard(String title, IconData icon, Color backgroundColor, {GestureTapCallback onTap}) {

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
              Text(title, style: TextStyle(fontSize: 16.0, color: Colors.white, fontWeight: FontWeight.bold),),
            ],
          ),
        ),
      ),
    );
  }
}