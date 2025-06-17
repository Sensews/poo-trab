package modelo;

/**
 * REQUISITO 11: Classe derivada de Exception
 * Esta classe representa exceções específicas do sistema Tamagotchi
 */
public class TamagotchiException extends Exception {
      /**
     * Construtor com mensagem de erro
     * @param message mensagem descritiva do erro
     */
    public TamagotchiException(String message) {
        super(message);
    }
    
    /**
     * Construtor com mensagem e causa
     * @param message mensagem descritiva do erro
     * @param cause causa original da exceção
     */
    public TamagotchiException(String message, Throwable cause) {
        super(message, cause);
    }
}
