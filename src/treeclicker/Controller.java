package treeclicker;

import java.awt.Rectangle;
import java.io.File;
import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

import javafx.animation.RotateTransition;
import javafx.event.ActionEvent;
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
import javafx.scene.layout.HBox;
import javafx.scene.effect.DropShadow;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;
import treeachievements.achievements;
import treerespawn.TreeRespawnSystem;
import treecutter.TreeCutter;
import treeplayer.Player;
import FlexFile.FlexFile;

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
    private Label cherryLabel;
    
    @FXML
    private Label kauriLabel;
    
    @FXML
    private Label goldenLabel;

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
    private HBox luckyCloverPopup;

    @FXML
    private HBox energyDrinkPopup;
    
    @FXML
    private ImageView energyDrinkIcon;
    
    @FXML 
    private ImageView luckyCloverIcon;
    
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
    
    //@FXML
    //private Rectangle greyRect;
    
    @FXML
    private Label lblAchvmt;
    

    private TreeRespawnSystem treeRespawnSystem = new TreeRespawnSystem();
    private TreeCutter treeCutter = new TreeCutter(1.0, "Axe"); // Manages points & toolssw
    private double pointsEarned =  treeCutter.getDamage();
    private String weather = "";
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
        
        setHoverMessage(luckyCloverImageView, "Lucky Clover: Adds a chance to earn bonus points.");
        setHoverMessage(autoLJackImageView, "Auto Lumberjack: Automatically chops trees for you.");
        setHoverMessage(energyDrinkImageView, "Energy Drink: Boosts your points per chop for a limited time.");
        
        
        
        File temp = new File("src/Saves/Main.txt");
        if(temp.exists()) {
        	FlexFile file = new FlexFile("Main");
        	Player player = Player.getInstance();
        	player.earnPoints(file.getProperty("Points",Double.class));
        	player.earnCherry(file.getProperty("Cherry",Double.class));
        	player.earnKauri(file.getProperty("Kauri",Double.class));
        	player.earnGolden(file.getProperty("Golden",Double.class));
        	player.setAutoLJackCount(file.getProperty("Lumberjacks",Integer.class));
        	player.setCloverCount(file.getProperty("Clovers",Integer.class));
        	player.setEnergyDrinkCount(file.getProperty("Energy Drinks",Integer.class));
        }
    }              
    

	public void BackGroundChangeClear() {
    	Image backgroundImage = new Image(getClass().getResourceAsStream("/resources/treeclickerbg.png"));
    	backgroundImageView.setImage(backgroundImage);
    }
	public void BackGroundChangeSunny() {
    	Image backgroundImage = new Image(getClass().getResourceAsStream("/resources/sunny.gif"));
    	backgroundImageView.setImage(backgroundImage);
    }
    
	public void BackGroundChangeSnow() {
    	Image backgroundImage = new Image(getClass().getResourceAsStream("/resources/snow.gif"));
    	backgroundImageView.setImage(backgroundImage);
    }
    
    public void BackGroundChangeRain() {
    	Image backgroundImage = new Image(getClass().getResourceAsStream("/resources/rain.gif"));
    	backgroundImageView.setImage(backgroundImage);
    }

    public void onSceneReady(Scene scene) {
    	
        if (backgroundImageView != null) {
        	backgroundImageView.fitWidthProperty().bind(scene.widthProperty());
        	backgroundImageView.fitHeightProperty().bind(scene.heightProperty());
        }
        
        Player.getInstance().setController(this);
        
        if (Player.getInstance().isEnergydrinkActive()) {
            setPowerupGlow(energyDrinkIcon, Color.DODGERBLUE);
        }

        if (Player.getInstance().isLuckycloverActive()) {
            setPowerupGlow(luckyCloverIcon, Color.LIMEGREEN);
        }
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
    
    public void setPowerupGlow(ImageView icon, Color color) {
        if (icon == null) return;

        DropShadow glow = new DropShadow();
        glow.setColor(color);
        glow.setRadius(25);
        glow.setSpread(0.6);
        icon.setEffect(glow);
    }

    public void removePowerupGlow(ImageView icon) {
        if (icon == null) return;
        icon.setEffect(null);
    }
    
    public ImageView getEnergyDrinkIcon() {
        return energyDrinkIcon;
    }

    public ImageView getLuckyCloverIcon() {
        return luckyCloverIcon;
    }    
    
    @FXML 
    private void handleLuckyCloverButtonClick() {
    	if (Player.getInstance().getPoints() >= 5) {
    		if (!Player.getInstance().isLuckycloverActive()) {
    			Player.getInstance().earnPoints(-5);
    			Player.getInstance().addLuckyClover();
    			updatePointsDisplay();
    			updateItemCount();
    		} else {
    			System.out.println("Lucky clover is already active. No points were spent.");    			
    		}    		
    	} else {
    		showNotEnoughPointsAlert();
    	}
    }
    
    @FXML 
    private void handleAutoLJackButtonClick() {
    	if (Player.getInstance().getPoints() >= 6) {
    		Player.getInstance().earnPoints(-6);    		
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
    		if (!Player.getInstance().isEnergydrinkActive()) {
    			Player.getInstance().earnPoints(-7);
    			Player.getInstance().addEnergydrink();
    			updatePointsDisplay();
    			updateItemCount();
    		} else {
    			System.out.println("Energy drink is already active. No points were spent.");
    		}    		
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
	    TreeWeatherTimer time = new TreeWeatherTimer(newController);
	    time.getWeaterState();
	    stage = (Stage)((Node)event.getSource()).getScene().getWindow();
	    scene = new Scene(root); 
	    
	    newController.onSceneReady(scene); 
	    
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
	    
	    newController.onSceneReady(scene); 
	    
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
	    achievements achvmt = new achievements(Player.getInstance().getTotalPoints(), Player.getInstance().getAutoLJackCount(), Player.getInstance().getLuckyCloverCount(), Player.getInstance().getEnergyDrinkCount());
	    achvmt.checkAchievements();
	    PB1.setProgress(achvmt.progressBarLJ());    
	    PB2.setProgress(achvmt.progressBarW1());
	    PB3.setProgress(achvmt.progressBarW2());
	    PB4.setProgress(achvmt.progressBarW3());
	    
	    lblLJ.setText(achvmt.NumLJ() + " Lumberjacks");
	    lblW1.setText(achvmt.NumW1() + " Oak Wood");
	    lblW2.setText(achvmt.NumW2() + " Lucky Clovers");
	    lblW3.setText(achvmt.NumW3() + " Energy Drinks Used");
	    
    }
    
    public void Save(ActionEvent e) {
    	File temp = new File("src/Saves/Main.txt");
    	if(temp.exists()) {
    		FlexFile file = new FlexFile("Main");
    		file.printProperties();
    		file.setProperty("Points",Player.getInstance().getPoints());
    		file.setProperty("Cherry",Player.getInstance().getCherry());
    		file.setProperty("Kauri",Player.getInstance().getKauri());
    		file.setProperty("Golden",Player.getInstance().getGolden());
    		file.setProperty("Lumberjacks",Player.getInstance().getAutoLJackCount());
    		file.setProperty("Clovers",Player.getInstance().getLuckyCloverCount());
    		file.setProperty("Energy Drinks",Player.getInstance().getEnergyDrinkCount());
    		file.saveFile();
    	} else {
    		FlexFile file = new FlexFile("Main","Points","Cherry","Kauri","Golden","Lumberjacks","Clovers","Energy Drinks");
    		file.setProperty("Points",Player.getInstance().getPoints());
    		file.setProperty("Cherry",Player.getInstance().getCherry());
    		file.setProperty("Kauri",Player.getInstance().getKauri());
    		file.setProperty("Golden",Player.getInstance().getGolden());
    		file.setProperty("Lumberjacks",Player.getInstance().getAutoLJackCount());
    		file.setProperty("Clovers",Player.getInstance().getLuckyCloverCount());
    		file.setProperty("Energy Drinks",Player.getInstance().getEnergyDrinkCount());
    		file.saveFile();
    	}
    }

   public void setPointsEarnedWeather(String WeatherState) {
    	weather = WeatherState;
    	if(WeatherState == "clear") {
    		System.out.println(pointsEarned);
    		pointsEarned *= 1;
    	}else if(WeatherState == "Sunny") {
    		System.out.println(pointsEarned);
    		pointsEarned *= 2;
    	}else if(WeatherState == "Rain"){
    		System.out.println(pointsEarned);
    		pointsEarned *= .5;
    	}else {
    		System.out.println(pointsEarned);
    		pointsEarned *= .25;
    	}
    }

    public void Chop(ActionEvent e) {
    	achievements achvmt = new achievements(Player.getInstance().getTotalPoints(), Player.getInstance().getAutoLJackCount(), Player.getInstance().getLuckyCloverCount(), Player.getInstance().getEnergyDrinkCount());	
    	achvmt.updatewood(Player.getInstance().getTotalPoints());
    	
        // Only increase points if tree is still "full"
        if (!treeRespawnSystem.getCurrentState().equals("stump")) {
            if(treeRespawnSystem.getCurrentState().equals("cherry")) {
            	 pointsEarned =  treeCutter.getDamage();
            	 setPointsEarnedWeather(weather);
                Player.getInstance().earnCherry(pointsEarned);
                cherryLabel.setText("Cherry Wood: " + Player.getInstance().getCherry());
            } else if(treeRespawnSystem.getCurrentState().equals("kauri")) {
            	 pointsEarned =  treeCutter.getDamage();
            	 setPointsEarnedWeather(weather);
                Player.getInstance().earnKauri(pointsEarned);
                kauriLabel.setText("Kauri Wood: " + Player.getInstance().getKauri());
            } else if(treeRespawnSystem.getCurrentState().equals("golden")) {
            	 pointsEarned =  treeCutter.getDamage();
            	 setPointsEarnedWeather(weather);
                Player.getInstance().earnGolden(pointsEarned);
                goldenLabel.setText("Golden Wood: " + Player.getInstance().getGolden());
            } else {
            	 pointsEarned =  treeCutter.getDamage();
            	 System.out.println(weather);
            	 setPointsEarnedWeather(weather);
            	Player.getInstance().earnPoints(pointsEarned);
            	pointsLabel.setText("POINTS: " + Player.getInstance().getPoints());
            }

            // Process the tree hit
            treeRespawnSystem.hitTree();

            // Update the tree image
            updateTreeImage();

          /*  // Rotate axe to simulate swinging
            RotateTransition rotate = new RotateTransition(Duration.seconds(0.03), axeImage);
            rotate.setByAngle(360);
            rotate.setCycleCount(1);
            rotate.setAutoReverse(true);
            rotate.play();
*/
            System.out.println("Chopping tree.. Points: " + Player.getInstance().getPoints());
            
        } else if (treeRespawnSystem.getCurrentState().equals("stump")) {
        	
            System.out.println("Tree is already a stump.");
            
            treeRespawnSystem.respawnTree();
            updateTreeImage();
        }
        
      //check if an achievement was achieved during the chop
        if (achvmt.updatewood(Player.getInstance().getTotalPoints()) == true) {
        	lblAchvmt.setText(achvmt.achievementBoxWood());
            //greyRect.setVisible(true);
        	lblAchvmt.setVisible(true);
        	Timer timer = new Timer();
        	timer.schedule(new TimerTask() {public void run() {popupFalse();}}, 10000);}
    }
    
    public void popupFalse() {
    	//greyRect.setVisible(false);
    	lblAchvmt.setVisible(false); 
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
            
            
         else if(treeRespawnSystem.getCurrentState().equals("cherry")) {
        	Image fullTreeImage = new Image(getClass().getResourceAsStream("/resources/cherrytree.png"));
        	treeImageView.setImage(fullTreeImage);
        } else if(treeRespawnSystem.getCurrentState().equals("kauri")) {
        	Image fullTreeImage = new Image(getClass().getResourceAsStream("/resources/kauritree.png"));
        	treeImageView.setImage(fullTreeImage);
        } else if(treeRespawnSystem.getCurrentState().equals("gold")) {
        	Image fullTreeImage = new Image(getClass().getResourceAsStream("/resources/goldtree.png"));
        	treeImageView.setImage(fullTreeImage);
        }
    }

    // Method to respawn the tree
    public void Respawn(ActionEvent e) {
        treeRespawnSystem.respawnTree();
        updateTreeImage(); // Update image after respawning the tree
    }
    

}
	

