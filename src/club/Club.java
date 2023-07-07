
package club;
import java.util.ArrayList;
import club.Socio.Tipo;

import javax.swing.*;

/**
 * Clase que modela un club.
 */
public class Club
{

    // -----------------------------------------------------------------
    // Constantes
    // -----------------------------------------------------------------

    /**
     * Cantidad m�xima de socios VIP que acepta el club.
     */
    public final static int MAXIMO_VIP = 3;

    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------
    /**
     * Lista de socios del club.
     */
    private ArrayList<Socio> socios;

    // -----------------------------------------------------------------
    // Constructor
    // -----------------------------------------------------------------

    /**
     * Constructor de la clase. <br>
     * <b>post: </b> Se inicializ� la lista de socios.
     */
    public Club( )
    {
        socios = new ArrayList<Socio>( );
    }

    // -----------------------------------------------------------------
    // M�todos
    // -----------------------------------------------------------------

    /**
     * Retorna los socios afiliados al club.
     * @return Lista de socios.
     */
    public ArrayList<Socio> darSocios( )
    {
        return socios;
    }

    /**
     * Afilia un nuevo socio al club. <br>
     * <b>pre: </b> La lista de socios est� inicializada. <br>
     * <b>post: </b> Se ha afiliado un nuevo socio en el club con los datos dados.
     * @param pCedula C�dula del socio a afiliar. pCedula != null && pCedula != "".
     * @param pNombre Nombre del socio a afiliar. pNombre != null && pNombre != "".
     * @param pTipo Es el tipo de subscripci�n del socio. pTipo != null.
     *
     */
    public void afiliarSocio( String pCedula, String pNombre, Tipo pTipo )
    {
        try{
        // Revisar que no haya ya un socio con la misma c�dula
        Socio s = buscarSocio( pCedula );
        if( pTipo == Tipo.VIP && contarSociosVIP( ) == MAXIMO_VIP )
        {
            JOptionPane.showMessageDialog(null,"El club en el momento no acepta m�s socios VIP" );

        }
        // Revisar que no se haya alcanzado el l�mite de subscripciones VIP
        if( s == null )
        {
            // Se crea el objeto del nuevo socio (todav�a no se ha agregado al club)
            Socio nuevoSocio = new Socio( pCedula, pNombre, pTipo );
            // Se agrega el nuevo socio al club
            socios.add( nuevoSocio );
            JOptionPane.showMessageDialog(null, "Se afilió al nuevo socio. Bienvenido "+pNombre,"EXITO", JOptionPane.INFORMATION_MESSAGE);
        }
        else
        {
            JOptionPane.showMessageDialog( null,"El socio ya existe" );

        }
    }catch (Exception e) {
            throw new IllegalArgumentException("Error al afiliar al socio: " + e.getMessage());
        }
    }

    /**
     * Retorna el socio con la c�dula dada. <br>
     * <b> pre:<b> La lista de socios est� inicializada.<br>
     * @param pCedulaSocio C�dula del socio buscado. pCedulaSocio != null && pCedulaSocio != "".
     * @return El socio buscado, null si el socio buscado no existe.
     */
    public Socio buscarSocio( String pCedulaSocio )
    {
        try{
        Socio elSocio = null;

        boolean encontre = false;
        int numSocios = socios.size( );
        for( int i = 0; i < numSocios && !encontre; i++ )
        {
            Socio s = socios.get( i );
            if( s.darCedula( ).equals( pCedulaSocio ) )
            {
                elSocio = s;
                encontre = true;
            }
        }

        return elSocio;
    }catch (Exception e) {
            throw new IllegalArgumentException("Error al buscar el socio: " + e.getMessage());
        }
    }

    /**
     * Retorna la cantidad de socios VIP que tiene el club.<br>
     * <b> pre: </b> La lista de socios est� inicializada.
     * @return N�mero de socios VIP.
     */
    public int contarSociosVIP( )
    {
        try{
        int conteo = 0;
        for( Socio socio : socios )
        {
            if( socio.darTipo( ) == Tipo.VIP )
            {
                conteo++;
            }
        }
        return conteo;
    }catch (Exception e) {
            throw new IllegalArgumentException("Error al contar los socios VIP: " + e.getMessage());
        }
    }

    /**
     * Retorna la lista de autorizados del socio con la c�dula dada.<br>
     * <b> pre: </b> La lista de socios est� inicializada.<br>
     * El socio buscado existe.
     * @param pCedulaSocio La c�dula del socio. pCedulaSocio != null && pCedulaSocio != "".
     * @return La lista de autorizados del socio.
     */
    public ArrayList<String> darAutorizadosSocio( String pCedulaSocio )
    {
        try{
        Socio s = buscarSocio( pCedulaSocio );
        ArrayList<String> autorizados = new ArrayList<String>( );

        autorizados.add( s.darNombre( ) );
        autorizados.addAll( s.darAutorizados( ) );

        return autorizados;
    }catch (Exception e) {
            throw new IllegalArgumentException("Error al devolver la lista de autorizados: " + e.getMessage());
        }
    }

    /**
     * Agrega una nueva persona autorizada por el socio con la c�dula dada. <br>
     * <b>pre:<b/> El socio con la c�dula dada existe. <b>post: </b> Se agreg� el nuevo autorizado..
     * @param pCedulaSocio La c�dula del socio al cual se va a agregar el autorizado. pCedulaSocio != null && pCedulaSocio != "".
     * @param pNombreAutorizado El nombre de la persona a autorizar. pNombreAutorizado != null && poNmbre != "".
     *
     */
    public void agregarAutorizadoSocio( String pCedulaSocio, String pNombreAutorizado )
    {
        try{
        Socio s = buscarSocio( pCedulaSocio );
        s.agregarAutorizado( pNombreAutorizado );

    }catch (Exception e) {
            throw new IllegalArgumentException("Error al agregar autorizado: " + e.getMessage());
        }
    }

    /**
     * Elimina la persona autorizada por el socio con la c�dula dada.
     * @param pCedulaSocio La c�dula del socio que autoriz� a la persona a eliminar.pCedulaSocio!= null && pCedulaSocio! ""
     * @param pNombreAutorizado El nombre del autorizado a eliminar. pNombreAutorizado!= null && pNombreAutorizado!=""
     *
     */
    public void eliminarAutorizadoSocio( String pCedulaSocio, String pNombreAutorizado )
    {
        try{
        Socio s = buscarSocio( pCedulaSocio );
        s.eliminarAutorizado( pNombreAutorizado );
    }catch (Exception e) {
            throw new IllegalArgumentException("Error al eliminar autorizado: " + e.getMessage());
        }
    }

    /**
     * Eliminar un socio
     */
    public void eliminarSocio(String pCedula){
        int indice = 0;
        for (Socio s : socios) {
            if (s.darCedula().equals(pCedula)) {
                socios.remove(indice);
            }
            indice++;
        }
    }
    /**
     * Registra un consumo a un socio o a su autorizado. <br>
     * <b>post: </b> Se agreg� una nueva factura al vector del socio.
     * @param pCedulaSocio La c�dula del socio. pCedulaSocio != null && pCedulaSocio != "".
     * @param pNombreCliente El nombre la persona que realiz� en consumo. pNombreCliente != null && pNombreCliente != "".
     * @param pConcepto El concepto del consumo. pConcepto != null && pConcepto != "".
     * @param pValor El valor del consumo. pValor >= 0.
     *
     */
    public void registrarConsumo( String pCedulaSocio, String pNombreCliente, String pConcepto, double pValor ) {
        try{
        Socio s = buscarSocio(pCedulaSocio);
        s.registrarConsumo(pNombreCliente, pConcepto, pValor);
    }catch (Exception e) {
            throw new IllegalArgumentException("Error al registrar consumo: " + e.getMessage());
        }
    }

    /**
     * Retorna la lista de facturas de un socio. <br>
     * <b>pre:<b> Existe el socio con la c�dula dada.
     * @param pCedulaSocio La c�dula del socio. pCedulaSocio != null && pCedulaSocio != "".
     * @return La lista de facturas del socio.
     */
    public ArrayList<Factura> darFacturasSocio( String pCedulaSocio )
    {
        return buscarSocio( pCedulaSocio ).darFacturas( );
    }
    /**
     * Realiza el pago de la factura de un socio. <br>
     * <b>post: </b> Se borr� la factura del vector del socio. <br>
     * @param pCedulaSocio La c�dula del socio. pCedulaSocio != null && pCedulaSocio != "".
     * @param pNombre El nombre al que esta la factura a pagar. pFacturaIndice >= 0.
     *
     */
    public void pagarFacturaSocio( String pCedulaSocio, String pNombre )
    {
        Socio s = buscarSocio( pCedulaSocio );
        if(s != null) {
            s.pagarFactura(pNombre);
            JOptionPane.showMessageDialog(null, "Se pago la factura");
        }
    }

    /**
     * Aumenta los fondos de un socio en la cantidad dada. <br>
     * <b>post: </b> Los fondos del socio aumentaron en el valor especificado.
     * @param pCedulaSocio La c�dula del socio. pCedulaSocio != null && pCedulaSocio != "".
     * @param pValor Valor por el cual se desean aumentar los fondos. pValor >= 0.
     *   */
    public void aumentarFondosSocio( String pCedulaSocio, double pValor )
    {
        Socio s = buscarSocio( pCedulaSocio );
        s.aumentarFondos( pValor );
    }

    // -----------------------------------------------------------------
    // M�todos de Extensi�n
    // -----------------------------------------------------------------

    /**
     * Retorna el total de consumos del socio.
     * Si no hay consumos registrados, retorna 0.
     * @return Total de consumos del socio o 0 si no hay consumos registrados.
     */
    public double obtenerTotalConsumos(String pCedula) {
        Socio socio = buscarSocio(pCedula);
        if (socio != null) {
            return socio.totalConsumos();
        } else {
            return 0;
        }
    }


    /**
     * Verifica si un socio puede ser eliminado según su cédula.
     * @param pCedulaSocio Cédula del socio a verificar.
     * @return true si el socio puede ser eliminado, false en caso contrario.
     */
    public boolean sePuedeEliminarSocio(String pCedulaSocio) {
        Socio socio = buscarSocio(pCedulaSocio);
        // Caso 1: No existe un socio con la cédula recibida
        if (socio == null) {
            return false;
        }

        // Caso 2: El socio es de tipo VIP
        if (socio.darTipo() == Tipo.VIP) {
            JOptionPane.showMessageDialog(null, "No se puede eliminar un socio de tipo VIP");
            return false;
        }

        // Caso 3: El socio tiene facturas pendientes de pago
        if (!socio.darFacturas().isEmpty()) {
            JOptionPane.showMessageDialog(null, "No se puede eliminar un socio con facturas pendientes de pago");
            return false;
        }

        // Caso 4: El socio tiene más de un autorizado
        if (socio.darAutorizados().size() > 1) {
            JOptionPane.showMessageDialog(null, "No se puede eliminar un socio con más de un autorizado");
            return false;
        }

        // Si no se cumple ninguno de los casos anteriores, el socio puede ser eliminado
        return true;
    }
}