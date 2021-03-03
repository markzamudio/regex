package regex.model;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author Mario Zamudio
 */
public class ReadData {
    private ArrayList<String> storage;
    private FileReader fr;
    private BufferedReader bf;
    
    public ReadData() {
        storage = new ArrayList<String>();
        try {
            fr = new FileReader("Entries.txt");
            bf = new BufferedReader(fr);
        } catch (FileNotFoundException fnfe) {
            JOptionPane.showMessageDialog(null, "El Archivo no se encontro","REGEX",JOptionPane.ERROR_MESSAGE);
        }
    }

    public void setStorage(ArrayList<String> storage) {
        this.storage = storage;
    }

    public ArrayList<String> getStorage() {
        return storage;
    }
    
    public void readFile() {
        String input = null;
        storage.clear();
        
        try {
            while ((input = bf.readLine()) != null) {            
                storage.add(input);
            }
        } catch (IOException io) {
            JOptionPane.showMessageDialog(null, "No se pudo leer el archivo","REGEX",JOptionPane.ERROR_MESSAGE);
        }
    }
}
