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
import jpos.Lights;

import org.apache.xerces.parsers.DOMParser;
import org.w3c.dom.Document;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

public class LightsController extends CommonController implements Initializable {

	@FXML
	@RequiredState(JposState.ENABLED)
	public Pane functionPane;

	@FXML
	public TextField switchOn_blinkOnCycle;
	@FXML
	public TextField switchOn_blinkOffCycle;

	@FXML
	public ComboBox<Integer> switchOn_lightNumber;
	@FXML
	public ComboBox<String> switchOn_color;
	@FXML
	public ComboBox<String> switchOn_alarm;
	@FXML
	public ComboBox<Integer> switchOff_lightNumber;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		setUpTooltips();
		service = new Lights();
		RequiredStateChecker.invokeThis(this, service);
		setUpLogicalNameComboBox("Lights");
	}

	/* ************************************************************************
	 * ************************ Action Handler *********************************
	 * ***********************************************************************
	 */

	@FXML
	public void handleDeviceEnable(ActionEvent e) {
		try {
			if (deviceEnabled.isSelected()) {
				((Lights) service).setDeviceEnabled(true);
				setUpComboBoxes();
			} else {
				((Lights) service).setDeviceEnabled(false);
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
		try {
			if(getDeviceState(service) == JposState.CLAIMED){
				deviceEnabled.setSelected(true);
				handleDeviceEnable(e);
			}
		} catch (JposException e1) {
			e1.printStackTrace();
		}
	}

	/**
	 * Shows statistics of device if they are supported by the device
	 */
	@Override
	@FXML
	public void handleInfo(ActionEvent e) {
		try {
			IMapWrapper lcm = new LightsConstantMapper();
			String msg = DeviceProperties.getProperties(service, lcm);
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
			((Lights) service).retrieveStatistics(stats);
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
	public void handleSwitchOn(ActionEvent e) {
		if (switchOn_blinkOffCycle.getText().isEmpty() || switchOn_blinkOnCycle.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Every Field should have a value!");
		} else {
			try {
				((Lights) service).switchOn(switchOn_lightNumber.getSelectionModel().getSelectedItem(), Integer
						.parseInt(switchOn_blinkOnCycle.getText()), Integer.parseInt(switchOn_blinkOnCycle.getText()),
						LightsConstantMapper.getConstantNumberFromString(switchOn_color.getSelectionModel()
								.getSelectedItem()), LightsConstantMapper.getConstantNumberFromString(switchOn_alarm
								.getSelectionModel().getSelectedItem()));
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
	public void handleSwitchOff(ActionEvent e) {
		try {
			((Lights) service).switchOff(switchOff_lightNumber.getSelectionModel().getSelectedItem());
		} catch (JposException e1) {
			JOptionPane.showMessageDialog(null, e1.getMessage());
			e1.printStackTrace();
		}
	}

	/*
	 * SetUpComboBoxes
	 */

	private void setUpSwitchOnLightNumber() {
		try {
			switchOn_lightNumber.getItems().clear();
			for (int i = 1; i <= ((Lights) service).getMaxLights(); i++) {
				switchOn_lightNumber.getItems().add(i);
			}
			switchOn_lightNumber.setValue(1);
		} catch (JposException e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
			e.printStackTrace();
		}
	}

	private void setUpSwitchOnColor() {
		switchOn_color.getItems().clear();
		switchOn_color.getItems().add(LightsConstantMapper.LGT_COLOR_PRIMARY.getConstant());
		switchOn_color.getItems().add(LightsConstantMapper.LGT_COLOR_CUSTOM1.getConstant());
		switchOn_color.getItems().add(LightsConstantMapper.LGT_COLOR_CUSTOM2.getConstant());
		switchOn_color.getItems().add(LightsConstantMapper.LGT_COLOR_CUSTOM3.getConstant());
		switchOn_color.getItems().add(LightsConstantMapper.LGT_COLOR_CUSTOM4.getConstant());
		switchOn_color.getItems().add(LightsConstantMapper.LGT_COLOR_CUSTOM5.getConstant());
		switchOn_color.setValue(LightsConstantMapper.LGT_COLOR_PRIMARY.getConstant());
	}

	private void setUpSwitchOnAlarm() {
		switchOn_alarm.getItems().clear();
		switchOn_alarm.getItems().add(LightsConstantMapper.LGT_ALARM_NOALARM.getConstant());
		switchOn_alarm.getItems().add(LightsConstantMapper.LGT_ALARM_SLOW.getConstant());
		switchOn_alarm.getItems().add(LightsConstantMapper.LGT_ALARM_MEDIUM.getConstant());
		switchOn_alarm.getItems().add(LightsConstantMapper.LGT_ALARM_FAST.getConstant());
		switchOn_alarm.getItems().add(LightsConstantMapper.LGT_ALARM_CUSTOM1.getConstant());
		switchOn_alarm.getItems().add(LightsConstantMapper.LGT_ALARM_CUSTOM2.getConstant());
		switchOn_alarm.setValue(LightsConstantMapper.LGT_ALARM_NOALARM.getConstant());
	}

	private void setUpSwitchOffLightNumber() {
		try {
			switchOff_lightNumber.getItems().clear();
			for (int i = 1; i <= ((Lights) service).getMaxLights(); i++) {
				switchOff_lightNumber.getItems().add(i);
			}
			switchOff_lightNumber.setValue(1);
		} catch (JposException e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
			e.printStackTrace();
		}
	}

	private void setUpComboBoxes() {
		setUpSwitchOffLightNumber();
		setUpSwitchOnAlarm();
		setUpSwitchOnColor();
		setUpSwitchOnLightNumber();
	}

}