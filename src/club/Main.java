package club;

import java.util.Scanner;
import club.Socio.Tipo;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) throws Exception {

        Scanner sc = new Scanner(System.in);
        int op;
        Club club = new Club();

        do{
            System.out.println("1. Afiliar un socio al club.");
            System.out.println("2. Registrar una persona autorizada por un socio.");
            System.out.println("3. Pagar una factura.");
            System.out.println("4. Registrar un consumo en la cuenta de un socio");
            System.out.println("5. Aumentar fondos de la cuenta de un socio");
            System.out.println("6. Salir");
            System.out.println("7. Valor total de consumos");
            System.out.println("8. Verificar si se puede eliminar socio");
            System.out.print("Ingrese una opcion: ");
            op = Integer.parseInt(sc.next());
            switch (op){
                case 1: {
                    String nombre;
                    do {
                        System.out.println("Escriba el nombre del socio");
                        nombre = sc.next();
                    } while (nombre == null | nombre == "");
                    String cedula;
                    do {
                        System.out.println("Escriba la cedula");
                        cedula = sc.next();
                    } while (cedula == null | cedula == "");
                    String tipoString;
                    do {
                        System.out.print("Tipo (REGULAR o VIP): ");
                        tipoString = sc.next();
                    } while (tipoString == null | tipoString == "");

                    club.afiliarSocio(cedula, nombre, Tipo.valueOf(tipoString));
                    break;
                }
                case 2:{
                    System.out.print("Ingrese la cedula del socio: ");
                    String cedulaSocio = sc.next();
                    System.out.print("Ingrese el nombre de la persona a autorizar: ");
                    String nombreAutorizado = sc.next();
                    club.agregarAutorizadoSocio(cedulaSocio, nombreAutorizado);
                    break;
                }
                case 3: {
                    System.out.print("Ingrese la cedula del socio: ");
                    String cedulaSocioPago = sc.next();
                    System.out.print("Ingrese el índice de la factura a pagar: ");
                    int indiceFactura = sc.nextInt();
                    club.pagarFacturaSocio(cedulaSocioPago, indiceFactura);
                    break;
                }
                case 4:{
                    System.out.print("Ingrese la cédula del socio: ");
                    String cedulaSocioConsumo = sc.next();
                    System.out.print("Ingrese el nombre del cliente: ");
                    String nombreCliente = sc.next();
                    System.out.print("Ingrese el concepto del consumo: ");
                    String concepto = sc.next();
                    System.out.print("Ingrese el valor del consumo: ");
                    double valor = sc.nextDouble();
                    club.registrarConsumo(cedulaSocioConsumo, nombreCliente, concepto, valor);
                    break;
                }
                case 5:{
                    System.out.print("Ingrese la cedula del socio: ");
                    String cedulaSocioFondos = sc.next();
                    System.out.print("Ingrese el valor a aumentar: ");
                    double valorAumentar = sc.nextDouble();
                    club.aumentarFondosSocio(cedulaSocioFondos, valorAumentar);
                    break;
                }
                case 6:{
                    System.out.println("Gracias!");
                    break;
                }
                case 7:{
                    System.out.println("Ingrese la cedula de la persona que quiere consultar el total de consumos");
                    String cedulaConsumos = sc.next();
                    double totalConsumos = club.obtenerTotalConsumos(cedulaConsumos);
                    if(totalConsumos!=0){
                        System.out.println("El valor a pagar del socio "+club.buscarSocio(cedulaConsumos).darNombre()+ " es $"+totalConsumos);
                    }else{
                        System.out.println("Socio no existe");
                    }
                    break;
                }
                case 8:
                {
                    System.out.println("Ingrese la cedula del socio a eliminar: ");
                    String cedulaEliminar = sc.next();
                    boolean eliminar = club.sePuedeEliminarSocio(cedulaEliminar);
                    System.out.println("Se puede eliminar: "+eliminar);
                    break;
                }
                default:
                    System.out.println("opcion invalida");
                    break;
            }

        }while(op!=6);


    }
}