/*
 * Copyright 2013 NTS New Technology Systems GmbH. All Rights reserved.
 * NTS PROPRIETARY/CONFIDENTIAL. Use is subject to NTS License Agreement.
 * Address: Doernbacher Strasse 126, A-4073 Wilhering, Austria
 * Homepage: www.ntswincash.com
 */
package postest2;

import java.lang.reflect.Field;

import jpos.BaseJposControl;
import jpos.JposConst;
import jpos.JposException;
import javafx.scene.Node;
import javafx.scene.control.*;

/**
 * This Class provides the functionality that a Field with the RequiredState-Annotation is disabled/enabled 
 * corresponding to the given Value (CLOSED, OPENED, CLAIMED, ENABLED) of the Variable
 *
 */
public class RequiredStateChecker {
	public static void invokeThis(Object theObject, BaseJposControl service){
		try {
			//get all Fields from the Class
			Field[] fields = Class.forName(theObject.getClass().getName()).getFields();
			JposState deviceState = null;
			deviceState = getDeviceState(service);
			//parse through all fields
			for (int i = 0; i < fields.length; i++) {
				//get the Annotation from each Field
				RequiredState requiredState = fields[i].getAnnotation(RequiredState.class);				
				//Get only those fields which are a JavaFX Node
				if(Node.class.isAssignableFrom(fields[i].getType())){
					Node c = (Node) fields[i].get(theObject);
					if(requiredState != null){
						//get the Value of each Annotation
						JposState componentState = requiredState.value();
						//Disable/Enable corresponding to the current deviceState and the requiredState
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
		return deviceState;
		
	}
}
