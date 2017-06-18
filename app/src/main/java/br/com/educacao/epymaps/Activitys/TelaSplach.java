package br.com.educacao.epymaps.Activitys;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;

import br.com.educacao.epymaps.R;

/**
 * Created by bruno on 18/06/17.
 */

public class TelaSplach extends Activity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tela_splach);

        Thread tempothread = new Thread(){
            @Override
            public void run() {
                try{
                    sleep(2500);
                }catch (InterruptedException e){
                    e.printStackTrace();
                }finally {
                    Intent intent = new Intent(TelaSplach.this, TelaLogin.class);
                    startActivity(intent);
                }
            }

        };
        tempothread.start();
    }

    @Override
    protected void onPause() {
        super.onPause();
        finish();
    }
}
