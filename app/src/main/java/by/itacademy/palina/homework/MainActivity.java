package by.itacademy.palina.homework;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import by.itacademy.palina.homework.classwork.cw1.CW1;

public class MainActivity extends AppCompatActivity {
    private View.OnClickListener listener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Class clazz = MainActivity.class;
            String numHW = String.valueOf(((Button) v).getText()).split(" ")[1];
            try {
                clazz = Class.forName(getPackageName() + ".hw" + numHW + ".HW" + numHW);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            Intent intent = new Intent(MainActivity.this, clazz);
            startActivity(intent);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Button btnHW1 = findViewById(R.id.btnHW1);
        btnHW1.setOnClickListener(listener);

        final Button btnHW2 = findViewById(R.id.btnHW2);
        btnHW2.setOnClickListener(listener);

        Button btnCW = findViewById(R.id.btnCW);
        btnCW.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, CW1.class);
                startActivity(intent);
            }
        });
    }
}
