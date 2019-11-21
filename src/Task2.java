import java.io.*;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Task2 {
    private static final Logger LOGGER = Logger.getLogger(Task2.class.getName());
    static {
        LOGGER.setLevel(Level.FINE);
    }
    private static void generateTestData() {
        for (int i = 0; i < 5; i++) {
            try (FileOutputStream out = new FileOutputStream("./data/task2/source" + i + ".txt")) {
                for (int j = 0; j < 100; j++) {
                    out.write(i+1);
                }
            } catch (IOException ex) {
                LOGGER.log(Level.SEVERE, ex.getMessage());
            }
        }
    }
    public static void main(String[] args) {
       generateTestData();
        Vector<InputStream> files = new Vector<>();
        try {
        for (int i = 0; i < 5; i++) {
            FileInputStream in = new FileInputStream("./data/task2/source" + i + ".txt");
                files.add(in);
            }
         Enumeration<InputStream> en=files.elements();
        SequenceInputStream in=new SequenceInputStream(en);
            FileOutputStream out = new FileOutputStream("./data/task2/result.txt", true);
                    int n;
                    while ((n = in.read()) != -1) {
                        out.write(n);
                    }
                  }
catch (IOException ex) {
                        LOGGER.log(Level.SEVERE, ex.getMessage());
                    }

        StringBuilder view=new StringBuilder();
        try (FileInputStream resIn = new FileInputStream("./data/task2/result.txt"))
        {
            int n=0;
            while ((n = resIn.read()) != -1)
            {
                view.append(n+" ");
            }

        }
        catch (IOException ex)
    {
        LOGGER.log(Level.SEVERE, ex.getMessage());
    }
        System.out.println(view);
}


}
