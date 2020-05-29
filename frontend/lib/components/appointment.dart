import 'package:flutter/foundation.dart';

class Appointment {
  final String id;
  final String pid;
  final DateTime date;
  final String title;
  final String note;
  final String userNote;
  final int duration;
  final String status;

  Appointment(
      {this.id,
        this.pid,
        this.date,
        this.title,
        this.note,
        this.userNote,
        this.duration,
        this.status});

  factory Appointment.fromJson(Map<String, dynamic> json) {
    return Appointment(
        id: json['id'],
        pid: json['uid'],
        date: DateTime.parse(json['date']),
        title: json['title'],
        note: json['note'],
        userNote: json['user_note'],
        duration: json['duration'] as int,
        status: json['status']
    );
  }
}