# Задание
Существует действующее рабочее приложение open.kzn.ru

1. Необходимо покрыть приложение тест - кейсами, оформив их в excel.
2. Необходимо покрыть приложение автотестами, используя Java + Selenide.
Будет плюсом, если будут прикреплены дополнительные артефакты, которые помогли при выполнении тестового задания.

# Ответ

## Файлы
 
 | id        | Назначение           | Файл  |
| ------------- |-------------| -----|
| 1       | Тест‑кейсы  | [TestCases.tsv](TestCases.tsv) == [TestCases.csv](https://raw.githubusercontent.com/Lagunov-PRO/TT_OK/master/TestCases.csv) |
| 2    | Значения переменных для тест‑кейсов      |   [TestCasesValues.txt](TestCasesValues.txt) |

## Положительные тест-кейсы
1. Регистрация: [RegistrationPositive.java](/src/test/java/ru/open/kzn/autotests/RegistrationPositive.java)
2. Авторизация: [LoginPositive.java](/src/test/java/ru/open/kzn/autotests/LoginPositive.java)
3. Авторизация через сторонние сервисы: [LoginPositiveThirdPartyServices.java](/src/test/java/ru/open/kzn/autotests/LoginPositiveThirdPartyServices.java)
4. Отправка заявки: [SendOrderPositiveValidFields.java](/src/test/java/ru/open/kzn/autotests/SendOrderPositiveValidFields.java)

## Негативные тест-кейсы:
1. Авторизация: [LoginNegative.java](/src/test/java/ru/open/kzn/autotests/LoginNegative.java)
2. Отправка заявки: [SendOrderNegativeEmptyRequiredFields.java](/src/test/java/ru/open/kzn/autotests/SendOrderNegativeEmptyRequiredFields.java)

## Логические тест-кейсы: 
1. Слайдер: [MainPageLogicalSlider.java](/src/test/java/ru/open/kzn/autotests/MainPageLogicalSlider.java)

## Дисклеймер: 
Файл с тест‑кейсами в двух форматах: *.tsv и *.csv: <br>
* *.tsv удобно просматривать прямо на гитхабе <sup>[1](https://github.com/Lagunov-PRO/TT_OK/blob/master/README.md#1)</sup>
* *.csv удобнее открывать в Excel <sup>[2](https://github.com/Lagunov-PRO/TT_OK/blob/master/README.md#1)</sup> <br>
<br>
Преимущество *.tsv и *.сsv перед *.xls в том, что формат текстовой, поэтому дифы и история изменений имеют смысл

### Колонки
#### Именование 	тест‑кейса
Пример: login.ent.0003.1
* login — имя тестируемого модуля
* ent — имя подмодуля
* 0001 — номер 	тест‑кейса этого подмодуля
* 1 — номер вариации этого 	тест‑кейса
#### Тип 	тест‑кейса
* P — позитивный сценарий
* N — негативный сценарий

## Артефакты
1. Блок-схема взаимодействий
      - [Авторизация](/TestCasesArtifacts/login.mindnode/QuickLook/Preview.jpg)
      - [Создание заявки](/TestCasesArtifacts/order.mindnode/QuickLook/Preview.jpg)
- [ ] Диаграмма Исикавы
- [ ] Таблица переходов

## Багрепорты
* В процессы исследования обнаружены некоторые баги, на них заведены issue:<br>
[label:OK-bugreport](https://github.com/Lagunov-PRO/TT_OK/issues?q=is%3Aissue+is%3Aopen+label%3AOK-bugreport)<br>
* Также обнаружены некоторые логические несоответствия:<br>
[label:OK-enhancement](https://github.com/Lagunov-PRO/TT_OK/issues?q=is%3Aissue+is%3Aopen+label%3AOK-enhancement)

## Работа с email
Так как тестирование проводится на production, базу захламлять я не стал.
Почему-то столь удобные адреса gmail с "+" вида "user+test@gmail.com" считаются невалидными.
Можно использовать адреса gmail с "." вида u.s.e.r@gmail.com но их ограниченное количество.
Поэтому принято решение все тестовые регистрации проводить на адреса домена lagunov.pro.
Любые письма на несуществующие адреса этого домена по-умолчанию переадресуются на администратора (меня)
Там настроена пересылка всех писем с no-reply@opencity.pro на open.kzn.registered@gmail.com

## Валидные значения
Первоначально нужно было протестировать положительные сценарии. Однако мне не удалось узнать валидный номер лицевого счёта.
Поэтому был написан тест на Selenide для его получения методом перебора. Прошу простить за лишние 500 обращений к базе.
Однако всё равно я не стал тестировать создание заявки, т.к. мне неизвестно, можно ли её удалить.

## Примечания
###### 1
[https://help.github.com/en/articles/rendering-csv-and-tsv-data](https://help.github.com/en/articles/rendering-csv-and-tsv-data)

###### 2
Файл [TestCases.csv](TestCases.csv), не отображается на гитхабе, <br>
т.к. Excel по-умолчанию разделяет ";", а гитхаб понимает только ","<br>
Если добавить в начало файла sep="," <br>
То начинает разделять по "," , но гитхаб не понимает эту первую строчку



            
