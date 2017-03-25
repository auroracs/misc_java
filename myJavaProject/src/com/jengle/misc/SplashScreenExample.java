package com.jengle.misc;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.LineBorder;

public class SplashScreenExample extends JWindow {

	private static final long serialVersionUID = 1L;
	// Getting the user's screen dimensions.
	private Dimension d = Toolkit.getDefaultToolkit().getScreenSize();

	public static void main(String args[]) {

		new SplashScreenExample();

	}

	// @SuppressWarnings("deprecation")
	public SplashScreenExample() {

		try {

			// Splash screen image
			JLabel lbImage = new JLabel(new ImageIcon("Images/Splash.jpg"));

			// Setting splash window border color
			Color cl = new Color(0, 0, 0);

			// Set splash window border
			lbImage.setBorder(new LineBorder(cl, 1));

			// Adding the image label to window
			getContentPane().add(lbImage, BorderLayout.CENTER);

			pack(); // Packing the Splash Window.

			// Set splash screen site
			setSize(getSize().width, getSize().height);

			// Setting the Splash Window Position on User's Screen.
			setLocation(d.width / 2 - getWidth() / 2, d.height / 2 - getHeight() / 2);

			setVisible(true); // Display the splash screen

			// Pause for 2 seconds
			Thread.sleep(2000);

			// now that splash is gone, do something here...

			toFront(); // Making the Splash Window to Front of Main Window.
			setVisible(false); // Setting the Splash Window InVisible.
			dispose(); // Unloading the Window & Release the Memory Resources.
			
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	}


}
