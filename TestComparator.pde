import java.util.Comparator;

import processing.core.PShape;
import remixlab.dandelion.core.Camera;

public class TestComparator implements Comparator<PShape> {

  private Camera camera;

  public TestComparator(Camera camera) {
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

    if (a.z>b.z)
      return 1;
    return -1;
  }

}
