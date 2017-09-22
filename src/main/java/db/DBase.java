package db;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class DBase {
    public static Map<String, Test> testsDB = new HashMap<String, Test>();
    private static final String FILE_NAME = "testResultsDB.txt";

    public DBase() {
        testsDB.put("testNumberComparing", new Test(false, 1, "testNumberComparing", 1));
        testsDB.put("testBoolean", new Test(true, 1, "testBoolean", 2));
        testsDB.put("testObjectNotNull", new Test(false, 1, "testObjectNotNull", 3));
        testsDB.put("testStringMatching", new Test(false, 1, "testStringMatching", 4));
    }

    public static void updateDB(String method, Test test) {
        testsDB.put(method, test);
    }

    public static void write() {
        File db = new File(FILE_NAME);
        createFileIfNotExist(db);
        FileWriter fw = null;
        try {
            fw = new FileWriter(db.getAbsoluteFile(), true);
        } catch (IOException e) {
            System.err.println(DBase.class.getName() + ":\t FileWriter can not be instantiated with file " + db.getName() + ".\n" +
                    "Test results will not be written into DB.");
            e.printStackTrace();
        }
        PrintWriter out = new PrintWriter(new BufferedWriter(fw));
        StringBuffer data = new StringBuffer();
        for (Test test : testsDB.values()) {
            data.append(test.getBuildRun() + " :\t" + test.getName() + " : \t\t" + test.getFlaky() + "\n\r");
        }
        out.println(data);
        out.close();
    }

    private static void createFileIfNotExist(File db) {
        if (!db.exists()) {
            try {
                db.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
