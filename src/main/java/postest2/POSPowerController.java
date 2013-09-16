package postest2;

import java.awt.Dimension;
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
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

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
		setUpTooltips();
		service = new POSPower();
		RequiredStateChecker.invokeThis(this, service);
		setUpLogicalNameComboBox("POSPower");
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
			JOptionPane.showMessageDialog(null, jpe.getMessage());
			jpe.printStackTrace();
		}
	}

	@FXML
	public void handleShutdownPOS(ActionEvent e) {
		try {
			((POSPower) service).shutdownPOS();
		} catch (JposException jpe) {
			JOptionPane.showMessageDialog(null, jpe.getMessage());
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
			JOptionPane.showMessageDialog(null, jpe.getMessage());
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
			JOptionPane.showMessageDialog(null, je.getMessage());
			je.printStackTrace();
		}
	}

	@Override
	@FXML
	public void handleOCE(ActionEvent e) {
		super.handleOCE(e);
		deviceEnabled.setSelected(true);
		handleDeviceEnable(e);
	}

	/**
	 * Shows statistics of device if they are supported by the device
	 */
	@Override
	@FXML
	public void handleInfo(ActionEvent e) {
		try {
//			String msg = DeviceProperties.getProperties(service, POSPowerConstantMapper.class);
			String msg = "";
			JTextArea jta = new JTextArea(msg);
			@SuppressWarnings("serial")
			JScrollPane jsp = new JScrollPane(jta) {
				@Override
				public Dimension getPreferredSize() {
					return new Dimension(460, 390);
				}
			};
			JOptionPane.showMessageDialog(null, jsp, "Information", JOptionPane.INFORMATION_MESSAGE);

		} catch (Exception jpe) {
			JOptionPane.showMessageDialog(null, "Exception in Info\nException: " + jpe.getMessage(),
					"Exception", JOptionPane.ERROR_MESSAGE);
			System.err.println("Jpos exception " + jpe);
		}
	}

	/**
	 * Shows statistics of device if they are supported by the device
	 */
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
			JOptionPane.showMessageDialog(null, ioe.getMessage());
			ioe.printStackTrace();
		} catch (SAXException saxe) {
			JOptionPane.showMessageDialog(null, saxe.getMessage());
			saxe.printStackTrace();
		} catch (ParserConfigurationException e1) {
			JOptionPane.showMessageDialog(null, e1.getMessage());
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
		reason.setValue(POSPowerConstantMapper.PWR_REASON_REQUEST.getConstant());
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
			JOptionPane.showMessageDialog(null, e1.getMessage());
			e1.printStackTrace();
		}
	}
}
