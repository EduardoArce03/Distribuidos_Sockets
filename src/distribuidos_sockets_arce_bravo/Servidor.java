/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package distribuidos_sockets_arce_bravo;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

/**
 *
 * @author ESTUDIANTE
 */
public class Servidor {
    public static void main (String[] args) {
        final int puerto = 5000;
        try {
            ServerSocket serverSocket = new ServerSocket(puerto);
            System.out.println("Servidor escuchando al puerto xdxd ");
            while (true) {                
                Socket clienteSocket = serverSocket.accept();
                System.out.println("Nuevo cliente conectado ");
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(clienteSocket.getInputStream()));
                //leer mensaje del cliente
                String mensaje = bufferedReader.readLine();
                System.out.println("Mensaje del cliente " + mensaje);
                bufferedReader.close();
                clienteSocket.close();
                serverSocket.close();
            }
        } catch (Exception e) {
            System.out.println("Pavlo aki ta fallando algo mmm " + e.getMessage());
        }
    }
    
    
}
