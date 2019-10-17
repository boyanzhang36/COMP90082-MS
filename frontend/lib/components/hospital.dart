class Hospital {
  final String name;
  final String contact;
  final String address;
  final String fax;
  final String website;
  final String type;

  Hospital(
      {this.name,
        this.contact,
        this.address,
        this.fax,
        this.website,
        this.type});

  factory Hospital.fromJson(Map<String, dynamic> json) {
    return Hospital(
        name: json['name'],
        contact: json['contact'],
        address: json['address'],
        fax: json['fax'],
        website: json['website'],
        type: json['type']);
  }
}