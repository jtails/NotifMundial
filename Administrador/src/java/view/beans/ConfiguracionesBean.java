
package view.beans;


public class ConfiguracionesBean {
    private String id_configuracion;
    private String Certificadoios;
    private byte[] CertificadoiosDecode;

    public byte[] getCertificadoiosDecode() {
        return CertificadoiosDecode;
    }

    public void setCertificadoiosDecode(byte[] CertificadoiosDecode) {
        this.CertificadoiosDecode = CertificadoiosDecode;
    }
    
    public String getId_configuracion() {
        return id_configuracion;
    }

    public void setId_configuracion(String id_configuracion) {
        this.id_configuracion = id_configuracion;
    }

    public String getCertificadoios() {
        return Certificadoios;
    }

    public void setCertificadoios(String Certificadoios) {
        this.Certificadoios = Certificadoios;
    }
}
