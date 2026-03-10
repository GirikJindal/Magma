import 'package:firebase_messaging/firebase_messaging.dart';

class NotificationService {
  NotificationService._();
  static final NotificationService instance = NotificationService._();

  final FirebaseMessaging _fm = FirebaseMessaging.instance;

  Future<void> init() async {
    try {
      await _fm.requestPermission();
      final token = await _fm.getToken();
      // In a full app send token to backend
      // ignore: avoid_print
      print('FCM token: $token');

      FirebaseMessaging.onMessage.listen((RemoteMessage message) {
        // handle foreground messages
        // ignore: avoid_print
        print('FCM message: ${message.notification?.title}');
      });
    } catch (e) {
      // ignore: avoid_print
      print('Notification init error: $e');
    }
  }
}
