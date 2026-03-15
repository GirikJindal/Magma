# Firebase & AdMob Setup — Magma

This document explains how to wire Firebase and AdMob into the Android skeleton.

1) Add `google-services.json`
   - In the Firebase console create an Android app for package `com.magma.app`.
   - Download `google-services.json` and place it under `app/`.

2) AdMob App ID
   - Create an AdMob app in your AdMob account and copy the App ID.
   - Replace the placeholder in `app/src/main/AndroidManifest.xml`:
     - `com.google.android.gms.ads.APPLICATION_ID` meta-data value.
   - The sample uses Google's test banner ad unit in `activity_main.xml` by default.

3) Sync Gradle
   - Open the `Magma` folder in Android Studio and let Gradle sync. The top-level Gradle file includes the `google-services` plugin; the `app` module applies it.

4) Verify
   - Run the app on a device/emulator with Google Play services.
   - The sample banner uses the test unit `ca-app-pub-3940256099942544/6300978111`.

Notes:
- Do NOT commit `google-services.json` to public repositories if it contains secrets.
- For Firestore/Storage usage update `Repository` to use `FirebaseFirestore.getInstance()` and `FirebaseStorage.getInstance()` when available.

5) Authentication (Firebase Auth)
   - The app includes a simple `AuthRepository` wrapper and auth UI scaffolds under `app/src/main/java/com/magma/app/auth`.
   - `LoginActivity` and `RegisterActivity` use email/password authentication. `MainActivity` redirects unauthenticated users to the login screen.
   - After registration or sign-in users are returned to `MainActivity`. `ProfileActivity` shows current user info and has a sign-out button.
   - To enable social providers (Google, Facebook), configure providers in the Firebase Console and add provider SDKs and configuration.

6) Google Sign-In (Firebase)
   - Create an OAuth 2.0 client ID (Web application) in Google Cloud Console and copy the Web client ID.
   - In the Firebase Console enable Google sign-in under Authentication -> Sign-in method.
   - Add your app's SHA-1 (and SHA-256) fingerprint to the Firebase Android app settings.
   - Replace the placeholder `YOUR_WEB_CLIENT_ID.apps.googleusercontent.com` in `LoginActivity` with your Web client ID.
   - Ensure `com.google.android.gms:play-services-auth` is present (already added to `app/build.gradle`).
   - The `AuthRepository.signInWithGoogle(idToken, callback)` helper exchanges the Google ID token for a Firebase credential.

