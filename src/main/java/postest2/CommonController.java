package postest2;

import javax.swing.JOptionPane;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.text.Text;
import jpos.BaseJposControl;
import jpos.JposConst;
import jpos.JposException;

public abstract class CommonController implements Initializable {

	/* ************************************************************************
	 * ************************ Action Handler ********************************
	 * ************************************************************************
	 */
	
	// Common
	@FXML
	public ComboBox<String> logicalName;
	@FXML @RequiredState(JposState.CLAIMED)
	public CheckBox deviceEnabled;
	@FXML
	public Button buttonOpen;
	@FXML  @RequiredState(JposState.OPENED)
	public Button buttonClaim;
	@FXML @RequiredState(JposState.OPENED)
	public Button buttonRelease;
	@FXML @RequiredState(JposState.OPENED)
	public Button buttonStatistics;
	@FXML @RequiredState(JposState.OPENED)
	public Button buttonClose;
	@FXML @RequiredState(JposState.OPENED)
	public Button buttonFirmware;
	@FXML @RequiredState(JposState.CLOSED)
	public Button buttonOCE;
	@FXML @RequiredState(JposState.ENABLED)
	public Button buttonInfo;
	@FXML
	public Text statusLabel;
	@FXML @RequiredState(JposState.ENABLED)
	public CheckBox freezeEvents;
	BaseJposControl service;
	
	@FXML
	public void handleOpen(ActionEvent e) {
		try {
			if (logicalName.getValue() != null && !logicalName.getValue().isEmpty()) {
				service.open(logicalName.getValue());
				RequiredStateChecker.invokeThis(this, (BaseJposControl) service);
				//buttonClaim.setDisable(false);
				System.out.println(service.getState());
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

	@FXML
	public void handleClaim(ActionEvent e) {
		try {
			service.claim(0);
			RequiredStateChecker.invokeThis(this, (BaseJposControl) service);
		} catch (JposException je) {
			JOptionPane.showMessageDialog(null,
					"Failed to claim \"" + logicalName.getSelectionModel().getSelectedItem()
							+ "\"\nException: " + je.getMessage(), "Failed", JOptionPane.ERROR_MESSAGE);
		}
	}

	
	@FXML
	public void handleRelease(ActionEvent e) {
		try {
			service.release();
			if (deviceEnabled.isSelected()) {
				deviceEnabled.setSelected(false);
			}

			RequiredStateChecker.invokeThis(this, (BaseJposControl) service);
		} catch (JposException je) {
			JOptionPane.showMessageDialog(null,
					"Failed to release \"" + logicalName.getSelectionModel().getSelectedItem()
							+ "\"\nException: " + je.getMessage(), "Failed", JOptionPane.ERROR_MESSAGE);
		}
	}

	@FXML
	public void handleClose(ActionEvent e) {
		try {
			service.close();
			if (!deviceEnabled.isDisable()) {
				deviceEnabled.setSelected(false);
			}

			RequiredStateChecker.invokeThis(this, (BaseJposControl) service);
			setStatusLabel();
		} catch (JposException je) {
			JOptionPane.showMessageDialog(null,
					"Failed to close \"" + logicalName.getSelectionModel().getSelectedItem()
							+ "\"\nException: " + je.getMessage(), "Failed", JOptionPane.ERROR_MESSAGE);
		}
	}

	@FXML
	public void handleOCE(ActionEvent e) {
		// TODO implement
	}

	@FXML
	public void handleInfo(ActionEvent e) {
		// TODO implement
	}
	
	@FXML
	public void handleStatistics(ActionEvent e) {
		// TODO implement

	}

	@FXML
	public void handleFirmware(ActionEvent e) {
		// TODO implement
	}

	@FXML
	public void handleFreezeEvents(ActionEvent e) {
		try {
			service.setFreezeEvents(freezeEvents.selectedProperty().getValue());
		} catch (JposException e1) {
			JOptionPane.showMessageDialog(null, e1.getMessage());
		}
	}
	
	/**
	 * Set StatusLabel corresponding to the Devicestatus
	 */
	private void setStatusLabel(){
		if(service.getState() == JposConst.JPOS_S_IDLE){
			statusLabel.setText("JPOS_S_IDLE");
		}
		
		if(service.getState() == JposConst.JPOS_S_CLOSED){
			statusLabel.setText("JPOS_S_CLOSED");
		}
		
		if(service.getState() == JposConst.JPOS_S_BUSY){
			statusLabel.setText("JPOS_S_BUSY");
		}
		
		if(service.getState() == JposConst.JPOS_S_ERROR){
			statusLabel.setText("JPOS_S_ERROR");
		}
	}

	
}
