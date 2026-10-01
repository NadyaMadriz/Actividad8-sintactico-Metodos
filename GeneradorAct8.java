package act8_s_metodos;

public class GeneradorAct8 {
    public static void main(String[] args) {
        try {
            jflex.Main.main(new String[] { "--nobak", "act8_s_metodos/Lexer8.jflex" });
            System.out.println("Lexer8 generado con exito.");
        } catch (Exception e) {
            System.err.println("Error JFlex: " + e.getMessage());
            System.exit(1);
            return;
        }

        try {
            java_cup.Main.main(new String[] {
                "-destdir", "act8_s_metodos",
                "-parser", "Parser8",
                "-symbols", "sym",
                "act8_s_metodos/parser8.cup"
            });
            System.out.println("Parser8 y sym generados con exito.");
        } catch (Exception e) {
            System.err.println("Error CUP: " + e.getMessage());
            System.exit(1);
        }
    }
}
