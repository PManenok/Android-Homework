package by.itacademy.palina.homework.classwork.cw1;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;


import com.squareup.picasso.Picasso;

import by.itacademy.palina.homework.R;

public class AnotherActivity extends Activity {
    public static final String EXTRA_USERNAME = "EXTRA_USERNAME";
    public static final String EXTRA_PASSWORD = "EXTRA_PASSWORD";

    public static void show(Activity activity, String username, String password) {
        Intent intent = new Intent(activity, AnotherActivity.class);
        intent.putExtra(AnotherActivity.EXTRA_USERNAME, String.valueOf(username));//login
        intent.putExtra(AnotherActivity.EXTRA_PASSWORD, String.valueOf(password));//password
        activity.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.anotherActivity);
        Intent intent = getIntent();
        String username = intent.getStringExtra(EXTRA_USERNAME);
        String password = intent.getStringExtra(EXTRA_PASSWORD);
        TextView textView = findViewById(R.id.loginTV);
        textView.setText(username + " " + password);
        ImageView image1 = findViewById(R.id.myImage);
        Picasso.get().load("http://i.imgur.com/DvpvklR.png").into(image1);
    }
}
