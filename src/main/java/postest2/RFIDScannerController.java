package postest2;

import java.awt.Dimension;
import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.FileChooser;

import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import jpos.JposException;
import jpos.RFIDScanner;

import org.apache.xerces.parsers.DOMParser;
import org.w3c.dom.Document;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

public class RFIDScannerController extends CommonController implements Initializable {

	@FXML
	@RequiredState(JposState.ENABLED)
	public Pane functionPane;

	@FXML
	public TextField readTimerInterval;
	@FXML
	public TextField disableLockTag_tagID;
	@FXML
	public TextField disableLockTag_timeout;
	@FXML
	public TextField disableLockTag_password;
	@FXML
	public TextField readStartReadTags_filterID;
	@FXML
	public TextField readStartReadTags_filtermask;
	@FXML
	public TextField readStartReadTags_start;
	@FXML
	public TextField readStartReadTags_length;
	@FXML
	public TextField readStartReadTags_timeout;
	@FXML
	public TextField readStartReadTags_password;
	@FXML
	public TextField stopReadTags_password;
	@FXML
	public TextField writeTagData_tagID;
	@FXML
	public TextField writeTagData_userdata;
	@FXML
	public TextField writeTagData_start;
	@FXML
	public TextField writeTagData_timeout;
	@FXML
	public TextField writeTagData_password;
	@FXML
	public TextField writeTagID_sourceID;
	@FXML
	public TextField writeTagID_destID;
	@FXML
	public TextField writeTagID_timeout;
	@FXML
	public TextField writeTagID_password;

	@FXML
	public ComboBox<String> protocolMask;
	@FXML
	public ComboBox<String> readStartReadTags_cmd;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		service = new RFIDScanner();
		RequiredStateChecker.invokeThis(this, service);
		setUpLogicalNameComboBox("RFIDScanner");
	}

	/* ************************************************************************
	 * ************************ Action Handler *********************************
	 * ***********************************************************************
	 */

	@FXML
	public void handleDeviceEnable(ActionEvent e) {
		try {
			if (deviceEnabled.isSelected()) {
				((RFIDScanner) service).setDeviceEnabled(true);
				setUpComboBoxes();
			} else {
				((RFIDScanner) service).setDeviceEnabled(false);
			}
			RequiredStateChecker.invokeThis(this, service);
		} catch (JposException je) {
			System.err.println("RFIDScannerPanel: CheckBoxListener: Jpos Exception" + je);
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
			IMapWrapper rfidscm = new RFIDScannerConstantMapper();
			String msg = DeviceProperties.getProperties(service, rfidscm);
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
			((RFIDScanner) service).retrieveStatistics(stats);
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
	public void handleSetProtocolMask(ActionEvent e) {
		try {
			((RFIDScanner)service).setProtocolMask(RFIDScannerConstantMapper.getConstantNumberFromString(protocolMask.getSelectionModel().getSelectedItem()));
		} catch (JposException e1) {
			JOptionPane.showMessageDialog(null, e1.getMessage());
			e1.printStackTrace();
		}
	}

	@FXML
	public void handleSetReadTimerInterval(ActionEvent e) {
		if(readTimerInterval.getText().isEmpty()){
			JOptionPane.showMessageDialog(null, "Parameter is not specified");
		} else {
			try {
				((RFIDScanner)service).setReadTimerInterval(Integer.parseInt(readTimerInterval.getText()));
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
	public void handleBrowseDisableLockTagTagID(ActionEvent e) {
		FileChooser chooser = new FileChooser();
		chooser.setTitle("Choose Binary");
		File f = chooser.showOpenDialog(null);
		if (f != null) {
			disableLockTag_tagID.setText(f.getAbsolutePath());
		}
	}

	@FXML
	public void handleBrowseDisableLockTagPassword(ActionEvent e) {
		FileChooser chooser = new FileChooser();
		chooser.setTitle("Choose Binary");
		File f = chooser.showOpenDialog(null);
		if (f != null) {
			disableLockTag_password.setText(f.getAbsolutePath());
		}
	}

	@FXML
	public void handleDisableTag(ActionEvent e) {
		if(disableLockTag_password.getText().isEmpty() || disableLockTag_tagID.getText().isEmpty() || disableLockTag_password.getText().isEmpty()){
			JOptionPane.showMessageDialog(null, "One of the Parameters is not specified");
		} else {
			try {
				((RFIDScanner)service).disableTag(readBytesFromFile(disableLockTag_tagID.getText()), Integer.parseInt(disableLockTag_timeout.getText()), readBytesFromFile(disableLockTag_password.getText()));
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
	public void handleFirstTag(ActionEvent e) {
		try {
			((RFIDScanner)service).firstTag();
		} catch (JposException e1) {
			JOptionPane.showMessageDialog(null, e1.getMessage());
			e1.printStackTrace();
		}
	}

	@FXML
	public void handleLockTag(ActionEvent e) {
		if(disableLockTag_password.getText().isEmpty() || disableLockTag_tagID.getText().isEmpty() || disableLockTag_password.getText().isEmpty()){
			JOptionPane.showMessageDialog(null, "One of the Parameters is not specified");
		} else {
			try {
				((RFIDScanner)service).lockTag(readBytesFromFile(disableLockTag_tagID.getText()), Integer.parseInt(disableLockTag_timeout.getText()), readBytesFromFile(disableLockTag_password.getText()));
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
	public void handleNextTag(ActionEvent e) {
		try {
			((RFIDScanner)service).nextTag();
		} catch (JposException e1) {
			JOptionPane.showMessageDialog(null, e1.getMessage());
			e1.printStackTrace();
		}
	}

	@FXML
	public void handlePreviousTag(ActionEvent e) {
		try {
			((RFIDScanner)service).previousTag();
		} catch (JposException e1) {
			JOptionPane.showMessageDialog(null, e1.getMessage());
			e1.printStackTrace();
		}
	}

	@FXML
	public void handleBrowseReadStartReadTagsFilterID(ActionEvent e) {
		FileChooser chooser = new FileChooser();
		chooser.setTitle("Choose Binary");
		File f = chooser.showOpenDialog(null);
		if (f != null) {
			readStartReadTags_filterID.setText(f.getAbsolutePath());
		}
	}

	@FXML
	public void handleStartReadTags(ActionEvent e) {
		if(readStartReadTags_filterID.getText().isEmpty() || readStartReadTags_filtermask.getText().isEmpty() || 
				readStartReadTags_length.getText().isEmpty() || readStartReadTags_password.getText().isEmpty() || 
				readStartReadTags_start.getText().isEmpty()){
			JOptionPane.showMessageDialog(null, "One of the Parameters is not specified");
		} else {
			try {
				((RFIDScanner)service).startReadTags(RFIDScannerConstantMapper.getConstantNumberFromString(
						readStartReadTags_cmd.getSelectionModel().getSelectedItem()), 
						readBytesFromFile(readStartReadTags_filterID.getText()), readBytesFromFile(readStartReadTags_filtermask.getText()), 
						Integer.parseInt(readStartReadTags_start.getText()), Integer.parseInt(readStartReadTags_length.getText()), 
						readBytesFromFile(readStartReadTags_password.getText()));
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
	public void handleReadTags(ActionEvent e) {
		if(readStartReadTags_filterID.getText().isEmpty() || readStartReadTags_filtermask.getText().isEmpty() || 
				readStartReadTags_length.getText().isEmpty() || readStartReadTags_password.getText().isEmpty() || 
				readStartReadTags_start.getText().isEmpty() || readStartReadTags_timeout.getText().isEmpty()){
			JOptionPane.showMessageDialog(null, "One of the Parameters is not specified");
		} else {
			try {
				((RFIDScanner)service).readTags(RFIDScannerConstantMapper.getConstantNumberFromString(
						readStartReadTags_cmd.getSelectionModel().getSelectedItem()), 
						readBytesFromFile(readStartReadTags_filterID.getText()), readBytesFromFile(readStartReadTags_filtermask.getText()), 
						Integer.parseInt(readStartReadTags_start.getText()), Integer.parseInt(readStartReadTags_length.getText()), 
						Integer.parseInt(readStartReadTags_timeout.getText()), readBytesFromFile(readStartReadTags_password.getText()));
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
	public void handleBrowseReadStartReadTagsFiltermask(ActionEvent e) {
		FileChooser chooser = new FileChooser();
		chooser.setTitle("Choose Binary");
		File f = chooser.showOpenDialog(null);
		if (f != null) {
			readStartReadTags_filtermask.setText(f.getAbsolutePath());
		}
	}

	@FXML
	public void handleBrowseReadStartReadTagsPassword(ActionEvent e) {
		FileChooser chooser = new FileChooser();
		chooser.setTitle("Choose Binary");
		File f = chooser.showOpenDialog(null);
		if (f != null) {
			readStartReadTags_password.setText(f.getAbsolutePath());
		}
	}

	@FXML
	public void handleBrowseStopReadTagsPassword(ActionEvent e) {
		FileChooser chooser = new FileChooser();
		chooser.setTitle("Choose Binary");
		File f = chooser.showOpenDialog(null);
		if (f != null) {
			stopReadTags_password.setText(f.getAbsolutePath());
		}
	}

	@FXML
	public void handleStopReadTag(ActionEvent e) {
		if(stopReadTags_password.getText().isEmpty()){
			JOptionPane.showMessageDialog(null, "Parameter is not specified!");
		} else {
			try {
				((RFIDScanner)service).stopReadTags(readBytesFromFile(stopReadTags_password.getText()));
						
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
	public void handleBrowseWriteTagDataTagID(ActionEvent e) {
		FileChooser chooser = new FileChooser();
		chooser.setTitle("Choose Binary");
		File f = chooser.showOpenDialog(null);
		if (f != null) {
			writeTagData_tagID.setText(f.getAbsolutePath());
		}
	}

	@FXML
	public void handleWriteTagData(ActionEvent e) {
		if(writeTagData_tagID.getText().isEmpty() || writeTagData_userdata.getText().isEmpty() || 
				writeTagData_start.getText().isEmpty() || writeTagData_timeout.getText().isEmpty() || 
				writeTagData_password.getText().isEmpty()){
			JOptionPane.showMessageDialog(null, "One of the Parameters is not specified");
		} else {
			try {
				((RFIDScanner)service).writeTagData(readBytesFromFile(writeTagData_tagID.getText()), 
						readBytesFromFile(writeTagData_userdata.getText()), Integer.parseInt(writeTagData_start.getText()), 
						Integer.parseInt(writeTagData_timeout.getText()), readBytesFromFile(writeTagData_password.getText()));
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
	public void handleBrowseWriteTagDataUserdata(ActionEvent e) {
		FileChooser chooser = new FileChooser();
		chooser.setTitle("Choose Binary");
		File f = chooser.showOpenDialog(null);
		if (f != null) {
			writeTagData_userdata.setText(f.getAbsolutePath());
		}
	}

	@FXML
	public void handleBrowseWriteTagDataPassword(ActionEvent e) {
		FileChooser chooser = new FileChooser();
		chooser.setTitle("Choose Binary");
		File f = chooser.showOpenDialog(null);
		if (f != null) {
			writeTagData_password.setText(f.getAbsolutePath());
		}
	}

	@FXML
	public void handleBrowseWriteTagIDSourceID(ActionEvent e) {
		FileChooser chooser = new FileChooser();
		chooser.setTitle("Choose Binary");
		File f = chooser.showOpenDialog(null);
		if (f != null) {
			writeTagID_sourceID.setText(f.getAbsolutePath());
		}
	}

	@FXML
	public void handleWriteTagID(ActionEvent e) {
		if(writeTagID_sourceID.getText().isEmpty() || writeTagID_destID.getText().isEmpty() || 
				writeTagID_password.getText().isEmpty() || writeTagID_timeout.getText().isEmpty()){
			JOptionPane.showMessageDialog(null, "One of the Parameters is not specified");
		} else {
			try {
				((RFIDScanner)service).writeTagID(readBytesFromFile(writeTagID_sourceID.getText()), 
						readBytesFromFile(writeTagID_destID.getText()), Integer.parseInt(writeTagID_timeout.getText()), 
						readBytesFromFile(writeTagID_password.getText()));
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
	public void handleBrowseWriteTagIDDestID(ActionEvent e) {
		FileChooser chooser = new FileChooser();
		chooser.setTitle("Choose Binary");
		File f = chooser.showOpenDialog(null);
		if (f != null) {
			writeTagID_destID.setText(f.getAbsolutePath());
		}
	}

	@FXML
	public void handleBrowseWriteTagIDPassword(ActionEvent e) {
		FileChooser chooser = new FileChooser();
		chooser.setTitle("Choose Binary");
		File f = chooser.showOpenDialog(null);
		if (f != null) {
			writeTagID_password.setText(f.getAbsolutePath());
		}
	}

	/*
	 * SetUp ComboBoxes
	 */
	
	private void setUpProtocolMask() {
		protocolMask.getItems().clear();
		protocolMask.getItems().add(RFIDScannerConstantMapper.RFID_PR_0PLUS.getConstant());
		protocolMask.getItems().add(RFIDScannerConstantMapper.RFID_PR_ALL.getConstant());
		protocolMask.getItems().add(RFIDScannerConstantMapper.RFID_PR_EPC0.getConstant());
		protocolMask.getItems().add(RFIDScannerConstantMapper.RFID_PR_EPC1.getConstant());
		protocolMask.getItems().add(RFIDScannerConstantMapper.RFID_PR_EPC1G2.getConstant());
		protocolMask.getItems().add(RFIDScannerConstantMapper.RFID_PR_EPC2.getConstant());
		protocolMask.getItems().add(RFIDScannerConstantMapper.RFID_PR_ISO14443A.getConstant());
		protocolMask.getItems().add(RFIDScannerConstantMapper.RFID_PR_ISO14443B.getConstant());
		protocolMask.getItems().add(RFIDScannerConstantMapper.RFID_PR_ISO15693.getConstant());
		protocolMask.getItems().add(RFIDScannerConstantMapper.RFID_PR_ISO180006B.getConstant());
		protocolMask.getItems().add(RFIDScannerConstantMapper.RFID_PR_OTHER.getConstant());
		protocolMask.setValue(RFIDScannerConstantMapper.RFID_PR_0PLUS.getConstant());
	}

	private void setUpReadStartReadTagsCmd() {
		readStartReadTags_cmd.getItems().clear();
		readStartReadTags_cmd.getItems().add(RFIDScannerConstantMapper.RFID_RT_ID.getConstant());
		readStartReadTags_cmd.getItems().add(RFIDScannerConstantMapper.RFID_RT_FULLUSERDATA.getConstant());
		readStartReadTags_cmd.getItems().add(RFIDScannerConstantMapper.RFID_RT_ID_FULLUSERDATA.getConstant());
		readStartReadTags_cmd.getItems().add(RFIDScannerConstantMapper.RFID_RT_PARTIALUSERDATA.getConstant());
		readStartReadTags_cmd.getItems().add(RFIDScannerConstantMapper.RFID_RT_ID_PARTIALUSERDATA.getConstant());
		readStartReadTags_cmd.setValue(RFIDScannerConstantMapper.RFID_RT_ID.getConstant());
	}

	private void setUpComboBoxes() {
		setUpProtocolMask();
		setUpReadStartReadTagsCmd();
	}
	
	
	/**
	 * Read the given binary file, and return its contents as a byte array.
	 * 
	 */
	private static byte[] readBytesFromFile(String aInputFileName) {
		File file = new File(aInputFileName);
		byte[] result = new byte[(int) file.length()];
		try {
			InputStream input = null;
			try {
				int totalBytesRead = 0;
				input = new BufferedInputStream(new FileInputStream(file));
				while (totalBytesRead < result.length) {
					int bytesRemaining = result.length - totalBytesRead;
					// input.read() returns -1, 0, or more :
					int bytesRead = input.read(result, totalBytesRead, bytesRemaining);
					if (bytesRead > 0) {
						totalBytesRead = totalBytesRead + bytesRead;
					}
				}
			} finally {
				input.close();
			}
		} catch (FileNotFoundException ex) {

			JOptionPane.showMessageDialog(null, ex.getMessage());
			ex.printStackTrace();
		} catch (IOException ex) {
			JOptionPane.showMessageDialog(null, ex.getMessage());
			ex.printStackTrace();

		}
		return result;
	}
	
}