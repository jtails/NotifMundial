package view.webservices;
import javax.jws.WebMethod;
import javax.jws.WebService;
 
/**
 * A web service endpoint interface.
 * http://www.codejava.net/java-ee/web-services/java-web-services-binary-data-transfer-example-base64-encoding
 */
@WebService
public interface FileTransferer {
    @WebMethod
    public void upload(String fileName, byte[] imageBytes);
     
    @WebMethod
    public byte[] download(String fileName);   
}
