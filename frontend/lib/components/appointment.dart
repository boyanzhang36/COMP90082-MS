class Appointment {
  final String date;
  final String title;
  final String note;

  Appointment(
      {this.date,
        this.title,
        this.note});

  factory Appointment.fromJson(Map<String, dynamic> json) {
    return Appointment(
        date: json['date'],
        title: json['title'],
        note: json['note']
    );
  }
}