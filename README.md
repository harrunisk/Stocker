# Stocker  
[![Kotlin Version](https://img.shields.io/badge/kotlin-1.3.50-blue.svg)](https://kotlinlang.org)
[![API](https://img.shields.io/badge/API-19%2B-brightgreen.svg?style=flat)](https://android-arsenal.com/api?level=19)
[![Android Studio](https://img.shields.io/badge/android%20studio-3.5-brightgreen)](https://developer.android.com/studio)

<p align="left">Stocker is a currency monitoring app. It offers instant currency rates of banks.</p>

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
### License
```
MIT License

Copyright (c) 2019 harrunisk

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
```
