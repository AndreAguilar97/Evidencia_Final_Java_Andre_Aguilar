import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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

    private void irDoctores(){
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

    private void irPacientes(){
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
    private void crearCitas(){
        this.dispose();
        CrearCitas crearCitas = new CrearCitas();
        JFrame frame = new JFrame("CrearCitas");
        frame.setContentPane(crearCitas.MiPanel);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);

    }

    private void cerrarSesion(){
        this.dispose();
        Login login = new Login();
        JFrame frame = new JFrame("VLogin");
        frame.setContentPane(login.MiPanel);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);  // Ajusta según tus necesidades
        frame.pack();
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        Menu menu = new Menu();
        menu.setContentPane(menu.MiPanel);
        menu.setSize(500,500);
        menu.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        menu.setVisible(true);
    }
}
