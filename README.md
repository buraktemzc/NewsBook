# NewsBookApp

<img align="left" src="https://github.com/buraktemzc/NewsBook/blob/dev/assets/home.png" width="200">
<img align="center" src="https://github.com/buraktemzc/NewsBook/blob/dev/assets/detail.png" width="200">

In this project, I aim to make a news book application running on an android device. Firstly, fetch
data from https://jsonplaceholder.typicode.com/posts endpoint and then all news
data are saved to database for providing single source of truth principle. User can able to change
title and body or remove news.

What did I use in this project?

- I used multi module Clean Architecture with MVVM using Hilt, Coroutines, Flow, Room,
  Retrofit, ViewBinding, Gson.
  On the other hand, I used JUnit and Truth libraries to verify DAO operations. This application is
  single activity and this activity has
  2 fragments.

#### The app has below modules:

1. **data**: This module contains response model, entities, datasources, DI modules, mappers,
   repository implementations and endpoint interfaces. Database
   unit tests are also in this module.
2. **domain**: This module contains business models, usecases and repositories for sending requests
   / DB operations to fill entities and serve data to presentation layer.
3. **gradle -> libs.versions.toml**: It is version catalogue to organize and control dependencies at one point.
4. **app**: This module has Application class, app icon for app. Navigation control is
   managed from main activity class.
5. **base:data**: Data based common classes.
6. **base:domain**: Domain based common classes.
7. **base:ui**: UI based common classes.
8. **core:model**: Models used for all layers.
9. **core:theme**: The theme used throughout the application and with features.
10. **core:ui**: Custom views, extensions used throughout all application features.
11. **features:detail-api**: Navigation api for detail screen.
12. **features:detail-impl**: Detail screen presentation layer.
13. **features:home-api**: Navigation api for home listing screen.
14. **features:home-impl**: Home listing screen presentation layer.
15. **network:manager**:  Deep level of networking operations are implemented in this module.
16. **network:model**:  Network based classes are in this module.

#### Important for clean build:
- Add this line into your local.properties.

```
api_url = "https://jsonplaceholder.typicode.com/"
```