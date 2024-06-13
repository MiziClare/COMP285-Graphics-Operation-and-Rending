<p align="center">
  <a href="https://www.liverpool.ac.uk/" target="blank">
    <img src="Liverpool_logo.png" alt="Logo" width="156" height="156">
  </a>
 <h1 align="center" style="font-weight: 600">COMP285    Computer Aided Software Development</h1>
 <h3 align="center" backgroundcolor="red">⭐ If the code has helped you, please stars this, thank you very much!</h3>

# Graphics-Operation-and-Rending

## Project Overview

This Java project allows for the creation, manipulation, and visualization of various geometric shapes such as circles, rectangles, and regular polygons. The project includes functionality for rendering these shapes on a canvas, rotating and scaling them, and detecting collisions between them.


## Classes Overview

### Main Components

- **CanvasFrame**: A JFrame that sets up the main application window and contains a `PanelCanvas`.
- **PanelCanvas**: A JPanel that holds and renders all the shapes.
- **Shape**: An abstract class implementing common functionality for all shapes.
- **Circle**: A class representing a circle.
- **Rectangle**: A class representing a rectangle.
- **RegularPolygon**: A class representing a regular polygon (e.g., triangle, pentagon).

### Interfaces

- **IShape**: Defines the methods that any shape class must implement.

### Utility Classes

- **Point**: Represents a point in 2D space with `x` and `y` coordinates.

### Shape Methods

- `getArea()`: Calculates the area of the shape.
- `getLowerLeftPoint()`: Returns the lower-left point of the bounding rectangle of the shape.
- `getUpperRightPoint()`: Returns the upper-right point of the bounding rectangle of the shape.
- `doesCollide(IShape shape)`: Checks if this shape touches or overlaps another shape.
- `render(Graphics g)`: Draws the shape using the AWT graphics class.
- `setRotation(Point origin, double angle)`: Sets a rotation on the object from the given origin.
- `setScale(Point origin, double scaleX, double scaleY)`: Scales the shape relative to the origin given.


## Must See Before Read My code
* Operating System: Win11
* Programming Languages: Java
* All codes are related to labs and assignments and all codes were uploaded after the assignment deadline, all personal information is taken from the University's public website.

⚠Please adhere to the University's Academic Integrity Policy and I accept no responsibility for suspected academic misconduct of any kind.

 ## Module Specification
* Year: 2024
* Lectuer: Sebastian Coope

 ## Module Results
* Assignment: 90/100 (Missing a couple of graphical threshold tests)
