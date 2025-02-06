package treeclicker;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Controller {
	
	  @FXML
	    private ImageView imageView;  // This will be injected by FXML

	    @FXML
	    public void initialize() {
	        // Load image here, if needed
	        Image image = new Image(getClass().getResourceAsStream("/resources/treeclickerbg.png"));
	        imageView.setImage(image);        
	        
	    }
	    
	    public void onSceneReady(Scene scene) {
	        imageView.fitWidthProperty().bind(scene.widthProperty());
	        imageView.fitHeightProperty().bind(scene.heightProperty());
	    }
		
	public void Chop(ActionEvent e) {
		
		System.out.println("Chopping tree..");
	}

}
