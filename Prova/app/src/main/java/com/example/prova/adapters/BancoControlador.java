package com.example.prova.adapters;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class BancoControlador extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "nutrilife";
    private static final int DATABASE_VERSION = 1;
    private final String CREATE_TABLE_CATALOGO =
            "CREATE TABLE historico ("
                    + "id INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + "resultado TEXT);";

    public BancoControlador(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(CREATE_TABLE_CATALOGO);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS catalogo");
        onCreate(sqLiteDatabase);
    }

    public long inserir(ContentValues cv){
        SQLiteDatabase db = this.getWritableDatabase();
        long id = db.insert("historico", null, cv);
        return id;
    }

//    public List<ContentValues> pesquiarPorTitulo(String titulo){
//        String sql = "SELECT * FROM catalogo WHERE titulo LIKE ?";
//        String where[] = new String[]{"%"+titulo+"%"};
//        return pesquisar(sql, where);
//    }
//
//    public List<ContentValues> pesquiarPorAno(int ano){
//        String sql = "SELECT * FROM catalogo WHERE ano LIKE ?";
//        String where[] = new String[]{String.valueOf(ano)};
//        return pesquisar(sql, where);
//    }

    public List<ContentValues> pesquiarPorTodos(){
        String sql = "SELECT * FROM historico ORDER BY id";
        String where[] = null;
        return pesquisar(sql, where);
    }

    @SuppressLint("Range")
    private List<ContentValues> pesquisar(String sql, String where[]){
        List<ContentValues> lista = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(sql, where);

        if (c.moveToFirst()){
            do{
               ContentValues cv = new ContentValues();
               cv.put("id", c.getInt(c.getColumnIndex("id")));
               cv.put("resultado", c.getString(c.getColumnIndex("resultado")));
               lista.add(cv);
            }while(c.moveToNext());
        }

        return lista;
    }

//    public void alterarRegistro(int id, String titulo, String autor, int ano){
//        ContentValues valores = new ContentValues();
//        String where;
//        SQLiteDatabase db = this.getWritableDatabase();
//        where = "id=" + id;
//        valores.put("titulo", titulo);
//        valores.put("autor", autor);
//        valores.put("ano", ano);
//        db.update("historico", valores, where, null);
//        db.close();
//    }

    public void deletarRegistro(int id){
        String where = "id=" + id;
        SQLiteDatabase db = this.getReadableDatabase();
        db.delete("historico", where, null);
        db.close();
    }


}
