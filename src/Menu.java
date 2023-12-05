/* Autor. Gerardo André Aguilar Juárez
Fecha de entrega: Jueves 07 de Diciembre del 2023
 */

//Librerías utilizadas
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

//Componentes de la interfaz gráfica.
/*Se definen componentes de la interfaz gráfica (campos de texto, botones, etc.).
        Estos elementos son utilizados para la entrada de datos, botones de acción y visualización de información en la GUI.*/
public class Menu extends JFrame{
    public JPanel MiPanel;
    private JButton btnAltaDoctores;
    private JButton btnAltaPacientes;
    private JButton btnGenerarCita;
    private JButton btnCerrarSesion;
    
    public Menu() {
        btnAltaDoctores.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                irDoctores();
            }
        });
        btnAltaPacientes.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                irPacientes();
            }
        });
        btnCerrarSesion.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cerrarSesion();
            }
        });
        btnGenerarCita.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {crearCitas();

            }
        });
    }

    private void irDoctores(){ //Cierra la ventana actual y abre una nueva ventana para dar de alta doctores (Alta_Doctor).
            // Cerrar la ventana actual
            this.dispose();

            // Crear e mostrar la ventana de registro
            Alta_Doctor altaDoctor = new Alta_Doctor();
            JFrame frame = new JFrame("Alta_Doctor");
            frame.setContentPane(altaDoctor.MiPanel);
            frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);  // Ajusta según tus necesidades
            frame.pack();
            frame.setVisible(true);
    }

    private void irPacientes(){//Cierra la ventana actual y abre una nueva ventana para dar de alta pacientes (Alta_Paciente).
        // Cerrar la ventana actual
        this.dispose();

        // Crear e mostrar la ventana de registro
        Alta_Paciente altaPaciente = new Alta_Paciente();
        JFrame frame = new JFrame("Alta_Paciente");
        frame.setContentPane(altaPaciente.MiPanel);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);  // Ajusta según tus necesidades
        frame.pack();
        frame.setVisible(true);
    }
    private void crearCitas(){//Cierra la ventana actual y abre una nueva ventana para crear citas (CrearCitas).
        this.dispose();
        CrearCitas crearCitas = new CrearCitas();
        JFrame frame = new JFrame("CrearCitas");
        frame.setContentPane(crearCitas.MiPanel);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);

    }

    private void cerrarSesion(){ //Cierra la ventana actual y abre una nueva ventana para iniciar sesión (Login).
        this.dispose();
        Login login = new Login();
        JFrame frame = new JFrame("VLogin");
        frame.setContentPane(login.MiPanel);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);  // Ajusta según tus necesidades
        frame.pack();
        frame.setVisible(true);
    }

    //Método principal que inicia la aplicación, creando una instancia de la clase Menu y mostrando la interfaz gráfica.
    public static void main(String[] args) {
        Menu menu = new Menu();
        menu.setContentPane(menu.MiPanel);
        menu.setSize(500,500);
        menu.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        menu.setVisible(true);
    }
}
