# SuperHeroes

<p align="center">  
SuperHeroes demonstrates my practical skills in modern Android development with Dagger2, Coroutines,Flow, Jetpack (Room, ViewModel, LiveData, Navigation Component) based on MVVM with Clean Architecture.
</p>

## Preview

<img src="/previews/preview.gif" align="right" width="32%"/>

## Tech stack & Open-source libraries
- Minimum SDK level 23
- [Kotlin](https://kotlinlang.org/) based, [Coroutines](https://github.com/Kotlin/kotlinx.coroutines) + [Flow](https://kotlin.github.io/kotlinx.coroutines/kotlinx-coroutines-core/kotlinx.coroutines.flow/) for asynchronous.
- [Dagger2](https://dagger.dev/) for dependency injection.
- Jetpack
    - Lifecycle - Observe Android lifecycles and handle UI states upon the lifecycle changes.
    - ViewModel - Manages UI-related data holder and lifecycle aware. Allows data to survive configuration changes such as screen rotations.
    - ViewBinding - Feature that allows you to more easily write code that interacts with views.
    - Room Persistence - Constructs Database by providing an abstraction layer over SQLite to allow fluent database access.
    - LiveData - Observable data holder class.
    - Navigation component - Helps you implement navigation, from simple button clicks to more complex patterns, such as app bars and the navigation drawer.
- Architecture
    - MVVM Architecture (View - DataBinding - ViewModel - Model) with Clean Architecture(Data - Domain - Presentation)
- [Retrofit2 & OkHttp3](https://github.com/square/retrofit) - Construct the REST APIs.
- [Gson](https://github.com/google/gson) - Gson is a Java library that can be used to convert Java Objects into their JSON representation.
- [Glide](https://github.com/bumptech/glide) - Loading images from network.
- [Palette](https://developer.android.com/develop/ui/views/graphics/palette-colors) - Support library that extracts prominent colors from images to help you create visually engaging apps.
- Custom Views
    - [ProgressView](https://github.com/skydoves/progressview) - A polished and flexible ProgressView, fully customizable with animations.

## Open API

SuperHeroes using the [superhero-api](https://akabab.github.io/superhero-api/) for constructing RESTful API.<br>
superhero-api provides a RESTful API interface to highly detailed objects built from thousands of lines of data related to Superheroes.

## Authors

* **Nikolay Lysenko**  - [SandroLN](https://github.com/SandroLN)
