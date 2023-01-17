import javax.swing.*;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;

import static java.nio.file.StandardOpenOption.CREATE;

public class PersonReader
{

    public static void main(String[] args)
    {
        JFileChooser chooser = new JFileChooser();

        File selectedFile;

        String data = "";

        try
        {
            File PersonData = new File(System.getProperty("user.dir"));
            chooser.setCurrentDirectory(PersonData);

            if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION)
            {
                selectedFile = chooser.getSelectedFile();
                Path file = selectedFile.toPath();

                InputStream in =
                        new BufferedInputStream(Files.newInputStream(file, CREATE));
                BufferedReader reader =
                        new BufferedReader(new InputStreamReader(in));

                int line = 0;

                System.out.printf("| %-5s | %-40s |%n", "Line", "ID,  FName,  LName,  Title,  YOB");
                System.out.printf("----------------------------------------------------%n");

                while (reader.ready())
                {
                    data = reader.readLine();
                    line++;
                    System.out.printf("| %-5s | %-40s |%n", line, data);
                }
                reader.close();
                System.out.println("\n\nfile read");
            }
        }
        catch (FileNotFoundException e)
        {
            System.out.println("file not found");
            e.printStackTrace();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

}
