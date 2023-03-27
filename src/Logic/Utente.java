package Logic;

import UI.Login;
import UI.MainWindow;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;
import java.io.*;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.Scanner;


public class Utente {
    private String username, password;
    private static String encryptionKey = "progetto_rubrica";

    public Utente(String username, String password){
        this.username = username;
        this.password = password;
    }

//    salva l'utente corrente su utenti.txt. La password viene criptata e poi salvata
    public void save(){
        try {

            SecretKeySpec key = new SecretKeySpec(encryptionKey.getBytes(), "AES");

            Cipher cipher = Cipher.getInstance("AES");

            cipher.init(Cipher.ENCRYPT_MODE, key);

            byte[] encryptedBytes = cipher.doFinal(password.getBytes());

            String encryptedText = Base64.getEncoder().encodeToString(encryptedBytes);


            FileWriter writer = new FileWriter("utenti.txt", true);
            writer.write(username + " " + encryptedText);
            writer.close();


        } catch (NoSuchPaddingException | IllegalBlockSizeException | NoSuchAlgorithmException | BadPaddingException |
                 InvalidKeyException | IOException e){
            throw new RuntimeException(e);
        }


    }

//    controlla che user e password appaiano i utenti.txt.
    public static boolean login(String user, String pass){
        SecretKeySpec key = new SecretKeySpec(encryptionKey.getBytes(), "AES");
        File info = new File("utenti.txt");

//      Se il file non dovesse esistere (ad es. al primo avvio), viene creato con le credenziali di default:
//        username: user
//        password: pass
        if(! info.exists()){
            try {
                info.createNewFile();
                PrintStream ps = new PrintStream(info);
                ps.println("user 2zuvxWY5C1I/R6UudPrRyQ==");
                ps.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        try {
            Cipher cipher = Cipher.getInstance("AES");
            cipher.init(Cipher.DECRYPT_MODE, key);
            Scanner scanner = new Scanner(info);

            while (scanner.hasNextLine()) {
                String[] line = scanner.nextLine().split(" ");
                if (line[0].equals(user)) {
                    String decryptedText = new String(cipher.doFinal(Base64.getDecoder().decode(line[1])));
//                    Dati validi, viene avviato il programma effettivo
                    if(decryptedText.equals(pass)){
                        Rubrica rubrica = loadRubrica();
                        MainWindow mainWindow = new MainWindow(rubrica);
                        mainWindow.setVisible(true);
                        return true;
                    }
                }
            }
            scanner.close();

        } catch (NoSuchPaddingException | IllegalBlockSizeException | NoSuchAlgorithmException | BadPaddingException |
                 InvalidKeyException | FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        Login.loginError();
        return false;
    }

//    carica la rubrica da informazioni.txt
    private static Rubrica loadRubrica(){
        Rubrica rubrica = new Rubrica();
        File info = new File("informazioni.txt");
        if (! info.exists()){
            try{
                info.createNewFile();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        try {
            Scanner scanner = new Scanner(info);
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                rubrica.addPersona(new Persona(line.split(";")));
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        return rubrica;
    }


}
