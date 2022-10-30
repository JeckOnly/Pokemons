# 											神奇宝贝图鉴·Pokedex

:fire:A Pokedex ​fully based on Jetpack Compose, with Coroutine, Flow, Retrofit, Jetpack(Hilt, WorkManager, ViewModel, Room, DataStore)and other MAD Kits be recommended by Google.

:pushpin: MVI :heavy_plus_sign: Repository Pattern :heavy_plus_sign: Recommed Architecture :heavy_plus_sign: Modularization.

![](docs/img/screen_shot.png)

<img src="docs/img/1b022b71b3e1fd3f25a39a2a01853ced.gif" align="right" width="320" />

## Download

Go [releases](https://github.com/JeckOnly/Pokemons/releases) to download the latest apk.

## Teck-stack

- min SDK 23, compile SDK 32.
- :100:Kotin based, Coroutine and Flow for asynchronous.
- Jetpack Compose: For UI, no xml anymore.
  - Compose Navigation: Navigation between Composables
  - Compose ConstraintLayout: `ConstraintLayout` is a layout that allows you to place composables relative to other composables on the screen
  - Material: This is the higher level entry point of Compose, designed to provide components that match those described at www.material.io.
- Acompanist: Accompanist is a group of libraries that aim to supplement [Jetpack Compose](https://developer.android.com/jetpack/compose) with features that are commonly required by developers but not yet available.
  - systemUIController: A library that provides easy-to-use utilities for recoloring the Android system bars from Jetpack Compose.
  - pager: A library that provides utilities for building paginated layouts in Jetpack Compose, similar to Android's [ViewPager](https://developer.android.com/reference/kotlin/androidx/viewpager/widget/ViewPager).(In fact, it has some bugs right now, but it' s ok)
- Jetpack
  - Lifecycle: Observe Android lifecycles and handle UI states upon the lifecycle changes.
  - ViewModel: Manages UI-related data holder and lifecycle aware. Allows data to survive configuration changes such as screen rotations.
  - Room: Constructs Database by providing an abstraction layer over SQLite to allow fluent database access.
  - Proto DataStore: A data storage solution that allows you to store key-value pairs or typed objects with [protocol buffers](https://developers.google.com/protocol-buffers)
  - [Hilt](https://dagger.dev/hilt/): for dependency injection.
  - WorkManager: [WorkManager](https://developer.android.com/reference/androidx/work/WorkManager) is the recommended solution for persistent work
- Splash Screen: Compatible with Android12 default splash screen.
- Coil: Load image from network.
- Palette: Extract representative color palettes from images.
- [Retrofit2 & OkHttp3](https://github.com/square/retrofit): Construct the REST APIs and paging network data.
- [Sandwich](https://github.com/skydoves/Sandwich): Construct a lightweight and modern response interface to handle network payload for Android.
- Gson: Used to convert network response to Java objects.

## Modularization

Pokedex is modulized, and I learn how to do it form [NowInAndroid by Google](https://github.com/android/nowinandroid).This is my project module structure diagram:

![image-20221025141602694](docs/img/image-20221025141602694.png)

## Architecture

#### Priciples

##### Separation of concerns

Programming with jetpack compose naturally applies this principle

##### Drive UI from data models

Another important principle is that you should drive your UI from data models, preferably persistent models. Data models represent the data of an app. I apply [domain specific models](https://blog.danlew.net/2022/08/15/domain-specific-models/) in this project. There are dto models(from network), entity models(save in database) and UI models(show in UI).And use a mapper file that contains some functions to transform between them.

##### Single source of truth

When a new data type is defined in your app, you should assign a Single Source of Truth (SSOT) to it. The SSOT is the *owner* of that data, and only the SSOT can modify or mutate it.

##### Unidirectional Data Flow

The [single source of truth principle](https://developer.android.com/topic/architecture?hl=en#single-source-of-truth) is often used in our guides with the Unidirectional Data Flow (UDF) pattern. In UDF, **state** flows in only one direction. The **events** that modify the data flow in the opposite direction.

[More info about recommended best practices.](https://developer.android.com/topic/architecture?hl=en#separation-of-concerns), I just copy form here.

#### UI Layer

##### Navigation

![image-20221025135936027](docs/img/image-20221025135936027.png)

##### UI and StateHolder Arch

<img src="docs/img/image-20221025140144101.png" alt="image-20221025140144101" style="zoom: 80%;" />

[More Infos](https://developer.android.com/topic/architecture/ui-layer)

It is highly recommended that you learn from the official documentation and make your hands dirty.

#### Data Layer

Data Layer contains Repository and Data Source.It is generally accessible only by the UI layer.

This is the official diagram:

![image-20221025140557633](docs/img/image-20221025140557633.png)

And in this project, data sources constains: remote, database, datastore(Jetpack), and repo constains: PokemonRepo, UserPrefsRepo.

![image-20221025140741042](docs/img/image-20221025140741042.png)

#### Domain Specific Model

**ApiModels** match the API schema. They are immutable (because they’re just used for one-time communication) and nullable (since we might request a subset of fields).

**DbModels** match the DB schema. They are mutable (because of OrmLite) but provide more non-null property guarantees. (In an ideal, non-ORM world, they would be immutable as well.)

**UiModels** match whatever the UI needs. They are immutable and their properties are generally non-null.

Between each of these models are converters. We send a network request to the server and get back an ApiModel; that’s converted into a DbModel for insertion into the database.  Then the UI queries the database, which converts the DbModel into whatever UiModels the UI requires.

![image-20221025142605107](docs/img/image-20221025142605107.png)

[You can learn more in here](https://blog.danlew.net/2022/08/15/domain-specific-models/)

## Reference

The idea to make a pokedex app in jetpack compose comes form [skydoves pokedex](https://github.com/skydoves/Pokedex), he make his app in views, and i decide to make a fully Compose app, and i learn a lot from him. 

UI design inspiration comes from [dribble](https://dribbble.com/shots/16833947-Mobile-Pokedex-App-Design-Exploration).



## License

```
Pokedex to show Pokemons
Copyright (C) 2022  JeckOnly

This program is free software: you can redistribute it and/or modify
it under the terms of the GNU General Public License as published by
the Free Software Foundation, either version 3 of the License, or
(at your option) any later version.

This program is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
GNU General Public License for more details.

You should have received a copy of the GNU General Public License
along with this program.  If not, see <https://www.gnu.org/licenses/>.
```













