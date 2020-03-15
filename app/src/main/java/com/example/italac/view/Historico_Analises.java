package com.example.italac.view;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.EditText;

import com.example.italac.R;



import com.github.mikephil.charting.charts.HorizontalBarChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;


public class Historico_Analises extends AppCompatActivity {
    private EditText editGordura, editProteina, editEst, editEsd, editLactose, editCCS, editCBT;
    private HorizontalBarChart horizontalChart;
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    float var = (float) 9.6;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_historico__analises);
        horizontalChart = (HorizontalBarChart)findViewById(R.id.horizontalChart);
        BarDataSet barDataSet = new BarDataSet(getData(), "Inducesmile");
        barDataSet.setBarBorderWidth(0.9f);
        barDataSet.setColors(ColorTemplate.COLORFUL_COLORS);
        BarData barData = new BarData(barDataSet);
        XAxis xAxis = horizontalChart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        final String[] months = new String[]{"CBT", "CCS", "Lactose", "ESD", "Proteina", "EST", "Gordura"};
        IndexAxisValueFormatter formatter = new IndexAxisValueFormatter(months);
        xAxis.setGranularity(1f);
        xAxis.setValueFormatter(formatter);
        horizontalChart.setData(barData);
        horizontalChart.setFitBars(true);
        horizontalChart.animateXY(0, 0);
        horizontalChart.invalidate();

        inicializarComponentes();
        eventoFirebase();
    }
    private ArrayList getData(){
        ArrayList<BarEntry> entries = new ArrayList<>();
        entries.add(new BarEntry(0f, var));
        entries.add(new BarEntry(1f, 1f));
        entries.add(new BarEntry(2f, 2.5f));
        entries.add(new BarEntry(3f, 4f));
        entries.add(new BarEntry(4f, 0.7f));
        entries.add(new BarEntry(5f, 10f));
        entries.add(new BarEntry(6f, 5.9f));

        return entries;
    }

    private void eventoFirebase() {


    }

    private void inicializarComponentes() {
        editGordura = (EditText) findViewById(R.id.gordura);
        editEst = (EditText) findViewById(R.id.est);
        editProteina = (EditText) findViewById(R.id.proteina);
        editEsd = (EditText) findViewById(R.id.esd);
        editLactose = (EditText) findViewById(R.id.lactose);
        editCCS = (EditText) findViewById(R.id.ccs);
        editCBT = (EditText) findViewById(R.id.ccs);
    }
}
