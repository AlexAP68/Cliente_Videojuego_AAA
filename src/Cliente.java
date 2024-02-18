import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class Cliente {

    private static final String BASE_URL = "http://localhost:3000";



    // Metodo para hacer una solicitud GET para listar todos los videojuegos
    public static void listarVideojocs() throws IOException {
        String endpoint = BASE_URL + "/videojocs";
        HttpURLConnection connection = (HttpURLConnection) new URL(endpoint).openConnection();
        connection.setRequestMethod("GET");
        int responseCode = connection.getResponseCode();

        if (responseCode == HttpURLConnection.HTTP_OK) {
            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String line;
            StringBuilder response = new StringBuilder();
            while ((line = reader.readLine()) != null) {
                response.append(line);
            }
            reader.close();
            System.out.println("Lista de videojuegos:");
            System.out.println(response.toString());
        } else {
            System.out.println("Error al obtener la lista de videojuegos. Código de respuesta: " + responseCode);
        }
    }

    // Metodo para obtener un videojuego por su ID
    public static void obtenerVideojocPorID(int id) throws IOException {
        String endpoint = BASE_URL + "/videojocs/" + id;
        HttpURLConnection connection = (HttpURLConnection) new URL(endpoint).openConnection();
        connection.setRequestMethod("GET");
        int responseCode = connection.getResponseCode();

        if (responseCode == HttpURLConnection.HTTP_OK) {
            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String line;
            StringBuilder response = new StringBuilder();
            while ((line = reader.readLine()) != null) {
                response.append(line);
            }
            reader.close();
            System.out.println("Videojuego con ID " + id + ":");
            System.out.println(response.toString());
        } else {
            System.out.println("Error al obtener el videojuego con ID " + id + ". Código de respuesta: " + responseCode);
        }
    }

    // Metodo para listar los videojuegos de una empresa
    public static void listarVideojocsPorEmpresa(String empresa) throws IOException {
        String endpoint = BASE_URL + "/videojocs?empresa=" + empresa;
        HttpURLConnection connection = (HttpURLConnection) new URL(endpoint).openConnection();
        connection.setRequestMethod("GET");
        int responseCode = connection.getResponseCode();

        if (responseCode == HttpURLConnection.HTTP_OK) {
            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String line;
            StringBuilder response = new StringBuilder();
            while ((line = reader.readLine()) != null) {
                response.append(line);
            }
            reader.close();
            System.out.println("Videojuegos de la empresa " + empresa + ":");
            System.out.println(response.toString());
        } else {
            System.out.println("Error al obtener los videojuegos de la empresa " + empresa + ". Código de respuesta: " + responseCode);
        }
    }

    // Metodo para crear un nuevo videojuego
    public static void crearVideojoc() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));


        System.out.println("Introduce el título del videojuego:");
        String titulo = reader.readLine();

        System.out.println("Introduce el año del videojuego:");
        String any = reader.readLine();

        System.out.println("Introduce la modalidad del videojuego:");
        String modalitat = reader.readLine();

        System.out.println("Introduce la empresa del videojuego:");
        String empresa = reader.readLine();

        String requestBody = "{ \"TITOL\": \"" + titulo + "\", \"ANY\": \"" + any + "\", \"MODALITAT\": \"" + modalitat + "\", \"EMPRESA\": \"" + empresa + "\"}";
        String endpoint = BASE_URL + "/videojocs";
        HttpURLConnection connection = (HttpURLConnection) new URL(endpoint).openConnection();
        connection.setRequestMethod("POST");
        connection.setRequestProperty("Content-Type", "application/json");
        connection.setDoOutput(true);

        OutputStream os = connection.getOutputStream();
        os.write(requestBody.getBytes());
        os.flush();
        os.close();

        int responseCode = connection.getResponseCode();
        if (responseCode == HttpURLConnection.HTTP_CREATED) {
            System.out.println("Nuevo videojuego creado con éxito.");
        } else {
            System.out.println("Error al crear el nuevo videojuego. Código de respuesta: " + responseCode);
        }
    }

    // M3todo para actualizar un videojuego existente
    public static void actualizarVideojoc(int id) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        // Solicitar al usuario los campos que desea actualizar
        System.out.println("¿Qué campos deseas actualizar? (Presiona Enter para mantener el valor actual)");

        System.out.print("TITOL (Dejar en blanco para mantener el valor actual): ");
        String nuevoTITOL = reader.readLine();

        System.out.print("ANY (Dejar en blanco para mantener el valor actual): ");
        String nuevoANY = reader.readLine();

        System.out.print("MODALITAT (Dejar en blanco para mantener el valor actual): ");
        String nuevaMODALITAT = reader.readLine();

        System.out.print("EMPRESA (Dejar en blanco para mantener el valor actual): ");
        String nuevaEMPRESA = reader.readLine();

        // Construir el cuerpo de la solicitud PUT con los campos actualizados
        StringBuilder requestBodyBuilder = new StringBuilder("{");
        if (!nuevoTITOL.isEmpty()) {
            requestBodyBuilder.append("\"TITOL\": \"").append(nuevoTITOL).append("\", ");
        }
        if (!nuevoANY.isEmpty()) {
            requestBodyBuilder.append("\"ANY\": \"").append(nuevoANY).append("\", ");
        }
        if (!nuevaMODALITAT.isEmpty()) {
            requestBodyBuilder.append("\"MODALITAT\": \"").append(nuevaMODALITAT).append("\", ");
        }
        if (!nuevaEMPRESA.isEmpty()) {
            requestBodyBuilder.append("\"EMPRESA\": \"").append(nuevaEMPRESA).append("\", ");
        }
        // Eliminar la coma adicional al final si es necesario
        if (requestBodyBuilder.charAt(requestBodyBuilder.length() - 2) == ',') {
            requestBodyBuilder.deleteCharAt(requestBodyBuilder.length() - 2);
        }
        requestBodyBuilder.append("}");

        String requestBody = requestBodyBuilder.toString();

        // Construir la URL del endpoint
        String endpoint = BASE_URL + "/videojocs/" + id;

        // Establecer la conexion y enviar la solicitud PUT
        HttpURLConnection connection = (HttpURLConnection) new URL(endpoint).openConnection();
        connection.setRequestMethod("PUT");
        connection.setRequestProperty("Content-Type", "application/json");
        connection.setDoOutput(true);

        OutputStream os = connection.getOutputStream();
        os.write(requestBody.getBytes());
        os.flush();
        os.close();

        // Obtener el codigo de respuesta y mostrar el resultado al usuario
        int responseCode = connection.getResponseCode();
        if (responseCode == HttpURLConnection.HTTP_OK) {
            System.out.println("Videojuego con ID " + id + " actualizado con éxito.");
        } else {
            System.out.println("Error al actualizar el videojuego con ID " + id + ". Código de respuesta: " + responseCode);
        }
    }

    // Metodo para eliminar un videojuego por su ID
    public static void eliminarVideojoc(int id) throws IOException {
        String endpoint = BASE_URL + "/videojocs/" + id;
        HttpURLConnection connection = (HttpURLConnection) new URL(endpoint).openConnection();
        connection.setRequestMethod("DELETE");

        int responseCode = connection.getResponseCode();
        if (responseCode == HttpURLConnection.HTTP_OK) {
            System.out.println("Videojuego con ID " + id + " eliminado con éxito.");
        } else {
            System.out.println("Error al eliminar el videojuego con ID " + id + ". Código de respuesta: " + responseCode);
        }
    }
}
