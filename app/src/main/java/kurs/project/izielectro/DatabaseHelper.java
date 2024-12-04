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
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.Nullable;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;

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



    public int checkEmailPassword(String email, String password) {
        create_db();
        SQLiteDatabase MyDatabase =SQLiteDatabase.openDatabase(DB_PATH, null, SQLiteDatabase.OPEN_READWRITE);
        Cursor cursor;
        int id;
        try{
            cursor= MyDatabase.rawQuery("SELECT * FROM User WHERE Login=? AND Password=?", new String[]{email, password});}
        catch (Exception e){
            Toast.makeText(myContext, e.getMessage(), Toast.LENGTH_SHORT).show();
            return -1;
        }

        if (cursor.getCount() > 0) { cursor.moveToFirst(); id= cursor.getInt(0);cursor.close();return id;}
        else {
            cursor.close(); return -1;}
    }
    public String checkRole(int id){
        create_db();
        SQLiteDatabase MyDatabase =SQLiteDatabase.openDatabase(DB_PATH, null, SQLiteDatabase.OPEN_READWRITE);

        String querId="id="+id;
        Cursor cursor=MyDatabase.query("User", new String[]{"Role"}, querId, null, null, null, null);
        if (cursor != null) {
            cursor.moveToFirst();
            querId= cursor.getString(0);
            cursor.close();
            return querId;
        }

        return "";
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

    public void addToCart(int id){}


   public  ArrayList<ListData> getItemss() {
       ArrayList<ListData> dataArrayList=new ArrayList<>();
       ListData listData;
       create_db();
        SQLiteDatabase MyDatabase =SQLiteDatabase.openDatabase(DB_PATH, null, SQLiteDatabase.OPEN_READWRITE);
        Cursor cursor = MyDatabase.query("Product", null, null, null, null, null, null);
        if (cursor != null) {
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                listData = new ListData(cursor.getInt(0), cursor.getString(3), cursor.getString(4), cursor.getString(2), cursor.getInt(5),cursor.getInt(6));

                dataArrayList.add(listData);
                cursor.moveToNext();
            }
            cursor.close();
        }
        return dataArrayList;
    }
    public  ArrayList<ListCart> getCart(int idUser) {
        ArrayList<ListCart> dataArrayList=new ArrayList<>();
        ListCart listCart;
        create_db();
        SQLiteDatabase MyDatabase =SQLiteDatabase.openDatabase(DB_PATH, null, SQLiteDatabase.OPEN_READWRITE);
        String querId="id="+idUser;

        Cursor cursor = MyDatabase.rawQuery("SELECT User.Id ,Product.Id,Detail.Id, Photo,Title,Description,Price,Quantity FROM Product INNER JOIN Detail on Product.Id=Detail.IdProduct INNER JOIN User on User.Id=Detail.IdUser WHERE Bought=\"НЕТ\" and User.Id=?", new String[]{String.valueOf(idUser)});
        if (cursor != null) {
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                listCart = new ListCart(cursor.getInt(0),cursor.getInt(1),cursor.getInt(2),cursor.getString(3),cursor.getString(4),cursor.getString(5),cursor.getInt(6),cursor.getInt(7));
                dataArrayList.add(listCart);
                cursor.moveToNext();
            }
            cursor.close();
        }
        return dataArrayList;
    }
}


