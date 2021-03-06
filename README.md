
![MovieApp](https://user-images.githubusercontent.com/40839023/140684260-d40dd0e7-1a09-406c-85a7-4b43b74a0440.png)

# ChipperMovie 聽

Es una aplicaci贸n de peliculas y sus detalles filtrados por categorias para demostrar el uso de mis habilidaedes en desarrollo
movil.

## Acerca

Permite cargar peliculas segun el tipo de categoria que se desee, el cual son 6, la data solo se maneja en tiempo real.

* Soportan dark theme
* Sencilla y limpia Material UI

El api que se consume proviene de [aqui!](https://developers.themoviedb.org/4/getting-started/authorization)

## Construido con 馃洜

- [Kotlin](https://kotlinlang.org/) - Lenguaje de programaci贸n aprobado por google
- [Coroutines](https://kotlinlang.org/docs/reference/coroutines-overview.html) - Para tareas asincronicas y mas
- [Android Architecture Components](https://developer.android.com/topic/libraries/architecture) - Colecci贸n de librerias que ayudan a tener un dise帽o robusto, testeable y mantenible.
  - [LiveData](https://developer.android.com/topic/libraries/architecture/livedata) - Objetos de data que notifica a la vista cuando algo cambia en la base de datos.
  - [ViewModel](https://developer.android.com/topic/libraries/architecture/viewmodel) - Almacena la informaci贸n de la UI y no se destruye con rotaciones entre otros.
  - [Room](https://developer.android.com/topic/libraries/architecture/room) - SQLite para android.
 - [Dependency Injection](https://developer.android.com/training/dependency-injection) - 
  - [Hilt-Dagger](https://dagger.dev/hilt/) - Libreria estandar para la inyecci贸n de dependencias en el proyecto
 - [Retrofit](https://square.github.io/retrofit/) - Permite la comunicaci贸n con servicios HTTP.
 - [Material Components for Android](https://github.com/material-components/material-components-android) - Componentes para android modularizados y editables.
 
 
 ## Patron de dise帽o
 
 Esta aplicaci贸n hace uso de MVC (Model view controller) 
 ![mvc](https://user-images.githubusercontent.com/40839023/140684709-4755f42d-6aec-4de6-b93f-670ea75a743b.png)
 
 
 ## Pendientes
 
 * Pruebas unitarias
 * Offline cache
 * Dise帽os enfocado a las tablets.
