package view.notification.android;

public enum Dispositivos {
    ANDROID("Android"),IOS("iOS");
    private String dispositivo;
    Dispositivos(String dispositivo){
        this.dispositivo=dispositivo;
    }
    public static Dispositivos getDispositivo(String dispositivo){
        if(dispositivo.equalsIgnoreCase("Android"))
            return ANDROID;
        if(dispositivo.equalsIgnoreCase("iOS"))
            return IOS;
        return null;
    }
}
