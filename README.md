# 🎬 MyTopTenMovies (GAME)

Aplicación Android desarrollada con Kotlin, que permite visualizar las películas mejor calificadas según la API de TMDB. El proyecto sigue una arquitectura moderna (MVVM) e incluye funcionalidades avanzadas como modo oscuro, animaciones, validación y almacenamiento local de sesión.

---

## 📲 Características

- 🧾 **Autenticación local** con DataStore (email y contraseña validados).
- 🎥 **Consumo de API de TMDB** con Retrofit.
- 📱 **Arquitectura MVVM** limpia y escalable.
- 🌗 **Soporte para Dark Mode** con `DayNight Theme`.
- 🎬 **Animación al abrir detalles** de película.
- 🧪 **Pruebas unitarias** con Mockito y JUnit.
- 🚀 **SplashScreen** con logo personalizado.

---

## 🧩 Tecnologías utilizadas

| Herramienta       | Propósito                        |
|-------------------|----------------------------------|
| Kotlin            | Lenguaje principal               |
| Retrofit          | Cliente HTTP para la API de TMDB |
| DataStore         | Almacenamiento local             |
| MVVM              | Patrón de arquitectura           |
| XML               | Diseño de vistas                 |
| ViewModel + LiveData | Comunicación reactiva         |
| JUnit / Mockito   | Testing                          |

---

## 🔒 Validación de Login

- ✅ Email con expresión regular
- ✅ Contraseña con mínimo 6 caracteres, 1 mayúscula y 1 número

---

## 🌓 Soporte Dark Mode

Implementado con `MaterialComponents.DayNight.DarkActionBar`, colores temáticos en `colors.xml` y `colors-night.xml`.

---

## 🖼 SplashScreen

Pantalla inicial con logo personalizado antes de lanzar `LoginActivity`.

---

## 📷 Capturas de pantalla

> (Aquí puedes añadir imágenes cuando estés listo con los screenshots)

---

## 📦 Instalación

1. Clona este repositorio:
```bash
git clone https://github.com/tu-usuario/MyTopTenMovies.git
