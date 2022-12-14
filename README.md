# evowill-task

## **Contents**

1. [Task](#Task)
2. [Project structure](#Project-structure)
3. [Technologies](#Technologies)
4. [How to run project](#How-to-run-project)
____

## **Task**

Створити консольну аплікацію для контролю витрат.
В аплікації має бути такий функціонал:
1) Можливість записувати витрати по категоріям.
   Приклад: їжа — 250 грн.
2) Отримувати статистику витрат — по всім категоріям одразу або по одній з них.
3) Можливість очистити всі дані.
4) Подивитися статистику витрат: за день, за місяць, за рік.
5) Внести витрати за певний день.
   Приклад: 12.12.2012 їжа — 250.
6) Додати можливість мати кілька користувачів.
7) Написати юніт тести для аплікації.

[:arrow_up:Contents](#Contents)
____

## **Project structure**

### Project built on 3-tier architecture:
1. Data access layer (DAO).
2. Application layer (service).
3. Presentation layer (main).

Tables relation: Many-to-One (Expenditure - User, Expenditure - Category) 

[:arrow_up:Contents](#Contents)
____

## **Technologies**

* MySQL;
* JDBC;
* Hibernate
* Maven;
* Maven Checkstyle Plugin;

[:arrow_up:Contents](#Contents)
____

## **How to run project**

### Tools to run project:
* IntelliJ Idea
* MySQL

1. Clone this project on your IDE
2. Create database
3. Use this [script](src/main/resources/init_db.sql) and add your url to DB, login, password and JDBC driver [here](src/main/resources/hibernate.cfg.xml)

[:arrow_up:Contents](#Contents)
____