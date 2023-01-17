import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

import static java.nio.file.StandardOpenOption.CREATE;
/**
 * @author boyerchris
 */
public class PersonGenerator {
    public static void main(String[] args)
    {
        Scanner in = new Scanner(System.in);

        ArrayList<String> listOne = new ArrayList<>();

        String iD = "";
        String fName = "";
        String lName = "";
        String title = "";
        String csvPersonRecord = "";

        int yob = 0; //1000-9999

        boolean done = false;


        do {
            iD = SafeInput.getNonZeroLenString(in, "Please enter a 6 digit ID (000001, 000002, Etc)");
            fName = SafeInput.getNonZeroLenString(in, "Please enter your first name");
            lName = SafeInput.getNonZeroLenString(in, "Please enter your last name");
            title = SafeInput.getNonZeroLenString(in, "Please enter you title");
            yob = SafeInput.getRangedInt(in, "Please enter your year of birth", 1000, 9999);

            csvPersonRecord = iD + ", " + fName + ", " + lName + ", " + title + ", " + yob;

            listOne.add(csvPersonRecord);


            done = SafeInput.getYNConfirm(in, "Are you finished entering person data?");

        }while (!done);

        File PersonData = new File(System.getProperty("user.dir"));
        Path file = Paths.get (PersonData.getPath() + "\\src\\PersonData.CSV");

        try {
            OutputStream out =
                    new BufferedOutputStream(Files.newOutputStream(file, CREATE));
            BufferedWriter writer =
                    new BufferedWriter(new OutputStreamWriter(out));


            for (String p : listOne) {
                writer.write(p, 0, p.length());
                writer.newLine();
            }
            writer.close();

        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
}