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
import by.itacademy.palina.task.classwork.cw4.CW4;

public class MainActivity extends Activity {
    private View.OnClickListener listener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = null;
            String[] taskName = String.valueOf(((Button) v).getText()).split(" ");
            try {
                Class anotherActivity;
                if (taskName[0].equalsIgnoreCase("homework"))
                    anotherActivity = Class.forName(getPackageName() + ".home.hw" + taskName[1] + ".HW" + taskName[1]);
                else
                    anotherActivity = Class.forName(getPackageName() + ".classwork.cw" + taskName[1] + ".CW" + taskName[1]);
                intent = new Intent(MainActivity.this, anotherActivity);
            } catch (ClassNotFoundException e) {
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

        Button btnHW1 = findViewById(R.id.btnHW1);
        btnHW1.setOnClickListener(listener);
        Button btnHW2 = findViewById(R.id.btnHW2);
        btnHW2.setOnClickListener(listener);
        Button btnHW3 = findViewById(R.id.btnHW3);
        btnHW3.setOnClickListener(listener);
        Button btnHW4 = findViewById(R.id.btnHW4);
        btnHW4.setOnClickListener(listener);
        Button btnHW5 = findViewById(R.id.btnHW5);
        btnHW5.setOnClickListener(listener);
        Button btnHW6 = findViewById(R.id.btnHW6);
        btnHW6.setOnClickListener(listener);
        Button btnHW7 = findViewById(R.id.btnHW7);
        btnHW7.setOnClickListener(listener);

        Button btnCW1 = findViewById(R.id.btnCW1);
        btnCW1.setOnClickListener(listener);
        Button btnCW2 = findViewById(R.id.btnCW2);
        btnCW2.setOnClickListener(listener);
        Button btnCW3 = findViewById(R.id.btnCW3);
        btnCW3.setOnClickListener(listener);
        Button btnCW4 = findViewById(R.id.btnCW4);
        btnCW4.setOnClickListener(listener);
    }
}
