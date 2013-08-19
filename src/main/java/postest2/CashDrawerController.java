package postest2;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;

import javax.swing.JOptionPane;

import jpos.CashDrawer;
import jpos.CashDrawerConst;
import jpos.JposException;
import jpos.events.StatusUpdateEvent;
import jpos.events.StatusUpdateListener;
import jpos.profile.JposDevCats;

public class CashDrawerController extends CommonController implements Initializable, StatusUpdateListener {

	/*
	@FXML
	private ComboBox<String> logicalName;
	
	@FXML
	private Button buttonOpen;
	@FXML
	private Button buttonClaim;
	@FXML
	private Button buttonRelease;
	@FXML
	private CheckBox deviceEnabled;
	*/
	@FXML @RequiredState(JposState.ENABLED)
	public TextArea textAreaActionLog;
	@FXML @RequiredState(JposState.ENABLED)
	public Button buttonOpenCash;
	@FXML @RequiredState(JposState.ENABLED)
	public Button buttonGetDrawer;
	@FXML @RequiredState(JposState.ENABLED)
	public Button buttonWaitForDrawer;
	
	/*
	@FXML
	private Label statusLabel;

	private CashDrawer cashDrawer;
	private String defaultLogicalName = "defaultCashDrawer";
	*/
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		setUpLogicalNameComboBox();
		service = new CashDrawer();
		((CashDrawer)service).addStatusUpdateListener(this);

		RequiredStateChecker.invokeThis(this, service);
	}
	
	/* ************************************************************************
	 * ************************ Action Handler *********************************
	 * ***********************************************************************
	 */
	/*
	@FXML
	public void handleOpen(ActionEvent e) {
		try {
			if (logicalName.getValue() != null && !logicalName.getValue().isEmpty()) {
				cashDrawer.open(logicalName.getValue());
				buttonClaim.setDisable(false);
				int version = cashDrawer.getDeviceServiceVersion();
				statusLabel.setText("JPOS_S_IDLE");
			} else {
				JOptionPane.showMessageDialog(null, "Choose a device!", "Logical name is empty", JOptionPane.WARNING_MESSAGE);
			}

		} catch (JposException je) {
			JOptionPane.showMessageDialog(null, "Failed to claim \"" + "CashDrawer" + "\"\nException: " + je.getMessage(), "Failed", JOptionPane.ERROR_MESSAGE);
		}
	}

	// Requests exclusive access to the device
	@FXML
	public void handleClaim(ActionEvent e) {
		try {
			cashDrawer.claim(0);
			deviceEnabled.setDisable(false);
			buttonRelease.setDisable(false);
		} catch (JposException je) {
			JOptionPane.showMessageDialog(null, "Failed to claim \"" + "CashDrawer" + "\"\nException: " + je.getMessage(), "Failed", JOptionPane.ERROR_MESSAGE);
		}
	}
	*/
	@FXML
	public void handleDeviceEnable(ActionEvent e) {
		try {
			if (deviceEnabled.isSelected()) {
				((CashDrawer)service).setDeviceEnabled(true);
				buttonOpenCash.setDisable(false);
				buttonGetDrawer.setDisable(false);
				buttonWaitForDrawer.setDisable(false);
			} else {
				((CashDrawer)service).setDeviceEnabled(false);
				buttonOpenCash.setDisable(true);
				buttonGetDrawer.setDisable(true);
				buttonWaitForDrawer.setDisable(true);
			}
			RequiredStateChecker.invokeThis(this, service);
		} catch (JposException je) {
			System.err.println("CashDrawerPanel: CheckBoxListener: Jpos Exception" + je);
		}
	}
	/*
	// Releases exclusive access to the device. The device is also disabled.
	@FXML
	public void handleRelease(ActionEvent e) {
		try {
			((CashDrawer)service).release();
			if (deviceEnabled.isSelected()) {
				deviceEnabled.setSelected(false);
				buttonOpenCash.setDisable(true);
				buttonGetDrawer.setDisable(true);
				buttonWaitForDrawer.setDisable(true);
			}
			deviceEnabled.setDisable(true);
		} catch (JposException je) {
			JOptionPane.showMessageDialog(null, "Failed to release \"" + logicalName + "\"\nException: " + je.getMessage(), "Failed", JOptionPane.ERROR_MESSAGE);
		}
	}

	// Releases the device and its resources. Also the device is released.
	@FXML
	public void handleClose(ActionEvent e) {
		try {
			((CashDrawer)service).close();
			statusLabel.setText("JPOS_S_CLOSED");
			if (!deviceEnabled.isDisable()) {
				deviceEnabled.setSelected(false);
				buttonOpenCash.setDisable(true);
				buttonGetDrawer.setDisable(true);
				buttonWaitForDrawer.setDisable(true);
			}
			buttonClaim.setDisable(true);
			deviceEnabled.setDisable(true);
			buttonRelease.setDisable(true);
		} catch (JposException je) {
			JOptionPane.showMessageDialog(null, "Failed to close \"" + logicalName + "\"\nException: " + je.getMessage(), "Failed", JOptionPane.ERROR_MESSAGE);
		}
	}
	*/

	@FXML
	public void handleOpenCash(ActionEvent e) {
		try {
			((CashDrawer)service).openDrawer();
		} catch (JposException je) {
			JOptionPane.showMessageDialog(null, "Exception in openDrawer: " + je.getMessage(), "Exception", JOptionPane.ERROR_MESSAGE);
		}
	}

	@FXML
	public void handleGetDrawer(ActionEvent e) {
		try {
			if (((CashDrawer)service).getDrawerOpened()) {
				textAreaActionLog.appendText("Cash drawer is open.\n");
			} else {
				textAreaActionLog.appendText("Cash drawer is closed.\n");
			}
		} catch (JposException je) {
			JOptionPane.showMessageDialog(null, "Exception in getDrawerOpened: " + je.getMessage(), "Exception", JOptionPane.ERROR_MESSAGE);
		}
	}

	@FXML
	public void handleWaitForDrawer(ActionEvent e) {
		try {
			((CashDrawer)service).waitForDrawerClose(100, 500, 100, 200);
			textAreaActionLog.appendText("Cash drawer is closed.\n");
		} catch (JposException je) {
			textAreaActionLog.appendText("Jpos exception " + je + "\n");
			JOptionPane.showMessageDialog(null, "Exception in waitForDrawerClose: " + je.getMessage(), "Exception", JOptionPane.ERROR_MESSAGE);
		}
	}

	@Override
	public void statusUpdateOccurred(StatusUpdateEvent sue) {
		String msg = "Status Update Event: ";
		statusLabel.setText(""+sue.getStatus());
		switch (sue.getStatus()) {
		case CashDrawerConst.CASH_SUE_DRAWERCLOSED:
			msg += "Drawer Closed\n";
			break;
		case CashDrawerConst.CASH_SUE_DRAWEROPEN:
			msg += "Drawer Opened\n";
			break;
		default:
			msg += "Unknown Status: " + Integer.toString(sue.getStatus()) + "\n";
			break;
		}
	}
	private void setUpLogicalNameComboBox() {
		logicalName.setItems(LogicalNameGetter.getLogicalNamesByCategory(JposDevCats.CASHDRAWER_DEVCAT.toString()));
	}
	
}