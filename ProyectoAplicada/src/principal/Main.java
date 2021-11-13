package principal;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {

        DecimalFormat df = new DecimalFormat("##.##");
        double Temp = 7;
        double viento = 22;
        System.out.println("\n--->Entrada\nTemperatura: " + Temp + " °C\nVelocidad del Viento: " + viento + " knots (1knots=1.852km/h)");
        Inferencia I = new Inferencia();
        ArrayList<TrapecioResultadoInferencia> ResultadoInferencia = I.entrada(Temp, viento);
        Defuzificacion D = new Defuzificacion(ResultadoInferencia);
        System.out.println("\n--->Salida defuzidicada\n Sensacion Termica: " + df.format(D.CalcularArea()) + " °C ");

    }
}
