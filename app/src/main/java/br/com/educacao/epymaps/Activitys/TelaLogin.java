package br.com.educacao.epymaps.Activitys;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import br.com.educacao.epymaps.DAO.UsuarioDAO;
import br.com.educacao.epymaps.R;
import com.facebook.FacebookSdk;
import com.facebook.appevents.AppEventsLogger;

public class TelaLogin extends AppCompatActivity {
    private LoginButton lblogar;
    private Button btnNovaConta;
    private Button btnLogar;
    private Button btnRecuperarSenha;
    private EditText edtEmail;
    private EditText edtSenha;
    private TextView tvStatusLogin;
    UsuarioDAO usuarioDAO;

    private CallbackManager callbackManager;//face

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FacebookSdk.sdkInitialize(getApplicationContext());
        setContentView(R.layout.tela_login);

        callbackManager = CallbackManager.Factory.create();//face
        lblogar = (LoginButton) findViewById(R.id.btface);

        btnNovaConta = (Button) findViewById(R.id.btnNovaConta);
        btnLogar = (Button) findViewById(R.id.btnLogar);
        edtEmail = (EditText) findViewById(R.id.edtEmail);
        edtSenha = (EditText) findViewById(R.id.edtSenha);
        tvStatusLogin = (TextView) findViewById(R.id.tvStatusLogin);
        btnRecuperarSenha = (Button) findViewById(R.id.btnRecupSenha);
        edtEmail.getBackground().setAlpha(60);
        edtSenha.getBackground().setAlpha(60);

        usuarioDAO = new UsuarioDAO(TelaLogin.this);

      LoginManager.getInstance().registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                goMainScreen();
            }

            @Override
            public void onCancel() {
              //  Toast.makeText(getApplicationContext(), R.cancel_login, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onError(FacebookException error) {
             //   Toast.makeText(getApplicationContext(),R.string.error_login, Toast.LENGTH_SHORT).show();
            }
        });

        //LoginManager.getInstance().logInWithReadPermissions(this, Arrays.asList("public_profile","user_friends","email"));



        btnNovaConta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                Intent intent = new Intent(TelaLogin.this, TelaCadastro.class);
                startActivity(intent);
            }
        });

        btnRecuperarSenha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                Intent intent = new Intent(TelaLogin.this, TelaRecuperarConta.class);
                startActivity(intent);
            }
        });

        btnLogar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (validarCampos()) {
                    if (usuarioDAO.logar(edtEmail.getText().toString(), edtSenha.getText().toString())) {
                        finish();
                        Intent intent = new Intent(TelaLogin.this, HomeActivity.class);
                        startActivity(intent);
                    }else{
                        tvStatusLogin.setVisibility(View.VISIBLE);
                    }
                }
            }
        });


    }

    private void goMainScreen() {
        Intent intent = new Intent(this, TelaCadastro.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
      //  super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);
    }


}


/*
 private Button btnDesativar;
    private Button btnAtivar;
 btnDesativar = (Button) findViewById(R.id.btnDesativar);
        btnAtivar = (Button) findViewById(R.id.btnAtivar);


        btnDesativar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                usuarioDAO.desativarUsuario(edtEmail.getText().toString());
            }
        });

 */