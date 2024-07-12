package np.edu.ncit.alarm;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
TextView textView;
private static final int FLAG=10;
private static final int REQ_CODE=100;
TextView button1;
TextView button2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        Button button1=(Button)findViewById(R.id.button);
        Button button2=(Button)findViewById(R.id.button2);
        textView=(TextView)findViewById(R.id.editTextNumber);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        AlarmManager am=(AlarmManager) getSystemService(ALARM_SERVICE);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this,"Try next alarm for now Give seconds after which you want to set alarm",Toast.LENGTH_LONG).show();

            }
        });
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int time=Integer.parseInt(textView.getText().toString());
                long trigerTime=System.currentTimeMillis()+(time*1000);
                Intent intent=new Intent(MainActivity.this,MyReceiver.class);
                PendingIntent pi=PendingIntent.getBroadcast(MainActivity.this,REQ_CODE,intent,PendingIntent.FLAG_UPDATE_CURRENT);
                Toast.makeText(MainActivity.this,"Alarm is Set for "+textView.getText().toString()+" seconds",Toast.LENGTH_LONG).show();
                am.set(AlarmManager.RTC_WAKEUP,trigerTime,pi);
            }
        });

    }
}