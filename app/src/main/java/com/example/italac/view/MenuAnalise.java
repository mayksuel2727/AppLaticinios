package com.example.italac.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.italac.R;

public class MenuAnalise extends AppCompatActivity {

    Button btn_adicionarAnalise, btn_HistoricoAnalise;
    String id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_analise);
        getSupportActionBar().hide();
        Intent i = getIntent();
        if (i!= null){
            Bundle parms = i.getExtras();
            if (parms != null){
                id = parms.getString("id");
            }
        }

        inicializarComponentes();
        eventoclick();

    }
    private void eventoclick() {
        btn_adicionarAnalise.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MenuAnalise.this, CadAnalise.class);
                Bundle parms = new Bundle();
                parms.putString("id", id);
                i.putExtras(parms);
                startActivity(i);
            }
        });

        btn_HistoricoAnalise.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MenuAnalise.this, Historico_Analises.class);
                Bundle parms = new Bundle();
                parms.putString("id", id);
                i.putExtras(parms);
                startActivity(i);
            }
        });


    }

    private void inicializarComponentes() {
        btn_adicionarAnalise = (Button) findViewById(R.id.adicionarAnalise);
        btn_HistoricoAnalise = (Button) findViewById(R.id.historicoAnalise);
    }
}
