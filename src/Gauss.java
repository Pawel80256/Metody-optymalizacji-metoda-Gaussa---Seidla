public class Gauss {
    //gauss
    double h = 0.00001;
    double epsilonGauss = 0.01;
    double yNext, xNext;
    double x0 = 1, y0 = 1 ;
    double xnGauss, ynGauss;
    double ynTemp;

    //styczne
    double epsilonStyczne = 0.0001;
    double xsr;
    double xnStyczne;
    double ynStyczne;
    double a = 100;
    double b = -100;

    int iterator = 0;

    private double f(double x, double y) {
        return Math.pow(x, 3) + 5 * Math.pow(y, 2) - 6 * x * y - y + 7; //moj
//        return 10* Math.pow(x,2) + 12*x*y + 10 * Math.pow(y,2); //z pdfa
//        return 2 * Math.pow(x,3) + Math.pow(y,2) - 4*x*y -y +5;
    }
    private double pochodnaX(double x, double y) {
        return (f(x + h, y) - f(x, y)) / h;
    }

    private double pochodnaY(double x, double y) {
        return (f(x, y + h) - f(x, y)) / h;
    }

    private double pochodnaXX(double x, double y) {
        return (f(x + 2 * h, y) - 2 * f(x + h, y) + f(x, y)) / Math.pow(h, 2);
    }

    private double pochodnaYY(double x, double y) {
        return (f(x, y + 2 * h) - 2 * f(x, y + h) + f(x, y)) / Math.pow(h, 2);
    }

    private double pochodnaXY(double x, double y) {
        return (f(x + h, y + h) - f(x + h, y) - f(x, y + h) + f(x, y)) / Math.pow(h, 2);
    }

    private double pochodnaXXX(){
        return 6;
    }

    private double pochodnaYYY(){
        return 0;
    }

    /////////////////////////////////////// STYCZNE PO X ///////////////////////////////////////
    private double punktStartowyStyczneX(double y){
        if(pochodnaXXX() * pochodnaX(a,b) >= 0){
            xnStyczne =a;
        }else {
            xnStyczne = b;
        }
        return iteracjaX(y);
    }

    private double iteracjaX(double y0){
        while (true){

            double xn1 = xnStyczne - (pochodnaX(xnStyczne,y0)/pochodnaXX(xnStyczne,y0));
            if(Math.abs(pochodnaX(xn1,y0)) < epsilonStyczne ||
                    Math.abs(xn1- xnStyczne)<epsilonStyczne){
                return xn1;
            }
            xnStyczne = xn1;
        }
    }

    public double calculateStyczneX(double y){
        return punktStartowyStyczneX(y);
    }

    /////////////////////////////////////// STYCZNE PO Y ///////////////////////////////////////
    private double punktStartowyStyczneY(double x){
        if(pochodnaYYY() * pochodnaY(a,b) >= 0){
            ynStyczne =a;
        }else {
            ynStyczne = b;
        }
        return iteracjaY(x);
    }

    private double iteracjaY(double x0){
        while (true){
            
            double yn1 = ynStyczne - (pochodnaY(x0, ynStyczne)/pochodnaYY(x0, ynStyczne));
            if(Math.abs(pochodnaY(x0,yn1)) < epsilonStyczne ||
                    Math.abs(yn1- ynStyczne)<epsilonStyczne){
                return yn1;
            }
            ynStyczne = yn1;
        }
    }

    public double calculateStyczneY(double x){
        return punktStartowyStyczneY(x);
    }

    /////////////////////////// GAUSS //////////////////////////////////
    public void  calculateGauss(){
        System.out.println(++iterator);
        xnGauss = calculateStyczneX(y0);

        ynGauss = calculateStyczneY(xnGauss);

        ynTemp = ynGauss;
        while (true){
            System.out.println(++iterator);

            xnGauss = calculateStyczneX(ynTemp);

            ynGauss = calculateStyczneY(xnGauss);
            ynTemp = ynGauss;

            if(Math.abs(pochodnaX(xnGauss,ynGauss)) < epsilonGauss
                    && Math.abs(pochodnaY(xnGauss,ynGauss))< epsilonGauss){
                System.out.println("( " + xnGauss + " , " + ynGauss + " ) ");
                break;
            }
        }
    }
}
