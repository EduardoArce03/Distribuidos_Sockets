package distribuidos_sockets_arce_bravo;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import java.util.Base64;

public class Encriptacion {
    private String mensajeOriginal;
    private SecretKey claveSecreta;

    // Constructor que recibe el mensaje a ser encriptado y la clave secreta
    public Encriptacion(String mensajeOriginal, SecretKey claveSecreta) {
        this.mensajeOriginal = mensajeOriginal;
        this.claveSecreta = claveSecreta;
    }

    // Método para encriptar el mensaje
    public String encriptarMensaje() {
        try {
            Cipher cipher = Cipher.getInstance("AES");
            cipher.init(Cipher.ENCRYPT_MODE, claveSecreta);
            byte[] mensajeEncriptado = cipher.doFinal(mensajeOriginal.getBytes());
            return Base64.getEncoder().encodeToString(mensajeEncriptado);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    // Método para desencriptar el mensaje
    public String desencriptarMensaje(String mensajeEncriptado) {
        try {
            Cipher cipher = Cipher.getInstance("AES");
            cipher.init(Cipher.DECRYPT_MODE, claveSecreta);
            byte[] bytesEncriptados = Base64.getDecoder().decode(mensajeEncriptado);
            byte[] mensajeDesencriptado = cipher.doFinal(bytesEncriptados);
            return new String(mensajeDesencriptado);
        } catch (Exception e) {
            System.out.println("Error de desencriptado " + e.getMessage());
            return null;
        }
    }
}

