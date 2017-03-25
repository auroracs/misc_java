package com.jengle.misc;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import java.awt.Rectangle;
import javax.swing.border.SoftBevelBorder;
import javax.swing.border.BevelBorder;
import java.awt.Dimension;
import java.awt.Insets;
import java.awt.Button;

public class FormExample {

	private JFrame frmFormExample;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FormExample window = new FormExample();
					window.frmFormExample.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public FormExample() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmFormExample = new JFrame();
		frmFormExample.setTitle("Form Example");
		frmFormExample.setBounds(100, 100, 450, 300);
		frmFormExample.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JButton btnExit = new JButton("Exit");
		btnExit.setPreferredSize(new Dimension(25, 23));
		btnExit.setMargin(new Insets(50, 0, 2, 14));
		btnExit.setSize(new Dimension(50, 50));
		btnExit.setBorder(new SoftBevelBorder(BevelBorder.RAISED, null, null, null, null));
		btnExit.setBounds(new Rectangle(0, 0, 50, 50));
		btnExit.setAlignmentY(10);
		frmFormExample.getContentPane().add(btnExit, BorderLayout.SOUTH);
	}

}
