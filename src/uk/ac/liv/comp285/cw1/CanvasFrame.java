package uk.ac.liv.comp285.cw1;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class CanvasFrame extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void showShapes() {
		panelCanvas = new PanelCanvas();
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CanvasFrame frame = new CanvasFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	private static PanelCanvas panelCanvas = null;

	/**
	 * Create the frame.
	 */
	public CanvasFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		setBounds(0, 0, (int) screenSize.getWidth(), (int) screenSize.getHeight());
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		contentPane.add(panelCanvas);
		panelCanvas.setVisible(true);
		setContentPane(contentPane);
	}

	/**
	 * @return the panelCanvas
	 */
	public static PanelCanvas getPanelCanvas() {
		return panelCanvas;
	}

}
