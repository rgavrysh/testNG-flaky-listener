package db;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class DBase {
    public static Map<String, Test> testsDB = new HashMap<String, Test>();

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
        String fileName = "testResultsDB.txt";
        File db = new File(fileName);
        if (!db.exists()) {
            try {
                db.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        FileWriter fw = null;
        try {
            fw = new FileWriter(db.getAbsoluteFile(), true);
        } catch (IOException e) {
            e.printStackTrace();
        }
        BufferedWriter bw = new BufferedWriter(fw);
        PrintWriter out = new PrintWriter(bw);
        String data = "";
        for (Test test : testsDB.values()) {
            data += test.getBuildRun() + " :\t" + test.getName() + " : \t\t" + test.getFlaky() + "\n\r";
        }
        out.println(data);
        out.close();
    }
}
