/* Autor. Gerardo André Aguilar Juárez
Fecha de entrega: Jueves 07 de Diciembre del 2023
 */

//Librerías utilizadas
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

//Componentes de la interfaz gráfica.
/*Se definen componentes de la interfaz gráfica (campos de texto, botones, etc.).
        Estos elementos son utilizados para la entrada de datos, botones de acción y visualización de información en la GUI.*/
public class MenuPacientes extends JFrame {
    private JButton btnDoctores;
    private JButton btnPacientes;
    private JButton btnCitas;
    private JButton btnSalir;
    public JPanel MiPanel;

    public MenuPacientes() {
        btnSalir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CerrarSesion();
            }
        });
        btnDoctores.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                abrirArchivo("Doctores.txt");
            }
        });
        btnPacientes.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                abrirArchivo("Pacientes.txt");
            }
        });
        btnCitas.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                abrirArchivo("Citas.txt");
            }
        });
    }

    private void abrirArchivo(String nombreArchivo) { //Abre y lee el contenido de un archivo específico (Doctores.txt, Pacientes.txt o Citas.txt) y lo muestra en un cuadro de diálogo.
        try (BufferedReader br = new BufferedReader(new FileReader(nombreArchivo))) {
            StringBuilder contenido = new StringBuilder();
            String linea;

            while ((linea = br.readLine()) != null) {
                contenido.append(linea).append("\n");
            }

            // Puedes mostrar el contenido en un cuadro de diálogo o realizar otras acciones según tus necesidades
            JOptionPane.showMessageDialog(this, "Contenido de " + nombreArchivo + ":\n" + contenido.toString(), "Archivo Abierto", JOptionPane.INFORMATION_MESSAGE);
        } catch (IOException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error al abrir el archivo " + nombreArchivo, "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void CerrarSesion(){//Cierra la sesión actual y abre la ventana de inicio de sesión (Login).
        Login ventanaLogin = new Login();
        JFrame frame = new JFrame("Login");
        frame.setContentPane(ventanaLogin.MiPanel);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        this.dispose();
    }

    public static void main(String[] args) {//Método principal que inicia la aplicación, creando una instancia de la clase MenuPacientes y mostrando la interfaz gráfica.
        MenuPacientes menuPacientes = new MenuPacientes();
        menuPacientes.setContentPane(menuPacientes.MiPanel);
        menuPacientes.setSize(500,500);
        menuPacientes.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        menuPacientes.setVisible(true);
    }
}
