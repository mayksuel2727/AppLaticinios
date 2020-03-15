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
import com.example.italac.controller.Conexao;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class CadUsuario extends AppCompatActivity {

    private EditText editEmail, editSenha, editNome, editTelefone;
    private Button btnCadastra;
    private FirebaseAuth auth;
    private FirebaseFirestore db = FirebaseFirestore.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cad_usuario);
        getSupportActionBar().hide();

        inicializarComponentes();
        eventoClicks();

    }

    private void eventoClicks() {

        btnCadastra.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = editEmail.getText().toString().trim();
                String senha = editSenha.getText().toString().trim();

                try {
                    criarUser(email, senha);
                } catch (IllegalArgumentException e) {
                    alert("Campos estão vazios");
                }

            }
        });
    }

    private void criarUser(String email, String senha) {
        auth.createUserWithEmailAndPassword(email, senha).addOnCompleteListener(CadUsuario.this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {


                    Map<String, Object> user = new HashMap<>();
                    user.put("nome", editNome.getText().toString());
                    user.put("Telefone", editTelefone.getText().toString());
                    user.put("E-mail", editEmail.getText().toString());
                    db.collection("Usuario").document(editEmail.getText().toString())
                            .set(user)
                            .addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void aVoid) {
                                    alert("Cadastrado com sucesso");
                                }
                            })
                            .addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    alert("Erro de cadastro");
                                }
                            });

                    Intent i = new Intent(CadUsuario.this, MenuFazenda.class);
                    startActivity(i);
                } else {
                    alert("Erro de cadastro");
                }
            }
        });
    }

    private void alert(String msg) {
        Toast.makeText(CadUsuario.this, msg, Toast.LENGTH_SHORT).show();
    }

    private void inicializarComponentes() {
        btnCadastra = (Button) findViewById(R.id.butonCadastra);
        editEmail = (EditText) findViewById(R.id.editEmail);
        editSenha = (EditText) findViewById(R.id.editSenha);
        editNome = (EditText) findViewById(R.id.editNome);
        editTelefone = (EditText) findViewById(R.id.editTelefone);
    }

    @Override
    protected void onStart() {
        super.onStart();
        auth = Conexao.getFirebaseAuth();
    }
}
