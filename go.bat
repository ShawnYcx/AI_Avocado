@echo off

javac phase_1\Phase1.java phase_1\Node.java

if %errorlevel%==0 java -cp "." phase_1.Phase1