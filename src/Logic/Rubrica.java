package Logic;

import java.util.Vector;

public class Rubrica {

    private Vector<Persona> persone = new Vector<>();
    public Rubrica(){}

    public void addPersona(Persona p){
        persone.add(p);
    }

    public void removePersona(int ind){
        persone.remove(ind);
    }

    public void editPersona (int ind, Persona p){
        persone.remove(ind);
        persone.insertElementAt(p,ind);
    }

    public Persona getPersona(int ind){
        return persone.elementAt(ind);
    }

    public Object[][] toArray(){
        Object[][] ret = new Object[persone.size()][5];
        for (int i = 0; i < persone.size(); i++) {
            ret[i][0] = persone.elementAt(i).getNome();
            ret[i][1] = persone.elementAt(i).getCognome();
            ret[i][2] = persone.elementAt(i).getIndirizzo();
            ret[i][3] = persone.elementAt(i).getTelefono();
            ret[i][4] = persone.elementAt(i).getEta();
        }
        return ret;
    }


}
