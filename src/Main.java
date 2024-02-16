
public class Main {
  public static void main(String[ ] args) {
        //test

      Bataille testBataille;
      testBataille = new Bataille();

      testBataille.initGrilleOrdi();

      for (int[] ligne : testBataille.grilleOrdi)
      {
          System.out.print("\n");
          for (int collone : ligne) {
            System.out.print("  " + collone);
          }
      }
  }
}
