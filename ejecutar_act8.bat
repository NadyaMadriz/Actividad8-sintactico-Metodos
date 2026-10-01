@echo off
setlocal

cd /d "%~dp0..\.."
if errorlevel 1 goto error
cd /d src
if errorlevel 1 goto error
chcp 65001 >nul

javac -encoding UTF-8 -cp "..\lib\*" -d . "act8_s_metodos\GeneradorAct8.java"
if errorlevel 1 goto error

java -cp ".;..\lib\*" act8_s_metodos.GeneradorAct8
if errorlevel 1 goto error
del /q "act8_s_metodos\Lexer8.java~" 2>nul

javac -encoding UTF-8 -cp "..\lib\*" -d . "act8_s_metodos\GeneradorAct8.java" "act8_s_metodos\Lexer8.java" "act8_s_metodos\Parser8.java" "act8_s_metodos\sym.java" "act8_s_metodos\PruebaAct8.java"
if errorlevel 1 goto error

if "%~1"=="" (
    java -Dfile.encoding=UTF-8 -cp ".;..\lib\*" act8_s_metodos.PruebaAct8 "act8_s_metodos\prueba_valida.txt"
) else (
    java -Dfile.encoding=UTF-8 -cp ".;..\lib\*" act8_s_metodos.PruebaAct8 "%~1"
)
if errorlevel 1 goto error
pause
exit /b 0

:error
echo Error al generar, compilar o ejecutar la Actividad 8.
pause
exit /b 1
