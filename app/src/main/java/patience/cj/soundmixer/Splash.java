package patience.cj.soundmixer;

import android.content.Intent;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class Splash extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        SystemClock.sleep(1000);
        Intent startMainActivity = new Intent(this, MainActivity.class);
        startActivity(startMainActivity);
    }
}
