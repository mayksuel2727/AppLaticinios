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

import java.util.HashMap;
import java.util.Map;

public class CadFazenda extends AppCompatActivity {
    private Button cadastrar;
    private EditText editNomeFazenda, editMunicipio, editIncEstadual, editCNPJouCPF, editEndereço;
    private FirebaseFirestore db = FirebaseFirestore.getInstance();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cad_fazenda);

        inicializarComponentes();
        eventoClicks();

    }

    private void inicializarComponentes() {
        cadastrar = (Button) findViewById(R.id.btn_Cadastrar);
        editNomeFazenda = (EditText) findViewById(R.id.editNomeFazenda);
        editMunicipio = (EditText) findViewById(R.id.editMunicipio);
        editCNPJouCPF = (EditText) findViewById(R.id.editCPFouCNPJ);
        editIncEstadual = (EditText) findViewById(R.id.inscricaoEstadual);
        editEndereço = (EditText) findViewById(R.id.endereco);
    }

    private void eventoClicks() {
        cadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                if (user != null) {
                    String email = user.getEmail();

                    Map<String, Object> fazenda = new HashMap<>();
                    fazenda.put("Nome da Fazenda", editNomeFazenda.getText().toString());
                    fazenda.put("Municipio", editMunicipio.getText().toString());
                    fazenda.put("Inscrição Estadual", editIncEstadual.getText().toString());
                    fazenda.put("Cnpj ou Cpf", editCNPJouCPF.getText().toString());
                    fazenda.put("Endereço", editEndereço.getText().toString());


                    db.collection("Usuario").document(email).collection("Fazendas").document(editIncEstadual.getText().toString())
                            .set(fazenda)
                            .addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void aVoid) {
                                    alert("Sucesso ao Cadastrar");
                                    Intent i = new Intent(CadFazenda.this, ListFazenda.class);
                                    startActivity(i);

                                }
                            })
                            .addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    alert("Erro ao cadastrar");
                                }
                            });

                }
            }
        });
    }

    private void alert(String msg) {
        Toast.makeText(CadFazenda.this, msg, Toast.LENGTH_SHORT).show();
    }


}
