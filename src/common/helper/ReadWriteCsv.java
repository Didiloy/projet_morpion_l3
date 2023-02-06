package common.helper;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class ReadWriteCsv {
    private final String file;
    public int draw = 0;
    public int computer = 0;
    public int player = 0;

    public ReadWriteCsv(String file) {
        this.file = file;

    }

    public void readFile() throws FileNotFoundException {
        File f = new File(this.file);
        Scanner s = new Scanner(f);
        s.useDelimiter(",");
        if (s.hasNextInt())
            this.draw = s.nextInt();
        if (s.hasNextInt())
            this.computer = s.nextInt();
        if (s.hasNextInt())
            this.player = s.nextInt();
        s.close();
    }

    public void writeFile() {
        try {
            FileWriter myWriter = new FileWriter(this.file);
            myWriter.write(this.draw + "," + this.computer + "," + this.player);
            myWriter.close();
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
}
