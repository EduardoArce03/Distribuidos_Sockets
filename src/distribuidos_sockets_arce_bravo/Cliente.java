/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package distribuidos_sockets_arce_bravo;

import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 *
 * @author ESTUDIANTE
 */
public class Cliente {
    public static void main(String[] args){
        final String servidor = "localhost";
        final int puerto = 5000;
        try {
            Socket cliente = new Socket(servidor, puerto);
            
            PrintWriter salidaServidor = new PrintWriter(cliente.getOutputStream(),true);
            
            salidaServidor.print("Hola servidor mmm");
            
            salidaServidor.close();
            cliente.close();
            
        } catch (Exception e) {
            System.out.println(e.getMessage() + "error");
        }
    }
}
