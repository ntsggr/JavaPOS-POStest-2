package postest2;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;

import javax.swing.JOptionPane;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import jpos.JposConst;
import jpos.JposException;
import jpos.Scale;

import org.apache.xerces.parsers.DOMParser;
import org.w3c.dom.Document;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

public class ScaleController extends CommonController implements Initializable {

	@FXML @RequiredState(JposState.ENABLED)
	public Pane functionPane;
	
	 @FXML @RequiredState(JposState.OPENED)
	 public CheckBox asyncMode;
	
	 @FXML public TextField tareWeight;
	 @FXML public TextField unitPrice;
	 @FXML public TextField displayText_data;
	 @FXML public TextField readWeight_weightData;
	 @FXML public TextField readWeight_timeout;
	 
	 @FXML public ComboBox<String> statusNotify;
	 @FXML public ComboBox<Boolean> zeroValid;
	
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		service = new Scale();
		RequiredStateChecker.invokeThis(this, service);
		setUpLogicalNameComboBox("Scale");
	}

	/* ************************************************************************
	 * ************************ Action Handler *********************************
	 * ***********************************************************************
	 */

	@FXML
	public void handleDeviceEnable(ActionEvent e) {
		try {
			if (deviceEnabled.isSelected()) {
				((Scale) service).setDeviceEnabled(true);
				setUpComboBoxes();
			} else {
				((Scale) service).setDeviceEnabled(false);
			}
			RequiredStateChecker.invokeThis(this, service);
		} catch (JposException je) {
			System.err.println("ScalePanel: CheckBoxListener: Jpos Exception" + je);
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
			String ver = new Integer(((Scale) service).getDeviceServiceVersion()).toString();
			String msg = "Service Description: " + ((Scale) service).getDeviceServiceDescription();
			msg = msg + "\nService Version: v" + new Integer(ver.substring(0, 1)) + "."
					+ new Integer(ver.substring(1, 4)) + "." + new Integer(ver.substring(4, 7));
			ver = new Integer(((Scale) service).getDeviceControlVersion()).toString();
			msg += "\n\nControl Description: " + ((Scale) service).getDeviceControlDescription();
			msg += "\nControl Version: v" + new Integer(ver.substring(0, 1)) + "."
					+ new Integer(ver.substring(1, 4)) + "." + new Integer(ver.substring(4, 7));
			msg += "\n\nPhysical Device Name: " + ((Scale) service).getPhysicalDeviceName();
			msg += "\nPhysical Device Description: " + ((Scale) service).getPhysicalDeviceDescription();

			msg += "\n\nProperties:\n------------------------";

			msg += "\nCapStatisticsReporting: " + (((Scale) service).getCapStatisticsReporting());

			msg += "\nCapUpdateFirmware: " + (((Scale) service).getCapUpdateFirmware());

			msg += "\nCapCompareFirmwareVersion: " + (((Scale) service).getCapCompareFirmwareVersion());

			msg += "\nCapPowerReporting: "
					+ (((Scale) service).getCapPowerReporting() == JposConst.JPOS_PR_ADVANCED ? "Advanced"
							: (((Scale) service).getCapPowerReporting() == JposConst.JPOS_PR_STANDARD ? "Standard"
									: "None"));

			msg = msg + "\nCapDisplay: " + ((Scale) service).getCapDisplay();
			msg = msg + "\nCapDisplayText: " + ((Scale) service).getCapDisplayText();
			msg = msg + "\nCapPriceCalculating: " + ((Scale) service).getCapPriceCalculating();
			msg = msg + "\nCapStatusUpdate: " + ((Scale) service).getCapStatusUpdate();
			msg = msg + "\nCapTareWeight: " + ((Scale) service).getCapTareWeight();
			msg = msg + "\nCapZeroScale: " + ((Scale) service).getCapZeroScale();

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
			((Scale) service).retrieveStatistics(stats);
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
	
	@FXML
	public void handleAsyncMode(ActionEvent e) {
		try {
			((Scale) service).setAsyncMode(asyncMode.selectedProperty().getValue());
		} catch (JposException e1) {
			JOptionPane.showMessageDialog(null, e1.getMessage());
			e1.printStackTrace();
		}
	}
	
	@FXML
	public void handleSetStatusNotify(ActionEvent e) {
		try {
			((Scale)service).setStatusNotify(ScaleConstantMapper.getConstantNumberFromString(statusNotify.getSelectionModel().getSelectedItem()));
		} catch (JposException e1) {
			JOptionPane.showMessageDialog(null, e1.getMessage());
			e1.printStackTrace();
		}
	}
	
	@FXML
	public void handleSetTareWeight(ActionEvent e) {
		if(tareWeight.getText().isEmpty()){
			JOptionPane.showMessageDialog(null, "Parameter is not specified");
		} else {
			try {
				((Scale)service).setTareWeight(Integer.parseInt(tareWeight.getText()));
			} catch (NumberFormatException e1) {
				JOptionPane.showMessageDialog(null, e1.getMessage());
				e1.printStackTrace();
			} catch (JposException e1) {
				JOptionPane.showMessageDialog(null, e1.getMessage());
				e1.printStackTrace();
			}
		}
	}
	
	@FXML
	public void handleSetUnitPrice(ActionEvent e) {
		if(unitPrice.getText().isEmpty()){
			JOptionPane.showMessageDialog(null, "Parameter is not specified");
		} else {
			try {
				((Scale)service).setUnitPrice(Long.parseLong(unitPrice.getText()));
			} catch (NumberFormatException e1) {
				JOptionPane.showMessageDialog(null, e1.getMessage());
				e1.printStackTrace();
			} catch (JposException e1) {
				JOptionPane.showMessageDialog(null, e1.getMessage());
				e1.printStackTrace();
			}
		}
	}
	
	@FXML
	public void handleSetZeroValid(ActionEvent e) {
		try {
			((Scale)service).setZeroValid(zeroValid.getSelectionModel().getSelectedItem());
		} catch (JposException e1) {
			JOptionPane.showMessageDialog(null, e1.getMessage());
			e1.printStackTrace();
		}
	}
	
	@FXML
	public void handleDisplayText(ActionEvent e) {
		if(displayText_data.getText().isEmpty()){
			JOptionPane.showMessageDialog(null, "Parameter is not specified");
		} else {
			try {
				((Scale)service).displayText(displayText_data.getText());
			} catch (JposException e1) {
				JOptionPane.showMessageDialog(null, e1.getMessage());
				e1.printStackTrace();
			}
		}
	}
	
	@FXML
	public void handleReadWeight(ActionEvent e) {
		if(readWeight_timeout.getText().isEmpty()){
			JOptionPane.showMessageDialog(null, "Parameter is not specified");
		} else {
			int[] weightData = new int[1];
			try {
				((Scale)service).readWeight(weightData, Integer.parseInt(readWeight_timeout.getText()));
				readWeight_weightData.setText("" + weightData[0]);
			} catch (JposException e1) {
				JOptionPane.showMessageDialog(null, e1.getMessage());
				e1.printStackTrace();
			} catch (NumberFormatException e1) {
				JOptionPane.showMessageDialog(null, e1.getMessage());
				e1.printStackTrace();
			}
		}
	}
	
	@FXML
	public void handleZeroScale(ActionEvent e) {
		try {
			((Scale)service).zeroScale();
		} catch (JposException e1) {
			JOptionPane.showMessageDialog(null, e1.getMessage());
			e1.printStackTrace();
		}
	}
	
	/*
	 * Set up ComboBoxes
	 */
	
	private void setUpStatusNotify(){
		statusNotify.getItems().clear();
		statusNotify.getItems().add(ScaleConstantMapper.SCAL_SN_DISABLED.getConstant());
		statusNotify.getItems().add(ScaleConstantMapper.SCAL_SN_ENABLED.getConstant());
		statusNotify.setValue(ScaleConstantMapper.SCAL_SN_DISABLED.getConstant());
	}

	private void setUpZeroValid(){
		zeroValid.getItems().clear();
		zeroValid.getItems().add(true);
		zeroValid.getItems().add(false);
		zeroValid.setValue(true);
	}

	private void setUpComboBoxes(){
		setUpZeroValid();
		setUpStatusNotify();
	}
	

}