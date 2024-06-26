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
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;

public class Cliente extends Thread{
    
    private VistaCliente clienteVista;
    private Encriptacion encriptacion;
    KeyGenerator keyGenerator;
    public Cliente(VistaCliente clienteVista) {
        this.clienteVista = clienteVista;
    }
    
    public void hiloInterfaz(){
        
    }
    
    public void run() {
        final String SERVIDOR = "192.168.0.107";
        final int PUERTO = 5000;
        
        
        try {
            Socket clienteSocket = new Socket(SERVIDOR, PUERTO);
            
            //DataOutputStream out = new DataOutputStream(clienteSocket.getOutputStream());
            //System.out.println("");
            //out.writeUTF(clienteVista.txtMensaje.getText());
            //System.out.println(clienteVista.txtMensaje.getText());
            KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
            keyGenerator.init(256);
            SecretKey claveSecreta = keyGenerator.generateKey();
            String mensaje = clienteVista.txtMensaje.getText();
            encriptacion = new Encriptacion(mensaje, claveSecreta);
            String msgEncriptado = encriptacion.encriptarMensaje();
            PaqueteEnvios paqueteEnvios = new PaqueteEnvios();
            paqueteEnvios.setClave(claveSecreta);
            paqueteEnvios.setIp(clienteVista.txtIp.getText());
            paqueteEnvios.setNick(clienteVista.txtNick.getText());
            paqueteEnvios.setMensaje(msgEncriptado);
            
            
            ObjectOutputStream paqueteDatos = new ObjectOutputStream(clienteSocket.getOutputStream());
            paqueteDatos.writeObject(paqueteEnvios);
            
            //out.close();
            clienteSocket.close();
        } catch (Exception e) {
            System.out.println("Error en el cliente: " + e.getMessage());
        }
    }
}
