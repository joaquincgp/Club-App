package club;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainGUI extends JFrame {

    private JPanel panelMain;
    private JButton afiliateAlClubButton;
    private JButton registrarAutorizaciónButton;
    private JButton pagarFacturaButton;
    private JButton registrarConsumoButton;
    private JButton aumentarFondosButton;
    private JButton totalDeConsumosButton;
    private JLabel imagenFondos;
    private JLabel imagenTotal;
    private JLabel titulo;
    private JButton eliminarSocioButton;
    private JLabel imagenAutorizacion;

    public static void main(String[] args) {
        MainGUI panel = new MainGUI();
        panel.setContentPane(panel.panelMain);
        panel.setTitle("Club de Quito");
        panel.setSize(500,600);
        panel.setVisible(true);
        panel.setResizable(false);
        panel.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }
    public MainGUI() {

    afiliateAlClubButton.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {

        }
    });

}


}
