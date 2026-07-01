@echo off
chcp 65001 > nul
echo [PetLife v8] Maven 打包 Fat JAR...
echo.
mvn clean package
echo.
echo 完成後 JAR 位置：target\PetLife_v8_Ultimate.jar
pause
