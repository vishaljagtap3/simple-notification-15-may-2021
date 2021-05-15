package in.bitcode.simplenotifications;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import android.annotation.SuppressLint;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Button mBtnNotify, mBtnCancel;
    private NotificationManager mNotificationManager;

    public static final int NOTIFICATION_BITCODE_ALERT = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();

        mBtnNotify.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View v) {
                @SuppressLint({"NewApi", "LocalSuppress"})
                NotificationCompat.Builder builder =
                        new NotificationCompat.Builder(
                                MainActivity.this,
                                getNotificationChannelId()
                        );

                builder.setContentTitle("BitCode");
                builder.setContentText("You are android expert now!");
                builder.setTicker("Notification from BitCode!");

                builder.setColor(Color.RED);
                builder.setSmallIcon(R.mipmap.ic_launcher);

                builder.setLights(Color.RED, 400, 300);
                builder.setVibrate( new long[]{400, 200, 400, 200, 400, 200, 400, 300} );
                //builder.setSound(Uri.parse("path to the audio file"));
                builder.setVisibility(NotificationCompat.VISIBILITY_PUBLIC);

                //builder.setAutoCancel(true);
                builder.setNumber(9);
                builder.setCategory(NotificationCompat.CATEGORY_EVENT);

                Notification notification = builder.build();
                mNotificationManager.notify(NOTIFICATION_BITCODE_ALERT, notification);
            }
        });

        mBtnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mNotificationManager.cancel(NOTIFICATION_BITCODE_ALERT);
            }
        });
    }

    private void init() {
        mBtnNotify = findViewById(R.id.btnNotify);
        mBtnCancel = findViewById(R.id.btnCancel);

        mNotificationManager =
                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public String getNotificationChannelId() {
        @SuppressLint({"NewApi", "LocalSuppress"})
        NotificationChannel notificationChannel =
                new NotificationChannel(
                        "batch_alerts",
                        "Batch Alerts",
                        NotificationManager.IMPORTANCE_HIGH
                );
        notificationChannel.setShowBadge(true);
        mNotificationManager.createNotificationChannel(notificationChannel);
        return "batch_alerts";
    }
}