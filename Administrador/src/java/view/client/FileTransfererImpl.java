package view.client;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;

import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.ws.Action;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;
// !DO NOT EDIT THIS FILE!
// This source file is generated by Oracle tools
// Contents may be subject to change
// For reporting problems, use the following
// Version = Oracle WebServices (11.1.1.0.0, build 130224.1947.04102)

@WebService(wsdlLocation="http://localhost:7101/NotifMundial-ViewController-context-root/FileTransfererImplService?WSDL",
  targetNamespace="http://view/", name="FileTransfererImpl")
@XmlSeeAlso(
  { view.client.ObjectFactory.class })
public interface FileTransfererImpl
{
  @WebMethod
  @Action(input="http://view/FileTransfererImpl/downloadRequest", output="http://view/FileTransfererImpl/downloadResponse")
  @ResponseWrapper(localName="downloadResponse", targetNamespace="http://view/",
    className="view.client.DownloadResponse")
  @RequestWrapper(localName="download", targetNamespace="http://view/",
    className="view.client.Download")
  @WebResult(targetNamespace="")
  public byte[] download(@WebParam(targetNamespace="", name="arg0")
    String arg0);

  @WebMethod
  @Action(input="http://view/FileTransfererImpl/uploadRequest", output="http://view/FileTransfererImpl/uploadResponse")
  @ResponseWrapper(localName="uploadResponse", targetNamespace="http://view/",
    className="view.client.UploadResponse")
  @RequestWrapper(localName="upload", targetNamespace="http://view/",
    className="view.client.Upload")
  public void upload(@WebParam(targetNamespace="", name="arg0")
    String arg0, @WebParam(targetNamespace="", name="arg1")
    byte[] arg1);
}
