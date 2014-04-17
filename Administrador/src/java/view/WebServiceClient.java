package view;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import view.client.FileTransfererImpl;
import view.client.FileTransfererImplService;

/**
 * A client program that connects to a web service in order to upload
 * and download files.
 *
 */
public class WebServiceClient {
 
    public static void main(String[] args) {
        // connects to the web service
        FileTransfererImplService client = new FileTransfererImplService();
        FileTransfererImpl service = (view.client.FileTransfererImpl)client.getFileTransfererImplPort();
         
        // downloads another file
        String fileName = "C:\\JDeveloper\\mywork\\NotifMundial\\ViewController\\images\\caballo.jpg";
        byte[] fileBytes = service.download(fileName);
         
        try {
            FileOutputStream fos = new FileOutputStream("C:\\JDeveloper\\mywork\\NotifMundial\\ViewController\\images\\caballo2.jpg");
            BufferedOutputStream outputStream = new BufferedOutputStream(fos);
            outputStream.write(fileBytes);
            outputStream.close();

        } catch (IOException ex) {
            System.err.println(ex);
        }
    }
}
