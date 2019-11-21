import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Task3 {
    private static final Logger LOGGER = Logger.getLogger(Task3.class.getName());
    static {
        LOGGER.setLevel(Level.FINE);
    }
    private static void generateTestData()
    {}

    public static void main(String[] args) {
       byte[] res=new byte[50];
        try (FileOutputStream out=new FileOutputStream("./data/task1/test1.txt"))
        {
for (byte j=0; j<50;j++)
{
    out.write(j);
}
        }
        catch (IOException ex)
        {
            LOGGER.log(Level.SEVERE, ex.getMessage());
        }

        try (FileInputStream out=new FileInputStream("./data/task1/test1.txt")) {
            int i = 0;
            int n;
            while ((n = out.read()) != -1)
            {
                res[i] =(byte) n;
                i++;
            }
        }
        catch (IOException ex)
    {
        LOGGER.log(Level.SEVERE, ex.getMessage());
    }
        System.out.println(Arrays.toString(res));
}


}
