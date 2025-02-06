package treeclicker;

import javafx.animation.RotateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;
import treerespawn.TreeRespawnSystem;

public class Controller {
    
    @FXML
    private ImageView backgroundImageView;  // New ImageView for the background

    @FXML
    private ImageView treeImageView;  // Separate ImageView for the tree

    @FXML    
    private Label pointsLabel;

    @FXML
    private ImageView axeImage;

    private int points = 0;
    private TreeRespawnSystem treeRespawnSystem = new TreeRespawnSystem();

    @FXML
    public void initialize() {
        // Set up background (only once)
        Image backgroundImage = new Image(getClass().getResourceAsStream("/resources/treeclickerbg.png"));
        backgroundImageView.setImage(backgroundImage);

        // Set initial tree image
        updateTreeImage();
    }

    public void onSceneReady(Scene scene) {
        backgroundImageView.fitWidthProperty().bind(scene.widthProperty());
        backgroundImageView.fitHeightProperty().bind(scene.heightProperty());
    }

    public void Chop(ActionEvent e) {
        // Only increase points if tree is still "full"
        if (treeRespawnSystem.getCurrentState().equals("full")) {
            points += 1;
            pointsLabel.setText("Points: " + points);

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

            System.out.println("Chopping tree.. Points: " + points);
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
        }
    }

    // Method to respawn the tree
    public void Respawn(ActionEvent e) {
        treeRespawnSystem.respawnTree();
        updateTreeImage(); // Update image after respawning the tree
    }
}
