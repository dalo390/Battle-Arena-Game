package cs203.project09;

import cs203.project08.*;

import cs203.battlearena.objectmon.Objectmon;
import cs203.battlearena.teams.Team;
import cs203.battlearena.gui.AbstractObjectmonView ;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.HashMap;


public class ObjectmonView extends AbstractObjectmonView{

	private Objectmon objmon;
	private JLabel lblName;
	private JLabel lblHp;
	private JLabel lblStamina;
	private JLabel lblWeight;
	private JLabel lblImage;

	private HashMap hashMap;

	public ObjectmonView(Objectmon objmon) throws Exception{
		super();

		this.objmon = objmon;

		createAndAddComponents();
	}

	public ObjectmonView(Objectmon objmon, HashMap hashMap) throws Exception{
		super();

		this.objmon = objmon;
		this.hashMap = hashMap;
		System.out.println(hashMap);

		createAndAddComponents();
	}

	public String getName() {
		return objmon.getName();
	}

	public void createAndAddComponents() throws Exception {
		createAndAddStatsPanel();
		createAndAddImage();
	}

	public void createAndAddImage() throws Exception  {
		addImage(createImage());
	}

	protected JLabel createImage() throws Exception {
		lblImage = new JLabel(getImageIcon(), JLabel.CENTER);
		return lblImage;
	}

	public void createAndAddStatsPanel() {
		addStatsPanel(createStatsPanel());
	}

	public void addStatsPanel(JPanel statsPanel) {
		add(statsPanel);
	}

	protected void addImage(JLabel lblImage) {
		add(lblImage);
	}

	public int getHp() {
		return objmon.getHp();
	}

	public ImageIcon getImageIcon() throws Exception {
		java.net.URL imageURL = getClass().getResource(((String)hashMap.get(getName())));
		//java.net.URL imageURL = getClass().getResource("abra.png");
		if (imageURL != null) {
			ImageIcon imageIcon = new ImageIcon(imageURL, "default text");
			return imageIcon;
		} else {
			throw new Exception("ObjectmonView image path not found (" + "no-image.png" + ")");
		}
	}

	public int getStamina() {
		return objmon.getStamina();
	}

	public int getWeight() {
		return objmon.getWeight();
	}

	protected JPanel createStatsPanel() {

		JPanel statsPanel = new JPanel();
		BoxLayout statsLayout = new BoxLayout(statsPanel, BoxLayout.Y_AXIS);
		statsPanel.setLayout(statsLayout);

		lblName = new JLabel("Name: " + getName());
		statsPanel.add(lblName);

		lblHp = new JLabel("HP:  " + Integer.toString(getHp()));
		statsPanel.add(lblHp);

		lblStamina = new JLabel("Stamina: " + Integer.toString(getStamina()));
		statsPanel.add(lblStamina);

		lblWeight = new JLabel("Weight: " + Integer.toString(getWeight()));
		statsPanel.add(lblWeight);

		return statsPanel;
	}
}