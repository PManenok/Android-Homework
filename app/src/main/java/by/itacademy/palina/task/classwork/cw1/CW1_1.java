package by.itacademy.palina.task.classwork.cw1;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;


import com.squareup.picasso.Picasso;

import by.itacademy.palina.task.R;

public class CW1_1 extends Activity {
    public static final String EXTRA_USERNAME = "EXTRA_USERNAME";
    public static final String EXTRA_PASSWORD = "EXTRA_PASSWORD";

    public static void show(Activity activity, String username, String password) {
        Intent intent = new Intent(activity, CW1_1.class);
        intent.putExtra(CW1_1.EXTRA_USERNAME, String.valueOf(username));//login
        intent.putExtra(CW1_1.EXTRA_PASSWORD, String.valueOf(password));//password
        activity.startActivity(intent);
        //activity.overridePendingTransition(R.anim,R.anim); анимация переключения активити
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cw1_1);
        Intent intent = getIntent();
        String username = intent.getStringExtra(EXTRA_USERNAME);
        String password = intent.getStringExtra(EXTRA_PASSWORD);
        TextView textView = findViewById(R.id.loginTV);
        textView.setText(username + " " + password);
        ImageView image1 = findViewById(R.id.myImage);
        Picasso.get().load("http://i.imgur.com/DvpvklR.png").into(image1);
    }

    @Override
    protected void onResume() {
        super.onResume();
        // stop animation
    }

    @Override
    protected void onPause() {
        super.onPause();
        //continue animation
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        //activity.overridePendingTransition(R.anim,R.anim);
    }
}
