
package view.webservices.impl.jaxws;

import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name = "getEquiposResponse", namespace = "http://impl.webservices.view/")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "getEquiposResponse", namespace = "http://impl.webservices.view/")
public class GetEquiposResponse {

    @XmlElement(name = "return", namespace = "")
    private List<view.beans.EquiposBean> _return;

    /**
     * 
     * @return
     *     returns List<EquiposBean>
     */
    public List<view.beans.EquiposBean> getReturn() {
        return this._return;
    }

    /**
     * 
     * @param _return
     *     the value for the _return property
     */
    public void setReturn(List<view.beans.EquiposBean> _return) {
        this._return = _return;
    }

}
