import javax.swing.JOptionPane;

// Anthony Phillips
// Tells if a given coordinate is within a circle
// of radius 10 centered at the origin

public class Circle {

  public static void main(String[] args){
    // I actually used the CartesianCoordinate class I made
    // previously for the "Distance" problem in project 2
    CartesianCoordinate originCoordinate = new CartesianCoordinate(0, 0);
    double x;
    double y;
    String input = JOptionPane.showInputDialog("Enter a point with two coordinates e.g. '4 5':");

    String[] inputArray;
    try {
      inputArray = input.split("\\s+");
    } catch (Exception e){
      System.out.println("Input error.");
      return;
    }

    try {
      x = Double.parseDouble(inputArray[0]);
    } catch (Exception e){
      System.out.println("Unable to parse coordinates.");
      return;
    }

    try {
      y = Double.parseDouble(inputArray[1]);
    } catch (Exception e){
      System.out.println("Unable to parse coordinates.");
      return;
    }

    CartesianCoordinate userCoordinate = new CartesianCoordinate(x, y);

    String message;
    if (userCoordinate.distance(originCoordinate) > 10) {
      message = String.format("The point %s is outside the circle.\n", userCoordinate.toString());
    } else {
      message = String.format("The point %s is inside the circle.\n", userCoordinate.toString());
    }

    JOptionPane.showMessageDialog(null, message);
  }

  // Didn't really need a class, but thought it'd be a good refresher
  private static class CartesianCoordinate {

    // No mutators
    // if a component needs changed, make a new coordinate object
    private final double X;
    public double getX(){ return X; }

    private final double Y;
    public double getY(){ return Y; }

    public CartesianCoordinate(double X, double Y){
      this.X = X;
      this.Y = Y;
    }

    // Added toString for easy string output
    public String toString() {
      return String.format("(%f, %f)", this.getX(), this.getY());
    }

    // Could make a static method in addition to, or instead of this method
    public double distance(CartesianCoordinate coord) {
      double xLeg = Math.abs(coord.getX() - this.getX()); // Δx
      double yLeg = Math.abs(coord.getY() - this.getY()); // Δy
      return Math.pow((Math.pow(xLeg, 2)+Math.pow(yLeg, 2)), 0.5); // hypotenuse
    }

  }
}
