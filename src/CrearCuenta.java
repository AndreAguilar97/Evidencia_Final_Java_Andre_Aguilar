/* Autor. Gerardo André Aguilar Juárez
Fecha de entrega: Jueves 07 de Diciembre del 2023
 */

//Librerías utilizadas
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

//Componentes de la interfaz gráfica.
/*Se definen componentes de la interfaz gráfica (campos de texto, botones, etc.).
        Estos elementos son utilizados para la entrada de datos, botones de acción y visualización de información en la GUI.*/
public class CrearCuenta extends JFrame{
    private JTextField txtEdad;
    private JTextField txtEmail;
    private JTextField txtNombre;
    private JComboBox cbxTipo;
    private JButton btnCrear;
    private JPasswordField pswContraseña;
    private JPasswordField pswConfirmar;
    public JPanel MiPanel;
    private JButton btnRegresar;

    public CrearCuenta() {
        btnCrear.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                guardarDatos();
            }
        });
        btnRegresar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                regresarAVentanaLogin();
            }
        });
    }

    //Métodos de la clase
    private void guardarDatos() {//Recopila datos de la interfaz, valida la información y guarda los datos en un archivo (Registro.txt).
        String nombre = txtNombre.getText();
        String email = txtEmail.getText();
        String edadStr = txtEdad.getText();
        String tipoUsuario = (String) cbxTipo.getSelectedItem(); // Obtener el tipo de usuario seleccionado
        String contraseña = new String(pswContraseña.getPassword());

        // Verificar si las contraseñas coinciden antes de guardar
        String confirmarContraseña = new String(pswConfirmar.getPassword());
        if (!contraseña.equals(confirmarContraseña)) {
            JOptionPane.showMessageDialog(this, "Las contraseñas no coinciden", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Verificar si la edad es un número
        int edad;
        try {
            edad = Integer.parseInt(edadStr);
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Ingrese una edad válida", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Guardar datos en el archivo Registro.txt
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("Registro.txt", true))) {
            // Agregar datos al archivo
            writer.write("Email:" + email + "\n");
            writer.write("Contraseña:" + contraseña + "\n");
            writer.write("Tipo de Usuario:" + tipoUsuario + "\n");
            writer.write("Nombre:" + nombre + "\n");
            writer.write("Edad:" + edad + "\n");
            writer.write("\n");  // Separador entre registros

            // Mensaje de éxito
            JOptionPane.showMessageDialog(this, "Datos guardados con éxito", "Éxito", JOptionPane.INFORMATION_MESSAGE);
        } catch (IOException ex) {
            // Manejar errores de escritura de archivos
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error al guardar datos", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void regresarAVentanaLogin() { //Cierra la ventana actual y muestra la ventana de inicio de sesión (Login).
        // Cerrar la ventana actual
        this.dispose();

        // Crear e mostrar la ventana de login
        Login login = new Login();
        JFrame frame = new JFrame("VentanaLogin");
        frame.setContentPane(login.MiPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  // Ajusta según tus necesidades
        frame.pack();
        frame.setVisible(true);
    }

    //Método principal que inicia la aplicación, creando una instancia de la clase CrearCuenta y mostrando la interfaz gráfica.
    public static void main(String[] args) {
        CrearCuenta registro = new CrearCuenta();
        registro.setContentPane(registro.MiPanel);
        registro.setSize(1000,1000);
        registro.setDefaultCloseOperation(EXIT_ON_CLOSE);
        registro.setVisible(true);
    }
}
