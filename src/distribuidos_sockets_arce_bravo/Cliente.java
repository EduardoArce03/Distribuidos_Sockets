/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package distribuidos_sockets_arce_bravo;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import javax.swing.JTextArea;

/**
 *
 * @author ESTUDIANTE
 */
public class Cliente extends Thread {

    private static final String SERVIDOR = "localhost";
    private static final int PUERTO = 5000;
    private static boolean conectado = true;
    private JTextArea panel;

    public Cliente(JTextArea jta) {
        this.panel = jta;
    }
    
    

    public void run() {
        try {
            Socket cliente = new Socket(SERVIDOR, PUERTO);

            BufferedReader entradaServidor = new BufferedReader(new InputStreamReader(cliente.getInputStream()));
            PrintWriter salidaServidor = new PrintWriter(cliente.getOutputStream(), true);

            System.out.println("Conectado al servidor");

            // Leer mensajes del servidor en un hilo separado
            new Thread(() -> {
                try {
                    String mensajeDelServidor;
                    while ((mensajeDelServidor = entradaServidor.readLine()) != null) {
                        System.out.println("Mensaje del servidor: " + mensajeDelServidor);
                    }
                } catch (Exception e) {
                    System.out.println("Desconectado del servidor");
                    // Intentar reconexión automática
                }
            }).start();

            // Leer mensajes desde la consola y enviarlos al servidor
            BufferedReader entradaConsola = new BufferedReader(new InputStreamReader(System.in));
            String mensajeAlServidor;
            while ((mensajeAlServidor = entradaConsola.readLine()) != null) {
                salidaServidor.println(mensajeAlServidor);
                panel.append(mensajeAlServidor);
                System.out.println(mensajeAlServidor);
            }

            // Cerrar recursos
            salidaServidor.close();
            entradaServidor.close();
            cliente.close();

        } catch (Exception e) {
            System.out.println("Error en el cliente: " + e.getMessage());
            // Intentar reconexión automática
;
        }
    }

    


}
