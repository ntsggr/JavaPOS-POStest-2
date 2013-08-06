package postest2;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.CheckBoxListCell;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.util.Callback;

public class POSTest2Controller implements Initializable {

	@FXML
	private ListView<String> listAllDevices;
	@FXML
	transient private ListView<String> listFavorites;

	@FXML
	private ToggleButton toggleButtonAllDevices;
	@FXML
	private ToggleButton toggleButtonFavorites;

	@FXML
	private AnchorPane anchorPaneRight;

	@FXML
	private ObservableList<String> favoriteDevices; // contains the favorites devices
	
	//private FileOutputStream fileOut;
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		//System.out.println(listAllDevices.getItems().get(1));
//		File f = new File("favorites.txt");
//		if (f.exists()) {
			favoriteDevices = FXCollections.observableArrayList();
//		} else {
//			System.out.println("file doesnt exist");
//			favoriteDevices = FXCollections.observableArrayList();
//		}
		
		// group for the toggles buttons
		final ToggleGroup toggleGroup = new ToggleGroup();
		// set user data to compare which togglebutton is selected
		toggleButtonAllDevices.setUserData("all");
		toggleButtonAllDevices.setToggleGroup(toggleGroup);
		// set user data to compare which togglebutton is selected
		toggleButtonFavorites.setUserData("favorites");
		toggleButtonFavorites.setToggleGroup(toggleGroup);
		toggleButtonAllDevices.setSelected(true);
		setPanel("About");
		
		// The setCellFactory method redefines the implementation of the list cell
		listAllDevices.setCellFactory(new Callback<ListView<String>, ListCell<String>>() {
			@Override
			public ListCell<String> call(ListView<String> param) {
				return new XCell();
			}
		});

		// Show the respective device panel for each selected item from the list "All devices"
		listAllDevices.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
			public void changed(ObservableValue<? extends String> ov, String old_val, String new_val) {
				System.out.println(old_val + ", " + new_val);
				setPanel(new_val);
			}
		});
		
		// Show the respective device panel for each selected item from the list "Favorites"
		listFavorites.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
			public void changed(ObservableValue<? extends String> ov, String old_val, String new_val) {
				System.out.println(old_val + ", " + new_val);
				setPanel(new_val);
			}
		});
		
		// Show "All devices" or "Favorites"
		toggleGroup.selectedToggleProperty().addListener(new ChangeListener<Toggle>() {
			public void changed(ObservableValue<? extends Toggle> ov, Toggle toggle, Toggle new_toggle) {
				if (new_toggle != null) {
					if (new_toggle.getUserData().equals("all")) {
						listAllDevices.setVisible(true);
						listFavorites.setVisible(false);
					} else {
						listAllDevices.setVisible(false);
						listFavorites.setVisible(true);
					}
				}
			}
		});
	}

	// Set the panel for each clicked device
	private void setPanel(String panel) {
		Parent root = null;
		try {
			root = FXMLLoader.load(getClass().getResource("gui/" + panel + ".fxml"));
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		anchorPaneRight.getChildren().clear();
		anchorPaneRight.getChildren().addAll(root.getChildrenUnmodifiable());
	}
	
	/*private void saveFavorites(String favName) {
		try {
		    PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("favorites.txt", true)));
		    out.println(favName);
		    out.close();
		} catch (IOException e) {
		}
	}*/
	
	/*private void retrieveFavorites() {
		BufferedReader br = null;
		try {
			String sCurrentLine;
			br = new BufferedReader(new FileReader("favorites.txt"));
			while ((sCurrentLine = br.readLine()) != null) {
				favoriteDevices.add(sCurrentLine);
				System.out.println(sCurrentLine);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (br != null)br.close();
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
	}*/
	
	// Add a checkbox for each ListView item.
	class XCell extends ListCell<String> {
		
		HBox hbox = new HBox();
		Label label = new Label("(empty)");
		Pane pane = new Pane();
		CheckBox checkBox = new CheckBox();
		String lastItem;
		
		public XCell() {
			super();
			hbox.getChildren().addAll(label, pane, checkBox);
			HBox.setHgrow(pane, Priority.ALWAYS);
			checkBox.setOnAction(new EventHandler<ActionEvent>() {
				@Override
				public void handle(ActionEvent event) {
					System.out.println(favoriteDevices.size());
					if (favoriteDevices.contains(lastItem)) {
						favoriteDevices.remove(lastItem);
						listFavorites.setItems(favoriteDevices);
					} else {
						favoriteDevices.add(lastItem);
						listFavorites.setItems(favoriteDevices);
					}
					System.out.println(lastItem + " : " + event);
				}
			});
		}
		
		@Override
		protected void updateItem(String item, boolean empty) {
			super.updateItem(item, empty);
			setText(null); // No text in label of super class
			if (empty) {
				lastItem = null;
				setGraphic(null);
			} else {
				lastItem = item;
				label.setText(item != null ? item : "<null>");
				setGraphic(hbox);
			}
		}
		
	} // end of XCell class

}

