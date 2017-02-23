Testing i.ua website. Automatically rerun failed TC. Maven, TestNG, read data from MySQL. 

How to run:
mvn clean test -Dbrowser=chrome -DtestNG="testNG" site


or 
mvn clean test -Dbrowser=chrome -DtestNG="testNG" -DtestGroup=minor site

SQL:
admin | password

