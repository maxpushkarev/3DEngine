Task
==============================
Добрый день!
Данная распредеденная система реализует процедуру рейтрейсинга

Итак, папка Task1Build содержит всё, что нужно для запуска.

- port.txt - номер порта, по которому сервер слушает клиентов, а клиенты, в свою очередь, цепляются к серверу, осуществляющему трассировку лучей
- example.xml - файлик со сценкой
- EngineClient.jar - это клиент, который посылает серверу координаты лучей для рэйтрейсинга.
Идентификатор и координаты луча вводятся в клиент прямо с клавиатуры в заданном формате:
rayId;originX,originY,originZ;directionX,directionY,directionZ.
- Engine.jar - это сервер с 3D-движком. Формат ответа: rayId:objectName 
- EngineScene.jar - это сервер с дампом сцены (занимает порт 7654)
В качестве параметра при запуске принимает имя файла с дампом сцены

Порядок запуска:

- запустить из командной строки EngineScene.jar с именем файла сцены (java -jar EngineScene.jar example.xml). 
Сцена являет собой совокупность объектов, каждый из которых состоит из нескольких треугольных полигонов
- запустить движок Engine.jar (java -jar Engine.jar). Он будет говорить клиенту, в какой из игровых объектов на сцене попал заданный клиентом луч.
- после того как движок проинициализирован, можно цеплять к нему клиентов (java -jar EngineClient.jar). Собственно, здесь можно вводить данные.

Пример запроса 1;0,0,0;1,1,1  
Пример ответа: 1:cube (если луч вообще ничего не пересекает, то вместо objectName остается пустая строка)

Решение многопоточное.
Сцена подгружается в движок по http-протоколу.
Клиенты общаются с движком через сокет.
Для оптимизации рейтрейсинга вся сцена разбивается на BSP-дерево. (Оптимальный сплит-анализ реализован)

Скрины:
- http://maxpushkarev.ru/images/3d_1.png
- http://maxpushkarev.ru/images/3d_2.png

Компилил проекты с помошью MAVEN.
Unit-тесты имеются
