import java.awt.*;
import javax.swing.*;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.Object;

/**
 * This is the view class for the MVC for the farming simulator game for MCO2.
 */

public class PlayerView {

	private JFrame mainFrame;
	private ArrayList<JButton> tiles = new ArrayList<JButton>();
	private JTextArea textPrompt;

	//farm buttons
	private JButton advanceDay;
	private JButton register;
	private JButton viewSeedToolInfo;
	private JButton viewRegisterInfo;
	private JButton startNewGame;

	//tool buttons
	private JButton usePlow;
	private JButton usePickaxe;
	private JButton useWateringCan;
	private JButton useFertilizer;
	private JButton useShovel;

	//plant buttons
	private JButton harvestCrop;

	private JButton plantTurnip;
	private JButton plantCarrot;
	private JButton plantPotato;

	private JButton plantRose;
	private JButton plantTulips;
	private JButton plantSunflower;

	private JButton plantMango;
	private JButton plantApple;



	public PlayerView() {
		this.mainFrame = new JFrame("My Farm");

		this.mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.mainFrame.setLayout(new FlowLayout());
		this.mainFrame.setSize(1250, 575);

		/*
		//soil
		ImageIcon soil = createImageIcon("soil.png", "unplowed soil tile");
		ImageIcon soilPlowed = createImageIcon("soilPlowed.png", "plowed soil tile");
		ImageIcon rock = createImageIcon("rock.png", "rock tile");
		//ImageIcon icon = createImageIcon("", "");
		//ImageIcon icon = createImageIcon("", "");

		/*
		//ungrown plants
		ImageIcon icon = createImageIcon("", "");
		ImageIcon icon = createImageIcon("", "");
		ImageIcon icon = createImageIcon("", "");
		ImageIcon icon = createImageIcon("", "");
		ImageIcon icon = createImageIcon("", "");

		//seeds & grown plants
		ImageIcon icon = createImageIcon("", "");
		ImageIcon icon = createImageIcon("", "");
		ImageIcon icon = createImageIcon("", "");
		ImageIcon icon = createImageIcon("", "");
		ImageIcon icon = createImageIcon("", "");
		*/

		this.initializeSidePanel();
		this.initializeTilePanel();
		this.initializeActionPanel();

		this.mainFrame.setVisible(true);

	}

	//initializations

	private void initializeSidePanel() {

		JPanel sidePanel = new JPanel();
		sidePanel.setLayout(new GridLayout(1, 1));

		textPrompt = new JTextArea(15, 18);
		textPrompt.setEditable(false);
		textPrompt.setText("Test");
		
		sidePanel.add(textPrompt);
		this.mainFrame.add(sidePanel);
	}

	private void initializeTilePanel() {

		JPanel tilePanel = new JPanel();
		tilePanel.setLayout(new GridLayout(5, 10));

		//tile buttons

		for(int c = 1; c <= 50; c++) {
			this.tiles.add(new JButton(Integer.toString(c)));
		}

		for(JButton btn : this.tiles) {
			btn.setPreferredSize(new Dimension(100, 50));
			tilePanel.add(btn);
		}

		this.mainFrame.add(tilePanel);
	}

	private void initializeActionPanel() {
		JPanel actionPanel = new JPanel();

		actionPanel.setLayout(new GridLayout(1, 3));

		JPanel actionSubPanel1 = new JPanel();
		actionSubPanel1.setLayout(new GridLayout(5, 1));

		JPanel actionSubPanel2 = new JPanel();
		actionSubPanel2.setLayout(new GridLayout(9, 1));

		JPanel actionSubPanel3 = new JPanel();
		actionSubPanel3.setLayout(new GridLayout(5, 1));

		//action buttons
		this.advanceDay = new JButton("Advance Day");
		this.register = new JButton("Register");
		this.viewSeedToolInfo = new JButton("View Seed & Tool Info");
		this.viewRegisterInfo = new JButton("View Register Info");
		this.startNewGame = new JButton("START NEW GAME");

		this.usePlow = new JButton("Use Plow");
		this.usePickaxe = new JButton("Use Pickaxe");
		this.useWateringCan = new JButton("Use Watering Can");
		this.useFertilizer = new JButton("Use Fertilizer");
		this.useShovel = new JButton("Use Shovel");
		
		this.harvestCrop = new JButton("Harvest Crop");

		this.plantTurnip = new JButton("Plant Turnip");
		this.plantCarrot = new JButton("Plant Carrot");
		this.plantPotato = new JButton("Plant Potato");

		this.plantRose = new JButton("Plant Rose");
		this.plantTulips = new JButton("Plant Tulips");
		this.plantSunflower = new JButton("Plant Sunflower");

		this.plantMango = new JButton("Plant Mango");
		this.plantApple = new JButton("Plant Apple");


		actionSubPanel1.add(usePickaxe);
		actionSubPanel1.add(usePlow);
		actionSubPanel1.add(useWateringCan);
		actionSubPanel1.add(useFertilizer);
		actionSubPanel1.add(useShovel);

		actionSubPanel2.add(harvestCrop);

		actionSubPanel2.add(plantTurnip);
		actionSubPanel2.add(plantCarrot);
		actionSubPanel2.add(plantPotato);

		actionSubPanel2.add(plantRose);
		actionSubPanel2.add(plantTulips);
		actionSubPanel2.add(plantSunflower);

		actionSubPanel2.add(plantMango);
		actionSubPanel2.add(plantApple);

		actionSubPanel3.add(register);
		actionSubPanel3.add(advanceDay);
		actionSubPanel3.add(viewSeedToolInfo);
		actionSubPanel3.add(viewRegisterInfo);
		actionSubPanel3.add(startNewGame);

		actionPanel.add(actionSubPanel1);
		actionPanel.add(actionSubPanel2);
		actionPanel.add(actionSubPanel3);
		

		this.mainFrame.add(actionPanel);
	}

	public void setTileButtonListener (ActionListener actionListener) {
		for(JButton btn : this.tiles) {
			btn.addActionListener(actionListener);
		}
	}

	public void setUsePickaxeButtonListener(ActionListener actionListener) {
		this.usePickaxe.addActionListener(actionListener);
	}	

	public void setUsePlowButtonListener(ActionListener actionListener) {
		this.usePlow.addActionListener(actionListener);
	}	

	public void setUseWateringCanButtonListener(ActionListener actionListener) {
		this.useWateringCan.addActionListener(actionListener);
	}	

	public void setUseFertilizerButtonListener(ActionListener actionListener) {
		this.useFertilizer.addActionListener(actionListener);
	}	

	public void setUseShovelButtonListener(ActionListener actionListener) {
		this.useShovel.addActionListener(actionListener);
	}	

	public void setHarvestCropButtonListener(ActionListener actionListener) {
		this.harvestCrop.addActionListener(actionListener);
	}

	public void setPlantTurnipButtonListener(ActionListener actionListener) {
		this.plantTurnip.addActionListener(actionListener);
	}

	public void setPlantCarrotButtonListener(ActionListener actionListener) {
		this.plantCarrot.addActionListener(actionListener);
	}

	public void setPlantPotatoButtonListener(ActionListener actionListener) {
		this.plantPotato.addActionListener(actionListener);
	}

	public void setPlantRoseButtonListener(ActionListener actionListener) {
		this.plantRose.addActionListener(actionListener);
	}

	public void setPlantTulipsButtonListener(ActionListener actionListener) {
		this.plantTulips.addActionListener(actionListener);
	}

	public void setPlantSunflowerButtonListener(ActionListener actionListener) {
		this.plantSunflower.addActionListener(actionListener);
	}

	public void setPlantMangoButtonListener(ActionListener actionListener) {
		this.plantMango.addActionListener(actionListener);
	}

	public void setPlantAppleButtonListener(ActionListener actionListener) {
		this.plantApple.addActionListener(actionListener);
	}

	public void setRegisterButtonListener(ActionListener actionListener) {
		this.register.addActionListener(actionListener);
	}

	public void setAdvanceDayButtonListener(ActionListener actionListener) {
		this.advanceDay.addActionListener(actionListener);
	}

	public void setViewSeedToolInfoButtonListener(ActionListener actionListener) {
		this.viewSeedToolInfo.addActionListener(actionListener);
	}

	public void setViewRegisterInfoButtonListener(ActionListener actionListener) {
		this.viewRegisterInfo.addActionListener(actionListener);
	}

	public void setStartNewGameButtonListener(ActionListener actionListener) {
		this.startNewGame.addActionListener(actionListener);
	}

	public void setTextPrompt(String output) {
		this.textPrompt.setText(output);
	}

	public void setTileButton(int index, String text, int color) {

		this.tiles.get(index).setText(text);

		if(color == -1)
			this.tiles.get(index).setForeground(new Color(139,69,19)); //brown
		else if(color == 0)
			this.tiles.get(index).setForeground(new Color(244, 164, 96)); //light brown
		else if(color == 1)
			this.tiles.get(index).setForeground(Color.gray);
		else if(color == 2)
			this.tiles.get(index).setForeground(Color.red);
		else if(color == 3)
			this.tiles.get(index).setForeground(Color.green);
		else if(color == 4)
			this.tiles.get(index).setForeground(Color.black);
			
	}

	public int getTileButtonIndex(Object btn) {
		return this.tiles.indexOf(btn);
	}

	public void addButtonCosts(double pickaxe, double plow, double wateringCan, double fertilizer, double shovel, double turnip, double carrot, double potato, double rose, double tulips, double sunflower, double mango, double apple) {

		this.usePickaxe.setText("Use Pickaxe " + "(" + pickaxe + ")");
		this.usePlow.setText("Use Plow " + "(" + plow + ")");
		this.useWateringCan.setText("Use Watering Can " + "(" + wateringCan + ")");
		this.useFertilizer.setText("Use Fertilizer " + "(" + fertilizer + ")");
		this.useShovel.setText("Use Shovel " + "(" + shovel + ")");

		this.plantTurnip.setText("Plant Turnip " + "(" + turnip + ")");
		this.plantCarrot.setText("Plant Carrot " + "(" + carrot + ")");
		this.plantPotato.setText("Plant Potato " + "(" + potato + ")");

		this.plantRose.setText("Plant Rose " + "(" + rose + ")");
		this.plantTulips.setText("Plant Tulips " + "(" + tulips + ")");
		this.plantSunflower.setText("Plant Sunflower " + "(" + sunflower + ")");

		this.plantMango.setText("Plant Mango Tree " + "(" + mango + ")");
		this.plantApple.setText("Plant Apple Tree " + "(" + apple + ")");
	}
	
	public void setToolButtons(boolean pickaxe, boolean plow, boolean wateringCan, boolean fertilizer, boolean shovel) {

		this.usePickaxe.setEnabled(pickaxe);
		this.usePlow.setEnabled(plow);
		this.useWateringCan.setEnabled(wateringCan);
		this.useFertilizer.setEnabled(fertilizer);
		this.useShovel.setEnabled(shovel);
	}

	public void setPlantButtons(boolean harvestCrop, boolean turnip, boolean carrot, boolean potato, boolean rose, boolean tulips, boolean sunflower, boolean mango, boolean apple) {

		this.harvestCrop.setEnabled(harvestCrop);

		this.plantTurnip.setEnabled(turnip);
		this.plantCarrot.setEnabled(carrot);
		this.plantPotato.setEnabled(potato);

		this.plantRose.setEnabled(rose);
		this.plantTulips.setEnabled(tulips);
		this.plantSunflower.setEnabled(sunflower);

		this.plantMango.setEnabled(mango);
		this.plantApple.setEnabled(apple);

	}

	public void popUpMessage(String label, String output) {
		JOptionPane.showConfirmDialog(null, output, label, JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE); 
	}

	public void setFarmButtons(boolean register) {

		this.register.setEnabled(register);
	}

	public void disableAll() {

		for(JButton btn : this.tiles) {
			btn.setEnabled(false);
		}

		this.register.setEnabled(false);
		this.advanceDay.setEnabled(false);
		this.viewSeedToolInfo.setEnabled(false);
		this.viewRegisterInfo.setEnabled(false);

		this.usePickaxe.setEnabled(false);
		this.usePlow.setEnabled(false);
		this.useWateringCan.setEnabled(false);
		this.useFertilizer.setEnabled(false);
		this.useShovel.setEnabled(false);

		this.harvestCrop.setEnabled(false);

		this.plantTurnip.setEnabled(false);
		this.plantCarrot.setEnabled(false);
		this.plantPotato.setEnabled(false);

		this.plantRose.setEnabled(false);
		this.plantTulips.setEnabled(false);
		this.plantSunflower.setEnabled(false);

		this.plantMango.setEnabled(false);
		this.plantApple.setEnabled(false);
	}

	public void enableAll() {

		for(JButton btn : this.tiles) {
			btn.setEnabled(true);
		}

		this.register.setEnabled(true);
		this.advanceDay.setEnabled(true);
		this.viewSeedToolInfo.setEnabled(true);
		this.viewRegisterInfo.setEnabled(true);

		this.usePickaxe.setEnabled(true);
		this.usePlow.setEnabled(true);
		this.useWateringCan.setEnabled(true);
		this.useFertilizer.setEnabled(true);
		this.useShovel.setEnabled(true);

		this.harvestCrop.setEnabled(true);

		this.plantTurnip.setEnabled(true);
		this.plantCarrot.setEnabled(true);
		this.plantPotato.setEnabled(true);

		this.plantRose.setEnabled(true);
		this.plantTulips.setEnabled(true);
		this.plantSunflower.setEnabled(true);

		this.plantMango.setEnabled(true);
		this.plantApple.setEnabled(true);
	}
}