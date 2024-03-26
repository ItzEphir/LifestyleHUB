# Features (android libs)

## Фичи приложения:

### Общая схема модулей:

#### UI - весь интерфейс, состоит из:

1. widget - вход в фичу, сам виджет
2. components - компоненты, которые необходимы для виджета

#### Presentation - логика по связи data/domain слоя и фичи, состоит из:

1. Model - модели, необходимые для ui
2. State - состояния виджета
3. Viewmodel - вью-модель виджета, единственная точка взаимодействия с domain/data
4. Mapper - мапперы для представления данных в ui-формате

---

## Current Weather - текущая погода

### Зависимости ViewModel: GetCurrentWeatherUseCase

---

## Recommendations - рекомендации для пользователя

###### Возможное расширение обусловлено созданием sealed class Recommendation, в которые позднее можно добавить не только места

### Зависимости ViewModel: GetRecommendationsUseCase

---

## Place Details - подробности о месте

### Зависимости ViewModel: GetPlaceInfoUseCase

---

## Profile - профиль пользователя

###### У данного экрана нет состояния, так как навигация сделана с помощью Navigation Component, так как все экраны независимы друг от друга и представляют разный функционал

### Зависимости ViewModel: SignInUseCase, SignUpUseCase, GetCurrentUserUseCase

---

## Leisure - досуг пользователя

###### У данного экрана есть экран-шорткат, который переносит сразу на ActivityEditor

### Зависимости ViewModel: GetCurrentUserUseCase, GetLeisureUseCase, PostLeisureUseCase