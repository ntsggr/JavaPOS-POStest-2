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

import jpos.Belt;
import jpos.JposException;

import org.apache.xerces.parsers.DOMParser;
import org.w3c.dom.Document;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

public class BeltController extends CommonController implements Initializable {

	@FXML
	public ComboBox<Boolean> autoStopBackward;
	@FXML
	public ComboBox<Boolean> autoStopForward;
	@FXML
	public ComboBox<Integer> moveBackward_speed;
	@FXML
	public ComboBox<Integer> moveForward_speed;
	@FXML
	public ComboBox<String> resetitemCount_direction;
	@FXML
	public ComboBox<String> adjustItemCount_direction;

	@FXML
	public TextField autoStopBackwardDelayTime;
	@FXML
	public TextField autoStopForwardDelayTime;
	@FXML
	public TextField adjustItemCount_Count;

	@FXML
	@RequiredState(JposState.ENABLED)
	public Pane functionPane;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		setUpTooltips();
		service = new Belt();
		setUpLogicalNameComboBox("Belt");
		RequiredStateChecker.invokeThis(this, service);
	}

	/* ************************************************************************
	 * ************************ Action Handler *********************************
	 * ***********************************************************************
	 */

	@FXML
	public void handleDeviceEnable(ActionEvent e) {
		try {
			if (deviceEnabled.isSelected()) {
				((Belt) service).setDeviceEnabled(true);
				setUpComboBoxes();

			} else {
				((Belt) service).setDeviceEnabled(false);
			}
		} catch (JposException je) {
			JOptionPane.showMessageDialog(null, je.getMessage());
		}
		RequiredStateChecker.invokeThis(this, service);
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

	@FXML
	public void handleAutoStopBackward(ActionEvent e) {
		if (autoStopBackward.getSelectionModel().getSelectedItem() != null) {
			try {
				((Belt) service).setAutoStopBackward(autoStopBackward.getSelectionModel().getSelectedItem());
			} catch (JposException e1) {
				JOptionPane.showMessageDialog(null, e1.getMessage());
			}
		}
	}

	@FXML
	public void handleAutoStopBackwardDelayTime(ActionEvent e) {
		if (!autoStopBackwardDelayTime.getText().isEmpty()) {
			try {
				((Belt) service).setAutoStopBackwardDelayTime(Integer.parseInt(autoStopBackwardDelayTime
						.getText()));
			} catch (NumberFormatException e1) {
				e1.printStackTrace();
				JOptionPane.showMessageDialog(null, e1.getMessage());
			} catch (JposException e1) {
				e1.printStackTrace();
				JOptionPane.showMessageDialog(null, e1.getMessage());
			}
		}
	}

	@FXML
	public void handleAutoStopForward(ActionEvent e) {
		if (autoStopForward.getSelectionModel().getSelectedItem() != null) {
			try {
				((Belt) service).setAutoStopForward(autoStopForward.getSelectionModel().getSelectedItem());
			} catch (JposException e1) {
				e1.printStackTrace();
				JOptionPane.showMessageDialog(null, e1.getMessage());
			}
		}
	}

	@FXML
	public void handleAutoStopForwardDelayTime(ActionEvent e) {
		if (!autoStopForwardDelayTime.getText().isEmpty()) {
			try {
				((Belt) service).setAutoStopForwardDelayTime(Integer.parseInt(autoStopForwardDelayTime
						.getText()));
			} catch (NumberFormatException e1) {
				e1.printStackTrace();
				JOptionPane.showMessageDialog(null, e1.getMessage());
			} catch (JposException e1) {
				e1.printStackTrace();
				JOptionPane.showMessageDialog(null, e1.getMessage());
			}
		}
	}

	@FXML
	public void handleAdjustItemCount(ActionEvent e) {
		if (adjustItemCount_direction.getSelectionModel().getSelectedItem() != null) {

			try {
				((Belt) service).adjustItemCount(BeltConstantMapper
						.getConstantNumberFromString(adjustItemCount_direction.getSelectionModel()
								.getSelectedItem()), Integer.parseInt(adjustItemCount_Count.getText()));
			} catch (NumberFormatException e1) {
				e1.printStackTrace();
				JOptionPane.showMessageDialog(null, e1.getMessage());
			} catch (JposException e1) {
				e1.printStackTrace();
				JOptionPane.showMessageDialog(null, e1.getMessage());
			}
		}
	}

	@FXML
	public void handleMoveBackward(ActionEvent e) {
		if (moveBackward_speed.getSelectionModel().getSelectedItem() != null) {
			try {
				((Belt) service).moveBackward(moveBackward_speed.getSelectionModel().getSelectedItem());
			} catch (JposException e1) {
				e1.printStackTrace();
				JOptionPane.showMessageDialog(null, e1.getMessage());
			}
		}
	}

	@FXML
	public void handleMoveForward(ActionEvent e) {
		if (moveForward_speed.getSelectionModel().getSelectedItem() != null) {
			try {
				((Belt) service).moveForward(moveForward_speed.getSelectionModel().getSelectedItem());
			} catch (JposException e1) {
				e1.printStackTrace();
				JOptionPane.showMessageDialog(null, e1.getMessage());
			}
		}
	}

	@FXML
	public void handleResetBelt(ActionEvent e) {
		try {
			((Belt) service).resetBelt();
		} catch (JposException e1) {
			e1.printStackTrace();
			JOptionPane.showMessageDialog(null, e1.getMessage());
		}
	}

	@FXML
	public void handleResetItemCount(ActionEvent e) {
		if (resetitemCount_direction.getSelectionModel().getSelectedItem() != null) {
			try {
				((Belt) service).resetItemCount(BeltConstantMapper
						.getConstantNumberFromString(resetitemCount_direction.getSelectionModel()
								.getSelectedItem()));
			} catch (JposException e1) {
				e1.printStackTrace();
				JOptionPane.showMessageDialog(null, e1.getMessage());
			}
		}
	}

	@FXML
	public void handleStopBelt(ActionEvent e) {
		try {
			((Belt) service).stopBelt();
		} catch (JposException e1) {
			e1.printStackTrace();
			JOptionPane.showMessageDialog(null, e1.getMessage());
		}
	}

	/**
	 * Shows statistics of device if they are supported by the device
	 */
	@Override
	@FXML
	public void handleInfo(ActionEvent e) {
		try {
			IMapWrapper bcm = new BeltConstantMapper();
			String msg = DeviceProperties.getProperties(service, bcm);
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
			((Belt) service).retrieveStatistics(stats);
			DOMParser parser = new DOMParser();
			parser.parse(new InputSource(new java.io.StringReader(stats[1])));

			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			DocumentBuilder db = dbf.newDocumentBuilder();
			Document doc = db.parse(new ByteArrayInputStream(stats[1].getBytes()));

			printStatistics(doc.getDocumentElement(), "");

			JOptionPane.showMessageDialog(null, statistics, "Statistics", JOptionPane.INFORMATION_MESSAGE);
		} catch (IOException ioe) {
			ioe.printStackTrace();
			JOptionPane.showMessageDialog(null, ioe.getMessage());
		} catch (SAXException saxe) {
			saxe.printStackTrace();
			JOptionPane.showMessageDialog(null, saxe.getMessage());
		} catch (ParserConfigurationException e1) {
			e1.printStackTrace();
			JOptionPane.showMessageDialog(null, e1.getMessage());
		} catch (JposException jpe) {
			jpe.printStackTrace();
			JOptionPane.showMessageDialog(null, "Statistics are not supported!", "Statistics",
					JOptionPane.ERROR_MESSAGE);
		}

		statistics = "";
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

	private void setUpComboBoxes() {
		setUpAutoStopBackward();
		setUpAutoStopForward();
		setUpAdjustItemCountDirection();
		setUpMoveBackwardSpeed();
		setUpMoveForwardSpeed();
		setUpResetItemCount();

	}

}
