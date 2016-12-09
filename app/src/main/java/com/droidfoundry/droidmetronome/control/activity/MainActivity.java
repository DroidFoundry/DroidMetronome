package com.droidfoundry.droidmetronome.control.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;

import com.droidfoundry.droidmetronome.R;
import com.droidfoundry.droidmetronome.control.CampoVazioException;
import com.droidfoundry.droidmetronome.control.ClickListenerModel;
import com.droidfoundry.droidmetronome.control.Compasso;
import com.droidfoundry.droidmetronome.model.UserInterface;


import org.greenrobot.eventbus.EventBus;



public class MainActivity extends AppCompatActivity {

    private boolean inExecution;

    private int idSom = 1;
    private int idBit8 = 1;
    private int idHihats = 2;
    private int idKickClap = 3;
    private int idRimshot = 4;
    private int idBeep = 5;

    private Spinner spinnerSons;
    private SeekBar seekBarTimer, seekBarBatidas , seekBarBase;
    private NumberPicker npQntBatidas;
    private NumberPicker npValorBase;
    private EditText textBpm;
    private FloatingActionButton buttonPlay, buttonStop;
    private TextView valorTimer, valorBatidas, valorBase;
    private Toolbar mainToolbar;



    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */

    static AppCompatActivity getActivity() {
        return getActivity();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.mountInterface();

        inExecution = false;

    }

    /**
     * Retorna o id do som escolhido pelo usuário
     */
    public int getIdSom() {
        return idSom;
    }

    /**
     * Seta o id do som escolhido pelo usuário como Bit8.
     *
     * @param view
     */
    public void setIdBit8(View view) {
        this.idSom = idBit8;
    }

    /**
     * Seta o id do som escolhido pelo usuário como idHihats.
     *
     * @param view
     */
    public void setIdHihats(View view) {
        this.idSom = idHihats;
    }

    /**
     * Seta o id do som escolhido pelo usuário como kickclap.
     *
     * @param view
     */
    public void setIdKickClap(View view) {
        this.idSom = idKickClap;
    }

    /**
     * Seta o id do som escolhido pelo usuário como Rimshot.
     *
     * @param view
     */
    public void setIdRimshot(View view) {
        this.idSom = idRimshot;
    }

    /**
     * Seta o id do som escolhido pelo usuário como Beep.
     *
     * @param view
     */
    public void setIdBeep(View view) {
        this.idSom = idBeep;
    }

    private void mountInterface() {
        mainToolbar = (Toolbar) findViewById(R.id.main_toolbar);
        setSupportActionBar(mainToolbar);

        int defaultTimer = 1;
        int minTimer = 1;
        int maxTimer = 15;

        valorTimer = (TextView) findViewById(R.id.value_timer);
        seekBarTimer = (SeekBar) findViewById(R.id.seek_bar_timer);
        seekBarTimer.setMax(maxTimer);
        seekBarTimer.setProgress(defaultTimer);
        valorTimer.setText(Integer.toString(seekBarTimer.getProgress()));

        ClickListenerModel clickListener = new ClickListenerModel(valorTimer,
                minTimer, maxTimer, getApplicationContext());

        seekBarTimer.setOnSeekBarChangeListener(clickListener.getSeekBarListener());

        int defaultBpm = 120;
        textBpm = (EditText) findViewById(R.id.edt_txt_bpm);
        textBpm.setText(Integer.toString(defaultBpm));

        int defaultBatidas = 4;
        int minBatidas = 1;
        int maxBatidas = 16;

        valorBatidas = (TextView) findViewById(R.id.value_batidas);
        seekBarBatidas = (SeekBar) findViewById(R.id.seek_bar_batidas);
        seekBarBatidas.setMax(maxBatidas);
        seekBarBatidas.setProgress(defaultBatidas);

        valorBatidas.setText(Integer.toString(seekBarBatidas.getProgress()));

        ClickListenerModel clickListenerBatidas = new ClickListenerModel(valorBatidas,
                minBatidas, maxBatidas, getApplicationContext());

        seekBarBatidas.setOnSeekBarChangeListener(clickListenerBatidas.getSeekBarListener());


        int defaultBase = 1;
        int minBase = 1;
        int maxBase = 6;


        valorBase = (TextView) findViewById(R.id.value_base);
        seekBarBase= (SeekBar) findViewById(R.id.seek_bar_valor_base);
        seekBarBase.setMax(maxBase);
        seekBarBase.setProgress(defaultBase);
        valorBase.setText(Integer.toString(seekBarBase.getProgress()));

        ClickListenerModel clickListenerBase = new ClickListenerModel(valorBase,
                minBase, maxBase, getApplicationContext());

        seekBarBase.setOnSeekBarChangeListener(clickListenerBase.getSeekBarListener());



        spinnerSons = (Spinner) findViewById(R.id.spinner_sons);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.sons_array, android.R.layout.simple_spinner_item);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerSons.setAdapter(adapter);

        buttonPlay = (FloatingActionButton) findViewById(R.id.floatingButtonPlay);
        buttonStop = (FloatingActionButton) findViewById(R.id.floatingButtonStop);



    }


    public void onClickPlay(View view) {

        try{
            if (isCampoValido(textBpm)) {
                verificarSpinnerSons(spinnerSons.getSelectedItemPosition());

                executar();

                this.buttonPlay.hide();
                this.buttonStop.show();

            }

        }catch(CampoVazioException e){
            textBpm.setText("120");

            executar();

            this.buttonPlay.hide();
            this.buttonStop.show();
        }

    }

    public void onClickStop(View view) {
        // Is the toggle on?
        this.buttonPlay.show();
        this.buttonStop.hide();
        stopService(new Intent(this, Compasso.class)); // encerrar service executar

    }

    public boolean isCampoValido(EditText editText) throws CampoVazioException{
        final long BPM_MAX = 320;
        final long BPM_DEFAULT = 120;
        final long BPM_MIN = 80;

        boolean isCampoValido = true;
        String textoDoCampo = editText.getText().toString();



        if(TextUtils.isEmpty(textoDoCampo)){
          throw new CampoVazioException();
        }

        long valorDoCampo = Long.parseLong(textoDoCampo);

        if (valorDoCampo > BPM_MAX) {

            editText.setError("Utilize BPM menores que " + BPM_MAX);
            isCampoValido = false;

        } else if (valorDoCampo < BPM_MIN) {

            editText.setError("Utilize BPM maiores que "+ BPM_MIN);
            isCampoValido = false;

        } else {

            isCampoValido = true;
        }

        return isCampoValido;

    }

    public void verificarSpinnerSons(int posicaoItem){
        switch (posicaoItem){
            case 0:
                this.idSom = idBit8;
                break;
            case 1:
                this.idSom = idHihats;
                break;
            case 2:
                this.idSom = idKickClap;
                break;
            case 3:
                this.idSom = idBeep;
                break;
            case 4:
                this.idSom = idRimshot;
                break;
        }
    }

    /**
     * Prepara e executa o metronomo.
     *
     * @param
     */
    public void executar() {

        UserInterface userInterface = new UserInterface();

        SharedPreferences sharedPrefs =
                PreferenceManager.getDefaultSharedPreferences(this.getApplicationContext());
        Boolean vibracao = sharedPrefs.getBoolean(
                getString(R.string.pref_vibracao_key), Boolean.parseBoolean(getString(R.string.pref_vibracao_default)));
        Boolean flash = sharedPrefs.getBoolean(
                getString(R.string.pref_flash_key), Boolean.parseBoolean(getString(R.string.pref_flash_default)));

        userInterface.setVibracao(vibracao);
        userInterface.setFlash(flash);

        userInterface.setTempoMinutos(seekBarTimer.getProgress());
        userInterface.setFrequenciaBPM(Long.parseLong(textBpm.getText().toString()));
        userInterface.setQuantidadeBatidas(seekBarBatidas.getProgress());

        userInterface.createSomById(getIdSom(), this);
        userInterface.createFiguraRitmicaById(seekBarBase.getProgress());

        EventBus.getDefault().postSticky(userInterface);
        startService(new Intent(this,Compasso.class));
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

    @Override
    protected void onDestroy() {
        if (inExecution) {
            stopService(new Intent(this, Compasso.class)); // encerrar service executar
            inExecution = false;

        }

        super.onDestroy();
    }

}
