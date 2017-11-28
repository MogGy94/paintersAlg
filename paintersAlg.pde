import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import processing.core.PApplet;
import processing.core.PShape;
import processing.core.PVector;
import remixlab.proscene.Scene;


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

  void createPyramids(int numx,int numy,int numz){
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
