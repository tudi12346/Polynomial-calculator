package com.assignment1.view;

import java.awt.EventQueue;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.UIManager;

import com.assignment1.controller.PolynomialController;
import com.assignment1.model.Polynomial;



public class AppWindow {

	private JFrame frmPolynomialCalculator;
	private JTextField p1TextField;
	private JTextField p2TextField;
	private JTextField rezTextField;

	/**
	 * Launch the application.
	 */
	public void launch() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AppWindow window = new AppWindow();
					window.frmPolynomialCalculator.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public AppWindow() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmPolynomialCalculator = new JFrame();
		frmPolynomialCalculator.getContentPane().setBackground(UIManager.getColor("Button.background"));
		frmPolynomialCalculator.setTitle("Polynomial Calculator");
		frmPolynomialCalculator.setBounds(400, 100, 600, 510);
		frmPolynomialCalculator.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmPolynomialCalculator.getContentPane().setLayout(null);

		JLabel firstPolyLabel = new JLabel("First polynomial(P1)");
		firstPolyLabel.setBounds(40, 60, 150, 20);
		frmPolynomialCalculator.getContentPane().add(firstPolyLabel);

		p1TextField = new JTextField();
		p1TextField.setBounds(40, 90, 300, 20);
		frmPolynomialCalculator.getContentPane().add(p1TextField);
		p1TextField.setColumns(10);

		JLabel secondPloyLabel = new JLabel("Second polynomial(P2)");
		secondPloyLabel.setBounds(40, 120, 150, 20);
		frmPolynomialCalculator.getContentPane().add(secondPloyLabel);

		p2TextField = new JTextField();
		p2TextField.setBounds(40, 150, 300, 20);
		frmPolynomialCalculator.getContentPane().add(p2TextField);
		p2TextField.setColumns(10);

		JTextArea InfoText = new JTextArea();
		InfoText.setBackground(SystemColor.info);
		InfoText.setText(
				"   Atentie!\r\n   Intre semnul \"-\" sau \"+\" si coeficient nu se lasa spatiu!\r\n   Exemple valide:\r\n   2x^3-2x+2\r\n   2x^3        +2x +2\r\n   2x^3 -2x^1 +2x^0\r\n   In cazul operatiilor care nu sunt comutative, P1 este \r\n   primul termen, iar P2 al doilea\r\n   Ex: pentru scadere, Result=P1-P2\r\n   Derivarea si integrarea se aplica lui P1");
		InfoText.setBounds(40, 280, 320, 160);
		frmPolynomialCalculator.getContentPane().add(InfoText);

		JLabel rezPolyLabel = new JLabel("Result(P1 op P2) or op(P1)");
		rezPolyLabel.setBounds(40, 180, 150, 20);
		frmPolynomialCalculator.getContentPane().add(rezPolyLabel);

		rezTextField = new JTextField();
		rezTextField.setBounds(40, 210, 350, 20);
		frmPolynomialCalculator.getContentPane().add(rezTextField);
		rezTextField.setColumns(10);

		JButton addButton = new JButton("+");
		addButton.setBounds(430, 90, 80, 20);
		frmPolynomialCalculator.getContentPane().add(addButton);

		JButton subButton = new JButton("-");
		subButton.setBounds(430, 130, 80, 20);
		frmPolynomialCalculator.getContentPane().add(subButton);

		JButton mtpButton = new JButton("*");
		mtpButton.setBounds(430, 170, 80, 20);
		frmPolynomialCalculator.getContentPane().add(mtpButton);

		JButton divButton = new JButton("/");
		divButton.setBounds(430, 210, 80, 20);
		frmPolynomialCalculator.getContentPane().add(divButton);

		JButton dvtButton = new JButton("dvt");
		dvtButton.setBounds(430, 250, 80, 20);
		frmPolynomialCalculator.getContentPane().add(dvtButton);

		JButton itgButton = new JButton("itg");
		itgButton.setBounds(430, 290, 80, 20);
		frmPolynomialCalculator.getContentPane().add(itgButton);
		
		

		addButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String ps1=p1TextField.getText();
				String ps2=p2TextField.getText();
				Polynomial p1=PolynomialController.mkFromString(ps1);
				Polynomial p2=PolynomialController.mkFromString(ps2);
				if(p1.getTerms().isEmpty()||p2.getTerms().isEmpty())
					JOptionPane.showMessageDialog(null, "nu stii ce e ala polinom?\nai regulile mai jos explicate\npentru aceasta operatie trebuie sa introduci 2 polinoame valide!");
				else {
				Polynomial rez=PolynomialController.add(p1,p2);
				rezTextField.setText(PolynomialController.retText(rez));}
			}
		});

		subButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String ps1=p1TextField.getText();
				String ps2=p2TextField.getText();
				Polynomial p1=PolynomialController.mkFromString(ps1);
				Polynomial p2=PolynomialController.mkFromString(ps2);
				if(p1.getTerms().isEmpty()||p2.getTerms().isEmpty())
					JOptionPane.showMessageDialog(null, "nu stii ce e ala polinom?\nai regulile mai jos explicate\npentru aceasta operatie trebuie sa introduci 2 polinoame valide!");
				else {
				Polynomial rez=PolynomialController.sub(p1,p2);
				rezTextField.setText(PolynomialController.retText(rez));}
			}
		});
		
		mtpButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String ps1=p1TextField.getText();
				String ps2=p2TextField.getText();
				Polynomial p1=PolynomialController.mkFromString(ps1);
				Polynomial p2=PolynomialController.mkFromString(ps2);
				if(p1.getTerms().isEmpty()||p2.getTerms().isEmpty())
					JOptionPane.showMessageDialog(null, "nu stii ce e ala polinom?\nai regulile mai jos explicate\npentru aceasta operatie trebuie sa introduci 2 polinoame valide!");
				else {
				Polynomial rez=PolynomialController.mtp(p1,p2);
				rezTextField.setText(PolynomialController.retText(rez));
				}
			}
		});
		
		dvtButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String ps1=p1TextField.getText();
				Polynomial p1=PolynomialController.mkFromString(ps1);
				if(p1.getTerms().isEmpty())
					JOptionPane.showMessageDialog(null, "nu stii ce e ala polinom?\nai regulile mai jos explicate\npentru aceasta operatie trebuie sa introduci 1 polinom valid(P1)!");
				else {
				Polynomial rez=PolynomialController.dvt(p1);
				rezTextField.setText(PolynomialController.retText(rez));}
			}
		});
		
		itgButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String ps1=p1TextField.getText();
				Polynomial p1=PolynomialController.mkFromString(ps1);
				if(p1.getTerms().isEmpty())
					JOptionPane.showMessageDialog(null, "daca ai lasat casuta goala sau ai scris prostii,\nai descoperit constanta care se adauga la integrare\napasa ok daca esti pregatit");
				
				Polynomial rez=PolynomialController.itg(p1);
				rezTextField.setText(PolynomialController.retText(rez));
				JOptionPane.showMessageDialog(null, "daca nu te interesa constanta fermecata,\nintrodu un polinom valid in campul P1 si il vom integra pe bune...");

			}
		});
	
		divButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String ps1=p1TextField.getText();
				String ps2=p2TextField.getText();
				Polynomial p1=PolynomialController.mkFromString(ps1);
				Polynomial p2=PolynomialController.mkFromString(ps2);
				if(p1.getTerms().isEmpty()||p2.getTerms().isEmpty())
					JOptionPane.showMessageDialog(null, "nu stii ce e ala polinom?\nai regulile mai jos explicate\npentru aceasta operatie trebuie sa introduci 2 polinoame valide!");
				else {
				Polynomial rez=PolynomialController.div(p1,p2);
				rezTextField.setText(PolynomialController.retText(rez));
				Polynomial rest=PolynomialController.mod(p1, p2);
				if(rest.getTerms().isEmpty()==false)
				JOptionPane.showMessageDialog(null, "ia is restul:"+PolynomialController.retText(rest));
				}
				}
		});
	
	}
}
