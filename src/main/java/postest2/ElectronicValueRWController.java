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
import javafx.scene.control.TabPane;
import javafx.scene.control.TextField;

import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import jpos.ElectronicValueRW;
import jpos.JposException;

import org.apache.xerces.parsers.DOMParser;
import org.w3c.dom.Document;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

public class ElectronicValueRWController extends CommonController implements Initializable {

	@FXML
	@RequiredState(JposState.ENABLED)
	public TabPane functionPane;

	@FXML
	@RequiredState(JposState.OPENED)
	public CheckBox asyncMode;

	@FXML
	public TextField additionalSecurityInformation;
	@FXML
	public TextField amount;
	@FXML
	public TextField approvalCode;
	@FXML
	public TextField currentService;
	@FXML
	public TextField mediumID;
	@FXML
	public TextField point;
	@FXML
	public TextField voucherID;
	@FXML
	public TextField voucherIDList;
	@FXML
	public TextField value_sequenceNumber;
	@FXML
	public TextField value_timeout;
	@FXML
	public TextField accessLog_sequenceNumber;
	@FXML
	public TextField accessLog_timeout;
	@FXML
	public TextField activateService_data;
	@FXML
	public TextField activateService_obj;
	@FXML
	public TextField beginDetection_timeout;
	@FXML
	public TextField updateKey_data;
	@FXML
	public TextField updateKey_obj;

	@FXML
	public ComboBox<Boolean> detectionControl;
	@FXML
	public ComboBox<String> accessLog_type;
	@FXML
	public ComboBox<String> beginDetection_type;
	@FXML
	public ComboBox<String> transactionAccess_control;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		service = new ElectronicValueRW();
		RequiredStateChecker.invokeThis(this, service);
		setUpLogicalNameComboBox("ElectronicValueRW");
	}

	/* ************************************************************************
	 * ************************ Action Handler *********************************
	 * ***********************************************************************
	 */

	@FXML
	public void handleDeviceEnable(ActionEvent e) {
		try {
			if (deviceEnabled.isSelected()) {
				((ElectronicValueRW) service).setDeviceEnabled(true);
				setUpComboBoxes();
			} else {
				((ElectronicValueRW) service).setDeviceEnabled(false);
			}
		} catch (JposException je) {
			System.err.println("ElectronicValueRWPanel: CheckBoxListener: Jpos Exception" + je);
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
			IMapWrapper evrwcm = new ElectronicValueRWConstantMapper();
			String msg = DeviceProperties.getProperties(service, evrwcm);
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

	// Shows statistics of device if they are supported by the device
	@Override
	@FXML
	public void handleStatistics(ActionEvent e) {
		String[] stats = new String[] { "", "U_", "M_" };
		try {
			((ElectronicValueRW) service).retrieveStatistics(stats);
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
		// System.out.println("asyncMode");
		try {
			((ElectronicValueRW) service).setAsyncMode(asyncMode.isSelected());
		} catch (JposException je) {
			JOptionPane.showMessageDialog(null, je.getMessage());
			je.printStackTrace();
		}
	}

	@FXML
	public void handleSetAdditionalSecurityInformation(ActionEvent e) {
		if (additionalSecurityInformation.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Field should have a value!");
		} else {
			try {
				((ElectronicValueRW) service).setAdditionalSecurityInformation(additionalSecurityInformation
						.getText());
			} catch (JposException je) {
				JOptionPane.showMessageDialog(null, je.getMessage());
				je.printStackTrace();
			}
		}
	}

	@FXML
	public void handleSetAmount(ActionEvent e) {
		if (amount.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Field should have a value!");
		} else {
			try {
				((ElectronicValueRW) service).setAmount(Long.parseLong(amount.getText()));
			} catch (JposException je) {
				JOptionPane.showMessageDialog(null, je.getMessage());
				je.printStackTrace();
			} catch (NumberFormatException e1) {
				JOptionPane.showMessageDialog(null, e1.getMessage());
				e1.printStackTrace();
			}
		}
	}

	@FXML
	public void handleSetApprovalCode(ActionEvent e) {
		if (approvalCode.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Field should have a value!");
		} else {
			try {
				((ElectronicValueRW) service).setApprovalCode(approvalCode.getText());
			} catch (JposException je) {
				JOptionPane.showMessageDialog(null, je.getMessage());
				je.printStackTrace();
			}
		}
	}

	@FXML
	public void handleSetCurrentService(ActionEvent e) {
		if (currentService.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Field should have a value!");
		} else {
			try {
				((ElectronicValueRW) service).setCurrentService(currentService.getText());
			} catch (JposException je) {
				JOptionPane.showMessageDialog(null, je.getMessage());
				je.printStackTrace();
			}
		}
	}

	@FXML
	public void handleSetDetectionControl(ActionEvent e) {
		try {
			((ElectronicValueRW) service).setDetectionControl(detectionControl.getSelectionModel()
					.getSelectedItem());
		} catch (JposException je) {
			JOptionPane.showMessageDialog(null, je.getMessage());
			je.printStackTrace();
		}
	}

	@FXML
	public void handleSetMediumID(ActionEvent e) {
		if (mediumID.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Field should have a value!");
		} else {
			try {
				((ElectronicValueRW) service).setMediumID(mediumID.getText());
			} catch (JposException je) {
				JOptionPane.showMessageDialog(null, je.getMessage());
				je.printStackTrace();
			}
		}
	}

	@FXML
	public void handleSetPoint(ActionEvent e) {
		if (point.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Field should have a value!");
		} else {
			try {
				((ElectronicValueRW) service).setPoint(Long.parseLong(point.getText()));
			} catch (JposException je) {
				JOptionPane.showMessageDialog(null, je.getMessage());
				je.printStackTrace();
			} catch (NumberFormatException e1) {
				JOptionPane.showMessageDialog(null, e1.getMessage());
				e1.printStackTrace();
			}
		}
	}

	@FXML
	public void handleSetVoucherID(ActionEvent e) {
		if (voucherID.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Field should have a value!");
		} else {
			try {
				((ElectronicValueRW) service).setVoucherID(voucherID.getText());
			} catch (JposException je) {
				JOptionPane.showMessageDialog(null, je.getMessage());
				je.printStackTrace();
			}
		}
	}

	@FXML
	public void handleSetVoucherIDList(ActionEvent e) {
		if (voucherIDList.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Field should have a value!");
		} else {
			try {
				((ElectronicValueRW) service).setVoucherIDList(voucherIDList.getText());
			} catch (JposException je) {
				JOptionPane.showMessageDialog(null, je.getMessage());
				je.printStackTrace();
			}
		}
	}

	@FXML
	public void handleAddValue(ActionEvent e) {
		if (value_sequenceNumber.getText().isEmpty() || value_timeout.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Every Field should have a value!");
		} else {
			try {
				((ElectronicValueRW) service).addValue(Integer.parseInt(value_sequenceNumber.getText()),
						Integer.parseInt(value_timeout.getText()));
			} catch (JposException je) {
				JOptionPane.showMessageDialog(null, je.getMessage());
				je.printStackTrace();
			} catch (NumberFormatException e1) {
				JOptionPane.showMessageDialog(null, e1.getMessage());
				e1.printStackTrace();
			}
		}
	}

	@FXML
	public void handleCancelValue(ActionEvent e) {
		if (value_sequenceNumber.getText().isEmpty() || value_timeout.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Every Field should have a value!");
		} else {
			try {
				((ElectronicValueRW) service).cancelValue(Integer.parseInt(value_sequenceNumber.getText()),
						Integer.parseInt(value_timeout.getText()));
			} catch (JposException je) {
				JOptionPane.showMessageDialog(null, je.getMessage());
				je.printStackTrace();
			} catch (NumberFormatException e1) {
				JOptionPane.showMessageDialog(null, e1.getMessage());
				e1.printStackTrace();
			}
		}
	}

	@FXML
	public void handleReadValue(ActionEvent e) {
		if (value_sequenceNumber.getText().isEmpty() || value_timeout.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Every Field should have a value!");
		} else {
			try {
				((ElectronicValueRW) service).readValue(Integer.parseInt(value_sequenceNumber.getText()),
						Integer.parseInt(value_timeout.getText()));
			} catch (JposException je) {
				JOptionPane.showMessageDialog(null, je.getMessage());
				je.printStackTrace();
			} catch (NumberFormatException e1) {
				JOptionPane.showMessageDialog(null, e1.getMessage());
				e1.printStackTrace();
			}
		}
	}

	@FXML
	public void handleSubtractValue(ActionEvent e) {
		if (value_sequenceNumber.getText().isEmpty() || value_timeout.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Every Field should have a value!");
		} else {
			try {
				((ElectronicValueRW) service).subtractValue(Integer.parseInt(value_sequenceNumber.getText()),
						Integer.parseInt(value_timeout.getText()));
			} catch (JposException je) {
				JOptionPane.showMessageDialog(null, je.getMessage());
				je.printStackTrace();
			} catch (NumberFormatException e1) {
				JOptionPane.showMessageDialog(null, e1.getMessage());
				e1.printStackTrace();
			}
		}
	}

	@FXML
	public void handleWriteValue(ActionEvent e) {
		if (value_sequenceNumber.getText().isEmpty() || value_timeout.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Every Field should have a value!");
		} else {
			try {
				((ElectronicValueRW) service).writeValue(Integer.parseInt(value_sequenceNumber.getText()),
						Integer.parseInt(value_timeout.getText()));
			} catch (JposException je) {
				JOptionPane.showMessageDialog(null, je.getMessage());
				je.printStackTrace();
			} catch (NumberFormatException e1) {
				JOptionPane.showMessageDialog(null, e1.getMessage());
				e1.printStackTrace();
			}
		}
	}

	@FXML
	public void handleAccessLog(ActionEvent e) {
		if (accessLog_sequenceNumber.getText().isEmpty() || accessLog_timeout.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Every Field should have a value!");
		} else {
			try {
				((ElectronicValueRW) service).accessLog(Integer.parseInt(accessLog_sequenceNumber.getText()),
						ElectronicValueRWConstantMapper.getConstantNumberFromString(accessLog_type
								.getSelectionModel().getSelectedItem()), Integer.parseInt(accessLog_timeout
								.getText()));
			} catch (JposException je) {
				JOptionPane.showMessageDialog(null, je.getMessage());
				je.printStackTrace();
			} catch (NumberFormatException e1) {
				JOptionPane.showMessageDialog(null, e1.getMessage());
				e1.printStackTrace();
			}
		}
	}

	@FXML
	public void handleActivateService(ActionEvent e) {
		if (activateService_data.getText().isEmpty() || activateService_obj.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Every Field should have a value!");
		} else {
			try {
				int[] data = new int[1];
				data[0] = Integer.parseInt(activateService_data.getText());

				Object[] obj = new Object[1];
				obj[0] = activateService_obj.getText();

				((ElectronicValueRW) service).activateService(data, obj);
				activateService_data.setText("" + data[0]);
				activateService_obj.setText("" + obj);
			} catch (JposException je) {
				JOptionPane.showMessageDialog(null, je.getMessage());
				je.printStackTrace();
			} catch (NumberFormatException e1) {
				JOptionPane.showMessageDialog(null, e1.getMessage());
				e1.printStackTrace();
			}
		}
	}

	@FXML
	public void handleBeginDetection(ActionEvent e) {
		if (beginDetection_timeout.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Field should have a value!");
		} else {
			try {
				((ElectronicValueRW) service).beginDetection(ElectronicValueRWConstantMapper
						.getConstantNumberFromString(beginDetection_type.getSelectionModel()
								.getSelectedItem()), Integer.parseInt(beginDetection_timeout.getText()));
			} catch (JposException je) {
				JOptionPane.showMessageDialog(null, je.getMessage());
				je.printStackTrace();
			} catch (NumberFormatException e1) {
				JOptionPane.showMessageDialog(null, e1.getMessage());
				e1.printStackTrace();
			}
		}
	}

	@FXML
	public void handleBeginRemoval(ActionEvent e) {
		try {
			((ElectronicValueRW) service).beginRemoval(0);
		} catch (JposException je) {
			JOptionPane.showMessageDialog(null, je.getMessage());
			je.printStackTrace();
		}
	}

	@FXML
	public void handleCaptureCard(ActionEvent e) {
		try {
			((ElectronicValueRW) service).captureCard();
		} catch (JposException je) {
			JOptionPane.showMessageDialog(null, je.getMessage());
			je.printStackTrace();
		}
	}

	@FXML
	public void handleEndDetection(ActionEvent e) {
		try {
			((ElectronicValueRW) service).endDetection();
		} catch (JposException je) {
			JOptionPane.showMessageDialog(null, je.getMessage());
			je.printStackTrace();
		}
	}

	@FXML
	public void handleEndRemoval(ActionEvent e) {
		try {
			((ElectronicValueRW) service).endRemoval();
		} catch (JposException je) {
			JOptionPane.showMessageDialog(null, je.getMessage());
			je.printStackTrace();
		}
	}

	@FXML
	public void handleEnumerateCardService(ActionEvent e) {
		try {
			((ElectronicValueRW) service).enumerateCardServices();
		} catch (JposException je) {
			JOptionPane.showMessageDialog(null, je.getMessage());
			je.printStackTrace();
		}
	}

	@FXML
	public void handleLockTerminal(ActionEvent e) {
		try {
			((ElectronicValueRW) service).lockTerminal();
		} catch (JposException je) {
			JOptionPane.showMessageDialog(null, je.getMessage());
			je.printStackTrace();
		}
	}

	@FXML
	public void handleUnlockTerminal(ActionEvent e) {
		try {
			((ElectronicValueRW) service).unlockTerminal();
		} catch (JposException je) {
			JOptionPane.showMessageDialog(null, je.getMessage());
			je.printStackTrace();
		}
	}

	@FXML
	public void handleTransactionAccess(ActionEvent e) {
		try {
			((ElectronicValueRW) service).transactionAccess(ElectronicValueRWConstantMapper
					.getConstantNumberFromString(transactionAccess_control.getSelectionModel()
							.getSelectedItem()));
		} catch (JposException je) {
			JOptionPane.showMessageDialog(null, je.getMessage());
			je.printStackTrace();
		}
	}

	@FXML
	public void handleUpdateKey(ActionEvent e) {
		if (updateKey_data.getText().isEmpty() || updateKey_obj.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Every Field should have a value!");
		} else {
			try {
				int[] data = new int[1];
				data[0] = Integer.parseInt(updateKey_data.getText());

				Object[] obj = new Object[1];
				obj[0] = updateKey_obj.getText();

				((ElectronicValueRW) service).updateKey(data, obj);
				updateKey_data.setText("" + data[0]);
				updateKey_obj.setText("" + obj);
			} catch (JposException je) {
				JOptionPane.showMessageDialog(null, je.getMessage());
				je.printStackTrace();
			} catch (NumberFormatException e1) {
				JOptionPane.showMessageDialog(null, e1.getMessage());
				e1.printStackTrace();
			}
		}
	}

	/*
	 * Set up ComboBoxes
	 */

	private void setUpDetectionControl() {
		detectionControl.getItems().clear();
		detectionControl.getItems().add(true);
		detectionControl.getItems().add(false);
		detectionControl.setValue(true);
	}

	private void setUpAccessLogType() {
		accessLog_type.getItems().clear();
		accessLog_type.getItems().add(ElectronicValueRWConstantMapper.EVRW_AL_REPORTING.getConstant());
		accessLog_type.getItems().add(ElectronicValueRWConstantMapper.EVRW_AL_SETTLEMENT.getConstant());
		accessLog_type.setValue(ElectronicValueRWConstantMapper.EVRW_AL_SETTLEMENT.getConstant());
	}

	private void setUpBeginDetectionType() {
		beginDetection_type.getItems().clear();
		beginDetection_type.getItems().add(ElectronicValueRWConstantMapper.EVRW_BD_ANY.getConstant());
		beginDetection_type.getItems().add(ElectronicValueRWConstantMapper.EVRW_BD_SPECIFIC.getConstant());
		beginDetection_type.setValue(ElectronicValueRWConstantMapper.EVRW_BD_ANY.getConstant());
	}

	private void setUpTransactionAccessControl() {
		transactionAccess_control.getItems().clear();
		transactionAccess_control.getItems()
				.add(ElectronicValueRWConstantMapper.EVRW_TA_NORMAL.getConstant());
		transactionAccess_control.getItems().add(
				ElectronicValueRWConstantMapper.EVRW_TA_TRANSACTION.getConstant());
		transactionAccess_control.setValue(ElectronicValueRWConstantMapper.EVRW_TA_NORMAL.getConstant());
	}

	private void setUpComboBoxes() {
		setUpDetectionControl();
		setUpAccessLogType();
		setUpBeginDetectionType();
		setUpTransactionAccessControl();
	}

}