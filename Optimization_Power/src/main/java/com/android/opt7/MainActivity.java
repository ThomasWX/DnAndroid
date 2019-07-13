package com.android.opt7;

import android.app.job.JobInfo;
import android.app.job.JobScheduler;
import android.content.ComponentName;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.JobIntentService;
import android.support.v7.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        JobScheduler scheduler = (JobScheduler) getSystemService(Context.JOB_SCHEDULER_SERVICE);
        ComponentName service = new ComponentName(this, MyJobService.class);
        JobInfo info;
        for (int i = 0; i < 10; i++) {
            info = new JobInfo.Builder(i, service)
                    .setMinimumLatency(5000) // 最小延时 5秒
                    .setOverrideDeadline(60000) // 最多的执行时间
                    .build();
            scheduler.schedule(info);
        }

    }
}
