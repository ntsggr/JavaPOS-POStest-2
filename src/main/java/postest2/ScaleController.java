package postest2;

import java.awt.Dimension;
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
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import jpos.JposException;
import jpos.Scale;

import org.apache.xerces.parsers.DOMParser;
import org.w3c.dom.Document;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

public class ScaleController extends CommonController implements Initializable {

	@FXML
	@RequiredState(JposState.ENABLED)
	public Pane functionPane;

	@FXML
	@RequiredState(JposState.OPENED)
	public CheckBox asyncMode;

	@FXML
	public TextField tareWeight;
	@FXML
	public TextField unitPrice;
	@FXML
	public TextField displayText_data;
	@FXML
	public TextField readWeight_weightData;
	@FXML
	public TextField readWeight_timeout;

	@FXML
	public ComboBox<String> statusNotify;
	@FXML
	public ComboBox<Boolean> zeroValid;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		setUpTooltips();
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
			IMapWrapper scm = new ScaleConstantMapper();
			String msg = DeviceProperties.getProperties(service, scm);
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
			((Scale) service).retrieveStatistics(stats);
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
			((Scale) service).setStatusNotify(ScaleConstantMapper.getConstantNumberFromString(statusNotify
					.getSelectionModel().getSelectedItem()));
		} catch (JposException e1) {
			JOptionPane.showMessageDialog(null, e1.getMessage());
			e1.printStackTrace();
		}
	}

	@FXML
	public void handleSetTareWeight(ActionEvent e) {
		if (tareWeight.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Parameter is not specified");
		} else {
			try {
				((Scale) service).setTareWeight(Integer.parseInt(tareWeight.getText()));
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
		if (unitPrice.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Parameter is not specified");
		} else {
			try {
				((Scale) service).setUnitPrice(Long.parseLong(unitPrice.getText()));
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
			((Scale) service).setZeroValid(zeroValid.getSelectionModel().getSelectedItem());
		} catch (JposException e1) {
			JOptionPane.showMessageDialog(null, e1.getMessage());
			e1.printStackTrace();
		}
	}

	@FXML
	public void handleDisplayText(ActionEvent e) {
		if (displayText_data.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Parameter is not specified");
		} else {
			try {
				((Scale) service).displayText(displayText_data.getText());
			} catch (JposException e1) {
				JOptionPane.showMessageDialog(null, e1.getMessage());
				e1.printStackTrace();
			}
		}
	}

	@FXML
	public void handleReadWeight(ActionEvent e) {
		if (readWeight_timeout.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Parameter is not specified");
		} else {
			int[] weightData = new int[1];
			try {
				((Scale) service).readWeight(weightData, Integer.parseInt(readWeight_timeout.getText()));
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
			((Scale) service).zeroScale();
		} catch (JposException e1) {
			JOptionPane.showMessageDialog(null, e1.getMessage());
			e1.printStackTrace();
		}
	}

	/*
	 * Set up ComboBoxes
	 */

	private void setUpStatusNotify() {
		statusNotify.getItems().clear();
		statusNotify.getItems().add(ScaleConstantMapper.SCAL_SN_DISABLED.getConstant());
		statusNotify.getItems().add(ScaleConstantMapper.SCAL_SN_ENABLED.getConstant());
		statusNotify.setValue(ScaleConstantMapper.SCAL_SN_DISABLED.getConstant());
	}

	private void setUpZeroValid() {
		zeroValid.getItems().clear();
		zeroValid.getItems().add(true);
		zeroValid.getItems().add(false);
		zeroValid.setValue(true);
	}

	private void setUpComboBoxes() {
		setUpZeroValid();
		setUpStatusNotify();
	}

}