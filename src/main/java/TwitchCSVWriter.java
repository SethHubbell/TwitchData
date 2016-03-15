import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;

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

    public void writeHeaders(String filename) throws IOException{
        //Create the CSVFormat object with "\n" as a record delimiter
        CSVFormat csvFileFormat = CSVFormat.DEFAULT.withRecordSeparator(",");
        //initialize FileWriter object
        fileWriter = new FileWriter(filename);
        //initialize CSVPrinter object
        csvFilePrinter = new CSVPrinter(fileWriter, csvFileFormat);
        //Create CSV file header
        csvFilePrinter.printRecord(FILE_HEADER);
    }

    public void writeRow(List row) throws IOException{
        //TODO not adding rows correctly at the moment
        csvFilePrinter.printRecord(row);
    }

    public void closeCSVWriter() throws IOException{
        fileWriter.flush();
        fileWriter.close();
        csvFilePrinter.close();
    }
}
