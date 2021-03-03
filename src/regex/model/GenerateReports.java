package regex.model;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Mark Zamudio
 */
public class GenerateReports {
    private File report;
    private FileWriter pencil;
    
    public GenerateReports() {}
    
    public void createReport(String reportName, String reportContent) {
        report = new File(reportName + ".txt");
        
        if (!report.exists()) {
            try {
                report.createNewFile();
                pencil = new FileWriter(report);
                pencil.write(reportContent);
                pencil.close();
            } catch (IOException ex) {
                Logger.getLogger(GenerateReports.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
