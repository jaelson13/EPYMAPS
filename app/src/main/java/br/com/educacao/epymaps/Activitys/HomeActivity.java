package br.com.educacao.epymaps.Activitys;

import android.content.ClipData;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;

import br.com.educacao.epymaps.R;

public class HomeActivity extends AppCompatActivity {

    private LinearLayout linearLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        linearLayout = (LinearLayout) findViewById(R.id.llPerfil);

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
            case R.id.itmSair:
                Intent intent = new Intent(this,PrincipalActivity.class);
                startActivity(intent);

        }
        return true;
    }
}
