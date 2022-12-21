package main.CapaPresentacio.Vista;

import main.CapaPresentacio.Controllers.CtrlPresentacio;

import java.util.Locale;

public class Application {
    public static void main(String[] args) throws Exception{
        Locale.setDefault(new Locale("en"));
        CtrlPresentacio cp = new CtrlPresentacio();
    }

}