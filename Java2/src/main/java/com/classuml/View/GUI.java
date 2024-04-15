/**
 * Provides the main graphical user interface (GUI) for a UML diagram editor. This class initializes
 * and manages the JFrame window, menus, and all GUI components required to interact with and display
 * UML diagrams. It includes functionalities for setting up the GUI controller, drawing UML relationship
 * arrows between class panels, managing menu actions, and more. Users can add, delete, and edit classes,
 * fields, methods, and relationships through the GUI. This class acts as the view in an MVC architecture,
 * directly interacting with the user and forwarding actions to the controller for application logic processing.
 */

/*******************************************************************************************************************
 * 			IMPORTS
********************************************************************************************************************/

package com.classuml.View;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Polygon;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;
import java.util.SortedMap;
import java.util.SortedSet;
import java.util.TreeMap;
import javax.swing.BorderFactory;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.UIManager;

import com.classuml.Control.GUIController;
import com.classuml.Model.Relationship;

public class GUI extends JPanel {
	private static final long serialVersionUID = 1L;
	public JFrame frame;
	private JMenuBar menuBar;
	private GUIController controller;

	/**
	 * Assigns a controller to manage interactions and data flow between this GUI
	 * and the application's model.
	 *
	 * @param controllerP The controller to be linked with this GUI.
	 */
	public void setController(GUIController controllerP) {
		controller = controllerP;
	}

	/**
	 * Calculates the destination panel's position relative to the source panel,
	 * categorizing it into one of eight regions (e.g., topLeft, topMid).
	 * Returns the position as a string, aiding in the visualization of UML
	 * relationships.
	 * 
	 * @param panel     The source panel from which the relationship arrow
	 *                  originates.
	 * @param panelDest The destination panel to which the relationship arrow
	 *                  points.
	 * @return A string indicating the relative location of panelDest with respect
	 *         to panel.
	 */

	private String destinationPanelLocation(JPanel panel, JPanel panelDest) {

		SortedMap<String, Rectangle> rectangles = new TreeMap<String, Rectangle>();
		Rectangle topLeft = new Rectangle(0, 0, panel.getX(), panel.getY());
		rectangles.put("topLeft", topLeft);
		Rectangle topMid = new Rectangle(panel.getX(), 0, panel.getWidth(), panel.getY());
		rectangles.put("topMid", topMid);
		Rectangle topRight = new Rectangle(panel.getX() + panel.getWidth(), 0,
				frame.getWidth() - (panel.getX() + panel.getWidth()), panel.getY());
		rectangles.put("topRight", topRight);
		Rectangle rightMid = new Rectangle(panel.getX() + panel.getWidth(), panel.getY(),
				frame.getWidth() - (panel.getX() + panel.getWidth()), panel.getHeight());
		rectangles.put("rightMid", rightMid);
		Rectangle rightBottom = new Rectangle(panel.getX() + panel.getWidth(), panel.getY() + panel.getHeight(),
				frame.getWidth() - (panel.getX() + panel.getWidth()),
				frame.getHeight() - (panel.getY() + panel.getHeight()));
		rectangles.put("rightBottom", rightBottom);
		Rectangle bottomMid = new Rectangle(panel.getX(), panel.getY() + panel.getHeight(), panel.getWidth(),
				frame.getHeight() - (panel.getY() + panel.getHeight()));
		rectangles.put("bottomMid", bottomMid);
		Rectangle bottomLeft = new Rectangle(0, panel.getY() + panel.getHeight(), panel.getX(),
				frame.getHeight() - (panel.getY() + panel.getHeight()));
		rectangles.put("bottomLeft", bottomLeft);
		Rectangle leftMid = new Rectangle(0, panel.getY(), panel.getX(), panel.getHeight());
		rectangles.put("leftMid", leftMid);

		int xDestinationMidPoint = panelDest.getX() + panelDest.getWidth() / 2;
		int yDestinationMidPoint = panelDest.getY() + panelDest.getHeight() / 2;

		String recLocation = "";
		for (SortedMap.Entry<String, Rectangle> rectangle : rectangles.entrySet()) {
			if (rectangle.getValue().contains(xDestinationMidPoint, yDestinationMidPoint)) {
				recLocation = rectangle.getKey();
			}
		}
		return recLocation;
	}

	/*******************************************************************************************************************
	 * ARROWS
	 ********************************************************************************************************************/

	/**
	 * Overrides the paintComponent method to draw UML relationship arrows between
	 * class panels.
	 * It ensures anti-aliasing for smoother arrow lines, determines the locations
	 * of class panels,
	 * and draws lines and arrows according to the relationship type (Aggregation,
	 * Composition,
	 * Inheritance, Realization). This method supports self-pointing arrows and
	 * adjusts arrow
	 * rendering based on the spatial relationship between source and destination
	 * panels.
	 * 
	 * @param g The Graphics object used for drawing operations.
	 */
	public void paintComponent(Graphics g) {

		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g;
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		List<Component> componentList = getAllComponents(this);
		// This sets the jpanel height and width to the labels height and width.
		for (int i = 0; i < componentList.size(); i = i + 2) {
			componentList.get(i).setSize(componentList.get(i + 1).getWidth(), componentList.get(i + 1).getHeight());

			((JComponent) componentList.get(i))
					.setBorder(BorderFactory.createMatteBorder(5, 10, 5, 10, Color.black));
			revalidate();
			repaint();
		}

		if (controller != null) {
			for (Entry<String, com.classuml.Model.ClassBase> entry : controller.getModel().getClasses().entrySet()) {
				if (entry.getValue().getRelationships() != null) {
					SortedSet<Relationship> relationships = entry.getValue().getRelationships();
					Iterator<Relationship> it = relationships.iterator();
					Relationship r = null;
					while (it.hasNext()) {
						r = it.next();
						int entryX = entry.getValue().getXLocation();
						int entryY = entry.getValue().getYLocation();
						int destinationX = controller.getModel().getClasses().get(r.getDestination()).getXLocation();
						int destinationY = controller.getModel().getClasses().get(r.getDestination()).getYLocation();

						JPanel panel = null;
						JPanel panelDest = null;
						for (int i = 0; i < componentList.size(); i++) {
							if (componentList.get(i).getName().equals(r.getDestination() + "Panel")) {
								panelDest = (JPanel) componentList.get(i);
							}
							if (componentList.get(i).getName().equals(entry.getValue().getName() + "Panel")) {
								panel = (JPanel) componentList.get(i);
							}
						}

						if (panel != null && panelDest != null) {
							// Aggregation Type
							if (r.getType().equals("Aggregation")) {
								// If it points to itself
								if (entry.getValue().getName().equals(r.getDestination())) {
									// Main Line
									g2d.setStroke(new BasicStroke(5));
									int xLinePoly[] = { entryX + panel.getWidth(), entryX + 40 + panel.getWidth(),
											entryX + 40 + panel.getWidth(), entryX + panel.getWidth() + 25 };
									int yLinePoly[] = { entryY + panel.getHeight() / 2, entryY + panel.getHeight() / 2,
											entryY + 5, entryY + 5 };
									g2d.drawPolyline(xLinePoly, yLinePoly, xLinePoly.length);

									int xTrianglePoly[] = { entryX + panel.getWidth() + 5,
											entryX + panel.getWidth() + 10 + 5, entryX + panel.getWidth() + 20 + 5,
											entryX + panel.getWidth() + 10 + 5 };
									int yTrianglePoly[] = { destinationY + 5, destinationY - 5 + 5, destinationY + 5,
											destinationY + 5 + 5 };
									Polygon triangle = new Polygon(xTrianglePoly, yTrianglePoly, xTrianglePoly.length);
									g2d.drawPolygon(triangle);
									g2d.setColor(new Color(255, 255, 255));
									g2d.fillPolygon(triangle);
									g2d.setColor(new Color(0, 0, 0));
								}
								// It doesn't point to itself make the arrows based on where the destination
								// panel is in regard to the original panel.
								else {
									String destinationPanelLocation = destinationPanelLocation(panel, panelDest);
									if (destinationPanelLocation.equals("topLeft")) {
										// Main Line
										g2d.setStroke(new BasicStroke(5));
										int xLinePoly[] = { panel.getX(), panel.getX() - 20,
												panelDest.getX() + panelDest.getWidth() + 20,
												panelDest.getX() + panelDest.getWidth() + 10 };
										int yLinePoly[] = { panel.getY(), panel.getY() - 20,
												panelDest.getY() + panelDest.getHeight() + 20,
												panelDest.getY() + panelDest.getHeight() + 10 };
										g2d.drawPolyline(xLinePoly, yLinePoly, xLinePoly.length);

										// Arrow Point
										int xTrianglePoly[] = { panelDest.getX() + panelDest.getWidth() + 2,
												panelDest.getX() + panelDest.getWidth() + 10 + 2,
												panelDest.getX() + panelDest.getWidth() + 13 + 2,
												panelDest.getX() + panelDest.getWidth() + 2 + 2 };
										int yTrianglePoly[] = { panelDest.getY() + panelDest.getHeight() + 2,
												panelDest.getY() + panelDest.getHeight() + 5 + 2,
												panelDest.getY() + panelDest.getHeight() + 13 + 2,
												panelDest.getY() + panelDest.getHeight() + 8 + 2 };
										Polygon triangle = new Polygon(xTrianglePoly, yTrianglePoly,
												xTrianglePoly.length);
										g2d.drawPolygon(triangle);
										g2d.setColor(new Color(255, 255, 255));
										g2d.fillPolygon(triangle);
										g2d.setColor(new Color(0, 0, 0));
									} else if (destinationPanelLocation.equals("topMid")) {
										// Main Line
										g2d.setStroke(new BasicStroke(5));
										int xLinePoly[] = { panel.getX() + panel.getWidth() / 2,
												panel.getX() + panel.getWidth() / 2,
												panelDest.getX() + panelDest.getWidth() / 2,
												panelDest.getX() + panelDest.getWidth() / 2 };
										int yLinePoly[] = { panel.getY(), panel.getY() - 20,
												panelDest.getY() + panelDest.getHeight() + 36,
												panelDest.getY() + panelDest.getHeight() + 10 };
										g2d.drawPolyline(xLinePoly, yLinePoly, xLinePoly.length);

										// Arrow Point
										int xTrianglePoly[] = { panelDest.getX() + panelDest.getWidth() / 2,
												panelDest.getX() + panelDest.getWidth() / 2 + 5,
												panelDest.getX() + panelDest.getWidth() / 2,
												panelDest.getX() + panelDest.getWidth() / 2 - 5 };
										int yTrianglePoly[] = { panelDest.getY() + panelDest.getHeight() + 5,
												panelDest.getY() + panelDest.getHeight() + 10 + 5,
												panelDest.getY() + panelDest.getHeight() + 20 + 5,
												panelDest.getY() + panelDest.getHeight() + 10 + 5 };
										Polygon triangle = new Polygon(xTrianglePoly, yTrianglePoly,
												xTrianglePoly.length);
										g2d.drawPolygon(triangle);
										g2d.setColor(new Color(255, 255, 255));
										g2d.fillPolygon(triangle);
										g2d.setColor(new Color(0, 0, 0));
									} else if (destinationPanelLocation.equals("topRight")) {
										// Main Line
										g2d.setStroke(new BasicStroke(5));
										int xLinePoly[] = { panel.getX() + panel.getWidth(),
												panel.getX() + panel.getWidth() + 20, panelDest.getX() - 20,
												panelDest.getX() - 10 };
										int yLinePoly[] = { panel.getY(), panel.getY() - 20,
												panelDest.getY() + panelDest.getHeight() + 20,
												panelDest.getY() + panelDest.getHeight() + 10 };
										g2d.drawPolyline(xLinePoly, yLinePoly, xLinePoly.length);

										// Arrow Point
										int xTrianglePoly[] = { panelDest.getX() - 2, panelDest.getX() - 10 - 2,
												panelDest.getX() - 13 - 2, panelDest.getX() - 2 - 2 };
										int yTrianglePoly[] = { panelDest.getY() + panelDest.getHeight() + 2,
												panelDest.getY() + panelDest.getHeight() + 5 + 2,
												panelDest.getY() + panelDest.getHeight() + 13 + 2,
												panelDest.getY() + panelDest.getHeight() + 9 + 2 };
										Polygon triangle = new Polygon(xTrianglePoly, yTrianglePoly,
												xTrianglePoly.length);
										g2d.drawPolygon(triangle);
										g2d.setColor(new Color(255, 255, 255));
										g2d.fillPolygon(triangle);
										g2d.setColor(new Color(0, 0, 0));
									} else if (destinationPanelLocation.equals("rightMid")) {
										// Main Line
										g2d.setStroke(new BasicStroke(5));
										int xLinePoly[] = { entryX + panel.getWidth(), entryX + 20 + panel.getWidth(),
												destinationX - 36, destinationX - 26 };
										int yLinePoly[] = { entryY + panel.getHeight() / 2,
												entryY + panel.getHeight() / 2,
												destinationY + panelDest.getHeight() / 2,
												destinationY + panelDest.getHeight() / 2 };
										g2d.drawPolyline(xLinePoly, yLinePoly, xLinePoly.length);

										// Arrow Point
										int xTrianglePoly[] = { destinationX - 5, destinationX - 10 - 5,
												destinationX - 20 - 5, destinationX - 10 - 5 };
										int yTrianglePoly[] = { destinationY + panelDest.getHeight() / 2,
												destinationY - 5 + panelDest.getHeight() / 2,
												destinationY + panelDest.getHeight() / 2,
												destinationY + 5 + panelDest.getHeight() / 2 };
										Polygon triangle = new Polygon(xTrianglePoly, yTrianglePoly,
												xTrianglePoly.length);
										g2d.drawPolygon(triangle);
										g2d.setColor(new Color(255, 255, 255));
										g2d.fillPolygon(triangle);
										g2d.setColor(new Color(0, 0, 0));
									} else if (destinationPanelLocation.equals("rightBottom")) {
										// Main Line
										g2d.setStroke(new BasicStroke(5));
										int xLinePoly[] = { panel.getX() + panel.getWidth(),
												panel.getX() + panel.getWidth() + 20, panelDest.getX() - 20,
												panelDest.getX() - 10 };
										int yLinePoly[] = { panel.getY() + panel.getHeight(),
												panel.getY() + panel.getHeight() + 20, panelDest.getY() - 20,
												panelDest.getY() - 10 };
										g2d.drawPolyline(xLinePoly, yLinePoly, xLinePoly.length);

										// Arrow Point
										int xTrianglePoly[] = { panelDest.getX() - 13 - 2, panelDest.getX() - 10 - 2,
												panelDest.getX() - 2, panelDest.getX() - 2 - 2 };
										int yTrianglePoly[] = { panelDest.getY() - 13 - 2, panelDest.getY() - 5 - 2,
												panelDest.getY() - 2, panelDest.getY() - 8 - 2 };
										Polygon triangle = new Polygon(xTrianglePoly, yTrianglePoly,
												xTrianglePoly.length);
										g2d.drawPolygon(triangle);
										g2d.setColor(new Color(255, 255, 255));
										g2d.fillPolygon(triangle);
										g2d.setColor(new Color(0, 0, 0));
									} else if (destinationPanelLocation.equals("bottomMid")) {
										// Main Line
										g2d.setStroke(new BasicStroke(5));
										int xLinePoly[] = { panel.getX() + panel.getWidth() / 2,
												panel.getX() + panel.getWidth() / 2,
												panelDest.getX() + panelDest.getWidth() / 2,
												panelDest.getX() + panelDest.getWidth() / 2 };
										int yLinePoly[] = { panel.getY() + panel.getHeight(),
												panel.getY() + panel.getHeight() + 20, panelDest.getY() - 36,
												panelDest.getY() - 10 };
										g2d.drawPolyline(xLinePoly, yLinePoly, xLinePoly.length);

										// Arrow Point
										int xTrianglePoly[] = { panelDest.getX() + panelDest.getWidth() / 2,
												panelDest.getX() + panelDest.getWidth() / 2 + 5,
												panelDest.getX() + panelDest.getWidth() / 2,
												panelDest.getX() + panelDest.getWidth() / 2 - 5 };
										int yTrianglePoly[] = { panelDest.getY() - 5, panelDest.getY() - 10 - 5,
												panelDest.getY() - 20 - 5, panelDest.getY() - 10 - 5 };
										Polygon triangle = new Polygon(xTrianglePoly, yTrianglePoly,
												xTrianglePoly.length);
										g2d.drawPolygon(triangle);
										g2d.setColor(new Color(255, 255, 255));
										g2d.fillPolygon(triangle);
										g2d.setColor(new Color(0, 0, 0));
									} else if (destinationPanelLocation.equals("bottomLeft")) {
										// Main Line
										g2d.setStroke(new BasicStroke(5));
										int xLinePoly[] = { panel.getX(), panel.getX() - 20,
												panelDest.getX() + panelDest.getWidth() + 20,
												panelDest.getX() + panelDest.getWidth() + 10 };
										int yLinePoly[] = { panel.getY() + panel.getHeight(),
												panel.getY() + panel.getHeight() + 20, panelDest.getY() - 20,
												panelDest.getY() - 10 };
										g2d.drawPolyline(xLinePoly, yLinePoly, xLinePoly.length);

										// Arrow Point
										int xTrianglePoly[] = { panelDest.getX() + panelDest.getWidth() + 2,
												panelDest.getX() + panelDest.getWidth() + 10 + 2,
												panelDest.getX() + panelDest.getWidth() + 12 + 2,
												panelDest.getX() + panelDest.getWidth() + 3 + 2 };
										int yTrianglePoly[] = { panelDest.getY() - 2, panelDest.getY() - 5 - 2,
												panelDest.getY() - 13 - 2, panelDest.getY() - 9 - 2 };
										Polygon triangle = new Polygon(xTrianglePoly, yTrianglePoly,
												xTrianglePoly.length);
										g2d.drawPolygon(triangle);
										g2d.setColor(new Color(255, 255, 255));
										g2d.fillPolygon(triangle);
										g2d.setColor(new Color(0, 0, 0));
									} else if (destinationPanelLocation.equals("leftMid")) {
										// Main Line
										g2d.setStroke(new BasicStroke(5));
										int xLinePoly[] = { panel.getX(), panel.getX() - 20,
												panelDest.getX() + panelDest.getWidth() + 36,
												panelDest.getX() + panelDest.getWidth() + 26 };
										int yLinePoly[] = { panel.getY() + panel.getHeight() / 2,
												panel.getY() + panel.getHeight() / 2,
												panelDest.getY() + panelDest.getHeight() / 2,
												panelDest.getY() + panelDest.getHeight() / 2 };
										g2d.drawPolyline(xLinePoly, yLinePoly, xLinePoly.length);

										// Arrow Point
										int xTrianglePoly[] = { panelDest.getX() + panelDest.getWidth() + 5,
												panelDest.getX() + panelDest.getWidth() + 10 + 5,
												panelDest.getX() + panelDest.getWidth() + 20 + 5,
												panelDest.getX() + panelDest.getWidth() + 10 + 5 };
										int yTrianglePoly[] = { panelDest.getY() + panelDest.getHeight() / 2,
												panelDest.getY() + panelDest.getHeight() / 2 - 5,
												panelDest.getY() + panelDest.getHeight() / 2,
												panelDest.getY() + panelDest.getHeight() / 2 + 5 };
										Polygon triangle = new Polygon(xTrianglePoly, yTrianglePoly,
												xTrianglePoly.length);
										g2d.drawPolygon(triangle);
										g2d.setColor(new Color(255, 255, 255));
										g2d.fillPolygon(triangle);
										g2d.setColor(new Color(0, 0, 0));
									}
								}
							}
							// If the type is Composition
							else if (r.getType().equals("Composition")) {
								// If it points to itself
								if (entry.getValue().getName().equals(r.getDestination())) {
									// Main Line
									g2d.setStroke(new BasicStroke(5));
									int xLinePoly[] = { entryX + panel.getWidth(), entryX + 40 + panel.getWidth(),
											entryX + 40 + panel.getWidth(), entryX + panel.getWidth() + 25 };
									int yLinePoly[] = { entryY + panel.getHeight() / 2, entryY + panel.getHeight() / 2,
											entryY + 5, entryY + 5 };
									g2d.drawPolyline(xLinePoly, yLinePoly, xLinePoly.length);

									int xTrianglePoly[] = { entryX + panel.getWidth() + 5,
											entryX + panel.getWidth() + 10 + 5, entryX + panel.getWidth() + 20 + 5,
											entryX + panel.getWidth() + 10 + 5 };
									int yTrianglePoly[] = { destinationY + 5, destinationY - 5 + 5, destinationY + 5,
											destinationY + 5 + 5 };
									Polygon triangle = new Polygon(xTrianglePoly, yTrianglePoly, xTrianglePoly.length);
									g2d.drawPolygon(triangle);
									g2d.fillPolygon(triangle);
								}
								// If it doesn't point to itself set the line based on the position of the
								// original panel and the destination panel.
								else {
									String destinationPanelLocation = destinationPanelLocation(panel, panelDest);
									if (destinationPanelLocation.equals("topLeft")) {
										// Main Line
										g2d.setStroke(new BasicStroke(5));
										int xLinePoly[] = { panel.getX(), panel.getX() - 20,
												panelDest.getX() + panelDest.getWidth() + 20,
												panelDest.getX() + panelDest.getWidth() + 10 };
										int yLinePoly[] = { panel.getY(), panel.getY() - 20,
												panelDest.getY() + panelDest.getHeight() + 20,
												panelDest.getY() + panelDest.getHeight() + 10 };
										g2d.drawPolyline(xLinePoly, yLinePoly, xLinePoly.length);

										// Arrow Point
										int xTrianglePoly[] = { panelDest.getX() + panelDest.getWidth() + 2,
												panelDest.getX() + panelDest.getWidth() + 10 + 2,
												panelDest.getX() + panelDest.getWidth() + 13 + 2,
												panelDest.getX() + panelDest.getWidth() + 2 + 2 };
										int yTrianglePoly[] = { panelDest.getY() + panelDest.getHeight() + 2,
												panelDest.getY() + panelDest.getHeight() + 5 + 2,
												panelDest.getY() + panelDest.getHeight() + 13 + 2,
												panelDest.getY() + panelDest.getHeight() + 8 + 2 };
										Polygon triangle = new Polygon(xTrianglePoly, yTrianglePoly,
												xTrianglePoly.length);
										g2d.drawPolygon(triangle);
										g2d.fillPolygon(triangle);
									} else if (destinationPanelLocation.equals("topMid")) {
										// Main Line
										g2d.setStroke(new BasicStroke(5));
										int xLinePoly[] = { panel.getX() + panel.getWidth() / 2,
												panel.getX() + panel.getWidth() / 2,
												panelDest.getX() + panelDest.getWidth() / 2,
												panelDest.getX() + panelDest.getWidth() / 2 };
										int yLinePoly[] = { panel.getY(), panel.getY() - 20,
												panelDest.getY() + panelDest.getHeight() + 36,
												panelDest.getY() + panelDest.getHeight() + 10 };
										g2d.drawPolyline(xLinePoly, yLinePoly, xLinePoly.length);

										// Arrow Point
										int xTrianglePoly[] = { panelDest.getX() + panelDest.getWidth() / 2,
												panelDest.getX() + panelDest.getWidth() / 2 + 5,
												panelDest.getX() + panelDest.getWidth() / 2,
												panelDest.getX() + panelDest.getWidth() / 2 - 5 };
										int yTrianglePoly[] = { panelDest.getY() + panelDest.getHeight() + 5,
												panelDest.getY() + panelDest.getHeight() + 10 + 5,
												panelDest.getY() + panelDest.getHeight() + 20 + 5,
												panelDest.getY() + panelDest.getHeight() + 10 + 5 };
										Polygon triangle = new Polygon(xTrianglePoly, yTrianglePoly,
												xTrianglePoly.length);
										g2d.drawPolygon(triangle);
										g2d.fillPolygon(triangle);
									} else if (destinationPanelLocation.equals("topRight")) {
										// Main Line
										g2d.setStroke(new BasicStroke(5));
										int xLinePoly[] = { panel.getX() + panel.getWidth(),
												panel.getX() + panel.getWidth() + 20, panelDest.getX() - 20,
												panelDest.getX() - 10 };
										int yLinePoly[] = { panel.getY(), panel.getY() - 20,
												panelDest.getY() + panelDest.getHeight() + 20,
												panelDest.getY() + panelDest.getHeight() + 10 };
										g2d.drawPolyline(xLinePoly, yLinePoly, xLinePoly.length);

										// Arrow Point
										int xTrianglePoly[] = { panelDest.getX() - 2, panelDest.getX() - 10 - 2,
												panelDest.getX() - 13 - 2, panelDest.getX() - 2 - 2 };
										int yTrianglePoly[] = { panelDest.getY() + panelDest.getHeight() + 2,
												panelDest.getY() + panelDest.getHeight() + 5 + 2,
												panelDest.getY() + panelDest.getHeight() + 13 + 2,
												panelDest.getY() + panelDest.getHeight() + 9 + 2 };
										Polygon triangle = new Polygon(xTrianglePoly, yTrianglePoly,
												xTrianglePoly.length);
										g2d.drawPolygon(triangle);
										g2d.fillPolygon(triangle);
									}

									else if (destinationPanelLocation.equals("rightMid")) {
										// Main Line
										g2d.setStroke(new BasicStroke(5));
										int xLinePoly[] = { entryX + panel.getWidth(), entryX + 20 + panel.getWidth(),
												destinationX - 36, destinationX - 26 };
										int yLinePoly[] = { entryY + panel.getHeight() / 2,
												entryY + panel.getHeight() / 2,
												destinationY + panelDest.getHeight() / 2,
												destinationY + panelDest.getHeight() / 2 };
										g2d.drawPolyline(xLinePoly, yLinePoly, xLinePoly.length);

										// Arrow Point
										int xTrianglePoly[] = { destinationX - 5, destinationX - 10 - 5,
												destinationX - 20 - 5, destinationX - 10 - 5 };
										int yTrianglePoly[] = { destinationY + panelDest.getHeight() / 2,
												destinationY - 5 + panelDest.getHeight() / 2,
												destinationY + panelDest.getHeight() / 2,
												destinationY + 5 + panelDest.getHeight() / 2 };
										Polygon triangle = new Polygon(xTrianglePoly, yTrianglePoly,
												xTrianglePoly.length);
										g2d.drawPolygon(triangle);
										g2d.fillPolygon(triangle);
									} else if (destinationPanelLocation.equals("rightBottom")) {
										// Main Line
										g2d.setStroke(new BasicStroke(5));
										int xLinePoly[] = { panel.getX() + panel.getWidth(),
												panel.getX() + panel.getWidth() + 20, panelDest.getX() - 20,
												panelDest.getX() - 10 };
										int yLinePoly[] = { panel.getY() + panel.getHeight(),
												panel.getY() + panel.getHeight() + 20, panelDest.getY() - 20,
												panelDest.getY() - 10 };
										g2d.drawPolyline(xLinePoly, yLinePoly, xLinePoly.length);

										// Arrow Point
										int xTrianglePoly[] = { panelDest.getX() - 13 - 2, panelDest.getX() - 10 - 2,
												panelDest.getX() - 2, panelDest.getX() - 2 - 2 };
										int yTrianglePoly[] = { panelDest.getY() - 13 - 2, panelDest.getY() - 5 - 2,
												panelDest.getY() - 2, panelDest.getY() - 8 - 2 };
										Polygon triangle = new Polygon(xTrianglePoly, yTrianglePoly,
												xTrianglePoly.length);
										g2d.drawPolygon(triangle);
										g2d.fillPolygon(triangle);
									} else if (destinationPanelLocation.equals("bottomMid")) {
										// Main Line
										g2d.setStroke(new BasicStroke(5));
										int xLinePoly[] = { panel.getX() + panel.getWidth() / 2,
												panel.getX() + panel.getWidth() / 2,
												panelDest.getX() + panelDest.getWidth() / 2,
												panelDest.getX() + panelDest.getWidth() / 2 };
										int yLinePoly[] = { panel.getY() + panel.getHeight(),
												panel.getY() + panel.getHeight() + 20, panelDest.getY() - 36,
												panelDest.getY() - 10 };
										g2d.drawPolyline(xLinePoly, yLinePoly, xLinePoly.length);

										// Arrow Point
										int xTrianglePoly[] = { panelDest.getX() + panelDest.getWidth() / 2,
												panelDest.getX() + panelDest.getWidth() / 2 + 5,
												panelDest.getX() + panelDest.getWidth() / 2,
												panelDest.getX() + panelDest.getWidth() / 2 - 5 };
										int yTrianglePoly[] = { panelDest.getY() - 5, panelDest.getY() - 10 - 5,
												panelDest.getY() - 20 - 5, panelDest.getY() - 10 - 5 };
										Polygon triangle = new Polygon(xTrianglePoly, yTrianglePoly,
												xTrianglePoly.length);
										g2d.drawPolygon(triangle);
										g2d.fillPolygon(triangle);
									}

									else if (destinationPanelLocation.equals("bottomLeft")) {
										// Main Line
										g2d.setStroke(new BasicStroke(5));
										int xLinePoly[] = { panel.getX(), panel.getX() - 20,
												panelDest.getX() + panelDest.getWidth() + 20,
												panelDest.getX() + panelDest.getWidth() + 10 };
										int yLinePoly[] = { panel.getY() + panel.getHeight(),
												panel.getY() + panel.getHeight() + 20, panelDest.getY() - 20,
												panelDest.getY() - 10 };
										g2d.drawPolyline(xLinePoly, yLinePoly, xLinePoly.length);

										// Arrow Point
										int xTrianglePoly[] = { panelDest.getX() + panelDest.getWidth() + 2,
												panelDest.getX() + panelDest.getWidth() + 10 + 2,
												panelDest.getX() + panelDest.getWidth() + 12 + 2,
												panelDest.getX() + panelDest.getWidth() + 3 + 2 };
										int yTrianglePoly[] = { panelDest.getY() - 2, panelDest.getY() - 5 - 2,
												panelDest.getY() - 13 - 2, panelDest.getY() - 9 - 2 };
										Polygon triangle = new Polygon(xTrianglePoly, yTrianglePoly,
												xTrianglePoly.length);
										g2d.drawPolygon(triangle);
										g2d.fillPolygon(triangle);
									} else if (destinationPanelLocation.equals("leftMid")) {
										// Main Line
										g2d.setStroke(new BasicStroke(5));
										int xLinePoly[] = { panel.getX(), panel.getX() - 20,
												panelDest.getX() + panelDest.getWidth() + 36,
												panelDest.getX() + panelDest.getWidth() + 26 };
										int yLinePoly[] = { panel.getY() + panel.getHeight() / 2,
												panel.getY() + panel.getHeight() / 2,
												panelDest.getY() + panelDest.getHeight() / 2,
												panelDest.getY() + panelDest.getHeight() / 2 };
										g2d.drawPolyline(xLinePoly, yLinePoly, xLinePoly.length);

										// Arrow Point
										int xTrianglePoly[] = { panelDest.getX() + panelDest.getWidth() + 5,
												panelDest.getX() + panelDest.getWidth() + 10 + 5,
												panelDest.getX() + panelDest.getWidth() + 20 + 5,
												panelDest.getX() + panelDest.getWidth() + 10 + 5 };
										int yTrianglePoly[] = { panelDest.getY() + panelDest.getHeight() / 2,
												panelDest.getY() + panelDest.getHeight() / 2 - 5,
												panelDest.getY() + panelDest.getHeight() / 2,
												panelDest.getY() + panelDest.getHeight() / 2 + 5 };
										Polygon triangle = new Polygon(xTrianglePoly, yTrianglePoly,
												xTrianglePoly.length);
										g2d.drawPolygon(triangle);
										g2d.fillPolygon(triangle);
									}
								}
							}

							// If the type is Inheritance
							else if (r.getType().equals("Inheritance")) {
								// If it points to itself
								if (entry.getValue().getName().equals(r.getDestination())) {
									// Main Line
									g2d.setStroke(new BasicStroke(5));
									int xLinePoly[] = { entryX + panel.getWidth(), entryX + 40 + panel.getWidth(),
											entryX + 40 + panel.getWidth(), entryX + panel.getWidth() + 20 };
									int yLinePoly[] = { entryY + panel.getHeight() / 2, entryY + panel.getHeight() / 2,
											entryY + 10, entryY + 10 };
									g2d.drawPolyline(xLinePoly, yLinePoly, xLinePoly.length);

									int xTrianglePoly[] = { entryX + panel.getWidth() + 5,
											entryX + panel.getWidth() + 10 + 5, entryX + panel.getWidth() + 5 + 10 };
									int yTrianglePoly[] = { destinationY + 10, destinationY - 5 + 10,
											destinationY + 10 + 5 };
									Polygon triangle = new Polygon(xTrianglePoly, yTrianglePoly, xTrianglePoly.length);
									g2d.drawPolygon(triangle);
									g2d.setColor(new Color(255, 255, 255));
									g2d.fillPolygon(triangle);
									g2d.setColor(new Color(0, 0, 0));
								}

								else {
									// If it doesn't point to itself set the line based on the position of the
									// original panel and the destination panel.
									String destinationPanelLocation = destinationPanelLocation(panel, panelDest);
									if (destinationPanelLocation.equals("topLeft")) {
										// Main Line
										g2d.setStroke(new BasicStroke(5));
										int xLinePoly[] = { panel.getX(), panel.getX() - 20,
												panelDest.getX() + panelDest.getWidth() + 20,
												panelDest.getX() + panelDest.getWidth() + 10 };
										int yLinePoly[] = { panel.getY(), panel.getY() - 20,
												panelDest.getY() + panelDest.getHeight() + 20,
												panelDest.getY() + panelDest.getHeight() + 10 };
										g2d.drawPolyline(xLinePoly, yLinePoly, xLinePoly.length);

										// Arrow Point
										int xTrianglePoly[] = { panelDest.getX() + panelDest.getWidth() + 2,
												panelDest.getX() + panelDest.getWidth() + 10 + 2,
												panelDest.getX() + panelDest.getWidth() + 4 + 2 };
										int yTrianglePoly[] = { panelDest.getY() + panelDest.getHeight() + 2,
												panelDest.getY() + panelDest.getHeight() + 4 + 2,
												panelDest.getY() + panelDest.getHeight() + 11 + 2 };
										Polygon triangle = new Polygon(xTrianglePoly, yTrianglePoly,
												xTrianglePoly.length);
										g2d.drawPolygon(triangle);
										g2d.setColor(new Color(255, 255, 255));
										g2d.fillPolygon(triangle);
										g2d.setColor(new Color(0, 0, 0));
									}

									else if (destinationPanelLocation.equals("topMid")) {
										// Main Line
										g2d.setStroke(new BasicStroke(5));
										int xLinePoly[] = { panel.getX() + panel.getWidth() / 2,
												panel.getX() + panel.getWidth() / 2,
												panelDest.getX() + panelDest.getWidth() / 2,
												panelDest.getX() + panelDest.getWidth() / 2 };
										int yLinePoly[] = { panel.getY(), panel.getY() - 20,
												panelDest.getY() + panelDest.getHeight() + 36,
												panelDest.getY() + panelDest.getHeight() + 10 };
										g2d.drawPolyline(xLinePoly, yLinePoly, xLinePoly.length);

										// Arrow Point
										int xTrianglePoly[] = { panelDest.getX() + panelDest.getWidth() / 2,
												panelDest.getX() + panelDest.getWidth() / 2 + 5,
												panelDest.getX() + panelDest.getWidth() / 2 - 5 };
										int yTrianglePoly[] = { panelDest.getY() + panelDest.getHeight() + 5,
												panelDest.getY() + panelDest.getHeight() + 10 + 5,
												panelDest.getY() + panelDest.getHeight() + 10 + 5 };
										Polygon triangle = new Polygon(xTrianglePoly, yTrianglePoly,
												xTrianglePoly.length);
										g2d.drawPolygon(triangle);
										g2d.setColor(new Color(255, 255, 255));
										g2d.fillPolygon(triangle);
										g2d.setColor(new Color(0, 0, 0));
									} else if (destinationPanelLocation.equals("topRight")) {
										// Main Line
										g2d.setStroke(new BasicStroke(5));
										int xLinePoly[] = { panel.getX() + panel.getWidth(),
												panel.getX() + panel.getWidth() + 20, panelDest.getX() - 20,
												panelDest.getX() - 10 };
										int yLinePoly[] = { panel.getY(), panel.getY() - 20,
												panelDest.getY() + panelDest.getHeight() + 20,
												panelDest.getY() + panelDest.getHeight() + 10 };
										g2d.drawPolyline(xLinePoly, yLinePoly, xLinePoly.length);

										// Arrow Point
										int xTrianglePoly[] = { panelDest.getX() - 2, panelDest.getX() - 10 - 2,
												panelDest.getX() - 4 - 2 };
										int yTrianglePoly[] = { panelDest.getY() + panelDest.getHeight() + 2,
												panelDest.getY() + panelDest.getHeight() + 4 + 2,
												panelDest.getY() + panelDest.getHeight() + 11 + 2 };
										Polygon triangle = new Polygon(xTrianglePoly, yTrianglePoly,
												xTrianglePoly.length);
										g2d.drawPolygon(triangle);
										g2d.setColor(new Color(255, 255, 255));
										g2d.fillPolygon(triangle);
										g2d.setColor(new Color(0, 0, 0));
									}

									else if (destinationPanelLocation.equals("rightMid")) {
										// Main Line
										g2d.setStroke(new BasicStroke(5));
										int xLinePoly[] = { entryX + panel.getWidth(), entryX + 20 + panel.getWidth(),
												panelDest.getX() - 36, panelDest.getX() - 18 };
										int yLinePoly[] = { entryY + panel.getHeight() / 2,
												entryY + panel.getHeight() / 2,
												panelDest.getY() + panelDest.getHeight() / 2,
												panelDest.getY() + panelDest.getHeight() / 2 };
										g2d.drawPolyline(xLinePoly, yLinePoly, xLinePoly.length);

										// Arrow Point
										int xTrianglePoly[] = { panelDest.getX() - 5, panelDest.getX() - 10 - 5,
												panelDest.getX() - 10 - 5 };
										int yTrianglePoly[] = { panelDest.getY() + panelDest.getHeight() / 2,
												panelDest.getY() - 5 + panelDest.getHeight() / 2,
												panelDest.getY() + 5 + panelDest.getHeight() / 2 };
										Polygon triangle = new Polygon(xTrianglePoly, yTrianglePoly,
												xTrianglePoly.length);
										g2d.drawPolygon(triangle);
										g2d.setColor(new Color(255, 255, 255));
										g2d.fillPolygon(triangle);
										g2d.setColor(new Color(0, 0, 0));
									} else if (destinationPanelLocation.equals("rightBottom")) {
										// Main Line
										g2d.setStroke(new BasicStroke(5));
										int xLinePoly[] = { panel.getX() + panel.getWidth(),
												panel.getX() + panel.getWidth() + 20, panelDest.getX() - 20,
												panelDest.getX() - 10 };
										int yLinePoly[] = { panel.getY() + panel.getHeight(),
												panel.getY() + panel.getHeight() + 20, panelDest.getY() - 20,
												panelDest.getY() - 10 };
										g2d.drawPolyline(xLinePoly, yLinePoly, xLinePoly.length);

										// Arrow Point
										int xTrianglePoly[] = { panelDest.getX() - 2, panelDest.getX() - 11 - 2,
												panelDest.getX() - 4 - 2 };
										int yTrianglePoly[] = { panelDest.getY() - 2, panelDest.getY() - 5 - 2,
												panelDest.getY() - 11 - 2 };
										Polygon triangle = new Polygon(xTrianglePoly, yTrianglePoly,
												xTrianglePoly.length);
										g2d.drawPolygon(triangle);
										g2d.setColor(new Color(255, 255, 255));
										g2d.fillPolygon(triangle);
										g2d.setColor(new Color(0, 0, 0));
									} else if (destinationPanelLocation.equals("bottomMid")) {
										// Main Line
										g2d.setStroke(new BasicStroke(5));
										int xLinePoly[] = { panel.getX() + panel.getWidth() / 2,
												panel.getX() + panel.getWidth() / 2,
												panelDest.getX() + panelDest.getWidth() / 2,
												panelDest.getX() + panelDest.getWidth() / 2 };
										int yLinePoly[] = { panel.getY() + panel.getHeight(),
												panel.getY() + panel.getHeight() + 20, panelDest.getY() - 36,
												panelDest.getY() - 10 };
										g2d.drawPolyline(xLinePoly, yLinePoly, xLinePoly.length);

										// Arrow Point
										int xTrianglePoly[] = { panelDest.getX() + panelDest.getWidth() / 2,
												panelDest.getX() + panelDest.getWidth() / 2 + 5,
												panelDest.getX() + panelDest.getWidth() / 2 - 5 };
										int yTrianglePoly[] = { panelDest.getY() - 5, panelDest.getY() - 10 - 5,
												panelDest.getY() - 10 - 5 };
										Polygon triangle = new Polygon(xTrianglePoly, yTrianglePoly,
												xTrianglePoly.length);
										g2d.drawPolygon(triangle);
										g2d.setColor(new Color(255, 255, 255));
										g2d.fillPolygon(triangle);
										g2d.setColor(new Color(0, 0, 0));
									}

									else if (destinationPanelLocation.equals("bottomLeft")) {
										// Main Line
										g2d.setStroke(new BasicStroke(5));
										int xLinePoly[] = { panel.getX(), panel.getX() - 20,
												panelDest.getX() + panelDest.getWidth() + 20,
												panelDest.getX() + panelDest.getWidth() + 10 };
										int yLinePoly[] = { panel.getY() + panel.getHeight(),
												panel.getY() + panel.getHeight() + 20, panelDest.getY() - 20,
												panelDest.getY() - 10 };
										g2d.drawPolyline(xLinePoly, yLinePoly, xLinePoly.length);

										// Arrow Point
										int xTrianglePoly[] = { panelDest.getX() + panelDest.getWidth() + 2,
												panelDest.getX() + panelDest.getWidth() + 10 + 2,
												panelDest.getX() + panelDest.getWidth() + 4 + 2 };
										int yTrianglePoly[] = { panelDest.getY() - 2, panelDest.getY() - 4 - 2,
												panelDest.getY() - 11 - 2 };
										Polygon triangle = new Polygon(xTrianglePoly, yTrianglePoly,
												xTrianglePoly.length);
										g2d.drawPolygon(triangle);
										g2d.setColor(new Color(255, 255, 255));
										g2d.fillPolygon(triangle);
										g2d.setColor(new Color(0, 0, 0));
									} else if (destinationPanelLocation.equals("leftMid")) {
										// Main Line
										g2d.setStroke(new BasicStroke(5));
										int xLinePoly[] = { panel.getX(), panel.getX() - 20,
												panelDest.getX() + panelDest.getWidth() + 36,
												panelDest.getX() + panelDest.getWidth() + 10 };
										int yLinePoly[] = { panel.getY() + panel.getHeight() / 2,
												panel.getY() + panel.getHeight() / 2,
												panelDest.getY() + panelDest.getHeight() / 2,
												panelDest.getY() + panelDest.getHeight() / 2 };
										g2d.drawPolyline(xLinePoly, yLinePoly, xLinePoly.length);

										// Arrow Point
										int xTrianglePoly[] = { panelDest.getX() + panelDest.getWidth() + 5,
												panelDest.getX() + panelDest.getWidth() + 10 + 5,
												panelDest.getX() + panelDest.getWidth() + 10 + 5 };
										int yTrianglePoly[] = { panelDest.getY() + panelDest.getHeight() / 2,
												panelDest.getY() + panelDest.getHeight() / 2 - 5,
												panelDest.getY() + panelDest.getHeight() / 2 + 5 };
										Polygon triangle = new Polygon(xTrianglePoly, yTrianglePoly,
												xTrianglePoly.length);
										g2d.drawPolygon(triangle);
										g2d.setColor(new Color(255, 255, 255));
										g2d.fillPolygon(triangle);
										g2d.setColor(new Color(0, 0, 0));
									}
								}
							}
							// If the type is Realization
							else if (r.getType().equals("Realization")) {
								// If it points to itself
								if (entry.getValue().getName().equals(r.getDestination())) {
									// Main Line
									g2d.setStroke(new BasicStroke(3, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL, 0,
											new float[] { 9 }, 0));
									int xLinePoly[] = { entryX + panel.getWidth(), entryX + 40 + panel.getWidth(),
											entryX + 40 + panel.getWidth(), entryX + panel.getWidth() + 20 };
									int yLinePoly[] = { entryY + panel.getHeight() / 2, entryY + panel.getHeight() / 2,
											entryY + 10, entryY + 10 };
									g2d.drawPolyline(xLinePoly, yLinePoly, xLinePoly.length);

									int xTrianglePoly[] = { entryX + panel.getWidth() + 5,
											entryX + panel.getWidth() + 10 + 5, entryX + panel.getWidth() + 5 + 10 };
									int yTrianglePoly[] = { destinationY + 10, destinationY - 5 + 10,
											destinationY + 10 + 5 };
									Polygon triangle = new Polygon(xTrianglePoly, yTrianglePoly, xTrianglePoly.length);
									g2d.setStroke(new BasicStroke(5));

									g2d.drawPolygon(triangle);
									g2d.setColor(new Color(255, 255, 255));
									g2d.fillPolygon(triangle);
									g2d.setColor(new Color(0, 0, 0));
								}
								// If it doesn't point to itself set the line based on the position of the
								// original panel and the destination panel.
								else {
									String destinationPanelLocation = destinationPanelLocation(panel, panelDest);
									if (destinationPanelLocation.equals("topLeft")) {
										// Main Line
										g2d.setStroke(new BasicStroke(3, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL,
												0, new float[] { 9 }, 0));
										int xLinePoly[] = { panel.getX(), panel.getX() - 20,
												panelDest.getX() + panelDest.getWidth() + 20,
												panelDest.getX() + panelDest.getWidth() + 10 };
										int yLinePoly[] = { panel.getY(), panel.getY() - 20,
												panelDest.getY() + panelDest.getHeight() + 20,
												panelDest.getY() + panelDest.getHeight() + 10 };
										g2d.drawPolyline(xLinePoly, yLinePoly, xLinePoly.length);

										// Arrow Point
										int xTrianglePoly[] = { panelDest.getX() + panelDest.getWidth() + 2,
												panelDest.getX() + panelDest.getWidth() + 10 + 2,
												panelDest.getX() + panelDest.getWidth() + 4 + 2 };
										int yTrianglePoly[] = { panelDest.getY() + panelDest.getHeight() + 2,
												panelDest.getY() + panelDest.getHeight() + 4 + 2,
												panelDest.getY() + panelDest.getHeight() + 11 + 2 };
										Polygon triangle = new Polygon(xTrianglePoly, yTrianglePoly,
												xTrianglePoly.length);
										g2d.setStroke(new BasicStroke(5));
										g2d.drawPolygon(triangle);
										g2d.setColor(new Color(255, 255, 255));
										g2d.fillPolygon(triangle);
										g2d.setColor(new Color(0, 0, 0));
									}

									else if (destinationPanelLocation.equals("topMid")) {
										// Main Line
										g2d.setStroke(new BasicStroke(3, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL,
												0, new float[] { 9 }, 0));
										int xLinePoly[] = { panel.getX() + panel.getWidth() / 2,
												panel.getX() + panel.getWidth() / 2,
												panelDest.getX() + panelDest.getWidth() / 2,
												panelDest.getX() + panelDest.getWidth() / 2 };
										int yLinePoly[] = { panel.getY(), panel.getY() - 20,
												panelDest.getY() + panelDest.getHeight() + 36,
												panelDest.getY() + panelDest.getHeight() + 10 };
										g2d.drawPolyline(xLinePoly, yLinePoly, xLinePoly.length);

										// Arrow Point
										int xTrianglePoly[] = { panelDest.getX() + panelDest.getWidth() / 2,
												panelDest.getX() + panelDest.getWidth() / 2 + 5,
												panelDest.getX() + panelDest.getWidth() / 2 - 5 };
										int yTrianglePoly[] = { panelDest.getY() + panelDest.getHeight() + 5,
												panelDest.getY() + panelDest.getHeight() + 10 + 5,
												panelDest.getY() + panelDest.getHeight() + 10 + 5 };
										Polygon triangle = new Polygon(xTrianglePoly, yTrianglePoly,
												xTrianglePoly.length);
										g2d.setStroke(new BasicStroke(5));
										g2d.drawPolygon(triangle);
										g2d.setColor(new Color(255, 255, 255));
										g2d.fillPolygon(triangle);
										g2d.setColor(new Color(0, 0, 0));
									} else if (destinationPanelLocation.equals("topRight")) {
										// Main Line
										g2d.setStroke(new BasicStroke(3, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL,
												0, new float[] { 9 }, 0));
										int xLinePoly[] = { panel.getX() + panel.getWidth(),
												panel.getX() + panel.getWidth() + 20, panelDest.getX() - 20,
												panelDest.getX() - 10 };
										int yLinePoly[] = { panel.getY(), panel.getY() - 20,
												panelDest.getY() + panelDest.getHeight() + 20,
												panelDest.getY() + panelDest.getHeight() + 10 };
										g2d.drawPolyline(xLinePoly, yLinePoly, xLinePoly.length);

										// Arrow Point
										int xTrianglePoly[] = { panelDest.getX() - 2, panelDest.getX() - 10 - 2,
												panelDest.getX() - 4 - 2 };
										int yTrianglePoly[] = { panelDest.getY() + panelDest.getHeight() + 2,
												panelDest.getY() + panelDest.getHeight() + 4 + 2,
												panelDest.getY() + panelDest.getHeight() + 11 + 2 };
										Polygon triangle = new Polygon(xTrianglePoly, yTrianglePoly,
												xTrianglePoly.length);
										g2d.setStroke(new BasicStroke(5));
										g2d.drawPolygon(triangle);
										g2d.setColor(new Color(255, 255, 255));
										g2d.fillPolygon(triangle);
										g2d.setColor(new Color(0, 0, 0));
									}

									else if (destinationPanelLocation.equals("rightMid")) {
										// Main Line
										g2d.setStroke(new BasicStroke(3, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL,
												0, new float[] { 9 }, 0));
										int xLinePoly[] = { entryX + panel.getWidth(), entryX + 20 + panel.getWidth(),
												panelDest.getX() - 36, panelDest.getX() - 18 };
										int yLinePoly[] = { entryY + panel.getHeight() / 2,
												entryY + panel.getHeight() / 2,
												panelDest.getY() + panelDest.getHeight() / 2,
												panelDest.getY() + panelDest.getHeight() / 2 };
										g2d.drawPolyline(xLinePoly, yLinePoly, xLinePoly.length);

										// Arrow Point
										int xTrianglePoly[] = { panelDest.getX() - 5, panelDest.getX() - 10 - 5,
												panelDest.getX() - 10 - 5 };
										int yTrianglePoly[] = { panelDest.getY() + panelDest.getHeight() / 2,
												panelDest.getY() - 5 + panelDest.getHeight() / 2,
												panelDest.getY() + 5 + panelDest.getHeight() / 2 };
										Polygon triangle = new Polygon(xTrianglePoly, yTrianglePoly,
												xTrianglePoly.length);
										g2d.setStroke(new BasicStroke(5));
										g2d.drawPolygon(triangle);
										g2d.setColor(new Color(255, 255, 255));
										g2d.fillPolygon(triangle);
										g2d.setColor(new Color(0, 0, 0));
									} else if (destinationPanelLocation.equals("rightBottom")) {
										// Main Line
										g2d.setStroke(new BasicStroke(3, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL,
												0, new float[] { 9 }, 0));
										int xLinePoly[] = { panel.getX() + panel.getWidth(),
												panel.getX() + panel.getWidth() + 20, panelDest.getX() - 20,
												panelDest.getX() - 10 };
										int yLinePoly[] = { panel.getY() + panel.getHeight(),
												panel.getY() + panel.getHeight() + 20, panelDest.getY() - 20,
												panelDest.getY() - 10 };
										g2d.drawPolyline(xLinePoly, yLinePoly, xLinePoly.length);

										// Arrow Point
										int xTrianglePoly[] = { panelDest.getX() - 2, panelDest.getX() - 11 - 2,
												panelDest.getX() - 4 - 2 };
										int yTrianglePoly[] = { panelDest.getY() - 2, panelDest.getY() - 5 - 2,
												panelDest.getY() - 11 - 2 };
										Polygon triangle = new Polygon(xTrianglePoly, yTrianglePoly,
												xTrianglePoly.length);
										g2d.setStroke(new BasicStroke(5));

										g2d.drawPolygon(triangle);
										g2d.setColor(new Color(255, 255, 255));
										g2d.fillPolygon(triangle);
										g2d.setColor(new Color(0, 0, 0));
									} else if (destinationPanelLocation.equals("bottomMid")) {
										// Main Line
										g2d.setStroke(new BasicStroke(3, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL,
												0, new float[] { 9 }, 0));
										int xLinePoly[] = { panel.getX() + panel.getWidth() / 2,
												panel.getX() + panel.getWidth() / 2,
												panelDest.getX() + panelDest.getWidth() / 2,
												panelDest.getX() + panelDest.getWidth() / 2 };
										int yLinePoly[] = { panel.getY() + panel.getHeight(),
												panel.getY() + panel.getHeight() + 20, panelDest.getY() - 36,
												panelDest.getY() - 10 };
										g2d.drawPolyline(xLinePoly, yLinePoly, xLinePoly.length);

										// Arrow Point
										int xTrianglePoly[] = { panelDest.getX() + panelDest.getWidth() / 2,
												panelDest.getX() + panelDest.getWidth() / 2 + 5,
												panelDest.getX() + panelDest.getWidth() / 2 - 5 };
										int yTrianglePoly[] = { panelDest.getY() - 5, panelDest.getY() - 10 - 5,
												panelDest.getY() - 10 - 5 };
										Polygon triangle = new Polygon(xTrianglePoly, yTrianglePoly,
												xTrianglePoly.length);
										g2d.setStroke(new BasicStroke(5));
										g2d.drawPolygon(triangle);
										g2d.setColor(new Color(255, 255, 255));
										g2d.fillPolygon(triangle);
										g2d.setColor(new Color(0, 0, 0));
									}

									else if (destinationPanelLocation.equals("bottomLeft")) {
										// Main Line
										g2d.setStroke(new BasicStroke(3, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL,
												0, new float[] { 9 }, 0));
										int xLinePoly[] = { panel.getX(), panel.getX() - 20,
												panelDest.getX() + panelDest.getWidth() + 20,
												panelDest.getX() + panelDest.getWidth() + 10 };
										int yLinePoly[] = { panel.getY() + panel.getHeight(),
												panel.getY() + panel.getHeight() + 20, panelDest.getY() - 20,
												panelDest.getY() - 10 };
										g2d.drawPolyline(xLinePoly, yLinePoly, xLinePoly.length);

										// Arrow Point
										int xTrianglePoly[] = { panelDest.getX() + panelDest.getWidth() + 2,
												panelDest.getX() + panelDest.getWidth() + 10 + 2,
												panelDest.getX() + panelDest.getWidth() + 4 + 2 };
										int yTrianglePoly[] = { panelDest.getY() - 2, panelDest.getY() - 4 - 2,
												panelDest.getY() - 11 - 2 };
										Polygon triangle = new Polygon(xTrianglePoly, yTrianglePoly,
												xTrianglePoly.length);
										g2d.setStroke(new BasicStroke(5));
										g2d.drawPolygon(triangle);
										g2d.setColor(new Color(255, 255, 255));
										g2d.fillPolygon(triangle);
										g2d.setColor(new Color(0, 0, 0));
									} else if (destinationPanelLocation.equals("leftMid")) {
										// Main Line
										g2d.setStroke(new BasicStroke(3, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL,
												0, new float[] { 9 }, 0));
										int xLinePoly[] = { panel.getX(), panel.getX() - 20,
												panelDest.getX() + panelDest.getWidth() + 36,
												panelDest.getX() + panelDest.getWidth() + 26 };
										int yLinePoly[] = { panel.getY() + panel.getHeight() / 2,
												panel.getY() + panel.getHeight() / 2,
												panelDest.getY() + panelDest.getHeight() / 2,
												panelDest.getY() + panelDest.getHeight() / 2 };
										g2d.drawPolyline(xLinePoly, yLinePoly, xLinePoly.length);

										// Arrow Point
										int xTrianglePoly[] = { panelDest.getX() + panelDest.getWidth() + 5,
												panelDest.getX() + panelDest.getWidth() + 10 + 5,
												panelDest.getX() + panelDest.getWidth() + 10 + 5 };
										int yTrianglePoly[] = { panelDest.getY() + panelDest.getHeight() / 2,
												panelDest.getY() + panelDest.getHeight() / 2 - 5,
												panelDest.getY() + panelDest.getHeight() / 2 + 5 };
										Polygon triangle = new Polygon(xTrianglePoly, yTrianglePoly,
												xTrianglePoly.length);
										g2d.setStroke(new BasicStroke(5));
										g2d.drawPolygon(triangle);
										g2d.setColor(new Color(255, 255, 255));
										g2d.fillPolygon(triangle);
										g2d.setColor(new Color(0, 0, 0));
									}
								}
							}
						}
					}
				}
			}
		}
		g2d.setStroke(new BasicStroke(1));
	}

	/**
	 * Gets a list of all the components in the view.
	 * 
	 * @param container the container we are getting the components from.
	 * @return a list of all the components in the view.
	 */
	public static List<Component> getAllComponents(final Container c) {
		Component[] comps = c.getComponents();
		List<Component> compList = new ArrayList<Component>();
		for (Component comp : comps) {
			compList.add(comp);
			if (comp instanceof Container)
				compList.addAll(getAllComponents((Container) comp));
		}
		return compList;
	}

	/**
	 * Constructor for the GUI view.
	 */
	public GUI() {
		frame = new JFrame("NRDS UML EDITOR");
		setLayout(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setPreferredSize(new Dimension(1280, 720));

		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) {
			System.out.println("Look and feel not set on this system.");
		}

		menuBar = new JMenuBar();

		/*******************************************************************************************************************
		 * JMENUBAR FILE
		 ********************************************************************************************************************/

		JMenu saveAndLoadMenu = new JMenu("File");
		menuBar.add(saveAndLoadMenu);

		JMenuItem saveItem = new JMenuItem("Save");
		saveAndLoadMenu.add(saveItem);

		JMenuItem loadItem = new JMenuItem("Load");
		saveAndLoadMenu.add(loadItem);

		JMenuItem imgItem = new JMenuItem("Export as Image");
		saveAndLoadMenu.add(imgItem);

		JMenu classMenu = new JMenu("Class");
		menuBar.add(classMenu);

		JMenuItem addClassItem = new JMenuItem("Add Class");
		classMenu.add(addClassItem);

		JMenuItem deleteClassItem = new JMenuItem("Delete Class");
		classMenu.add(deleteClassItem);

		JMenuItem editClassNameItem = new JMenuItem("Edit Class Name");
		classMenu.add(editClassNameItem);

		/*******************************************************************************************************************
		 * JMENUBAR FIELDS
		 ********************************************************************************************************************/

		JMenu fieldMenu = new JMenu("Field");
		menuBar.add(fieldMenu);

		JMenuItem addFieldItem = new JMenuItem("Add Field");
		fieldMenu.add(addFieldItem);

		JMenuItem deleteFieldItem = new JMenuItem("Delete Field");
		fieldMenu.add(deleteFieldItem);

		JMenuItem editFieldNameItem = new JMenuItem("Edit Field Name");
		fieldMenu.add(editFieldNameItem);

		JMenuItem editFieldTypeItem = new JMenuItem("Edit Field Type");
		fieldMenu.add(editFieldTypeItem);

		/*******************************************************************************************************************
		 * JMENUBAR METHODS
		 ********************************************************************************************************************/

		JMenu methodMenu = new JMenu("Method");
		menuBar.add(methodMenu);

		JMenuItem addMethodItem = new JMenuItem("Add Method");
		methodMenu.add(addMethodItem);

		JMenuItem deleteMethodItem = new JMenuItem("Delete Method");
		methodMenu.add(deleteMethodItem);

		JMenuItem editMethodNameItem = new JMenuItem("Edit Method Name");
		methodMenu.add(editMethodNameItem);

		JMenuItem editMethodParametersItem = new JMenuItem("Edit Method Parameters");
		methodMenu.add(editMethodParametersItem);

		JMenuItem editMethodTypeItem = new JMenuItem("Edit Method Return Type");
		methodMenu.add(editMethodTypeItem);

		/*******************************************************************************************************************
		 * JMENUBAR RELATIONSHIPS
		 ********************************************************************************************************************/

		JMenu relationshipMenu = new JMenu("Relationship");
		menuBar.add(relationshipMenu);

		JMenuItem addRelationshipItem = new JMenuItem("Add Relationship");
		relationshipMenu.add(addRelationshipItem);

		JMenuItem deleteRelationshipItem = new JMenuItem("Delete Relationship");
		relationshipMenu.add(deleteRelationshipItem);

		JMenuItem editRelationshipDestinationItem = new JMenuItem("Edit Relationship Destination");
		relationshipMenu.add(editRelationshipDestinationItem);

		JMenuItem editRelationshipTypeItem = new JMenuItem("Edit Relationship Type");
		relationshipMenu.add(editRelationshipTypeItem);

		/*******************************************************************************************************************
		 * JMENUBAR SAVE & UNDO & REDO
		 ********************************************************************************************************************/

		JMenu undoAndRedoMenu = new JMenu("Edit");
		menuBar.add(undoAndRedoMenu);

		JMenuItem undoItem = new JMenuItem("Undo");
		undoAndRedoMenu.add(undoItem);

		JMenuItem redoItem = new JMenuItem("Redo");
		undoAndRedoMenu.add(redoItem);

		frame.setJMenuBar(menuBar);
		frame.add(this);
		frame.pack();
		frame.setVisible(true);
	}

	/**
	 * @param listener         the listener to be added.
	 * @param menuPosition     where the menu is in the ribbon.
	 * @param menuItemPosition where the item is in the menu.
	 */
	public void addActionListener(ActionListener listener, int menuPosition, int menuItemPosition) {

		menuBar.getMenu(menuPosition).getItem(menuItemPosition).addActionListener(listener);
	}
}
