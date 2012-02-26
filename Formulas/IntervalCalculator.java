/*
 * IntervalCalculator.java
 *
 * Created on March 9, 2006, 2:09 PM
 */

package Formulas;

import javax.microedition.midlet.*;
import javax.microedition.lcdui.*;
import java.util.Date;

import net.dclausen.microfloat.*;
/**
 *
 * @author user
 */
public class IntervalCalculator extends MIDlet 
        implements javax.microedition.lcdui.CommandListener,
                   javax.microedition.lcdui.ItemStateListener {
    
    /** Creates a new instance of IntervalCalculator */
    public IntervalCalculator() {
    }
    
// --- This section is auto-generated by NetBeans IDE. Do not modify or you may lose your changes.//<editor-fold id="MVDMethods" defaultstate="collapsed" desc="This section is auto-generated by NetBeans IDE.">//GEN-BEGIN:MVDMethods
    /**
     * This method initializes UI of the application.
     */
    private void initialize() {
// For adding user code into this block, select "Design" item in the inspector and invoke property editor on Action property in Properties window.
        javax.microedition.lcdui.Display.getDisplay(this).setCurrent(get_intervalCalcForm());
    }
    
    /**
     * Called by the system to indicate that a command has been invoked on a particular displayable.
     * @param command the Command that ws invoked
     * @param displayable the Displayable on which the command was invoked
     **/
    public void commandAction(javax.microedition.lcdui.Command command, javax.microedition.lcdui.Displayable displayable) {
        if (displayable == intervalCalcForm) {
            if (command == backCommand1) {
// For adding user code into this block, select "Design | Screens | intervalCalcForm [Form] | Assigned Commands | backCommand1" item in the inspector and invoke property editor on Action property in Properties window.
                javax.microedition.lcdui.Display.getDisplay(this).setCurrent(null);
                destroyApp(true);
                notifyDestroyed();
            }
        }
    }
    
    /**
     * This method returns instance for intervalCalcForm component and should be called instead of accessing intervalCalcForm field directly.
     * @return Instance for intervalCalcForm component
     **/
    private javax.microedition.lcdui.Form get_intervalCalcForm() {
        if (intervalCalcForm == null) {
            intervalCalcForm = new javax.microedition.lcdui.Form("Interval Calculator", new javax.microedition.lcdui.Item[] {
                get_firstDate(),
                        get_secondDate(),
                        get_intervalType(),
                        get_result()
            });
            intervalCalcForm.addCommand(get_backCommand1());
            intervalCalcForm.setCommandListener(this);
        }
        return intervalCalcForm;
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
     * This method returns instance for firstDate component and should be called instead of accessing firstDate field directly.
     * @return Instance for firstDate component
     **/
    private javax.microedition.lcdui.DateField get_firstDate() {
        if (firstDate == null) {
            firstDate = new javax.microedition.lcdui.DateField("First Date", javax.microedition.lcdui.DateField.DATE_TIME);
        }
        return firstDate;
    }
    
    /**
     * This method returns instance for secondDate component and should be called instead of accessing secondDate field directly.
     * @return Instance for secondDate component
     **/
    private javax.microedition.lcdui.DateField get_secondDate() {
        if (secondDate == null) {
            secondDate = new javax.microedition.lcdui.DateField("Second Date", javax.microedition.lcdui.DateField.DATE_TIME);
        }
        return secondDate;
    }
    
    /**
     * This method returns instance for intervalType component and should be called instead of accessing intervalType field directly.
     * @return Instance for intervalType component
     **/
    private javax.microedition.lcdui.ChoiceGroup get_intervalType() {
        if (intervalType == null) {
            intervalType = new javax.microedition.lcdui.ChoiceGroup("View in", javax.microedition.lcdui.Choice.EXCLUSIVE, new java.lang.String[] {
                "Minutes",
                        "Hours",
                        "Days",
                        "Weeks"
            }, new javax.microedition.lcdui.Image[] {
                null,
                        null,
                        null,
                        null
            });
            intervalType.setSelectedFlags(new boolean[] {
                true,
                        false,
                        false,
                        false
            });
        }
        return intervalType;
    }
    
    /**
     * This method returns instance for result component and should be called instead of accessing result field directly.
     * @return Instance for result component
     **/
    private javax.microedition.lcdui.StringItem get_result() {
        if (result == null) {
            result = new javax.microedition.lcdui.StringItem("Interval [min] = ", "");
        }
        return result;
    }
    
    javax.microedition.lcdui.Form intervalCalcForm;
    javax.microedition.lcdui.Command backCommand1;
    javax.microedition.lcdui.DateField firstDate;
    javax.microedition.lcdui.DateField secondDate;
    javax.microedition.lcdui.ChoiceGroup intervalType;
    javax.microedition.lcdui.StringItem result;
// --- This is the end of auto-generated section.//</editor-fold>//GEN-END:MVDMethods
    
    public void startApp() {
        initialize();
        get_intervalCalcForm().setItemStateListener(this);
        get_firstDate().setDate(new Date());
        get_secondDate().setDate(new Date());
    }
    
    public void pauseApp() {
    }
    
    public void destroyApp(boolean unconditional) {
    }
    
    public void itemStateChanged(javax.microedition.lcdui.Item item) {
        if (!allItemsValid()) {
            get_result().setText("<all items must be non empty and first date must be before second date>");
        } else {
            long diff = getDiffInSeconds();
            INumeric interval;
            String newLabel;
            switch (get_intervalType().getSelectedIndex()) {
                // minutes
                case 0 : 
                    interval = getIntervalInMinutes(diff);
                    newLabel = "Interval [min] = ";
                    break;
                case 1 :
                    interval = getIntervalInHours(diff);
                    newLabel = "Interval [hours] = ";
                    break;
                case 2 :
                    interval = getIntervalInDays(diff);
                    newLabel = "Interval [days] = ";
                    break;
                case 3 :
                    interval = getIntervalInWeeks(diff);
                    newLabel = "Interval [weeks] = ";
                    break;
                default :
                    interval = getIntervalInMinutes(diff);
                    newLabel = "Interval [mins] = ";
            }
            get_result().setLabel(newLabel);
            get_result().setText(interval.format(2));
        }
    }
    
    private INumeric getIntervalInMinutes(long diff) {
        return getIntervalUsingDivisor(diff, 60);
    }
    
    private INumeric getIntervalInHours(long diff) {
        return getIntervalUsingDivisor(diff, 3600);
    }
    
    private INumeric getIntervalInDays(long diff) {
        return getIntervalUsingDivisor(diff, 86400);
    }
    
    private INumeric getIntervalInWeeks(long diff) {
        return getIntervalUsingDivisor(diff, 86400 * 7);
    }
    
    private INumeric getIntervalUsingDivisor(long diff, long divisor) {
        return new Double(String.valueOf(diff)).div(String.valueOf(divisor));
    }
    
    private long getDiffInSeconds() {
        long first = get_firstDate().getDate().getTime();
        long second = get_secondDate().getDate().getTime();

        return ((second - first) / 1000);
    }
    
    private boolean allItemsValid() {
        if (getDiffInSeconds() <= 0) {
            return false;
        } else {
            return true;
        }
    }
}