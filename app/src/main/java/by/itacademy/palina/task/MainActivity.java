package by.itacademy.palina.task;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import by.itacademy.palina.task.classwork.cw1.CW1;
import by.itacademy.palina.task.classwork.cw2.CW2_1;
import by.itacademy.palina.task.classwork.cw3.CW3;

public class MainActivity extends Activity {
    private View.OnClickListener listener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = null;
            String numHW = String.valueOf(((Button) v).getText()).split(" ")[1];
            try {
                Class anotherActivity = Class.forName(getPackageName() + ".home.hw" + numHW + ".HW" + numHW);
                intent = new Intent(MainActivity.this, anotherActivity);
            } catch (ClassNotFoundException e) {
                Toast.makeText(MainActivity.this, getPackageName(), Toast.LENGTH_SHORT).show();
                e.printStackTrace();
            }
            if (intent != null) {
                startActivity(intent);
                overridePendingTransition(R.anim.right_in, R.anim.left_out);
            }
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

        final Button btnHW3 = findViewById(R.id.btnHW3);
        btnHW3.setOnClickListener(listener);

        final Button btnHW4 = findViewById(R.id.btnHW4);
        btnHW4.setOnClickListener(listener);

        final Button btnHW5 = findViewById(R.id.btnHW5);
        btnHW5.setOnClickListener(listener);

        Button btnCW1 = findViewById(R.id.btnCW1);
        btnCW1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, CW1.class);
                startActivity(intent);
            }
        });
        Button btnCW2 = findViewById(R.id.btnCW2);
        btnCW2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, CW2_1.class);
                startActivity(intent);
            }
        });

        Button btnCW3 = findViewById(R.id.btnCW3);
        btnCW3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, CW3.class);
                startActivity(intent);
            }
        });
    }
}
