package club;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;

public class MainGUI extends JFrame {

    private JPanel panelMain;
    private JButton afiliateAlClubButton;
    private JButton pagarFacturaButton;
    private JButton eliminarButton;
    private JTextField textFieldNombreAfiliar;
    private JTextField textFieldCedulaAfiliar;
    private JTextField textFieldTipoAfiliar;
    private JTextField textFieldCedulaEliminar;
    private JTextField cedulaSocioAutorizar;
    private JButton registrarAutorizaciónButton;
    private JTextField textFieldNombreNuevoAutorizado;
    private JTextField textCedulaFactura;
    private JTextField textIndice;
    private JButton registrarConsumoButton;
    private JLabel titulo;
    private JTextField textCedulaConsumo;
    private JTextField textCliente;
    private JTextField textConcepto;
    private JTextField textValor;
    private JButton aumentarFondosButton;
    private JButton totalDeConsumosButton;
    private Club club = new Club();

    public static void main(String[] args) {
        try {
            MainGUI panel = new MainGUI();
            panel.setContentPane(panel.panelMain);
            panel.setTitle("Club de Quito");
            panel.setSize(500, 600);
            panel.setVisible(true);
            //panel.setResizable(false);
            panel.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        }catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Error: Se ingresó un formato inválido. Verifique los valores ingresados.", "Error", JOptionPane.ERROR_MESSAGE);
        } catch (IllegalArgumentException e) {
            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al ejecutar la aplicación: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    public MainGUI() {
        try {
            afiliateAlClubButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    try{
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
                    club.afiliarSocio(cedula, nombre, Socio.Tipo.valueOf(tipoString.toUpperCase()));

                }catch (Exception ex) {
                        JOptionPane.showMessageDialog(MainGUI.this, "Error al afiliar al socio: Hay algun(os) campo(s) vacio(s) o un formato no es valido", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
            });

            eliminarButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    try{
                    String cedulaEliminar;
                    Socio.Tipo tipo;
                    do {
                        cedulaEliminar = textFieldCedulaEliminar.getText();
                        if (cedulaEliminar.isEmpty()) {
                            throw new CampoVacioException("No se ha ingresado la cédula del socio a eliminar");
                        }
                    } while (cedulaEliminar == null | Objects.equals(cedulaEliminar, ""));
                    boolean eliminar = club.sePuedeEliminarSocio(cedulaEliminar);

                    if (eliminar) {
                        club.eliminarSocio(cedulaEliminar);
                        JOptionPane.showMessageDialog(MainGUI.this, "Se elimino al socio " + club.buscarSocio(cedulaEliminar).darNombre(), "EXITO", JOptionPane.INFORMATION_MESSAGE);
                    } else {
                        throw new SocioNoExisteException("No existe el socio. No se puede eliminar");
                    }
                } catch (CampoVacioException ex) {
                        JOptionPane.showMessageDialog(MainGUI.this, "Error al eliminar al socio: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                    }
                    catch (SocioNoExisteException ex) {
                        JOptionPane.showMessageDialog(MainGUI.this, "Error al eliminar al socio: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
            });


            registrarAutorizaciónButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    try{
                    String cedulaSocio;
                    cedulaSocio = cedulaSocioAutorizar.getText();
                    if (club.buscarSocio(cedulaSocio) != null) {
                        String nombreAutorizado = textFieldNombreNuevoAutorizado.getText();
                        if(nombreAutorizado != null) {
                            club.agregarAutorizadoSocio(cedulaSocio, nombreAutorizado);
                            JOptionPane.showMessageDialog(MainGUI.this, nombreAutorizado + " ahora esta autorizado", "EXITO", JOptionPane.INFORMATION_MESSAGE);
                        }
                    } else {
                       throw new SocioNoExisteException("El socio no existe");
                    }
                }
                    catch (SocioNoExisteException ex) {
                        JOptionPane.showMessageDialog(MainGUI.this, "Error al registrar la autorización: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
            });
            pagarFacturaButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    try{
                    String cedulaFactura;
                    String titular;
                    cedulaFactura = textCedulaFactura.getText();
                    titular = textIndice.getText();
                    if (!cedulaFactura.isEmpty() && !titular.isEmpty()) {
                        club.pagarFacturaSocio(cedulaFactura, titular);
                    } else {
                        throw new CampoVacioException("Algun campo esta vacio. Ingresa datos nuevamente");
                    }
                }catch (CampoVacioException ex) {
                        JOptionPane.showMessageDialog(MainGUI.this, "Error al pagar factura: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
            });


            registrarConsumoButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    try{
                    String cedulaSocioConsumo = textCedulaConsumo.getText();
                    String nombreCliente = textCliente.getText();
                    String concepto = textConcepto.getText();
                    String valor = (textValor.getText());
                        if (cedulaSocioConsumo.isEmpty() | nombreCliente.isEmpty() | concepto.isEmpty()) {
                            throw new CampoVacioException("Algun campo esta vacio o incorrecto");
                        }else{
                            club.registrarConsumo(cedulaSocioConsumo, nombreCliente, concepto, Double.parseDouble(valor));
                        }
                } catch (CampoVacioException ex) {
                        JOptionPane.showMessageDialog(MainGUI.this, "Error al registrar consumo: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
            });
            aumentarFondosButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    try{
                    String cedulaSocioFondos = JOptionPane.showInputDialog("Cedula del titular");
                    double valorAumentar = Double.parseDouble(JOptionPane.showInputDialog("Valor para acreditar"));
                    if (cedulaSocioFondos != null && valorAumentar > 0) {
                        club.aumentarFondosSocio(cedulaSocioFondos, valorAumentar);
                        JOptionPane.showMessageDialog(MainGUI.this, "Los fondos se aumentaron correctamente");
                    } else {
                        JOptionPane.showMessageDialog(MainGUI.this, "Ingresa valores validos");

                    }
                }catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(MainGUI.this, "Error: Se ingresó un formato inválido. Verifique los valores ingresados.", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
            });
            totalDeConsumosButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    try{
                    String cedulaConsumos = JOptionPane.showInputDialog("Ingrese la cedula de la persona que quiere consultar el total de consumos");
                    double totalConsumos = club.obtenerTotalConsumos(cedulaConsumos);
                    if (totalConsumos != 0) {
                        JOptionPane.showMessageDialog(MainGUI.this, "El total de consumos de " + club.buscarSocio(cedulaConsumos).darNombre() + " es $" + totalConsumos);
                    } else {
                        JOptionPane.showMessageDialog(MainGUI.this, "Socio no existe");
                    }
                }catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(MainGUI.this, "Error: Se ingresó un formato inválido. Verifique los valores ingresados.", "Error", JOptionPane.ERROR_MESSAGE);
                    } catch (IllegalArgumentException ex) {
                        JOptionPane.showMessageDialog(MainGUI.this, "Error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(MainGUI.this, "Error al registrar la autorización: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
            });
        }catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al ejecutar la aplicación: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }


}
