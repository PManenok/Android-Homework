package by.itacademy.palina.task.classwork;

import android.app.Activity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import by.itacademy.palina.task.BuildConfig;
import by.itacademy.palina.task.R;

public class CW1 extends Activity {
    private Button btnLogIn;
    private EditText loginET;
    private EditText passwordET;

    private View.OnClickListener listener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            startAnotherActivity();
        }
    };

    private void startAnotherActivity() {
        if (TextUtils.isEmpty(loginET.getText()) || TextUtils.isEmpty(passwordET.getText())) {
            Toast.makeText(this, R.string.error, Toast.LENGTH_SHORT).show();
            return;
        }

        AnotherActivity.show(this, String.valueOf(loginET.getText()), String.valueOf(passwordET.getText()));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cw1);

        btnLogIn = findViewById(R.id.btnLogIn);
        loginET = findViewById(R.id.loginET);
        passwordET = findViewById(R.id.passwordET);
        loginET.setText(BuildConfig.SERVER_URL);

        btnLogIn.setOnClickListener(listener);
    }
}
