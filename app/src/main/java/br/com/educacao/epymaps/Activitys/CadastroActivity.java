package br.com.educacao.epymaps.Activitys;

import android.content.Intent;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import br.com.educacao.epymaps.DAO.UsuarioDAO;
import br.com.educacao.epymaps.Model.Usuario;
import br.com.educacao.epymaps.R;

public class CadastroActivity extends AppCompatActivity {

    private EditText edtNome;
    private EditText edtSobrenome;
    private EditText edtEmail;
    private EditText edtSenha;
    private Spinner spCidades;
    private Spinner spEstados;
    private RadioGroup radioGenero;
    private RadioButton rbFeminino;
    private RadioButton rbMasculino;
    private Button btnCadastrar;
    private UsuarioDAO usuarioDAO;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);


        usuarioDAO = new UsuarioDAO(CadastroActivity.this);
        edtNome = (EditText) findViewById(R.id.edtNome);
        edtSobrenome = (EditText) findViewById(R.id.edtSobrenome);
        edtEmail = (EditText) findViewById(R.id.edtEmail);
        edtSenha = (EditText) findViewById(R.id.edtSenha);
        btnCadastrar = (Button) findViewById(R.id.btnCadastrar);
        spCidades = (Spinner) findViewById(R.id.spCidades);
        spEstados = (Spinner) findViewById(R.id.spEstados);
        rbFeminino = (RadioButton) findViewById(R.id.rbFeminino);
        rbMasculino = (RadioButton) findViewById(R.id.rbMasculino);
        radioGenero = (RadioGroup) findViewById(R.id.radioGenero)

        ;
        ArrayList<String> arrayEstados = usuarioDAO.getEstados();
        ArrayAdapter aadEstados = new ArrayAdapter(this, android.R.layout.simple_list_item_1, arrayEstados);
        spEstados.setAdapter(aadEstados);

        spEstados.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                ArrayList<String> arrayCidades = usuarioDAO.getMunicipios(spEstados.getSelectedItem().toString());
                ArrayAdapter aadCidades = new ArrayAdapter(CadastroActivity.this, android.R.layout.simple_list_item_1, arrayCidades);
                spCidades.setAdapter(aadCidades);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        btnCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (validarCampos()) {
                    Usuario usuario = new Usuario();
                    usuario.setNome(edtNome.getText().toString() + " " + edtSobrenome.getText().toString());
                    usuario.setEmail(edtEmail.getText().toString());
                    usuario.setSenha(edtSenha.getText().toString());
                    usuario.setCidade(spCidades.getSelectedItem().toString());
                    usuario.setEstado(spEstados.getSelectedItem().toString());
                    if (rbFeminino.isChecked()) {
                        usuario.setSexo(rbFeminino.getText().toString());
                    } else {
                        usuario.setSexo(rbMasculino.getText().toString());
                    }

                    if (usuarioDAO.verificarEmailBanco(usuario.getEmail())) {
                        if (usuarioDAO.salvar(usuario)) {
                            finish();
                            Intent intent = new Intent(CadastroActivity.this, PrincipalActivity.class);
                            startActivity(intent);
                        }
                    } else {
                        edtEmail.setError("Email já existe!");
                    }
                }
            }
        });
    }

    private boolean validarCampos() {
        if (TextUtils.isEmpty(edtNome.getText().toString())) {
            edtNome.setError("Preecha este campo");
            return false;
        }

        if (TextUtils.isEmpty(edtSobrenome.getText().toString())) {
            edtSobrenome.setError("Preecha este campo");
            return false;
        }

        if (TextUtils.isEmpty(edtEmail.getText().toString())) {
            edtEmail.setError("Preecha este campo");
            return false;
        }

        if (TextUtils.isEmpty(edtSenha.getText().toString())) {
            edtSenha.setError("Preecha este campo");
            return false;
        }

        if (TextUtils.isEmpty(spEstados.toString())) {
            edtEmail.setError("Preecha este campo");
            return false;
        }

        if(!validarEmail(edtEmail.getText().toString())){
            edtEmail.setError("E-mail inválido");
            return false;
        }

        if (TextUtils.isEmpty(spCidades.toString())) {
            edtEmail.setError("Preecha este campo");
            return false;
        }

        if (!rbFeminino.isChecked() && !rbMasculino.isChecked()) {
            rbFeminino.setError("Seleciona o sexo");
            rbMasculino.setError("Seleciona o sexo");
            return false;
        }
        return true;
    }

    private boolean validarEmail(String email) {
            Pattern pattern;
            Matcher matcher;
            String EMAIL_PATTERN = "^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
            pattern = Pattern.compile(EMAIL_PATTERN);
            matcher = pattern.matcher(email);
            return matcher.matches();
    }
}
