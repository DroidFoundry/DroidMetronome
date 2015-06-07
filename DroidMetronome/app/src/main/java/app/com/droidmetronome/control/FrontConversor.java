package app.com.droidmetronome.control;

/**
 * Created by pedro on 16/05/15.
 */

import android.content.Context;

import app.com.droidmetronome.model.Compasso;
import app.com.droidmetronome.model.Figura_Colcheia;
import app.com.droidmetronome.model.Figura_Fusa;
import app.com.droidmetronome.model.Figura_Minima;
import app.com.droidmetronome.model.Figura_SemiBreve;
import app.com.droidmetronome.model.Figura_SemiColcheia;
import app.com.droidmetronome.model.Figura_SemiFusa;
import app.com.droidmetronome.model.Figura_SemiMinima;
import app.com.droidmetronome.model.Sound_8Bits;
import app.com.droidmetronome.model.Sound_beep;
import app.com.droidmetronome.model.Sound_hiHats;
import app.com.droidmetronome.model.Sound_kickClap;
import app.com.droidmetronome.model.Sound_rimShot;
import app.com.droidmetronome.model.TemplateFiguraritmica;
import app.com.droidmetronome.model.TemplateSound;


/**
 * Classe responsável por ligar o front-end ao back-end
 */
public class FrontConversor {

    private TemplateSound sound;

    private long frequenciaBPM;
    private int quantidadeBatidas;
    private int tempoMinutos;

    private boolean vibracao;
    private boolean flash;

    private TemplateFiguraritmica figuraRitmica;

    /**
     * Define o som do metrônomo com base no definido pelo usuário.
     * @param idTipoSom - Id para escolha do som (caso não exista é atribuido um valor padrão).
     * @param context - Contexto aonde o som será exibido.
     */
    public void createSomById(int idTipoSom,Context context) {

        switch(idTipoSom){
            //Som 1:
            case 1:
                sound = new Sound_8Bits(context);
                break;

            //Som 2:
            case 2:
                sound = new Sound_hiHats(context);
                break;

            //Som 3:
            case 3:
                sound = new Sound_kickClap(context);
                break;

            //Som 4:
            case 4:
                sound = new Sound_rimShot(context);
                break;

            //Som 5:
            case 5:
                sound = new Sound_beep(context);
                break;

            //Som padrão
            default:
                sound = new Sound_8Bits(context);

        }
    }

    /**
     * Define a figura rítmica definido pelo usuário.
     * @param idFiguraRitmica - Id para escolha da figura ritmica (caso não exista é atribuido um valor padrão).
     */
    public void createFiguraRitmicaById(int idFiguraRitmica) {

        switch(idFiguraRitmica){

            case 1:
                figuraRitmica = new Figura_SemiBreve();
                break;

            case 2:
                figuraRitmica = new Figura_Minima();
                break;

            case 3:
                figuraRitmica = new Figura_SemiMinima();
                break;

            case 4:
                figuraRitmica = new Figura_Colcheia();
                break;

            case 5:
                figuraRitmica = new Figura_SemiColcheia();
                break;

            case 6:
                figuraRitmica = new Figura_Fusa();
                break;

            case 7:
                figuraRitmica = new Figura_SemiFusa();
                break;

            //Som padrão
            default:
                figuraRitmica = new Figura_SemiBreve();

        }
    }

    /**
     * Permição de vibrar durante a execução
     * @param vibracao - Ativado se verdadeiro ,desativado se falso
     */
    public void setVibracao(boolean vibracao) {
        this.vibracao = vibracao;
    }

    /**
     * Permição de ativar o flash durante a execução
     * @param flash - Ativado se verdadeiro ,desativado se falso
     */
    public void setFlash(boolean flash) {
        this.flash = flash;
    }

    /**
     * Define o tempo de duração em minutos. Caso esteja fora do intervalo é definido um valor padrão.
     * @param tempoMinutos - Tempo em minutos definido pelo usuário.
     */
    public void setTempoMinutos(int tempoMinutos) {
        if((tempoMinutos < 1)||(tempoMinutos > 15)){
            //Valor padrão caso esteja fora dos limites.
            this.tempoMinutos = 1;
        }else {
            this.tempoMinutos = tempoMinutos;
        }
    }

    /**
     * Define a frequencia em BPM's. Caso esteja fora do intervalo é definido um valor padrão.
     * @param frequenciaBPM - Frequencia em BPM definido pelo usuário.
     */
    public void setFrequenciaBPM(long frequenciaBPM) {
        if((frequenciaBPM < 10)||(frequenciaBPM > 300)){
            //Valor padrão caso esteja fora dos limites.
            this.frequenciaBPM = 120;
        }else {
            this.frequenciaBPM = frequenciaBPM;
        }
    }

    /**
     * Define a quantidade máxima de batidas. Caso esteja fora do intervalo é definido um valor padrão.
     * @param quantidadeBatidas - Quantidade máxima de batidas definido pelo usuário.
     */
    public void setQuantidadeBatidas(int quantidadeBatidas) {
        if((quantidadeBatidas < 1)||(quantidadeBatidas > 16)){
            //Valor padrão caso esteja fora dos limites.
            this.quantidadeBatidas = 4;
        }else {
            this.quantidadeBatidas = quantidadeBatidas;
        }
    }

    /**
     * Converte todos os valores obtidos na U.I em um objeto da classe compasso.
     * @return Compasso com os valores definidos anteriormente.
     * @see Compasso
     */
    public Compasso toCompasso(Context context){

        Compasso compasso = new Compasso(context);

        compasso.setVibrating(this.vibracao);
        compasso.setLighting(this.flash);

        compasso.setFrequenciaBPM(this.frequenciaBPM);
        compasso.setFiguraRitmica(this.figuraRitmica);
        compasso.setQuantidadeBatidas(this.quantidadeBatidas);
        compasso.setTempoMinutos(this.tempoMinutos);

        compasso.setSom(this.sound);

        return(compasso);
    }
}
