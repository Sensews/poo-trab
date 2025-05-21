// Parte do Diego
// Isso é apenas uma base por agora, o código será arrumado conforme o decorrer do trabalho
package persistencia;

import java.io.*;
import java.util.*;

public class Persistencia {

    public static void salvar(List<CriaturaVirtual> criaturas, String caminho) {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(caminho))) {
            out.writeObject(criaturas);
            System.out.println("Criaturas salvas com sucesso.");
        } catch (IOException e) {
            System.err.println("Erro ao salvar: " + e.getMessage());
        }
    }

    @SuppressWarnings("unchecked")
    public static List<CriaturaVirtual> carregar(String caminho) {
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(caminho))) {
            return (List<CriaturaVirtual>) in.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Erro ao carregar: " + e.getMessage());
            return new ArrayList<>();
        }
    }
}


