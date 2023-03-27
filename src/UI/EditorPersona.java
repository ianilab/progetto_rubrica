package UI;

import Logic.Persona;
import Logic.Rubrica;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.PrintStream;

public class EditorPersona extends JFrame {
    private final JTextField[] fields = {new JTextField(),new JTextField(),new JTextField(),new JTextField(),new JTextField()};

    private Rubrica rubrica;

    public EditorPersona(Rubrica r, int idx) throws HeadlessException {
        super("Editor Persona");

        this.rubrica = r;

        setSize(400,300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JButton salvaBtn = new JButton("Salva");
        salvaBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                salva(idx);
            }
        });

        JButton annullaBtn = new JButton("Annulla");
        annullaBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                dispose();
            }
        });

        this.setLayout(new GridLayout(6,2));

        JLabel[] fieldNames = {new JLabel("Nome"), new JLabel("Cognome"),
                new JLabel("Indirizzo"), new JLabel("Telefono"), new JLabel("Età"),};

        for(int i = 0; i < fieldNames.length; i++){
            getContentPane().add(fieldNames[i]);
            getContentPane().add(fields[i]);
        }
        JToolBar toolBar = new JToolBar();
        toolBar.add(salvaBtn);
        toolBar.add(annullaBtn);
        getContentPane().add(toolBar);

        if (idx >= 0){
            setupFields(idx);
        }
    }

//    metodo per modificare o aggiungere una persona. I dati vengono salvati su informazioni.txt
    private void salva(int idx){
        String[] values = new String[fields.length];
        for (int i = 0; i < fields.length; i++) {
            values[i] = fields[i].getText();
        }
//        idx indica se si sta modificando o creando una persona
        if (idx >= 0){
            rubrica.editPersona(idx,new Persona(values));
        }else {
            rubrica.addPersona(new Persona(values));
        }

//        salvataggio su file
        try {
            PrintStream ps = new PrintStream("informazioni.txt");
            for (Object[] persona:rubrica.toArray()) {
                ps.printf("%s;%s;%s;%s;%s%n",persona[0],persona[1],persona[2],persona[3],persona[4]);
            }
            ps.close();
        }catch (FileNotFoundException e){
            e.printStackTrace();
        }

        MainWindow.updateTable();
        this.dispose();
    }

//    sui text field vengono precaricati i dati salvati dalla rubrica
    private void setupFields(int idx){
        Persona p = rubrica.getPersona(idx);
        fields[0].setText(p.getNome());
        fields[1].setText(p.getCognome());
        fields[2].setText(p.getIndirizzo());
        fields[3].setText(p.getTelefono());
        fields[4].setText(Integer.toString(p.getEta()));
    }

}
