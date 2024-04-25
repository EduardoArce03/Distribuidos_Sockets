package distribuidos_sockets_arce_bravo;
import Vista.ServidorVista;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Servidor extends Thread {
    
    private ServidorVista servidorVista;

    public Servidor(ServidorVista servidorVista) {
        this.servidorVista = servidorVista;
    }
    
    
    
    public void run() {
        
        final int PUERTO = 5000;
        try {
            ServerSocket serverSocket = new ServerSocket(PUERTO);
            System.out.println("Servidor esperando conexiones en el puerto " + PUERTO);
            
            while (true) {
                Socket clienteSocket = serverSocket.accept();
                System.out.println("Cliente conectado desde: " + clienteSocket.getInetAddress().getHostAddress());
                
                BufferedReader entrada = new BufferedReader(new InputStreamReader(clienteSocket.getInputStream()));
                PrintWriter salida = new PrintWriter(clienteSocket.getOutputStream(), true);
                
                // Leer mensaje del cliente
                String mensajeCliente = entrada.readLine();
                System.out.println("Mensaje recibido del cliente: " + mensajeCliente);
                servidorVista.llenarArea("\n" + mensajeCliente);
                
                // Enviar respuesta al cliente
                salida.println("Mensaje recibido por el servidor: " + mensajeCliente);
                
                // Cerrar recursos
                entrada.close();
                salida.close();
                clienteSocket.close();
            }
        } catch (Exception e) {
            System.out.println("Error en el servidor: " + e.getMessage());
        }
    }
}
