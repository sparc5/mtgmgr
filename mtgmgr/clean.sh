#!/bin/bash
echo Cleaning logs...
rm -R ../../logs/* 2>/dev/null

echo Cleaning generated jsp files in work\...
rm -R ../../work 2>/dev/null

echo Cleaning generated webapp log file...
rm *.html 2>/dev/null

# echo Cleaning generated hbm.xml and src entity files and java class files...
# rm -R WEB-INF/classes/hbms 2>/dev/null
# rm -R WEB-INF/src/java/mtgmgr/entity 2>/dev/null
# rm -R WEB-INF/classes/mtgmgr 2>/dev/null
