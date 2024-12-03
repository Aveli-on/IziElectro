package kurs.project.izielectro;


import android.app.Application;
import android.content.Context;
import android.database.Cursor;
import android.database.CursorIndexOutOfBoundsException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {
    public static final String databaseName="IziElectro.db";
    Context con;
    public DatabaseHelper(@Nullable Context context) {

        super(context,"IziElectro.db",null,1);
        con=context;
    }

    @Override
    public void onCreate(SQLiteDatabase MyDatabase) {
        Toast.makeText(con, "DB created", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onUpgrade(SQLiteDatabase MyDatabase, int i, int i1) {
        MyDatabase.execSQL("DROP TABLE IF EXISTS "+databaseName);
    }
    public Boolean checkEmail(String email) {
        SQLiteDatabase MyDatabase = this.getWritableDatabase();
        Cursor cursor = MyDatabase.rawQuery("SELECT * FROM " + databaseName + " WHERE Login=?", new String[]{email});
        if (cursor.getCount() > 0) return true;

        else return false;
    }
    public Boolean checkEmailPassword(String email, String password) {
        SQLiteDatabase MyDatabase = this.getWritableDatabase();
        Cursor cursor = MyDatabase.rawQuery("SELECT * FROM " + databaseName + " WHERE Login=? AND Password=?", new String[]{email, password});
        if (cursor.getCount() > 0) return true;
        else return false;
    }

    public Boolean insertData(String email, String password) {
        return true;
    }
}


