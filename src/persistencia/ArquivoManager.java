// Parte do Diego
// Isso é apenas uma base por agora, o código será arrumado conforme o decorrer do trabalho
package persistencia;

public class ArquivoManager {
  public static List<Tamagotchi> lerPetsCSV(String nomeArquivo) {
        List<Tamagotchi> pets = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(nomeArquivo))) {
            String linha;
            br.readLine(); // Ignora o cabeçalho

            while ((linha = br.readLine()) != null) {
                String[] campos = linha.split(",");
                String tipo = campos[0];
                String nome = campos[1];
                int fome = Integer.parseInt(campos[2]);
                int felicidade = Integer.parseInt(campos[3]);
                int energia = Integer.parseInt(campos[4]);

                Tamagotchi pet = null;
                // 🔧 Adaptar com construtor 
                if (tipo.equalsIgnoreCase("gato")) {
                    pet = new GatoTamagotchi(nome); // Apenas para preencher por agora, implementar resto depois
                } else if (tipo.equalsIgnoreCase("cachorro")) {
                    pet = new CachorroTamagotchi(nome); // Apenas para preencher por agora, implementar resto depois
                }

                if (pet != null) {
                    // 🔧 Adaptar com setters se os atributos forem privados
                    pet.setFome(fome);          
                    pet.setFelicidade(felicidade);
                    pet.setEnergia(energia);
                    pets.add(pet);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return pets;
    }

    public static void salvarPetsCSV(List<Tamagotchi> pets, String nomeArquivo) {
        try (PrintWriter pw = new PrintWriter(new FileWriter(nomeArquivo))) {
            pw.println("tipo,nome,fome,felicidade,energia");
            for (Tamagotchi pet : pets) {
                // 🔧 Adaptar com base ao Tamagotchi 
                String tipo = (pet instanceof GatoTamagotchi) ? "gato" : "cachorro";
                pw.printf("%s,%s,%d,%d,%d\n",
                          tipo,
                          pet.getNome(),          // 🔧 Adaptar com contruotor correto mais tarde
                          pet.getFome(),          // 🔧 Adaptar com contruotor correto mais tarde
                          pet.getFelicidade(),    // 🔧 Adaptar com contruotor correto mais tarde
                          pet.getEnergia());      // 🔧 Adaptar com contruotor correto mais tarde
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

