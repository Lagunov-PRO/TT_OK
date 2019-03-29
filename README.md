# Задание

# Дисклеймер

## Файлы
Тест-кейсы | Значения переменных для Тест-кейсов
------------ | -------------
[TestCases.tsv](TestCases.tsv) | [TestCasesValues.txt](TestCasesValues.txt)

Файл с тест-кейсами в формате *.tsv <sup>[1](https://github.com/Lagunov-PRO/TT_OK/blob/master/README.md#1)</sup>
Его преимущества:
1. Нативно отображается прямо на гитхабе
2. Формат текстовой, поэтому дифы и история изменений имеют смысл
3. Открывается и редактируется в Excel <sup>[2](https://github.com/Lagunov-PRO/TT_OK/blob/master/README.md#2)</sup>


### Колонки
#### Именование тест-кейса
Пример: login.ent.0003.1
* login — имя тестируемого модуля
* ent — имя подмодуля
* 0001 — номер тест-кейса этого подмодуля
* 1 — номер вариации этого тест-кейса
#### Тип тест-кейса
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
Так как тестирование проводится на production, базу захламлять не стоит.
Почему-то столь удобные адреса gmail с "+" вида "user+test@gmail.com" считаются невалидными.
Можно использовать адреса gmail с "." вида u.s.e.r@gmail.com но их ограниченное количество.
Поэтому принято решение все тестовые регистрации проводить на адреса домена lagunov.pro.
Любые письма на несуществующие адреса этого домена по-умолчанию переадресуются на администратора (меня)
Там настроена пересылка всех писем с no-reply@opencity.pro на open.kzn.registered@gmail.com


## Примечания
###### 1
Есть сконвертированный в xls файл [TestCases.xls](TestCases.xls) , но дата загрузки должна совпадать с TestCases.tsv, иначе неактуален
###### 2
      - Нужно сначала открыть Excel
      - Кинуть в него файл, (Если "открыть как", то предлагает импорт с разбиением)
      - ctrl + A, Формат > Автоподбор ширины столбца
            
