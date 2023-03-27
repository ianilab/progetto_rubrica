package UI;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import Logic.Persona;
import Logic.Rubrica;
import Logic.Utente;

import static javax.swing.JOptionPane.*;

public class MainWindow extends JFrame {
    private static JTable table;
    private JButton nuovoBtn = new JButton("Nuovo"), modificaBtn = new JButton("Modifica"),
            eliminaBtn = new JButton("Elimina"), newUserBtn = new JButton("Nuovo utente");
    private static JPanel tablePanel = new JPanel(new BorderLayout());
    private static Rubrica rubrica;

    public MainWindow(Rubrica rubrica){
        super("Rubrica");
        setSize(800, 300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.rubrica = rubrica;

        createTable();
        buttonSetup();

        setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));

        JToolBar toolBar = new JToolBar();
        toolBar.add(nuovoBtn);
        toolBar.addSeparator();
        toolBar.add(modificaBtn);
        toolBar.addSeparator();
        toolBar.add(eliminaBtn);
        toolBar.addSeparator();
        toolBar.addSeparator();
        toolBar.add(newUserBtn);


        add(toolBar);
        add(tablePanel);

    }

    private void createTable(){
        String[] titles = {"Nome","Cognome","Indirizzo","Telefono","Età"};
        Object[][] data = rubrica.toArray();
        DefaultTableModel tableModel = new DefaultTableModel(data, titles);
        table = new JTable(tableModel);

        JScrollPane scrollPane = new JScrollPane(table);
        tablePanel.add(scrollPane, BorderLayout.CENTER);
    }

    public static void updateTable(){
        String[] titles = {"Nome","Cognome","Indirizzo","Telefono","Età"};
        Object[][] data = rubrica.toArray();
        DefaultTableModel tableModel = new DefaultTableModel(data, titles);
        table.setModel(tableModel);
    }


//    metodo che raccoglie i setup di tutti i buttoni di questo Frame
    public void buttonSetup(){
        nuovoBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                EditorPersona editorPersona;
                editorPersona = new EditorPersona(rubrica, -1);
                editorPersona.setVisible(true);
            }
        });

        modificaBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                if(table.getSelectedRow() == -1){
                    showMessageDialog(null,"Seleziona una persona per modificarla");
                    return;
                }
                EditorPersona editorPersona = new EditorPersona(rubrica,table.getSelectedRow());
                editorPersona.setVisible(true);
            }
        });

        eliminaBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                if(table.getSelectedRow() == -1){
                    showMessageDialog(null,"Seleziona una persona per eliminarla");
                    return;
                }
                Persona p = rubrica.getPersona(table.getSelectedRow());
                int result = showConfirmDialog(null,"Eliminare la persona " + p.getNome() + " " + p.getCognome(),
                        "Conferma eliminazione", YES_NO_OPTION);
                if (result == YES_OPTION){
                    rubrica.removePersona(table.getSelectedRow());
                    updateTable();
                }
            }
        });

        newUserBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                Signup signup = new Signup();
            }
        });
    }
}
