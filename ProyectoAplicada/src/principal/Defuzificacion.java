package principal;

import java.util.ArrayList;

public class Defuzificacion {

    public ArrayList<TrapecioResultadoInferencia> R;

    public Defuzificacion(ArrayList<TrapecioResultadoInferencia> Resultado){
        this.R = Resultado;
    }

    public double CalcularArea(){
        double salida=0;
        double areaT=0;
        double salidaD=0;
        for (int x = 0; x < R.size(); x++) {
            double x2 = R.get(x).B2.x;
            double x1 = R.get(x).B1.x;
            double h = R.get(x).altura;
            double area = (x2-x1)*(h-((h*h)/2));
            areaT = areaT + area;
            double puntoMedio = (x2+x1)/2;
            double pre = area*puntoMedio;
            salida = salida + pre;
            salidaD = salida/areaT;
        }
        return salidaD;

    }

}
