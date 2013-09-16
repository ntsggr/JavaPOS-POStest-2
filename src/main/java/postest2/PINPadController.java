package postest2;

import java.awt.Dimension;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import jpos.JposException;
import jpos.PINPad;

import org.apache.xerces.parsers.DOMParser;
import org.w3c.dom.Document;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

public class PINPadController extends CommonController implements Initializable {

	@FXML
	@RequiredState(JposState.ENABLED)
	public AnchorPane anchorPane;
	@FXML
	public TextField accountNumber;
	@FXML
	public TextField amount;
	@FXML
	public TextField merchantID;
	@FXML
	public TextField terminalID;
	@FXML
	public TextField track1Data;
	@FXML
	public TextField track2Data;
	@FXML
	public TextField track3Data;
	@FXML
	public TextField track4Data;
	@FXML
	public ComboBox<String> transactionType;
	@FXML
	public TextField pinPadSystem;
	@FXML
	public TextField transactionHost;
	@FXML
	public TextField completionCode;
	@FXML
	public TextField keyNumber;
	@FXML
	public TextField key;
	@FXML
	public TextField encryptedPIN;
	@FXML
	public CheckBox dataEvent;
	@FXML
	public TextField authenticationCode;

	private Runnable doDataUpdate;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		service = new PINPad();
		RequiredStateChecker.invokeThis(this, service);
		setUpLogicalNameComboBox("PINPad");

		dataEvent.selectedProperty().addListener(new ChangeListener<Boolean>() {
			public void changed(ObservableValue<? extends Boolean> ov, Boolean old_val, Boolean new_val) {
				try {
					((PINPad) service).setDataEventEnabled(new_val);
				} catch (JposException jpe) {
					JOptionPane.showMessageDialog(null, jpe.getMessage());
					jpe.printStackTrace();
				}
			}
		});

		doDataUpdate = new Runnable() {
			public void run() {
				updateData();
			}
		};
	}

	/* ************************************************************************
	 * ************************ Action Handler *********************************
	 * ***********************************************************************
	 */

	@FXML
	public void handleDeviceEnable(ActionEvent e) {
		try {
			if (deviceEnabled.isSelected()) {
				((PINPad) service).setDeviceEnabled(true);
			} else {
				((PINPad) service).setDeviceEnabled(false);
			}
			setUpTransactionType();
		} catch (JposException je) {
			JOptionPane.showMessageDialog(null, je.getMessage());
			je.printStackTrace();
		}
		RequiredStateChecker.invokeThis(this, service);
	}

	@Override
	@FXML
	public void handleOCE(ActionEvent e) {
		super.handleOCE(e);
		deviceEnabled.setSelected(true);
		handleDeviceEnable(e);
	}
	
	/**
	 * Initialize the beginning of an EFT Transaction.
	 * @param e
	 */
	@FXML
	public void handleBeginEFTTransaction(ActionEvent e) {
		try {
			((PINPad) service).setAccountNumber(accountNumber.getText());
			((PINPad) service).setAmount(Long.parseLong(amount.getText()));
			((PINPad) service).setMerchantID(merchantID.getText());
			((PINPad) service).setTerminalID(terminalID.getText());
			((PINPad) service).setTrack1Data(track1Data.getText().getBytes());
			((PINPad) service).setTrack2Data(track2Data.getText().getBytes());
			((PINPad) service).setTrack3Data(track3Data.getText().getBytes());

			if (!track4Data.getText().isEmpty())
				((PINPad) service).setTrack4Data(track4Data.getText().getBytes());

			((PINPad) service).setTransactionType(PINPadConstantMapper
					.getConstantNumberFromString(transactionType.getSelectionModel().getSelectedItem()));
			((PINPad) service).beginEFTTransaction(pinPadSystem.getText(),
					Integer.parseInt(transactionHost.getText()));
		} catch (JposException jpe) {
			jpe.printStackTrace();
		}
	}

	@FXML
	public void handleEndEFTTransaction(ActionEvent e) {
		try {
			((PINPad) service).endEFTTransaction(Integer.parseInt(completionCode.getText()));
		} catch (NumberFormatException nfe) {
			JOptionPane.showMessageDialog(null, nfe.getMessage());
			nfe.printStackTrace();
		} catch (JposException jpe) {
			JOptionPane.showMessageDialog(null, jpe.getMessage());
			jpe.printStackTrace();
		}
	}

	/**
	 *  Computes a MAC value and appends it to the designated message.
	 */
	@FXML
	public void handleComputeMAC(ActionEvent e) {
		try {
			String[] outMsg = { "" };
			((PINPad) service).computeMAC(authenticationCode.getText(), outMsg);
		} catch (JposException jpe) {
			JOptionPane.showMessageDialog(null, jpe.getMessage());
			jpe.printStackTrace();
		}
	}

	/**
	 *  Verify the MAC value in a message reecived from an EFT Transaction host.
	 * @param e
	 */
	@FXML
	public void handleVerifyMAC(ActionEvent e) {
		try {
			((PINPad) service).verifyMAC(transactionHost.getText());
		} catch (JposException jpe) {
			JOptionPane.showMessageDialog(null, jpe.getMessage());
			jpe.printStackTrace();
		}
	}

	/**
	 * Provides a new encryption key to the PIN Pad.
	 * @param e
	 */
	@FXML
	public void handleUpdateKey(ActionEvent e) {
		try {
			((PINPad) service).updateKey(Integer.parseInt(keyNumber.getText()), key.getText());
		} catch (NumberFormatException nfe) {
			JOptionPane.showMessageDialog(null, nfe.getMessage());
			nfe.printStackTrace();
		} catch (JposException jpe) {
			JOptionPane.showMessageDialog(null, jpe.getMessage());
			jpe.printStackTrace();
		}
	}

	@FXML
	public void handleClearInput(ActionEvent e) {
		try {
			((PINPad) service).clearInput();
		} catch (JposException jpe) {
			JOptionPane.showMessageDialog(null, jpe.getMessage());
			jpe.printStackTrace();
		}
	}

	@FXML
	public void handleClearInputProperties(ActionEvent e) {
		try {
			((PINPad) service).clearInputProperties();
		} catch (JposException jpe) {
			JOptionPane.showMessageDialog(null, jpe.getMessage());
			jpe.printStackTrace();
		}
	}

	@FXML
	public void handleClearFields(ActionEvent e) {
		accountNumber.clear();
		amount.clear();
		merchantID.clear();
		terminalID.clear();
		track1Data.clear();
		track2Data.clear();
		track3Data.clear();
		track4Data.clear();
		pinPadSystem.clear();
		transactionHost.clear();
		completionCode.clear();
		keyNumber.clear();
		key.clear();
		encryptedPIN.clear();
	}

	@FXML
	public void handleRefreshFields(ActionEvent e) {
		try {
			accountNumber.setText(((PINPad) service).getAccountNumber());
			amount.setText(String.valueOf(((PINPad) service).getAmount()));
			merchantID.setText(((PINPad) service).getMerchantID());
			terminalID.setText(((PINPad) service).getTerminalID());
			track1Data.setText(String.valueOf(((PINPad) service).getTrack1Data()));
			track2Data.setText(String.valueOf(((PINPad) service).getTrack1Data()));
			track3Data.setText(String.valueOf(((PINPad) service).getTrack1Data()));
			track4Data.setText(String.valueOf(((PINPad) service).getTrack1Data()));
			encryptedPIN.setText(((PINPad) service).getEncryptedPIN());
		} catch (JposException jpe) {
			JOptionPane.showMessageDialog(null, jpe.getMessage());
			jpe.printStackTrace();
		}
	}

	public void updateData() {
		try {
			encryptedPIN.setText(new String(((PINPad) service).getEncryptedPIN()));
			((PINPad) service).setDataEventEnabled(true);
		} catch (JposException je) {
			JOptionPane.showMessageDialog(null, je.getMessage());
			je.printStackTrace();
		}
		try {
			dataEvent.setSelected(((PINPad) service).getDataEventEnabled());
		} catch (JposException je) {
			JOptionPane.showMessageDialog(null, je.getMessage());
			je.printStackTrace();
		}

	}

	private void setUpTransactionType() {
		transactionType.getItems().clear();
		transactionType.getItems().add(PINPadConstantMapper.PPAD_TRANS_DEBIT.getConstant());
		transactionType.getItems().add(PINPadConstantMapper.PPAD_TRANS_CREDIT.getConstant());
		transactionType.getItems().add(PINPadConstantMapper.PPAD_TRANS_INQ.getConstant());
		transactionType.getItems().add(PINPadConstantMapper.PPAD_TRANS_RECONCILE.getConstant());
		transactionType.getItems().add(PINPadConstantMapper.PPAD_TRANS_ADMIN.getConstant());
	}

	/**
	 * Shows statistics of device if they are supported by the device
	 */
	@Override
	@FXML
	public void handleInfo(ActionEvent e) {
		try {
			IMapWrapper ppcm = new PINPadConstantMapper();
			String msg = DeviceProperties.getProperties(service, ppcm);
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
			((PINPad) service).retrieveStatistics(stats);
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

}