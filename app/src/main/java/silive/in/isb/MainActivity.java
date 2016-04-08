package silive.in.isb;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    public Intent i;
    Button startService, stopService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        i = new Intent(this, Head.class);

        startService = (Button) findViewById(R.id.hd);
        stopService = (Button) findViewById(R.id.hs);

        startService.setOnClickListener(this);
        stopService.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.hd)
            startService(i);
        if (v.getId() == R.id.hs)
            stopService(i);

    }
}