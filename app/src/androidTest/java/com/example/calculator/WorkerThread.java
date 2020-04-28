package com.example.calculator;

import android.content.Context;
import android.util.Log;

import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.platform.app.InstrumentationRegistry;
import androidx.work.Configuration;
import androidx.work.Data;
import androidx.work.OneTimeWorkRequest;
import androidx.work.WorkInfo;
import androidx.work.WorkManager;
import androidx.work.impl.utils.SynchronousExecutor;
import androidx.work.testing.TestDriver;
import androidx.work.testing.WorkManagerTestInitHelper;

import com.example.calculator.calculator.CalculatorWorkerThread;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.concurrent.TimeUnit;

import static com.example.calculator.calculator.CalculatorWorkerThread.WORKER_ANSWER;
import static com.example.calculator.calculator.CalculatorWorkerThread.WORKER_NUMBER;
import static java.lang.Double.NaN;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

@RunWith(AndroidJUnit4.class)
public class WorkerThread {

    private static final String TEST_ENTER_FORMULA = "22+33";
    private static final double TEST_WORKER_RESULTS = 55.0;

    Context mContext;

    @Before
    public void setup() {
        mContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
        Configuration config = new Configuration.Builder()
                // Set log level to Log.DEBUG to
                // make it easier to see why tests failed
                .setMinimumLoggingLevel(Log.DEBUG)
                // Use a SynchronousExecutor to make it easier to write tests
                .setExecutor(new SynchronousExecutor())
                .build();

        // Initialize WorkManager for instrumentation tests.
        WorkManagerTestInitHelper.initializeTestWorkManager(
                mContext, config);
    }

    /*
    Test a correctly entered formula
     */
    @Test
    public void testEnterFormula() throws Exception {

        // Define data
        Data input = new Data.Builder()
                .put(WORKER_NUMBER, TEST_ENTER_FORMULA)
                .build();

        // Create request
        OneTimeWorkRequest request = new OneTimeWorkRequest.Builder(CalculatorWorkerThread.class)
                .setInputData(input)
                .build();

        WorkManager workManager = WorkManager.getInstance(mContext);
        // Enqueue and wait for results. THis also runs the worker
        workManager.enqueue(request).getResult().get();

        // Get Work Info and outputData.
        WorkInfo workInfo = workManager.getWorkInfoById(request.getId()).get();
        Data outputData = workInfo.getOutputData();
        // Assert
        assertThat(workInfo.getState(), is(WorkInfo.State.SUCCEEDED));
        assertThat(workInfo.getOutputData().getDouble(WORKER_ANSWER, NaN), is(TEST_WORKER_RESULTS));

    }

    /*
    Test no data entered.
     */
    @Test
    public void testNoFormulaEntered() throws Exception{
        OneTimeWorkRequest request = new OneTimeWorkRequest.Builder(CalculatorWorkerThread.class)
                .build();

        WorkManager workManager = WorkManager.getInstance(mContext);
        workManager.enqueue(request).getResult().get();

        // Get Work info
        WorkInfo workInfo = workManager.getWorkInfoById(request.getId()).get();
        // Assert
        assertThat(workInfo.getState(), is(WorkInfo.State.FAILED));
    }

    /*
    Test with delay
     */
    @Test
    public void testWithDelay() throws Exception {
        Data input = new Data.Builder()
                .put(WORKER_NUMBER, TEST_ENTER_FORMULA)
                .build();

        OneTimeWorkRequest request = new OneTimeWorkRequest.Builder(CalculatorWorkerThread.class)
                .setInputData(input)
                .setInitialDelay(10, TimeUnit.SECONDS)
                .build();

        WorkManager workManager = WorkManager.getInstance(mContext);
        TestDriver testDriver = WorkManagerTestInitHelper.getTestDriver(mContext);
        workManager.enqueue(request).getResult().get();
        testDriver.setInitialDelayMet(request.getId());
        WorkInfo workInfo = workManager.getWorkInfoById(request.getId()).get();
        Data output = workInfo.getOutputData();
        // Assert
        assertThat(workInfo.getState(), is(WorkInfo.State.SUCCEEDED));
        assertThat(workInfo.getOutputData().getDouble(WORKER_ANSWER, NaN), is(TEST_WORKER_RESULTS));

    }

}
