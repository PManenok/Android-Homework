package by.itacademy.palina.task.home.hw4;

import android.app.Activity;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import by.itacademy.palina.task.R;

public class HW4 extends Activity {
    AnimationDrawable owlAnimation;
    EditText text;
    PieDiagram pie;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hw4);
        ImageView owl = findViewById(R.id.hw4ImageView);
        owlAnimation = (AnimationDrawable) owl.getBackground();
        if (owlAnimation != null) {
            owlAnimation.start();
        }
        text = findViewById(R.id.hw4EnterNums);
        pie = findViewById(R.id.hw4Pie);

        Button draw = findViewById(R.id.hw4btnDraw);
        draw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String numString = String.valueOf(text.getText());
                String[] nums = numString.split(" ");
                Integer[] array = new Integer[nums.length];
                for (int i = 0; i < nums.length; i++) {
                    array[i] = Integer.parseInt(nums[i]);
                }
                pie.setIntArray(array);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        owlAnimation.start();
    }

    @Override
    protected void onPause() {
        super.onPause();
        owlAnimation.stop();
    }

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.left_in, R.anim.right_out);
    }
}
