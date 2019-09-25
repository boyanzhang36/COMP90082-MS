class Doctor {
  final String address;
  final String contact;
  final String email;
  final String expertise;
  final String id;
  final String name;

  Doctor(
      {this.address,
        this.contact,
        this.email,
        this.expertise,
        this.id,
        this.name});

  factory Doctor.fromJson(Map<String, dynamic> json) {
    return Doctor(
        address: json['address'],
        contact: json['contact'],
        email: json['email'],
        expertise: json['expertise'],
        id: json['id'],
        name: json['name']);
  }
}