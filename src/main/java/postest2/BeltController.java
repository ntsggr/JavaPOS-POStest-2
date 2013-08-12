package postest2;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javax.swing.JOptionPane;
import jpos.Belt;
import jpos.JposConst;
import jpos.JposException;

public class BeltController implements Initializable {

	@FXML
	private ComboBox<String> logicalName;
	@FXML
	private CheckBox deviceEnabled;
	@FXML
	private Button buttonOpen;
	@FXML
	private Button buttonClaim;
	@FXML
	private Button buttonRelease;
	@FXML
	private Button buttonStatistics;
	@FXML
	private Button buttonClose;
	@FXML
	private Button buttonOCE;
	@FXML
	private Button buttonFirmware;
	@FXML
	private Text statusLabel;
	@FXML
	private CheckBox freezeEvents;

	@FXML
	private ComboBox<Boolean> autoStopBackward;
	@FXML
	private ComboBox<Boolean> autoStopForward;
	@FXML
	private ComboBox<Integer> moveBackward_speed;
	@FXML
	private ComboBox<Integer> moveForward_speed;
	@FXML
	private ComboBox<String> resetitemCount_direction;
	@FXML
	private ComboBox<String> adjustItemCount_direction;

	@FXML
	private TextField autoStopBackwardDelayTime;
	@FXML
	private TextField autoStopForwardDelayTime;
	@FXML
	private TextField adjustItemCount_Count;
	
	@FXML
	private Pane functionPane;

	private Belt belt;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		belt = new Belt();
		setUpLogicalNameComboBox();
	}

	private void setUpLogicalNameComboBox() {
		if(!LogicalNameGetter.getLogicalNamesByCategory("Belt").isEmpty()){
			logicalName.setItems(LogicalNameGetter.getLogicalNamesByCategory("Belt"));
		}
	}

	/* ************************************************************************
	 * ************************ Action Handler *********************************
	 * ***********************************************************************
	 */
	@FXML
	public void handleOpen(ActionEvent e) {
		System.out.println("Open");
		try {
			if (logicalName != null && logicalName.getValue() != null && !logicalName.getValue().isEmpty()) {
				belt.open(logicalName.getValue());
				buttonClaim.setDisable(false);
				setStatusLabel();
			} else {
				JOptionPane.showMessageDialog(null, "Choose a device!", "Logical name is empty",
						JOptionPane.WARNING_MESSAGE);
			}

		} catch (JposException je) {
			JOptionPane.showMessageDialog(null,
					"Failed to claim \"" + logicalName.getSelectionModel().getSelectedItem()
							+ "\"\nException: " + je.getMessage(), "Failed", JOptionPane.ERROR_MESSAGE);
		}
	}

	// Requests exclusive access to the device
	@FXML
	public void handleClaim(ActionEvent e) {
		System.out.println("Claim");
		try {
			belt.claim(0);
			deviceEnabled.setDisable(false);
			buttonRelease.setDisable(false);
			
		} catch (JposException je) {
			JOptionPane.showMessageDialog(null,
					"Failed to claim \"" + logicalName.getSelectionModel().getSelectedItem()
							+ "\"\nException: " + je.getMessage(), "Failed", JOptionPane.ERROR_MESSAGE);
		}
	}

	@FXML
	public void handleDeviceEnable(ActionEvent e) {
		System.out.println("DevEnable");
		try {
			if (deviceEnabled.isSelected()) {
				belt.setDeviceEnabled(true);
				setUpCheckboxes();
				
			} else {
				belt.setDeviceEnabled(false);
			}
		} catch (JposException je) {
			JOptionPane.showMessageDialog(null, je.getMessage());
		}
	}

	// Releases exclusive access to the device. The device is also disabled.
	@FXML
	public void handleRelease(ActionEvent e) {
		System.out.println("Release");
		try {
			belt.release();
			if (deviceEnabled.isSelected()) {
				deviceEnabled.setSelected(false);
				functionPane.setVisible(false);
			}
			deviceEnabled.setDisable(true);
		} catch (JposException je) {
			JOptionPane.showMessageDialog(null,
					"Failed to release \"" + logicalName.getSelectionModel().getSelectedItem()
							+ "\"\nException: " + je.getMessage(), "Failed", JOptionPane.ERROR_MESSAGE);
		}
	}

	// Releases the device and its resources. Also the device is released.
	@FXML
	public void handleClose(ActionEvent e) {
		System.out.println("Close");
		try {
			belt.close();
			if (!deviceEnabled.isDisable()) {
				deviceEnabled.setSelected(false);
				functionPane.setVisible(false);
			}
			buttonClaim.setDisable(true);
			deviceEnabled.setDisable(true);
			buttonRelease.setDisable(true);
			setStatusLabel();
		} catch (JposException je) {
			JOptionPane.showMessageDialog(null,
					"Failed to close \"" + logicalName.getSelectionModel().getSelectedItem()
							+ "\"\nException: " + je.getMessage(), "Failed", JOptionPane.ERROR_MESSAGE);
		}
	}

	@FXML
	public void handleOCE(ActionEvent e) {
		System.out.println("OCE");
		// TODO implement
	}

	@FXML
	public void handleInfo(ActionEvent e) {
		System.out.println("Info");
		// TODO implement
	}
	
	@FXML
	public void handleStatistics(ActionEvent e) {
		System.out.println("Statistics");
		// TODO implement

	}

	@FXML
	public void handleFirmware(ActionEvent e) {
		System.out.println("Firmware");
		// TODO implement
	}

	@FXML
	public void handleFreezeEvents(ActionEvent e) {
		System.out.println("Freeze");
		try {
			belt.setFreezeEvents(freezeEvents.selectedProperty().getValue());
		} catch (JposException e1) {
			JOptionPane.showMessageDialog(null, e1.getMessage());
		}
	}

	@FXML
	public void handleAutoStopBackward(ActionEvent e) {
		System.out.println("AutoStopBackward");
		if(autoStopBackward.getSelectionModel().getSelectedItem() != null){
			try {
				belt.setAutoStopBackward(autoStopBackward.getSelectionModel().getSelectedItem());
			} catch (JposException e1) {
				JOptionPane.showMessageDialog(null, e1.getMessage());
			}
		}
	}

	@FXML
	public void handleAutoStopBackwardDelayTime(ActionEvent e) {
		System.out.println("AutoStopBWDT");
		if(!autoStopBackwardDelayTime.getText().isEmpty()){
			try {
				belt.setAutoStopBackwardDelayTime(Integer.parseInt(autoStopBackwardDelayTime.getText()));
			} catch (NumberFormatException e1){
				JOptionPane.showMessageDialog(null, e1.getMessage());
			} catch (JposException e1) {
				JOptionPane.showMessageDialog(null, e1.getMessage());
			}
		}
	}

	@FXML
	public void handleAutoStopForward(ActionEvent e) {
		System.out.println("ASW");
		if(autoStopForward.getSelectionModel().getSelectedItem() != null){
			try {
				belt.setAutoStopForward(autoStopForward.getSelectionModel().getSelectedItem());
			} catch (JposException e1) {
				JOptionPane.showMessageDialog(null, e1.getMessage());
			}
		}
	}

	@FXML
	public void handleAutoStopForwardDelayTime(ActionEvent e) {
		System.out.println("ASFDT");
		if(!autoStopForwardDelayTime.getText().isEmpty()){
			try {
				belt.setAutoStopForwardDelayTime(Integer.parseInt(autoStopForwardDelayTime.getText()));
			} catch (NumberFormatException e1){
				JOptionPane.showMessageDialog(null, e1.getMessage());
			} catch (JposException e1) {
				JOptionPane.showMessageDialog(null, e1.getMessage());
			} 
		}
	}

	@FXML
	public void handleAdjustItemCount(ActionEvent e) {
		System.out.println("AIC");
		if(adjustItemCount_direction.getSelectionModel().getSelectedItem() != null){
	
			try {
				belt.adjustItemCount(BeltConstantMapper.getConstantNumberFromString(adjustItemCount_direction
						.getSelectionModel().getSelectedItem()),
						Integer.parseInt(adjustItemCount_Count.getText()));
			} catch (NumberFormatException e1){
				JOptionPane.showMessageDialog(null, e1.getMessage());
			} catch (JposException e1) {
				JOptionPane.showMessageDialog(null, e1.getMessage());
			}
		}
	}

	@FXML
	public void handleMoveBackward(ActionEvent e) {
		System.out.println("MB");
		if(moveBackward_speed.getSelectionModel().getSelectedItem() != null){
			try {
				belt.moveBackward(moveBackward_speed.getSelectionModel().getSelectedItem());
			} catch (JposException e1) {
				JOptionPane.showMessageDialog(null, e1.getMessage());
			}
		}
	}

	@FXML
	public void handleMoveForward(ActionEvent e) {
		System.out.println("MF");
		if(moveForward_speed.getSelectionModel().getSelectedItem() != null){
			try {
				belt.moveForward(moveForward_speed.getSelectionModel().getSelectedItem());
			} catch (JposException e1) {
				JOptionPane.showMessageDialog(null, e1.getMessage());
			}
		}
	}

	@FXML
	public void handleResetBelt(ActionEvent e) {
		System.out.println("RB");
		try {
			belt.resetBelt();
		} catch (JposException e1) {
			JOptionPane.showMessageDialog(null, e1.getMessage());
		}
	}

	@FXML
	public void handleResetItemCount(ActionEvent e) {
		System.out.println("RIC");
		if(resetitemCount_direction.getSelectionModel().getSelectedItem() != null){
			try {
				belt.resetItemCount(BeltConstantMapper.getConstantNumberFromString(resetitemCount_direction
						.getSelectionModel().getSelectedItem()));
			} catch (JposException e1) {
				JOptionPane.showMessageDialog(null, e1.getMessage());
			}
		}
	}

	@FXML
	public void handleStopBelt(ActionEvent e) {
		System.out.println("SB");
		try {
			belt.stopBelt();
		} catch (JposException e1) {
			JOptionPane.showMessageDialog(null, e1.getMessage());
		}
	}

	/*
	 * Initialize ComboBoxes
	 */

	private void setUpAutoStopBackward() {
		autoStopBackward.getItems().clear();
		autoStopBackward.getItems().add(true);
		autoStopBackward.getItems().add(false);
		autoStopBackward.setValue(true);
	}

	private void setUpAutoStopForward() {
		autoStopForward.getItems().clear();
		autoStopForward.getItems().add(true);
		autoStopForward.getItems().add(false);
		autoStopForward.setValue(true);
	}

	private void setUpAdjustItemCountDirection() {
		adjustItemCount_direction.getItems().clear();
		adjustItemCount_direction.getItems().add(BeltConstantMapper.BELT_AIC_BACKWARD.getConstant());
		adjustItemCount_direction.getItems().add(BeltConstantMapper.BELT_AIC_FORWARD.getConstant());
		adjustItemCount_direction.setValue(BeltConstantMapper.BELT_AIC_FORWARD.getConstant());
	}


	// Cannot implement correctly because the Variable CapSpeedStepsBackward of
	// the JPOS Class Belt is not implemented correctly
	// Wrong Datatype (boolean instead of int)
	private void setUpMoveBackwardSpeed() {
		// int speedSteps;
		// for(int i = 0; i < belt.getcaps.getCapSpeedStepsBackward();i++){
		// }
		moveBackward_speed.getItems().clear();
		moveBackward_speed.getItems().add(1);
		moveBackward_speed.getItems().add(2);
		moveBackward_speed.setValue(1);

	}

	// Cannot implement correctly because the Variable CapSpeedStepsBackward of
	// the JPOS Class Belt is not implemented correctly
	// Wrong Datatype (boolean instead of int)
	private void setUpMoveForwardSpeed() {
		// int speedSteps;
		// for(int i = 0; i < belt.getCapSpeedStepsBackward();i++){
		// }
		moveForward_speed.getItems().clear();
		moveForward_speed.getItems().add(1);
		moveForward_speed.getItems().add(2);
		moveForward_speed.setValue(1);
	}

	private void setUpResetItemCount() {
		resetitemCount_direction.getItems().clear();
		resetitemCount_direction.getItems().add(BeltConstantMapper.BELT_RIC_BACKWARD.getConstant());
		resetitemCount_direction.getItems().add(BeltConstantMapper.BELT_RIC_FORWARD.getConstant());
		resetitemCount_direction.setValue(BeltConstantMapper.BELT_RIC_FORWARD.getConstant());
	}

	private void setUpCheckboxes() {
		setUpAutoStopBackward();
		setUpAutoStopForward();
		setUpAdjustItemCountDirection();
		setUpMoveBackwardSpeed();
		setUpMoveForwardSpeed();
		setUpResetItemCount();

	}

	private void setStatusLabel(){
		if(belt.getState() == JposConst.JPOS_S_IDLE){
			statusLabel.setText("JPOS_S_IDLE");
		}
		
		if(belt.getState() == JposConst.JPOS_S_CLOSED){
			statusLabel.setText("JPOS_S_CLOSED");
		}
		
		if(belt.getState() == JposConst.JPOS_S_BUSY){
			statusLabel.setText("JPOS_S_BUSY");
		}
		
		if(belt.getState() == JposConst.JPOS_S_ERROR){
			statusLabel.setText("JPOS_S_ERROR");
		}
	}
	
}
