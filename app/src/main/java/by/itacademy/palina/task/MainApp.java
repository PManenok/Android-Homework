package by.itacademy.palina.task;

import android.app.Application;

import com.squareup.leakcanary.LeakCanary;

public class MainApp extends Application {
    @Override
    public void onCreate() {
        {
            super.onCreate();
            if (LeakCanary.isInAnalyzerProcess(this)) {
                return;
            }
            LeakCanary.install(this); //install(this);
        }
    }
}
