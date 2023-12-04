import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Login extends JFrame {
    private JPasswordField pswContraseña;
    private JTextField txtEmail;
    private JButton btnCrearCuenta;
    private JButton btnIngresar;

    public JPanel MiPanel;
    private JLabel Email;

    private static final String NOMBRE_LABEL = "Nombre:";
    private static final String EMAIL_LABEL = "Email:";
    private static final String EDAD_LABEL = "Edad:";
    private static final String USUARIO_LABEL = "Tipo de Usuario:";
    private static final String CONTRASEÑA_LABEL = "Contraseña:";

    public Login () {
        btnCrearCuenta.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                abrirVentanaCrearCuenta();
            }
        });
        btnIngresar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                verificarCredencialesLogin();
            }
        });
    }

    private void abrirVentanaCrearCuenta() {
        // Cerrar la ventana actual
        this.dispose();

        // Crear e mostrar la ventana de registro
        CrearCuenta crearCuenta = new CrearCuenta();
        JFrame frame = new JFrame("CrearCuenta");
        frame.setContentPane(crearCuenta.MiPanel);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);  // Ajusta según tus necesidades
        frame.pack();
        frame.setVisible(true);
    }

    private void abrirVentanaMenu(){
        // Crear e mostrar la ventana de funciones para doctores
        Menu menu = new Menu(); //
        JFrame frame = new JFrame("FUNCIONES HABILITADAS PARA ESPECIALISTAS");
        frame.setContentPane(menu.MiPanel);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);

        // Cerrar la ventana actual de inicio de sesión
        this.dispose();
    }

    private void abrirMenuPacientes(){
        MenuPacientes menuPacientes = new MenuPacientes();
        JFrame frame = new JFrame("FUNCIONES HABILITADAS PARA PACIENTES");
        frame.setContentPane(menuPacientes.MiPanel);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);


        this.dispose();
    }

    private void verificarCredencialesLogin() {
        String correoIngresado = txtEmail.getText().trim();
        char[] contraseñaIngresada = pswContraseña.getPassword();
        String contraseñaIngresadaStr = new String(contraseñaIngresada).trim();

        if (correoIngresado.isEmpty() || contraseñaIngresadaStr.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Ingrese correo y contraseña", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try (BufferedReader br = new BufferedReader(new FileReader("Registro.txt"))) {
            String line;
            boolean credencialesCorrectas = false;
            String correoEnRegistro = "";
            String contraseñaEnRegistro = "";
            String tipoUsuarioRegistro = "";

            while ((line = br.readLine()) != null) {
                System.out.println("Línea leída: " + line);

                if (line.startsWith("Email:")) {
                    correoEnRegistro = obtenerValorDespuesDeCadena(line, "Email:");
                } else if (line.startsWith("Contraseña:")) {
                    contraseñaEnRegistro = obtenerValorDespuesDeCadena(line, "Contraseña:");
                } else if (line.startsWith("Tipo de Usuario:")) {
                    tipoUsuarioRegistro = obtenerValorDespuesDeCadena(line, "Tipo de Usuario:").trim();
                } else if (line.trim().isEmpty()) { // Se encontró una línea en blanco, verifica las credenciales

                    if (correoIngresado.equals(correoEnRegistro) && contraseñaIngresadaStr.equals(contraseñaEnRegistro)) {
                        if ("Doctor".equalsIgnoreCase(tipoUsuarioRegistro)) {
                            abrirVentanaMenu();
                        } else if ("Paciente".equalsIgnoreCase(tipoUsuarioRegistro)) {
                            abrirMenuPacientes();
                        }
                        credencialesCorrectas = true;
                        break;
                    }
                    // Restablece las variables para la siguiente iteración
                    correoEnRegistro = "";
                    contraseñaEnRegistro = "";
                    tipoUsuarioRegistro = "";
                }
            }

            // Verifica las credenciales después de salir del bucle (para el último usuario en el archivo)

            if (!credencialesCorrectas && correoIngresado.equals(correoEnRegistro) && contraseñaIngresadaStr.equals(contraseñaEnRegistro)) {
                if ("Doctor".equalsIgnoreCase(tipoUsuarioRegistro)) {
                    abrirVentanaMenu();
                } else if ("Paciente".equalsIgnoreCase(tipoUsuarioRegistro)) {
                    abrirMenuPacientes();
                }

                credencialesCorrectas = true;
            }

            if (!credencialesCorrectas) {
                JOptionPane.showMessageDialog(this, "Contraseña Incorrecta o el usuario no existe", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (IOException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error al verificar credenciales, intente de nuevo", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private String obtenerValorDespuesDeCadena(String linea, String cadena) {
        int indiceCadena = linea.indexOf(cadena);
        if (indiceCadena != -1) {
            // Si la cadena se encuentra, devolver la parte después de la cadena
            return linea.substring(indiceCadena + cadena.length()).trim();
        } else {
            // Si la cadena no se encuentra, devolver una cadena vacía o manejar el caso según sea necesario
            return "";
        }
    }
    public static void main(String[] args) {
         Login login = new Login ();
        login.setContentPane(login.MiPanel);
        login.setSize(600,600);
        login.setDefaultCloseOperation(EXIT_ON_CLOSE);
        login.setVisible(true);
    }
}

