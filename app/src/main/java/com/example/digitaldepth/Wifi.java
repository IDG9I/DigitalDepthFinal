package com.example.digitaldepth;

import android.os.Bundle;
import android.os.Handler;
import android.app.Activity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import kotlinx.coroutines.Delay;

public class Wifi extends AppCompatActivity {

    private TextView txtPing;
    private Handler handler = new Handler();

    public String DNS = "8.8.8.8";
    Button btnMonitoriar;
    EditText txtDNSs;
    TextView textDNS;

    Switch swDetener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wifi);

        txtPing = findViewById(R.id.txtPing);
        btnMonitoriar = (Button)findViewById(R.id.btnMonitoriar);
        txtDNSs = findViewById(R.id.txtDNSs);
        textDNS = findViewById(R.id.textDNS);



        btnMonitoriar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                DNS = txtDNSs.getText().toString();
                textDNS.setText(DNS);
            }
        });

        Runnable pingRunnable = new Runnable() {
            @Override
            public void run() {
                if(isActivityRunning){
                    try {
                        Process process = Runtime.getRuntime().exec("ping -c 1 "+DNS);
                        BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
                        String line;
                        String pingResult = "";
                        while ((line = reader.readLine()) != null) {
                            if (line.contains("time=")) {
                                int startIndex = line.indexOf("time=") + 5;
                                int endIndex = line.indexOf(" ms");
                                if (startIndex >= 0 && endIndex >= 0) {
                                    pingResult = line.substring(startIndex, endIndex);
                                    break;
                                }
                            }
                        }
                        int exitCode = process.waitFor();
                        if (!pingResult.isEmpty() && exitCode == 0) {
                            String finalPingResult = pingResult;
                            handler.post(new Runnable() {
                                @Override
                                public void run() {
                                    txtPing.setText(finalPingResult);
                                }
                            });
                        } else {
                            handler.post(new Runnable() {
                                @Override
                                public void run() {
                                    txtPing.setText("ERROR");
                                }
                            });
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

                handler.postDelayed(this, 1000); //segundos
            }
        };

        new Thread(pingRunnable).start();

    }

    private boolean isActivityRunning = true;
    @Override
    protected void onPause() {
        super.onPause();
        isActivityRunning = false;
    }

    @Override
    protected void onResume() {
        super.onResume();
        isActivityRunning = true;
    }
}