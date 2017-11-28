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
