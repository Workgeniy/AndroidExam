# 🐱 ExamAndroid

Приложение на Android, отображающее галерею изображений котов с возможностью добавления в избранное.

---

## ✨ Возможности

- Просмотр изображений котов (из API)
- Добавление/удаление из избранного
- Навигация через `BottomNavigationView`
- Информация о приложении (экран "About")

---

## 🧩 Архитектура

Проект реализован с использованием:
- MVVM (ViewModel + UseCases + Repository)
- Hilt (для Dependency Injection)
- Navigation Component
- Coroutine + Flow
- Coil (для загрузки изображений)

---

## 📂 Структура проекта

com.example.examandroid
│
├── data # API и реализация репозитория
├── domain # Модели и UseCase'ы
├── presentation # UI: фрагменты, адаптеры, ViewModel
└── di # Hilt модули

---

## ⚙️ Зависимости

```kotlin
// Coil
implementation("io.coil-kt:coil:2.4.0")

// Navigation
implementation("androidx.navigation:navigation-fragment-ktx:2.7.7")
implementation("androidx.navigation:navigation-ui-ktx:2.7.7")

// Hilt
implementation("com.google.dagger:hilt-android:2.50")
kapt("com.google.dagger:hilt-android-compiler:2.50")

// Coroutines
implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.7.3")