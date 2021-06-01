
package calculadora;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author Sergio Antonio Trujillo del Valle
 */

public class VentanaPrincipal extends JFrame {
    
    // Atributos
    
    public String op = "+";
    
    public int ancho = 320, alto = 450;
    
    static JTextField text, operacion;
    static JButton 
            zero, 
            one, 
            two, 
            three, 
            four, 
            five, 
            six, 
            seven, 
            eigth, 
            nine,
            plus,
            minus,
            multiplication,
            division,
            equal,
            reset,
            decimal,
            square,
            exponent,
            reciprocal,
            absolute, 
            triangle,
            polygon,
            eraseNumber;
    // Objeto Operaciones que tiene los metodos para realizar los calculos
    public Operaciones cal = new Operaciones();
        
    public VentanaPrincipal() {
        
        super("Calculadora Sergio Trujillo");
        getContentPane().setBackground(Color.DARK_GRAY);
        
        //Panel para el contenido de la caja donde se ingresarán los números
        //y se visualizara el resultado
        JPanel panelText = new JPanel();
        panelText.setLayout(new BoxLayout(panelText, BoxLayout.Y_AXIS));
        Font fuenteTextField = new Font("helvetica", Font.CENTER_BASELINE, 38);
        Font fuenteTextoperaciones = new Font("helvetica", Font.BOLD, 20);
        operacion = new JTextField(Variables.operacion);
        operacion.setBackground(Color.white);
        operacion.setEditable(false);
        operacion.setFont(fuenteTextoperaciones);
        operacion.setHorizontalAlignment(JTextField.RIGHT);
        operacion.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 10));
        text = new JTextField(Variables.resultado);
        text.setBackground(Color.white);
        text.setFont(fuenteTextField);
        text.setHorizontalAlignment(JTextField.RIGHT);
        text.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 10));
        text.addKeyListener(new KeyValidate());
//        text.addKeyListener(txtNumeroKeyTyped);
        panelText.add(operacion);
        panelText.setBackground(Color.white);
        panelText.add(text);
        panelText.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
        panelText.setBounds(0, 0, 320, 100);
                
        
        // Creacio´n delos botones para la calculadora
        zero = new JButton("0");
        one = new JButton("1");
        two = new JButton("2");
        three = new JButton("3");
        four = new JButton("4");
        five = new JButton("5");
        six = new JButton("6");
        seven = new JButton("7");
        eigth = new JButton("8");
        nine = new JButton("9");
        plus = new JButton("+");
        minus = new JButton("-");
        multiplication = new JButton("*");
        division = new JButton("/");
        equal = new JButton("=");
        reset = new JButton("AC");
        decimal = new JButton("∙");
        
        square = new JButton("√x");
        exponent = new JButton("<html>X<sup>n</sup></html>");
        reciprocal = new JButton("1/x");
        absolute = new JButton("±"); 
        triangle = new JButton("△");
        eraseNumber = new JButton("C");
        polygon = new JButton("P");
        
        // Panel para colocar los numeros y las operaciones de la calculadora         
        JPanel buttons = new JPanel(new GridLayout(0,4));
        buttons.setBounds(0, 100, 320, 320);
        
        // Se agregan los botones al panel
        buttons.add(reset);
        buttons.add(eraseNumber);
        buttons.add(triangle);
        buttons.add(polygon);
        
        
        buttons.add(square);
        buttons.add(exponent);
        buttons.add(reciprocal);
        buttons.add(multiplication);
        
        buttons.add(seven);
        buttons.add(eigth);
        buttons.add(nine);
        buttons.add(division);
        
        buttons.add(four);
        buttons.add(five);
        buttons.add(six);
        buttons.add(minus);
        
        buttons.add(one);
        buttons.add(two);
        buttons.add(three);
        buttons.add(plus);
                
        buttons.add(absolute);
        buttons.add(zero);
        buttons.add(decimal);
        buttons.add(equal);
        
        Component[] component = buttons.getComponents();

        // Estilos de los botones
        Font fuente = new Font("helvetica", Font.PLAIN, 24);
        Font numeros = new Font("helvetica", Font.PLAIN, 28);
        for (Component component1 : component) {
            JButton button = (JButton) component1;
            button.setBorder(BorderFactory.createEmptyBorder());
            button.addActionListener(new ButtonListener());
            button.setBackground(new Color(0,150,136));
            button.setForeground(Color.white);
            button.setFont(fuente);
            
            if (
                    button.getActionCommand().matches("\\d") ||
                    button == absolute ||
                    button == decimal
                ){
                button.setBackground(Color.DARK_GRAY);
                button.setFont(numeros);
            }
            
            else if (button == equal)  {
                button.setBackground(new Color(255,76,0));
            }
            
        }
        
        //Configuración del marco General
        Image icon = new ImageIcon(getClass().getResource("icon.png")).getImage();
        setIconImage(icon);
        setLayout(null);
        setSize(ancho,alto);
        setLocation(Variables.positionX,Variables.positionY);
        setResizable(false);
        add(panelText);
        add(buttons);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
    }
    
    // Metodo para validar si en el textField solo hay un 0 o u operador
    // si lo hay lo elimina y coloca el numero del boton correspondiente
    // si no lo hay va concatenando los numeros
    public void validarNumero(String textfield, String num) {
        text.setText("");
        if (
                textfield.equals("0") || 
                textfield.equals("ERROR") ||
                textfield.matches("([\\+-/*])")
            ) {
            text.setText(num);
        } else {
            text.setText(textfield + num);
        }
    }
    
    public class KeyValidate implements KeyListener {
    
        @Override
        public void keyTyped(KeyEvent ke) {
            ke.consume();
        }
        @Override
        public void keyPressed(KeyEvent ke) {
            String value = text.getText();
            String tecla = ""+ke.getKeyChar();
            if (tecla.matches("\\d")) {
                validarNumero(value, tecla);      
            }
            else if (ke.getKeyCode() == 110){
                if (!value.contains(".")) {
                    text.setText(value+".");                
                }            
            }
            else if(tecla.matches("([\\+-/*])")) {
                operacion.setText(value+tecla);
                //setea el valor del primer numero en el objeto calculadora
                cal.setNum1(text.getText().trim());
                //Asigna el valor del operador a la variable op
                op = tecla;
                // Limpia el input de la calculadora
                text.setText(tecla);
            }
            else if (ke.getKeyCode() == 8) {            
                if (value.length() > 0 ) {
                    text.setText(value.substring(0, value.length()));
                }           
            }
            else if (ke.getKeyCode() == 10) {
                if (op.equals("/") && value.equals("0")) {
                    JOptionPane.showMessageDialog(
                            null, 
                            "No se puede dividir " + cal.getResultadoText() + " por 0",
                            "Error",
                            JOptionPane.ERROR_MESSAGE
                    );
                    text.setText("Error");
                    operacion.setText("Error");
                } else {
                    
                    // Asigna el valor del input al num 2 de la calculadora
                    cal.setNum2(value);
                    //Realiza la operación
                    cal.Operar(op);
                    
                    // Muestra el resultado
                    text.setText(cal.getResultadoText());
                    operacion.setText(operacion.getText()+value);
                }            
            }
            else  {
                ke.consume();
            }
        }
        @Override
        public void keyReleased(KeyEvent ke) {
        }

    }
    
    private class ButtonListener implements ActionListener {
        // Metodo para los eventos de los botones
        @Override
        public void actionPerformed(java.awt.event.ActionEvent e) {
            
            Variables.positionX = getLocation().x;
            Variables.positionY = getLocation().y;            
            
            if (e.getSource() == square) {
                Variables.titleJFrame = "Raiz Cuadrada";
                Variables.calculo = "square";
                Variables.title = "Ingrese el número";
                Variables.operacion = "√ Num";
                Variables.numberOfVariableRequired = 1;
                
                OperacionesComplejas square = new OperacionesComplejas();
                square.setVisible(true);
                dispose();
            }
            
            else if (e.getSource() == exponent) {
                Variables.titleJFrame = "Exponente";
                Variables.calculo = "exponent";
                Variables.title = "Agrega la Base";
                Variables.title2 = "Agrega el exponente";
                Variables.operacion = "<html> Base<sup>exp</sup></html>";
                Variables.numberOfVariableRequired = 2;
                
                OperacionesComplejas square = new OperacionesComplejas();
                square.setVisible(true);
                dispose();
            }
            
            else if (e.getSource() == absolute) {
                
                Variables.titleJFrame = "Valor Absoluto";
                Variables.calculo = "absolute";
                Variables.title = "Ingrese el número";
                Variables.operacion = "|Num|";
                Variables.numberOfVariableRequired = 1;
                OperacionesComplejas square = new OperacionesComplejas();
                square.setVisible(true);
                dispose();
            }
            
            else if (e.getSource() == reciprocal) {
                
                Variables.titleJFrame = "Reciproco";
                Variables.calculo = "reciprocal";
                Variables.title = "Ingrese el número";
                Variables.operacion = "1/Num";
                Variables.numberOfVariableRequired = 1;
                OperacionesComplejas square = new OperacionesComplejas();
                square.setVisible(true);
                dispose();
            }
            
            else if (e.getSource() == triangle) {
                
                Variables.titleJFrame = "Área de un tríangulo";
                Variables.calculo = "triangle";
                Variables.title = "Agrega la base";
                Variables.title2 = "Agrega la altura";
                Variables.operacion = "Base*Altura/2";
                Variables.numberOfVariableRequired = 2;
                
                OperacionesComplejas square = new OperacionesComplejas();
                square.setVisible(true);
                dispose();
            }
            
            else if (e.getSource() == polygon) {
                
                Variables.titleJFrame = "Perímetro de un Péntagono";
                Variables.calculo = "polygon";
                Variables.title = "Agrega el No. de lados";
                Variables.title2 = "Agrega la longitud";
                Variables.operacion = "No. de lados * Longitud";
                Variables.numberOfVariableRequired = 2;
                
                OperacionesComplejas square = new OperacionesComplejas();
                square.setVisible(true);
                dispose();
            }
                        
            else if (e.getSource() == decimal ) {
                String value = text.getText();
                if (!value.contains(".")) {
                    text.setText(value+".");                
                }
            }
            
            String buttonValue = e.getActionCommand();
            
            
            // Botones de los los numeros
            // Valida gracias a una exprecion regular si el boton es un numero
            if (buttonValue.matches("\\d")) {
                // Pasa el valor del boton pulsado al input de la calculadora
                validarNumero(text.getText().trim(), buttonValue);                
            }
            // Botones de los operadores
            // Valida gracias a una exprecion regular si el boton es un operador
            else if(buttonValue.matches("([\\+-/*])")) {
                operacion.setText(text.getText()+ " "+buttonValue);
                //setea el valor del primer numero en el objeto calculadora
                cal.setNum1(text.getText().trim());
                //Asigna el valor del operador a la variable op
                op = buttonValue;
                // Limpia el input de la calculadora
                text.setText(buttonValue);
            }
            
            // Boton del resultado 
            // Valida si el boton precionado es =
            else if (buttonValue.equals("=")){
                operacion.setText(operacion.getText()+ " "+text.getText()+" =");
                // Valida si el operador es de dividir y el numero por el que se quiere dividir es 0
                if (op.equals("/") && text.getText().trim().equals("0")) {
                    JOptionPane.showMessageDialog(
                            null, 
                            "No se puede dividir " + cal.getResultadoText() + " por 0",
                            "Error",
                            JOptionPane.ERROR_MESSAGE
                    );
                    text.setText("Error");
                    operacion.setText("Error");
                } else {
                    
                    // Asigna el valor del input al num 2 de la calculadora
                    cal.setNum2(text.getText().trim());
                    //Realiza la operación
                    cal.Operar(op);
                    
                    // Muestra el resultado
                    text.setText(cal.getResultadoText());
                    
                    
                }
            }
            
            // Por defecto si no cumple ninguna condicion anterior resetea todo
            else if (buttonValue.equals("AC")){
                cal.setReset();
                text.setText("0");
                operacion.setText("0");
            }
            
            else if (buttonValue.equals("C") ) {
                String value = text.getText();
                if (value.length() > 1 ) {
                    text.setText(value.substring(0, value.length()-1));
                }            
            }
            
        }
    }
    
}
