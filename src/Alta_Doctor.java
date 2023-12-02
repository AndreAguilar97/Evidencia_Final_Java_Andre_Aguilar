import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class Alta_Doctor extends JFrame {
    private JButton btnRegistrar;
    private JTextField txtID;
    private JTextField txtNombres;
    private JTextField txtApMat;
    private JTextField txtApPat;
    private JButton btnLimpiar;
    private JTextField txtEspecialidad;
    private JTextField txtTelefono;
    public JPanel MiPanel;
    private JTextField txtEmail;
    private JComboBox comboBox1;
    private JButton btnVerificar;
    private JButton btnGenerarID;
    private JTextField txtBusquedaID;
    private JButton btnBuscar;
    private JLabel lblBuscaID;
    private JButton btnEliminar;
    private JButton btnConsultarLista;

    public Alta_Doctor() {
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
        btnBuscar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                BuscarPorID();
            }
        });
        btnGenerarID.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                InsertarIDentxtID();
            }
        });
        btnRegistrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                InsertarenArchivo();
            }
        });
        btnEliminar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                eliminarDoctor();
            }
        });
        btnConsultarLista.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                consultarListaDoctores();
            }
        });
    }
    public class Doctor {
        private String ID;
        private String Nombres;
        private String ApPat;
        private String ApMat;
        private String Especialidad;
        private String Telefono;
        private String Email;

        public Doctor() {
            ID = "";
            Nombres = "";
            ApPat = "";
            ApMat = "";
            Especialidad = "";
            Telefono = "";
            Email = "";
        }

        public Doctor(String ID, String Nombres, String ApPat, String ApMat, String Especialidad, String Telefono, String Email) {
            this.ID = ID;
            this.Nombres = Nombres;
            this.ApPat = ApPat;
            this.ApMat = ApMat;
            this.Especialidad = Especialidad;
            this.Telefono = Telefono;
            this.Email = Email;
        }
    }

    public void limpiarCampos() {
        txtID.setText("");
        txtNombres.setText("");
        txtApPat.setText("");
        txtApMat.setText("");
        txtEspecialidad.setText("");
        txtTelefono.setText("");
        txtEmail.setText("");
        txtBusquedaID.setText("");
        JOptionPane.showMessageDialog(this, "Datos Limpiados");
    }

    public void verificarCampos() {
        String ID = txtID.getText();
        String Nombres = txtNombres.getText();
        String ApPat = txtApPat.getText();
        String ApMat = txtApMat.getText();
        String Especialidad = txtEspecialidad.getText();
        String Telefono = txtTelefono.getText();
        String Email = txtEmail.getText();

        if (!Nombres.isEmpty() && !ID.isEmpty() && !ApPat.isEmpty() && !ApMat.isEmpty() && !Especialidad.isEmpty() && !Telefono.isEmpty() && !Email.isEmpty()) {
            Doctor newDoctor = new Doctor(ID, Nombres, ApPat, ApMat, Especialidad, Telefono, Email);
            JOptionPane.showMessageDialog(this, "Especialista Verficado, Listo para registrar ");
        } else {
            JOptionPane.showMessageDialog(this, "Ingrese todos los datos necesarios para el registro");
        }
    }

    private void InsertarDoctor(Doctor newDoc) {
        String Documento = "Doctores.txt";

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(Documento, true))) {
            writer.write(newDoc.ID + ", " + newDoc.Nombres + ", " + newDoc.ApPat + ", " + newDoc.ApMat + ", "  + newDoc.Especialidad + ", "  + newDoc.Telefono + ", " + newDoc.Email+"\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void InsertarenArchivo() {
        String ID = txtID.getText();
        String Nombres = txtNombres.getText();
        String ApPat = txtApPat.getText();
        String ApMat = txtApMat.getText();
        String Especialidad = txtEspecialidad.getText();
        String Telefono = txtTelefono.getText();
        String Email = txtEmail.getText();

        if (!Nombres.isEmpty() && !ID.isEmpty() && !ApPat.isEmpty() && !ApMat.isEmpty() && !Especialidad.isEmpty() && !Telefono.isEmpty() && !Email.isEmpty()) {
            String IDtxt = txtID.getText();
            Doctor newDoctor = new Doctor(String.valueOf(IDtxt), Nombres, ApPat, ApMat, Especialidad, Telefono, Email);
            InsertarDoctor(newDoctor);
            JOptionPane.showMessageDialog(this, "Especilista agregado en la base de datos");
        }
    }

    private int GenerarId() {
        Random numrandom = new Random();
        return numrandom.nextInt(10000) + 10000;
    }

    private void InsertarIDentxtID() {
        int idrandom = GenerarId();
        String idrandstr = "D" + idrandom;
        txtID.setText(idrandstr);
    }

    private Doctor BuscarDoctorPorId(String ID) {
        String Documento = "Doctores.txt";
        try (BufferedReader lector = new BufferedReader(new FileReader(Documento))) {
            String linea;
            while ((linea = lector.readLine()) != null) {
                String[] partes = linea.split(", ");
                if (partes.length == 7) {
                    String idDoctor = partes[0].trim();
                    if (idDoctor.equals(ID)) {
                        return new Doctor(idDoctor, partes[1], partes[2], partes[3],partes[4],partes[5], partes [6]);
                    }
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private void BuscarPorID() {
        String idBusqueda = txtBusquedaID.getText();

        if (!idBusqueda.isEmpty()) {
            Doctor finded = BuscarDoctorPorId(idBusqueda);
            if (finded != null) {
                JOptionPane.showMessageDialog(this, "Doctor encontrado:\n" +
                        "ID: " + finded.ID + "\n" +
                        "Nombre(s): " + finded.Nombres + " " + finded.ApPat + " " + finded.ApMat+ "\n"+
                        "Especialidad: " + finded.Especialidad+"\n"+
                        "Teléfono: " + finded.Telefono + "\n"+
                        "E-Mail: " + finded.Email);
            } else {
                JOptionPane.showMessageDialog(this, "No se encontró un doctor con ese ID.");
            }
        } else {
            JOptionPane.showMessageDialog(this, "Ingrese un ID para realizar la búsqueda.");
        }
    }
    public static void main (String[]args){
        Alta_Doctor p = new Alta_Doctor();
        p.setContentPane(p.MiPanel);
        p.setSize(700, 500);
        p.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        p.setVisible(true);
    }

    private void eliminarDoctor(){
        String idEliminar = txtBusquedaID.getText();
        if (!idEliminar.isEmpty()){
            String documento = "Doctores.txt";
            List<String> lineas =new ArrayList<>();

            try (BufferedReader lector = new BufferedReader(new FileReader(documento))) {
                String linea;
                boolean doctorEncontrado = false;

                while ((linea = lector.readLine()) != null) {
                    String[] partes = linea.split(", ");
                    if (partes.length == 7) {
                        String idDoctor = partes[0].trim();
                        if (!idDoctor.equals(idEliminar)) {
                            lineas.add(linea);
                        } else {
                            doctorEncontrado = true;
                        }
                    }
                }

                if (!doctorEncontrado) {
                    JOptionPane.showMessageDialog(this, "Especialista no encontrado con ese ID");
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
                JOptionPane.showMessageDialog(this, "Error al tratar de eliminar al especialista");
                return;
            }

            JOptionPane.showMessageDialog(this, "Especialista eliminado");
        } else {
            JOptionPane.showMessageDialog(this, "Inngrese un ID para eliminar. ");
        }
    }

    private void consultarListaDoctores(){
        String documento = "Doctores.txt";

        try (BufferedReader lector = new BufferedReader(new FileReader(documento))){
            StringBuilder datosDoctores = new StringBuilder();
            String linea;

            while ((linea = lector.readLine()) != null) {
                datosDoctores.append(linea).append("\n");
            }

            if (datosDoctores.length()>0){
                JOptionPane.showMessageDialog(this, "Especialistas: \n"+datosDoctores.toString());
            } else {
                JOptionPane.showMessageDialog(this, "No existen registros de especialistas");
            }
        } catch (IOException e){
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error al consultar datos de especialistas");
        }
    }
}


