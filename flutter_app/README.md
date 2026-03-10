# Magma (Flutter MVP)

This folder contains a Flutter MVP for the Magma Android app. It includes:

- Email/password authentication (Firebase Auth wiring)
- Local database (sqflite) with a simple points/tasks table
- FCM and in-app-purchase dependency wiring (stubs)
- Simple login and home screens

Notes before running:

1. Install Flutter (https://flutter.dev/docs/get-started/install).
2. Add Android platform: `flutter create .` inside this folder if you need generated Android/iOS folders.
3. To enable Firebase features, add your `google-services.json` to `android/app/` and follow Firebase setup.
4. Run the app:

```bash
flutter pub get
flutter run -d emulator-5554
```

This is an MVP skeleton. Firebase, FCM, and IAP require platform configuration and app IDs.
