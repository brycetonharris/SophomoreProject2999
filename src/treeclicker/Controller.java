package treeclicker;

import java.io.IOException;

import javafx.animation.RotateTransition;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.util.Duration;
import treerespawn.TreeRespawnSystem;
import treecutter.TreeCutter;
import treeplayer.Player;
import treeAchievements.achievements;

public class Controller {
	
	private Scene scene;
	private Stage stage;
	private Parent root;
    
	private static Controller instance;
	
    @FXML
    private ImageView backgroundImageView;  // New ImageView for the background

    @FXML
    private ImageView treeImageView;  // Separate ImageView for the tree
    
    @FXML
    private ImageView lumberjackImageView;

    @FXML    
    private Label pointsLabel;

    @FXML
    private ImageView axeImage;
    
    @FXML
    private Button upgradeButton;
    
    @FXML
    private Button achievmentsButton;
    
    @FXML
    private Label luckyCloverLabel;
    
    @FXML
    private Label autoLJackLabel;
    
    @FXML
    private Label energyDrinkLabel;
    
    @FXML
    private ImageView luckyCloverImageView;

    @FXML
    private ImageView autoLJackImageView;

    @FXML
    private ImageView energyDrinkImageView;
    
    @FXML
    private ProgressBar PB1;
    
    @FXML
    private ProgressBar PB2;
    
    @FXML
    private ProgressBar PB3;
    
    @FXML
    private ProgressBar PB4;
    
    @FXML
    private Label lblW1;

    @FXML
    private Label lblW2;
    
    @FXML
    private Label lblW3;
    
    @FXML
    private Label lblLJ;
    
    @FXML
    private Button btnBack;
    

    private TreeRespawnSystem treeRespawnSystem = new TreeRespawnSystem();
    private TreeCutter treeCutter = new TreeCutter(1.0, "Axe"); // Manages points & tools

    @FXML
    public void initialize() {
    	
    	instance = this;
        // Set up background (only once)
    	if (backgroundImageView != null) { 
    		
            Image backgroundImage = new Image(getClass().getResourceAsStream("/resources/treeclickerbg.png"));
            backgroundImageView.setImage(backgroundImage);
            
        } else {
        	
            System.out.println("backgroundImageView not injected!");
        }

        // Ensure treeImageView is injected
        if (treeImageView == null) {
        	
            System.out.println("treeImageView is not injected!");
            
        } else {
            
            updateTreeImage();
        }
        
        setHoverMessage(luckyCloverImageView, "Lucky Clover: Adds a chance to earn bonus points.");
        setHoverMessage(autoLJackImageView, "Auto Lumberjack: Automatically chops trees for you.");
        setHoverMessage(energyDrinkImageView, "Energy Drink: Boosts your points per chop for a limited time.");
    }
	public void BackGroundChangeClear() {
    	Image backgroundImage = new Image(getClass().getResourceAsStream("/resources/treeclickerbg.png"));
    	backgroundImageView.setImage(backgroundImage);
    }
	public void BackGroundChangeSunny() {
    	Image backgroundImage = new Image(getClass().getResourceAsStream("/resources/sunny.jpg"));
    	backgroundImageView.setImage(backgroundImage);
    }
    
	public void BackGroundChangeSnow() {
    	Image backgroundImage = new Image(getClass().getResourceAsStream("/resources/snow.png"));
    	backgroundImageView.setImage(backgroundImage);
    }
    
    public void BackGroundChangeRain() {
    	Image backgroundImage = new Image(getClass().getResourceAsStream("/resources/rain.gif"));
    	backgroundImageView.setImage(backgroundImage);
    }
    
    public static Controller getInstance() {
        return instance;
    }


    public void onSceneReady(Scene scene) {
    	
        backgroundImageView.fitWidthProperty().bind(scene.widthProperty());
        backgroundImageView.fitHeightProperty().bind(scene.heightProperty());
    }
    
    public void updatePointsDisplay() {
    	
        if (pointsLabel != null) {
        	
        	pointsLabel.setText("Wood: " + Player.getInstance().getPoints());
        } else {
        	System.out.println("pointsLabel is null!");
        }
    }
    
    private void showNotEnoughPointsAlert() {
        Alert alert = new Alert(AlertType.WARNING);
        alert.setTitle("Not Enough Points");
        alert.setHeaderText(null);
        alert.setContentText("You don't have enough points to purchase this item.");
        alert.showAndWait();
    }
    
    public void updateItemCount() {
        if (luckyCloverLabel != null) {
            luckyCloverLabel.setText("Lucky Clover: " + Player.getInstance().getLuckyCloverCount());
        }
        if (autoLJackLabel != null) {
            autoLJackLabel.setText("Auto LJack: " + Player.getInstance().getAutoLJackCount());
        }
        if (energyDrinkLabel != null) {
            energyDrinkLabel.setText("Energy Drink: " + Player.getInstance().getEnergyDrinkCount());
        }
    }    
    
    public void setHoverMessage(ImageView imageView, String message) {
    	
    	if (imageView != null) {
            // Set the tooltip directly
            Tooltip tooltip = new Tooltip(message);
            Tooltip.install(imageView, tooltip);
        } else {
            System.out.println("ImageView is null!");
        }
    }

    
    @FXML 
    private void handleLuckyCloverButtonClick() {
    	if (Player.getInstance().getPoints() >= 5) {
    		Player.getInstance().earnPoints(-5);  
    		Player.getInstance().addLuckyClover();
    		updatePointsDisplay();    		
    		updateItemCount();
    	} else {
    		showNotEnoughPointsAlert();
    	}
    }
    
    @FXML 
    private void handleAutoLJackButtonClick() {
    	if (Player.getInstance().getPoints() >= 6) {
    		Player.getInstance().earnPoints(-6);
    		Player.getInstance().addAutoLJackCount();
    		Player.getInstance().addLumberjack();
    		updatePointsDisplay();
    		updateItemCount();
    	} else {
    		showNotEnoughPointsAlert();
    	}
    }
    
    @FXML
    private void handleEnergyDrinkButtonClick() {
    	if (Player.getInstance().getPoints() >= 7) {
    		Player.getInstance().earnPoints(-7);
    		Player.getInstance().addEnergyDrinkCount();
    		updatePointsDisplay();
    		updateItemCount();
    	} else {
    		showNotEnoughPointsAlert();
    	}
    }
    
    @FXML
    public void switchtoGame(ActionEvent event) throws IOException {
		
	    FXMLLoader loader = new FXMLLoader(getClass().getResource("/resources/game.fxml"));
	    root = loader.load();
	    
	    Controller newController = loader.getController();
	    newController.updatePointsDisplay();
	    newController.updateItemCount();
	    
	    stage = (Stage)((Node)event.getSource()).getScene().getWindow();
	    scene = new Scene(root);   
	    
	    stage.setScene(scene);
	    stage.show();   
		
	}
	
    @FXML
    public void switchtoStore(ActionEvent event) throws IOException {
    	
    	FXMLLoader loader = new FXMLLoader(getClass().getResource("/resources/treestore.fxml"));
	    root = loader.load();
	    
	    Controller newController = loader.getController();
	    newController.updatePointsDisplay();
	    newController.updateItemCount();
	    
	    stage = (Stage)((Node)event.getSource()).getScene().getWindow();
	    scene = new Scene(root);   
	    
	    stage.setScene(scene);
	    stage.show();      	
		
	}
    
    @FXML
    public void switchtoAchievements(ActionEvent event) throws IOException {
    	
    	FXMLLoader loader = new FXMLLoader(getClass().getResource("/resources/achievements.fxml"));
	    root = loader.load();
	    Controller newController = loader.getController();
	    newController.achievementUpdate();
	    
	    stage = (Stage)((Node)event.getSource()).getScene().getWindow();
	    scene = new Scene(root);   
	    
	    
	    stage.setScene(scene);
	    stage.show();
	}
    
    public void achievementUpdate() {
	    achievements achvmt = new achievements(Player.getInstance().getTotalPoints(), Player.getInstance().getAutoLJackCount());
	    achvmt.checkAchievements();
	    PB1.setProgress(achvmt.progressBarLJ());    
	    PB2.setProgress(achvmt.progressBarW1());
	    PB3.setProgress(achvmt.progressBarW2());
	    PB4.setProgress(achvmt.progressBarW3());
	    
	    lblLJ.setText(achvmt.NumLJ() + " Lumberjacks");
	    lblW1.setText(achvmt.NumW1() + " Oak Wood");
	    //lblW2.setText(achvmt.NumW2() + " Wood");
	    //lblW3.setText(achvmt.NumW3() + " Wood");
	    
    }

    public void Chop(ActionEvent e) {
        // Only increase points if tree is still "full"
        if (treeRespawnSystem.getCurrentState().equals("full")) {
        	
            int pointsEarned = (int) treeCutter.getDamage();
            Player.getInstance().earnPoints(pointsEarned);            
            pointsLabel.setText("Wood: " + Player.getInstance().getPoints());

            // Process the tree hit
            treeRespawnSystem.hitTree();

            // Update the tree image
            updateTreeImage();

            // Rotate axe to simulate swinging
            RotateTransition rotate = new RotateTransition(Duration.seconds(0.03), axeImage);
            rotate.setByAngle(360);
            rotate.setCycleCount(1);
            rotate.setAutoReverse(true);
            rotate.play();

            System.out.println("Chopping tree.. Points: " + Player.getInstance().getPoints());
            
        } else if (treeRespawnSystem.getCurrentState().equals("stump")) {
        	
            System.out.println("Tree is already a stump.");
            
            treeRespawnSystem.respawnTree();
            updateTreeImage();
        }
    }

    private void updateTreeImage() {
    	
        if (treeRespawnSystem.getCurrentState().equals("full")) {
            // Set the full tree image
            Image fullTreeImage = new Image(getClass().getResourceAsStream("/resources/tree.png"));
            
            treeImageView.setImage(fullTreeImage);
            
        } else if (treeRespawnSystem.getCurrentState().equals("stump")) {
            // Set the stump image
            Image stumpImage = new Image(getClass().getResourceAsStream("/resources/treestump.png"));
            treeImageView.setImage(stumpImage);
            
        } else if(treeRespawnSystem.getCurrentState().equals("cherry")) {
        	Image fullTreeImage = new Image(getClass().getResourceAsStream("/resources/cherrytree.png"));
        } else if(treeRespawnSystem.getCurrentState().equals("kauri")) {
        	Image fullTreeImage = new Image(getClass().getResourceAsStream("/resources/kauritree.png"));
        } else if(treeRespawnSystem.getCurrentState().equals("gold")) {
        	Image fullTreeImage = new Image(getClass().getResourceAsStream("/resources/goldtree.png"));
        }
    }

    // Method to respawn the tree
    public void Respawn(ActionEvent e) {
        treeRespawnSystem.respawnTree();
        updateTreeImage(); // Update image after respawning the tree
    }

}
	

