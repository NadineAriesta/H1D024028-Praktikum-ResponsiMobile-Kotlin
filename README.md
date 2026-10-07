# 🎮 Game Explorer (Katalog dan Eksplorasi Video Game)

Aplikasi Android berbasis **Jetpack Compose** dan **Material Design 3** yang dikembangkan untuk memenuhi Ujian Responsi Pemrograman Mobile. Aplikasi ini mengambil dan menampilkan data video game secara dinamis dari **RAWG REST API** menggunakan arsitektur **MVVM (Model-View-ViewModel)**.

---

## 📋 Daftar Isi
1. [Fitur Utama](#-fitur-utama)
2. [Persyaratan & Spesifikasi Teknis](#-persyaratan--spesifikasi-teknis)
3. [Penjelasan Teknis & Arsitektur](#-penjelasan-teknis--arsitektur)
4. [Struktur Proyek](#-struktur-proyek)
5. [Pengaturan API Key RAWG](#-pengaturan-api-key-rawg)
6. [Screenshot Aplikasi](#-screenshot-aplikasi)
7. [Cara Menjalankan](#-cara-menjalankan-aplikasi)

---

## 📱 Fitur Utama
1. **Home Screen**:
   - Menampilkan kumpulan data video game menggunakan **`LazyVerticalGrid`** (grid responsif 2 kolom).
   - Setiap kartu game (*Game Card*) memuat gambar sampul (*background image*), judul game, rating bintang dengan angka, dan tanggal rilis format ISO 8601.
   - **Search Bar**: Kolom pencarian reaktif untuk mencari game berdasarkan nama/kata kunci secara *real-time*.
   - **Dialog Pengaturan API Key**: Memungkinkan pengguna memasukkan RAWG API Key secara langsung di aplikasi untuk mencegah kendala autentikasi (HTTP 401).
2. **Game Detail Screen**:
   - Menampilkan informasi mendalam saat salah satu game dipilih.
   - Memuat baner gambar, judul game, rating lengkap, tanggal rilis, chip genre game, chip platform tersedia, serta sinopsis/deskripsi lengkap game yang diparsing bersih dari HTML.
   - Navigasi mulus menggunakan **Jetpack Navigation Compose**.

---

## 🛠️ Persyaratan & Spesifikasi Teknis
- **Bahasa Pemrograman**: Kotlin (memanfaatkan *data class*, *null safety* `?` / `?:`, *lambda expressions*, dan *Coroutines*).
- **User Interface**: Jetpack Compose dengan Material Design 3, kustomisasi *Theme* dan *Typography*.
- **Networking**: Retrofit 2, Gson Converter, dan OkHttp Logging Interceptor.
- **Image Loading**: Coil Compose untuk memuat gambar dari URL secara efisien.
- **State Management**: State-driven UI menggunakan `StateFlow`, `MutableStateFlow`, dan `collectAsState`.

---

## ⚙️ Penjelasan Teknis & Arsitektur (MVVM)
Aplikasi ini menerapkan pola arsitektur **MVVM (Model-View-ViewModel)** secara ketat untuk memisahkan logika bisnis, pengelolaan data, dan antarmuka pengguna:

1. **Model & Data Layer**:
   - `GameDto` & `GameResponse`: Data class Kotlin yang memetakan struktur JSON dari RAWG API.
   - `ApiService`: Interface Retrofit yang mendefinisikan endpoint `GET /games` (pencarian & daftar game) dan `GET /games/{id}` (detail game).
   - `GameRepository`: Menangani pemanggilan network melalui Retrofit dan membungkus hasilnya dalam `Result<T>` untuk penanganan error yang aman (*error handling*).
2. **ViewModel Layer**:
   - `GameViewModel`: Mengelola state UI (`gamesState`, `detailState`, `searchQuery`, dan `apiKey`). Berkomunikasi dengan repository di dalam `viewModelScope` menggunakan Coroutines agar tidak memblokir *main thread*.
3. **UI Layer**:
   - `HomeScreen` & `GameDetailScreen`: Komosabel Jetpack Compose yang bersifat reaktif terhadap perubahan state dari ViewModel.

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

## 🔑 Pengaturan API Key RAWG
1. Dapatkan API Key gratis di [https://rawg.io/apidocs](https://rawg.io/apidocs).
2. Buka aplikasi, lalu tap **ikon Kunci (🔑)** di pojok kanan atas Home Screen.
3. Masukkan API Key Anda pada dialog yang muncul, lalu tap **"Simpan & Muat Ulang"**.

---

## 📸 Screenshot Aplikasi

<p align="center">
  <table border="1" cellspacing="0" cellpadding="8" width="100%" style="border-collapse: collapse;">
    <tr>
      <th align="center" width="33%"><b>Home Screen</b></th>
      <th align="center" width="33%"><b>Fitur Pencarian</b></th>
      <th align="center" width="33%"><b>Game Detail Screen</b></th>
    </tr>
    <tr>
      <td align="center" width="33%">Menampilkan grid katalog game awal dengan penjelasan singkat</td>
      <td align="center" width="33%">Fitur pencarian game secara <i>real-time</i></td>
      <td align="center" width="33%">Menampilkan informasi detail, rating, genre, platform, dan deskripsi</td>
    </tr>
    <tr>
      <td align="center" width="33%"><img src="HomeScreen.png" width="100%" alt="Home Screen"/></td>
      <td align="center" width="33%"><img src="Searchbar.png" width="100%" alt="Fitur Pencarian"/></td>
      <td align="center" width="33%"><img src="GameDetailScreen.png" width="100%" alt="Game Detail Screen"/></td>
    </tr>
  </table>
</p>

---

## 🚀 Cara Menjalankan Aplikasi
1. Clone repository ini ke komputer Anda.
2. Buka proyek menggunakan **Android Studio** (versi Koala / Jellyfish atau terbaru).
3. Tunggu proses *Gradle Sync* selesai.
4. Hubungkan perangkat Android fisik atau jalankan emulator (Minimum SDK 29).
5. Klik tombol **Run (▶)** di Android Studio.
