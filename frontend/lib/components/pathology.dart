class Pathology {
  final String name;
  final String contact;
  final String address;
  final String fax;
  final String website;

  Pathology(
      {this.name,
        this.contact,
        this.address,
        this.fax,
        this.website});

  factory Pathology.fromJson(Map<String, dynamic> json) {
    return Pathology(
        name: json['name'],
        contact: json['contact'],
        address: json['address'],
        fax: json['fax'],
        website: json['website']);
  }
}