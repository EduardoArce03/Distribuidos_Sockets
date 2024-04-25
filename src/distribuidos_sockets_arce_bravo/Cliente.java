package distribuidos_sockets_arce_bravo;
import Vista.ClienteVista;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Cliente extends Thread{
    
    private ClienteVista clienteVista;
    
    public Cliente(ClienteVista clienteVista) {
        this.clienteVista = clienteVista;
    }
    
    public void run() {
        final String SERVIDOR = "localhost";
        final int PUERTO = 5000;
        
        
        
        try {
            Socket clienteSocket = new Socket(SERVIDOR, PUERTO);
            
            DataOutputStream out = new DataOutputStream(clienteSocket.getOutputStream());
            System.out.println("");
            out.writeUTF(clienteVista.txtMensaje.getText());
            System.out.println(clienteVista.txtMensaje.getText());
            
            out.close();
            clienteSocket.close();
        } catch (Exception e) {
            System.out.println("Error en el cliente: " + e.getMessage());
        }
    }

    public Cliente() {
    }
}
