package view.notification;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AndroidPayloadRequest implements Serializable {
    private static final long serialVersionUID = 1826014008550426786L;
    /**
     * Accion a tomar
     * I  .- Insert  (Inserta un nuevo partido)
     * U  .- Update  (Actualiza el marcador del partido)
     */
    /**
     * Estatus del partido 
     * 1.- No iniciado
     * 2.- Iniciado
     * 3.- Terminado
     * 4.- Suspendido
     */
    
    public Map<String,String> data;
    public List<String> ListgcmId;


    public void addGcmId(String gcmId) {
        ListgcmId.add(gcmId);
    }
    
    public AndroidPayloadRequest(){
        data=new HashMap<String,String>();
        ListgcmId=new ArrayList<String>();
    }
    
    
    public void put(String key,String value){
        data.put(key,value);
    }

    public String toString() {
        String gcmIds="\"";
        for(String gcm:ListgcmId)
            gcmIds+=gcm+"\",\"";
        return "{ \"data\": {\n" +
            "    \"action\": \"" + data.get("action") + "\",\n" +
            "    \"id\": \"" + data.get("id") + "\",\n" +
            "    \"golDe\": \"" + data.get("golDe") + "\",\n" +
            "    \"eL\": \"" + data.get("eL") + "\",\n" +
            "    \"eV\": \"" + data.get("eV") + "\",\n" +
            "    \"gL\": \"" + data.get("gL") + "\",\n" +
            "    \"gV\": \"" + data.get("gV") + "\",\n" +
            "    \"min\": \"" + data.get("min") + "\",\n" +
            "    \"fHr\": \"" + data.get("fHr") + "\",\n" +
            "    \"lugar\": \"" + data.get("lugar") + "\",\n" +
            "    \"ronda\": \"" + data.get("ronda") + "\",\n" +
            "    \"eP\": \"" + data.get("eP") + "\",\n" +
             "    \"nN\": \"" + data.get("nN") + "\",\n" +  
            "  },\n" +
            "  \"registration_ids\": [" + gcmIds.substring(0,gcmIds.length()-2) + "]\n" +
            "}";
    }
}
