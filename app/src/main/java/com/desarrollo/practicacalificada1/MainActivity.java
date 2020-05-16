package com.desarrollo.practicacalificada1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity {

    Button button_clean, button_process;
    EditText field_first_phrase, field_second_phrase;
    TextView text_equals_value, fp_closed_vowels_value, sp_closed_vowels_value;
    LinearLayout result_wrapper;
    int number_fp = 0;
    int number_sp = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toast.makeText(this, "onCreate", Toast.LENGTH_SHORT).show();
        loadViews();
        enabledButtons(true);

        button_process.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String first_phrase = field_first_phrase.getText().toString();
                String second_phrase = field_second_phrase.getText().toString();
                if(first_phrase.isEmpty())
                {
                    Toast.makeText(MainActivity.this, "Ingrese primera frase", Toast.LENGTH_SHORT).show();
                }
                else if(second_phrase.isEmpty())
                {
                    Toast.makeText(MainActivity.this, "Ingrese segunda frase", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    processSentences(first_phrase, second_phrase);
                }
            }
        });

        button_clean.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clean();
                enabledButtons(true);
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        Toast.makeText(this, "onStart", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onResume() {
        super.onResume();
        Toast.makeText(this, "onResume", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onPause() {
        Toast.makeText(this, "onPause", Toast.LENGTH_SHORT).show();
        super.onPause();
    }

    @Override
    protected void onStop() {
        Toast.makeText(this, "onStop", Toast.LENGTH_SHORT).show();
        super.onStop();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Toast.makeText(this, "onRestart", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onDestroy() {
        Toast.makeText(this, "onDestroy", Toast.LENGTH_SHORT).show();
        super.onDestroy();
    }

    private void loadViews()
    {
        button_clean = findViewById(R.id.button_clean);
        button_process = findViewById(R.id.button_process);
        field_first_phrase = findViewById(R.id.field_first_phrase);
        field_second_phrase = findViewById(R.id.field_second_phrase);
        text_equals_value = findViewById(R.id.text_equals_value);
        fp_closed_vowels_value = findViewById(R.id.fp_closed_vowels_value);
        sp_closed_vowels_value = findViewById(R.id.sp_closed_vowels_value);
        result_wrapper = findViewById(R.id.result_wrapper);
        result_wrapper.setVisibility(View.INVISIBLE);
    }

    private void processSentences(String first_phrase, String second_phrase)
    {
        number_fp = 0;
        number_sp = 0;
        String equals_value = first_phrase.equals(second_phrase) ? "SI" : "NO";
        text_equals_value.setText(equals_value);
        Pattern closed_vowel_pattern = Pattern.compile("[IiíUu]");
        Matcher matcher_fp = closed_vowel_pattern.matcher(first_phrase);
        Matcher matcher_sp = closed_vowel_pattern.matcher(second_phrase);
        while (matcher_fp.find()) number_fp++;
        while (matcher_sp.find()) number_sp++;
        fp_closed_vowels_value.setText(String.valueOf(number_fp));
        sp_closed_vowels_value.setText(String.valueOf(number_sp));
        enabledButtons(false);
        result_wrapper.setVisibility(View.VISIBLE);
    }

    private void clean()
    {
        field_first_phrase.setText("");
        field_second_phrase.setText("");
        result_wrapper.setVisibility(View.INVISIBLE);
        number_fp = 0;
        number_sp = 0;
    }

    private void enabledButtons(boolean value)
    {
        button_clean.setEnabled(!value);
    }
}
