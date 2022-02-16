import java.io.PrintWriter;
import java.io.IOException;
import java.io.*;

public class File {
    private String name;

    public File(String name)
    {
        this.name = name;
    }

    public File()
    {
        this.name = "Undefined";
    }

    public void changeData(String pesel, String oldLine, String newLine)
    {
        PrintWriter writer = null;
        String newContent = "";
        try (BufferedReader reader = new BufferedReader(new FileReader(this.name))) {
            for(int i = 0; i < this.numOfLines(); i++) {
                String line = reader.readLine();
                String[] splitLine = line.split(",");
                if(splitLine[2].equals(pesel) || oldLine.equals(line)) {
                    if(oldLine.equals(line)) {
                        newContent += newLine + System.lineSeparator();
                    }
                    line = "#DELETED " + line;
                }
                newContent += line + System.lineSeparator();
            }
            writer = new PrintWriter(this.name, "UTF-8");
            writer.print(newContent);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void addData(String[] inputVal)
    {
        try (FileWriter f = new FileWriter(this.name, true); BufferedWriter b = new BufferedWriter(f); PrintWriter p = new PrintWriter(b);){ 
            inputVal[5] += inputVal[0] + ',' + inputVal[1] + ',' + inputVal[2] + ',' + inputVal[3] + ',' + inputVal[4];
            p.println(inputVal[5]);
        } catch (IOException e) { e.printStackTrace(); }
    }

    public long numOfLines()
    {
        long lines = 0;
        try (BufferedReader reader = new BufferedReader(new FileReader(this.name))) {
            while (reader.readLine() != null) {
                lines++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return lines;
    }
}
