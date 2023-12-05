/* Autor. Gerardo André Aguilar Juárez
Fecha de entrega: Jueves 07 de Diciembre del 2023
 */

//Librerías utilizadas
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

//Componentes de la interfaz gráfica.
/*Se definen componentes de la interfaz gráfica (campos de texto, botones, etc.).
        Estos elementos son utilizados para la entrada de datos, botones de acción y visualización de información en la GUI.*/
public class Alta_Paciente extends JFrame {
    private JTextField txtNombres;
    private JTextField txtApPat;
    private JTextField txtApMat;
    private JComboBox comboBox1;
    private JTextField txtEmail;
    private JTextField txtTelefono;
    private JButton btnRegistrar;
    private JButton btnLimpiar;
    private JButton btnVerificar;
    public JPanel MiPanel;
    private JTextField txtEdad;
    private JLabel lblID;
    private JTextField txtID;
    private JButton btnGenerarID;
    private JButton btnBuscar;
    private JLabel lblBuscaID;
    private JTextField txtBusquedaID;
    private JButton btnEliminar;
    private JButton btnConsultarLista;
    // Configuración de ActionListeners para los botones
    public Alta_Paciente() {
        btnLimpiar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                limpiarCampos();
            }
        });
        btnVerificar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                verificarCampos();
            }
        });
        btnRegistrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) { InsertarenArchivo();}
        });
        btnGenerarID.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) { InsertarIDentxtID();

            }
        });
        btnBuscar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) { BuscarPorID();

            }
        });
        btnEliminar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) { eliminarPaciente();

            }
        });
        btnConsultarLista.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) { consultarListaPacientes();

            }
        });
    }

    //Método principal que inicia la aplicación, creando una instancia de la clase Alta_Paciente y mostrando la interfaz gráfica.
    public static void main(String[] args) {
        Alta_Paciente d = new Alta_Paciente();
        d.setContentPane(d.MiPanel);
        d.setSize(700, 500);
        d.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        d.setVisible(true);
    }

    //COnstructor de la clase interna Paciente donde se definen los atributos
    public class Paciente {
        private String ID;
        private String Nombres;
        private String ApPat;
        private String ApMat;
        private String Telefono;
        private String Email;
        private int Edad;

        // Constructor por defecto
        public Paciente() {
            ID = "";
            Nombres = "";
            ApPat = "";
            ApMat = "";
            Telefono = "";
            Email = "";
            Edad = 0;
        }
        // Constructor con parámetros
        public Paciente (String ID, String Nombres, String ApPat, String ApMat, String Telefono, String Email, int Edad){
            this.ID = ID;
            this.Nombres = Nombres;
            this.ApPat=ApPat;
            this.ApMat=ApMat;
            this.Telefono = Telefono;
            this.Email = Email;
            this.Edad = Edad;
        }
    }

    //Métodos de la clase.
    private void limpiarCampos() { //Limpia los campos de la GUI y muestra un mensaje de confirmación.
        txtID.setText("");
        txtNombres.setText("");
        txtApPat.setText("");
        txtApMat.setText("");
        txtTelefono.setText("");
        txtEmail.setText("");
        txtEdad.setText("");
        JOptionPane.showMessageDialog(this, "Datos Limpiados");
    }
    private void verificarCampos(){ //Verifica si todos los campos obligatorios están completos antes de registrar un paciente.
        String ID = txtID.getText();
        String Nombres = txtNombres.getText();
        String ApPat = txtApPat.getText();
        String ApMat = txtApMat.getText();
        String Telefono = txtTelefono.getText();
        String Email = txtEmail.getText();
        String edadTxt = txtEdad.getText();

        if(!Nombres.isEmpty() && !ApPat.isEmpty() && !ApMat.isEmpty() && !Telefono.isEmpty() && !Email.isEmpty()) {
            try{
                int Edad = Integer.parseInt(edadTxt);
                Paciente NewPac = new Paciente ( ID,Nombres, ApPat, ApMat, Telefono,Email,Edad);

                JOptionPane.showMessageDialog(this, "Paciente Verificado");
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(this, "Edad no válida. Ingrese una edad válida");
            }
        }else{
            JOptionPane.showMessageDialog(this, "Ingrese todos los datos necesarios para el registro");
        }
    }

    private void InsertarPaciente(Paciente newPac) { //Inserta un nuevo paciente en un archivo de texto.
        String Documento = "Pacientes.txt";

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(Documento, true))) {
            writer.write(newPac.ID + ", " + newPac.Nombres + ", " + newPac.ApPat + ", " + newPac.ApMat + ", "  + newPac.Edad + ", "  + newPac.Telefono + ", " + newPac.Email+"\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void InsertarenArchivo() { //Obtiene datos de la GUI y llama a InsertarPaciente.
        String ID = txtID.getText();
        String Nombres = txtNombres.getText();
        String ApPat = txtApPat.getText();
        String ApMat = txtApMat.getText();
        String Edadtxt = txtEdad.getText();
        String Telefono = txtTelefono.getText();
        String Email = txtEmail.getText();
        int Edad = Integer.parseInt(Edadtxt);

        if (!Nombres.isEmpty() && !ID.isEmpty() && !ApPat.isEmpty() && !ApMat.isEmpty() && !Edadtxt.isEmpty() && !Telefono.isEmpty() && !Email.isEmpty()) {
            String IDtxt = txtID.getText();
            Paciente newPaciente = new Paciente(String.valueOf(IDtxt), Nombres, ApPat, ApMat, Email, Telefono, Edad);
            InsertarPaciente(newPaciente);
            JOptionPane.showMessageDialog(this, "Paciente agregado en la base de datos");
        }
    }

    private int GenerarId() { //Genera un ID aleatorio para los pacientes.
        Random numrandom = new Random();
        return numrandom.nextInt(10000) + 10000;
    }

    private void InsertarIDentxtID() { //Genera y muestra un ID aleatorio en el campo de texto txtID.
        int idrandom = GenerarId();
        String idrandstr = "P" + idrandom;
        txtID.setText(idrandstr);
    }

    private Alta_Paciente.Paciente BuscarPacientePorId(String ID) { //Busca un paciente por su ID en el archivo de texto.
        String Documento = "Pacientes.txt";
        try (BufferedReader lector = new BufferedReader(new FileReader(Documento))) {
            String linea;
            while ((linea = lector.readLine()) != null) {
                String[] partes = linea.split(", ");
                if (partes.length == 7) {
                    String idPaciente = partes[0].trim();
                    if (idPaciente.equals(ID)) {
                        return new Alta_Paciente.Paciente(idPaciente, partes[1], partes[2], partes[3],partes[4],partes[5], Integer.parseInt(partes [6]));
                    }
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private void BuscarPorID() { //Obtiene un ID desde la GUI y muestra la información del paciente correspondiente.
        String idBusqueda = txtBusquedaID.getText();

        if (!idBusqueda.isEmpty()) {
            Alta_Paciente.Paciente finded = BuscarPacientePorId(idBusqueda);
            if (finded != null) {
                JOptionPane.showMessageDialog(this, "Doctor encontrado:\n" +
                        "ID: " + finded.ID + "\n" +
                        "Nombre(s): " + finded.Nombres + " " + finded.ApPat + " " + finded.ApMat+ "\n"+
                        "Edad: " + finded.Edad+"\n"+
                        "Teléfono: " + finded.Telefono + "\n"+
                        "E-Mail: " + finded.Email);
            } else {
                JOptionPane.showMessageDialog(this, "No se encontró un paciente con ese ID.");
            }
        } else {
            JOptionPane.showMessageDialog(this, "Ingrese un ID para realizar la búsqueda.");
        }
    }

    private void eliminarPaciente(){ //Elimina un paciente del archivo de texto por su ID.
        String idEliminar = txtBusquedaID.getText();
        if (!idEliminar.isEmpty()){
            String documento = "Pacientes.txt";
            List<String> lineas =new ArrayList<>();

            try (BufferedReader lector = new BufferedReader(new FileReader(documento))) {
                String linea;
                boolean pacienteEncontrado = false;

                while ((linea = lector.readLine()) != null) {
                    String[] partes = linea.split(", ");
                    if (partes.length == 7) {
                        String idPaciente = partes[0].trim();
                        if (!idPaciente.equals(idEliminar)) {
                            lineas.add(linea);
                        } else {
                            pacienteEncontrado = true;
                        }
                    }
                }

                if (!pacienteEncontrado) {
                    JOptionPane.showMessageDialog(this, "Paciente no encontrado con ese ID");
                    return;
                }
            }catch (IOException e) {
                e.printStackTrace();
            }

            try (BufferedWriter writer = new BufferedWriter(new FileWriter(documento))){
                for (String linea:lineas){
                    writer.write(linea+"\n");
                }
            } catch (IOException e){
                e.printStackTrace();
                JOptionPane.showMessageDialog(this, "Error al tratar de eliminar al paciente");
                return;
            }

            JOptionPane.showMessageDialog(this, "Paciente eliminado");
        } else {
            JOptionPane.showMessageDialog(this, "Inngrese un ID para eliminar. ");
        }
    }

    private void consultarListaPacientes(){ //Muestra todos los pacientes registrados en el archivo de texto.
        String documento = "Pacientes.txt";

        try (BufferedReader lector = new BufferedReader(new FileReader(documento))){
            StringBuilder datosPacientes = new StringBuilder();
            String linea;

            while ((linea = lector.readLine()) != null) {
                datosPacientes.append(linea).append("\n");
            }

            if (datosPacientes.length()>0){
                JOptionPane.showMessageDialog(this, "Pacientes: \n"+datosPacientes.toString());
            } else {
                JOptionPane.showMessageDialog(this, "No existen registros de pacientes");
            }
        } catch (IOException e){
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error al consultar datos de pacientes");
        }
    }
}

