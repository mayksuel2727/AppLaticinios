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
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Login extends AppCompatActivity {

    private FirebaseAuth auth;

    private Button btnEntra;
    private Button btnCadastro;
    private EditText edtEmail;
    private EditText editSenha;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        getSupportActionBar().hide();

        auth = FirebaseAuth.getInstance();
        inicializarComponentes();
        eventoClicks();
    }

    private void inicializarComponentes() {
        btnCadastro = (Button) findViewById(R.id.ButonCadastro);
        btnEntra = (Button) findViewById(R.id.ButonEntra);
        edtEmail = (EditText) findViewById(R.id.EditEmail);
        editSenha = (EditText) findViewById(R.id.EditSenha);
    }

    private void eventoClicks() {
        btnCadastro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), CadUsuario.class);
                startActivity(i);
            }
        });


        btnEntra.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = edtEmail.getText().toString().trim();
                String senha = editSenha.getText().toString().trim();
                try {
                    login(email, senha);
                }catch (IllegalArgumentException e){
                    alerte("Campos e-mail ou senha estão vazios");
                }

            }
        });
    }

    private void login(String email, String senha) {
        auth.signInWithEmailAndPassword(email,senha).addOnCompleteListener(Login.this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    Intent i = new Intent(Login.this, MenuFazenda.class);
                    //i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(i);
                }else{
                    alerte("email ou senha errrados");
                }
            }
        });
    }

    private void alerte(String s) {
        Toast.makeText(Login.this,s,Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onStart() {
        super.onStart();
        auth = Conexao.getFirebaseAuth();
    }

    public void callReset(View view){
        Intent intent = new Intent( this, ResetLogin.class );
        startActivity(intent);
    }
    public void sendLoginData(View view){

    }
    public void sendLoginFacebookData(View view){

    }
}
