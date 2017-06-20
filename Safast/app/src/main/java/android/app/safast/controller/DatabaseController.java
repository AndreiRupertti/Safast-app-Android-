package android.app.safast.controller;

import android.app.safast.R;
import android.app.safast.dao.PhoneDAO;
import android.app.safast.domain.Phone;
import android.app.safast.domain.Wiki;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by teste on 23/01/2017.
 */

public class DatabaseController {
    private SQLiteDatabase db;
    private PhoneDAO banco;

    public DatabaseController(Context context){
        banco = new PhoneDAO(context);
    }

    public String insertData(String name, String phone, String color){
        ContentValues values;
        long resultado;

        db = banco.getWritableDatabase();
        values = new ContentValues();
        values.put(PhoneDAO.NAME, name);
        values.put(PhoneDAO.PHONE, phone);
        values.put(PhoneDAO.COLOR, color);


        resultado = db.insert(PhoneDAO.TABELA, null, values);
        db.close();

        if (resultado ==-1)
            return "Erro ao inserir registro";
        else
            return "Registro Inserido com sucesso";

    }

    public Cursor getData(){
        Cursor cursor;
        String[] campos =  {banco.ID,banco.NAME,banco.PHONE,banco.COLOR};

        db = banco.getReadableDatabase();
        cursor = db.query(banco.TABELA, campos, null, null, null, null, null, null);

        if(cursor!=null){
            cursor.moveToFirst();
        }
        db.close();
        return cursor;
    }

    public Cursor getDataById(int id){
        Cursor cursor;
        String[] campos =  {banco.ID,banco.NAME,banco.PHONE,banco.COLOR};
        String where = PhoneDAO.ID + "=" + id;
        db = banco.getReadableDatabase();
        cursor = db.query(PhoneDAO.TABELA,campos,where, null, null, null, null, null);
        if(cursor!=null){
            cursor.moveToFirst();
        }
        db.close();
        return cursor;
    }

    public Phone getPhoneById(int id){
        Cursor cursor;
        String name, tel, color ;
        String[] campos =  {banco.ID,banco.NAME,banco.PHONE,banco.COLOR};
        String where = PhoneDAO.ID + "=" + id;
        db = banco.getReadableDatabase();
        cursor = db.query(PhoneDAO.TABELA,campos,where, null, null, null, null, null);

        if(cursor!=null){
            cursor.moveToFirst();
            name = cursor.getString(1);
            tel = cursor.getString(2);
            color = cursor.getString(3);
            Phone p = new Phone(id, name, tel, color);
            return p;
        }

        db.close();
        Phone p = new Phone();
        return p;

    }

    public Wiki getWikiById(int id){
        Cursor cursor;
        String category, description ;
        int[] photos = new int[]{R.drawable.alagamento, R.drawable.incendio, R.drawable.terremoto, R.drawable.transito};
        String[] campos =  {banco.ID_WIKI,banco.CATEGORY,banco.DESCRIPTION};
        String where = PhoneDAO.ID_WIKI+ "=" + id;
        db = banco.getReadableDatabase();
        cursor = db.query(PhoneDAO.TABELA2,campos,where, null, null, null, null, null);


        if(cursor!=null){
            cursor.moveToFirst();

            category = cursor.getString(1);
            description= cursor.getString(2);
            Wiki wiki = new Wiki(id, category, description, photos[id-1]);
            return wiki;
        }

        db.close();
        Wiki wiki = new Wiki();
        return wiki;

    }

    public void alterData(int id, String name, String phone){
        ContentValues valores;
        String where;

        db = banco.getWritableDatabase();

        where = PhoneDAO.ID + "=" + id;

        valores = new ContentValues();
        valores.put(PhoneDAO.NAME, name);
        valores.put(PhoneDAO.PHONE, phone);

        db.update(PhoneDAO.TABELA,valores,where,null);
        db.close();
    }

    public void removePhone(int id){
        String where;
        db = banco.getWritableDatabase();
        where = PhoneDAO.ID + "=" + id;

        db.delete(PhoneDAO.TABELA,where,null);
        db.close();
    }
    // CRUD WIKI >>>>>>>>

    public Cursor getDataWiki(){
        Cursor cursor;
        String[] campos =  {banco.ID_WIKI,banco.CATEGORY,banco.DESCRIPTION};
        cursor = db.query(banco.TABELA2, campos, null, null, null, null, null, null);

        if(cursor!=null){
            cursor.moveToFirst();
        }
        db.close();
        return cursor;
    }
    public Cursor getDataSoon(){
        Cursor cursor;
        String[] campos =  {banco.ID_SOON,banco.FEATURE,banco.DATE};
        db = banco.getReadableDatabase();
        cursor = db.query(banco.TABELA3, campos, null, null, null, null, null, null);

        if(cursor!=null){
            cursor.moveToFirst();
        }
        db.close();
        return cursor;
    }
    public Cursor getDataSoonById(int id){
        Cursor cursor;
        String[] campos =  {banco.ID_SOON,banco.FEATURE,banco.DATE};
        String where = PhoneDAO.ID_SOON + "=" + id;
        db = banco.getReadableDatabase();
        cursor = db.query(PhoneDAO.TABELA3,campos,where, null, null, null, null, null);
        if(cursor!=null){
            cursor.moveToFirst();
        }
        db.close();
        return cursor;
    }
}


