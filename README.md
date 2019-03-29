# Задание

# Дисклеймер

## Файлы
Тест-кейсы | Значения переменных для Тест-кейсов
------------ | -------------
[TestCases.tsv](TestCases.tsv) | [TestCasesValues.txt](TestCasesValues.txt)


## Артефакты
1. Блок-схема сайта
      - [Авторизация](/TestCasesArtifacts/login.mindnode/QuickLook/Preview.jpg)
      - [Создание заявки](/TestCasesArtifacts/order.mindnode/QuickLook/Preview.jpg)
- [ ] Диаграмма Исикавы

## Багрепорты
* В процессы исследования обнаружены некоторые баги, на них заведены issue:<br>
[label:OK-bugreport](https://github.com/Lagunov-PRO/TT_OK/issues?q=is%3Aissue+is%3Aopen+label%3AOK-bugreport)<br>
* Также обнаружены некоторые логические несоответствия:<br>
[label:OK-enhancement](https://github.com/Lagunov-PRO/TT_OK/issues?q=is%3Aissue+is%3Aopen+label%3AOK-enhancement)
      

## Формат файлы
Файл с тест-кейсами в формате *.tsv
Его преимущества:
1. Нативно отображается прямо на гитхабе
2. Формат текстовой, поэтому дифы и история изменений имеют смысл
3. Открывается и редактируется в Excel

## Именование тест-кейса
Пример: login.02.1.P
* login — имя тестируемого модуля
* 02 — номер тест-кейса
* 1 — номер вариации тест-кейса
* P — позитивный сценарий
* N — негативный сценарий

## Работа с email
Так как тестирование проводится на production, базу захламлять не стоит.
Почему-то столь удобные адреса gmail с "+" вида "user+test@gmail.com" считаются невалидными.
Можно использовать адреса gmail с "." вида u.s.e.r@gmail.com но их ограниченное количество.
Поэтому принято решение все тестовые регистрации проводить на адреса домена lagunov.pro.
Любые письма на несуществующие адреса этого домена по-умолчанию переадресуются на администратору
Там настроена пересылка всех писем с no-reply@opencity.pro на open.kzn.registered@gmail.com

