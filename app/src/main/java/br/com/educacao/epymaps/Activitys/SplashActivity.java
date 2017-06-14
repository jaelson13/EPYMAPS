package br.com.educacao.epymaps.Activitys;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;

import br.com.educacao.epymaps.R;

public class SplashActivity extends Activity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash);

        Thread tempoThread = new Thread(){
            @Override
            public void run() {
               try {
                   sleep(3000);
               }catch (InterruptedException e){
                   e.printStackTrace();
               } finally {
                   Intent intent = new Intent(SplashActivity.this, PrincipalActivity.class);
                   startActivity(intent);
               }
            }
        };
        tempoThread.start();
    }

    @Override
    protected void onPause() {
        super.onPause();
        finish();
    }
}
