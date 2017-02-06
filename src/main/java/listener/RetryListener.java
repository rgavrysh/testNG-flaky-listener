package listener;

import annotation.Flaky;
import db.DBase;
import db.Test;
import org.testng.*;
import org.testng.annotations.ITestAnnotation;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

public class RetryListener implements IAnnotationTransformer, ITestListener {

    public void transform(ITestAnnotation iTestAnnotation, Class aClass, Constructor constructor, Method method) {
        IRetryAnalyzer retry = iTestAnnotation.getRetryAnalyzer();

        if (retry == null) {
            iTestAnnotation.setRetryAnalyzer(Retry.class);
        }
    }

    public void onTestStart(ITestResult iTestResult) {

    }

    public void onTestSuccess(ITestResult iTestResult) {

    }

    public void onTestFailure(ITestResult iTestResult) {


    }

    public void onTestSkipped(ITestResult iTestResult) {

    }

    public void onTestFailedButWithinSuccessPercentage(ITestResult iTestResult) {

    }

    public void onStart(ITestContext iTestContext) {
        System.out.println("on start");
        for (ITestNGMethod method : iTestContext.getAllTestMethods()) {
            boolean isAnnotated = method.getConstructorOrMethod().getMethod().isAnnotationPresent(Flaky.class);
            if ((isAnnotated && method.getConstructorOrMethod().getMethod().getAnnotation(Flaky.class).value()) ||
                    DBase.testsDB.get(method.getMethodName()).getFlaky()) {
                try {
                    method.setRetryAnalyzer(Retry.class.newInstance());
                } catch (InstantiationException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void onFinish(ITestContext iTestContext) {
        Integer buildRun = (int) iTestContext.getEndDate().getTime();
        for (ITestNGMethod method : iTestContext.getAllTestMethods()) {
            boolean isFailed = !iTestContext.getFailedTests().getResults(method).isEmpty();
            DBase.updateDB(method.getMethodName(), new Test(isFailed, buildRun, method.getMethodName(), 1));
        }
        DBase.write();
    }
}
