import java.io.IOException;
import java.util.Scanner;

public class MenuCliente {

    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);
        String opcion;

        do {
            mostrarMenu();
            opcion = scanner.next();
            scanner.nextLine(); // Limpiar el buffer del scanner

            switch (opcion) {
                //Eleccion de listar todos los videojuegos
                case "1":
                    System.out.println("Has seleccionado listar todos los videojuegos.");
                    Cliente.listarVideojocs();
                    break;
                    //Eleccion de listar un videojuego por su ID
                case "2":
                    System.out.println("Introduce el ID del videojuego:");
                    int id = scanner.nextInt();
                    scanner.nextLine(); // Limpiar el buffer del scanner
                    System.out.println("Has seleccionado listar el videojuego con ID " + id);
                    Cliente.obtenerVideojocPorID(id);
                    break;
                    //Eleccion de listar videojuegos de una empresa
                case "3":
                    System.out.println("Introduce el nombre de la empresa:");
                    String empresa = scanner.nextLine();
                    System.out.println("Has seleccionado listar los videojuegos de la empresa " + empresa);
                    Cliente.listarVideojocsPorEmpresa(empresa);
                    break;
                    //Eleccion de crear un nuevo videojuego
                case "4":
                    System.out.println("Has seleccionado crear un nuevo videojuego.");
                    Cliente.crearVideojoc();
                    break;
                    //Eleccion de actualizar un videojuego por su ID
                case "5":
                    System.out.println("Introduce el ID del videojuego a actualizar:");
                    int idActualizar = scanner.nextInt();
                    scanner.nextLine(); // Limpiar el buffer del scanner
                    System.out.println("Has seleccionado actualizar el videojuego con ID " + idActualizar);
                    Cliente.actualizarVideojoc(idActualizar);
                    break;
                    //Eleccion de eliminar un videojuego por su ID
                case "6":
                    System.out.println("Introduce el ID del videojuego a eliminar:");
                    int idEliminar = scanner.nextInt();
                    scanner.nextLine(); // Limpiar el buffer del scanner
                    System.out.println("Has seleccionado eliminar el videojuego con ID " + idEliminar);
                    Cliente.eliminarVideojoc(idEliminar);
                    break;
                    //Eleccion de salir del programa
                case "0":
                    System.out.println("Saliendo del programa. ¡Hasta luego!");
                    break;
                default:
                    System.out.println("Opción no válida. Por favor, selecciona una opción del menú.");
                    break;
            }
        } while (!opcion.equals("0"));

        scanner.close();
    }

    // Metodo para mostrar el menu
    public static void mostrarMenu() {
        System.out.println("\n*** Menu de Cliente ***");
        System.out.println("1. Listar todos los videojuegos");
        System.out.println("2. Listar un videojuego por su ID");
        System.out.println("3. Listar videojuegos de una empresa");
        System.out.println("4. Crear un nuevo videojuego");
        System.out.println("5. Actualizar un videojuego por su ID");
        System.out.println("6. Eliminar un videojuego por su ID");
        System.out.println("0. Salir del programa");
        System.out.print("Selecciona una opción: ");
    }
}
