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
}
