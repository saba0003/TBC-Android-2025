# 🎬 Netflix Imitation App

![Kotlin](https://img.shields.io/badge/Kotlin-7F52FF?style=for-the-badge&logo=kotlin&logoColor=white)
![Android](https://img.shields.io/badge/Android-3DDC84?style=for-the-badge&logo=android&logoColor=white)
![Gradle](https://img.shields.io/badge/Gradle-02303A?style=for-the-badge&logo=gradle&logoColor=white)
![Java](https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white)
![Spring Boot](https://img.shields.io/badge/Spring_Boot-6DB33F?style=for-the-badge&logo=spring-boot&logoColor=white)
![Docker](https://img.shields.io/badge/Docker-2496ED?style=for-the-badge&logo=docker&logoColor=white)

## 📱 Project Overview

This is a native Android application designed to imitate the core viewing experience of Netflix. The
primary goal of this application is to demonstrate modern Android development practices, using a
custom-built backend to serve movie content.

Users can browse a catalogue of movies, search for specific titles, watch trailers, and stream
content in a seamless, user-friendly interface.

---

## ✨ Features & Usage

### How to use the App:

1. **Splash Screen:** Upon launching, users are greeted with a branded splash screen.
2. **Home/Catalogue:** Browse a list of available movies fetched from the server.
    * *Note on Navigation:* Pressing "Back" on this screen will exit the app (navigate to the device
      home screen).
3. **Search:** Use the search bar to query movies by title using the custom API endpoints.
4. **Movie Details:** Click on any movie poster to view details, watch the trailer, or start the
   movie.
5. **Visual Feedback:** The app handles data loading states with placeholders and displays custom
   error images if fetching fails.

---

## 🧰 Tech Stack

### Android (Client)

The application is written in **100% Kotlin** and built using **Gradle**.

* **Architecture:** MVVM with Repository Pattern.
* **Asynchronous Operations:** Kotlin Coroutines & **Flows** (for continuous data fetching).
* **Dependency Injection:** [Dagger Hilt](https://dagger.dev/hilt/).
* **Networking:**
    * [Retrofit](https://square.github.io/retrofit/): For REST API communication.
    * [Moshi](https://github.com/square/moshi): For JSON serialization/deserialization.
* **Image Loading:** [Coil](https://coil-kt.github.io/coil/): Handles URL fetching, placeholders,
  and error images.
* **Navigation:** Android Jetpack Navigation Component with **SafeArgs** for type-safe data passing.
* **UI:** XML layouts utilizing **ConstraintLayout**.
* **Minimum SDK:** 24 (Android 7.0).
* **Target/Compile SDK:** 36 (Android 16).

### Backend (Server)

The app connects to a custom API running on `localhost`.

* **Language:** Java (21).
* **Framework:** Spring Boot.
* **Build Tool:** Maven.
* **Database:** MySQL.
* **Containerization:** Docker.

---

## ⚙️ Configuration & Setup

### Environment Setup

The application is configured to run locally using the Android Emulator's loopback address (
`10.0.2.2`) to access the localhost server.

**Build Configuration (Debug):**

```kotlin
buildTypes {
    debug {
        isMinifyEnabled = true
        // Android Emulator localhost alias
        buildConfigField(type = "String", name = "BASE_URL", value = "\"http://10.0.2.2:8080/\"")
        buildConfigField(type = "String", name = "API", value = "\"api/\"")
        buildConfigField(type = "String", name = "API_VERSION", value = "\"v1/\"")
        buildConfigField(type = "String", name = "GET_ALL_MOVIES_ENDPOINT", value = "\"movies\"")
        buildConfigField(
            type = "String",
            name = "GET_MOVIES_BY_TITLE_ENDPOINT",
            value = "\"movies/search/by-title\""
        )
    }
}
```
