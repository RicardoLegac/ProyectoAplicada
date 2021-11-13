package principal;


import java.util.ArrayList;

public class Inferencia {


    public ArrayList<TrapecioResultadoInferencia> entrada (double t,double v){
        Temperatura T = new Temperatura();
        VelocidadViento VV = new VelocidadViento();
        double Arraytemp[] = new double[4];
        double Arrayviento[] = new double[3];

        //para la temperatura, revisar los rangos
        if(t > T.FRIO.alto.x && t <= T.FRIO.bajo.x){
            double ufrio;
            //esta en el rango de FRIO, atraviesa a esa regla
            if(t >= T.FRIO.borde.x){
                ufrio = interseccion(t,T.FRIO.borde,T.FRIO.bajo);
            }else{
                ufrio = 1;
            }
            Arraytemp[0]=ufrio;
        }
        if(t >= T.FRESCO.rango1.x && t <= T.FRESCO.rango2.x){
            //esta en el rango de FRESCO, quiere decir que le iontercepta a ese conjunto
            double ufresco;
            if(t<T.FRESCO.medio.x){
                ufresco = interseccion(t,T.FRESCO.rango1,T.FRESCO.medio);
            }else if(t>T.FRESCO.medio.x){
                ufresco = interseccion(t,T.FRESCO.medio,T.FRESCO.rango2);
            }else{
                ufresco = 1;
            }
            Arraytemp[1]=ufresco;
        }
        if(t > T.CALIDO.rango1.x && t <= T.CALIDO.rango2.x){
            //esta en el rango de CALIDO, quiere decir que le iontercepta a ese conjunto
            double ucalido;
            if(t<T.CALIDO.medio.x){
                ucalido = interseccion(t,T.CALIDO.rango1,T.CALIDO.medio);
            }else if(t>T.CALIDO.medio.x){
                ucalido = interseccion(t,T.CALIDO.medio,T.CALIDO.rango2);
            }else{
                ucalido = 1;
            }
            Arraytemp[2]=ucalido;
        }
        if(t < T.CALUROSO.borde.x && t > T.CALUROSO.bajo.x){
            double ucaluroso;
            //esta en el rango de CALUROSO, atraviesa a esa regla
            if(t <= T.CALUROSO.borde.x){
                ucaluroso = interseccion(t,T.CALUROSO.bajo,T.CALUROSO.borde);
            }else{
                ucaluroso = 1;
            }
            Arraytemp[3]=ucaluroso;
        }
        //para la velocidad de viento, revisar los rangos
        if(v > VV.BAJO.alto.x && v <= VV.BAJO.bajo.x){
            double ubajo;
            //esta en el rango de BAJO, atraviesa a ese conjunto
            if(v >= VV.BAJO.borde.x){
                ubajo = interseccion(v,VV.BAJO.borde,VV.BAJO.bajo);
            }else{
                ubajo = 1;
            }
            Arrayviento[0]=ubajo;
        }
        if(v > VV.SUAVE.rango1.x && v <= VV.SUAVE.rango2.x){
            //esta en el rango de SUAVE, quiere decir que le iontercepta a ese conjunto
            double usuave;
            if(v<VV.SUAVE.medio.x){
                usuave = interseccion(v,VV.SUAVE.rango1,VV.SUAVE.medio);
            }else if(v > VV.SUAVE.medio.x){
                usuave = interseccion(v,VV.SUAVE.medio,VV.SUAVE.rango2);
            }else{
                usuave = 1;
            }
            Arrayviento[1]=usuave;
        }
        if(v < VV.ALTO.borde.x && v > VV.ALTO.bajo.x){
            double ucaluroso;
            //esta en el rango de ALTO, atraviesa a esa regla
            if(v <= VV.ALTO.borde.x){
                ucaluroso = interseccion(v,VV.ALTO.bajo,VV.ALTO.borde);
            }else{
                ucaluroso = 1;
            }
            Arrayviento[2]=ucaluroso;
        }
        //interseccion de las reglas que se dispararon
        double[] Reglas = new double[12];
        int c=0;
        for(int i=0;i<4;i++){
            for(int j = 0; j<3;j++){
                Reglas[c] = min( Arraytemp[i], Arrayviento[j] );
                //System.out.println("min entre "+Arraytemp[i]+" y "+Arrayviento[j]+" es "+Reglas[c]);
                c++;
            }
        }
        ArrayList<PreResultadoInferencia> Resultados=new ArrayList<PreResultadoInferencia>();
        double REGLAS[] = {3,2,1,4,3,2,5,4,3,5,5,4};
        //tengo las reglas de 0 a 11
        //crear los trapecios por min
        for(int i = 0; i<12 ; i++){
            if(Reglas[i] != 0){
                //entonces se hace el minimo entre ese valor y la regla c+1 que corresponde
                Resultados.add(new PreResultadoInferencia(REGLAS[i],Reglas[i]));
            }

        }
        return ResultadoInferencia(Resultados);

    }

    public ArrayList<TrapecioResultadoInferencia> ResultadoInferencia(ArrayList<PreResultadoInferencia> R){
        ArrayList<TrapecioResultadoInferencia> TrapeciosResultado = new ArrayList<TrapecioResultadoInferencia>();
        SensacionTermica ST = new SensacionTermica();
        for (int x = 0; x < R.size(); x++) {
            double tipo = R.get(x).tipo;
            double min = R.get(x).min;
            if(tipo == 1){  //severa
                TrapeciosResultado.add(new TrapecioResultadoInferencia(ST.SEVERA.rango1,ST.SEVERA.rango2,min));
            }else if(tipo == 2){ //mala
                TrapeciosResultado.add(new TrapecioResultadoInferencia(ST.MALA.rango1,ST.MALA.rango2,min));
            }else if(tipo == 3){ //soportable
                TrapeciosResultado.add(new TrapecioResultadoInferencia(ST.SOPORTABLE.rango1,ST.SOPORTABLE.rango2,min));
            }else if(tipo == 4){ //leve
                TrapeciosResultado.add(new TrapecioResultadoInferencia(ST.LEVE.rango1,ST.LEVE.rango2,min));
            }else{ //imperceptible
                TrapeciosResultado.add(new TrapecioResultadoInferencia(ST.IMPERCEPTIBLE.rango1,ST.IMPERCEPTIBLE.rango2,min));
            }
        }
        return TrapeciosResultado;
    }

    public double min(double a, double b){
        return (a<=b)?a:b;
    }

    public double interseccion(double t, Punto A, Punto B) {
        double pendiente = (B.y-A.y)/(B.x-A.x);
        double ec = (pendiente*(t-A.x))+A.y;
        return ec;
    }


}
class PreResultadoInferencia{
    double min;
    double tipo;

    public PreResultadoInferencia(double tipo, double min){
        this.min=min;
        this.tipo=tipo;
    }

}
class TrapecioResultadoInferencia{
    Punto B1;
    Punto B2;
    double altura;
    public TrapecioResultadoInferencia(Punto b1, Punto b2, double altura){
        this.B1=b1;
        this.B2=b2;
        this.altura=altura;
    }

}
