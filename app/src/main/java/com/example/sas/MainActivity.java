package com.example.sas;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {


    private TextView txvPergunta;
    private RadioGroup rdgPrincipal;
    private Button btnResponder;

    int pontuacao = 0;
    int indicePergunta = 0;

    // Perguntas
    String[] perguntas = {
            "Minha pergunta 1",
            "Minha pergunta 2",
            "Minha pergunta 3",
            "Minha pergunta 4",
            "Minha pergunta 5"
    };

    // IDs das respostas corretas
    int[] respostasCorretas = {
            R.id.rb_a, // Pergunta 1 -> rb_a
            R.id.rb_a, // Pergunta 2 -> rb_a
            R.id.rb_b, // Pergunta 3 -> rb_b
            R.id.rb_d, // Pergunta 4 -> rb_d
            R.id.rb_c  // Pergunta 5 -> rb_c
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        
        txvPergunta = findViewById(R.id.txvPerguntas);
        rdgPrincipal = findViewById(R.id.rbg_principal);
        btnResponder = findViewById(R.id.btn_responder);

        carregarPergunta();

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) ->{
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        private void carregarPerguntas(){
            if(indicePergunta < perguntas.length){
                txvPergunta.setText((perguntas[indicePergunta]));
                rdgPrincipal.clearCheck();
                btnResponder.setEnabled(true);

            } else {
                txvPergunta.setText("Fim");
                btnResponder.setEnabled(false);
            }
        }

        //btnReiniciar.setOnClickListener(v -> reiniciarQuiz());

        btnReiniciar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                reiniciarQuiz();
            }
        });
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });}
    private void carregarPergunta() {
        if (indicePergunta < perguntas.length) {
            txvPergunta.setText(perguntas[indicePergunta]);
            rdgPrincipal.clearCheck();
            btnResponder.setEnabled(true);

        } else {
            txvPergunta.setText("Quiz finalizado!");
            btnResponder.setEnabled(false);
        }
    }

    private void verificarResposta() {
        if (indicePergunta >= perguntas.length) return;

        int respostaCorreta = respostasCorretas[indicePergunta];
        int respostaSelecionada = rdgPrincipal.getCheckedRadioButtonId();

        if (respostaSelecionada == -1) {
            Toast.makeText(this, "Selecione uma resposta!", Toast.LENGTH_SHORT).show();
            return;
        }

        if (respostaSelecionada == respostaCorreta) {
            pontuacao++;
            Toast.makeText(this, "Correto!", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Errado!", Toast.LENGTH_SHORT).show();
        }
        btnResponder.postDelayed(() -> {
            indicePergunta++;
            carregarPergunta();
        }, 8000);
    }

    private void reiniciarQuiz(){
        pontuacao = 0;
        indicePergunta = 0;
        carregarPergunta();
    }


}

