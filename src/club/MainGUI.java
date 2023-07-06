package club;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;

public class MainGUI extends JFrame {

    private JPanel panelMain;
    private JButton afiliateAlClubButton;
    private JButton pagarFacturaButton;
    private JButton registrarConsumoButton;
    private JButton aumentarFondosButton;
    private JButton totalDeConsumosButton;
    private JLabel imagenFondos;
    private JLabel imagenTotal;
    private JButton eliminarButton;
    private JLabel imagenAutorizacion;
    private JTextField textFieldNombreAfiliar;
    private JTextField textFieldCedulaAfiliar;
    private JTextField textFieldTipoAfiliar;
    private JTextField textFieldCedulaEliminar;
    private JLabel titulo;
    private JTextField cedulaSocioAutorizar;
    private JButton registrarAutorizaciónButton;
    private JTextField nombreNuevoAutorizado;
    private Club club = new Club();

    public static void main(String[] args) {
        MainGUI panel = new MainGUI();
        panel.setContentPane(panel.panelMain);
        panel.setTitle("Club de Quito");
        panel.setSize(500,600);
        panel.setVisible(true);
        //panel.setResizable(false);
        panel.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }
    public MainGUI() {

    afiliateAlClubButton.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            String nombre;
            do {
                nombre = textFieldNombreAfiliar.getText();
            } while (nombre == null | nombre == "");
            String cedula;
            do {
                cedula = textFieldCedulaAfiliar.getText();
            } while (cedula == null | cedula == "");
            String tipoString;
            do {
                tipoString = textFieldTipoAfiliar.getText();

            } while (tipoString == null | tipoString == "");
            club.afiliarSocio(cedula, nombre, Socio.Tipo.valueOf(tipoString));
            JOptionPane.showMessageDialog(MainGUI.this, "Se afilió al nuevo socio. Bienvenido "+club.buscarSocio(cedula).darNombre(),"EXITO", JOptionPane.INFORMATION_MESSAGE);

        }
    });

        eliminarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String cedulaEliminar;
                Socio.Tipo tipo;
                do {
                    cedulaEliminar = textFieldCedulaEliminar.getText();
                } while (cedulaEliminar == null | Objects.equals(cedulaEliminar, ""));
                boolean eliminar = club.sePuedeEliminarSocio(cedulaEliminar);
                if(eliminar){
                    JOptionPane.showMessageDialog(MainGUI.this, "Se elimino al socio "+club.buscarSocio(cedulaEliminar).darNombre(),"EXITO", JOptionPane.INFORMATION_MESSAGE);
                } else{
                    JOptionPane.showMessageDialog(MainGUI.this, "No existe el socio. No se puede eliminar", "ERROR", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        });


    }


}
