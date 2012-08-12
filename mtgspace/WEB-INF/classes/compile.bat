@echo off
javac -classpath "%~dp0;%~dp0connector.jar;%~dp0servlet.jar" %1
pause