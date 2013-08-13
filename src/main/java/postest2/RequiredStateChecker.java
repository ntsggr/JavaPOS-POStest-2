package postest2;

import java.lang.reflect.Field;

import jpos.BaseJposControl;
import jpos.JposConst;
import jpos.JposException;
import javafx.scene.control.*;

public class RequiredStateChecker {
	public static void invokeThis(Object theObject, BaseJposControl service){
		try {
			Field[] fields = Class.forName(theObject.getClass().getName()).getFields();
			JposState deviceState = null;
			Control control = new Control() {};
			deviceState = getDeviceState(service);
			System.out.println("Field len: " + fields.length);
			for (int i = 0; i < fields.length; i++) {
				System.out.println("Field len: " + fields.length);
				RequiredState requiredState = fields[i].getAnnotation(RequiredState.class);
				//mit getType arbeiten
				
				if(Control.class.isAssignableFrom(fields[i].getType())){
					Control c = (Control) fields[i].get(theObject);
					if(requiredState != null){
						JposState componentState = requiredState.value();
						if(componentState == JposState.OPENED){
							if(deviceState == JposState.OPENED || deviceState == JposState.CLAIMED || deviceState == JposState.ENABLED){
								c.setDisable(false);
							} else {
								c.setDisable(true);
							}
						}
						if(componentState == JposState.CLAIMED){
							if(deviceState == JposState.CLAIMED || deviceState == JposState.ENABLED){
								c.setDisable(false);
							} else {
								c.setDisable(true);
							}
						} 
						if(componentState == JposState.ENABLED){
							if(deviceState == JposState.ENABLED){
								c.setDisable(false);
							} else {
								c.setDisable(true);
							}
						} 
					}
					
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Gets the current DeviceState
	 * @param service
	 * @return
	 * @throws JposException
	 */
	private static JposState getDeviceState(BaseJposControl service) throws JposException{
		JposState deviceState = null;
		try{
			if(!service.getClaimed()){
				deviceState = JposState.OPENED;
			}
			if(service.getClaimed()){
				deviceState= JposState.CLAIMED;
			}
			
			if(service.getDeviceEnabled()){
				deviceState = JposState.ENABLED;
			}
		}catch(JposException e){
			if(e.getErrorCode() == JposConst.JPOS_E_CLOSED){
				deviceState= JposState.CLOSED;
			}
		}
		System.out.println(deviceState.toString());
		return deviceState;
		
	}
}
