
package calculadora;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

/**
 *
 * @author Sergio Antonio Trujillo del Valle
 */

public class OperacionesComplejas extends JFrame {
    
    // Atributos
    
    public int ancho = 320, alto = 450;
    
    static JTextField text, text2;
    static JLabel title, title2, mostrarOperacion;
    static JButton operacion;
    
    
    // Objeto Operaciones que tiene los metodos para realizar los calculos
    public Operaciones cal = new Operaciones();
        
    public OperacionesComplejas() {
        
        super(Variables.titleJFrame);
        
        this.getContentPane().setBackground(Color.DARK_GRAY);
        
        //Panel para el contenido de la caja donde se ingresará ek número      
        
        Font fuenteTextField = new Font("helvetica", Font.CENTER_BASELINE, 38);
        Font fuente = new Font("helvetica", Font.PLAIN, 28);
        
        mostrarOperacion = new JLabel("");
        title = new JLabel("");
        text = new JTextField("0");
        title2 = new JLabel("");        
        text2 = new JTextField("0");
        operacion = new JButton("Calcular");
        
        mostrarOperacion.setFont(fuente);
        mostrarOperacion.setForeground(Color.white);
        mostrarOperacion.setHorizontalTextPosition(JLabel.CENTER);
        
        title.setFont(fuente);
        title.setForeground(Color.white);
        
        title2.setFont(fuente);
        title2.setForeground(Color.white);
        
        text.setBackground(Color.white);
        text.setFont(fuenteTextField);
        text.setHorizontalAlignment(JTextField.RIGHT);
        text.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 10));
        text.addKeyListener(new textValidate());
        
        
        text2.setBackground(Color.white);
        text2.setFont(fuenteTextField);
        text2.setHorizontalAlignment(JTextField.RIGHT);
        text2.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 10));
        text2.addKeyListener(new text2Validate());
        
        operacion.setBorder(BorderFactory.createEmptyBorder());
        operacion.addActionListener(new ButtonListener());
        operacion.setBackground(new Color(0,150,136));
        operacion.setForeground(Color.white);
        operacion.setFont(fuente);                
        
        //Configuración del marco General
        Image icon = new ImageIcon(getClass().getResource("icon.png")).getImage();
        setIconImage(icon);
        setLayout(null);
        setSize(ancho,alto);
        setLocation(Variables.positionX,Variables.positionY);
        setResizable(false);
        
        
        if (
                Variables.numberOfVariableRequired == 2
            ) {
            
            title.setText(Variables.title);
            title.setBounds(10,50, 300, 50);
            text.setText("0"); 
            text.setBounds(10,100, 300, 50);
            
            title2.setText(Variables.title2);
            title2.setBounds(10,150, 300, 50);
            text2.setText("0");
            text2.setBounds(10,200, 300, 50);
            
            mostrarOperacion.setText(Variables.operacion);
            mostrarOperacion.setBounds(10,270, 300, 50);
            
            operacion.setBounds(100,350, 120, 60);
        } else {
            
            title.setText(Variables.title);
            title.setBounds(10,100, 300, 50);
            text.setText("0"); 
            text.setBounds(10,150, 300, 50);
            mostrarOperacion.setText(Variables.operacion);
            mostrarOperacion.setBounds(10,210, 300, 50);            
            operacion.setBounds(100,280, 120, 60);
        }
        
        add(title);
        add(text);
        add(title2);
        add(text2);
        add(mostrarOperacion);
        add(operacion);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    
    private void typeofCalculo (String calculo) {
        switch (calculo) {
            case "square":
                mostrarOperacion.setText("√"+ cal.getNum1Text());
                break;
            case "exponent":
                mostrarOperacion.setText("<html>"+ cal.getNum1Text()+"<sup>"+cal.getNum2Text()+"</sup></html>");
                break;
            case "absolute":
                mostrarOperacion.setText("|"+ cal.getNum1Text()+"|");
                break;
            case "reciprocal":
                mostrarOperacion.setText("1/"+ cal.getNum1Text());
                break;
            case "triangle":
                mostrarOperacion.setText(cal.getNum1Text()+"*"+cal.getNum2Text()+"/2");
                break;
            case "polygon":
                mostrarOperacion.setText(cal.getNum1Text()+"*"+cal.getNum2Text());
                break;
            default:
                break;
        }
    }
    
    private class textValidate implements KeyListener {        

        public String num1;
        
        @Override
        public void keyTyped(KeyEvent ke) {
            ke.consume();
        }
        @Override
        public void keyPressed(KeyEvent ke) {
            String value = text.getText();
            String tecla = ""+ke.getKeyChar();
            text.setText("");
            if (tecla.matches("\\d")) {
                if (value.equals("0") || value.equals("")) {
                    text.setText(tecla);
                    cal.setNum1(text.getText());
                } else  {
                    text.setText(value + tecla);
                    cal.setNum1(text.getText());
                }
                
                typeofCalculo(Variables.calculo);
            }
            else if (ke.getKeyCode() == 110){
                if (!value.contains(".")) {
                    text.setText(value+".");                
                }else {
                    text.setText(value);
                }         
            }
            else if (ke.getKeyCode() == 8) {
                
                if (value.length() > 1 ) {
                    text.setText(value.substring(0, value.length()));
                    cal.setNum1(text.getText().substring(0, text.getText().length()-1));                    
                    
                } else {
                    text.setText("0");
                    cal.setNum1("0");
                }
                
                typeofCalculo(Variables.calculo);
                
            }
            else {
                ke.consume();            
            }
        }
        @Override
        public void keyReleased(KeyEvent ke) {
        }
    };
    
    private class text2Validate implements KeyListener {
        
        @Override
        public void keyTyped(KeyEvent ke) {
            ke.consume();
        }
        @Override
        public void keyPressed(KeyEvent ke) {
            String value = text2.getText();
            String tecla = ""+ke.getKeyChar();
            text2.setText("");
            
            if (tecla.matches("\\d")) {
                if (value.equals("0") || value.equals("")) {
                    text2.setText(tecla);
                    cal.setNum2(text2.getText());
                } else  {
                    text2.setText(value + tecla);
                    cal.setNum2(text2.getText());
                }
                
                typeofCalculo(Variables.calculo);
            } 
            else if (ke.getKeyCode() == 110){
                text2.setText(value);
                ke.consume();  
            }
            else if (ke.getKeyCode() == 8) {
                if (value.length() > 1 ) {
                    text2.setText(value.substring(0, value.length()));
                    cal.setNum2(text2.getText().substring(0, text2.getText().length()-1));
                } else {
                    text2.setText("0");
                    cal.setNum2("0");
                }
                typeofCalculo(Variables.calculo);
            }
            else {
                ke.consume();            
            }
        }
        @Override
        public void keyReleased(KeyEvent ke) {
        }
    };
     
    private class ButtonListener implements ActionListener {
        // Metodo para los eventos de los botones
        @Override
        public void actionPerformed(ActionEvent e) {
            
            Variables.positionX = getLocation().x;
            Variables.positionY = getLocation().y; 
            
            switch (Variables.calculo) {
                case "square":
                    cal.Square();
                    Variables.operacion = "√"+text.getText().trim()+ " =";
                    Variables.resultado = cal.getResultadoText();
                    break;
                case "exponent":
                    cal.Exponent();
                    Variables.operacion = cal.getExponent(text.getText()) + " =";
                    Variables.resultado = cal.getResultadoText();
                    break;
                case "absolute":
                    cal.Absolute();
                    Variables.operacion = "|"+text.getText()+"| =";
                    Variables.resultado = cal.getResultadoText();
                    break;
                case "reciprocal":
                    cal.Reciprocal();
                    Variables.operacion = "1/"+text.getText()+" =";
                    Variables.resultado = cal.getResultadoText();
                    break;
                case "triangle":
                    cal.Triangle();
                    Variables.operacion = "b: "+text.getText() + " * a: " +text2.getText() +" /2" +" =";
                    Variables.resultado = cal.getResultadoText();
                    break;
                case "polygon":
                    cal.Polygon();
                    Variables.operacion = "No. La: " +text.getText() + " * Lon: " +text2.getText()+" =";
                    Variables.resultado = cal.getResultadoText();
                    break;
                default:
                    break;
            }
            
            
            VentanaPrincipal ventana = new VentanaPrincipal();
            ventana.setVisible(true);
            dispose();
            
        }
    }

    
}
