# Stocker  
[![Kotlin Version](https://img.shields.io/badge/kotlin-1.3.50-blue.svg)](https://kotlinlang.org)
[![API](https://img.shields.io/badge/API-19%2B-brightgreen.svg?style=flat)](https://android-arsenal.com/api?level=19)
[![Android Studio](https://img.shields.io/badge/android%20studio-3.5-brightgreen)](https://developer.android.com/studio)

<p align="center">Stocker is a currency monitoring app. It offers instant currency rates of banks.</p>

### Art
<p align="center">
<img src="https://github.com/harrunisk/Stocker/blob/master/art/App0.jpg" width="200">
<img src="https://github.com/harrunisk/Stocker/blob/master/art/App1.jpg" width="200">
<img src="https://github.com/harrunisk/Stocker/blob/master/art/App2.jpg" width="200">
<img src="https://github.com/harrunisk/Stocker/blob/master/art/App3.jpg" width="200">
</p>

<p align="center"><img src="https://github.com/harrunisk/Stocker/blob/master/art/AppGif.gif" alt="Scenic Hiking" width="200"></p>  

### Architecture
* [Clean Architecture](https://www.amazon.com/Clean-Architecture-Craftsmans-Software-Structure/dp/0134494164)
* [MVVM](https://www.raywenderlich.com/8984-mvvm-on-android)
* [Modularization]()

### Patterns
* [Repository](https://developer.android.com/jetpack/docs/guide)
* [Observer](https://code.tutsplus.com/tutorials/android-design-patterns-the-observer-pattern--cms-28963)

### Approaches
* [SOLID Principle](https://itnext.io/solid-principles-explanation-and-examples-715b975dcad4?gi=79443348411d)
* [Unit Testing](http://softwaretestingfundamentals.com/unit-testing/)

### Technology Stack
* [Kotlin](https://kotlinlang.org/)
* [Kotlin DSL](https://docs.gradle.org/current/userguide/kotlin_dsl.html)
* [Dagger 2](https://github.com/google/dagger)
* [Retrofit 2](https://square.github.io/retrofit/)
* [Room Persistence Library](https://developer.android.com/topic/libraries/architecture/room) 
* [Kotlin Coroutines](https://kotlinlang.org/docs/reference/coroutines-overview.html)
* [Android Jetpack](https://developer.android.com/jetpack)
  * [Lifecycle Aware Components](https://developer.android.com/topic/libraries/architecture/lifecycle)
  * [LiveData](https://developer.android.com/topic/libraries/architecture/livedata)
  * [ViewModel](https://developer.android.com/topic/libraries/architecture/viewmodel)
  * [ConstraintLayout](https://developer.android.com/training/constraint-layout)
  * [Architecture Components](https://developer.android.com/topic/libraries/architecture)
* [Lottie](https://github.com/airbnb/lottie-android)
### Layers
* Data (This layer responsible for all kind of data calls and holding the data.)  
* Domain  (The layer is responsible for data migration between Data and Presentation layers. It can be used every kind of kotlin project. It is not only for android)
* Presentation (UI staff such as displaying data, user interaction)

