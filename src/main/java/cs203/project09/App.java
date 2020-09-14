package cs203.project09;

import cs203.project02.*;

import cs203.battlearena.*;
import cs203.battlearena.objectmon.*;

import cs203.battlearena.teams.Team;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.HashMap;


public class App {

	public JFrame frame;
	private static HashMap dictionaryApp;
	public static GUIConfiguredTurnGym gym;

	public App() {
		frame = new JFrame("Objectmon Fighting");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	public void createVSPanel() {
		JPanel centerPanel = new JPanel();
		JLabel vsLabel = new JLabel("V.S.");
		centerPanel.add(vsLabel);
		frame.add(centerPanel, BorderLayout.CENTER);
	}

	public void createAndShowFightGUI(Team teamA, Team teamB) {
		createVSPanel();
		setImageLeft(teamA.nextObjectmon());
		System.out.println(teamA.nextObjectmon());
		setImageRight(teamB.nextObjectmon());
		createAndAddFightButton();
		
		//frame.setSize(600,600);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}
	
	public void setImageLeft(Objectmon omon) {
		try{
			ObjectmonView objectmonPanel = new ObjectmonView(omon, dictionaryApp);
			frame.getContentPane().add(objectmonPanel, BorderLayout.LINE_START);

			frame.pack();
			frame.validate();
			frame.repaint();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void setImageRight(Objectmon omon) {
		try {
			ObjectmonView objectmonPanel = new ObjectmonView(omon, dictionaryApp);
			frame.getContentPane().add(objectmonPanel, BorderLayout.EAST);

			frame.pack();
			frame.validate();
			frame.repaint();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void createAndAddFightButton() {
		JButton fightButton = new JButton("Next Turn");
		fightButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				gym.executeTurn();
							
			}
		});

		JPanel bottomPanel = new JPanel();
		bottomPanel.add(fightButton);
		frame.getContentPane().add(bottomPanel, BorderLayout.SOUTH);
	}

	public void setDictionary(HashMap dictionary) {
		this.dictionaryApp = dictionary;
	}


	public static void main(String[] args) {
		gym = new GUIConfiguredTurnGym();
		
		gym.configDialogPrompt();
		//dictionaryApp = gym.getDictionaryAgain();
		//System.out.println("above");
		//System.out.println(dictionaryApp);
		//System.out.println("below");
		
		/*
		try {
			ObjectmonPanel eeek = new ObjectmonPanel(gym.getTeamA());
			app.setImageLeft(eeek);
		} catch (Exception e) {
			e.printStackTrace();
		}
		*/
		//app.setImageLeft(gym.getTeamA());
		
		//gym.fight();
		
		//System.exit(0);
	}
	
	
	
}