
package view.webservices.impl.jaxws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name = "addNotificacion", namespace = "http://impl.webservices.view/")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "addNotificacion", namespace = "http://impl.webservices.view/")
public class AddNotificacion {

    @XmlElement(name = "arg0", namespace = "")
    private view.beans.NotificacionesBean arg0;

    /**
     * 
     * @return
     *     returns NotificacionesBean
     */
    public view.beans.NotificacionesBean getArg0() {
        return this.arg0;
    }

    /**
     * 
     * @param arg0
     *     the value for the arg0 property
     */
    public void setArg0(view.beans.NotificacionesBean arg0) {
        this.arg0 = arg0;
    }

}
