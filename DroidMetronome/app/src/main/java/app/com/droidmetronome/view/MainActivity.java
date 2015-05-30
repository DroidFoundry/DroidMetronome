package app.com.droidmetronome.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.NumberPicker;
import android.widget.Toast;
import android.support.v7.widget.Toolbar;

import app.com.droidmetronome.R;
import app.com.droidmetronome.control.Executer;
import app.com.droidmetronome.control.FrontConversor;


public class MainActivity extends ActionBarActivity {
    private Executer executer;
    private boolean inExecution;
    Toolbar toolbar;
    ImageButton FAB;

    private NumberPicker npBPM;
    private NumberPicker npQntBatidas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        npBPM = (NumberPicker) findViewById(R.id.bpm);
        npBPM.setMinValue(10);
        npBPM.setMaxValue(300);
        npBPM.setValue(120); //Padrão

        npQntBatidas = (NumberPicker) findViewById(R.id.qntBatidas);
        npQntBatidas.setMinValue(1);
        npQntBatidas.setMaxValue(16);
        npQntBatidas.setValue(4); // Padrão

        executer =  new Executer();
        inExecution = false;

       /* toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FAB = (ImageButton) findViewById(R.id.imageButton);
        FAB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Toast.makeText(MainActivity.this,"Hello Worl",Toast.LENGTH_SHORT).show();


            }
        });*/
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
            startActivity(new Intent(this, ConfiguracoesActivity.class));
            return true;
        }


        return super.onOptionsItemSelected(item);
    }
    public void executar(View view){
        if(!inExecution) {

            FrontConversor conversor = new FrontConversor();
            conversor.setSom(this);
            conversor.setFrequenciaBPM(npBPM);
            conversor.setQuantidadeBatidas(npQntBatidas);

            executer.preExecuter(this,conversor.toCompasso()); // preparar
            executer.onExecuter(); // executar

            inExecution = true;

        }else{
            Toast mensagem = Toast.makeText(this, "Metronomo em execução.", Toast.LENGTH_SHORT);
            mensagem.show();
        }
    }
    public void parar(View view){
        if(inExecution){
            executer.stopExecuter();
            inExecution = false;

        }else {
            Toast mensagem = Toast.makeText(this, "Todos os sons foram encerrados.", Toast.LENGTH_SHORT);
            mensagem.show();
        }
    }
}
