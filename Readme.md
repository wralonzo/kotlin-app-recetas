# 📱 Jetpack Compose App

## 📌 Descripción
Esta es una aplicación desarrollada en **Jetpack Compose** que implementa navegación con `NavController`, manejo de sesión con **Room** y una UI moderna.

## 🛠️ Tecnologías Utilizadas
- **Kotlin**
- **Jetpack Compose**
- **Room Database**
- **Navigation Component**
- **Material Design 3**

## 🚀 Características
- **Inicio de sesión persistente** con almacenamiento local usando Room.
- **Navegación con NavHost** para gestionar diferentes pantallas.
- **Snackbar reutilizable** para mostrar mensajes.
- **Custom Bottom Navigation Bar** con iconos y estilos personalizados.

## 📂 Estructura del Proyecto
```
📂 app
 ┣ 📂 ui
 ┃ ┣ 📜 MainActivity.kt
 ┃ ┣ 📜 HomeScreen.kt
 ┃ ┣ 📜 LoginScreen.kt
 ┃ ┣ 📜 RecipeScreen.kt
 ┃ ┣ 📜 RecipeForm.kt
 ┃ ┣ 📜 RecipeViewScreen.kt
 ┃ ┣ 📜 components
 ┃ ┃ ┣ 📜 CustomBottomNavigation.kt
 ┃ ┃ ┣ 📜 CustomSnackbar.kt
 ┃ ┃ ┗ 📜 CustomOutlinedTextField.kt
 ┣ 📂 data
 ┃ ┣ 📜 SessionManager.kt
 ┃ ┣ 📜 AppDatabase.kt
 ┃ ┣ 📜 UserDao.kt
 ┃ ┗ 📜 User.kt
```

## 📥 Instalación y Configuración
### 🔹 Requisitos Previos
- Android Studio 📱
- Kotlin 1.7+
- Gradle 8+

### 🔹 Clonar el Repositorio
```sh
git clone https://github.com/user/tu_repositorio.git
cd tu_repositorio
```

### 🔹 Configurar Room Database
En `AppDatabase.kt`:
```kotlin
@Database(entities = [User::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
}
```

En `UserDao.kt`:
```kotlin
@Dao
interface UserDao {
    @Query("SELECT * FROM user WHERE id = 1")
    suspend fun getSession(): User?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveSession(user: User)

    @Query("DELETE FROM user")
    suspend fun clearSession()
}
```

### 🔹 Agregar Dependencias (build.gradle.kts)
```kotlin
dependencies {
    implementation("androidx.room:room-runtime:2.5.0")
    kapt("androidx.room:room-compiler:2.5.0")
    implementation("androidx.navigation:navigation-compose:2.5.3")
    implementation("androidx.compose.material3:material3:1.1.0")
}
```

## 📌 Uso
### 🔹 Manejo de Sesión con Room
```kotlin
val sessionManager = SessionManager(context)
val isLoggedIn = sessionManager.getSession()
```

### 🔹 Navegación con NavController
```kotlin
NavHost(
    navController = navController,
    startDestination = if (isLoggedIn) "home" else "login"
) {
    composable("home") { HomeScreen() }
    composable("login") { LoginScreen() }
}
```

### 🔹 Mostrar Snackbar Personalizado
```kotlin
CustomSnackbar(message = "¡Bienvenido!", type = SnackbarType.SUCCESS)
```

## 📄 Licencia
MIT License © 2024 wralonzo

