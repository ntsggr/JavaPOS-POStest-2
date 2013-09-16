package postest2;

import java.awt.Dimension;
import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
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

import jpos.Biometrics;
import jpos.JposException;

import org.apache.xerces.parsers.DOMParser;
import org.w3c.dom.Document;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

public class BiometricsController extends CommonController implements Initializable {

	@FXML
	@RequiredState(JposState.ENABLED)
	public TabPane functionPane;
	
	@FXML
	@RequiredState(JposState.CLAIMED)
	public TabPane notEnabledTab;
	
	
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
		setUpTooltips();
		service = new Biometrics();
		RequiredStateChecker.invokeThis(this, service);
		setUpLogicalNameComboBox("Biometrics");
	}

	/* ************************************************************************
	 * ************************ Action Handler *********************************
	 * ***********************************************************************
	 */

	/**
	 * Shows statistics of device if they are supported by the device
	 */
	@Override
	@FXML
	public void handleInfo(ActionEvent e) {
		try {
			IMapWrapper bcm = new BiometricsConstantMapper();
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
			((Biometrics) service).retrieveStatistics(stats);
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

	@FXML
	public void handleDeviceEnable(ActionEvent e) {
		try {
			if (deviceEnabled.isSelected()) {
				((Biometrics) service).setDeviceEnabled(true);
				setUpComboBoxes();

			} else {
				((Biometrics) service).setDeviceEnabled(false);
			}
		} catch (JposException je) {
			je.printStackTrace();
			JOptionPane.showMessageDialog(null, je.getMessage());
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
			((Biometrics) service).setSensorType(BiometricsConstantMapper
					.getConstantNumberFromString(sensorType.getSelectionModel().getSelectedItem()));
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

	@FXML
	public void handleBeginEnrollCapture(ActionEvent e) {
		if (beginEnrollCapture_referenceBIR.getText().isEmpty()
				|| beginEnrollCapture_payload.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Every Field should have a value!");
		} else {
			byte[] referenceBIR = readBytesFromFile(beginEnrollCapture_referenceBIR.getText());
			byte[] payload = readBytesFromFile(beginEnrollCapture_payload.getText());
			try {
				((Biometrics) service).beginEnrollCapture(referenceBIR, payload);
			} catch (JposException e1) {
				JOptionPane.showMessageDialog(null, e1.getMessage());
				e1.printStackTrace();
			}
		}
	}

	@FXML
	public void handleBeginVerifyCapture(ActionEvent e) {
		try {
			((Biometrics) service).beginVerifyCapture();
		} catch (JposException e1) {
			JOptionPane.showMessageDialog(null, e1.getMessage());
			e1.printStackTrace();
		}

	}

	@FXML
	public void handleEndCapture(ActionEvent e) {
		try {
			((Biometrics) service).endCapture();
		} catch (JposException e1) {
			JOptionPane.showMessageDialog(null, e1.getMessage());
			e1.printStackTrace();
		}
	}

	@FXML
	public void handleBrowseIdentifyReferenceBIR(ActionEvent e) {
		FileChooser chooser = new FileChooser();
		chooser.setTitle("Choose Binary");
		File f = chooser.showOpenDialog(null);
		if (f != null) {
			this.identify_newReferenceBIR.setText(f.getAbsolutePath());
		}
	}

	@FXML
	public void handleIdentifyAddReferenceBIR(ActionEvent e) {
		if (identify_newReferenceBIR.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Every Field should have a value!");
		} else {
			identify_referenceBIRPopulation.getItems().add(identify_newReferenceBIR.getText());
		}
	}

	@FXML
	public void handleIdentify(ActionEvent e) {
		if (identify_maxFARRequested.getText().isEmpty() || identify_maxFRRRequested.getText().isEmpty()
				|| identify_referenceBIRPopulation.getItems().isEmpty()
				|| identify_timeout.getText().isEmpty()) {

			JOptionPane.showMessageDialog(null, "Every Field should have a value!");
		} else {
			try {
				int maxFARRequested = Integer.parseInt(identify_maxFARRequested.getText());
				int maxFRRRequested = Integer.parseInt(identify_maxFRRRequested.getText());
				boolean FARPrecedence = identify_FARPrecedence.getSelectionModel().getSelectedItem();
				byte[][] referenceBIRPopulation = new byte[identify_referenceBIRPopulation.getItems().size()][];

				for (int i = 0; i < identify_referenceBIRPopulation.getItems().size(); i++) {
					referenceBIRPopulation[i] = readBytesFromFile(identify_referenceBIRPopulation.getItems()
							.get(i));
				}
				int[][] candidateRanking = null;
				int timeout = Integer.parseInt(identify_timeout.getText());

				((Biometrics) service).identify(maxFARRequested, maxFRRRequested, FARPrecedence,
						referenceBIRPopulation, candidateRanking, timeout);

				identify_candidateRanking.getItems().clear();
				try {
					for (int i = 0; i < candidateRanking.length; i++) {
						identify_candidateRanking.getItems().add("" + candidateRanking[i][0]);
					}
				} catch (NullPointerException e1) {
					JOptionPane.showMessageDialog(null, e1.getMessage());
					e1.printStackTrace();
				}
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
	public void handleBrowseIdentifyMatchReferenceBIR(ActionEvent e) {
		FileChooser chooser = new FileChooser();
		chooser.setTitle("Choose Binary");
		File f = chooser.showOpenDialog(null);
		if (f != null) {
			this.identifyMatch_newReferenceBIR.setText(f.getAbsolutePath());
		}
	}

	@FXML
	public void handleIdentifyMatchAddReferenceBIR(ActionEvent e) {
		if (identifyMatch_newReferenceBIR.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Every Field should have a value!");
		} else {
			identifyMatch_referenceBIRPopulation.getItems().add(identifyMatch_newReferenceBIR.getText());
		}
	}

	@FXML
	public void handleIdentifyMatch(ActionEvent e) {
		if (identifyMatch_maxFARRequested.getText().isEmpty()
				|| identifyMatch_maxFRRRequested.getText().isEmpty()
				|| identifyMatch_referenceBIRPopulation.getItems().isEmpty()
				|| identifyMatch_sampleBIR.getText().isEmpty()) {

			JOptionPane.showMessageDialog(null, "Every Field should have a value!");
		} else {
			try {
				int maxFARRequested = Integer.parseInt(identifyMatch_maxFARRequested.getText());
				int maxFRRRequested = Integer.parseInt(identifyMatch_maxFRRRequested.getText());
				boolean FARPrecedence = identifyMatch_FARPrecedence.getSelectionModel().getSelectedItem();
				byte[][] referenceBIRPopulation = new byte[identifyMatch_referenceBIRPopulation.getItems().size()][];

				for (int i = 0; i < identifyMatch_referenceBIRPopulation.getItems().size(); i++) {
					referenceBIRPopulation[i] = readBytesFromFile(identifyMatch_referenceBIRPopulation.getItems().get(i));
				}
				int[][] candidateRanking = null;
				byte[] sampleBIR = readBytesFromFile(identifyMatch_sampleBIR.getText());

				((Biometrics) service).identifyMatch(maxFARRequested, maxFRRRequested, FARPrecedence,
						sampleBIR, referenceBIRPopulation, candidateRanking);
				identifyMatch_candidateRanking.getItems().clear();
				try {
					for (int i = 0; i < candidateRanking.length; i++) {
						identifyMatch_candidateRanking.getItems().add("" + candidateRanking[i][0]);
					}
				} catch (NullPointerException e1) {
					JOptionPane.showMessageDialog(null, e1.getMessage());
					e1.printStackTrace();
				}

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
		if (processPrematchData_sampleBIR.getText().isEmpty()
				|| processPrematchData_prematchDataBIR.getText().isEmpty()) {

			JOptionPane.showMessageDialog(null, "Every Field should have a value!");
		} else {
			byte[] sampleBIR = readBytesFromFile(processPrematchData_sampleBIR.getText());
			byte[] prematchDataBIR = readBytesFromFile(processPrematchData_prematchDataBIR.getText());
			byte[][] processedBIR = null;

			try {
				((Biometrics) service).processPrematchData(sampleBIR, prematchDataBIR, processedBIR);
				writeBytesToFile(processedBIR, "ProcessPrematchData_ProcessedBIR.bin");
				processPrematchData_ProcessedBIR
						.setText("$POSTEST_HOME/ProcessPrematchData_ProcessedBIR.bin");
			} catch (JposException e1) {
				JOptionPane.showMessageDialog(null, e1.getMessage());
				e1.printStackTrace();
			}
		}

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
		if (verify_maxFARRequested.getText().isEmpty() || verify_maxFRRRequested.getText().isEmpty()
				|| verify_referenceBIR.getText().isEmpty() || verify_adaptedBIR.getText().isEmpty()
				|| verify_payload.getText().isEmpty() || verify_timeout.getText().isEmpty()) {

			JOptionPane.showMessageDialog(null, "Every Field should have a value!");
		} else {
			try {
				int maxFARRequested = Integer.parseInt(verify_maxFARRequested.getText());
				int maxFRRRequested = Integer.parseInt(verify_maxFRRRequested.getText());
				boolean FARPrecedence = verify_FARPrecedence.getSelectionModel().getSelectedItem();
				byte[] referenceBIR = readBytesFromFile(verify_referenceBIR.getText());
				byte[][] adaptedBIR = new byte[1][];
				adaptedBIR[0] = readBytesFromFile(verify_adaptedBIR.getText());
				boolean[] result = new boolean[1];
				int[] FARAchieved = new int[1];
				int[] FRRAchieved = new int[1];
				byte[][] payload = new byte[1][];
				payload[0] = readBytesFromFile(verify_payload.getText());
				int timeout = Integer.parseInt(verify_timeout.getText());

				((Biometrics) service).verify(maxFARRequested, maxFRRRequested, FARPrecedence, referenceBIR,
						adaptedBIR, result, FARAchieved, FRRAchieved, payload, timeout);
				verify_result.setText("" + result[0]);
				verify_FARAchieved.setText("" + FARAchieved[0]);
				verify_FRRAchieved.setText("" + FRRAchieved[0]);

			} catch (JposException e1) {
				JOptionPane.showMessageDialog(null, e1.getMessage());
				e1.printStackTrace();
			} catch (NumberFormatException e1) {
				JOptionPane.showMessageDialog(null, e1.getMessage());
				e1.printStackTrace();
			} catch (NullPointerException e1) {
				JOptionPane.showMessageDialog(null, e1.getMessage());
				e1.printStackTrace();
			}
		}
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
		if (verifyMatch_maxFARRequested.getText().isEmpty()
				|| verifyMatch_maxFRRRequested.getText().isEmpty()
				|| verifyMatch_referenceBIR.getText().isEmpty() || verifyMatch_adaptedBIR.getText().isEmpty()
				|| verifyMatch_payload.getText().isEmpty() || verifyMatch_sampleBIR.getText().isEmpty()) {

			JOptionPane.showMessageDialog(null, "Every Field should have a value!");
		} else {
			try {
				int maxFARRequested = Integer.parseInt(verifyMatch_maxFARRequested.getText());
				int maxFRRRequested = Integer.parseInt(verifyMatch_maxFRRRequested.getText());
				boolean FARPrecedence = verifyMatch_FARPrecedence.getSelectionModel().getSelectedItem();
				byte[] sampleBIR = readBytesFromFile(verifyMatch_sampleBIR.getText());
				byte[] referenceBIR = readBytesFromFile(verifyMatch_referenceBIR.getText());
				byte[][] adaptedBIR = new byte[1][];
				adaptedBIR[0] = readBytesFromFile(verifyMatch_adaptedBIR.getText());
				boolean[] result = new boolean[1];
				int[] FARAchieved = new int[1];
				int[] FRRAchieved = new int[1];
				byte[][] payload = new byte[1][];
				payload[0] = readBytesFromFile(verifyMatch_payload.getText());

				((Biometrics) service).verifyMatch(maxFARRequested, maxFRRRequested, FARPrecedence,
						sampleBIR, referenceBIR, adaptedBIR, result, FARAchieved, FRRAchieved, payload);
				verifyMatch_result.setText("" + result[0]);
				verifyMatch_FARAchieved.setText("" + FARAchieved[0]);
				verifyMatch_FRRAchieved.setText("" + FRRAchieved[0]);

			} catch (JposException e1) {
				JOptionPane.showMessageDialog(null, e1.getMessage());
				e1.printStackTrace();
			} catch (NumberFormatException e1) {
				JOptionPane.showMessageDialog(null, e1.getMessage());
				e1.printStackTrace();
			} catch (NullPointerException e1) {
				JOptionPane.showMessageDialog(null, e1.getMessage());
				e1.printStackTrace();
			}
		}
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
		setUpAlgorithm();
		setUpRealTimeDataEnabled();
		setUpSensorOrientation();
		setUpSensorType();
		setUpIdentifyFARPrecedence();
		setUpIdentifyMatchFARPrecedence();
		setUpVerifyMatchFARPrecedence();
		setUpVerifyFARPrecedence();
	}


	/**
	 * Write a byte array to the given file. Writing binary data is
	 * significantly simpler than reading it.
	 */
	private static void writeBytesToFile(byte[][] aInput, String aOutputFileName) {
		try {
			OutputStream output = null;
			try {
				output = new BufferedOutputStream(new FileOutputStream(aOutputFileName));
				output.write(aInput[0]);
			} finally {
				output.close();
			}
		} catch (FileNotFoundException ex) {
			JOptionPane.showMessageDialog(null, ex.getMessage());
			ex.printStackTrace();
		} catch (IOException ex) {
			JOptionPane.showMessageDialog(null, ex.getMessage());
			ex.printStackTrace();
		}
	}

}
