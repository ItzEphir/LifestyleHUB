# Модуль Domain (java lib)

## Слой бизнес-логики приложения

### Model - использованные модели данных

### Repositories - репозитории, использованные в use case`ах

#### Local - локальные, работающие с базами данных на устройстве

#### Remote - удаленные, работающие с сетью и Http-запросами

### UseCases - сценарии использования:

###### Все классы для вызова используют один operator-метод invoke

#### GetCurrentWeatherUseCase - получает данные о погоде

##### требует WeatherRemoteRepository в конструкторе

---

#### GetRecommendationsUseCase - получает рекомендации для пользователя

##### требует PlacesRemoteRepository в конструкторе

возможно может дополнятся другими рекомендациями

---

#### GetPlaceInfoUseCase - получает и кэширует информацию о месте

##### требует PlaceInfo(Remote&Local)Repository в конструкторе

---

#### GetCurrentUserUseCase - получает текущего пользователя

##### требует UserLocalRepository в конструкторе

---

#### SignInUseCase - вход пользователя

##### требует UserLocalRepository в конструкторе

---

#### SignUpUseCase - регистрация пользователя

##### требует User(Remote&Local)Repository в конструкторе

---

#### GetLeisureUseCase - получает досуг пользователя

##### требует LeisureLocalRepository в конструкторе

---

#### PostLeisureUseCase - добавляет активность в досуг

##### требует LeisureLocalRepository в конструкторе
