import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Alta_Doctor extends JFrame{
    private JButton registrarButton;
    private JTextField txtID;
    private JTextField txtNombres;
    private JTextField txtApMat;
    private JTextField txtApPat;
    private JButton btnLimpiar;
    private JTextField txtEspecialidad;
    private JTextField txtTelefono;
    private JPanel MiPanel;
    private JTextField txtEmail;
    private JComboBox comboBox1;
    private JButton btnVerificar;

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
    }

    public static void main(String[] args) {
        Alta_Doctor p = new Alta_Doctor();
        p.setContentPane(p.MiPanel);
        p.setSize(700,500);
        p.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        p.setVisible(true);
    }

    public void limpiarCampos() {
        txtID.setText("");
        txtNombres.setText("");
        txtApPat.setText("");
        txtApMat.setText("");
        txtEspecialidad.setText("");
        txtTelefono.setText("");
        txtEmail.setText("");
        JOptionPane.showMessageDialog(this,"Datos Limpiados");
    }

    public class Doctor{
        private String ID;
        private String Nombres;
        private String ApPat;
        private String ApMat;
        private String Especialidad;
        private String Telefono;
        private String Email;

        public Doctor(){
            ID ="";
            Nombres = "";
            ApPat = "";
            ApMat = "";
            Especialidad = "";
            Telefono = "";
            Email = "";
        }

        public Doctor (String ID, String Nombres, String ApPat, String ApMat, String Especialidad, String Telefono, String Email){
            this.ID = ID;
            this.Nombres = Nombres;
            this.ApPat=ApPat;
            this.ApMat=ApMat;
            this.Especialidad=Especialidad;
            this.Telefono = Telefono;
            this.Email = Email;
        }
    }

    public void verificarCampos(){
        String ID = txtID.getText();
        String Nombres = txtNombres.getText();
        String ApPat = txtApPat.getText();
        String ApMat = txtApMat.getText();
        String Especialidad = txtEspecialidad.getText();
        String Telefono = txtTelefono.getText();
        String Email = txtEmail.getText();

        if(!Nombres.isEmpty() && !ID.isEmpty() && !ApPat.isEmpty() && !ApMat.isEmpty() && !Especialidad.isEmpty() && !Telefono.isEmpty() && !Email.isEmpty()) {
            Doctor newDoctor = new Doctor(ID, Nombres, ApPat, ApMat, Especialidad, Telefono, Email);
            JOptionPane.showMessageDialog(this, "Especialista Verficado, Listo para registrar ");
        }else{
            JOptionPane.showMessageDialog(this, "Ingrese todos los datos necesarios para el registro");
            }
        }
    }


