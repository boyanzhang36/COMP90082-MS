import 'package:flutter/material.dart';
import 'package:frontend/login.dart';
import 'package:frontend/appointments.dart';
import 'package:frontend/screens/doctors.dart';
import 'package:frontend/screens/dashboard.dart';
import 'package:frontend/util/authentication.dart';


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
        '/doctors': (context) => Doctors()
      }
    );
  }
}

class MainPage extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    Future<String> currentToken = Authentication.getCurrentToken();
    if(currentToken ==null){
      Authentication.logout();
      return LoginPage();
    }
    else{
      return DashBoard();
    }
  }

}