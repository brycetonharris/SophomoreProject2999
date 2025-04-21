package treeplayer;

import treecutter.TreeCutter;
import java.util.Timer;
import java.util.TimerTask;
import javafx.application.Platform;
import javafx.scene.paint.Color;
import treeclicker.Controller;

public class Player {
    private static Player instance;

    private double points = 0.0;
    private double cherryWood = 0.0;
    private double kauriWood = 0.0;
    private double goldenWood = 0.0;
    private double totalPoints = 0.0;
    private int luckyCloverCount = 0;
    private int autoLJackCount = 0;
    private int energyDrinkCount = 0;
    private int totalenergyDrinksUsed = 0;
    

    private Timer lumberjackTimer;
    private Timer energydrinkTimer;
    private Timer luckycloverTimer;

    private TreeCutter treecutter;
    private Controller controller;

    private Player() {
        this.treecutter = new TreeCutter(points, "Axe");
    }

    public static Player getInstance() {
        if (instance == null) {
            instance = new Player();
        }
        return instance;
    }

    public void setController(Controller controller) {
        this.controller = controller;
    }

    public void addLumberjack() {
        autoLJackCount++;
        System.out.println("Lumberjack power-up added! Total Lumberjacks: " + autoLJackCount);

        treecutter.setDamage(autoLJackCount * 0.1);

        if (lumberjackTimer == null) {
            lumberjackTimer = new Timer();
            lumberjackTimer.scheduleAtFixedRate(new TimerTask() {
                @Override
                public void run() {
                    earnPoints(autoLJackCount * 0.1);
                    Platform.runLater(() -> {
                        if (controller != null) {
                            controller.updatePointsDisplay();
                        }
                    });
                }
            }, 0, 3000);
        }
    }

    public void stopLumberjackTimer() {
        if (lumberjackTimer != null) {
            lumberjackTimer.cancel();
            lumberjackTimer = null;
        }
    }

    public boolean isLumberjackActive() {
        return autoLJackCount > 0;
    }

    public void addEnergydrink() {
        if (isEnergydrinkActive()) {
            System.out.println("Energy drink is already active! Wait until it wears off");
            return;
        }
        totalenergyDrinksUsed++;
        energyDrinkCount = 1;
        System.out.println("Energy drink power-up activated for 10 seconds.");

        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                Platform.runLater(() -> {
                    if (controller != null && controller.getEnergyDrinkIcon() != null) {
                        System.out.println(">>> Applying glow to Energy Drink icon!");
                        controller.setPowerupGlow(controller.getEnergyDrinkIcon(), Color.DODGERBLUE);
                    }
                });
            }
        }, 300);

        energydrinkTimer = new Timer();
        energydrinkTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                Platform.runLater(() -> {
                    energyDrinkCount = 0;
                    System.out.println("Energy drink effect ended.");
                    if (controller != null && controller.getEnergyDrinkIcon() != null) {
                        controller.removePowerupGlow(controller.getEnergyDrinkIcon());
                    }
                    energydrinkTimer.cancel();
                    energydrinkTimer = null;
                });
            }
        }, 10000);
    }

    public boolean isEnergydrinkActive() {
        return energyDrinkCount > 0;
    }

    public void addLuckyClover() {
        if (isLuckycloverActive()) {
            System.out.println("Lucky clover is already active! Wait until it wears off");
            return;
        }

        luckyCloverCount = 1;
        System.out.println("Lucky clover power-up activated for 10 seconds.");

        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                Platform.runLater(() -> {
                    if (controller != null && controller.getLuckyCloverIcon() != null) {
                        System.out.println(">>> Applying glow to Lucky Clover icon!");
                        controller.setPowerupGlow(controller.getLuckyCloverIcon(), Color.LIGHTGREEN);
                    }
                });
            }
        }, 300);

        luckycloverTimer = new Timer();
        luckycloverTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                Platform.runLater(() -> {
                    luckyCloverCount = 0;
                    System.out.println("Lucky clover effect ended.");
                    if (controller != null && controller.getLuckyCloverIcon() != null) {
                        controller.removePowerupGlow(controller.getLuckyCloverIcon());
                    }
                    luckycloverTimer.cancel();
                    luckycloverTimer = null;
                });
            }
        }, 10000);
    }

    public boolean isLuckycloverActive() {
        return luckyCloverCount > 0;
    }

    // Earning Points & Resources
    public void earnPoints(double pointsEarned) {
        points += pointsEarned;
        totalPoints += pointsEarned;
        System.out.println("Earned wood: " + pointsEarned + " points. Total wood: " + points);
    }

    public void earnCherry(double cherryEarned) {
        cherryWood += cherryEarned;
        totalPoints += cherryEarned;
    }

    public void earnKauri(double kauriEarned) {
        kauriWood += kauriEarned;
        totalPoints += kauriEarned;
    }

    public void earnGolden(double goldenEarned) {
        goldenWood += goldenEarned;
        totalPoints += goldenEarned;
    }

    // Getters
    public double getCherry() {
        return cherryWood;
    }

    public double getKauri() {
        return kauriWood;
    }

    public double getGolden() {
        return goldenWood;
    }

    public double getTotalPoints() {
        return totalPoints;
    }

    public double getPoints() {
        return points;
    }

    public TreeCutter getTreeCutter() {
        return treecutter;
    }

    public int getLuckyCloverCount() {
        return luckyCloverCount;
    }

    public int getAutoLJackCount() {
        return autoLJackCount;
    }

    public int getEnergyDrinkCount() {
        return energyDrinkCount;
    }
    public int getTotalEnergyDrinkCount() {
        return totalenergyDrinksUsed;
    }

    // Setters for saving/restoring
    public void setAutoLJackCount(int count) {
        autoLJackCount = count;
    }

    public void setCloverCount(int count) {
        luckyCloverCount = count;
    }

    public void setEnergyDrinkCount(int count) {
        energyDrinkCount = count;
    }
}
