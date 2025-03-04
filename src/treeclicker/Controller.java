package treeclicker;

import java.io.IOException;

import javafx.animation.RotateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Duration;
import treerespawn.TreeRespawnSystem;
import treecutter.TreeCutter;

public class Controller {
	
	private Scene scene;
	private Stage stage;
	private Parent root;
    
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
   
    private TreeRespawnSystem treeRespawnSystem = new TreeRespawnSystem();
    private TreeCutter treeCutter = new TreeCutter(1.0, "Axe"); // Manages points & tools


    @FXML
    public void initialize() {
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
    }


    public void onSceneReady(Scene scene) {
        backgroundImageView.fitWidthProperty().bind(scene.widthProperty());
        backgroundImageView.fitHeightProperty().bind(scene.heightProperty());
    }
    
    @FXML
    public void switchtoGame(ActionEvent event) throws IOException {
		
	    root = FXMLLoader.load(getClass().getResource("/resources/game.fxml"));
	    stage = (Stage)((Node)event.getSource()).getScene().getWindow();
	    scene = new Scene(root);
	    stage.setScene(scene);
	    stage.show();   
		
	}
	
    @FXML
    public void switchtoStore(ActionEvent event) throws IOException {
    	
    	root = FXMLLoader.load(getClass().getResource("/resources/treestore.fxml"));
	    stage = (Stage)((Node)event.getSource()).getScene().getWindow();
	    scene = new Scene(root);
	    stage.setScene(scene);
	    stage.show();	
		
	}
    

    public void Chop(ActionEvent e) {
        // Only increase points if tree is still "full"
        if (treeRespawnSystem.getCurrentState().equals("full")) {
            int pointsEarned = (int) treeCutter.getDamage();
            treeCutter.earnPoints(pointsEarned);
            
            pointsLabel.setText("Points: " + treeCutter.getPoints());

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

            System.out.println("Chopping tree.. Points: " + treeCutter.getPoints());
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
