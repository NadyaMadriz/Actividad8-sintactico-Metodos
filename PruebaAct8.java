package act8_s_metodos;

import java.io.StringReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

public class PruebaAct8 {
    @SuppressWarnings("deprecation")
    public static void main(String[] args) {
        String rutaArchivo = (args.length > 0) ? args[0] : "prueba_valida.txt";

        System.out.println("Cervantes Mora Juan Pablo");
        System.out.println("Muñoz Madriz Nadya Janneth");
        System.out.println("Ramirez Torres Jesus Adrian");

        System.out.println("==============================================");
        System.out.println("       INICIO DE ANÁLISIS SINTÁCTICO (ACT 8)");
        System.out.println("==============================================");

        Path path = resolverArchivo(rutaArchivo);
        if (path == null) {
            System.err.println("No se encontro el archivo de entrada: " + rutaArchivo);
            System.err.println("Ejecute desde la raiz del proyecto o desde src, o proporcione una ruta valida.");
            return;
        }

        Parser8 parser = null;
        boolean analisisCompleto = false;
        try {
            String contenido = Files.readString(path, StandardCharsets.UTF_8);
            System.out.println("Analizando archivo: " + path.toAbsolutePath() + "\n");

            Lexer8 lexer = new Lexer8(new StringReader(contenido));
            parser = new Parser8(lexer, new java_cup.runtime.DefaultSymbolFactory());
            parser.parse();

            analisisCompleto = true;
        } catch (IOException e) {
            System.err.println("No se pudo leer el archivo de entrada: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("El analisis se detuvo por un error fatal: " + e.getMessage());
        }

        if (parser != null) {
            parser.validarLlamadas();
            System.out.println();
            if (analisisCompleto && parser.erroresSintacticos == 0 && parser.erroresLexicos == 0
                    && parser.erroresSemanticos == 0) {
                System.out.println("Resultado: analisis finalizado sin errores.");
            } else {
                System.out.println("Resultado: analisis con " + parser.erroresSintacticos
                    + " error(es) sintactico(s), " + parser.erroresLexicos
                    + " error(es) lexico(s) y " + parser.erroresSemanticos
                    + " error(es) semantico(s).");
            }
        }

        System.out.println();
        System.out.println("Cervantes Mora Juan Pablo");
        System.out.println("Muñoz Madriz Nadya Janneth");
        System.out.println("Ramirez Torres Jesus Adrian");
    }

    private static Path resolverArchivo(String rutaArchivo) {
        Path solicitada = Paths.get(rutaArchivo);
        ArrayList<Path> candidatos = new ArrayList<>();
        candidatos.add(solicitada);
        if (!solicitada.isAbsolute()) {
            candidatos.add(Paths.get("src").resolve(solicitada));
            candidatos.add(Paths.get("src", "act8_s_metodos").resolve(solicitada));
            candidatos.add(Paths.get("act8_s_metodos").resolve(solicitada));
        }

        for (Path candidato : candidatos) {
            if (Files.isRegularFile(candidato)) {
                return candidato;
            }
        }
        return null;
    }
}
