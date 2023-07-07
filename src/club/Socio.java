package club;
import javax.swing.*;
import java.util.ArrayList;

/**
 * Clase que modela un socio.
 */
public class Socio
{
    // -----------------------------------------------------------------
    // Enumeraciones
    // -----------------------------------------------------------------

    /**
     * Enumeraciones para los tipos de suscripci�n.
     */
    public enum Tipo
    {
        /**
         * Representa el socio VIP.
         */
        VIP,
        /**
         * Representa el socio regular.
         */
        REGULAR
    }
    // -----------------------------------------------------------------
    // Constantes
    // -----------------------------------------------------------------

    /**
     * Dinero base con el que empiezan todos los socios regulares.
     */
    public final static double FONDOS_INICIALES_REGULARES = 50;

    /**
     * Dinero base con el que empiezan todos los socios VIP.
     */
    public final static double FONDOS_INICIALES_VIP = 100;

    /**
     * Dinero m�ximo que puede tener un socio regular en sus fondos.
     */
    public final static double MONTO_MAXIMO_REGULARES = 1000;

    /**
     * Dinero m�ximo que puede tener un socio VIP en sus fondos.
     */
    public final static double MONTO_MAXIMO_VIP = 5000;

    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------

    /**
     * C�dula del socio.
     */
    private String cedula;

    /**
     * Nombre del socio.
     */
    private String nombre;

    /**
     * Dinero que el socio tiene disponible.
     */
    private double fondos;

    /**
     * Tipo de subscripci�n del socio.
     */
    private Tipo tipoSubscripcion;

    /**
     * Facturas que tiene por pagar el socio.
     */
    private ArrayList<Factura> facturas;

    /**
     * Nombres de las personas autorizadas para este socio.
     */
    private ArrayList<String> autorizados;

    // -----------------------------------------------------------------
    // Constructor
    // -----------------------------------------------------------------
    /**
     * Crea un socio del club. <br>
     * <b>post: </b> Se cre� un objeto socio con los valores pasados por par�metro.<br>
     * El vector de facturas y el vector de autorizados fueron inicializados. <br>
     * Se inicializaron los fondos disponibles en FONDOS_INICIALES.
     * @param pCedula Corresponde a la c�dula del socio nuevo. pCedula != null && pCedula != "".
     * @param pNombre Corresponde al nombre del socio nuevo. pNombre != null && pNombre != "".
     * @param pTipo Corresponde al tipo de subscripci�n del socio. pTipo pertenece {Tipo.VIP, Tipo.REGULAR}.
     */
    public Socio( String pCedula, String pNombre, Tipo pTipo )
    {
        try{
            if (pCedula == null || pCedula.isEmpty() || pNombre == null || pNombre.isEmpty() || pTipo == null) {
                throw new IllegalArgumentException("Parámetros inválidos para crear un socio.");
            }
        cedula = pCedula;
        nombre = pNombre;
        tipoSubscripcion = pTipo;

        switch( tipoSubscripcion )
        {
            case VIP:
                fondos = FONDOS_INICIALES_VIP;
                break;
            default:
                fondos = FONDOS_INICIALES_REGULARES;
        }

        facturas = new ArrayList<Factura>( );
        autorizados = new ArrayList<String>( );
    }catch(NullPointerException | IllegalArgumentException e){
            JOptionPane.showMessageDialog(null, "Error al crear el socio: " + e.getMessage(), "Error", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    // -----------------------------------------------------------------
    // M�todos
    // -----------------------------------------------------------------

    /**
     * Retorna el nombre del socio. <br>
     * @return El nombre del socio.
     */
    public String darNombre( )
    {
        return nombre;
    }

    /**
     * Retorna la c�dula del socio. <br>
     * @return La c�dula del socio.
     */
    public String darCedula( )
    {
        return cedula;
    }

    /**
     * Retorna los fondos disponibles del socio. <br>
     * @return Los fondos del socio.
     */
    public double darFondos( )
    {
        return fondos;
    }

    /**
     * Retorna el tipo de subscripci�n del socio. <br>
     * @return El tipo de subscripci�n del socio.
     */
    public Tipo darTipo( )
    {
        return tipoSubscripcion;
    }

    /**
     * Retorna la lista de facturas. <br>
     * @return Retorna una lista con todas las facturas pendientes de pago del socio.
     */
    public ArrayList<Factura> darFacturas( )
    {
        return facturas;
    }

    /**
     * Retorna la lista de autorizados por el socio. <br>
     * @return La lista con los nombres de los autorizados por este socio.
     */
    public ArrayList<String> darAutorizados( )
    {
        return autorizados;
    }

    /**
     * Indica si un autorizado pertenece o no a lista del socio. <br>
     * <b>pre: </b> La lista de autorizados ha sido inicializada. <br>
     * @param pNombreAutorizado Nombre del autorizado a buscar. pNombreAutorizado != null && pNombreAutorizado != "".
     * @return True si en la lista existe un autorizado con el nombre dado, false de lo contrario.
     */
    private boolean existeAutorizado( String pNombreAutorizado )
    {
        boolean encontro = false;

        for( int i = 0; i < autorizados.size( ) && !encontro; i++ )
        {
            String a = autorizados.get( i );
            if( a.equals( pNombreAutorizado ) )
            {
                encontro = true;
            }
        }
        return encontro;
    }
    
    /**
     * Indica si un autorizado tiene una factura asociada.<br>
     * <b>pre: </b> La lista de facturas ha sido inicializada. <br>
     * @param pNombreAutorizado Nombre del autorizado a verificar. pNombreAutorizado != null && pNombreAutorizado != "".
     * @return True si el autorizado tiene factura asociada, false de lo contrario.
     */
    private boolean tieneFacturaAsociada( String pNombreAutorizado)
    {
        boolean tiene = false;
        for( int i = 0; i < facturas.size( ) && !tiene; i++ )
        {
            Factura factura = facturas.get( i );
            if( factura.darNombre( ).equals( pNombreAutorizado ) )
            {
                tiene = true;
            }
        }
        
        return tiene;
    }

    /**
     * Aumenta los fondos disponibles del socio.
     * @param pFondos Valor por adicionar a los fondos. pFondos > 0.
     *
     */
    public void aumentarFondos( double pFondos )
    {
        try {
            if (pFondos < 0) {
                throw new IllegalArgumentException("El valor de aumento de fondos debe ser mayor o igual a cero.");
            }
            if (tipoSubscripcion == Tipo.VIP && pFondos + fondos > MONTO_MAXIMO_VIP) {
                JOptionPane.showMessageDialog(null, "Con este monto se exceder�an los fondos m�ximos de un socio VIP, ingrese una cantidad menor");


            } else if (tipoSubscripcion == Tipo.REGULAR && pFondos + fondos > MONTO_MAXIMO_REGULARES) {
                JOptionPane.showMessageDialog(null, "Con este monto se exceder�an los fondos m�ximos de un socio regular, ingrese una cantidad menor");
            } else {
                fondos = fondos + pFondos;
            }
        }catch (IllegalArgumentException e){
            JOptionPane.showMessageDialog(null, "Error al aumentar los fondos del socio: " + e.getMessage());
        }catch (Exception e) {
            JOptionPane.showMessageDialog(null,"Error desconocido al aumentar los fondos del socio" + e.getMessage());
        }
    }

    /**
     * Registra un nuevo consumo para el socio, realizado por �l mismo o por una de sus personas autorizadas. <br>
     * <b>pre: </b> La lista de facturas ha sido inicializada. <br>
     * El nombre pertenece a la lista de autorizados.<br>
     * <b>post: </b> Se agreg� una nueva factura .
     * @param pNombre El nombre de la persona que realiz� el consumo. pNombre != null && pNombre != "".
     * @param pConcepto Es la descripci�n del consumo. pConcepto != null && pConcepto != "".
     * @param pValor Es el valor del consumo. pValor >= 0.
     *
     */
    public void registrarConsumo( String pNombre, String pConcepto, double pValor )
    {
        try {
            if (pNombre == null || pNombre.isEmpty() || pConcepto == null || pConcepto.isEmpty() || pValor <= 0) {
                throw new IllegalArgumentException("Parámetros inválidos para registrar un consumo.");
            }
            if (pValor > fondos) {
                JOptionPane.showMessageDialog(null, "El socio no posee fondos suficientes para este consumo");
            }
            Factura nuevaFactura = new Factura(pNombre, pConcepto, pValor);
            facturas.add(nuevaFactura);
            JOptionPane.showMessageDialog(null, "Consumo registrado con exito");
            if (!existeAutorizado(pNombre)) {
                JOptionPane.showMessageDialog(null, "El cliente no tiene personas autorizadas");
            }
        }catch(IllegalArgumentException e) {
            JOptionPane.showMessageDialog(null,"Error al registrar el consumo del socio: " + e.getMessage());
        }catch (Exception e) {
            JOptionPane.showMessageDialog(null,"Error desconocido al registrar consumo" + e.getMessage());
        }
    }

    /**
     * Agrega una nueva persona autorizada al socio. <br>
     * <b>pre: </b> La lista de autorizados ha sido inicializada. <br>
     * <b>post: </b> Se agreg� un nuevo autorizado.
     * @param pNombreAutorizado Es el nombre de la nueva persona autorizada para el socio. pNombreAutorizado != null.
     *
     */
    public void agregarAutorizado( String pNombreAutorizado ) {
        try {
            if (pNombreAutorizado == null || pNombreAutorizado.isEmpty()) {
                throw new IllegalArgumentException("El nombre del autorizado a agregar es inválido.");
            }
            // Verificar que el nombre del socio no es el mismo del que se quiere autorizar
            if (pNombreAutorizado.equals(darNombre())) {
                JOptionPane.showMessageDialog(null, "No puede agregar el socio como autorizado.");
            }

            // Verificar que el socio posee fondos para financiar un nuevo autorizado
            if (fondos == 0) {
                JOptionPane.showMessageDialog(null, "El socio no tiene fondos para financiar un nuevo autorizado.");
            }
            // Si el nombre no exist�a entonces lo agregamos
            if (!existeAutorizado(pNombreAutorizado)) {
                autorizados.add(pNombreAutorizado);
            } else {
                JOptionPane.showMessageDialog(null, "El autorizado ya existe.");
            }
        } catch (IllegalArgumentException e) {
            JOptionPane.showMessageDialog(null,"Error al agregar un autorizado al socio: " + e.getMessage());
        }catch (Exception e) {
            JOptionPane.showMessageDialog(null,"Error desconocido al agregar autorizado" + e.getMessage());
        }
    }

    /**
     * Elimina el autorizado del socio con el nombre dado. <br>
     * <b>pre: </b> La lista de autorizados ha sido inicializada. <br>
     * <b>post: </b> Se elimin� un socio, con nombre igual a alguno de los vinculados.
     * @param pNombreAutorizado Nombre del autorizado. pNombreAutorizado != null.
     *
     */
    public void eliminarAutorizado( String pNombreAutorizado ) {
        try {
            if (pNombreAutorizado == null || pNombreAutorizado.isEmpty()) {
                throw new IllegalArgumentException("El nombre del autorizado a eliminar es inválido.");
            }
            boolean encontro = false;
            int numAutorizados = autorizados.size();
            if (tieneFacturaAsociada(pNombreAutorizado)) {
                JOptionPane.showMessageDialog(null, pNombreAutorizado + " tiene una factura sin pagar.");
            }
            for (int i = 0; i < numAutorizados && !encontro; i++) {
                String a = autorizados.get(i);
                if (a.equals(pNombreAutorizado)) {
                    encontro = true;
                    autorizados.remove(i);
                }
            }
        }catch (IllegalArgumentException e) {
            JOptionPane.showMessageDialog(null,"Error al eliminar un autorizado del socio: " + e.getMessage());
        }catch (Exception e) {
            JOptionPane.showMessageDialog(null,"Error desconocido al eliminar un autorizado" + e.getMessage());
        }
    }

    /**
     * Paga la factura con el �ndice dado. <br>
     * <b>pre: </b> La lista de facturas ha sido inicializada. <br>
     * <b>post: </b> Se borr� la factura de la lista de facturas.
     * @param pNombre Nombre del titular al que se hizo la factura a eliminar. facturaIndice >= 0.
     *
     */
    public void pagarFactura( String pNombre )
    {
            int indice = 0;
            for (Factura f : facturas) {
                if (f.darNombre().equals(pNombre)) {
                    fondos = fondos - f.darValor();
                    facturas.remove(indice);
                }
                if (f.darValor() > fondos) {
                    JOptionPane.showMessageDialog(null, "El socio no posee fondos suficientes para pagar esta factura");
                }
                indice++;
            }
    }

    /**
     * Retorna la cadena que representa al socio.
     * @return Cadena de caracteres con la informaci�n del socio con el siguiente formato: <c�dula> - <nombre>.
     */
    public String toString( )
    {
        String socio = cedula + " - " + nombre;
        return socio;
    }

    public double totalConsumos() {
        double total = 0;
        for (Factura factura : facturas) {
            total += factura.darValor();
        }
        return total;
    }

}