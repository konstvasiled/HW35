package core;

import java.io.*;
import java.util.*;
import org.testng.*;
import org.testng.annotations.*;
import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;
import java.lang.reflect.Method;

public class PrimeNumberCheckerTest implements ITest {
    String csvFile = "./src/test/java/core/primes.csv";
    private String test_name = "";
    public String getTestName() {
        return test_name;
    }
    private void setTestName(String a) {
        test_name = a;
    }
    @BeforeMethod(alwaysRun = true)
    public void beforemethod(Method method, Object[] parameters) {
        setTestName(method.getName());
        Override a = method.getAnnotation(Override.class);
        String testCaseId = (String) parameters[a.id()];
        setTestName(testCaseId);
    }
    @DataProvider(name = "prime number")
    public Iterator<String[]> data() throws IOException {
        String cvsLine = "";
        String[] a = null;
        ArrayList<String[]> arl = new ArrayList<>();
        BufferedReader read = new BufferedReader(new FileReader(csvFile));
        while ((cvsLine = read.readLine()) != null) {
            a = cvsLine.split(",");
            arl.add(a);
        }
        read.close();
        return arl.iterator();
    }
    @Override
    @Test(timeOut = 1000, dataProvider = "prime number")
    public void test(String a, String b, String c) {
        assertThat(PrimeNumberChecker.validate(Integer.parseInt(b)), equalTo(Boolean.parseBoolean(c)));
    }
}
