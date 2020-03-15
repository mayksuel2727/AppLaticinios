package com.example.italac.view;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.example.italac.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class CadAnalise extends AppCompatActivity {
    private EditText gordura, est, proteina, esd, lactose, ccs, cbt;
    private Button bnt_reg_analise;
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private String id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cad_analise);
        getSupportActionBar().hide();

        inicializarComponentes();
        eventosClicks();
    }

    private void eventosClicks() {
        Intent i = getIntent();
        if (i!= null){
            Bundle parms = i.getExtras();
            if (parms != null){
                id = parms.getString("id");
            }
        }
        bnt_reg_analise.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                if (user != null) {
                    String email = user.getEmail();

                    Map<String, Object> analise = new HashMap<>();
                    analise.put("Gordura", gordura.getText().toString());
                    analise.put("EST", est.getText().toString());
                    analise.put("ESD", esd.getText().toString());
                    analise.put("Lactose", lactose.getText().toString());
                    analise.put("CCS", ccs.getText().toString());
                    analise.put("CBT", cbt.getText().toString());

                    SimpleDateFormat formataData = new SimpleDateFormat("dd-MM-yyyy HH-");
                    Date data = new Date();
                    String dataFormatada = formataData.format(data);


                    db.collection("Usuario").document(email).collection("Fazendas")
                            .document(id).collection("Analise").document(dataFormatada)
                            .set(analise)
                            .addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void aVoid) {
                                    alert("Sucesso ao Cadastrar");
                                    Intent i = new Intent(CadAnalise.this, MenuAnalise.class);
                                    startActivity(i);
                                }
                            })
                            .addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    alert("Error ao cadastrar");
                                    //a
                                }
                            });
                }
            }
        });
    }

    private void inicializarComponentes() {
        gordura = (EditText) findViewById(R.id.gordura);
        est = (EditText) findViewById(R.id.est);
        proteina = (EditText) findViewById(R.id.gordura);
        esd = (EditText) findViewById(R.id.esd);
        lactose = (EditText) findViewById(R.id.lactose);
        ccs = (EditText) findViewById(R.id.ccs);
        cbt = (EditText) findViewById(R.id.cbt);
        bnt_reg_analise = (Button) findViewById(R.id.reg_analise);
    }

    private void alert(String msg) {
        Toast.makeText(CadAnalise.this, msg, Toast.LENGTH_SHORT).show();
    }
}
