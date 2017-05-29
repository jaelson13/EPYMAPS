package br.com.educacao.epymaps.Activitys;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.Date;

import br.com.educacao.epymaps.DAO.UsuarioDAO;
import br.com.educacao.epymaps.Model.Usuario;
import br.com.educacao.epymaps.R;

public class CadastroActivity extends AppCompatActivity {

    private EditText edtNome;
    private EditText edtSobrenome;
    private EditText edtEmail;
    private EditText edtTelefone;
    private EditText edtSenha;
    private EditText edtDataNasc;
    private EditText edtCidade;
    private EditText edtEstado;
    private Button btnCadastrar;
    Usuario usuario = new Usuario();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);

        edtNome = (EditText) findViewById(R.id.edtNome);
        edtSobrenome = (EditText) findViewById(R.id.edtSobrenome);
        edtEmail = (EditText) findViewById(R.id.edtEmail);
        edtTelefone = (EditText) findViewById(R.id.edtTelefone);
        edtSenha = (EditText) findViewById(R.id.edtSenha);
        edtDataNasc = (EditText) findViewById(R.id.edtDataNasc);
        edtCidade = (EditText) findViewById(R.id.edtCidade);
        edtEstado = (EditText) findViewById(R.id.edtEstado);
        btnCadastrar = (Button) findViewById(R.id.btnCadastrar);



        Date data = new Date();
        usuario.setNome(edtNome.getText().toString());
        usuario.setSobrenome(edtSobrenome.getText().toString());
        usuario.setEmail(edtEmail.getText().toString());
        usuario.setTelefone(edtTelefone.getText().toString());
        usuario.setSenha(edtSenha.getText().toString());
        usuario.setDataNascimento(data);
        usuario.setCidade(edtCidade.getText().toString());
        usuario.setEstado(edtEstado.getText().toString());



        btnCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UsuarioDAO usuarioDAO = new UsuarioDAO(CadastroActivity.this);
                usuarioDAO.salvar(usuario);
            }
        });
    }
}
