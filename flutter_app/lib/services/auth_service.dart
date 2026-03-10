import 'dart:async';
import 'package:flutter/foundation.dart';
import 'package:firebase_auth/firebase_auth.dart' as fb;
import '../models/user_model.dart';

class AuthService with ChangeNotifier {
  final fb.FirebaseAuth _auth = fb.FirebaseAuth.instance;

  Stream<UserModel?> get userChanges async* {
    await for (final fb.User? u in _auth.userChanges()) {
      if (u == null) {
        yield null;
      } else {
        yield UserModel(uid: u.uid, email: u.email);
      }
    }
  }

  Future<UserModel?> signIn(String email, String password) async {
    final cred = await _auth.signInWithEmailAndPassword(email: email, password: password);
    final u = cred.user;
    if (u == null) return null;
    final model = UserModel(uid: u.uid, email: u.email);
    notifyListeners();
    return model;
  }

  Future<UserModel?> register(String email, String password) async {
    final cred = await _auth.createUserWithEmailAndPassword(email: email, password: password);
    final u = cred.user;
    if (u == null) return null;
    final model = UserModel(uid: u.uid, email: u.email);
    notifyListeners();
    return model;
  }

  Future<void> signOut() async {
    await _auth.signOut();
    notifyListeners();
  }
}
