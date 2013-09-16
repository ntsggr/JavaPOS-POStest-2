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

import jpos.CAT;
import jpos.JposException;

import org.apache.xerces.parsers.DOMParser;
import org.w3c.dom.Document;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

public class CATController extends CommonController implements Initializable {

	@FXML
	@RequiredState(JposState.ENABLED)
	public TabPane functionPane;

	@FXML
	@RequiredState(JposState.OPENED)
	public CheckBox asyncMode;

	@FXML
	public ComboBox<String> paymentMedia;
	@FXML
	public ComboBox<Boolean> trainingMode;
	@FXML
	public ComboBox<String> accessDailyLog_type;

	@FXML
	public TextField additionalSecurityInformation;
	@FXML
	public TextField accessDailyLog_sequenceNumber;
	@FXML
	public TextField accessDailyLog_timeout;
	@FXML

	public TextField authorize_sequenceNumber;
	@FXML
	public TextField authorize_amount;
	@FXML
	public TextField authorize_taxOthers;
	@FXML
	public TextField authorize_timeout;
	@FXML
	public TextField cashDeposit_sequenceNumber;
	@FXML
	public TextField cashDeposit_amount;
	@FXML
	public TextField cashDeposit_timeout;
	@FXML
	public TextField checkCard_sequenceNumber;
	@FXML
	public TextField checkCard_timeout;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		setUpTooltips();
		service = new CAT();
		setUpLogicalNameComboBox("CAT");
		RequiredStateChecker.invokeThis(this, service);
	}

	/* ************************************************************************
	 * ************************ Action Handler *********************************
	 * ***********************************************************************
	 */

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
			IMapWrapper ccm = new CATConstantMapper();
			String msg = DeviceProperties.getProperties(service, ccm);
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
			((CAT) service).retrieveStatistics(stats);
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
	public void handleDeviceEnable(ActionEvent e) {
		try {
			if (deviceEnabled.isSelected()) {
				((CAT) service).setDeviceEnabled(true);
				setUpComboBoxes();

			} else {
				((CAT) service).setDeviceEnabled(false);
			}
		} catch (JposException je) {
			JOptionPane.showMessageDialog(null, je.getMessage());
			je.printStackTrace();
		}

		RequiredStateChecker.invokeThis(this, service);
	}

	@FXML
	public void handleAsyncMode(ActionEvent e) {
		try {
			((CAT) service).setAsyncMode(asyncMode.isSelected());
		} catch (JposException e1) {
			JOptionPane.showMessageDialog(null, e1.getMessage());
			e1.printStackTrace();
		}
	}

	@FXML
	public void handleSetAdditionalSecurityInformation(ActionEvent e) {
		if (additionalSecurityInformation.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Field should have a value");
		} else {
			try {
				((CAT) service).setAdditionalSecurityInformation(additionalSecurityInformation.getText());
			} catch (JposException e1) {
				JOptionPane.showMessageDialog(null, e1.getMessage());
				e1.printStackTrace();
			}
		}
	}

	@FXML
	public void handleSetPaymentMedia(ActionEvent e) {
		try {
			((CAT) service).setPaymentMedia(CATConstantMapper.getConstantNumberFromString(paymentMedia
					.getSelectionModel().getSelectedItem()));
		} catch (JposException e1) {
			JOptionPane.showMessageDialog(null, e1.getMessage());
			e1.printStackTrace();
		}
	}

	@FXML
	public void handleSetTrainingMode(ActionEvent e) {
		try {
			((CAT) service).setTrainingMode(trainingMode.getSelectionModel().getSelectedItem());
		} catch (JposException e1) {
			JOptionPane.showMessageDialog(null, e1.getMessage());
			e1.printStackTrace();
		}
	}

	@FXML
	public void handleAccessDailyLog(ActionEvent e) {
		if (accessDailyLog_sequenceNumber.getText().isEmpty() || accessDailyLog_timeout.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Every Field should have a value");
		} else {
			try {
				((CAT) service).accessDailyLog(Integer.parseInt(accessDailyLog_sequenceNumber.getText()),
						CATConstantMapper.getConstantNumberFromString(accessDailyLog_type.getSelectionModel()
								.getSelectedItem()), Integer.parseInt(accessDailyLog_timeout.getText()));

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
	public void handleAuthorizeCompletion(ActionEvent e) {
		if (authorize_amount.getText().isEmpty() || authorize_sequenceNumber.getText().isEmpty()
				|| authorize_taxOthers.getText().isEmpty() || authorize_timeout.getText().isEmpty()) {

			JOptionPane.showMessageDialog(null, "Every Field should have a value");
		} else {
			try {
				((CAT) service).authorizeCompletion(Integer.parseInt(authorize_sequenceNumber.getText()),
						Long.parseLong(authorize_amount.getText()), Long.parseLong(authorize_taxOthers.getText()),
						Integer.parseInt(authorize_timeout.getText()));
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
	public void handleAuthorizePreSales(ActionEvent e) {
		if (authorize_amount.getText().isEmpty() || authorize_sequenceNumber.getText().isEmpty()
				|| authorize_taxOthers.getText().isEmpty() || authorize_timeout.getText().isEmpty()) {

			JOptionPane.showMessageDialog(null, "Every Field should have a value");
		} else {
			try {
				((CAT) service).authorizePreSales(Integer.parseInt(authorize_sequenceNumber.getText()),
						Long.parseLong(authorize_amount.getText()), Long.parseLong(authorize_taxOthers.getText()),
						Integer.parseInt(authorize_timeout.getText()));
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
	public void handleAuthorizeVoid(ActionEvent e) {
		if (authorize_amount.getText().isEmpty() || authorize_sequenceNumber.getText().isEmpty()
				|| authorize_taxOthers.getText().isEmpty() || authorize_timeout.getText().isEmpty()) {

			JOptionPane.showMessageDialog(null, "Every Field should have a value");
		} else {
			try {
				((CAT) service).authorizeVoid(Integer.parseInt(authorize_sequenceNumber.getText()),

						Long.parseLong(authorize_amount.getText()), Long.parseLong(authorize_taxOthers.getText()),

						Integer.parseInt(authorize_timeout.getText()));
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
	public void handleAuthorizeSales(ActionEvent e) {

		if (authorize_amount.getText().isEmpty() || authorize_sequenceNumber.getText().isEmpty()
				|| authorize_taxOthers.getText().isEmpty() || authorize_timeout.getText().isEmpty()) {

			JOptionPane.showMessageDialog(null, "Every Field should have a value");
		} else {
			try {
				((CAT) service).authorizeSales(Integer.parseInt(authorize_sequenceNumber.getText()),

						Long.parseLong(authorize_amount.getText()), Long.parseLong(authorize_taxOthers.getText()),

						Integer.parseInt(authorize_timeout.getText()));
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
	public void handleAuthorizeRefund(ActionEvent e) {

		if (authorize_amount.getText().isEmpty() || authorize_sequenceNumber.getText().isEmpty()
				|| authorize_taxOthers.getText().isEmpty() || authorize_timeout.getText().isEmpty()) {

			JOptionPane.showMessageDialog(null, "Every Field should have a value");
		} else {
			try {
				((CAT) service).authorizeRefund(Integer.parseInt(authorize_sequenceNumber.getText()),

						Long.parseLong(authorize_amount.getText()), Long.parseLong(authorize_taxOthers.getText()),

						Integer.parseInt(authorize_timeout.getText()));
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
	public void handleAuthorizeVoidPreSales(ActionEvent e) {

		if (authorize_amount.getText().isEmpty() || authorize_sequenceNumber.getText().isEmpty()
				|| authorize_taxOthers.getText().isEmpty() || authorize_timeout.getText().isEmpty()) {

			JOptionPane.showMessageDialog(null, "Every Field should have a value");
		} else {
			try {
				((CAT) service).authorizeVoidPreSales(Integer.parseInt(authorize_sequenceNumber.getText()),
						Long.parseLong(authorize_amount.getText()), Long.parseLong(authorize_taxOthers.getText()),
						Integer.parseInt(authorize_timeout.getText()));
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
	public void handleCashDeposit(ActionEvent e) {
		if (cashDeposit_amount.getText().isEmpty() || cashDeposit_sequenceNumber.getText().isEmpty()
				|| cashDeposit_timeout.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Every Field should have a value");
		} else {
			try {
				((CAT) service).cashDeposit(Integer.parseInt(cashDeposit_sequenceNumber.getText()),
						Long.parseLong(cashDeposit_amount.getText()), Integer.parseInt(cashDeposit_timeout.getText()));
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
	public void handleCheckCard(ActionEvent e) {
		if (checkCard_sequenceNumber.getText().isEmpty() || checkCard_timeout.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Every Field should have a value");
		} else {
			try {
				((CAT) service).checkCard(Integer.parseInt(checkCard_sequenceNumber.getText()),
						Integer.parseInt(checkCard_timeout.getText()));

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
	public void handleLockTerminal(ActionEvent e) {
		try {
			((CAT) service).lockTerminal();

		} catch (JposException e1) {
			JOptionPane.showMessageDialog(null, e1.getMessage());
			e1.printStackTrace();
		}
	}

	@FXML
	public void handleUnlockTerminal(ActionEvent e) {
		try {
			((CAT) service).unlockTerminal();
		} catch (JposException e1) {
			JOptionPane.showMessageDialog(null, e1.getMessage());
			e1.printStackTrace();
		}
	}

	/*
	 * Set up ComboBoxes
	 */
	private void setUpPaymentMedia() {
		paymentMedia.getItems().clear();
		paymentMedia.getItems().add(CATConstantMapper.CAT_MEDIA_UNSPECIFIED.getConstant());
		paymentMedia.getItems().add(CATConstantMapper.CAT_MEDIA_NONDEFINE.getConstant());
		paymentMedia.getItems().add(CATConstantMapper.CAT_MEDIA_CREDIT.getConstant());
		paymentMedia.getItems().add(CATConstantMapper.CAT_MEDIA_DEBIT.getConstant());
		paymentMedia.getItems().add(CATConstantMapper.CAT_MEDIA_ELECTRONIC_MONEY.getConstant());
		paymentMedia.setValue(CATConstantMapper.CAT_MEDIA_UNSPECIFIED.getConstant());

	}

	private void setUpTrainingMode() {
		trainingMode.getItems().clear();
		trainingMode.getItems().add(true);
		trainingMode.getItems().add(false);
		trainingMode.setValue(false);

	}

	private void setUpAccessDailyLogType() {
		accessDailyLog_type.getItems().clear();
		accessDailyLog_type.getItems().add(CATConstantMapper.CAT_DL_REPORTING.getConstant());
		accessDailyLog_type.getItems().add(CATConstantMapper.CAT_DL_SETTLEMENT.getConstant());
		accessDailyLog_type.setValue(CATConstantMapper.CAT_DL_REPORTING.getConstant());
	}

	private void setUpComboBoxes() {
		setUpPaymentMedia();
		setUpTrainingMode();
		setUpAccessDailyLogType();
	}

	

}
