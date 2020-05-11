package com.lab2.ques2;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class StorageActivity extends AppCompatActivity {
    EditText txt_content;
    EditText contenttoDisplay;
    String FILENAME = "MyAppStorage";
    //private File pathSave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_storage);
        txt_content = (EditText) findViewById(R.id.id_txt_mycontent);
        contenttoDisplay = (EditText) findViewById(R.id.id_txt_display);
       // String timeStamp = new SimpleDateFormat("yyyyMMddHHmmss").format( new Date());
        //String filename = "sample" + timeStamp + ".txt";
        //pathSave = new File(getFilesDir(),filename);
    }

    public void saveTofile(View v) throws IOException {
        String msg = txt_content.getText().toString();
        FileOutputStream fos = openFileOutput(FILENAME, Context.MODE_APPEND);
        fos.write(msg.getBytes());
        fos.close();
        Toast.makeText(this,"Your file is Saved", Toast.LENGTH_SHORT).show();
    }


    public void retrieveFromFile(View v) throws IOException {
        String ret;
        FileInputStream fis= openFileInput("MyAppStorage");
        if ( fis != null ) {
            InputStreamReader inputStreamReader = new InputStreamReader(fis);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            String receiveString = "";
            StringBuilder stringBuilder = new StringBuilder();
            while ( (receiveString = bufferedReader.readLine()) != null ) {
                stringBuilder.append(receiveString);
            }
            fis.close();
            ret = stringBuilder.toString();
            contenttoDisplay.setText(ret);
            contenttoDisplay.setVisibility(View.VISIBLE);
        }
        else {
            Toast.makeText(this, "File is unable to retrive", Toast.LENGTH_SHORT).show();
        }


    }
}
