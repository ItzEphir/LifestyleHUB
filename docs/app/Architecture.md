# Модуль App (android lib)

## Основная точка входа в приложение

### Из MainActivity задается основной экран, в котором установлен Bottom App Bar и Navigation Graph

### В NavigationGraph для упрощения используется ScreenProvider (имплементация из AndroidBase модуля), и в зависимости от пути запускается соответствующий из 5 экранов:

* Home - главная страница
* Leisure - страница с досугом пользователя
* Profile, из него можно попасть на экран Choosing, SignIn и SignUp
* Recommendation - подробности о рекомендации
* AddActivityShortcut - переход к добавлению активности в Leisure