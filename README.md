# Actividad 8: analisis sintactico de metodos

Esta carpeta contiene el analizador sintactico de la Actividad 8 para el
lenguaje tipo C usado en las practicas. Reconoce definiciones de metodos,
parametros, retornos, llamadas como sentencias y llamadas dentro de
expresiones. Tambien valida la cantidad de argumentos de las llamadas a
metodos definidos y trata de recuperarse de errores para continuar el analisis.

El programa realiza analisis lexico y sintactico; no ejecuta el codigo fuente
ni genera codigo maquina.

## Requisitos

- JDK instalado y disponible en `PATH` (`javac` y `java`).
- Ejecutar desde Windows.
- Las bibliotecas JFlex y Java CUP incluidas en la carpeta `lib` de este
  proyecto.

## Ejecucion

Desde la raiz del proyecto, ejecutar:

```bat
src\act8_s_metodos\ejecutar_act8.bat
```

Esto regenera el lexer y el parser a partir de `Lexer8.jflex` y `parser8.cup`,
compila las clases y analiza `prueba_valida.txt`.

Para analizar otro archivo de prueba:

```bat
src\act8_s_metodos\ejecutar_act8.bat prueba_combinada.txt
```

Tambien se puede pasar una ruta completa o relativa al archivo. La clase
principal es `act8_s_metodos.PruebaAct8`; se puede ejecutar desde la raiz del
proyecto o desde `src`.

## Archivos principales

- `Lexer8.jflex`: reglas del analizador lexico.
- `parser8.cup`: gramatica CUP, diagnosticos, recuperacion de errores y
  validacion de cantidad de argumentos.
- `Lexer8.java`, `Parser8.java` y `sym.java`: fuentes generadas por JFlex y
  CUP.
- `PruebaAct8.java`: punto de entrada para analizar archivos.
- `prueba_*.txt`: ejemplos validos e invalidos, incluidas llamadas anidadas,
  estructuras de control, errores lexicos y recuperacion de errores.

La salida informa los errores sintacticos con linea y columna y distingue las
inconsistencias semanticas de cantidad de argumentos o retorno.
