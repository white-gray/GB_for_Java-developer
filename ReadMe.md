### Урок 5


1. Создать базу данных Student с полями id, name, mark.
2. Создать сущность Student и добавить в нее аннотации. Поле id должно заполняться автоматически.
3. Создать конфигурационный файл hibernate.cfg.xml, в котором указать свойства для подключения к БД и список классов с аннотациями Entity.
4. Создать класс со статическим методом, который возвращает объект SessionFactory.
5. Создать DAO-слой с операциями добавления, удаления, изменения сущности, выборки записи по ID и всех записей.
6. Добавить 1000 записей в таблицу Student.
7. Проверить работоспособность приложения, выполнить методы по удалению, изменению, добавлению записей, а также выборки одной и всех записей.

####п.7 не сработал. Изначально все было нормально. Но потом перестало запускаться: 
`Exception in thread "main" java.lang.NoClassDefFoundError: org/hibernate/service/ServiceRegistry
	at ru.whitegray.LearnMain.main(LearnMain.java:17)
Caused by: java.lang.ClassNotFoundException: org.hibernate.service.ServiceRegistry
at java.base/jdk.internal.loader.BuiltinClassLoader.loadClass(BuiltinClassLoader.java:581)
at java.base/jdk.internal.loader.ClassLoaders$AppClassLoader.loadClass(ClassLoaders.java:178)
at java.base/java.lang.ClassLoader.loadClass(ClassLoader.java:522)`
Не удалось решить. Не хватает времени.
