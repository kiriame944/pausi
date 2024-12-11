package com.example.pausi;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

    // Nombre de la base de datos y versión
    private static final String DATABASE_NAME = "pausi.db";
    private static final int DATABASE_VERSION = 1;

    // Nombre de la tabla y columnas
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
        // Crear la tabla de usuarios
        String createTableUsers = "CREATE TABLE " + TABLE_USERS + " (" +
                COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_NAME + " TEXT NOT NULL, " +
                COLUMN_EMAIL + " TEXT NOT NULL UNIQUE, " +
                COLUMN_PASSWORD + " TEXT NOT NULL)";
        db.execSQL(createTableUsers);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Eliminar la tabla si ya existe y volver a crearla
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USERS);
        onCreate(db);
    }

    // Registrar un usuario
    public boolean registrarUsuario(String nombre, String correo, String contraseña) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME, nombre);
        values.put(COLUMN_EMAIL, correo);
        values.put(COLUMN_PASSWORD, contraseña);

        long result = db.insert(TABLE_USERS, null, values);
        return result != -1; // Devuelve true si la inserción fue exitosa
    }

    // Verificar usuario para inicio de sesión
    public boolean verificarUsuario(String correo, String contraseña) {
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT * FROM " + TABLE_USERS + " WHERE " +
                COLUMN_EMAIL + " = ? AND " + COLUMN_PASSWORD + " = ?";
        Cursor cursor = db.rawQuery(query, new String[]{correo, contraseña});
        boolean existe = cursor.getCount() > 0;
        cursor.close();
        return existe;
    }

    // Obtener datos de un usuario
    public Cursor obtenerUsuario(String correo) {
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT * FROM " + TABLE_USERS + " WHERE " + COLUMN_EMAIL + " = ?";
        Cursor cursor = db.rawQuery(query, new String[]{correo});
        return cursor;
    }

    // Actualizar datos de usuario
    public boolean actualizarUsuario(String correoActual, String nuevoNombre, String nuevoCorreo, String nuevaContraseña) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME, nuevoNombre);
        values.put(COLUMN_EMAIL, nuevoCorreo);
        values.put(COLUMN_PASSWORD, nuevaContraseña);

        int filasAfectadas = db.update(TABLE_USERS, values, COLUMN_EMAIL + " = ?", new String[]{correoActual});
        return filasAfectadas > 0; // Devuelve true si al menos una fila fue afectada
    }

    // Eliminar usuario
    public boolean eliminarUsuario(String correo) {
        SQLiteDatabase db = this.getWritableDatabase();
        int filasEliminadas = db.delete(TABLE_USERS, COLUMN_EMAIL + " = ?", new String[]{correo});
        return filasEliminadas > 0; // Devuelve true si al menos una fila fue eliminada
    }

    // Obtener todos los usuarios (opcional, útil para debugging o funcionalidades futuras)
    public Cursor obtenerTodosLosUsuarios() {
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT * FROM " + TABLE_USERS;
        return db.rawQuery(query, null);
    }
}
