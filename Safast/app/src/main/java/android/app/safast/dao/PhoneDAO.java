package android.app.safast.dao;

import android.app.safast.R;
import android.app.safast.controller.DatabaseController;
import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by teste on 23/01/2017.
 */

public class PhoneDAO extends SQLiteOpenHelper {
    private static final String NOME_BANCO = "banco.db";
    public static final String TABELA = "phones";//ESTA EM PUBLICO
    public static final String ID = "_id";
    public static final String NAME = "name";//ESTA EM PUBLICO
    public static final String PHONE = "phone";//ESTA EM PUBLICO
    public static final String COLOR = "color";//ESTA EM PUBLICO

    public static final String TABELA2 = "wiki";//ESTA EM PUBLICO
    public static final String ID_WIKI = "_id_wiki";
    public static final String CATEGORY = "category";//ESTA EM PUBLICO
    public static final String DESCRIPTION = "description";//ESTA EM PUBLICO

    public static final String TABELA3 = "soon";//ESTA EM PUBLICO
    public static final String ID_SOON = "_id";
    public static final String FEATURE = "feature";//ESTA EM PUBLICO
    public static final String DATE= "date";//ESTA EM PUBLICO

    private static final int VERSAO = 1;

    public PhoneDAO(Context context){
        super(context, NOME_BANCO,null,VERSAO);

    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE "+TABELA+"("
                + ID + " integer primary key autoincrement,"
                + NAME + " text,"
                + PHONE + " text,"
                + COLOR + " text"
                +")";
        db.execSQL(sql);


        ContentValues values = new ContentValues();
        values.put(NAME,"Bombeiros");
        values.put(PHONE,"193");
        values.put(COLOR,"#cc0000");
        db.insert(TABELA,null,values);
        values.clear();

        values.put(NAME,"Policia Cívil");
        values.put(PHONE,"197");
        values.put(COLOR,"#007acc");
        db.insert(TABELA,null,values);
        values.clear();

        values.put(NAME,"Policia Federal");
        values.put(PHONE,"194");
        values.put(COLOR,"#00802b");
        db.insert(TABELA,null,values);
        values.clear();

        values.put(NAME,"Delegacia da Mulher");
        values.put(PHONE,"181");
        values.put(COLOR,"#423d70");
        db.insert(TABELA,null,values);
        values.clear();

        values.put(NAME,"SAMU");
        values.put(PHONE,"192");
        values.put(COLOR,"#fcd079");
        db.insert(TABELA,null,values);
        values.clear();

        //WIKI DAO >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
        String sql2 = "CREATE TABLE "+TABELA2+"("
                + ID_WIKI + " integer primary key autoincrement,"
                + CATEGORY + " text,"
                + DESCRIPTION + " text"
                +")";
        db.execSQL(sql2);

        ContentValues values2 = new ContentValues();
        values2.put(CATEGORY,"ALAGAMENTOS");
        values2.put(DESCRIPTION,"Ops! Ocorreu algum erro! Desculpe pelo emprevisto :(");
        //values2.put(COLOR,"#cc0000");
        db.insert(TABELA2,null,values2);
        values2.clear();

        values2.put(CATEGORY,"INCENDIOS");
        values2.put(DESCRIPTION,"Ops! Ocorreu algum erro! Desculpe pelo emprevisto :(");
        //values2.put(COLOR,"#AA0000");
        db.insert(TABELA2,null,values2);
        values2.clear();

        values2.put(CATEGORY,"TERREMOTOS");
        values2.put(DESCRIPTION,"Ops! Ocorreu algum erro! Desculpe pelo emprevisto :(");
        // values2.put(COLOR,"#DD0000");
        db.insert(TABELA2,null,values2);
        values2.clear();

        values2.put(CATEGORY,"TRANSITO");
        values2.put(DESCRIPTION, "Ops! Ocorreu algum erro! Desculpe pelo emprevisto :(");
        // values2.put(COLOR,"#FF0000");
        db.insert(TABELA2,null,values2);
        values2.clear();

        //FEATURE DAO >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
        String sql3 = "CREATE TABLE "+TABELA3+"("
                + ID_SOON + " integer primary key autoincrement,"
                + FEATURE + " text,"
                + DATE + " text"
                +")";
        db.execSQL(sql3);

        ContentValues values3 = new ContentValues();
        values3.put(FEATURE,"Geolocalização");
        values3.put(DATE,"25/07");
        //values2.put(COLOR,"#cc0000");
        db.insert(TABELA3,null,values3);
        values3.clear();

        values3.put(FEATURE,"Notificações e Widgets");
        values3.put(DATE,"Em breve!");
        //values2.put(COLOR,"#AA0000");
        db.insert(TABELA3,null,values3);
        values3.clear();

        values3.put(FEATURE,"Artigo: Assaltos");
        values3.put(DATE,"Em breve!");
        // values2.put(COLOR,"#DD0000");
        db.insert(TABELA3,null,values3);
        values3.clear();


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS" + TABELA);
        db.execSQL("DROP TABLE IF EXISTS" + TABELA2);
        db.execSQL("DROP TABLE IF EXISTS" + TABELA3);
        onCreate(db);
    }

}
