class Appointment {
  final DateTime date;
  final String title;
  final String note;
  final int duration;
  final String status;

  Appointment(
      {this.date,
        this.title,
        this.note,
      this.duration,
      this.status});

  factory Appointment.fromJson(Map<String, dynamic> json) {
    return Appointment(
        date: DateTime.parse(json['date']),
        title: json['title'],
        note: json['note'] as String,
        duration: json['duration'] as int,
        status: json['status']
    );
  }
}