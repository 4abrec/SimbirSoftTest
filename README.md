# SimbirSoftTest
После запуска программы, необходимо будет ввести с консоли ссылку на веб-страницу. После этого, если ссылка введена корректна, страница будет сохранена 
по пути src/main/files/page.html. Далее будет произведен подсчет слов и вывод в консоль, в соответствии с условием. Также, при первом запуске, в корне будет создана 
база данных, куда и запишется статистика. При дальнейших запусках программы, page.html будет перезаписываться, также как и записи в базе данных. Также, если при 
использовании будут возникать ошибки, будет создан файл логирования по пути src/main/resources/log/application.log, в который будут записываться ошибки. Эти ошибки 
также можно будет увидеть в консоли.
Класс DownloadPage нужен для скачивания страницы на жесткий диск.
Класс GetStatistics - для разделения слов и подсчета уникальных. Для более честного подсчета все слова сделаны заглавными буквами.
Класс DataBase предназначен для создания базы данных и записи в нее статисктики.
Класс Main служит для запуска программы.
Также реализованы тесты JUnit. В классе GetStatisticsTest находятся 2 теста: первый проверяет правильность подсчета слов (была выбрана страница, на которой количество слов 
легко посчитать вручную), а второй - сохранились ли слова в HashMap. В классе DownloadPageTest находится тест, проверяющий, не пустой ли оказался документ, в который 
записывалась HTML страница.
