@ECHO OFF

SETLOCAL
CALL :setvars
SET cmd=%1
IF "%cmd%"=="" SET cmd=all
CALL :%cmd%
GOTO :eof

:setvars
SET name=OrbGrab
SET cc=javac
SET cflags=-g:none -Xlint:deprecation
SET src=src
SET lib=lib
SET res=resources
SET out=bin
SET dist=%name%.jar
SET lstf=temp.txt
SET imgdir=img
SET manifest=Manifest.txt
SET versionfile=Version.txt
FOR /F %%G IN (%versionfile%) DO SET version=%%G
CALL "%res%\FindJDK.bat"
GOTO :eof

:all
ECHO Compiling game
CALL :Compile
ECHO Packing JAR
CALL :pack
CALL :end
GOTO :eof

:Compile
IF EXIST "%lstf%" DEL /F /Q "%lstf%"
FOR /F "usebackq tokens=*" %%G IN (`DIR /B /S "%src%\*.java"`) DO CALL :append "%%G"
IF EXIST "%out%" RMDIR /S /Q "%out%" > NUL
MKDIR "%out%"
"%cc%" %cflags% -d "%out%" "@%lstf%" 2>NUL
GOTO :eof

:pack
COPY "%manifest%" "%lstf%" > NUL
ECHO Specification-Version: "1.0" >> "%lstf%"
ECHO Implementation-Version: "1.0" >> "%lstf%"
jar cfm "%dist%" "%lstf%" -C bin %versionfile% img models orbgrab.properties
GOTO :eof

:end
ECHO Compilation successful.
GOTO :eof

:append
SET gx=%1
SET gx=%gx:\=\\%
ECHO %gx% >> %lstf%
GOTO :eof

GOTO :eof
