import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.InputMismatchException;

import javax.xml.soap.Name;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.text.TextAlignment;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;

public class Driver extends Application{

	public static void main(String[] args) {
		Application.launch(args);
	}

	
	@Override
	public void start(Stage stage) throws Exception,InputMismatchException,NameMismatchException {
		//to save orders here
		ArrayList<PizzaOrder> orders = new ArrayList<>();
		//where all the action goes
		BorderPane mainPane = new BorderPane();
		//contains every other node except nodes in hbox
		GridPane grid = new GridPane();
		//which contains the 3 buttons in the bottom
		HBox hbox = new HBox();
		//to add a background and make the applet even cooler 
		StackPane backPane = new StackPane();
		Image backgroundimg = new Image("background1.jpg");
		ImageView backView = new ImageView(backgroundimg);
		backView.setFitHeight(320);
		backView.setFitWidth(350);
		backPane.getChildren().add(backView);
		
		// Configuring The Grid
		grid.setHgap(10);
		grid.setVgap(10);
		ColumnConstraints col1 = new ColumnConstraints();
        col1.setPercentWidth(30);
        ColumnConstraints col2 = new ColumnConstraints();
        col2.setPercentWidth(70);
        grid.getColumnConstraints().addAll(col1,col2);
		
		//adding items to the grid (text fields and labels)
		Label nameLabel = new Label("Customer Name");
		nameLabel.setTextAlignment(TextAlignment.CENTER);
		grid.add(nameLabel, 0, 0);
		
		Label orderTypeLabel = new Label("Order Type");
		orderTypeLabel.setTextAlignment(TextAlignment.CENTER);
		grid.add(orderTypeLabel, 0,1);
		
		Label sizeLabel = new Label("Pizza Size");
		sizeLabel.setTextAlignment(TextAlignment.CENTER);
		grid.add(sizeLabel, 0,2);
		
		Label toppingsLabel = new Label("Toppings");
		toppingsLabel.setTextAlignment(TextAlignment.CENTER);
		grid.add(toppingsLabel, 0,3); 
		
		Label toppingsPriceLabel = new Label("Toppings Price");
		toppingsPriceLabel.setTextAlignment(TextAlignment.CENTER);
		grid.add(toppingsPriceLabel, 0,4);
		
		Label orderPriceLabel = new Label("Order Price");
		orderPriceLabel.setTextAlignment(TextAlignment.CENTER);
		grid.add(orderPriceLabel, 0,5);
		
		TextField nameText = new TextField();
		nameText.setMaxWidth(200);
		grid.add(nameText, 1, 0);
		
		
		//created combobox to choose order type
		ComboBox<String> comboBox = new ComboBox<String>();
		comboBox.getItems().addAll(
		        "To Go",
		        "Delivery",
		        "Seated"
		    );
		//selected To Go as default
		comboBox.getSelectionModel().selectFirst();
		comboBox.setMaxWidth(200);
		grid.add(comboBox, 1,1);
		//created an hbox to add size radioButtons to the grid
		HBox sizeHbox = new HBox();
		sizeHbox.setSpacing(15);
		RadioButton small = new RadioButton("Small");
		RadioButton medium = new RadioButton("Medium");
		RadioButton large = new RadioButton("Large");
		//created a toggle group so the radio buttons can act as a group and allow the user to choose a item only
		ToggleGroup sizeGroup = new ToggleGroup();
		small.setToggleGroup(sizeGroup);
		medium.setToggleGroup(sizeGroup);
		large.setToggleGroup(sizeGroup);
		//defined small as default
		small.setSelected(true);
		sizeHbox.getChildren().addAll(small,medium,large);
		grid.add(sizeHbox, 1,2);
		//add grid and 3 buttons hbox in their places in the main border pane
		mainPane.setCenter(grid);
		mainPane.setBottom(hbox);
		// created flow pane to contain toppings and add it to gridpane
		FlowPane toppingsPane = new FlowPane();
		toppingsPane.setHgap(15);
		CheckBox onion = new CheckBox("Onion");
		CheckBox olive = new CheckBox("Olive");
		CheckBox peppers = new CheckBox("Peppers");
		toppingsPane.getChildren().addAll(onion,olive,peppers);
		grid.add(toppingsPane, 1,3);
		
		TextField toppingPriceField = new TextField("10.0");
		toppingPriceField.setMaxWidth(200);
		grid.add(toppingPriceField, 1, 4);
		
		Label orderFinalPriceLabel = new Label("0.0");
		grid.add(orderFinalPriceLabel, 1,5);
		
		//cereated bottom buttons and added them to bottom hbox
		Button processOrderButton = new Button("Process Order");
		Button printOrdersButton = new Button("Print Orders");
		Button resetButton = new Button("RESET");
		hbox.getChildren().addAll(processOrderButton,printOrdersButton,resetButton);
		//spacing between nodes in the hbox
		hbox.setSpacing(40);
		//make reset button functional
		resetButton.setOnAction(e -> {
			nameText.setText("");
			comboBox.getSelectionModel().selectFirst();
			small.setSelected(true);
			onion.setSelected(false);
			peppers.setSelected(false);
			olive.setSelected(false);
			toppingPriceField.setText("10.0");
			orderFinalPriceLabel.setText("0.0");
		});
		
		Label tipRateLabel = new Label("Tip Rate");
		TextField tipRateField = new TextField();
		
		Label serviceChargeLabel = new Label("Service Charge");
		TextField serviceChargeField = new TextField();
		
		comboBox.setOnAction(e -> {
			if (comboBox.getValue().toString().equals("Delivery")){
				small.setSelected(true);
				onion.setSelected(false);
				peppers.setSelected(false);
				olive.setSelected(false);
				orderFinalPriceLabel.setText("0.0");
				toppingPriceField.setText("10.0");
				grid.getChildren().remove(serviceChargeField);
				grid.getChildren().remove(serviceChargeLabel);
				tipRateField.setText("");
				grid.add(tipRateLabel, 0,6);
				grid.add(tipRateField, 1,6);
			}
			else if (comboBox.getValue().toString().equals("Seated")){
				
				small.setSelected(true);
				onion.setSelected(false);
				peppers.setSelected(false);
				olive.setSelected(false);
				toppingPriceField.setText("10.0");
				orderFinalPriceLabel.setText("0.0");
				grid.getChildren().remove(tipRateField);
				grid.getChildren().remove(tipRateLabel);
				serviceChargeField.setText("");
				grid.add(serviceChargeLabel, 0,6);
				grid.add(serviceChargeField, 1,6);
				
			}
			else {
				small.setSelected(true);
				onion.setSelected(false);
				peppers.setSelected(false);
				olive.setSelected(false);
				toppingPriceField.setText("10.0");
				orderFinalPriceLabel.setText("0.0");
				grid.getChildren().remove(tipRateField);
				grid.getChildren().remove(tipRateLabel);
				grid.getChildren().remove(serviceChargeField);
				grid.getChildren().remove(serviceChargeLabel);
			}
			
				
			});
		//making alert to let the user know that order was created successfully
		Alert successAlert = new Alert(AlertType.INFORMATION);
		successAlert.setTitle("Order Added Successfully !");
		successAlert.setContentText("Your Order Was Created Successfully!\nThank You For Visiting Us!");
		((Stage)successAlert.getDialogPane().getScene().getWindow()).getIcons().add(new Image("information.png"));
		successAlert.setHeaderText("Success Message!");
		//making process order button functional  
		processOrderButton.setOnAction(f->{
			try{
				int toppingsCount = 0,size;
				if (onion.isSelected())
					toppingsCount++;
				if (olive.isSelected())
					toppingsCount++;
				if (peppers.isSelected())
					toppingsCount++;
				RadioButton selectedSize = (RadioButton) sizeGroup.getSelectedToggle() ;
				if (selectedSize.getText().equals("Small"))
					size = 1;
				else if (selectedSize.getText().equals("Medium"))
					size = 2;
				else
					size = 3;
				//to check if name contains numbers or other symbols 
				if ( !nameText.getText().matches("[a-zA-z ,']+") )
					throw new NameMismatchException();
				
				if (comboBox.getValue().toString().equals("Delivery")){
					if ( !toppingPriceField.getText().matches("[0-9.]+") )
						throw new InputMismatchException("Topping Price Must Be Double!");
					if ( !tipRateField.getText().matches("[0-9.]+") )
						throw new InputMismatchException("Tip Rate Must Be Double!");
					orders.add(new Delivery(nameText.getText(),size, toppingsCount, Double.parseDouble(toppingPriceField.getText()), Double.parseDouble(tipRateField.getText())));
					orderFinalPriceLabel.setText(orders.get(orders.size()-1).calculateOrderPrice()+"");
					successAlert.showAndWait();
					
				}
				else if (comboBox.getValue().toString().equals("Seated")){
					if ( !toppingPriceField.getText().matches("[0-9.]+") )
						throw new InputMismatchException("Topping Price Must Be Double!");
					if ( !serviceChargeField.getText().matches("[0-9.]+") )
						throw new InputMismatchException("Service Charge Must Be Double!");
					
					orders.add(new Seated(nameText.getText(),size,toppingsCount,Double.parseDouble(toppingPriceField.getText()),Double.parseDouble(serviceChargeField.getText())));
					orderFinalPriceLabel.setText(orders.get(orders.size()-1).calculateOrderPrice()+"");
					successAlert.showAndWait();
				}
				else{ 
					if ( !toppingPriceField.getText().matches("[0-9.]+") )
						throw new InputMismatchException("Topping Price Must Be Double!");
					orders.add(new ToGo(nameText.getText(),size, toppingsCount, Double.parseDouble(toppingPriceField.getText())));
					orderFinalPriceLabel.setText(orders.get(orders.size()-1).calculateOrderPrice()+"");
					successAlert.showAndWait();
				}
				
				
			
			} catch (InputMismatchException e){
				Alert alert = new Alert(AlertType.ERROR);
				alert.setTitle("No Toppings Choosen!");
				alert.setContentText(e.getMessage());
				((Stage)alert.getDialogPane().getScene().getWindow()).getIcons().add(new Image("erroricon.png"));
				alert.showAndWait();
			} catch(NameMismatchException e){
				Alert alert = new Alert(AlertType.ERROR);
				alert.setTitle("Name Input Invalid!");
				alert.setContentText(e.getMessage());
				((Stage)alert.getDialogPane().getScene().getWindow()).getIcons().add(new Image("erroricon.png"));
				alert.showAndWait();
			}
		});
		
		printOrdersButton.setOnAction(e -> {
			try {
			//Sorting ArrayList 
			java.util.Collections.sort(orders);
			//Creating Table To View Data In
			TableView<PizzaOrder> table = new TableView<PizzaOrder>();
			//set text when table is empty
			table.setPlaceholder(new Label("No Orders Yet."));

			//Creating Name Column
			TableColumn<PizzaOrder,String> nameCol = new TableColumn<PizzaOrder,String>("Names");
			nameCol.setMinWidth(200);
			nameCol.setCellValueFactory(new PropertyValueFactory<>("customerName"));
			//Creating Price Column
			TableColumn<PizzaOrder,Double> priceCol = new TableColumn<PizzaOrder,Double>("Orders Prices ");
			priceCol.setMinWidth(200);
			priceCol.setCellValueFactory(new PropertyValueFactory<>("orderPrice"));
			//adding both columns to table
			table.getColumns().addAll(nameCol,priceCol);
			//adding information to the table
			table.setItems(getOrders(orders));
			
			//adding table to stackpane
			StackPane root = new StackPane();
			root.getChildren().add(table);
			Stage stage2 = new Stage();
			//defining 2nd stage and adding table to it
			stage2.setScene(new Scene(root,400,400));
			stage2.setTitle("Orders Table");
			stage2.getIcons().add(new Image("icon.png"));
			stage2.show();
			
			//print data to a file the user chooses it's directory
 			FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Save Orders Info");
            fileChooser.getExtensionFilters().addAll( new ExtensionFilter("Text Files", "*.txt") , new ExtensionFilter("All Files", "*.*") );
			fileChooser.setInitialFileName("ordersfile.txt");
            File ordersFile = fileChooser.showSaveDialog(stage);
			PrintWriter out = new PrintWriter(ordersFile);
            //printing data out to the file user choosen
            for (int i=0;i<orders.size();i++)
            	out.println(orders.get(i).getCustomerName() + "\t" + orders.get(i).calculateOrderPrice());
			out.close();
			}catch(FileNotFoundException e1){
				Alert alert = new Alert(AlertType.ERROR);
				alert.setTitle("File Not Found!");
				alert.setContentText("The File Is Not Found!");
				((Stage)alert.getDialogPane().getScene().getWindow()).getIcons().add(new Image("erroricon.png"));
				alert.showAndWait();
				
			}catch (IOException ex) {
				Alert alert = new Alert(AlertType.ERROR);
				alert.setTitle("IO Exception");
				alert.setContentText(ex.toString());
				((Stage)alert.getDialogPane().getScene().getWindow()).getIcons().add(new Image("erroricon.png"));
				alert.showAndWait();
            
            }catch(NullPointerException e2){
            	Alert alert = new Alert(AlertType.WARNING);
				alert.setTitle("You Didn't Choose A File!");
				alert.setContentText("Data Won't Be Saved To the Hard-Drive.");
				((Stage)alert.getDialogPane().getScene().getWindow()).getIcons().add(new Image("warningIcon.jpg"));
				alert.showAndWait();
				
            }
			
		});
		//creating stage and showing backPane (main pane) that contains every nodes
		backPane.getChildren().add(mainPane);
		stage.getIcons().add(new Image("icon.png"));
		mainPane.setPadding(new Insets(20));
		Scene scene = new Scene(backPane,350,320);
		stage.setTitle("Birzeit's Pizza");
		stage.setScene(scene);
		stage.show();
		
		
		}
	//to return orders as ObservableList insteead of ArrayList
	public ObservableList<PizzaOrder> getOrders(ArrayList<PizzaOrder> orders){
		ObservableList<PizzaOrder> oListOrders = FXCollections.observableArrayList(orders);
		return oListOrders;
	}
	
	
}
