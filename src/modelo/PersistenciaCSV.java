package modelo;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * REQUISITO 13: Lê dados de arquivo CSV
 * REQUISITO 14: Recupera e salva objetos persistentes
 * Esta classe gerencia a persistência das criaturas em formato CSV
 */
public class PersistenciaCSV {
    private static final String HEADER = "nome,tipo,fome,sede,sono,felicidade,saude,vivo,humor,pontos,doente,tipoDoenca,inventario";
    
    /**
     * REQUISITO 11: Uso da classe derivada de Exception
     * REQUISITO 14: Salva objetos persistentes
     * Salva uma criatura virtual em arquivo CSV
     */
    public static void salvarParaCSV(CriaturaVirtual criatura, String caminhoArquivo) throws TamagotchiException {
        try (PrintWriter writer = new PrintWriter(new FileWriter(caminhoArquivo))) {
            // Escrever cabeçalho
            writer.println(HEADER);
            
            // Escrever dados da criatura
            StringBuilder linha = new StringBuilder();
            linha.append(criatura.getNome()).append(",");
            linha.append(criatura.getTipo()).append(",");
            linha.append(criatura.getFome()).append(",");
            linha.append(criatura.getSede()).append(",");
            linha.append(criatura.getSono()).append(",");
            linha.append(criatura.getFelicidade()).append(",");
            linha.append(criatura.getSaude()).append(",");
            linha.append(criatura.isVivo()).append(",");
            linha.append("\"").append(criatura.getHumor()).append("\",");
            linha.append(criatura.getPontos()).append(",");
            linha.append(criatura.isDoente()).append(",");
            linha.append("\"").append(criatura.getTipoDoenca() != null ? criatura.getTipoDoenca() : "").append("\",");
            
            // Serializar inventário
            String inventarioStr = serializarInventario(criatura.getInventario());
            linha.append("\"").append(inventarioStr).append("\"");
            
            writer.println(linha.toString());
            
            System.out.println("Criatura salva com sucesso em: " + caminhoArquivo);
        } catch (IOException e) {
            throw new TamagotchiException("Erro ao salvar criatura no arquivo: " + caminhoArquivo, e);
        }    }
    
    /**
     * REQUISITO 11: Uso da classe derivada de Exception
     * REQUISITO 13: Lê dados de arquivo CSV
     * REQUISITO 14: Recupera objetos persistentes
     * Carrega uma criatura virtual de arquivo CSV
     */
    public static CriaturaVirtual carregarDeCSV(String caminhoArquivo) throws TamagotchiException {
        try (BufferedReader reader = new BufferedReader(new FileReader(caminhoArquivo))) {
            String linha = reader.readLine(); // Pular cabeçalho
            if (linha == null) {
                throw new TamagotchiException("Arquivo CSV vazio: " + caminhoArquivo, null);
            }
            
            linha = reader.readLine(); // Ler dados
            if (linha == null) {
                throw new TamagotchiException("Arquivo CSV não contém dados da criatura: " + caminhoArquivo, null);
            }
            
            return parsearLinha(linha);
            
        } catch (IOException e) {
            throw new TamagotchiException("Erro ao carregar criatura do arquivo: " + caminhoArquivo, e);
        }
    }
      private static CriaturaVirtual parsearLinha(String linha) throws TamagotchiException {
        try {
            // Dividir a linha, mas cuidar com strings que contêm vírgulas
            List<String> campos = new ArrayList<>();
            boolean dentroDaString = false;
            StringBuilder campoAtual = new StringBuilder();
            
            for (int i = 0; i < linha.length(); i++) {
                char c = linha.charAt(i);
                
                if (c == '"') {
                    dentroDaString = !dentroDaString;
                } else if (c == ',' && !dentroDaString) {
                    campos.add(campoAtual.toString().trim());
                    campoAtual = new StringBuilder();
                } else {
                    campoAtual.append(c);
                }
            }            campos.add(campoAtual.toString().trim()); // Adicionar último campo
            
            if (campos.size() < 13) {
                throw new TamagotchiException("Linha CSV inválida: " + linha, null);
            }
            
            String nome = campos.get(0);
            String tipo = campos.get(1);
            int fome = Integer.parseInt(campos.get(2));
            int sede = Integer.parseInt(campos.get(3));
            int sono = Integer.parseInt(campos.get(4));
            int felicidade = Integer.parseInt(campos.get(5));
            int saude = Integer.parseInt(campos.get(6));
            // boolean vivo = Boolean.parseBoolean(campos.get(7)); // Não usado
            // String humor = campos.get(8); // Não usado
            int pontos = Integer.parseInt(campos.get(9));
            // boolean doente = Boolean.parseBoolean(campos.get(10)); // Não usado
            // String tipoDoenca = campos.get(11); // Não usado
            String inventarioStr = campos.get(12);
            
            /**
             * REQUISITO 6: Relação de herança
             * REQUISITO 8: Chamada polimórfica
             * Criação de objetos das subclasses baseado no tipo
             */
            CriaturaVirtual criatura;
            switch (tipo.toLowerCase()) {
                case "gato":
                    criatura = new Gato(nome);
                    break;
                case "cachorro":
                    criatura = new Cachorro(nome);
                    break;                case "peixe":
                    criatura = new Peixe(nome);
                    break;
                default:
                    throw new TamagotchiException("Tipo de criatura desconhecido: " + tipo, null);
            }
            
            // Restaurar estado
            criatura.setFome(fome);
            criatura.setSede(sede);
            criatura.setSono(sono);
            criatura.setFelicidade(felicidade);
            criatura.setSaude(saude);
            criatura.ganharPontos(pontos - criatura.getPontos()); // Ajustar pontos
            
            /**
             * REQUISITO 9: Relação de associação
             * Restaurar inventário (associação entre Criatura e Inventario)
             */
            restaurarInventario(criatura.getInventario(), inventarioStr);
            
            return criatura;
            
        } catch (Exception e) {
            throw new TamagotchiException("Erro ao parsear linha CSV: " + e.getMessage(), e);
        }
    }
    
    private static String serializarInventario(Inventario inventario) {
        StringBuilder sb = new StringBuilder();
        List<ItemComida> comidas = inventario.getComidas();
        
        for (int i = 0; i < comidas.size(); i++) {
            ItemComida comida = comidas.get(i);
            if (i > 0) sb.append(";");
            
            sb.append(comida.getNome()).append("|")
              .append(comida.getPreco()).append("|")
              .append(comida.getEfeitoFome()).append("|")
              .append(comida.getEfeitoSede()).append("|")
              .append(comida.getEfeitoFelicidade()).append("|")
              .append(comida.getEfeitoSaude()).append("|")
              .append(comida.getDescricao());
        }
        
        return sb.toString();
    }
    
    private static void restaurarInventario(Inventario inventario, String inventarioStr) {
        if (inventarioStr == null || inventarioStr.trim().isEmpty()) {
            return;
        }
        
        String[] itens = inventarioStr.split(";");
        
        for (String item : itens) {
            if (item.trim().isEmpty()) continue;
            
            String[] partes = item.split("\\|");
            if (partes.length >= 7) {
                try {
                    String nome = partes[0];
                    int preco = Integer.parseInt(partes[1]);
                    int efeitoFome = Integer.parseInt(partes[2]);
                    int efeitoSede = Integer.parseInt(partes[3]);
                    int efeitoFelicidade = Integer.parseInt(partes[4]);
                    int efeitoSaude = Integer.parseInt(partes[5]);
                    String descricao = partes[6];
                    
                    ItemComida comida = new ItemComida(nome, preco, efeitoFome, efeitoSede, efeitoFelicidade, efeitoSaude, descricao);
                    inventario.adicionarComida(comida);
                } catch (NumberFormatException e) {
                    System.err.println("Erro ao restaurar item do inventário: " + item);
                }
            }
        }
    }
}
