# Модуль Data (java lib)

## Модуль работы с данными

### Mappers - мапперы для данных

### Repositories - имплементация репозиториев из domain:

#### Local:

* PlaceInfoLocalRepository - управляет закэшированной информацией о месте (требует PlaceInfoDatastore)
* UserLocalRepository - управляет пользователями (аутентификация, регистрация и нахождение текущего пользователя, если
  он есть) (требует UsersDatastore) 
* LeisureLocalRepository - управляет сохраненным досугом (требует LeisureDatastore)

#### Remote:
* WeatherRemoteRepository - получает текущую погоду (требует WeatherService и GeoService)
* PlacesRemoteRepository - получает рекомендованные места (требует PlaceService и PlacePhotoService)
* PlaceInfoRemoteRepository - получает информацию о месте (трубует PlaceDetailsService и PlacePhotoService)
* UserRemoteRepository - получает информацию о пользователе (требует UserService)

## Файл HttpClient - создание KtorClient для сетевых запросов

