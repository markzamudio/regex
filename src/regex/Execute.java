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
        JFPrincipal jfPrincipal = new JFPrincipal();
        CtrlRegExp handler = new CtrlRegExp(jfPrincipal);
        handler.run();
    }
}
