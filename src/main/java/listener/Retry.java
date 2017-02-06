package listener;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class Retry implements IRetryAnalyzer {

    private int retryCount = 0;
    private int maxRetryCount = 2;

    public boolean retry(ITestResult iTestResult) {
        if (retryCount < maxRetryCount) {
            System.out.println("Retry test " + iTestResult.getName() + " with status " + iTestResult.getStatus() +
                    " for the " + (retryCount + 1) + " time(s).");
            retryCount++;
            return true;
        }
        return false;
    }
}
