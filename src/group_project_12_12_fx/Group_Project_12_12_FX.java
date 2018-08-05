//********************************************************************************
//  Group Assignment       Authors: Kara Keck, Kara Uitenbroek , Pamela Kuepper
//  Date: 8/2/2018
// Class: CPS 315 Object-Oriented Programming I 
//  Chapter 12 pp 12.12
//********************************************************************************
package group_project_12_12_fx;

import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.application.Application;
import javafx.geometry.Point2D;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;

public class Group_Project_12_12_FX extends Application {
    
    @Override    
    public void start(Stage primaryStage) {
        //Create a new instance of STP
        SierpinskiTrianglePane trianglePane = new SierpinskiTrianglePane();  
        BorderPane borderPane = new BorderPane();
        borderPane.setCenter(trianglePane);
        
        Scene scene = new Scene(borderPane,800,800);
        primaryStage.setTitle("Sierpinski Triangle");
        primaryStage.setScene(scene);
        primaryStage.show();
        //Call the draw method
        trianglePane.draw();
    }
    
    public static void main(String[] args) {
        launch(args);
    }
    
    public class SierpinskiTrianglePane extends Pane {
        SierpinskiTrianglePane(){};
        //Impose maximum order of 8
        private int order = 8;
                
        public void draw(){
            Point2D point1 = new Point2D(getWidth()/2,0);
            System.out.println(point1);
            Point2D point2 = new Point2D(0, getHeight());
            Point2D point3 = new Point2D(getWidth(), getHeight());
            //Initial call to begin recursive process
            displayTriangles(order, point1, point2, point3);
        };
    
        private void displayTriangles(int order, Point2D p1, Point2D p2, Point2D p3){
            if(order == 0){
                Polygon triangle = new Polygon();
                triangle.getPoints().addAll(p1.getX(), p1.getY(), p2.getX(), p2.getY(), p3.getX(), p3.getY());
                triangle.setStroke(Color.BLACK);
                triangle.setFill(Color.WHITE);
                this.getChildren().add(triangle);
            }else{
                Point2D p12 = p1.midpoint(p2);
                Point2D p23 = p2.midpoint(p3);
                Point2D p31 = p3.midpoint(p1);
                //draw the three inner triangles by recursively calling self with the decremented numbers.
                displayTriangles(order-1, p1, p12, p31);
                displayTriangles(order-1, p12, p2, p23);
                displayTriangles(order-1, p31, p23, p3);
            }
        }
    
}
}