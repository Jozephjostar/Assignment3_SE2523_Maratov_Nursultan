public class Main {

    public static void main(String[] args) {
        if (args.length > 0 && "--demo".equals(args[0])) {
            runDemo();
        } else {
            runDemo();
        }
    }

    public static void runDemo() {
        int passed = 0;
        int total = 5;

        // T1: Circle with VectorRenderer
        Circle c1 = new Circle("c-1", new VectorRenderer(), 2);
        String actualT1 = c1.execute();
        String expectedT1 = "VECTOR circle radius=2";
        if (expectedT1.equals(actualT1)) {
            passed++;
            System.out.println("T1 PASS | Circle + VectorRenderer | result=" + actualT1);
        } else {
            System.out.println("T1 FAIL | Circle + VectorRenderer | expected=" + expectedT1 + " | actual=" + actualT1);
        }

        // T2: Circle with RasterRenderer
        Circle c2 = new Circle("c-2", new RasterRenderer(), 2);
        String actualT2 = c2.execute();
        String expectedT2 = "RASTER circle radius=2";
        if (expectedT2.equals(actualT2)) {
            passed++;
            System.out.println("T2 PASS | Circle + RasterRenderer | result=" + actualT2);
        } else {
            System.out.println("T2 FAIL | Circle + RasterRenderer | expected=" + expectedT2 + " | actual=" + actualT2);
        }

        // T3: Square with VectorRenderer
        Square s1 = new Square("s-1", new VectorRenderer(), 3);
        String actualT3 = s1.execute();
        String expectedT3 = "VECTOR square side=3";
        if (expectedT3.equals(actualT3)) {
            passed++;
            System.out.println("T3 PASS | Square + VectorRenderer | result=" + actualT3);
        } else {
            System.out.println("T3 FAIL | Square + VectorRenderer | expected=" + expectedT3 + " | actual=" + actualT3);
        }

        // T4: Square with RasterRenderer
        Square s2 = new Square("s-2", new RasterRenderer(), 3);
        String actualT4 = s2.execute();
        String expectedT4 = "RASTER square side=3";
        if (expectedT4.equals(actualT4)) {
            passed++;
            System.out.println("T4 PASS | Square + RasterRenderer | result=" + actualT4);
        } else {
            System.out.println("T4 FAIL | Square + RasterRenderer | expected=" + expectedT4 + " | actual=" + actualT4);
        }

        // T5: Runtime switch on a single Circle object
        Circle switchCircle = new Circle("c-switch", new VectorRenderer(), 2);
        Circle refBefore = switchCircle;
        String idBefore = switchCircle.getId();
        int radiusBefore = switchCircle.getRadius();
        String resultBefore = switchCircle.execute();

        // Switch implementation at runtime
        switchCircle.setImplementation(new RasterRenderer());

        Circle refAfter = switchCircle;
        String idAfter = switchCircle.getId();
        int radiusAfter = switchCircle.getRadius();
        String resultAfter = switchCircle.execute();

        boolean sameObject = (refBefore == refAfter);
        boolean stateUnchanged = idBefore.equals(idAfter) && (radiusBefore == radiusAfter);
        boolean resultsValid = "VECTOR circle radius=2".equals(resultBefore)
                && "RASTER circle radius=2".equals(resultAfter);

        if (sameObject && stateUnchanged && resultsValid) {
            passed++;
            System.out.println("T5 PASS | sameObject=" + sameObject + " | stateUnchanged=" + stateUnchanged);
            System.out.println("  before=" + resultBefore + " | after=" + resultAfter);
        } else {
            System.out.println("T5 FAIL | sameObject=" + sameObject + " | stateUnchanged=" + stateUnchanged);
            System.out.println("  before=" + resultBefore + " | after=" + resultAfter);
        }

        System.out.println("SUMMARY: " + passed + "/" + total + " PASS");
    }
}
