package distribuidos_sockets_arce_bravo;
import ClienteVista.VistaCliente;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Cliente extends Thread{
    
    private VistaCliente clienteVista;
    
    public Cliente(VistaCliente clienteVista) {
        this.clienteVista = clienteVista;
    }
    
    public void hiloInterfaz(){
        
    }
    
    public void run() {
        final String SERVIDOR = "18.223.116.83";
        final int PUERTO = 5000;
        
        
        try {
            Socket clienteSocket = new Socket(SERVIDOR, PUERTO);
            
            //DataOutputStream out = new DataOutputStream(clienteSocket.getOutputStream());
            //System.out.println("");
            //out.writeUTF(clienteVista.txtMensaje.getText());
            //System.out.println(clienteVista.txtMensaje.getText());
            
            PaqueteEnvios paqueteEnvios = new PaqueteEnvios();
            paqueteEnvios.setIp(clienteVista.txtIp.getText());
            paqueteEnvios.setNick(clienteVista.txtNick.getText());
            paqueteEnvios.setMensaje(clienteVista.txtMensaje.getText());
            
            
            ObjectOutputStream paqueteDatos = new ObjectOutputStream(clienteSocket.getOutputStream());
            paqueteDatos.writeObject(paqueteEnvios);
            
            //out.close();
            clienteSocket.close();
        } catch (Exception e) {
            System.out.println("Error en el cliente: " + e.getMessage());
        }
    }

}
