package regex;

import regex.controller.CtrlRegExp;
import regex.model.ReadData;
import regex.view.JFPrincipal;

/**
 *
 * @author Mark Zamudio
 */
public class Execute {
    public static void main(String[] args) {
        ReadData readData = new ReadData();
        JFPrincipal jfPrincipal = new JFPrincipal();
        CtrlRegExp handler = new CtrlRegExp(readData, jfPrincipal);
        handler.run();
        jfPrincipal.setVisible(true);
    }
}
