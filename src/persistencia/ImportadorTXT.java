//import java.io.*;
//import java.util.*;
//
//public class ImportadorTXT {
//
//    public static List<CriaturaVirtual> importarDeTXT(String caminhoArquivo) {
//        List<CriaturaVirtual> criaturas = new ArrayList<>();
//
//        try (BufferedReader br = new BufferedReader(new FileReader(caminhoArquivo))) {
//            String linha;
//
//            while ((linha = br.readLine()) != null) {
//                String[] campos = linha.split(",");
//                String nome = campos[1];
//                int idade = Integer.parseInt(campos[2]);
//                int fome = Integer.parseInt(campos[3]);
//                int saude = Integer.parseInt(campos[4]);
//                int energia = Integer.parseInt(campos[5]);
//                int higiene = Integer.parseInt(campos[6]);
//                int felicidade = Integer.parseInt(campos[7]);
//                int afeto = Integer.parseInt(campos[8]);
//                boolean doente = Boolean.parseBoolean(campos[9]);
//
//                // Classe anônima para permitir instanciar uma criatura genérica
//                CriaturaVirtual criatura = new CriaturaVirtual(nome) {
//                    @Override
//                    public void agir() {
//                        // implementação genérica para teste
//                        brincar();
//                    }
//                };
//
//                // Atribuição dos atributos
//                criatura.idade = idade;
//                criatura.fome = fome;
//                criatura.saude = saude;
//                criatura.energia = energia;
//                criatura.higiene = higiene;
//                criatura.felicidade = felicidade;
//                criatura.afeto = afeto;
//                criatura.doente = doente;
//                criatura.atualizarHumor();
//
//                criaturas.add(criatura);
//            }
//
//        } catch (IOException e) {
//            System.err.println("Erro ao ler arquivo: " + e.getMessage());
//        }
//
//        return criaturas;
//    }
//}
