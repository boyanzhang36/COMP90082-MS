class Appointment {
  final DateTime date;
  final String title;
  final String note;

  Appointment(
      {this.date,
        this.title,
        this.note});

  factory Appointment.fromJson(Map<String, dynamic> json) {
    return Appointment(
        date: DateTime.parse(json['date']),
        title: json['title'],
        note: json['note']
    );
  }
}