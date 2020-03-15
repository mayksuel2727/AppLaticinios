package com.example.italac.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.italac.R;

public class MenuFazenda extends AppCompatActivity {
    Button btn_VerFazenda, btn_CadFazenda;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_fazenda);
        getSupportActionBar().hide();

        inicializaComponentes();
        eventoClicks();
    }

    private void inicializaComponentes() {
        btn_VerFazenda = (Button) findViewById(R.id.btn_VERfazenda);
        btn_CadFazenda = (Button) findViewById(R.id.cad_fazenda);
    }

    private void eventoClicks() {
        btn_VerFazenda.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i = new Intent(getApplicationContext(), ListFazenda.class);
                startActivity(i);
            }
        });

        btn_CadFazenda.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), CadFazenda.class);
                startActivity(i);
            }
        });
    }

    private void alert(String msg) {
        Toast.makeText(MenuFazenda.this, msg, Toast.LENGTH_SHORT).show();
    }

}
