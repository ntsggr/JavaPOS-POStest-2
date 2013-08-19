package postest2;

import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.Files;
import java.util.ResourceBundle;

import javax.swing.JOptionPane;
import java.io.FileInputStream;

import sun.misc.IOUtils;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import jpos.BillDispenser;
import jpos.Biometrics;
import jpos.JposException;

public class BiometricsController extends CommonController implements Initializable {

	@FXML
	@RequiredState(JposState.ENABLED)
	public TabPane functionPane;

	@FXML
	public ComboBox<Integer> algorithm;
	@FXML
	public ComboBox<Boolean> realTimeDataEnabled;
	@FXML
	public ComboBox<String> sensorOrientation;
	@FXML
	public ComboBox<String> sensorType;

	@FXML
	public ComboBox<Boolean> identify_FARPrecedence;
	@FXML
	public ComboBox<Boolean> identifyMatch_FARPrecedence;
	@FXML
	public ComboBox<Boolean> verify_FARPrecedence;
	@FXML
	public ComboBox<Boolean> verifyMatch_FARPrecedence;

	@FXML
	public TextField beginEnrollCapture_referenceBIR;
	@FXML
	public TextField beginEnrollCapture_payload;

	@FXML
	public TextField identify_maxFARRequested;
	@FXML
	public TextField identify_maxFRRRequested;
	@FXML
	public TextField identify_newReferenceBIR;
	@FXML
	public TextField identify_timeout;
	@FXML
	public TextField identifyMatch_maxFARRequested;
	@FXML
	public TextField identifyMatch_maxFRRRequested;
	@FXML
	public TextField identifyMatch_newReferenceBIR;
	@FXML
	public TextField identifyMatch_sampleBIR;
	@FXML
	public TextField verify_maxFARRequested;
	@FXML
	public TextField verify_maxFRRRequested;
	@FXML
	public TextField verify_referenceBIR;
	@FXML
	public TextField verify_timeout;
	@FXML
	public TextField verify_adaptedBIR;
	@FXML
	public TextField verify_payload;
	@FXML
	public TextField verifyMatch_maxFARRequested;
	@FXML
	public TextField verifyMatch_maxFRRRequested;
	@FXML
	public TextField verifyMatch_referenceBIR;
	@FXML
	public TextField verifyMatch_adaptedBIR;
	@FXML
	public TextField verifyMatch_payload;
	@FXML
	public TextField verifyMatch_sampleBIR;
	@FXML
	public TextField processPrematchData_sampleBIR;
	@FXML
	public TextField processPrematchData_ProcessedBIR;
	@FXML
	public TextField processPrematchData_prematchDataBIR;

	@FXML
	public Label verify_result;
	@FXML
	public Label verify_FARAchieved;
	@FXML
	public Label verify_FRRAchieved;
	@FXML
	public Label verifyMatch_result;
	@FXML
	public Label verifyMatch_FARAchieved;
	@FXML
	public Label verifyMatch_FRRAchieved;

	@FXML
	public ListView<String> identify_referenceBIRPopulation;
	@FXML
	public ListView<String> identify_candidateRanking;
	@FXML
	public ListView<String> identifyMatch_referenceBIRPopulation;
	@FXML
	public ListView<String> identifyMatch_candidateRanking;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		service = new Biometrics();
		RequiredStateChecker.invokeThis(this, service);
	}

	/* ************************************************************************
	 * ************************ Action Handler *********************************
	 * ***********************************************************************
	 */

	@FXML
	public void handleDeviceEnable(ActionEvent e) {
		System.out.println("DevEnable");
		try {
			if (deviceEnabled.isSelected()) {
				((BillDispenser) service).setDeviceEnabled(true);
				setUpComboBoxes();

			} else {
				((BillDispenser) service).setDeviceEnabled(false);
			}
		} catch (JposException je) {
			JOptionPane.showMessageDialog(null, je.getMessage());
		}
		RequiredStateChecker.invokeThis(this, service);
	}

	@FXML
	public void handleSetAlgorithm(ActionEvent e) {
		try {
			((Biometrics) service).setAlgorithm(algorithm.getSelectionModel().getSelectedItem());
		} catch (JposException e1) {
			JOptionPane.showMessageDialog(null, e1.getMessage());
			e1.printStackTrace();
		}
	}

	@FXML
	public void handleSetRealTimeDataEnabled(ActionEvent e) {
		try {
			((Biometrics) service).setRealTimeDataEnabled(realTimeDataEnabled.getSelectionModel()
					.getSelectedItem());
		} catch (JposException e1) {
			JOptionPane.showMessageDialog(null, e1.getMessage());
			e1.printStackTrace();
		}
	}

	@FXML
	public void handleSetSensorOrientation(ActionEvent e) {
		try {
			((Biometrics) service).setSensorOrientation(BiometricsConstantMapper
					.getConstantNumberFromString(sensorOrientation.getSelectionModel().getSelectedItem()));
		} catch (JposException e1) {
			JOptionPane.showMessageDialog(null, e1.getMessage());
			e1.printStackTrace();
		}
	}

	@FXML
	public void handleSetSensorType(ActionEvent e) {
		try {
			((Biometrics)service).setSensorType(BiometricsConstantMapper.getConstantNumberFromString(sensorType.getSelectionModel().getSelectedItem()));
		} catch (JposException e1) {
			JOptionPane.showMessageDialog(null, e1.getMessage());
			e1.printStackTrace();
		}
	}

	@FXML
	public void handleBrowseBeginEnrollCaptureReferenceBIR(ActionEvent e) {
		FileChooser chooser = new FileChooser();
		chooser.setTitle("Choose Binary");
		File f = chooser.showOpenDialog(null);
		if (f != null) {
			this.beginEnrollCapture_referenceBIR.setText(f.getAbsolutePath());
		}
	}

	@FXML
	public void handleBrowseBeginEnrollCapturePayload(ActionEvent e) {
		FileChooser chooser = new FileChooser();
		chooser.setTitle("Choose Binary");
		File f = chooser.showOpenDialog(null);
		if (f != null) {
			this.beginEnrollCapture_payload.setText(f.getAbsolutePath());
		}
	}
	
	
	//TODO go further!!! 
	
	
	@FXML
	public void handleBeginEnrollCapture(ActionEvent e) {
		byte[] referenceBIR = readBytesFromFile(beginEnrollCapture_referenceBIR.getText());
		
		

		//((Biometrics)service).beginEnrollCapture(referenceBIR, payload)
	}

	@FXML
	public void handleBeginVerifyCapture(ActionEvent e) {

	}

	@FXML
	public void handleEndCapture(ActionEvent e) {

	}

	@FXML
	public void handleBrowseIdentifyReferenceBIR(ActionEvent e) {
		FileChooser chooser = new FileChooser();
		chooser.setTitle("Choose Binary");
		File f = chooser.showOpenDialog(null);
		if (f != null) {
			this.identify_referenceBIRPopulation.getItems().add(f.getAbsolutePath());
		}
	}

	@FXML
	public void handleIdentifyAddReferenceBIR(ActionEvent e) {

	}

	@FXML
	public void handleIdentify(ActionEvent e) {

	}

	@FXML
	public void handleBrowseIdentifyMatchReferenceBIR(ActionEvent e) {
		FileChooser chooser = new FileChooser();
		chooser.setTitle("Choose Binary");
		File f = chooser.showOpenDialog(null);
		if (f != null) {
			this.identifyMatch_referenceBIRPopulation.getItems().add(f.getAbsolutePath());
		}
	}

	@FXML
	public void handleIdentifyMatchAddReferenceBIR(ActionEvent e) {

	}

	@FXML
	public void handleIdentifyMatch(ActionEvent e) {

	}

	@FXML
	public void handleBrowseIdentifyMatchSampleBIR(ActionEvent e) {
		FileChooser chooser = new FileChooser();
		chooser.setTitle("Choose Binary");
		File f = chooser.showOpenDialog(null);
		if (f != null) {
			this.identifyMatch_sampleBIR.setText(f.getAbsolutePath());
		}
	}

	@FXML
	public void handleBrowseProcessPrematchDataSampleBIR(ActionEvent e) {
		FileChooser chooser = new FileChooser();
		chooser.setTitle("Choose Binary");
		File f = chooser.showOpenDialog(null);
		if (f != null) {
			this.processPrematchData_sampleBIR.setText(f.getAbsolutePath());
		}
	}

	@FXML
	public void handleProcessPrematchData(ActionEvent e) {

	}

	@FXML
	public void handleBrowseProcessPrematchDataPrematchDataBIR(ActionEvent e) {
		FileChooser chooser = new FileChooser();
		chooser.setTitle("Choose Binary");
		File f = chooser.showOpenDialog(null);
		if (f != null) {
			this.processPrematchData_prematchDataBIR.setText(f.getAbsolutePath());
		}
	}

	@FXML
	public void handleBrowseVerifyReferenceBIR(ActionEvent e) {
		FileChooser chooser = new FileChooser();
		chooser.setTitle("Choose Binary");
		File f = chooser.showOpenDialog(null);
		if (f != null) {
			this.verify_referenceBIR.setText(f.getAbsolutePath());
		}
	}

	@FXML
	public void handleVerify(ActionEvent e) {

	}

	@FXML
	public void handleBrowseVerifyAdaptedBIR(ActionEvent e) {
		FileChooser chooser = new FileChooser();
		chooser.setTitle("Choose Binary");
		File f = chooser.showOpenDialog(null);
		if (f != null) {
			this.verify_adaptedBIR.setText(f.getAbsolutePath());
		}
	}

	@FXML
	public void handleBrowseVerifyPayload(ActionEvent e) {
		FileChooser chooser = new FileChooser();
		chooser.setTitle("Choose Binary");
		File f = chooser.showOpenDialog(null);
		if (f != null) {
			this.verify_payload.setText(f.getAbsolutePath());
		}
	}

	@FXML
	public void handleBrowseVerifyMatchReferenceBIR(ActionEvent e) {
		FileChooser chooser = new FileChooser();
		chooser.setTitle("Choose Binary");
		File f = chooser.showOpenDialog(null);
		if (f != null) {
			this.verifyMatch_referenceBIR.setText(f.getAbsolutePath());
		}
	}

	@FXML
	public void handleVerifyMatch(ActionEvent e) {

	}

	@FXML
	public void handleBrowseVerifyMatchAdaptedBIR(ActionEvent e) {
		FileChooser chooser = new FileChooser();
		chooser.setTitle("Choose Binary");
		File f = chooser.showOpenDialog(null);
		if (f != null) {
			this.verifyMatch_adaptedBIR.setText(f.getAbsolutePath());
		}
	}

	@FXML
	public void handleBrowseVerifyMatchPayload(ActionEvent e) {
		FileChooser chooser = new FileChooser();
		chooser.setTitle("Choose Binary");
		File f = chooser.showOpenDialog(null);
		if (f != null) {
			this.verifyMatch_payload.setText(f.getAbsolutePath());
		}
	}

	@FXML
	public void handleBrowseVerifyMatchSampleBIR(ActionEvent e) {
		FileChooser chooser = new FileChooser();
		chooser.setTitle("Choose Binary");
		File f = chooser.showOpenDialog(null);
		if (f != null) {
			this.verifyMatch_sampleBIR.setText(f.getAbsolutePath());
		}
	}

	/*
	 * Set Up all ComboBoxes
	 */

	private void setUpAlgorithm() {
		algorithm.getItems().clear();

		// Default value
		algorithm.getItems().add(0);

		String[] algorithms = null;
		try {
			algorithms = ((Biometrics) service).getAlgorithmList().split(";");
		} catch (JposException e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
			e.printStackTrace();
		}
		for (int i = 1; i <= algorithms.length; i++) {
			algorithm.getItems().add(i);
		}

		algorithm.setValue(0);
	}

	private void setUpRealTimeDataEnabled() {
		realTimeDataEnabled.getItems().clear();
		realTimeDataEnabled.getItems().add(true);
		realTimeDataEnabled.getItems().add(false);
		realTimeDataEnabled.setValue(true);
	}

	private void setUpSensorOrientation() {
		sensorOrientation.getItems().clear();
		sensorOrientation.getItems().add(BiometricsConstantMapper.BIO_SO_NORMAL.getConstant());
		sensorOrientation.getItems().add(BiometricsConstantMapper.BIO_SO_RIGHT.getConstant());
		sensorOrientation.getItems().add(BiometricsConstantMapper.BIO_SO_LEFT.getConstant());
		sensorOrientation.getItems().add(BiometricsConstantMapper.BIO_SO_INVERTED.getConstant());
		sensorOrientation.setValue(BiometricsConstantMapper.BIO_SO_NORMAL.getConstant());
	}

	private void setUpSensorType() {
		sensorType.getItems().clear();
		sensorType.getItems().add(BiometricsConstantMapper.BIO_ST_FACIAL_FEATURES.getConstant());
		sensorType.getItems().add(BiometricsConstantMapper.BIO_ST_FINGERPRINT.getConstant());
		sensorType.getItems().add(BiometricsConstantMapper.BIO_ST_GAIT.getConstant());
		sensorType.getItems().add(BiometricsConstantMapper.BIO_ST_HAND_GEOMETRY.getConstant());
		sensorType.getItems().add(BiometricsConstantMapper.BIO_ST_IRIS.getConstant());
		sensorType.getItems().add(BiometricsConstantMapper.BIO_ST_KEYSTROKE_DYNAMICS.getConstant());
		sensorType.getItems().add(BiometricsConstantMapper.BIO_ST_LIP_MOVEMENT.getConstant());
		sensorType.getItems().add(BiometricsConstantMapper.BIO_ST_PASSWORD.getConstant());
		sensorType.getItems().add(BiometricsConstantMapper.BIO_ST_RETINA.getConstant());
		sensorType.getItems().add(BiometricsConstantMapper.BIO_ST_SIGNATURE_DYNAMICS.getConstant());
		sensorType.getItems().add(BiometricsConstantMapper.BIO_ST_THERMAL_FACE_IMAGE.getConstant());
		sensorType.getItems().add(BiometricsConstantMapper.BIO_ST_THERMAL_HAND_IMAGE.getConstant());
		sensorType.getItems().add(BiometricsConstantMapper.BIO_ST_VOICE.getConstant());
		sensorType.setValue(BiometricsConstantMapper.BIO_ST_FACIAL_FEATURES.getConstant());
	}

	private void setUpIdentifyFARPrecedence() {
		identify_FARPrecedence.getItems().clear();
		identify_FARPrecedence.getItems().add(true);
		identify_FARPrecedence.getItems().add(false);
		identify_FARPrecedence.setValue(true);
	}

	private void setUpIdentifyMatchFARPrecedence() {
		identifyMatch_FARPrecedence.getItems().clear();
		identifyMatch_FARPrecedence.getItems().add(true);
		identifyMatch_FARPrecedence.getItems().add(false);
		identifyMatch_FARPrecedence.setValue(true);
	}

	private void setUpVerifyFARPrecedence() {
		verify_FARPrecedence.getItems().clear();
		verify_FARPrecedence.getItems().add(true);
		verify_FARPrecedence.getItems().add(false);
		verify_FARPrecedence.setValue(true);
	}

	private void setUpVerifyMatchFARPrecedence() {
		verifyMatch_FARPrecedence.getItems().clear();
		verifyMatch_FARPrecedence.getItems().add(true);
		verifyMatch_FARPrecedence.getItems().add(false);
		verifyMatch_FARPrecedence.setValue(true);
	}

	private void setUpComboBoxes() {
		setUpLogicalNameComboBox();
		setUpAlgorithm();
		setUpRealTimeDataEnabled();
		setUpSensorOrientation();
		setUpSensorType();
		setUpIdentifyFARPrecedence();
		setUpIdentifyMatchFARPrecedence();
		setUpVerifyFARPrecedence();
		setUpVerifyMatchFARPrecedence();
	}

	private void setUpLogicalNameComboBox() {
		if (!LogicalNameGetter.getLogicalNamesByCategory("BillDispenser").isEmpty()) {
			logicalName.setItems(LogicalNameGetter.getLogicalNamesByCategory("BillDispenser"));
		}
	}
	
	/** 
	 * Read the given binary file, and return its contents as a byte array.
	 * 
	 */ 
	private static byte[] readBytesFromFile(String aInputFileName){
	    File file = new File(aInputFileName);
	    byte[] result = new byte[(int)file.length()];
	    try {
	      InputStream input = null;
	      try {
	        int totalBytesRead = 0;
	        input = new BufferedInputStream(new FileInputStream(file));
	        while(totalBytesRead < result.length){
	          int bytesRemaining = result.length - totalBytesRead;
	          //input.read() returns -1, 0, or more :
	          int bytesRead = input.read(result, totalBytesRead, bytesRemaining); 
	          if (bytesRead > 0){
	            totalBytesRead = totalBytesRead + bytesRead;
	          }
	        }
	        /*
	         the above style is a bit tricky: it places bytes into the 'result' array; 
	         'result' is an output parameter;
	         the while loop usually has a single iteration only.
	        */
	      }
	      finally {
	        input.close();
	      }
	    }
	    catch (FileNotFoundException ex) {

			JOptionPane.showMessageDialog(null, ex.getMessage());
			ex.printStackTrace();
	    }
	    catch (IOException ex) {
			JOptionPane.showMessageDialog(null, ex.getMessage());
			ex.printStackTrace();
	    	
	    }
	    return result;
	  }
	

}
