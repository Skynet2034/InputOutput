import java.io.*;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Task3 {
    private static final Logger LOGGER = Logger.getLogger(Task3.class.getName());
  private static final int PAGE_SIZE=1800;
    static {
        LOGGER.setLevel(Level.FINE);
    }
    private static void generateTestData()
    {

    }
private static void readPage(RandomAccessFile file, long pageNumber, long pagesTotal) throws IOException {
    if ((pageNumber < 0) || (pageNumber > pagesTotal))
    { System.out.println("No such page"); return;}
    long start=System.currentTimeMillis();
    long readLegth=PAGE_SIZE;
    if ((pageNumber+1)*PAGE_SIZE>file.length()) readLegth=file.length()-pageNumber*PAGE_SIZE;//���� �� ����� ����� ������ 1 ������ ��������
    file.seek(pageNumber*PAGE_SIZE);
    byte[] rawPage=new byte[(int)readLegth];
    file.readFully(rawPage);
    String page=new String(rawPage, "CP1251");
    System.out.println(page);
    System.out.printf("Page loaded in "+(System.currentTimeMillis()-start)+" milliseconds");
    System.out.println();
   // file.seek(pageNumber*PAGE_SIZE+readLegth);
}
private static void printInfo(File fileName, long pageCurrent, long pagesTotal)
{
    if (pageCurrent==-1) pageCurrent=0;
    System.out.println("File name - "+fileName.getName()+" page "+(pageCurrent+1)+" of "+(pagesTotal+1));
    System.out.println("Press N for next page, P for previous page, X for exit or enter page number");
}
    public static void main(String[] args) {

        File fileName = new File("./data/task3/test1.txt");
        long start=System.currentTimeMillis();
exit:
        try (RandomAccessFile file=new RandomAccessFile(fileName,"r"); Scanner in=new Scanner(System.in)) {
            long length = file.length();
            long pagesTotal = length / PAGE_SIZE;
            long pageCurrent = -1;
            System.out.printf("File loaded in "+(System.currentTimeMillis()-start)+" milliseconds");
            System.out.println();
            while (true) {
                printInfo(fileName, pageCurrent, pagesTotal);
                long position = file.getFilePointer();
               //readPage(file,pageCurrent, pagesTotal);
                String page;
                String input = in.nextLine().trim().toLowerCase();
                if (input.matches("[0-9]+")) {
                    int pageNumber = Integer.valueOf(input)-1;
                    readPage(file, pageNumber, pagesTotal);
                    } else if ((input.matches("[npx]")) && (input.length() == 1)) {
                    char tmp = input.charAt(0);
                    switch (tmp) {
                        case 'n': {readPage(file, pageCurrent + 1, pagesTotal); break;}
                        case 'p':{readPage(file, pageCurrent - 1, pagesTotal); break;}
                        case 'x': break exit;
                             }
                }
                else System.out.println("Bad command");
                 pageCurrent = file.getFilePointer() / PAGE_SIZE;
            }

        }
        catch (IOException ex)
        {
            LOGGER.log(Level.SEVERE, ex.getMessage());
        }

}


}
