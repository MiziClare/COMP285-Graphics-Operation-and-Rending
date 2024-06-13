package uk.ac.liv.comp285.cw1;

import java.awt.Graphics;
import java.util.Vector;

import javax.swing.JPanel;

@SuppressWarnings("serial")
public class PanelCanvas extends JPanel {

	private Vector<IShape> allShapes = new Vector<IShape>();

	public void addShape(IShape shape) {
		allShapes.add(shape);
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);

		allShapes.forEach((shape) -> {
			shape.render(g);
		});
	}

}
