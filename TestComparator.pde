import java.util.Comparator;

import processing.core.PShape;
import remixlab.dandelion.core.Camera;

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
