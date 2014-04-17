
package view.webservices.impl.jaxws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name = "getDeatilsEmpresaResponse", namespace = "http://impl.webservices.view/")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "getDeatilsEmpresaResponse", namespace = "http://impl.webservices.view/")
public class GetDeatilsEmpresaResponse {

    @XmlElement(name = "return", namespace = "")
    private view.beans.EmpresasBean _return;

    /**
     * 
     * @return
     *     returns EmpresasBean
     */
    public view.beans.EmpresasBean getReturn() {
        return this._return;
    }

    /**
     * 
     * @param _return
     *     the value for the _return property
     */
    public void setReturn(view.beans.EmpresasBean _return) {
        this._return = _return;
    }

}
