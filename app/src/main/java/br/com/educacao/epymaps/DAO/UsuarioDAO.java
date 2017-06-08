package br.com.educacao.epymaps.DAO;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;

import java.util.ArrayList;

import br.com.educacao.epymaps.Model.Usuario;

/**
 * Created by Jaelson on 25/04/2017.
 */

public class UsuarioDAO extends GenericDAO<Usuario> implements DAO<Usuario>{

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
                new Object[]{usuario.getNome(),usuario.getEmail(),usuario.getSenha(),usuario.getSexo(),usuario.getCidade(),usuario.getEstado(),true});
        Toast.makeText(context,"Usuario Cadastrado",Toast.LENGTH_LONG).show();
        return true;
    }

    public ArrayList<String> getMunicipios(String estado){
        Cursor aux = database.rawQuery("SELECT uf FROM estado WHERE nome='"+estado+"'", null);
        aux.moveToFirst();
        String valor = aux.getString(aux.getColumnIndex("uf"));
        Cursor c = database.rawQuery("SELECT nome FROM municipio WHERE uf='"+valor+"'",null);
        ArrayList<String> dados = new ArrayList<>();
        c.moveToFirst();
        do {
            dados.add(c.getString(c.getColumnIndex("nome")));
        }while(c.moveToNext());
        return dados;
    }

    public ArrayList<String> getEstados(){
        Cursor c = database.rawQuery("SELECT nome FROM estado",null);
        ArrayList<String> dados = new ArrayList<>();
        c.moveToFirst();
        do {
            dados.add(c.getString(c.getColumnIndex("nome")));
        }while(c.moveToNext());
        return dados;
    }


    public boolean verificarEmail(String email){
        Cursor c = database.rawQuery("SELECT email FROM usuario WHERE email='"+email+"'",null);
        c.moveToFirst();

        if(c.getCount() == 1){
            return false;
        }else{
            return true;
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
