package modelo;

import java.io.*;
import java.util.List;

public class Persistencia {

    // Método para salvar uma lista de criaturas no arquivo
    public static void salvar(List<CriaturaVirtual> criaturas, String caminho) {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(caminho))) {
            out.writeObject(criaturas); // Salva a lista de criaturas no arquivo
            System.out.println("Criaturas salvas com sucesso.");
        } catch (IOException e) {
            System.err.println("Erro ao salvar: " + e.getMessage());
        }
    }

    // Método para carregar uma lista de criaturas do arquivo
    @SuppressWarnings("unchecked")
    public static List<CriaturaVirtual> carregar(String caminho) {
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(caminho))) {
            return (List<CriaturaVirtual>) in.readObject(); // Retorna a lista de criaturas do arquivo
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Erro ao carregar: " + e.getMessage());
            return null;
        }
    }

    // Método para salvar uma criatura virtual individual no arquivo
    public static void salvarCriatura(CriaturaVirtual criatura, String caminho) {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(caminho))) {
            out.writeObject(criatura); // Salva a criatura no arquivo
            System.out.println("Criatura salva com sucesso.");
        } catch (IOException e) {
            System.err.println("Erro ao salvar a criatura: " + e.getMessage());
        }
    }

    // Método para carregar uma criatura virtual individual do arquivo
    public static CriaturaVirtual carregarCriatura(String caminho) {
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(caminho))) {
            return (CriaturaVirtual) in.readObject(); // Retorna a criatura do arquivo
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Erro ao carregar a criatura: " + e.getMessage());
            return null;
        }
    }
}
