package Register;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 *
 * @author SYNC
 * @ Classe responsavel pelos registro de logs
 */
public class Logs {

    private String ArchivesLogs = "E:/Projetos/Mercado/Logs/logs.txt";

    public void registerLogs(String status) throws Exception {
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        String dateSTR = now.format(formatter);
        try (BufferedWriter print = new BufferedWriter(new FileWriter(ArchivesLogs, true))) {
            print.write(dateSTR + " : " + status);
            print.newLine();
        } catch(IOException e){
            e.getMessage();
        }
    }

}
