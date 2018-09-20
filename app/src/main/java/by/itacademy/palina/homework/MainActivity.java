package by.itacademy.palina.homework;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import by.itacademy.palina.homework.hw1.HW1;
import by.itacademy.palina.homework.hw2.HW2;

public class MainActivity extends AppCompatActivity {
    private Button btnHW1;
    private Button btnHW2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnHW1 = findViewById(R.id.btnHW1);
        btnHW1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, HW1.class);
                startActivity(intent);
            }
        });
        btnHW2 = findViewById(R.id.btnHW2);
        btnHW2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, HW2.class);
                startActivity(intent);
            }
        });
    }
}
