@echo off

set arg1=%1
set flag=0

if not exist .\%arg1% (
	echo "Folder does not exist."
	goto NODIR
)

javac %arg1%\*.java

if %errorlevel%==0 java -cp "." %arg1%.Phase%arg1:~-1%

:NODIR