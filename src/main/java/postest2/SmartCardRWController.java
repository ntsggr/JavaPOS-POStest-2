package postest2;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;

import javax.swing.JOptionPane;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import jpos.JposConst;
import jpos.JposException;
import jpos.SmartCardRW;

import org.apache.xerces.parsers.DOMParser;
import org.w3c.dom.Document;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

public class SmartCardRWController extends CommonController implements Initializable {

	@FXML @RequiredState(JposState.ENABLED)
	public Pane functionPane;
	
	@FXML public TextField SCSlot;
	@FXML public TextField readData_count;
	@FXML public TextField readData_data;
	@FXML public TextField writeData_count;
	@FXML public TextField writeData_data;
	
	@FXML public ComboBox<String> interfaceMode;
	@FXML public ComboBox<String> readData_action;
	@FXML public ComboBox<String> writeData_action;
	
	
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		service = new SmartCardRW();
		RequiredStateChecker.invokeThis(this, service);
		setUpLogicalNameComboBox("SmartCardRW");
	}

	/* ************************************************************************
	 * ************************ Action Handler *********************************
	 * ***********************************************************************
	 */

	@FXML
	public void handleDeviceEnable(ActionEvent e) {
		try {
			if (deviceEnabled.isSelected()) {
				((SmartCardRW) service).setDeviceEnabled(true);
				setUpComboBoxes();
			} else {
				((SmartCardRW) service).setDeviceEnabled(false);
			}
			RequiredStateChecker.invokeThis(this, service);
		} catch (JposException je) {
			System.err.println("SmartCardRWPanel: CheckBoxListener: Jpos Exception" + je);
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
			String ver = new Integer(((SmartCardRW) service).getDeviceServiceVersion()).toString();
			String msg = "Service Description: " + ((SmartCardRW) service).getDeviceServiceDescription();
			msg = msg + "\nService Version: v" + new Integer(ver.substring(0, 1)) + "."
					+ new Integer(ver.substring(1, 4)) + "." + new Integer(ver.substring(4, 7));
			ver = new Integer(((SmartCardRW) service).getDeviceControlVersion()).toString();
			msg += "\n\nControl Description: " + ((SmartCardRW) service).getDeviceControlDescription();
			msg += "\nControl Version: v" + new Integer(ver.substring(0, 1)) + "."
					+ new Integer(ver.substring(1, 4)) + "." + new Integer(ver.substring(4, 7));
			msg += "\n\nPhysical Device Name: " + ((SmartCardRW) service).getPhysicalDeviceName();
			msg += "\nPhysical Device Description: " + ((SmartCardRW) service).getPhysicalDeviceDescription();

			msg += "\n\nProperties:\n------------------------";

			msg += "\nCapStatisticsReporting: " + (((SmartCardRW) service).getCapStatisticsReporting());

			msg += "\nCapUpdateFirmware: " + (((SmartCardRW) service).getCapUpdateFirmware());

			msg += "\nCapCompareFirmwareVersion: " + (((SmartCardRW) service).getCapCompareFirmwareVersion());

			msg += "\nCapPowerReporting: "
					+ (((SmartCardRW) service).getCapPowerReporting() == JposConst.JPOS_PR_ADVANCED ? "Advanced"
							: (((SmartCardRW) service).getCapPowerReporting() == JposConst.JPOS_PR_STANDARD ? "Standard"
									: "None"));

			msg = msg + "\nCapInterfaceMode: " + ((SmartCardRW) service).getCapInterfaceMode();
			msg = msg + "\nCapIsoEmvMode: " + ((SmartCardRW) service).getCapIsoEmvMode();
			msg = msg + "\nCapSCPresentSensor: " + ((SmartCardRW) service).getCapSCPresentSensor();
			msg = msg + "\nCapSCSlots: " + ((SmartCardRW) service).getCapSCSlots();
			msg = msg + "\nCapTransmissionProtocol: " + ((SmartCardRW) service).getCapTransmissionProtocol();
			msg = msg + "\nCapCardErrorDetection: " + ((SmartCardRW) service).getCapCardErrorDetection();

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
			((SmartCardRW) service).retrieveStatistics(stats);
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
	public void handleBeginInsertion(ActionEvent e) {
		try {
			((SmartCardRW) service).beginInsertion(0);
		} catch (JposException e1) {
			JOptionPane.showMessageDialog(null, e1.getMessage());
			e1.printStackTrace();
		}
	}

	@FXML
	public void handleEndInsertion(ActionEvent e) {
		try {
			((SmartCardRW) service).endInsertion();
		} catch (JposException e1) {
			JOptionPane.showMessageDialog(null, e1.getMessage());
			e1.printStackTrace();
		}
	}

	@FXML
	public void handleBeginRemoval(ActionEvent e) {
		try {
			((SmartCardRW) service).beginRemoval(0);
		} catch (JposException e1) {
			JOptionPane.showMessageDialog(null, e1.getMessage());
			e1.printStackTrace();
		}
	}

	@FXML
	public void handleEndRemoval(ActionEvent e) {
		try {
			((SmartCardRW) service).endRemoval();
		} catch (JposException e1) {
			JOptionPane.showMessageDialog(null, e1.getMessage());
			e1.printStackTrace();
		}
	}

	@FXML
	public void handleSetInterfaceMode(ActionEvent e) {
		try {
			((SmartCardRW)service).setInterfaceMode(SmartCardRWConstantMapper.getConstantNumberFromString(interfaceMode.getSelectionModel().getSelectedItem()));
		} catch (JposException e1) {
			JOptionPane.showMessageDialog(null, e1.getMessage());
			e1.printStackTrace();
		}
	}

	@FXML
	public void handleSetSCSlot(ActionEvent e) {
		if(SCSlot.getText().isEmpty()){
			JOptionPane.showMessageDialog(null, "Parameter is not specified");
		} else {
			try {
				((SmartCardRW)service).setSCSlot(Integer.parseInt(SCSlot.getText()));
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
	public void handleReadData(ActionEvent e) {
		if(readData_count.getText().isEmpty() || readData_data.getText().isEmpty()){
			JOptionPane.showMessageDialog(null, "One of the parameters are not specified");
		} else {
			try {
				int[] count = new int[1];
				count[0] = Integer.parseInt(readData_count.getText());
				String[] data = new String[1];
				data[0] = readData_data.getText();
				((SmartCardRW)service).readData(SmartCardRWConstantMapper.getConstantNumberFromString(
						readData_action.getSelectionModel().getSelectedItem()), count, data);
				readData_count.setText("" + count[0]);
				readData_data.setText(data[0]);
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
	public void handleWriteData(ActionEvent e) {
		if(writeData_count.getText().isEmpty() || writeData_data.getText().isEmpty()){
			JOptionPane.showMessageDialog(null, "One of the parameters are not specified");
		} else {
			try {
				((SmartCardRW)service).writeData(SmartCardRWConstantMapper.getConstantNumberFromString(
						writeData_action.getSelectionModel().getSelectedItem()), Integer.parseInt(writeData_count.getText()), writeData_data.getText());
			} catch (NumberFormatException e1) {
				JOptionPane.showMessageDialog(null, e1.getMessage());
				e1.printStackTrace();
			} catch (JposException e1) {
				JOptionPane.showMessageDialog(null, e1.getMessage());
				e1.printStackTrace();
			}
		}
	}
	
	
	/*
	 * Set Up ComboBoxes
	 */
	
	private void setUpInterfaceMode(){
		interfaceMode.getItems().clear();
		interfaceMode.getItems().add(SmartCardRWConstantMapper.SC_MODE_APDU.getConstant());
		interfaceMode.getItems().add(SmartCardRWConstantMapper.SC_MODE_BLOCK.getConstant());
		interfaceMode.getItems().add(SmartCardRWConstantMapper.SC_MODE_TRANS.getConstant());
		interfaceMode.getItems().add(SmartCardRWConstantMapper.SC_MODE_XML.getConstant());
		interfaceMode.setValue(SmartCardRWConstantMapper.SC_MODE_APDU.getConstant());
	}
	
	private void setUpReadDataAction(){
		readData_action.getItems().clear();
		readData_action.getItems().add(SmartCardRWConstantMapper.SC_READ_DATA.getConstant());
		readData_action.getItems().add(SmartCardRWConstantMapper.SC_READ_PROGRAM.getConstant());
		readData_action.getItems().add(SmartCardRWConstantMapper.SC_EXECUTE_AND_READ_DATA.getConstant());
		readData_action.getItems().add(SmartCardRWConstantMapper.SC_XML_READ_BLOCK_DATA.getConstant());
		readData_action.setValue(SmartCardRWConstantMapper.SC_READ_DATA.getConstant());
	}
	
	private void setUpWriteDataAction(){
		writeData_action.getItems().clear();
		writeData_action.getItems().add(SmartCardRWConstantMapper.SC_STORE_DATA.getConstant());
		writeData_action.getItems().add(SmartCardRWConstantMapper.SC_STORE_PROGRAM.getConstant());
		writeData_action.getItems().add(SmartCardRWConstantMapper.SC_EXECUTE_DATA.getConstant());
		writeData_action.getItems().add(SmartCardRWConstantMapper.SC_XML_BLOCK_DATA.getConstant());
		writeData_action.getItems().add(SmartCardRWConstantMapper.SC_SECURITY_FUSE.getConstant());
		writeData_action.getItems().add(SmartCardRWConstantMapper.SC_RESET.getConstant());
		writeData_action.setValue(SmartCardRWConstantMapper.SC_STORE_DATA.getConstant());
	}
	
	private void setUpComboBoxes(){
		setUpInterfaceMode();
		setUpReadDataAction();
		setUpWriteDataAction();
	}


}
