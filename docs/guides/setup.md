# Setup Guide – Demy Android App

This guide explains how to set up the development environment and run the Demy Android application locally.  
Follow these steps before contributing to the project.

---

## 1. Prerequisites

- **Android Studio** (latest stable version, e.g., Iguana or Hedgehog).
- **JDK 21** (configured in Android Studio).
- **Android SDK 34+** (install via SDK Manager).
- **Gradle Wrapper** (already included in the repo, use `./gradlew`).
- Emulator or physical device running **Android 8.0 (API 26)** or higher.

---

## 2. Clone the Repository

```
git clone https://github.com/<org>/<repo>.git
cd <repo>
```

---

## 3. Open the Project in Android Studio

1. Launch **Android Studio**.
2. Select **Open** → choose the project folder.
3. Wait for Gradle sync to complete.
4. Verify JDK in:  
   *File > Settings > Build, Execution, Deployment > Build Tools > Gradle > Gradle JDK = 21*.

---

## 4. Build the Project

Run the following command in the project root:

```
./gradlew clean build
```

---

## 5. Run the Application

### On Emulator
1. Open **AVD Manager** in Android Studio.
2. Create a device (e.g., Pixel 6, API 34).
3. Click **Run** to launch the app on the emulator.

### On Physical Device
1. Enable **Developer Options** and **USB Debugging**.
2. Connect the device via USB.
3. Run:  
   ```
   ./gradlew installDebug
   ```

---

## 6. Run Tests

- Unit tests:
  ```
  ./gradlew test
  ```

- Instrumented tests (requires emulator/device):
  ```
  ./gradlew connectedAndroidTest
  ```

---

## 7. Troubleshooting

- **Gradle sync failed** → Check Android Studio is updated and JDK is set to 21.
- **Emulator slow** → Enable hardware acceleration (Intel HAXM / Hyper-V).
- **Build errors** → Run `./gradlew clean` and re-sync.
- **Missing dependencies** → Ensure internet connection for Gradle downloads.

---

## 8. Next Steps

- Review [CONTRIBUTING.md](../../CONTRIBUTING.md) for workflow and commit conventions.
- Check [style-guide.md](../design/style-guide.md) for UI/UX rules.
- Explore [backend-integration.md](../api/backend-integration.md) to understand API endpoints.
