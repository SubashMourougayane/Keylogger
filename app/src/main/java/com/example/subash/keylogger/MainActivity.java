package com.example.subash.keylogger;

import android.app.ActivityManager;
import android.content.Context;
import android.os.Process;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.List;

public class MainActivity extends AppCompatActivity
{
    Button button;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button = (Button)findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ActivityManager am = (ActivityManager)getApplicationContext().getSystemService(Context.ACTIVITY_SERVICE);

                List<ActivityManager.RunningAppProcessInfo> pids = am.getRunningAppProcesses();
                System.out.println("&&&&&&&&&  BEFORE  &&&&&&&&");
                for(int i=0;i<pids.size();i++)
                {
                    ActivityManager.RunningAppProcessInfo info = pids.get(i);
                    System.out.println("Process  "+i+" --> "+info.processName);

                    if (info.processName.equalsIgnoreCase("com.example.subash.vision"))
                    {
                        System.out.println("Killing "+info.processName);
                        Process.sendSignal(info.pid,Process.SIGNAL_KILL);
                    }
                }
                System.out.println("&&&&&&&&&  AFTER  &&&&&&&&");
                for(int i=0;i<pids.size();i++)
                {
                    ActivityManager.RunningAppProcessInfo info = pids.get(i);
                    System.out.println("Process  "+i+" --> "+info.processName);

                }


            }
        });
    }
}
