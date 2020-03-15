package com.example.italac.view;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.italac.R;
import com.example.italac.model.Fazenda;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class ListFazenda extends AppCompatActivity {
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
    private ListView listViewFazendas;
    private List<String> listFazenda = new ArrayList<String>();
    private ArrayAdapter<String> arrayAdapterFazenda;

    private String fazendaSelecionada;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_fazenda);

        inicializarComponentes();
        eventoCollection();
        if (user != null) {
            final String email1 = user.getEmail();

            listViewFazendas.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                    fazendaSelecionada = (String) adapterView.getItemAtPosition(position);

                    db.collection("Usuario").document(email1).collection("Fazendas").whereEqualTo("Nome da Fazenda", fazendaSelecionada)
                            .get()
                            .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                                @Override
                                public void onComplete(@NonNull Task<QuerySnapshot> task) {
                                    String no = null;
                                    if (task.isSuccessful()) {
                                        for (QueryDocumentSnapshot document : task.getResult()) {
                                            no = (String) document.get("Inscrição Estadual");
                                        }
                                    }
                                    System.out.println(no);
                                    Intent i = new Intent(ListFazenda.this, MenuAnalise.class);
                                    Bundle parms = new Bundle();
                                    parms.putString("id", no);
                                    i.putExtras(parms);
                                    startActivity(i);
                                }

                            });
                }
            });
        }


    }

    private void eventoCollection() {

        if (user != null) {
            String email1 = user.getEmail();

            db.collection("Usuario").document(email1).collection("Fazendas").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                @Override
                public void onComplete(@NonNull Task<QuerySnapshot> task) {
                    if (task.isSuccessful()) {
                        for (QueryDocumentSnapshot documentSnapshot : task.getResult()) {
                            String nome = (String) documentSnapshot.get("Nome da Fazenda");
                            listFazenda.add(nome);
                        }
                    }
                    arrayAdapterFazenda = new ArrayAdapter<String>(getApplication(), android.R.layout.simple_expandable_list_item_1, listFazenda);
                    listViewFazendas.setAdapter(arrayAdapterFazenda);
                }
            });

        }
    }


    private void inicializarComponentes() {
        listViewFazendas = (ListView) findViewById(R.id.ListFazendas);

    }

    private void alert(String msg) {
        Toast.makeText(ListFazenda.this, msg, Toast.LENGTH_SHORT).show();
    }
}
