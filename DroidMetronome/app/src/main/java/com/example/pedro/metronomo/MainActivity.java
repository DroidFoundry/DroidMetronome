package com.example.pedro.metronomo;

import android.app.Activity;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;


public class MainActivity extends Activity {
    private Executer executer;
    private boolean inExecution;

    private int id1;
    private int id2;
    private SoundPool sound;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        executer =  new Executer();
        inExecution = false;
    }

    /**
     * Prepara e executa o metronomo.
     * @param view
     */
    public void executar(View view){
        if(!inExecution) {
            executer.preExecuter(this); // preparar
            executer.onExecuter(); // executar

            inExecution = true;

        }else{
            Toast mensagem = Toast.makeText(this, "Metronomo em execução.", Toast.LENGTH_SHORT);
            mensagem.show();
        }
    }

    /**
     * Para a execussão do metronomo
     * @param view
     */
    public void parar(View view){
        if(inExecution){
            executer.stopExecuter();
            inExecution = false;

        }else {
            Toast mensagem = Toast.makeText(this, "Todos os sons foram encerrados.", Toast.LENGTH_SHORT);
            mensagem.show();
        }
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
        super.onDestroy();
    }
}