class Pdffile {
  final String id;
  final String title;
  final String link;
  final String apptid;

  Pdffile(
      {this.id,
        this.title,
        this.link,
        this.apptid});

  factory Pdffile.fromJson(Map<String, dynamic> json) {
    return Pdffile(
        id: json['id'],
        title: json['title'],
        link: json['link'],
        apptid: json['apptid']);
  }
}