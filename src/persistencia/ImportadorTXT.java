import java.io.*;
import java.util.*;
import modelo.CriaturaVirtual;
import modelo.Gato;
import modelo.Peixe;
import modelo.Cachorro;

public class ImportadorTXT {

    public static List<CriaturaVirtual> importarDeTXT(String caminhoArquivo) {
        List<CriaturaVirtual> criaturas = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(caminhoArquivo))) {
            String linha;
            br.readLine(); // Ignora o cabeçalho

            while ((linha = br.readLine()) != null) {
                String[] campos = linha.split(",");

                String tipo = campos[0].trim().toLowerCase();
                String nome = campos[1];
                int fome = Integer.parseInt(campos[2]);
                int sede = Integer.parseInt(campos[3]);
                int sono = Integer.parseInt(campos[4]);
                int felicidade = Integer.parseInt(campos[5]);
                int saude = Integer.parseInt(campos[6]);
                int pontos = Integer.parseInt(campos[7]);
                boolean doente = Boolean.parseBoolean(campos[8]);
                String tipoDoenca = campos[9];

                CriaturaVirtual criatura = switch (tipo) {
                    case "gato" -> new Gato(nome);
                    case "peixe" -> new Peixe(nome);
                    case "cachorro" -> new Cachorro(nome);
                    default -> {
                        System.err.println("❌ Tipo desconhecido: " + tipo);
                        continue;
                    }
                };

                // Usa os setters para manter encapsulamento e lógica de humor
                criatura.setFome(fome);
                criatura.setSede(sede);
                criatura.setSono(sono);
                criatura.setFelicidade(felicidade);
                criatura.setSaude(saude);
                criatura.setPontos(pontos);

                if (doente) {
                    // Força o estado de doença (acesso direto se necessário)
                    criatura.doente = true;
                    criatura.tipoDoenca = tipoDoenca;
                }

                criatura.atualizarHumor();
                criaturas.add(criatura);
            }

        } catch (IOException | NumberFormatException e) {
            System.err.println("Erro ao ler o arquivo: " + e.getMessage());
        }

        return criaturas;
    }
}
