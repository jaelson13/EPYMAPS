package br.com.educacao.epymaps.DAO;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;

import java.util.ArrayList;

import br.com.educacao.epymaps.Activitys.CadastroActivity;
import br.com.educacao.epymaps.Model.Usuario;

/**
 * Created by Jaelson on 25/04/2017.
 */

public class UsuarioDAO extends GenericDAO<Usuario> implements DAO<Usuario> {

    private SQLiteDatabase database;
    private Context context;

    public UsuarioDAO(Context context) {
        super(context);
        database = getWritableDatabase();
        this.context = context;
    }

    @Override
    public boolean salvar(Usuario usuario) {
        database.execSQL("INSERT INTO usuario(nome,email,senha,sexo,cidade,estado,status) VALUES(?,?,?,?,?,?,?)",
                new Object[]{usuario.getNome(), usuario.getEmail(), usuario.getSenha(), usuario.getSexo(), usuario.getCidade(), usuario.getEstado(), true});
        Toast.makeText(context, "Usuario Cadastrado", Toast.LENGTH_LONG).show();
        return true;
    }

    public ArrayList<String> getMunicipios(String estado) {
        Cursor c = database.rawQuery("SELECT uf FROM estado WHERE nome='" + estado + "'", null);
        c.moveToFirst();
        String valor = c.getString(c.getColumnIndex("uf"));
        c = database.rawQuery("SELECT nome FROM municipio WHERE uf='" + valor + "'", null);
        ArrayList<String> dados = new ArrayList<>();
        c.moveToFirst();
        do {
            dados.add(c.getString(c.getColumnIndex("nome")));
        } while (c.moveToNext());
        return dados;
    }

    public ArrayList<String> getEstados() {
        Cursor c = database.rawQuery("SELECT nome FROM estado", null);
        ArrayList<String> dados = new ArrayList<>();
        c.moveToFirst();
        do {
            dados.add(c.getString(c.getColumnIndex("nome")));
        } while (c.moveToNext());
        return dados;
    }


    public boolean verificarEmail(String email) {
        Cursor c = database.rawQuery("SELECT email FROM usuario WHERE email='" + email + "'", null);
        c.moveToFirst();

        if (c.getCount() == 1) {
            return false;
        } else {
            return true;
        }
    }

    public void logar(String email, String senha) {
        Cursor c = database.rawQuery("SELECT email,senha,status FROM usuario WHERE email='" + email + "' AND senha='" + senha + "'", null);
        c.moveToFirst();

        if (c.getCount() == 1) {
            if (c.getInt(c.getColumnIndex("status")) == 1) {
                Toast.makeText(context, "Usuario Logado", Toast.LENGTH_LONG).show();
            } else if (c.getInt(c.getColumnIndex("status")) == 0) {
                Toast.makeText(context, "Usuario Desativado", Toast.LENGTH_LONG).show();
            }
        } else {
            Toast.makeText(context, "Usuario ou Senha invalidos", Toast.LENGTH_LONG).show();

        }
    }

    public void desativarUsuario(String email){
            Cursor c = database.rawQuery("SELECT email FROM usuario WHERE email='"+email+"'",null);

            if(c.getCount()==1) {
                database.execSQL("UPDATE usuario SET status='"+0+"' WHERE email=?", new Object[]{email});
                Toast.makeText(context, "O usuario foi desativado", Toast.LENGTH_LONG).show();
            }else{
                Toast.makeText(context, "Usuario Inexistente", Toast.LENGTH_LONG).show();
            }


    }

    public void ativarUsuario(String email){
        Cursor c = database.rawQuery("SELECT email FROM usuario WHERE email='"+email+"'",null);

        if(c.getCount()==1) {
            database.execSQL("UPDATE usuario SET status='"+1+"' WHERE email=?", new Object[]{email});
            Toast.makeText(context, "O usuario foi ativado", Toast.LENGTH_LONG).show();
        }else{
            Toast.makeText(context, "Usuario Inexistente", Toast.LENGTH_LONG).show();
        }

    }

    @Override
    public boolean filtrar(Usuario usuario) {
        return false;
    }

    @Override
    public boolean atualiza(Usuario usuario) {
        return false;
    }
}
