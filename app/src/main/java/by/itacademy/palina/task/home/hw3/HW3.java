package by.itacademy.palina.task.home.hw3;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import by.itacademy.palina.task.R;

public class HW3 extends Activity {
    ImageView image;
    EditText editText;
    ProgressBar progressBar;

    private View.OnClickListener listener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            downloadPic();
        }
    };

    private View.OnClickListener listener2 = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            clear();
        }
    };

    //http://www.androidwallpper.com/wp-content/uploads/2017/02/High-Resolution-4K-Wallpaper-Phone-Wallpapers-V4KU.jpg
    //http://science.psu.edu/alert/images/B-OmegaNebulaComplex.jpg
    private void downloadPic() {
        progressBar.setVisibility(View.VISIBLE);
        Picasso.get().load(String.valueOf(editText.getText())).transform(new PicassoCircleTransformation()).into(image, new Callback() {
            @Override
            public void onSuccess() {
                if (progressBar != null)
                    progressBar.setVisibility(View.GONE);
            }

            @Override
            public void onError(Exception e) {
                Toast.makeText(HW3.this, e.getMessage(), Toast.LENGTH_LONG).show();
            }
        });

    }

    private void clear() {
        editText.setText("");
        image.setImageDrawable(null);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hw3);

        progressBar = findViewById(R.id.hw3ProgressBar);
        image = findViewById(R.id.hw3ImageView);
        editText = findViewById(R.id.hw3EditText);

        Button button1 = findViewById(R.id.hw3BtnReady);
        button1.setOnClickListener(listener);

        Button button2 = findViewById(R.id.hw3BtnClear);
        button2.setOnClickListener(listener2);
    }
}
