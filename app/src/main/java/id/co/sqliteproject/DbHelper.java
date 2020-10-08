package id.co.sqliteproject;

import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Build;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;

public class DbHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "mahasiswa.db";
    private static final int DATABASE_VERSION = 1;
    private static final String KEY_ID = "id";
    static final String TABLE_STUDENTS = "students";
    static final String KEY_NAME = "nama";

    private static final String CREATE_TABLE_STUDENTS = "CREATE TABLE " + TABLE_STUDENTS + "("+ KEY_ID
            + " INTEGER PRIMARY KEY AUTOINCREMENT," + KEY_NAME +  " TEXT );";

    public DbHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    public DbHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version, @Nullable DatabaseErrorHandler errorHandler) {
        super(context, name, factory, version, errorHandler);
    }

    public DbHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(CREATE_TABLE_STUDENTS);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_STUDENTS);
        onCreate(sqLiteDatabase);
    }
}
