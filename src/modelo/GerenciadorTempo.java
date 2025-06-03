package modelo;

import java.util.Timer;
import java.util.TimerTask;

public class GerenciadorTempo {
    private Timer timer;
    private CriaturaVirtual criatura;
    private boolean ativo;
    
    public GerenciadorTempo(CriaturaVirtual criatura) {
        this.criatura = criatura;
        this.ativo = false;
    }
    
    public void iniciar() {
        if (ativo) return;
        
        ativo = true;
        timer = new Timer(true); // Timer daemon
          // Atualização a cada 10 segundos
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                if (criatura != null && criatura.isVivo()) {
                    // Aplicar efeitos dos upgrades antes da atualização
                    aplicarUpgrades();
                    criatura.atualizacaoTempo();
                }
            }
        }, 10000, 10000); // Primeiro delay de 10s, depois a cada 10s
        
        System.out.println("⏰ Sistema de tempo automático iniciado!");
    }
    
    public void parar() {
        if (timer != null) {
            timer.cancel();
            timer = null;
        }
        ativo = false;
        System.out.println("⏰ Sistema de tempo automático parado!");
    }
    
    public boolean isAtivo() {
        return ativo;
    }
    
    private void aplicarUpgrades() {
        // Aplicar upgrade de Felicidade Eterna
        if (Loja.temFelicidadeEterna(criatura) && criatura.getFelicidade() < 30) {
            criatura.setFelicidade(30);
        }
        
        // Aplicar upgrade de Super Regeneração
        if (Loja.temSuperRegeneracao(criatura)) {
            criatura.setSaude(Math.min(100, criatura.getSaude() + 2));
        }
        
        // Aplicar upgrade de Masterpet (status sempre no máximo)
        if (Loja.temMasterpet(criatura)) {
            criatura.setFome(100);
            criatura.setSede(100);
            criatura.setSono(100);
            criatura.setFelicidade(100);
            criatura.setSaude(100);
        }
    }
}
