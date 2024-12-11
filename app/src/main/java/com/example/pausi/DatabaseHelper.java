package com.example.pausi;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "pausi.db";
    private static final int DATABASE_VERSION = 1;

    // Tabla de usuarios
    private static final String TABLE_USERS = "usuarios";
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_NAME = "nombre";
    private static final String COLUMN_EMAIL = "correo";
    private static final String COLUMN_PASSWORD = "contraseña";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTableUsers = "CREATE TABLE " + TABLE_USERS + " (" +
                COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_NAME + " TEXT, " +
                COLUMN_EMAIL + " TEXT UNIQUE, " +
                COLUMN_PASSWORD + " TEXT)";
        db.execSQL(createTableUsers);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USERS);
        onCreate(db);
    }

    // Métodos CRUD
    public boolean registrarUsuario(String nombre, String correo, String contraseña) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME, nombre);
        values.put(COLUMN_EMAIL, correo);
        values.put(COLUMN_PASSWORD, contraseña);

        long result = db.insert(TABLE_USERS, null, values);
        return result != -1; // Retorna true si se insertó correctamente
    }

    public boolean verificarUsuario(String correo, String contraseña) {
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT * FROM " + TABLE_USERS + " WHERE " +
                COLUMN_EMAIL + " = ? AND " + COLUMN_PASSWORD + " = ?";
        Cursor cursor = db.rawQuery(query, new String[]{correo, contraseña});
        boolean existe = cursor.getCount() > 0;
        cursor.close();
        return existe;
    }
}
