@echo off
echo Cleaning logs...
del /q ..\..\logs\*.* 1> nul 2>&1

echo Cleaning generated jsp files in work\...
rmdir ..\..\work /s /q 2> nul

echo Cleaning generated webapp log file...
del /q *.html 2> nul

REM echo Cleaning generated hbm.xml and src entity files and java class files...
REM rmdir WEB-INF\classes\hbms /s /q 2> nul
REM rmdir WEB-INF\src\java\mtgmgr\entity /s /q 2> nul
REM rmdir WEB-INF\classes\mtgmgr /s /q 2> nul