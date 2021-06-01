package calculadora;

/**
 *
 * @author Sergio Antonio Trujillo del Valle
 */
public class Operaciones {
    
    //atributos 
    private double num1, num2;
		
    // constructor
    public Operaciones() {
        this.num1 = 0.0;
        this.num2 = 0.0;
    }
		
    // método para hacer la operación
    public void Operar(String operador){
        switch ( operador ) {
            case "+":
                this.num1 += this.num2;
                this.num2 = 0.0; 
                break;
            case "-":
                this.num1 -= this.num2;
                this.num2 = 0.0;
                break;
            case "*":
                this.num1 *= this.num2;
                this.num2 = 0.0;
                break;
            case "/":
                this.num1 /= this.num2;
                this.num2 = 0.0;
                break;
            default:
                this.num1 += this.num2;
                this.num2 = 0.0;
                break;
        } 
    }
    
    public void Square(){
        this.num1 = Math.sqrt(this.num1);
    }
    
    public void Absolute(){
        this.num1 = this.num1*(-1);
    }
    
    public void Reciprocal(){
        this.num1 = 1/this.num1;
    }
    
    public void Exponent(){
        double base = this.num1;
        int expo = (int) Math.round(this.num2)-1;
        for (int i=1;i <= expo; i++) {
            this.num1 = base * this.num1;
        }
    }
    
    public void Triangle(){
        this.num1 = this.num1*this.num2/2;
    }
    
    public void Polygon(){
        this.num1 = this.num1*this.num2;
    }
    
    public String getExponent(String numBase){
                
        double base = Double.parseDouble(numBase);        
        String resultado = "";
        
        int expo = (int) Math.round(this.num2);
        for (int i=1;i <= expo; i++) {
            if (i == expo ) {
                resultado = resultado + base;
            } else {
                resultado = resultado + base + " x ";
            }
        }
        return "("+resultado+")";
    }

    //getters y Setters
    public String getResultadoText() {
        // Convierte el valor double a un string y lo asigna a la variable resultado
        String resultado = String.valueOf(this.num1);
        // Separa la cadena en dos y valida si el decimal es 0
        // si es asi lo elimina del resultado, de lo contrario 
        // muestra todos los decimales
        String[] validate = resultado.split("\\.");
        if (validate[1].equals("0")) {
            resultado = validate[0];
        }
        // valida si el numero de decimales es mayor que 6 corta el resultado
        else if ( validate[1].length() >= 6) {
             resultado = validate[0] + "." + validate[1].substring(0, 5);
            
        }
        
        return resultado;
    }
    
    public String getNum1Text() {
        
        String resultado = String.valueOf(this.num1);
        String[] validate = resultado.split("\\.");
        if (validate[1].equals("0")) {
            resultado = validate[0];
        }
        else if ( validate[1].length() >= 6) {
             resultado = validate[0] + "." + validate[1].substring(0, 5);
            
        }
        
        return resultado;
    }
    
    public String getNum2Text() {
        
        String resultado = String.valueOf(this.num2);
        String[] validate = resultado.split("\\.");
        if (validate[1].equals("0")) {
            resultado = validate[0];
        }
        else if ( validate[1].length() >= 6) {
             resultado = validate[0] + "." + validate[1].substring(0, 5);
            
        }
        
        return resultado;
    }
    
    public double getResultadoNum() {     
        return this.num1;
    }
    public void setNum1(String num2) {
        this.num1 = Double.parseDouble(num2);
    }
    public void setNum2(String num2) {
        this.num2 = Double.parseDouble(num2);
    }
    public void setReset() {
        this.num1 = 0.0;
        this.num2 = 0.0;
    }
}
