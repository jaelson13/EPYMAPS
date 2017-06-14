package br.com.educacao.epymaps.Activitys;

import android.content.ClipData;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;

import br.com.educacao.epymaps.R;

public class HomeActivity extends AppCompatActivity {

    private LinearLayout linearLayout;
    private ImageButton ibStatus;
    private String status = "Doente";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        linearLayout = (LinearLayout) findViewById(R.id.llPerfil);
        ibStatus = (ImageButton) findViewById(R.id.ibStatus);
        ibStatus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//aqui
                if(ibStatus.getDrawable() == R.mipmap.sadio) {
                    ibStatus.setImageResource(R.mipmap.sadio);
                    status = "Sadio";
                }
                //ibCheckDoente.setVisibility(View.VISIBLE);

               // ibCheckSadio.setVisibility(View.INVISIBLE);
            }
        });

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public void setSupportActionBar(@Nullable Toolbar toolbar) {
        super.setSupportActionBar(toolbar);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.itmPerfil:
                linearLayout.setVisibility(LinearLayout.VISIBLE);
                break;
            case R.id.itmSair:
                Intent intent = new Intent(this,PrincipalActivity.class);
                startActivity(intent);
                break;

            case android.R.id.home:
                finish();
        }
        return true;
    }
}
/*
<ImageView
                    android:id="@+id/idCheckDoente"
                    android:layout_width="wrap_content"
                    android:layout_height="wrap_content"
                    android:src="@mipmap/checado"
                    android:visibility="invisible"
                    android:alpha="0.7"
                    />


                <ImageButton
                    android:id="@+id/ibSaude"
                    android:layout_width="wrap_content"
                    android:layout_height="wrap_content"
                    android:layout_marginLeft="75dp"
                    android:background="@android:color/transparent"
                    android:src="@mipmap/sadio" />

                <ImageView
                    android:id="@+id/idCheckSaude"
                    android:layout_width="wrap_content"
                    android:layout_height="wrap_content"
                    android:layout_alignRight="@+id/ibSaude"
                    android:src="@mipmap/checado"
                    android:alpha="0.7"
                    android:visibility="invisible" />
 */