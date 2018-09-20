package by.itacademy.palina.homework.hw1;

import android.app.Activity;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import by.itacademy.palina.homework.R;

public class HW1 extends Activity implements View.OnClickListener {
    private TextView textView1;
    private TextView textView2;
    private Button btnClick;
    private View.OnClickListener listener = new View.OnClickListener() {
        public void onClick(View v) {
            changer();
        }
    };

    private void changer() {
        CharSequence temp = textView1.getText();
        textView1.setText(textView2.getText());
        textView2.setText(temp);
        int backgroundColor = ((ColorDrawable) btnClick.getBackground()).getColor();
        btnClick.setBackgroundColor(((ColorDrawable) textView2.getBackground()).getColor());
        textView2.setBackgroundColor(((ColorDrawable) textView1.getBackground()).getColor());
        textView1.setBackgroundColor(backgroundColor);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hw1);

        textView1 = findViewById(R.id.textView1);
        textView2 = findViewById(R.id.textView2);
        btnClick = findViewById(R.id.btnClick);

        btnClick.setOnClickListener(this);

        textView2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                changer();
            }
        });

        textView1.setOnClickListener(listener);
    }

    @Override
    public void onClick(View v) {
        changer();
    }
}
