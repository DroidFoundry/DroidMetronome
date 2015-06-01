package app.com.droidmetronome.model;

import android.media.SoundPool;

/**
 * Created by pedro on 12/05/15.
 */
public class AudioPlayer{
    private int somAlto;
    private int somBaixo;

    private SoundPool sound;

    private int batidasMaximo;
    private int batidasAtual;

    private int idSom;

    /**
     * Construtor de AudioPlayer
     * @param sound - Som definido pelo usuário.
     * @param somAlto - Identificador do som Alto.
     * @param somBaixo - Identificador do som Baixo.
     */
    public AudioPlayer(SoundPool sound,int somAlto,int somBaixo){
        this.sound = sound;
        this.somAlto = somAlto;
        this.somBaixo = somBaixo;

        this.batidasAtual = 0;
    }

    /**
     * Define a quantidade máxima de batidas. Caso esteja fora do intervalo é definido um valor padrão.
     * @param batidasMaximo - Quantidade máxima de batidas definido pelo usuário.
     */
    public void setBatidasMaximo(int batidasMaximo) {

        if((batidasMaximo < 1)||(batidasMaximo > 16)) {
            //Valor padrão caso esteja fora dos limites.
            this.batidasMaximo = 4;
        }else{
            this.batidasMaximo = batidasMaximo;
        }
    }

    /**
     * Retorna a quantidade máxima de batidas
     * @return
     */
    public int getBatidasMaximo() {
        return batidasMaximo;
    }

    /**
     * Inicia os sons
     */
    public void play(boolean inLoop){

        if(this.batidasAtual < this.batidasMaximo) {
            idSom = this.sound.play(somAlto, 1, 1, 1, 0, 1);
            if(!inLoop) this.batidasAtual++;

        }else{
            idSom = this.sound.play(somBaixo,1,1,1,0,1);
            if(!inLoop) this.batidasAtual = 1;
        }
    }

    /**
     * Encerra os sons
     */
    public void stop(){
        this.sound.stop(this.idSom);
    }
}