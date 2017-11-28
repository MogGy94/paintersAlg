import processing.core.*; 
import processing.data.*; 
import processing.event.*; 
import processing.opengl.*; 

import java.util.ArrayList; 
import java.util.Collections; 
import java.util.List; 
import java.util.Random; 
import processing.core.PApplet; 
import processing.core.PShape; 
import processing.core.PVector; 
import remixlab.proscene.Scene; 
import java.util.Comparator; 
import processing.core.PShape; 
import remixlab.dandelion.core.Camera; 

import java.util.HashMap; 
import java.util.ArrayList; 
import java.io.File; 
import java.io.BufferedReader; 
import java.io.PrintWriter; 
import java.io.InputStream; 
import java.io.OutputStream; 
import java.io.IOException; 

public class paintersAlg extends PApplet {












  private Random random;
  private Scene scene;

  private CompareZ CX;
  private CompareZ CY;
  private CompareZ CZ;

  private List<PShape> shapes;

  private Pyramid[] pyramids;

  private boolean enableZBuffer;
  private boolean shuffle;

  private boolean sortX;
  private boolean sortY;
  private boolean sortZ;

  @Override
  public void settings() {
    size(640, 480, P3D);
  }

  @Override
  public void setup() {
    random = new Random();
    scene = new Scene(this);

    CX = new CompareZ(scene.camera());
    CY = new CompareZ(scene.camera());
    CZ = new CompareZ(scene.camera());

    pyramids = new Pyramid[4*4*4];
    shapes = new ArrayList();

    createPyramids(4,4,4);


    enableZBuffer = true;
    shuffle = false;
    sortX = false;
    sortY = false;
    sortZ = false;
    surface.setTitle("ENABLE_DEPTH_TEST");
    hint(ENABLE_DEPTH_TEST);

    noStroke();
  }


  @Override
  public void draw() {
    background(0);
    if(shuffle)Collections.shuffle(shapes);

    if(sortX)Collections.sort(shapes, CX);
    if(sortY)Collections.sort(shapes, CY);
    if(sortZ)Collections.sort(shapes, CZ);

    for (PShape shape : shapes) {
      shape(shape);

    }
  }

  @Override
  public void keyPressed() {
    if (key=='q' ) {
      shuffle = !shuffle;
      println("shuffle");
      //if(shuffle)Collections.shuffle(shapes);
    }
    if (key=='t' ) {
        sortX = !sortX;
        println("sortX "+sortX);
        //if(sortX)Collections.sort(shapes, CX);

    }
    if (key=='y' ) {
        sortY = !sortY;
        println("sortY "+sortY);
        //if(sortY)Collections.sort(shapes, CY);

    }
    if (key=='u' ) {
        sortZ = !sortZ;
        println("sortZ "+sortZ);
        //if(sortZ)Collections.sort(shapes, CZ);

    }

    if (key==' ' ) {
        enableZBuffer = !enableZBuffer;


        if (enableZBuffer) {
          surface.setTitle("ENABLE_DEPTH_TEST");
          hint(ENABLE_DEPTH_TEST);
        println("Z_ON");
        } else {
          surface.setTitle("DISABLE_DEPTH_TEST");
          hint(DISABLE_DEPTH_TEST);
          println("Z_OFF");
          //hint(DISABLE_DEPTH_SORT);
        }
      }
  }

  public void createPyramids(int numx,int numy,int numz){
    Pyramid t ;
    for (int i = 0; i < numx; i++) {
      for (int j = 0; j < numy; j++) {
        for (int k = 0; k < numz; k++) {

          int x = 20 * (i - numx / 2);
          int y = 20 * (j - numy / 2);
          int z = 20 * (k - numz / 2);
          t = new Pyramid(x, y, z);
          pyramids[i+j+k]= t ;
        }
      }
    }
  }





public int bub(float a ,float b ){
  if (a>b){
    return 1;
  }
    else if (a==b){
      return 0;
    }
  return -1;
}


public class CompareX implements Comparator<PShape> {

  private Camera camera;

  public CompareX(Camera camera) {
    super();
    this.camera = camera;
  }

  public Camera getCamera() {
    return camera;
  }

  public void setCamera(Camera camera) {
    this.camera = camera;
  }

  @Override
  public int compare(PShape o1, PShape o2) {
    PVector a = o1.getVertex(0);
    PVector b = o2.getVertex(0);

    return bub(a.x,b.x);
  }
}


  public class CompareY implements Comparator<PShape> {

    private Camera camera;

    public CompareY(Camera camera) {
      super();
      this.camera = camera;
    }

    public Camera getCamera() {
      return camera;
    }

    public void setCamera(Camera camera) {
      this.camera = camera;
    }

    @Override
    public int compare(PShape o1, PShape o2) {
      PVector a = o1.getVertex(0);
      PVector b = o2.getVertex(0);
      println(a.y+" "+b.y);
      return bub(a.y,b.y);

    }
}
public class CompareZ implements Comparator<PShape> {

  private Camera camera;

  public CompareZ(Camera camera) {
    super();
    this.camera = camera;
  }

  public Camera getCamera() {
    return camera;
  }

  public void setCamera(Camera camera) {
    this.camera = camera;
  }

  @Override
  public int compare(PShape o1, PShape o2) {
    PVector a = o1.getVertex(0);
    PVector b = o2.getVertex(0);

    return bub(a.z,b.z);

  }
}
 class Pyramid{
  PVector p1 ;
  PVector p2 ;
  PVector p3 ;
  PVector p4 ;

  PShape face1;
  PShape face2;
  PShape face3;
  PShape face4;


    public  Pyramid (int x, int y, int z) {
       p1 = new PVector(x + random.nextInt(20), y + random.nextInt(10), z);
       p2 = new PVector(x + 10 + random.nextInt(10), y + 10 + random.nextInt(10), z);
       p3 = new PVector(x + random.nextInt(10), y + 10 + random.nextInt(10), z);
       p4 = new PVector(x + random.nextInt(20), y + random.nextInt(20), z + 1 + random.nextInt(10));

      face1 = createFace(p1,p2,p3);
      face2 = createFace(p1,p2,p4);
      face3 = createFace(p1,p3,p4);
      face4 = createFace(p2,p4,p3);



      //face5 =

      }
    public PShape createFace(PVector k1,PVector k2 ,PVector k3){
      PShape face = createShape();
      face.beginShape(TRIANGLE);
      face.vertex(k1.x, k1.y, k1.z);
      face.vertex(k2.x, k2.y, k2.z);
      face.vertex(k3.x, k3.y, k3.z);
      face.endShape();

      shapes.add(face);

      return face;
    }
  }
public class MyQuickSort {

    private int array[];
    private int length;

    public void sort(int[] inputArr) {

        if (inputArr == null || inputArr.length == 0) {
            return;
        }
        this.array = inputArr;
        length = inputArr.length;
        quickSort(0, length - 1);
    }

    private void quickSort(int lowerIndex, int higherIndex) {

        int i = lowerIndex;
        int j = higherIndex;
        // calculate pivot number, I am taking pivot as middle index number
        int pivot = array[lowerIndex+(higherIndex-lowerIndex)/2];
        // Divide into two arrays
        while (i <= j) {
            /**
             * In each iteration, we will identify a number from left side which
             * is greater then the pivot value, and also we will identify a number
             * from right side which is less then the pivot value. Once the search
             * is done, then we exchange both numbers.
             */
            while (array[i] < pivot) {
                i++;
            }
            while (array[j] > pivot) {
                j--;
            }
            if (i <= j) {
                exchangeNumbers(i, j);
                //move index to next position on both sides
                i++;
                j--;
            }
        }
        // call quickSort() method recursively
        if (lowerIndex < j)
            quickSort(lowerIndex, j);
        if (i < higherIndex)
            quickSort(i, higherIndex);
    }

    private void exchangeNumbers(int i, int j) {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }
  }  
  static public void main(String[] passedArgs) {
    String[] appletArgs = new String[] { "paintersAlg" };
    if (passedArgs != null) {
      PApplet.main(concat(appletArgs, passedArgs));
    } else {
      PApplet.main(appletArgs);
    }
  }
}
