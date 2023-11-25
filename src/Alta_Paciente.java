import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
    private JPanel MiPanel;
    private JTextField txtEdad;

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
    }

    public static void main(String[] args) {
        Alta_Paciente d = new Alta_Paciente();
        d.setContentPane(d.MiPanel);
        d.setSize(700, 500);
        d.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        d.setVisible(true);
    }

    public void limpiarCampos() {

        txtNombres.setText("");
        txtApPat.setText("");
        txtApMat.setText("");
        txtTelefono.setText("");
        txtEmail.setText("");
        txtEdad.setText("");
        JOptionPane.showMessageDialog(this, "Datos Limpiados");
    }

    public class Paciente {
        private String Nombres;
        private String ApPat;
        private String ApMat;
        private String Telefono;
        private String Email;
        private int Edad;

        public Paciente() {
            Nombres = "";
            ApPat = "";
            ApMat = "";
            Telefono = "";
            Email = "";
            Edad = 0;
        }
        public Paciente (String Nombres, String ApPat, String ApMat, String Telefono, String Email, int Edad){
            this.Nombres = Nombres;
            this.ApPat=ApPat;
            this.ApMat=ApMat;
            this.Telefono = Telefono;
            this.Email = Email;
            this.Edad = Edad;
        }
    }
    public void verificarCampos(){
        String Nombres = txtNombres.getText();
        String ApPat = txtApPat.getText();
        String ApMat = txtApMat.getText();
        String Telefono = txtTelefono.getText();
        String Email = txtEmail.getText();
        String edadTxt = txtEdad.getText();

        if(!Nombres.isEmpty() && !ApPat.isEmpty() && !ApMat.isEmpty() && !Telefono.isEmpty() && !Email.isEmpty()) {
            try{
                int Edad = Integer.parseInt(edadTxt);
                Paciente NewPac = new Paciente ( Nombres, ApPat, ApMat, Telefono,Email,Edad);

                JOptionPane.showMessageDialog(this, "Paciente Verificado");
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(this, "Edad no válida. Ingrese una edad válida");
            }
        }else{
            JOptionPane.showMessageDialog(this, "Ingrese todos los datos necesarios para el registro");
        }
    }
}

