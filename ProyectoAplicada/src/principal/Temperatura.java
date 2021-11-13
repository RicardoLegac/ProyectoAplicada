package principal;

public class Temperatura {

    public Trapecio FRIO = new Trapecio(new Punto(-5,1),new Punto(0,1),new Punto(10,0),true);
    public Triangulo FRESCO = new Triangulo(new Punto(0,0),new Punto(10,1),new Punto(20,0));
    public Triangulo CALIDO = new Triangulo(new Punto(10,0),new Punto(20,1),new Punto(30,0));
    public Trapecio CALUROSO = new Trapecio(new Punto(35,1), new Punto(30,1),new Punto(20,0),false);

}


class Trapecio {
    public boolean izquierda = true;
    public Punto alto;
    public Punto borde;
    public Punto bajo;
    public Trapecio(Punto aalto,Punto bborde,Punto cbajo,boolean izq){
        this.alto = aalto;
        this.borde = bborde;
        this.bajo = cbajo;
        this.izquierda = izq;
    }
}
class Triangulo {
    public Punto rango1;
    public Punto medio;
    public Punto rango2;
    public Triangulo(Punto r1,Punto med,Punto r2){
        this.rango1=r1;
        this.medio = med;
        this.rango2=r2;
    }
}
class Punto{
    public double x;
    public double y;
    public Punto(double X, double Y){
        this.x=X;
        this.y=Y;
    }
}

