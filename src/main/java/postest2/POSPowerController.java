package postest2;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;

import javax.swing.JOptionPane;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import jpos.JposConst;
import jpos.JposException;
import jpos.POSPower;
import jpos.POSPowerConst;
import jpos.events.StatusUpdateEvent;
import jpos.events.StatusUpdateListener;

import org.apache.xerces.parsers.DOMParser;
import org.w3c.dom.Document;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

public class POSPowerController extends CommonController implements Initializable, StatusUpdateListener {

	@FXML
	@RequiredState(JposState.ENABLED)
	public Pane pane;
	@FXML
	public ComboBox<String> reason;
	@FXML
	public Text state;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		service = new POSPower();
		RequiredStateChecker.invokeThis(this, service);

		setUpReason();
	}

	/* ************************************************************************
	 * ************************ Action Handler *********************************
	 * ***********************************************************************
	 */

	@FXML
	public void handleRestartPOS(ActionEvent e) {
		try {
			((POSPower) service).restartPOS();
		} catch (JposException jpe) {
			jpe.printStackTrace();
		}
	}

	@FXML
	public void handleShutdownPOS(ActionEvent e) {
		try {
			((POSPower) service).shutdownPOS();
		} catch (JposException jpe) {
			jpe.printStackTrace();
		}
	}

	@FXML
	public void handleStandbyPOS(ActionEvent e) {
		try {
			((POSPower) service).standbyPOS(POSPowerConstantMapper.getConstantNumberFromString(reason
					.getSelectionModel().getSelectedItem()));
		} catch (JposException jpe) {
			jpe.printStackTrace();
		}
	}

	@FXML
	public void handleSuspendPOS(ActionEvent e) {
		try {
			((POSPower) service).suspendPOS(POSPowerConstantMapper.getConstantNumberFromString(reason
					.getSelectionModel().getSelectedItem()));
		} catch (JposException jpe) {
			jpe.printStackTrace();
		}
	}

	@FXML
	public void handleDeviceEnable(ActionEvent e) {
		try {
			if (deviceEnabled.isSelected()) {
				((POSPower) service).setDeviceEnabled(true);
			} else {
				((POSPower) service).setDeviceEnabled(false);
			}
			RequiredStateChecker.invokeThis(this, service);
		} catch (JposException je) {
			System.err.println("POSPowerPanel: CheckBoxListener: Jpos Exception" + je);
		}
	}

	@Override
	@FXML
	public void handleOCE(ActionEvent e) {
		super.handleOCE(e);
		deviceEnabled.setSelected(true);
		handleDeviceEnable(e);
	}

	// Shows statistics of device if they are supported by the device
	@Override
	@FXML
	public void handleInfo(ActionEvent e) {
		try {
			String ver = new Integer(((POSPower) service).getDeviceServiceVersion()).toString();
			String msg = "Service Description: " + ((POSPower) service).getDeviceServiceDescription();
			msg = msg + "\nService Version: v" + new Integer(ver.substring(0, 1)) + "."
					+ new Integer(ver.substring(1, 4)) + "." + new Integer(ver.substring(4, 7));
			ver = new Integer(((POSPower) service).getDeviceControlVersion()).toString();
			msg += "\n\nControl Description: " + ((POSPower) service).getDeviceControlDescription();
			msg += "\nControl Version: v" + new Integer(ver.substring(0, 1)) + "."
					+ new Integer(ver.substring(1, 4)) + "." + new Integer(ver.substring(4, 7));
			msg += "\n\nPhysical Device Name: " + ((POSPower) service).getPhysicalDeviceName();
			msg += "\nPhysical Device Description: " + ((POSPower) service).getPhysicalDeviceDescription();

			msg += "\n\nProperties:\n------------------------";

			msg += "\nCapStatisticsReporting: " + (((POSPower) service).getCapStatisticsReporting());

			msg += "\nCapUpdateFirmware: " + (((POSPower) service).getCapUpdateFirmware());

			msg += "\nCapCompareFirmwareVersion: " + (((POSPower) service).getCapCompareFirmwareVersion());

			msg += "\nCapPowerReporting: "
					+ (((POSPower) service).getCapPowerReporting() == JposConst.JPOS_PR_ADVANCED ? "Advanced"
							: (((POSPower) service).getCapPowerReporting() == JposConst.JPOS_PR_STANDARD ? "Standard"
									: "None"));

			msg = msg + "\nCapUPSChargeState: " + ((POSPower) service).getCapUPSChargeState();
			msg = msg + "\nCapBatteryCapacityRemaining: "
					+ ((POSPower) service).getCapBatteryCapacityRemaining();
			msg = msg + "\nCapFanAlarm: " + ((POSPower) service).getCapFanAlarm();
			msg = msg + "\nCapHeatAlarm: " + ((POSPower) service).getCapHeatAlarm();
			msg = msg + "\nCapQuickCharge: " + ((POSPower) service).getCapQuickCharge();
			msg = msg + "\nCapRestartPOS: " + ((POSPower) service).getCapRestartPOS();
			msg = msg + "\nCapShutdownPOS: " + ((POSPower) service).getCapShutdownPOS();
			msg = msg + "\nCapStandbyPOS: " + ((POSPower) service).getCapStandbyPOS();
			msg = msg + "\nCapCapSuspendPOS: " + ((POSPower) service).getCapSuspendPOS();
			msg = msg + "\nCapVariableBatteryCriticallyLowThreshold: "
					+ ((POSPower) service).getCapVariableBatteryCriticallyLowThreshold();
			msg = msg + "\nCapVariableBatteryLowThreshold: "
					+ ((POSPower) service).getCapVariableBatteryLowThreshold();

			JOptionPane.showMessageDialog(null, msg, "Info", JOptionPane.INFORMATION_MESSAGE);

		} catch (JposException jpe) {
			JOptionPane.showMessageDialog(null, "Exception in Info\nException: " + jpe.getMessage(),
					"Exception", JOptionPane.ERROR_MESSAGE);
			System.err.println("Jpos exception " + jpe);
		}
	}

	// Shows statistics of device if they are supported by the device
	@Override
	@FXML
	public void handleStatistics(ActionEvent e) {
		String[] stats = new String[] { "", "U_", "M_" };
		try {
			((POSPower) service).retrieveStatistics(stats);
			DOMParser parser = new DOMParser();
			parser.parse(new InputSource(new java.io.StringReader(stats[1])));

			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			DocumentBuilder db = dbf.newDocumentBuilder();
			Document doc = db.parse(new ByteArrayInputStream(stats[1].getBytes()));

			printStatistics(doc.getDocumentElement(), "");

			JOptionPane.showMessageDialog(null, statistics, "Statistics", JOptionPane.INFORMATION_MESSAGE);
		} catch (IOException ioe) {
			ioe.printStackTrace();
		} catch (SAXException saxe) {
			saxe.printStackTrace();
		} catch (ParserConfigurationException e1) {
			e1.printStackTrace();
		} catch (JposException jpe) {
			jpe.printStackTrace();
			JOptionPane.showMessageDialog(null, "Statistics are not supported!", "Statistics",
					JOptionPane.ERROR_MESSAGE);
		}

		statistics = "";
	}

	private void setUpReason() {
		reason.getItems().clear();
		reason.getItems().add(POSPowerConstantMapper.PWR_REASON_REQUEST.getConstant());
		reason.getItems().add(POSPowerConstantMapper.PWR_REASON_ALLOW.getConstant());
		reason.getItems().add(POSPowerConstantMapper.PWR_REASON_DENY.getConstant());
	}

	@Override
	public void statusUpdateOccurred(StatusUpdateEvent e) {
		try {
			int status = ((POSPower) service).getUPSChargeState();
			if (status == POSPowerConst.PWR_SUE_BAT_CAPACITY_REMAINING) {
				state.setText("BAT_CAPACITY_REMAINING");
			} else if (status == POSPowerConst.PWR_SUE_BAT_CRITICAL) {
				state.setText("BAT_CRITICAL");
			} else if (status == POSPowerConst.PWR_SUE_BAT_LOW) {
				state.setText("BAT_LOW");
			} else if (status == POSPowerConst.PWR_SUE_FAN_RUNNING) {
				state.setText("FAN_RUNNING");
			} else if (status == POSPowerConst.PWR_SUE_FAN_STOPPED) {
				state.setText("FAN_STOPPED");
			} else if (status == POSPowerConst.PWR_SUE_PWR_SOURCE) {
				state.setText("PWR_SOURCE");
			} else if (status == POSPowerConst.PWR_SUE_RESTART) {
				state.setText("RESTART");
			} else if (status == POSPowerConst.PWR_SUE_SHUTDOWN) {
				state.setText("SHUTDOWN");
			} else if (status == POSPowerConst.PWR_SUE_STANDBY) {
				state.setText("STANDBY");
			} else if (status == POSPowerConst.PWR_SUE_SUSPEND) {
				state.setText("SUSPEND");
			} else if (status == POSPowerConst.PWR_SUE_TEMPERATURE_HIGH) {
				state.setText("TEMPERATURE_HIGH");
			} else if (status == POSPowerConst.PWR_SUE_TEMPERATURE_OK) {
				state.setText("TEMPERATURE_OK");
			} else if (status == POSPowerConst.PWR_SUE_UPS_CRITICAL) {
				state.setText("UPS_CRITICAL");
			} else if (status == POSPowerConst.PWR_SUE_UPS_FULL) {
				state.setText("UPS_FULL");
			} else if (status == POSPowerConst.PWR_SUE_UPS_LOW) {
				state.setText("UPS_LOW");
			} else if (status == POSPowerConst.PWR_SUE_UPS_WARNING) {
				state.setText("UPS_WARNING");
			} else if (status == POSPowerConst.PWR_SUE_USER_STANDBY) {
				state.setText("USER_STANDBY");
			} else if (status == POSPowerConst.PWR_SUE_USER_SUSPEND) {
				state.setText("USER_SUSPEND");
			}
		} catch (JposException e1) {
			e1.printStackTrace();
		}
	}
}
