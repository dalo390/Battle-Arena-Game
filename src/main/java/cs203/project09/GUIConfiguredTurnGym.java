package cs203.project09;

import cs203.project02.*;
import cs203.project03.*;
import cs203.project08.*;

import javax.swing.JOptionPane;

import cs203.battlearena.*;
import cs203.battlearena.objectmon.*;
import cs203.battlearena.teams.*;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.HashMap;

public class GUIConfiguredTurnGym extends GUIConfiguredGym{
	App app;
	HashMap dlog1Dictionary;
	HashMap dlog2Dictionary;

	public GUIConfiguredTurnGym(){
		super();
	}
	public String promptTeamName(String name){
		return JOptionPane.showInputDialog(null, "What is team "+name+"'s name?", "team");
	}
	public void configDialogPrompt() {
		if (JOptionPane.showConfirmDialog(null,"Configure fight?",null,JOptionPane.YES_NO_OPTION)==0){
			this.configureFight();
			app = new App();
			app.setDictionary(dlog2Dictionary);
			app.createAndShowFightGUI(getTeamA(), getTeamB());
		}
	}
	
	public void executeTurn(){
		
		tick();
		
		Objectmon omanA = getTeamA().nextObjectmon();
		Objectmon omanB = getTeamB().nextObjectmon();
		
		if (omanA == null || omanB == null) {
			return;
		}	
		
		int damageDone = executeAttack(omanA, omanB);
		JOptionPane.showMessageDialog(null,omanA.getName() + " deals " + damageDone + " to " + omanB.getName());
		
		if (omanB.isFainted()){
			JOptionPane.showMessageDialog(null,omanB.getName() + " fainted! Moving to next turn!");
			if(getTeamB().nextObjectmon()==null){
				JOptionPane.showMessageDialog(null,"ROUND OVER! TEAM "+getTeamA().getName()+" WINS!");
				System.exit(0);
			}
			return;
		}
		
		damageDone = executeAttack(omanB, omanA);
		
		JOptionPane.showMessageDialog(null,omanB.getName() + " deals " + damageDone + " to " + omanA.getName());
		app.frame.setVisible(false);
		app.frame = new JFrame();
		app.createVSPanel();
		app.createAndAddFightButton();
		app.setImageLeft(omanA);
		app.setImageRight(omanB);
		app.frame.setLocationRelativeTo(null);
		app.frame.setVisible(true);
		if (omanA.isFainted()){
			JOptionPane.showMessageDialog(null,omanA.getName() + " fainted! Moving to next turn!");
			if(getTeamA().nextObjectmon()==null){
				JOptionPane.showMessageDialog(null,"ROUND OVER! TEAM "+getTeamB().getName()+" WINS!");
				System.exit(0);
			}
			return;
		}
	}
	public void configureFight() {

        try {

            setMaxRounds(promptNumRounds()); 
            int numOnTeamA = promptNumObjectmon("A");

            Team<Objectmon> newTeamA = new ListTeam<>();
            newTeamA.setMaxSize(numOnTeamA);

            newTeamA.setName(promptTeamName("A"));

            TeamBuilderDialog dlog1 = new TeamBuilderDialog(newTeamA);
            dlog1Dictionary = dlog1.getDictionary();

            Team<Objectmon> builtTeam = dlog1.getBuiltTeam();

            if (builtTeam == null) {
                JOptionPane.showMessageDialog(null, "Team Building Failed. Hard Exit!");
                System.exit(1);
            }

            this.setTeamA(builtTeam);

            if (this.getTeamA() != builtTeam) {
                JOptionPane.showMessageDialog(null, "Team Not A Part Of Gym. Hard Exit!");
                System.exit(1);
            }

            int numOnTeamB = promptNumObjectmon("B");

            Team<Objectmon> newTeamB = new ListTeam<>();
            newTeamB.setMaxSize(numOnTeamB);

            newTeamB.setName(promptTeamName("B"));

            TeamBuilderDialog dlog2 = new TeamBuilderDialog(newTeamB);
            dlog2Dictionary = dlog2.getDictionary();

            Team<Objectmon> builtTeam2 = dlog2.getBuiltTeam();

            this.setTeamB(builtTeam2);

            dlog2Dictionary.putAll(dlog1Dictionary);
            //System.out.println(dlog2Dictionary);

            if(builtTeam2 == null) {
                JOptionPane.showMessageDialog(null, "Team Building Failed.Hard Exit!");
                System.exit(1);
            } 

            if(this.getTeamB() != builtTeam2) {
                JOptionPane.showMessageDialog(null, "Team Not A Part Of Gym. Hard Exit!");
                System.exit(1);
            } 
        } catch (Exception ex) {
                ex.printStackTrace();
                System.exit(1);
        }
    }

    public HashMap getDictionaryAgain() {
    	return dlog2Dictionary;
    }
}