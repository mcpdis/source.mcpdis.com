/*
 * CorrectedPhenytoin.java
 *
 * Created on March 8, 2006, 3:22 PM
 */

package Formulas;

import javax.microedition.midlet.*;
import javax.microedition.lcdui.*;

import net.dclausen.microfloat.*;
/**
 *
 * @author user
 */
public class CorrectedPhenytoin extends MIDlet 
        implements javax.microedition.lcdui.CommandListener,
                   javax.microedition.lcdui.ItemStateListener {
    
    /** Creates a new instance of CorrectedPhenytoin */
    public CorrectedPhenytoin() {
    }

    private Persistence.FormulaValue get_formulaValue() {
        return new Persistence.FormulaValue("Corrected Phenytoin [ug/mL]", get_result().getText());
    }

    
// --- This section is auto-generated by NetBeans IDE. Do not modify or you may lose your changes.//<editor-fold id="MVDMethods" defaultstate="collapsed" desc="This section is auto-generated by NetBeans IDE.">//GEN-BEGIN:MVDMethods
    /**
     * This method initializes UI of the application.
     */
    private void initialize() {
// For adding user code into this block, select "Design" item in the inspector and invoke property editor on Action property in Properties window.
        javax.microedition.lcdui.Display.getDisplay(this).setCurrent(get_correctedPhenytoinForm());
    }
    
    /**
     * Called by the system to indicate that a command has been invoked on a particular displayable.
     * @param command the Command that ws invoked
     * @param displayable the Displayable on which the command was invoked
     **/
    public void commandAction(javax.microedition.lcdui.Command command, javax.microedition.lcdui.Displayable displayable) {
        if (displayable == correctedPhenytoinForm) {
            if (command == backCommand1) {
// For adding user code into this block, select "Design | Screens | correctedPhenytoinForm [Form] | Assigned Commands | backCommand1" item in the inspector and invoke property editor on Action property in Properties window.
                javax.microedition.lcdui.Display.getDisplay(this).setCurrent(null);
                destroyApp(true);
                notifyDestroyed();
            } else if (command == saveCommand) {
// For adding user code into this block, select "Design | Screens | correctedPhenytoinForm [Form] | Assigned Commands | saveCommand" item in the inspector and invoke property editor on Action property in Properties window.
                Persistence.PatientSuiteDispatcher.invoke(this, get_correctedPhenytoinForm(), get_formulaValue());
            }
        }
    }
    
    /**
     * This method returns instance for correctedPhenytoinForm component and should be called instead of accessing correctedPhenytoinForm field directly.
     * @return Instance for correctedPhenytoinForm component
     **/
    private javax.microedition.lcdui.Form get_correctedPhenytoinForm() {
        if (correctedPhenytoinForm == null) {
            correctedPhenytoinForm = new javax.microedition.lcdui.Form("Corrected phenytoin", new javax.microedition.lcdui.Item[] {
                get_totalPhenytoin(),
                        get_albumin(),
                        get_result()
            });
            correctedPhenytoinForm.addCommand(get_backCommand1());
            correctedPhenytoinForm.addCommand(get_saveCommand());
            correctedPhenytoinForm.setCommandListener(this);
        }
        return correctedPhenytoinForm;
    }
    
    /**
     * This method returns instance for totalPhenytoin component and should be called instead of accessing totalPhenytoin field directly.
     * @return Instance for totalPhenytoin component
     **/
    private javax.microedition.lcdui.TextField get_totalPhenytoin() {
        if (totalPhenytoin == null) {
            totalPhenytoin = new javax.microedition.lcdui.TextField("Total phenyoin [ug/ml]", "", 20, 0x0);
        }
        return totalPhenytoin;
    }
    
    /**
     * This method returns instance for albumin component and should be called instead of accessing albumin field directly.
     * @return Instance for albumin component
     **/
    private javax.microedition.lcdui.TextField get_albumin() {
        if (albumin == null) {
            albumin = new javax.microedition.lcdui.TextField("Albumin [g/dL]", "", 20, 0x0);
        }
        return albumin;
    }
    
    /**
     * This method returns instance for result component and should be called instead of accessing result field directly.
     * @return Instance for result component
     **/
    private javax.microedition.lcdui.StringItem get_result() {
        if (result == null) {
            result = new javax.microedition.lcdui.StringItem("Corr.level [ug/mL] = ", "");
        }
        return result;
    }
    
    /**
     * This method returns instance for backCommand1 component and should be called instead of accessing backCommand1 field directly.
     * @return Instance for backCommand1 component
     **/
    private javax.microedition.lcdui.Command get_backCommand1() {
        if (backCommand1 == null) {
            backCommand1 = new javax.microedition.lcdui.Command("Back", javax.microedition.lcdui.Command.BACK, 1);
        }
        return backCommand1;
    }
    
    /**
     * This method returns instance for saveCommand component and should be called instead of accessing saveCommand field directly.
     * @return Instance for saveCommand component
     **/
    private javax.microedition.lcdui.Command get_saveCommand() {
        if (saveCommand == null) {
            saveCommand = new javax.microedition.lcdui.Command("Save", javax.microedition.lcdui.Command.ITEM, 1);
        }
        return saveCommand;
    }
    
    javax.microedition.lcdui.Form correctedPhenytoinForm;
    javax.microedition.lcdui.TextField totalPhenytoin;
    javax.microedition.lcdui.TextField albumin;
    javax.microedition.lcdui.StringItem result;
    javax.microedition.lcdui.Command backCommand1;
    javax.microedition.lcdui.Command saveCommand;
// --- This is the end of auto-generated section.//</editor-fold>//GEN-END:MVDMethods
    
    public void startApp() {
        initialize();
        get_correctedPhenytoinForm().setItemStateListener(this);
    }
    
    public void pauseApp() {
    }
    
    public void destroyApp(boolean unconditional) {
    }
    
    public void itemStateChanged(javax.microedition.lcdui.Item item) {
        if (!allItemsValid()) {
            get_result().setText("<all fields must be non empty and valid");
        } else {
            String phen = get_totalPhenytoin().getString();
            String alb = get_albumin().getString();
            
            INumeric denom = new Float("0.2").mul(new Float(alb).add("0.1"));
            get_result().setText(new Float(phen).div(denom).format(2));
        }
    }
    
    private boolean allItemsValid() {
        try {
            MicroFloat.parseFloat(get_totalPhenytoin().getString());
            MicroFloat.parseFloat(get_albumin().getString());
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}