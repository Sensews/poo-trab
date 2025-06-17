package modelo;

import java.io.File;

public class GerenciadorSaves {
    private static final String PASTA_SAVES = System.getProperty("user.dir") + File.separator + "saves";
    
    /**
     * Cria a pasta de saves se ela não existir
     */
    public static void criarPastaSaves() {
        File pasta = new File(PASTA_SAVES);
        if (!pasta.exists()) {
            boolean criada = pasta.mkdirs();
            if (criada) {
                System.out.println("Pasta de saves criada: " + PASTA_SAVES);
            } else {
                System.err.println("Erro ao criar pasta de saves: " + PASTA_SAVES);
            }
        }
    }
    
    /**
     * Retorna o caminho da pasta de saves
     * @return caminho da pasta de saves
     */
    public static String getCaminhoPastaSaves() {
        return PASTA_SAVES;
    }
    
    /**
     * Gera o caminho completo para um arquivo de save
     * @param nomeCriatura nome da criatura
     * @param isAutoSave se é um auto-save
     * @return caminho completo do arquivo
     */
    public static String gerarCaminhoSave(String nomeCriatura, boolean isAutoSave) {
        criarPastaSaves();
        
        String prefixo = isAutoSave ? "autosave_" : "";
        String nomeArquivo = prefixo + "tamagotchi_" + nomeCriatura.toLowerCase().replaceAll("\\s+", "_") + ".csv";
        
        return PASTA_SAVES + File.separator + nomeArquivo;
    }
    
    /**
     * Lista todos os arquivos de save na pasta
     * @return array de arquivos de save
     */
    public static File[] listarSaves() {
        File pasta = new File(PASTA_SAVES);
        if (!pasta.exists()) {
            return new File[0];
        }
        
        return pasta.listFiles((dir, name) -> name.toLowerCase().endsWith(".csv"));
    }
    
    /**
     * Verifica se um arquivo de save existe
     * @param nomeCriatura nome da criatura
     * @return true se o arquivo existe
     */
    public static boolean saveExiste(String nomeCriatura) {
        String caminho = gerarCaminhoSave(nomeCriatura, false);
        return new File(caminho).exists();
    }
}
