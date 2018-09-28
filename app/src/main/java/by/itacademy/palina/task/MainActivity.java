package by.itacademy.palina.task;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import by.itacademy.palina.task.classwork.CW1;

public class MainActivity extends Activity {
    private View.OnClickListener listener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = null;
            //by.itacademy.palina.task
            try {
                String numHW = String.valueOf(((Button) v).getText()).split(" ")[1];
                Class clazz = Class.forName(getPackageName() + ".home.hw" + numHW + ".HW" + numHW);
                intent = new Intent(MainActivity.this, clazz);
            } catch (ClassNotFoundException e) {
                Toast.makeText(MainActivity.this, getPackageName(), Toast.LENGTH_SHORT).show();
                e.printStackTrace();
            }
            if (intent != null) {
                startActivity(intent);
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
