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
import javafx.scene.control.ListView;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;

import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import jpos.JposException;
import jpos.MSR;

import org.apache.xerces.parsers.DOMParser;
import org.w3c.dom.Document;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

public class MSRController extends CommonController implements Initializable {

	@FXML
	@RequiredState(JposState.ENABLED)
	public TabPane functionPane;

	@FXML
	public TextField writeCardType;
	@FXML
	public TextField authenticateDevice_response;
	@FXML
	public TextField deauthenticateDevice_response;
	@FXML
	public TextField retrieveCardProperty_name;
	@FXML
	public TextField retrieveCardProperty_value;
	@FXML
	public TextField retrieveDeviceAuthenticationData_challenge;
	@FXML
	public TextField updateKey_key;
	@FXML
	public TextField updateKey_keyName;
	@FXML
	public TextField writeTracks_addData;
	@FXML
	public TextField writeTracks_timeout;

	@FXML
	public ComboBox<String> dataEncryptionAlgorithm;
	@FXML
	public ComboBox<Boolean> decodeData;
	@FXML
	public ComboBox<String> errorReportingType;
	@FXML
	public ComboBox<Boolean> parseDecodeData;
	@FXML
	public ComboBox<String> tracksToWrite;
	@FXML
	public ComboBox<String> tracksToRead;
	@FXML
	public ComboBox<Boolean> transmitSentinels;
	@FXML
	public ListView<String> writeTracks_data;
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		service = new MSR();
		RequiredStateChecker.invokeThis(this, service);
		setUpLogicalNameComboBox("MSR");
	}

	/* ************************************************************************
	 * ************************ Action Handler *********************************
	 * ***********************************************************************
	 */

	@FXML
	public void handleDeviceEnable(ActionEvent e) {
		try {
			if (deviceEnabled.isSelected()) {
				((MSR) service).setDeviceEnabled(true);
				setUpComboBoxes();
				setUpListViews();
			} else {
				((MSR) service).setDeviceEnabled(false);
			}
			RequiredStateChecker.invokeThis(this, service);
		} catch (JposException je) {
			System.err.println("MSRPanel: CheckBoxListener: Jpos Exception" + je);
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
			IMapWrapper msrcm = new MSRConstantMapper();
			String msg = DeviceProperties.getProperties(service, msrcm);
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

	// Shows statistics of device if they are supported by the device
	@Override
	@FXML
	public void handleStatistics(ActionEvent e) {
		String[] stats = new String[] { "", "U_", "M_" };
		try {
			((MSR) service).retrieveStatistics(stats);
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
	public void handleSetDataEncryptionAlgorithm(ActionEvent e) {
		try {
			((MSR)service).setDataEncryptionAlgorithm(MSRConstantMapper.getConstantNumberFromString(dataEncryptionAlgorithm.getSelectionModel().getSelectedItem()));
		} catch (JposException e1) {
			JOptionPane.showMessageDialog(null, e1.getMessage());
			e1.printStackTrace();
		}
	}

	@FXML
	public void handleSetDecodeData(ActionEvent e) {
		try {
			((MSR)service).setDecodeData(decodeData.getSelectionModel().getSelectedItem());
		} catch (JposException e1) {
			JOptionPane.showMessageDialog(null, e1.getMessage());
			e1.printStackTrace();
		}
	}

	@FXML
	public void handleSetErrorReportingType(ActionEvent e) {
		try {
			((MSR)service).setErrorReportingType(MSRConstantMapper.getConstantNumberFromString(errorReportingType.getSelectionModel().getSelectedItem()));
		} catch (JposException e1) {
			JOptionPane.showMessageDialog(null, e1.getMessage());
			e1.printStackTrace();
		}
	}

	@FXML
	public void handleSetParseDecodeData(ActionEvent e) {
		try {
			((MSR)service).setParseDecodeData(parseDecodeData.getSelectionModel().getSelectedItem());
		} catch (JposException e1) {
			JOptionPane.showMessageDialog(null, e1.getMessage());
			e1.printStackTrace();
		}
	}

	@FXML
	public void handleSetTracksToRead(ActionEvent e) {
		try {
			((MSR)service).setTracksToRead(MSRConstantMapper.getConstantNumberFromString(tracksToRead.getSelectionModel().getSelectedItem()));
		} catch (JposException e1) {
			JOptionPane.showMessageDialog(null, e1.getMessage());
			e1.printStackTrace();
		}
	}

	@FXML
	public void handleSetTracksToWrite(ActionEvent e) {
		try {
			((MSR)service).setTracksToWrite(MSRConstantMapper.getConstantNumberFromString(tracksToWrite.getSelectionModel().getSelectedItem()));
		} catch (JposException e1) {
			JOptionPane.showMessageDialog(null, e1.getMessage());
			e1.printStackTrace();
		}
	}

	@FXML
	public void handleSetTransmitSentinels(ActionEvent e) {
		try {
			((MSR)service).setTransmitSentinels(transmitSentinels.getSelectionModel().getSelectedItem());
		} catch (JposException e1) {
			JOptionPane.showMessageDialog(null, e1.getMessage());
			e1.printStackTrace();
		}
	}

	@FXML
	public void handleSetWriteCardType(ActionEvent e) {
		if(writeCardType.getText().isEmpty()){
			JOptionPane.showMessageDialog(null, "Field should have a value!");
		} else {
			try {
				((MSR)service).setWriteCardType(writeCardType.getText());
			} catch (JposException e1) {
				JOptionPane.showMessageDialog(null, e1.getMessage());
				e1.printStackTrace();
			}
		}
	}

	@FXML
	public void handleBrowseAuthenticateDeviceResponse(ActionEvent e) {
		FileChooser chooser = new FileChooser();
		chooser.setTitle("Choose File");
		File f = chooser.showOpenDialog(null);
		if (f != null) {
			authenticateDevice_response.setText(f.getAbsolutePath());
		}
	}

	@FXML
	public void handleAuthenticatedevice(ActionEvent e) {
		if(authenticateDevice_response.getText().isEmpty()){
			JOptionPane.showMessageDialog(null, "Field should have a value!");
		} else {
			try {
				((MSR)service).authenticateDevice(readBytesFromFile(authenticateDevice_response.getText()));
			} catch (JposException e1) {
				JOptionPane.showMessageDialog(null, e1.getMessage());
				e1.printStackTrace();
			}
		}
	}

	@FXML
	public void handleBrowseDeauthenticateDeviceResponse(ActionEvent e) {
		FileChooser chooser = new FileChooser();
		chooser.setTitle("Choose File");
		File f = chooser.showOpenDialog(null);
		if (f != null) {
			deauthenticateDevice_response.setText(f.getAbsolutePath());
		}
	}

	@FXML
	public void handleDeauthenticateDevice(ActionEvent e) {
		if(deauthenticateDevice_response.getText().isEmpty()){
			JOptionPane.showMessageDialog(null, "Field should have a value!");
		} else {
			try {
				((MSR)service).deauthenticateDevice(readBytesFromFile(deauthenticateDevice_response.getText()));
			} catch (JposException e1) {
				JOptionPane.showMessageDialog(null, e1.getMessage());
				e1.printStackTrace();
			}
		}
	}

	@FXML
	public void handleRetrieveCardProperty(ActionEvent e) {
		if(retrieveCardProperty_name.getText().isEmpty()){
			JOptionPane.showMessageDialog(null, "Field should have a value!");
		} else {
			String[] value = new String[1];
			try {
				((MSR)service).retrieveCardProperty(retrieveCardProperty_name.getText(), value);
				retrieveCardProperty_value.setText(value[0]);
			} catch (JposException e1) {
				JOptionPane.showMessageDialog(null, e1.getMessage());
				e1.printStackTrace();
			}
		}
	}

	@FXML
	public void handleBrowseRetrieveDeviceAuthenticationDataChallenge(ActionEvent e) {
		FileChooser chooser = new FileChooser();
		chooser.setTitle("Choose File");
		File f = chooser.showOpenDialog(null);
		if (f != null) {
			retrieveDeviceAuthenticationData_challenge.setText(f.getAbsolutePath());
		}
	}

	@FXML
	public void handleRetrieveDeviceAuthenticationData(ActionEvent e) {
		if(retrieveDeviceAuthenticationData_challenge.getText().isEmpty()){
			JOptionPane.showMessageDialog(null, "Field should have a value!");
		} else {
			try {
				((MSR)service).retrieveDeviceAuthenticationData(readBytesFromFile(retrieveDeviceAuthenticationData_challenge.getText()));
			} catch (JposException e1) {
				JOptionPane.showMessageDialog(null, e1.getMessage());
				e1.printStackTrace();
			}
		}
	}
	
	@FXML
	public void handleUpdateKey(ActionEvent e) {
		if(updateKey_key.getText().isEmpty() || updateKey_keyName.getText().isEmpty()){
			JOptionPane.showMessageDialog(null, "Field should have a value!");
		} else {
			try {
				((MSR)service).updateKey(updateKey_key.getText(), updateKey_keyName.getText());
			} catch (JposException e1) {
				JOptionPane.showMessageDialog(null, e1.getMessage());
				e1.printStackTrace();
			}
		}
	}

	@FXML
	public void handleBrowseWriteTracksAddData(ActionEvent e) {
		FileChooser chooser = new FileChooser();
		chooser.setTitle("Choose File");
		File f = chooser.showOpenDialog(null);
		if (f != null) {
			writeTracks_addData.setText(f.getAbsolutePath());
		}
	}

	@FXML
	public void handleWriteTracks(ActionEvent e) {
		if(writeTracks_timeout.getText().isEmpty() || writeTracks_data.getItems().isEmpty()){
			JOptionPane.showMessageDialog(null, "Field should have a value!");
		} else {
			byte[][] data = new byte[writeTracks_data.getItems().size()][];
			for(int i = 0; i < data.length; i++){
				data[i] = readBytesFromFile(writeTracks_data.getItems().get(i));
			}
			try {
				((MSR)service).writeTracks(data, Integer.parseInt(writeTracks_timeout.getText()));
			} catch (JposException e1) {
				JOptionPane.showMessageDialog(null, e1.getMessage());
				e1.printStackTrace();
			} catch(NumberFormatException e1){
				JOptionPane.showMessageDialog(null, e1.getMessage());
				e1.printStackTrace();
			}
		}
	}
	
	@FXML
	public void handleWriteTracksAddData(ActionEvent e) {
		if(!writeTracks_addData.getText().isEmpty()){
			writeTracks_data.getItems().add(writeTracks_addData.getText());
		}
	}
	/*
	 * Set Up ComboBoxes
	 */
	
	private void setUpDataEncryptionAlgorithm(){
		dataEncryptionAlgorithm.getItems().clear();
		dataEncryptionAlgorithm.getItems().add(MSRConstantMapper.MSR_DE_3DEA_DUKPT.getConstant());
		dataEncryptionAlgorithm.getItems().add(MSRConstantMapper.MSR_DE_NONE.getConstant());
		dataEncryptionAlgorithm.setValue(MSRConstantMapper.MSR_DE_NONE.getConstant());
		
	}
	
	private void setUpDecodeData(){
		decodeData.getItems().clear();
		decodeData.getItems().add(true);
		decodeData.getItems().add(false);
		decodeData.setValue(true);
		
	}
	
	private void setUpErrorReportingType(){
		errorReportingType.getItems().clear();
		errorReportingType.getItems().add(MSRConstantMapper.MSR_ERT_CARD.getConstant());
		errorReportingType.getItems().add(MSRConstantMapper.MSR_ERT_TRACK.getConstant());
		errorReportingType.setValue(MSRConstantMapper.MSR_ERT_CARD.getConstant());
	}
	
	private void setUpParseDecodeData(){
		parseDecodeData.getItems().clear();
		parseDecodeData.getItems().add(true);
		parseDecodeData.getItems().add(false);
		parseDecodeData.setValue(true);
	}
	
	private void setUpTracksToWrite(){
		tracksToWrite.getItems().clear();
		tracksToWrite.getItems().add(MSRConstantMapper.MSR_TR_1.getConstant());
		tracksToWrite.getItems().add(MSRConstantMapper.MSR_TR_1_2.getConstant());
		tracksToWrite.getItems().add(MSRConstantMapper.MSR_TR_1_2_3.getConstant());
		tracksToWrite.getItems().add(MSRConstantMapper.MSR_TR_1_2_3_4.getConstant());
		tracksToWrite.getItems().add(MSRConstantMapper.MSR_TR_1_2_4.getConstant());
		tracksToWrite.getItems().add(MSRConstantMapper.MSR_TR_1_3.getConstant());
		tracksToWrite.getItems().add(MSRConstantMapper.MSR_TR_1_3_4.getConstant());
		tracksToWrite.getItems().add(MSRConstantMapper.MSR_TR_1_4.getConstant());
		tracksToWrite.getItems().add(MSRConstantMapper.MSR_TR_2.getConstant());
		tracksToWrite.getItems().add(MSRConstantMapper.MSR_TR_2_3.getConstant());
		tracksToWrite.getItems().add(MSRConstantMapper.MSR_TR_2_3_4.getConstant());
		tracksToWrite.getItems().add(MSRConstantMapper.MSR_TR_2_4.getConstant());
		tracksToWrite.getItems().add(MSRConstantMapper.MSR_TR_3.getConstant());
		tracksToWrite.getItems().add(MSRConstantMapper.MSR_TR_3_4.getConstant());
		tracksToWrite.getItems().add(MSRConstantMapper.MSR_TR_4.getConstant());
		tracksToWrite.getItems().add(MSRConstantMapper.MSR_TR_NONE.getConstant());
		tracksToWrite.setValue(MSRConstantMapper.MSR_TR_1.getConstant());
	}
	
	private void setUpTracksToRead(){
		tracksToRead.getItems().clear();
		tracksToRead.getItems().add(MSRConstantMapper.MSR_TR_1.getConstant());
		tracksToRead.getItems().add(MSRConstantMapper.MSR_TR_1_2.getConstant());
		tracksToRead.getItems().add(MSRConstantMapper.MSR_TR_1_2_3.getConstant());
		tracksToRead.getItems().add(MSRConstantMapper.MSR_TR_1_2_3_4.getConstant());
		tracksToRead.getItems().add(MSRConstantMapper.MSR_TR_1_2_4.getConstant());
		tracksToRead.getItems().add(MSRConstantMapper.MSR_TR_1_3.getConstant());
		tracksToRead.getItems().add(MSRConstantMapper.MSR_TR_1_3_4.getConstant());
		tracksToRead.getItems().add(MSRConstantMapper.MSR_TR_1_4.getConstant());
		tracksToRead.getItems().add(MSRConstantMapper.MSR_TR_2.getConstant());
		tracksToRead.getItems().add(MSRConstantMapper.MSR_TR_2_3.getConstant());
		tracksToRead.getItems().add(MSRConstantMapper.MSR_TR_2_3_4.getConstant());
		tracksToRead.getItems().add(MSRConstantMapper.MSR_TR_2_4.getConstant());
		tracksToRead.getItems().add(MSRConstantMapper.MSR_TR_3.getConstant());
		tracksToRead.getItems().add(MSRConstantMapper.MSR_TR_3_4.getConstant());
		tracksToRead.getItems().add(MSRConstantMapper.MSR_TR_4.getConstant());
		tracksToRead.getItems().add(MSRConstantMapper.MSR_TR_NONE.getConstant());
		tracksToRead.setValue(MSRConstantMapper.MSR_TR_1.getConstant());
	}
	
	private void setUpTransmitSentinels(){
		transmitSentinels.getItems().clear();
		transmitSentinels.getItems().add(true);
		transmitSentinels.getItems().add(false);
		transmitSentinels.setValue(true);
	}	
	
	private void setUpComboBoxes(){
		setUpDataEncryptionAlgorithm();
		setUpDecodeData();
		setUpErrorReportingType();
		setUpParseDecodeData();
		setUpTracksToRead();
		setUpTracksToWrite();
		setUpTransmitSentinels();
	}
	
	private void setUpListViews(){
		writeTracks_data.getItems().clear();
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
