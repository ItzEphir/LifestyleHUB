# Модули Database

## Базовая структура модуля:
### Model - модели данных
### Datastore - абстракция и реализация датасторов
### Файл ModuleDatabase.kt - настройка realm для данной базы данных

### PlaceInfo - кэш информации о месте
#### Datastore - PlaceInfoDatastore

---

### Users - база данных пользователей
###### Частично должна быть пересена на бекенд, [стратегия](UsersStrategy.md)
#### Datastore - UsersDatastore

---

### Leisure - база данных досуга пользователя
#### Datastore - LeisureDatastore
