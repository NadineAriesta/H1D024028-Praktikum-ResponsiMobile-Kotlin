# Aplikasi Katalog dan Eksplorasi Video Game (GameCatalog)

Aplikasi Android berbasis **Jetpack Compose** dan **Material Design 3** yang dikembangkan untuk memenuhi Ujian Responsi Pemrograman Mobile. Aplikasi ini mengambil data game secara dinamis dari **RAWG API** menggunakan arsitektur **MVVM (Model-View-ViewModel)**.

---

## 📱 Fitur Utama
1. **Home Screen**:
   - Menampilkan daftar video game menggunakan `LazyVerticalGrid`.
   - Setiap kartu game menampilkan gambar sampul, judul game, rating, dan tanggal rilis.
   - **Search Bar**: Memungkinkan pengguna mencari game secara real-time berdasarkan kata kunci.
2. **Game Detail Screen**:
   - Menampilkan informasi detail saat salah satu game dipilih.
   - Memuat judul, gambar, rating lengkap, tanggal rilis, genre, platform, serta deskripsi lengkap game.
   - Navigasi mulus menggunakan **Jetpack Navigation Compose**.

---

## 🛠️ Tech Stack & Arsitektur
- **Bahasa Pemrograman**: Kotlin (Null safety, Data Class, Coroutines, StateFlow)
- **UI Framework**: Jetpack Compose & Material Design 3
- **Arsitektur**: MVVM (Model-View-ViewModel)
- **Networking**: Retrofit 2, Gson Converter, OkHttp Logging Interceptor
- **Image Loading**: Coil Compose
- **Navigasi**: Navigation Compose (`androidx.navigation:navigation-compose`)

---

## 📂 Struktur Proyek
```text
com.pemmob.gamecatalog/
│
├── data/
│   ├── model/          # GameDto, GameResponse, PlatformDto, GenreDto
│   ├── network/        # ApiService, RetrofitClient
│   └── repository/     # GameRepository
│
├── ui/
│   ├── navigation/     # NavGraph & Screen routes
│   ├── screen/         # HomeScreen, GameDetailScreen, GameCard
│   ├── theme/          # Theme, Color, Type (Material 3)
│   └── viewmodel/      # GameViewModel & UiState
│
└── MainActivity.kt
```

---

## 🚀 Cara Menjalankan Aplikasi
1. Clone repository ini.
2. Buka proyek menggunakan **Android Studio** (Koala / Jellyfish atau versi terbaru).
3. Pastikan koneksi internet aktif (karena data diambil dari REST API RAWG).
4. Build dan Run aplikasi pada emulator atau perangkat fisik Android (Minimum SDK 29).
