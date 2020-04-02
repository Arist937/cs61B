package week1.proj0;

public class NBody {
    public static double readRadius (String fileName) {
        In in = new In(fileName);

        int N = in.readInt();
        double radius = in.readDouble();

        return radius;
    }

    public static Body[] readBodies(String fileName) {
        In in = new In(fileName);

        int N = in.readInt();
        Body[] allBodies = new Body[N];
        double radius = in.readDouble();

        for (int i = 0; i < N; ++i) {
            Body b = new Body(in.readDouble(), in.readDouble(), in.readDouble(), in.readDouble(), in.readDouble(), in.readString());
            allBodies[i] = b;
        }

        return allBodies;
    }

    public static void main (String[] args) {
        args = new String[3];

        args[0] = "157788000.0";
        args[1] = "25000.0";
        args[2] = "data/planets.txt";

        double T = Double.parseDouble(args[0]);
        double dt = Double.parseDouble(args[1]);
        String filename = args[2];

        double radius = readRadius(filename);
        Body[] allBodies = readBodies(filename);

        StdDraw.enableDoubleBuffering();
        StdDraw.setScale(-radius, radius);

        int N = allBodies.length;
        for (double time = 0.0; time < T; time += dt) {
            double[] xForces = new double[N];
            double[] yForces = new double[N];

            for (int i = 0; i < allBodies.length; ++i) {
                xForces[i] = allBodies[i].calcNetForceExertedByX(allBodies);
                yForces[i] = allBodies[i].calcNetForceExertedByY(allBodies);
            }

            for (int i = 0; i < allBodies.length; ++i) {
                allBodies[i].update(dt, xForces[i], yForces[i]);
            }

            StdDraw.picture(0, 0, "images/starfield.jpg");

            for (int i = 0; i < allBodies.length; ++i) {
                allBodies[i].draw();
            }

            StdDraw.show();
            StdDraw.pause(10);
        }

        StdOut.printf("%d\n", allBodies.length);
        StdOut.printf("%.2e\n", radius);
        for (int i = 0; i < allBodies.length; i++) {
            StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
                    allBodies[i].xxPos, allBodies[i].yyPos, allBodies[i].xxVel,
                    allBodies[i].yyVel, allBodies[i].mass, allBodies[i].imgFileName);
        }
    }
}
