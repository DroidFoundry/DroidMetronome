package app.com.droidmetronome.control;

/**
 * Created by pedro on 16/05/15.
 */

import android.content.Context;
import android.media.AudioManager;
import android.media.SoundPool;

import app.com.droidmetronome.R;
import app.com.droidmetronome.model.AudioPlayer;
import app.com.droidmetronome.model.Compasso;


/**
 * Classe responsável por ligar o front-end ao back-end
 */
public class FrontConversor {
    private AudioPlayer som;
    private long frequenciaBPM;
    private int quantidadeBatidas;
    private int figuraRitmica;
    private int tempoMinutos;

    /**
     * Define o som do metrônomo com base no definido pelo usuário.
     * @param idTipoSom - Id para escolha do som (caso não exista é atribuido um valor padrão).
     * @param context - Contexto aonde o som será exibido.
     */
    public void createSomById(int idTipoSom,Context context) {
        SoundPool sound = new SoundPool(10, AudioManager.STREAM_MUSIC,0);
        int id_Alto = sound.load(context, R.raw.rim_shot_01,1);
        int id_Baixo = sound.load(context,R.raw.rim_shot_02,1);

        switch(idTipoSom){
            //Som 1
            case 1:
                id_Alto = sound.load(context,R.raw.bit8_02,1);
                id_Baixo = sound.load(context,R.raw.bit8_01,1);
                break;

            //Som 2
            case 2:
                id_Alto = sound.load(context,R.raw.hihats_02,1);
                id_Baixo = sound.load(context,R.raw.hihats_01,1);
                break;

            //Som 3:
            case 3:
                id_Alto = sound.load(context,R.raw.kick_clap_02,1);
                id_Baixo = sound.load(context,R.raw.kick_clap_01,1);
                break;

            case 4:
                id_Alto = sound.load(context,R.raw.rim_shot_02,1);
                id_Baixo = sound.load(context,R.raw.rim_shot_01,1);
                break;
            case 5:
                id_Alto = sound.load(context,R.raw.beep_2,1);
                id_Baixo = sound.load(context,R.raw.beep_1,1);
                break;
            //Som padrão
            default:

        }

        this.som = new AudioPlayer(sound,id_Alto,id_Baixo);
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
     * Define a figura rítmica. Caso esteja fora do intervalo é definido um valor padrão.
     * @param figuraRitmica - Figura rítmica definido pelo usuário.
     */
    public void setFiguraRitmica(int figuraRitmica){
        if((figuraRitmica < 1)||(figuraRitmica > 32)){
            //Valor padrão caso esteja fora dos limites.
            this.figuraRitmica = 1;
        }else {
            this.figuraRitmica = figuraRitmica;
        }
    }

    /**
     * Converte todos os valores obtidos na U.I em um objeto da classe compasso.
     * @return Compasso com os valores definidos anteriormente.
     * @see Compasso
     */
    public Compasso toCompasso(){

        Compasso compasso = new Compasso();
        compasso.setFrequenciaBPM(this.frequenciaBPM);
        compasso.setFiguraRitmica(this.figuraRitmica);
        compasso.setSom(this.som);
        compasso.setQuantidadeBatidas(this.quantidadeBatidas);
        compasso.setTempoMinutos(this.tempoMinutos);

        return(compasso);
    }
    //startActivity(new Intent(this, ConfiguracoesActivity.class));
}
