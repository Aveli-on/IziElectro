package kurs.project.izielectro;


import android.app.Application;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.CursorIndexOutOfBoundsException;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.Nullable;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static String DB_PATH;
     // полный путь к базе данных
    private static String DB_NAME = "IziElectro.db";
    private static final int SCHEMA = 1; // версия базы данных
    static final String TABLE = "User"; // название таблицы в бд

    private Context myContext;

    public DatabaseHelper(@Nullable Context context) {

        super(context,"IziElectro.db",null,1);
        this.myContext=context;
        DB_PATH=context.getFilesDir().getPath()+DB_NAME;

    }

    @Override
    public void onCreate(SQLiteDatabase MyDatabase) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase MyDatabase, int i, int i1) {

    }
    public Boolean checkEmail(String email) {
        create_db();
        SQLiteDatabase MyDatabase =SQLiteDatabase.openDatabase(DB_PATH, null, SQLiteDatabase.OPEN_READWRITE);

        Cursor cursor;
        try {
            cursor = MyDatabase.rawQuery("SELECT * FROM User WHERE Login=?", new String[]{email});
        } catch (Exception e){
            Toast.makeText(myContext, e.getMessage(), Toast.LENGTH_SHORT).show();
            return false;
        }

        if (cursor.getCount() > 0) return false;

        else return true;
    }
    public Boolean checkEmailPassword(String email, String password) {
        create_db();
        SQLiteDatabase MyDatabase =SQLiteDatabase.openDatabase(DB_PATH, null, SQLiteDatabase.OPEN_READWRITE);;
        Cursor cursor;
        try{
            cursor= MyDatabase.rawQuery("SELECT * FROM User WHERE Login=? AND Password=?", new String[]{email, password});}
        catch (Exception e){
            Toast.makeText(myContext, e.getMessage(), Toast.LENGTH_SHORT).show();
            return false;
        }
        MyDatabase.isOpen();
        if (cursor.getCount() > 0) {cursor.close();return true;}
        else {
            cursor.close(); return false;}
    }

    public Boolean insertDataUser(String fio, String role, String login, String password,String phone) {
        ContentValues values = new ContentValues();
        values.put("FIO", fio);
        values.put("Role", role);
        values.put("Login", login);
        values.put("Password", password);
        values.put("PhoneNumber", phone);
        create_db();
        SQLiteDatabase MyDatabase =SQLiteDatabase.openDatabase(DB_PATH, null, SQLiteDatabase.OPEN_READWRITE);;
        long result = MyDatabase.insert("User", null, values);
        if (result != -1) {
            Log.d("Database", "Данные успешно добавлены");
            return true;
        }
        return false;
    }
    public void saveChanges( SQLiteDatabase MyDatabaseChange ){
        create_db();
        SQLiteDatabase MyDatabase =SQLiteDatabase.openDatabase(DB_PATH, null, SQLiteDatabase.OPEN_READWRITE);;

    }
    public SQLiteDatabase open()throws SQLException {

        return SQLiteDatabase.openDatabase(DB_PATH, null, SQLiteDatabase.OPEN_READWRITE);
    }
    void create_db(){

        File file = new File(DB_PATH);

        if (!file.exists()) {
            //получаем локальную бд как поток
            try(InputStream myInput = myContext.getAssets().open(DB_NAME);
                // Открываем пустую бд
                OutputStream myOutput = new FileOutputStream(DB_PATH)) {

                // побайтово копируем данные
                byte[] buffer = new byte[1024];
                int length;
                while ((length = myInput.read(buffer)) > 0) {
                    myOutput.write(buffer, 0, length);
                }
                myOutput.flush();
            }
            catch(IOException ex){
                Log.d("DatabaseHelper", ex.getMessage());
            }
        }
    }
    void saveChanges(){

        File file = new File(DB_PATH);
        if (!file.exists()) {
            //получаем локальную бд как поток
            try(InputStream myInput = myContext.getAssets().open(DB_NAME);
                // Открываем пустую бд
                OutputStream myOutput = new FileOutputStream(DB_PATH)) {

                // побайтово копируем данные
                byte[] buffer = new byte[1024];
                int length;
                while ((length = myInput.read(buffer)) > 0) {
                    myOutput.write(buffer, 0, length);
                }
                myOutput.flush();
            }
            catch(IOException ex){
                Log.d("DatabaseHelper", ex.getMessage());
            }
        }
    }

}


