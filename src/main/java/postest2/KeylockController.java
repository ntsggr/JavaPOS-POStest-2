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
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;

import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import jpos.JposException;
import jpos.Keylock;

import org.apache.xerces.parsers.DOMParser;
import org.w3c.dom.Document;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

public class KeylockController extends CommonController implements Initializable {

	@FXML
	@RequiredState(JposState.ENABLED)
	public Pane functionPane;

	@FXML
	public ComboBox<String> waitForKeylockChange_keyPosition;
	@FXML
	public TextField waitForKeylockChange_timeout;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		service = new Keylock();
		RequiredStateChecker.invokeThis(this, service);
		setUpLogicalNameComboBox("Keylock");
	}

	/* ************************************************************************
	 * ************************ Action Handler *********************************
	 * ***********************************************************************
	 */

	@FXML
	public void handleDeviceEnable(ActionEvent e) {
		try {
			if (deviceEnabled.isSelected()) {
				((Keylock) service).setDeviceEnabled(true);
				setUpComboBoxes();
			} else {
				((Keylock) service).setDeviceEnabled(false);
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
			IMapWrapper kcm = new KeylockConstantMapper();
			String msg = DeviceProperties.getProperties(service, kcm);
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
			JOptionPane.showMessageDialog(null, "Exception in Info\nException: " + jpe.getMessage(), "Exception",
					JOptionPane.ERROR_MESSAGE);
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
			((Keylock) service).retrieveStatistics(stats);
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

	@FXML
	public void handleWaitForKeylockChange(ActionEvent e) {

		if (waitForKeylockChange_timeout.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Field should have a value!");
		} else {
			try {
				((Keylock) service).waitForKeylockChange(KeylockConstantMapper.getConstantNumberFromString(
						waitForKeylockChange_keyPosition.getSelectionModel().getSelectedItem()), 
						Integer.parseInt(waitForKeylockChange_timeout.getText()));
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
	 * Set up ComboBoxes
	 */
	
	private void setUpWaitForKeylockChangeKeyPosition(){
		waitForKeylockChange_keyPosition.getItems().clear();
		waitForKeylockChange_keyPosition.getItems().add(KeylockConstantMapper.LOCK_KP_ANY.getConstant());
		waitForKeylockChange_keyPosition.getItems().add(KeylockConstantMapper.LOCK_KP_ELECTRONIC.getConstant());
		waitForKeylockChange_keyPosition.getItems().add(KeylockConstantMapper.LOCK_KP_LOCK.getConstant());
		waitForKeylockChange_keyPosition.getItems().add(KeylockConstantMapper.LOCK_KP_NORM.getConstant());
		waitForKeylockChange_keyPosition.getItems().add(KeylockConstantMapper.LOCK_KP_SUPR.getConstant());
		waitForKeylockChange_keyPosition.setValue(KeylockConstantMapper.LOCK_KP_ANY.getConstant());
	}
	
	private void setUpComboBoxes(){
		setUpWaitForKeylockChangeKeyPosition();
	}

}