class Radiology {
  final String name;
  final String contact;
  final String address;
  final String fax;
  final String website;

  Radiology({this.name,
    this.contact,
    this.address,
    this.fax,
    this.website});

  factory Radiology.fromJson(Map<String, dynamic> json) {
    return Radiology(
        name: json['name'],
        contact: json['contact'],
        address: json['address'],
        fax: json['fax'],
        website: json['website']);
  }
}