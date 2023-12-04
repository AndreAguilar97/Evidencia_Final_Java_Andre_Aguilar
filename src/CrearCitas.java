import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.Random;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CrearCitas extends JFrame {
    private JTextField txtID;
    private JTextField txtFecha;
    private JTextField txtHora;
    private JTextField txtPaciente;
    private JButton btnBuscarPaciente;
    private JTextField txtDatosPaciente;
    private JButton btnGenerar;
    private JButton btnLimpiar;
    private JButton btnCreatCita;
    private JTextArea txtAreaObservaciones;
    public JPanel MiPanel;

    public CrearCitas() {
        btnGenerar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                InsertarIDEnTextPanel();
            }
        });
        btnLimpiar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                limpiarCampos();
            }
        });
        btnCreatCita.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                VerificarEInsertarEnArchivo();
            }
        });
        btnBuscarPaciente.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Obtenemos el ID del campo txtPaciente
                String idPaciente = txtPaciente.getText();

                // Verificamos si se proporcionó un ID
                if (!idPaciente.isEmpty()) {
                    // Llamamos al método buscarPacientePorID con el ID proporcionado
                    buscarPacientePorID(idPaciente);
                } else {
                    JOptionPane.showMessageDialog(CrearCitas.this, "Por favor, ingrese un ID de paciente", "Error", JOptionPane.ERROR_MESSAGE);
                    txtDatosPaciente.setText("");
                }
            }
        });
    }

    public class Citas{
        private String paciente;
        private String id;
        private String fecha;
        private String hora;
        private String datos;
        private String observaciones;

        public Citas(String paciente, String id, String fecha, String hora, String datos, String observaciones){
            this.paciente = paciente;
            this.id = id;
            this.fecha = fecha;
            this.hora = hora;
            this.datos = datos;
            this.observaciones = observaciones;
        }
    }
    public void limpiarCampos(){
        txtID.setText("");
        txtFecha.setText("");
        txtHora.setText("");
        txtPaciente.setText("");
        txtAreaObservaciones.setText("");
        txtDatosPaciente.setText("");
        JOptionPane.showMessageDialog(this, "Datos limpiados con éxito");
    }
    private void VerificarEInsertarEnArchivo(){
        String idTxt = txtID.getText();
        String fecha = txtFecha.getText();
        String hora = txtHora.getText();
        String paciente = txtPaciente.getText();
        String datos = txtDatosPaciente.getText();
        String observaciones = txtAreaObservaciones.getText();

        // Verificar que las fechas y horas tengan el formato correcto y sean válidas
        if (verificarFormatoFecha(fecha) && verificarFormatoHora(hora) &&
                !idTxt.isEmpty() && !paciente.isEmpty() && !datos.isEmpty() && !observaciones.isEmpty()) {
            Citas newCita = new Citas(paciente, idTxt, fecha, hora, datos, observaciones);
            InsertarCita(newCita);
            JOptionPane.showMessageDialog(this, "Cita Insertada en el Archivo:\n" + "Paciente: " + newCita.paciente + "\n" + "ID: " + newCita.id + "\n" + "Fecha: " + newCita.fecha + "\n" + "Hora: " + newCita.hora + "\n" + "Datos: " + newCita.datos + "\n" + "Observaciones: " + newCita.observaciones);
        } else {
            JOptionPane.showMessageDialog(this, "Por favor, complete todos los campos correctamente", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }


    private void buscarPacientePorID(String id) {
        String documentoPacientes = "Pacientes.txt";
        boolean pacienteEncontrado = false;

        try (BufferedReader br = new BufferedReader(new FileReader(documentoPacientes))) {
            String line;

            while ((line = br.readLine()) != null) {
                // Asumiendo que las líneas en el archivo tienen el formato "Nombre, ID, Edad, Teléfono, Género"
                String[] partes = line.split(", ");

                // Verificar si la línea contiene el ID buscado
                if (partes.length >= 2) {
                    String idEnArchivo = partes[0].trim();
                    if (idEnArchivo.equals(id)) {
                        // Se encontró el paciente con el ID especificado
                        String datosPaciente = obtenerDatosPacienteDesdeLinea(line);
                        txtDatosPaciente.setText(datosPaciente);
                        JOptionPane.showMessageDialog(this, datosPaciente);
                        pacienteEncontrado = true;
                        break;
                    }
                }
            }

            if (!pacienteEncontrado) {
                JOptionPane.showMessageDialog(this, "Paciente no encontrado", "Error", JOptionPane.ERROR_MESSAGE);
                txtDatosPaciente.setText("");
            }
        } catch (IOException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error al buscar paciente", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    private String obtenerDatosPacienteDesdeLinea(String linea) {
        // Asumiendo que las líneas en el archivo tienen el formato "Nombre, ID, Edad, Teléfono, Género"
        String[] partes = linea.split(", ");
        String id = partes[0].trim();
        String nombre = partes[1].trim();
        String apPat = partes[2].trim();
        String apMat = partes[3].trim();
        String edad = partes[4].trim();
        String email = partes[5].trim();
        String telefono = partes[6].trim();


        // Formatear los datos según tus necesidades
        String datosFormateados = "ID: "+id+"\n Nombre: "+nombre+"  "+apPat+" "+apMat+"\n Edad: "+edad+ "\n Email: "+email+ "\nTeléfono: "+telefono;


        return datosFormateados;
    }

    private boolean verificarFormatoFecha(String fecha) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        dateFormat.setLenient(false);

        try {
            Date parsedDate = dateFormat.parse(fecha);
            return true;
        } catch (ParseException e) {
            return false;
        }
    }

    private boolean verificarFormatoHora(String hora) {
        SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm");
        timeFormat.setLenient(false);

        try {
            Date parsedTime = timeFormat.parse(hora);
            return true;
        } catch (ParseException e) {
            return false;
        }
    }
    private void InsertarCita(Citas newCita){
        String Archivo = "Citas.txt";

        try(BufferedWriter escritor = new BufferedWriter(new FileWriter(Archivo, true))){
            escritor.write(newCita.paciente + ", " + newCita.id + ", " + newCita.fecha+ ", " + newCita.hora + ", " + newCita.datos + ", " + newCita.observaciones + "\n");
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    private int GenerarNumRandom(){
        Random random = new Random();
        return random.nextInt(9000)+1000;
    }
    private void InsertarIDEnTextPanel() {
        int numran = GenerarNumRandom();
        String idRan = "C" + numran;
        txtID.setText(idRan);
    }

    public static void main(String[] args) {
        CrearCitas citas = new CrearCitas();
        citas.setContentPane(citas.MiPanel);
        citas.setSize(800,300);
        citas.setDefaultCloseOperation(EXIT_ON_CLOSE);
        citas.setVisible(true);
    }
}
