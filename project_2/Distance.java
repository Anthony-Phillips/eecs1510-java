import java.util.Scanner;

// Anthony Phillips
// Gives distance between two sets cartesian coordinates

public class Distance {

  public static void main(String[] args){
    double x1 = promptDoubleInput("Please enter a value for x1: ");
    double y1 = promptDoubleInput("Please enter a value for y1: ");

    double x2 = promptDoubleInput("Please enter a value for x2: ");
    double y2 = promptDoubleInput("Please enter a value for y2: ");

    CartesianCoordinate coord1 = new CartesianCoordinate(x1, y1);
    CartesianCoordinate coord2 = new CartesianCoordinate(x2, y2);

    System.out.println("The distance is: " + coord1.distance(coord2));
  }

  // Cut down redundancy
  private static double promptDoubleInput(String prompt){
    Scanner stdIn = new Scanner(System.in);
    do {
      System.out.print(prompt);
      if (stdIn.hasNextDouble()){
        return stdIn.nextDouble();
      }
    } while (true);
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

    // Could make a static method in addition to, or instead of this method
    public double distance(CartesianCoordinate coord){
      double xLeg = Math.abs(coord.getX() - this.getX()); // Δx
      double yLeg = Math.abs(coord.getY() - this.getY()); // Δy
      return Math.pow((Math.pow(xLeg, 2)+Math.pow(yLeg, 2)), 0.5); // hypotenuse
    }

  }

}
