package br.com.educacao.epymaps.Activitys;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;

import java.util.Date;
import java.util.List;

import br.com.educacao.epymaps.DAO.FichaDAO;
import br.com.educacao.epymaps.Model.FichaDiaria;
import br.com.educacao.epymaps.R;

public class HomeActivity extends AppCompatActivity {

    private LinearLayout linearLayout;
    private ImageButton ibStatus;
    private String status = "Doente";
    private EditText edtDescricao;
    private Button btnEnviar;
    private ListView lvFichasRespData;
    private ListView lvFichasRespStatus;
    private ListView lvFichasRespDescricao;
    private FichaDAO fichaDAO;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        fichaDAO = new FichaDAO(HomeActivity.this);
        linearLayout = (LinearLayout) findViewById(R.id.llPerfil);
        ibStatus = (ImageButton) findViewById(R.id.ibStatus);
        edtDescricao = (EditText) findViewById(R.id.edtDescricao);
        btnEnviar = (Button) findViewById(R.id.btnEnviar);
        lvFichasRespData = (ListView) findViewById(R.id.lvFichasRespData);
        lvFichasRespStatus = (ListView) findViewById(R.id.lvFichasRespStatus);
        lvFichasRespDescricao = (ListView) findViewById(R.id.lvFichasRespDescricao);

        atualizarSpinner();


        ibStatus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(status == "Doente") {
                    ibStatus.setImageResource(R.mipmap.sadio);
                    status = "Sadio";
                }else{
                    ibStatus.setImageResource(R.mipmap.doente);
                    status = "Doente";
                }

            }
        });

        btnEnviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FichaDiaria fichaDiaria = new FichaDiaria();
                fichaDiaria.setStatusUsuario(status);
                fichaDiaria.setDescricao(edtDescricao.getText().toString());
                fichaDiaria.setData(new Date());

                fichaDAO.salvar(fichaDiaria);
                atualizarSpinner();

            }
        });

    }

    private void atualizarSpinner() {

        List<FichaDiaria> arrayFicha = fichaDAO.listarFichas();
        if(arrayFicha != null) {

            ArrayAdapter<FichaDiaria> adapterFicha = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,arrayFicha);
            lvFichasRespData.setAdapter(adapterFicha);
        }
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
                Intent intent = new Intent(this,TelaLogin.class);
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