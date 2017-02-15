@echo off

javac phase_2\Phase2.java phase_2\Node.java

if %errorlevel%==0 java -cp "." Phase_2.Phase2