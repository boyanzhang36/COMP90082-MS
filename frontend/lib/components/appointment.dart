class Appointment {
  final DateTime date;
  final String title;
  final String note;
  final int duration;

  Appointment(
      {this.date,
        this.title,
        this.note,
        this.duration});

  factory Appointment.fromJson(Map<String, dynamic> json) {
    return Appointment(
        date: DateTime.parse(json['date']),
        title: json['title'],
        note: json['note'],
        duration: json['duration']
    );
  }
}