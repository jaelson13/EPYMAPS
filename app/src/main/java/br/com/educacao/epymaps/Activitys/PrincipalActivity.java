package br.com.educacao.epymaps.Activitys;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import br.com.educacao.epymaps.DAO.UsuarioDAO;
import br.com.educacao.epymaps.R;

public class PrincipalActivity extends AppCompatActivity {

    private Button btnNovaConta;
    private Button btnLogar;
    private Button btnDesativar;
    private Button btnAtivar;
    private EditText edtEmail;
    private EditText edtSenha;
    UsuarioDAO usuarioDAO;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);

        btnNovaConta = (Button) findViewById(R.id.btnNovaConta);
        btnLogar = (Button) findViewById(R.id.btnLogar);
        btnDesativar = (Button) findViewById(R.id.btnDesativar);
        btnAtivar = (Button) findViewById(R.id.btnAtivar);
        edtEmail = (EditText) findViewById(R.id.edtEmail);
        edtSenha = (EditText) findViewById(R.id.edtSenha);
        usuarioDAO = new UsuarioDAO(PrincipalActivity.this);

        btnNovaConta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                Intent intent = new Intent(PrincipalActivity.this, CadastroActivity.class);
                startActivity(intent);
            }
        });

        btnLogar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(validarCampos()) {
                    if (usuarioDAO.logar(edtEmail.getText().toString(), edtSenha.getText().toString())) {
                        finish();
                        Intent intent = new Intent(PrincipalActivity.this, HomeActivity.class);
                        startActivity(intent);
                    }
                }
            }
        });

        btnAtivar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                usuarioDAO.ativarUsuario(edtEmail.getText().toString());
            }
        });

        btnDesativar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                usuarioDAO.desativarUsuario(edtEmail.getText().toString());
            }
        });

    }

    private boolean validarCampos() {
        if (TextUtils.isEmpty(edtEmail.getText().toString())) {
            edtEmail.setError("Preecha o email");
            return false;
        }

        if (TextUtils.isEmpty(edtSenha.getText().toString())) {
            edtSenha.setError("Preecha a senha");
            return false;
        }

        return true;
    }
}
