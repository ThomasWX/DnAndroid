package com.android.opt7;

import android.app.job.JobParameters;
import android.app.job.JobService;

public class MyJobService extends JobService {
    @Override
    public boolean onStartJob(JobParameters params) {
        // 耗时耗电任务


        return false;
    }

    @Override
    public boolean onStopJob(JobParameters params) {
        // 在任务完成时调用jobFinished，该方法便会触发onStopJob
        return false;
    }
}
