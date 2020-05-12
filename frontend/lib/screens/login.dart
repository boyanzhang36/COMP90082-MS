import 'dart:convert';
import 'package:flutter/material.dart';
import 'package:flutter/services.dart';
import 'package:http/http.dart' as http;
import 'package:frontend/main.dart';
import 'package:shared_preferences/shared_preferences.dart';
import 'package:frontend/util/serverDetails.dart';
import 'package:frontend/util/firebase.dart';

import 'package:url_launcher/url_launcher.dart';

class LoginPage extends StatefulWidget {
  @override
  _LoginPageState createState() => _LoginPageState();
}

class _LoginPageState extends State<LoginPage> {
  bool _isLoading = false;
  final TextEditingController emailController = new TextEditingController();
  final TextEditingController passwordController = new TextEditingController();

  final TextEditingController resetPasswordEmailPasswordController = new TextEditingController();
  final TextEditingController newPasswordController = new TextEditingController();
  final TextEditingController confirmPasswordController = new TextEditingController();

  @override
  Widget build(BuildContext context) {
    SystemChrome.setSystemUIOverlayStyle(SystemUiOverlayStyle.light
        .copyWith(statusBarColor: Colors.transparent));
    return Scaffold(
      body: Container(
        decoration: BoxDecoration(
          gradient: LinearGradient(
              colors: [Colors.blue, Colors.teal],
              begin: Alignment.topCenter,
              end: Alignment.bottomCenter),
        ),
        child: _isLoading
            ? Center(child: CircularProgressIndicator())
            : ListView(
                children: <Widget>[
                  headerSection(),
                  textSection(),
                  buttonSection(),
                ],
              ),
      ),
    );
  }

  signIn(String email, pass) async {
    SharedPreferences sharedPreferences = await SharedPreferences.getInstance();
    String url = ServerDetails.ip +
        ':' +
        ServerDetails.port +
        ServerDetails.api +
        'login';
    Map<String, String> headers = {"Content-type": "application/json"};
    var data = jsonEncode({
      'email': email,
      'password': pass,
      'token':
          FirebaseNotifications.fcmtoken
    });
    var jsonResponse = null;
    var response = await http.post(url, headers: headers, body: data);

    if (response.statusCode == 200) {
      jsonResponse = json.decode(response.body);
      if (jsonResponse != null) {
        setState(() {
          _isLoading = false;
        });
        sharedPreferences.setString("token", jsonResponse['token']);
        sharedPreferences.setString(
            "token_expire_date", jsonResponse['token_expire_date']);
        Navigator.of(context).pushAndRemoveUntil(
            MaterialPageRoute(builder: (BuildContext context) => MainPage()),
            (Route<dynamic> route) => false);
      }
    } else {
      setState(() {
        _isLoading = false;
      });
      print(response.headers);
      print(response.body);
    }
  }

  Column buttonSection() {
    return Column(children: <Widget>[
      Container(
        width: MediaQuery.of(context).size.width,
        height: 40.0,
        padding: EdgeInsets.symmetric(horizontal: 30.0),
        margin: EdgeInsets.only(top: 15.0),
        child: RaisedButton(
          onPressed: emailController.text == "" || passwordController.text == ""
              ? null
              : () {
                  setState(() {
                    _isLoading = true;
                  });
                  signIn(emailController.text, passwordController.text);
                },
          elevation: 0.0,
          color: Colors.purple,
          child: Text("LOGIN", style: TextStyle(color: Colors.white70, fontSize: 17)),
          shape:
              RoundedRectangleBorder(borderRadius: BorderRadius.circular(10.0)),
        ),
      ),
      //Register user button
      Container(
        width: MediaQuery.of(context).size.width,
        height: 40.0,
        padding: EdgeInsets.symmetric(horizontal: 30.0),
        margin: EdgeInsets.only(top: 15.0),
        child: RaisedButton(
          onPressed: (){Navigator.of(context).pushNamed("/register");
          },
          elevation: 0.0,
          color: Colors.purple,
          child: Text("REGISTER", style: TextStyle(color: Colors.white70, fontSize: 17)),
          shape:
          RoundedRectangleBorder(borderRadius: BorderRadius.circular(10.0)),
        ),
      ),
      Container(
          margin: EdgeInsets.only(top: 10.0, left: 60),
          padding: EdgeInsets.symmetric(horizontal: 30.0),
          child: Row(
              children: <Widget>[
                  Text('Forget your ', style: TextStyle(color: Colors.black, fontSize: 17)),
                  new InkWell(
                    onTap: () {
                      createAlertDialog1(context);
                    },
                    child: new Text('Username ', style: TextStyle(color: Colors.white70, fontSize: 17)),
                  ),
                  Text('or ', style: TextStyle(color: Colors.black, fontSize: 17)),
                  new InkWell(
                    onTap: () {
                      createAlertDialog2(context);
                    },
                    child: new Text('Password ', style: TextStyle(color: Colors.white70, fontSize: 17)),
                  ),
                  Text('?'),
              ],
          )
      )

    ]);
  }

  Container textSection() {
    return Container(
      padding: EdgeInsets.only(left: 30.0, right: 30.0, top: 20.0, bottom: 10.0),
      child: Column(
        children: <Widget>[
          TextFormField(
            controller: emailController,
            cursorColor: Colors.black,
            style: TextStyle(color: Colors.black, fontSize: 17),
            decoration: InputDecoration(
              //icon: Icon(Icons.email, color: Colors.white70),
              hintText: "Email",
              border: OutlineInputBorder(
                  borderSide: BorderSide(color: Colors.black),
                  borderRadius: const BorderRadius.all(
                      const Radius.circular(13.0),
                  ),
              ),
              hintStyle: TextStyle(color: Colors.grey),
              contentPadding: const EdgeInsets.only(left: 20.0),
              filled: true,
              fillColor: const Color(0xFFddeff9),
            ),
          ),
          SizedBox(height: 20.0),
          TextFormField(
            controller: passwordController,
            cursorColor: Colors.black,
            obscureText: true,
            style: TextStyle(color: Colors.black, fontSize: 17),
            decoration: InputDecoration(
              //icon: Icon(Icons.lock, color: Colors.white70),
              hintText: "Password",
              border: OutlineInputBorder(
                  borderSide: BorderSide(color: Colors.black),
                  borderRadius: const BorderRadius.all(
                      const Radius.circular(13.0),
                  ),
              ),
              hintStyle: TextStyle(color: Colors.grey),
              contentPadding: const EdgeInsets.only(left: 20.0),
              filled: true,
              fillColor: const Color(0xFFddeff9),
            ),
          ),
        ],
      ),
    );
  }

  Container headerSection() {
    return Container(
      margin: EdgeInsets.only(top: 120.0),
      padding: EdgeInsets.only(left: 40.0, right: 20.0),
      child: Row(children: <Widget>[
        Text("My Medical Secretary",
          textAlign: TextAlign.center,
          style: TextStyle(
              color: Colors.white70,
              fontSize: 30.0,
              fontWeight: FontWeight.bold
          ),
        ),
        Image.asset('assets/images/logo.jpg'),
        //Image.file('../../assets/images/logo.jpg'),
      ])
    );
  }

  createAlertDialog1(BuildContext context) {

    return showDialog<void>(
      context: context,
      // false = user must tap button, true = tap outside dialog
      builder: (BuildContext dialogContext) {
        return AlertDialog(
          title: Center(
              child: Text('Forgot username?', style: TextStyle(color: Colors.grey, fontSize: 17)),
          ),
          content: Container(
              height: 40.0,
              padding: EdgeInsets.symmetric(horizontal: 20.0),
              child: Column(children: <Widget> [
                Text('Please contact your clinic'),
                Text('ph: 0415181703'),
              ],)
          ),
          actions: <Widget>[
            FlatButton(
              child: Text('Cancel'),
              onPressed: () {
                Navigator.of(dialogContext).pop(); // Dismiss alert dialog
              },
            ),
            FlatButton(
              child: Text('Call'),
              onPressed: () => launch("tel://0415181703"),
            ),
          ],
        );
      },
    );
  }

  createAlertDialog2(BuildContext context) {
    return showDialog<void>(
      context: context,
      // false = user must tap button, true = tap outside dialog
      builder: (BuildContext dialogContext) {
        return AlertDialog(
          title: Center(
            child: Text('Forgot password?', style: TextStyle(color: Colors.grey, fontSize: 17)),
          ),
          content: Container(
              height: 180.0,
              padding: EdgeInsets.symmetric(horizontal: 10.0),
              child: Column(children: <Widget> [
                Text('Please enter your email to reset'),
                Text('your password.'),
                Container(
                  height: 30.0,
                  margin: EdgeInsets.only(top: 10.0, bottom: 0.0),
                  padding: EdgeInsets.only(left: 0.0),
                  child: TextFormField(
                    controller: resetPasswordEmailPasswordController,
                    cursorColor: Colors.black,
                    style: TextStyle(color: Colors.black, fontSize: 15),
                    decoration: InputDecoration(
                      //icon: Icon(Icons.email, color: Colors.white70),
                      hintText: "Email...",
                      border: OutlineInputBorder(
                        borderSide: BorderSide(color: Colors.black),
                        borderRadius: const BorderRadius.all(
                          const Radius.circular(5.0),
                        ),
                      ),
                      hintStyle: TextStyle(color: Colors.grey),
                      contentPadding: const EdgeInsets.only(left: 20.0),
                      filled: true,
                      fillColor: Colors.white70,
                    ),
                  ),
                ),
                Container(
                  height: 30.0,
                  margin: EdgeInsets.only(top: 10.0, bottom: 0.0),
                  padding: EdgeInsets.only(left: 0.0),
                  child: TextFormField(
                    controller: newPasswordController,
                    cursorColor: Colors.black,
                    style: TextStyle(color: Colors.black, fontSize: 15),
                    decoration: InputDecoration(
                      //icon: Icon(Icons.email, color: Colors.white70),
                      hintText: "New password...",
                      border: OutlineInputBorder(
                        borderSide: BorderSide(color: Colors.black),
                        borderRadius: const BorderRadius.all(
                          const Radius.circular(5.0),
                        ),
                      ),
                      hintStyle: TextStyle(color: Colors.grey),
                      contentPadding: const EdgeInsets.only(left: 20.0),
                      filled: true,
                      fillColor: Colors.white70,
                    ),
                  ),
                ),
                Container(
                  height: 30.0,
                  margin: EdgeInsets.only(top: 10.0, bottom: 0.0),
                  padding: EdgeInsets.only(left: 0.0),
                  child: TextFormField(
                    controller: confirmPasswordController,
                    cursorColor: Colors.black,
                    style: TextStyle(color: Colors.black, fontSize: 15),
                    decoration: InputDecoration(
                      //icon: Icon(Icons.email, color: Colors.white70),
                      hintText: "Confirm password...",
                      border: OutlineInputBorder(
                        borderSide: BorderSide(color: Colors.black),
                        borderRadius: const BorderRadius.all(
                          const Radius.circular(5.0),
                        ),
                      ),
                      hintStyle: TextStyle(color: Colors.grey),
                      contentPadding: const EdgeInsets.only(left: 20.0),
                      filled: true,
                      fillColor: Colors.white70,
                    ),
                  ),
                )
              ],)
          ),
          actions: <Widget>[
            FlatButton(
              child: Text('Cancel'),
              onPressed: () {
                Navigator.of(dialogContext).pop(); // Dismiss alert dialog
              },
            ),
            FlatButton(
              child: Text('Send'),
              onPressed: () {
                // Send the email
              },
            ),
          ],
        );
      },
    );
  }



}
