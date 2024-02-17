
public class Main {
  public static void main(String[ ] args) {
        //test

      Bataille testBataille;
      testBataille = new Bataille();

      Vue testVue;
      testVue = new Vue();

      testBataille.initGrilleOrdi();

      testVue.afficherGrille(testBataille.grilleOrdi);

  }
}
