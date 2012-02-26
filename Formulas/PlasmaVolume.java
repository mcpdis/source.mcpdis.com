/*
 * PlasmaVolume.java
 *
 * Created on March 9, 2006, 11:42 PM
 */

package Formulas;

import javax.microedition.midlet.*;
import javax.microedition.lcdui.*;

import net.dclausen.microfloat.*;
/**
 *
 * @author user
 */
public class PlasmaVolume extends MIDlet 
        implements javax.microedition.lcdui.CommandListener,
                   javax.microedition.lcdui.ItemStateListener {
    
    /** Creates a new instance of PlasmaVolume */
    public PlasmaVolume() {
    }

    private Persistence.FormulaValue get_formulaValue() {
        return new Persistence.FormulaValue("Plasma Volume [mL]", get_result().getText());
    }
    
// --- This section is auto-generated by NetBeans IDE. Do not modify or you may lose your changes.//<editor-fold id="MVDMethods" defaultstate="collapsed" desc="This section is auto-generated by NetBeans IDE.">//GEN-BEGIN:MVDMethods
    /**
     * This method initializes UI of the application.
     */
    private void initialize() {
// For adding user code into this block, select "Design" item in the inspector and invoke property editor on Action property in Properties window.
        javax.microedition.lcdui.Display.getDisplay(this).setCurrent(get_plasmaVolumeForm());
    }
    
    /**
     * Called by the system to indicate that a command has been invoked on a particular displayable.
     * @param command the Command that ws invoked
     * @param displayable the Displayable on which the command was invoked
     **/
    public void commandAction(javax.microedition.lcdui.Command command, javax.microedition.lcdui.Displayable displayable) {
        if (displayable == plasmaVolumeForm) {
            if (command == backCommand1) {
// For adding user code into this block, select "Design | Screens | plasmaVolumeForm [Form] | Assigned Commands | backCommand1" item in the inspector and invoke property editor on Action property in Properties window.
                javax.microedition.lcdui.Display.getDisplay(this).setCurrent(null);
                destroyApp(true);
                notifyDestroyed();
            } else if (command == saveCommand) {
// For adding user code into this block, select "Design | Screens | plasmaVolumeForm [Form] | Assigned Commands | saveCommand" item in the inspector and invoke property editor on Action property in Properties window.
                Persistence.PatientSuiteDispatcher.invoke(this, get_plasmaVolumeForm(), get_formulaValue());
            }
        }
    }
    
    /**
     * This method returns instance for plasmaVolumeForm component and should be called instead of accessing plasmaVolumeForm field directly.
     * @return Instance for plasmaVolumeForm component
     **/
    private javax.microedition.lcdui.Form get_plasmaVolumeForm() {
        if (plasmaVolumeForm == null) {
            plasmaVolumeForm = new javax.microedition.lcdui.Form("Plasma volume", new javax.microedition.lcdui.Item[] {
                get_hematocrit(),
                        get_weight(),
                        get_result()
            });
            plasmaVolumeForm.addCommand(get_backCommand1());
            plasmaVolumeForm.addCommand(get_saveCommand());
            plasmaVolumeForm.setCommandListener(this);
        }
        return plasmaVolumeForm;
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
     * This method returns instance for hematocrit component and should be called instead of accessing hematocrit field directly.
     * @return Instance for hematocrit component
     **/
    private javax.microedition.lcdui.TextField get_hematocrit() {
        if (hematocrit == null) {
            hematocrit = new javax.microedition.lcdui.TextField("Hematocrit [%]", "", 20, 0x0);
        }
        return hematocrit;
    }
    
    /**
     * This method returns instance for weight component and should be called instead of accessing weight field directly.
     * @return Instance for weight component
     **/
    private javax.microedition.lcdui.TextField get_weight() {
        if (weight == null) {
            weight = new javax.microedition.lcdui.TextField("Weight [kg]", "", 20, 0x0);
        }
        return weight;
    }
    
    /**
     * This method returns instance for result component and should be called instead of accessing result field directly.
     * @return Instance for result component
     **/
    private javax.microedition.lcdui.StringItem get_result() {
        if (result == null) {
            result = new javax.microedition.lcdui.StringItem("Plasma volume [mL]", "");
        }
        return result;
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
    
    javax.microedition.lcdui.Form plasmaVolumeForm;
    javax.microedition.lcdui.Command backCommand1;
    javax.microedition.lcdui.TextField hematocrit;
    javax.microedition.lcdui.TextField weight;
    javax.microedition.lcdui.StringItem result;
    javax.microedition.lcdui.Command saveCommand;
// --- This is the end of auto-generated section.//</editor-fold>//GEN-END:MVDMethods
    
    public void startApp() {
        initialize();
        get_plasmaVolumeForm().setItemStateListener(this);
    }
    
    public void pauseApp() {
    }
    
    public void destroyApp(boolean unconditional) {
    }
    
    public void itemStateChanged(javax.microedition.lcdui.Item item) {
        if (!allItemsValid()) {
            get_result().setText("<all items must be non-empty and valid>");
        } else {
            String hema = get_hematocrit().getString();
            String weight = get_weight().getString();
            
            get_result().setText(
                new Float("0.065").mul(weight).mul(new Float("1").sub(hema)).format(2));
        }
    }

    private boolean allItemsValid() {
        try {
            MicroFloat.parseFloat(get_hematocrit().getString());
            MicroFloat.parseFloat(get_weight().getString());
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
