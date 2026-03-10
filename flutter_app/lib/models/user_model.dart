class UserModel {
  final String uid;
  final String? email;
  final int points;

  UserModel({required this.uid, this.email, this.points = 0});

  UserModel copyWith({String? uid, String? email, int? points}) {
    return UserModel(uid: uid ?? this.uid, email: email ?? this.email, points: points ?? this.points);
  }
}
