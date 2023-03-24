package com.example.almacenamientnodatosvitacora;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class MainActivity extends AppCompatActivity {

    private EditText eb1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        eb1=(EditText) findViewById(R.id.txtB);
        String archivos[]=fileList();

        if (ArchivoExiste(archivos, "bitacora.txt")){
            try {
                InputStreamReader archivo=new InputStreamReader(openFileInput("bitacora.txt"));
                BufferedReader br=new BufferedReader(archivo);
                String linea=br.readLine();
                String bitacoraC="";
                while (linea!=null){
                    bitacoraC=bitacoraC+linea+"\n";
                    linea=br.readLine();
                }
                br.close();
                archivo.close();
                eb1.setText(bitacoraC);
            }catch (IOException e){

            }
        }
    }

    @SuppressLint("SuspiciousIndentation")
    private boolean ArchivoExiste(String archivos[], String NombreArchivo){
        for (int i=0; i<archivos.length; i++)
            if (NombreArchivo.equals(archivos[i]))
                return true;
        return false;
    }

    public void Guardar(View view){
        try {
            OutputStreamWriter archivo=new OutputStreamWriter(openFileOutput("bitacora.txt", Activity.MODE_PRIVATE));
            archivo.write(eb1.getText().toString());
            archivo.flush();
            archivo.close();
        }catch (IOException e){

        }

        Toast.makeText(this, "Bitacora guardada correctamente", Toast.LENGTH_SHORT).show();
        finish();

    }
}