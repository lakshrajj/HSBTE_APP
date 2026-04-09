# HSBTE App

  An Android study and quiz app for students of the Haryana State Board of Technical Education
  (HSBTE). Includes subject-wise multiple-choice questions, score tracking, and an admin panel
  for managing content.

  ## Features

  - Subject and semester selection
  - MCQ-based quiz with scoring
  - Final result screen
  - Admin panel for content management
  - AdMob integration
  - Firebase backend

  ## Tech Stack

  - **Language:** Java
  - **Platform:** Android
  - **Backend:** Firebase
  - **Ads:** Google AdMob
  - **Networking:** Requires `INTERNET` permission

  ## Getting Started

  ```bash
  git clone https://github.com/Lakshrajj/HSBTE_APP.git

  1. Open in Android Studio
  2. Add your google-services.json to app/
  3. Set your AdMob App ID in AndroidManifest.xml
  4. Build & run

  App Flow

  MainActivity → Select (subject/semester) → QueMain (quiz) → Final (result)
                                           ↓
                                      admina (admin panel)
