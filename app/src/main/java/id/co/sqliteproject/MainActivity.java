package id.co.sqliteproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private DbHelper dbHelper;
    EditText etName;
    TextView tvResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etName = findViewById(R.id.et_name);
        tvResult = findViewById(R.id.tv_Result);

        dbHelper = new DbHelper(this);
        dbHelper.getWritableDatabase();

    }

    public void InsertData(View view) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        String name = etName.getText().toString();
        values.put(DbHelper.KEY_NAME, name);
        long id = db.insert(DbHelper.TABLE_STUDENTS, null, values);
        Log.i("DATABASE", "Id Data: " + id);
    }

    public void readData(View view) {
        ArrayList<String> data = new ArrayList<>();

        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String query = "SELECT * FROM " + DbHelper.TABLE_STUDENTS;
        Cursor c = db.rawQuery(query, null);

        while (c.moveToNext()) {
            data.add(c.getString(c.getColumnIndex(DbHelper.KEY_NAME)));
        }
        c.close();

        String hasil = "";
        for (int i = 0; i < data.size() ; i++) {
            Log.d("DATABASE", data.get(i));
            hasil += data.get(i);
        }
        tvResult.setText(hasil);
    }
}