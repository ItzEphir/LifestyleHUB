# Архитектура проекта

###### Рассмотрим модули в проекте:

### [App](app/Architecture.md) - точка входа в приложение, здесь находится activity (в проекте используется подход Single Activity)

### [Feature](feature/Architecture.md) - папка с модулями, представляющие из себя конечные фичи приложения

### [Domain](domain/Architecture.md) - модуль с бизнес-логикой, отвечает за запросы от ui к удаленному сервису

### [Data](data/Architecture.md) - модуль с репозиториями, отвечает за работу с данными, их получением и сохранением

### [Api](api/Architecture.md) - папка с модулями, представляющие из себя конкретные api, с которыми работает data

### [Database](database/Architecture.md) - папка с модулями, которые имеют функционал работы с локальными бд

### [Common](common/Architecture.md) - общий модуль с полезными функциями/классами

### [AndroidBase](androidBase/Architecture.md) - модуль с общими зависимостями для android-модулей (по сути common для android-модулей)

### [Auth](auth/Architecture.md) - временный модуль на время, пока бэкенд лежит (задание 4), необходимо перенести на бэкенд и добавить логику

## Зависимости:

1. App: AndroidBase, Feature, Data, Domain
2. Feature: Common, AndroidBase, Domain, Data
3. Domain: Common
4. Data: Common, Domain, Database, Api, Auth
5. Api: Common
6. Database: Common
7. Common: -
8. AndroidBase: -
9. Auth: -