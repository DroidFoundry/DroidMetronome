package com.example.pedro.metronomo;

import android.app.Activity;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;


public class MainActivity extends Activity {
    private NotaAudio som;
    private Compasso compasso;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void executar(View view){
        SoundPool sound = new SoundPool(4, AudioManager.STREAM_MUSIC,0);

        int id_Alto = sound.load(this,R.raw.beep_2,1);
        Log.d("TIME","Alto: "+String.valueOf(id_Alto));
        int id_Baixo = sound.load(this,R.raw.beep_1,1);
        Log.d("TIME","Baixo: "+String.valueOf(id_Baixo));


            this.compasso = new Compasso();
            this.som = new NotaAudio(sound,id_Alto,id_Baixo);


            //Definindo configurações do metronomo
            compasso.setFrequenciaBPM(999); // Frequencia em BPM
            compasso.setTempoMinutos(1); // Duração em minutos
            compasso.setSom(som); // Som a ser tocado (Alto e baixo)
            compasso.setQuantidadeBatidas(4); // Quantidade de batidas por ciclo
            //====================================

            try {
                compasso.startMetronomo();

            } catch (InterruptedException e) {
                Toast mensagem = Toast.makeText(this, "Ocorreu um erro.", Toast.LENGTH_SHORT);
                mensagem.show();

            }

            Toast mensagem = Toast.makeText(this, "Compasso já inicializado.", Toast.LENGTH_SHORT);
            mensagem.show();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onDestroy() {
        Toast mensagem = Toast.makeText(this,"Destruindo activity.",Toast.LENGTH_SHORT);
        mensagem.show();

        super.onDestroy();
    }
}
