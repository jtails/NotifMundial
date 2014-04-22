package view.notification;

import java.io.Serializable;

public class Results implements Serializable{
    private static final long serialVersionUID = 583097452669315514L;
    private String error;

    public void setError(String error) {
        this.error = error;
    }

    public String getError() {
        return error;
    }
    
    public String toString(){
        return "error=" +error;
    }
}
