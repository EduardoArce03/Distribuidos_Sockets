package distribuidos_sockets_arce_bravo;
import Vista.ServidorVista;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
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
            String nick, ip, mensaje;
            PaqueteEnvios paqueteRecibido;
            
            System.out.println("Servidor esperando conexiones en el puerto " + PUERTO);
            
            while (true) {
                Socket clienteSocket = serverSocket.accept();
                System.out.println("Cliente conectado desde: " + clienteSocket.getInetAddress().getHostAddress());
                
                ObjectInputStream paqueteDatos = new ObjectInputStream(clienteSocket.getInputStream());
                paqueteRecibido = (PaqueteEnvios) paqueteDatos.readObject();
                
                nick = paqueteRecibido.getNick();
                ip = paqueteRecibido.getIp();
                mensaje = paqueteRecibido.getMensaje();
                
                servidorVista.llenarArea("\n" + nick + ": " + mensaje + " para " + ip);
                
                /*BufferedReader entrada = new BufferedReader(new InputStreamReader(clienteSocket.getInputStream()));
                PrintWriter salida = new PrintWriter(clienteSocket.getOutputStream(), true);
                
                // Leer mensaje del cliente
                String mensajeCliente = entrada.readLine();
                System.out.println("Mensaje recibido del cliente: " + mensajeCliente);
                servidorVista.llenarArea("\n" + mensajeCliente);
                
                // Enviar respuesta al cliente
                salida.println("Mensaje recibido por el servidor: " + mensajeCliente);
                
                // Cerrar recursos
                entrada.close();
                salida.close();*/
                
                //Para crear un puente entre destino y servidor
                Socket destinatario = new Socket(ip, 9090);
                ObjectOutputStream paqueteReenvio = new ObjectOutputStream(destinatario.getOutputStream());       
                paqueteReenvio.writeObject(paqueteRecibido);
                destinatario.close();
                
                paqueteReenvio.close();
                
                clienteSocket.close();
            }
        } catch (Exception e) {
            System.out.println("Error en el servidor: " + e.getMessage());
        }
    }
}
