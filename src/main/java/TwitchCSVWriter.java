import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class TwitchCSVWriter {
    private static final String GAME = "Game";
    private static final String FOLLOWERS = "Followers";
    private static final String PAGE_VIEWS = "Page Views";
    private static final String LIVE_VIEWERS = "Live Viewers";
    private static final String REQUEST_TIME = "Request Time";
    private static final Object [] FILE_HEADER = {LIVE_VIEWERS,FOLLOWERS,PAGE_VIEWS,GAME,REQUEST_TIME};

    FileWriter fileWriter = null;
    CSVPrinter csvFilePrinter = null;
    boolean writeHeaders;

    public void initializeCSVPrinter(File file) throws IOException{
        //Create the CSVFormat object with "\n" as a record delimiter
        CSVFormat csvFileFormat = CSVFormat.DEFAULT;
        //initialize FileWriter object
        fileWriter = new FileWriter(file, true);
        //initialize CSVPrinter object
        csvFilePrinter = new CSVPrinter(fileWriter, csvFileFormat);
        //Create CSV file header
        if (writeHeaders){
            csvFilePrinter.printRecord(FILE_HEADER);
        }
    }

    public void setWriteHeaders(boolean check) throws IOException{
        writeHeaders = check;
    }

    public void writeRow(List row) throws IOException{
        csvFilePrinter.printRecord(row);
    }

    public void closeCSVWriter() throws IOException{
        fileWriter.flush();
        fileWriter.close();
        csvFilePrinter.close();
    }
}
