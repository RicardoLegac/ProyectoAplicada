package principal;

public class VelocidadViento {

    public Trapecio BAJO = new Trapecio(new Punto(0,1),new Punto(5,1),new Punto(17.5,0),true);
    public Triangulo SUAVE = new Triangulo(new Punto(2.5,0),new Punto(15,1),new Punto(27.5,0));
    public Trapecio ALTO = new Trapecio(new Punto(30,1), new Punto(25,1),new Punto(12.5,0),false);

}
