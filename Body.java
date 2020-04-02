package week1.proj0;

public class Body {
    public double xxPos;
    public double yyPos;
    public double xxVel;
    public double yyVel;
    public double mass;
    public String imgFileName;

    public Body(double xP, double yP, double xV, double yV, double m, String img) {
        xxPos = xP;
        yyPos = yP;
        xxVel = xV;
        yyVel = yV;
        mass = m;
        imgFileName = img;
    }

    public Body(Body b) {
        xxPos = b.xxPos;
        yyPos = b.yyPos;
        xxVel = b.xxVel;
        yyVel = b.yyVel;
        mass = b.mass;
        imgFileName = b.imgFileName;
    }

    public double calcDistance(Body b) {
        double distance = Math.sqrt(Math.pow(this.xxPos - b.xxPos, 2) + Math.pow(this.yyPos - b.yyPos, 2));
        return distance;
    }

    public double calcForceExertedBy(Body b) {
        double force = (6.67 * Math.pow(10, -11) * this.mass * b.mass) / Math.pow(calcDistance(b), 2);
        return force;
    }

    public double calcForceExertedByX(Body b) {
        double xForce = this.calcForceExertedBy(b) * ((b.xxPos - this.xxPos) / this.calcDistance(b));
        return xForce;
    }

    public double calcForceExertedByY(Body b) {
        double yForce = this.calcForceExertedBy(b) * ((b.yyPos - this.yyPos) / this.calcDistance(b));
        return yForce;
    }

    public double calcNetForceExertedByX(Body[] allBodies) {
        double xNetForce = 0.0;

        for (int i = 0; i < allBodies.length; ++i) {
            if (!this.equals(allBodies[i])) {
                xNetForce += this.calcForceExertedByX(allBodies[i]);
            }
        }

        return xNetForce;
    }

    public double calcNetForceExertedByY(Body[] allBodies) {
        double yNetForce = 0.0;

        for (int i = 0; i < allBodies.length; ++i) {
            if (!this.equals(allBodies[i])) {
                yNetForce += this.calcForceExertedByY(allBodies[i]);
            }
        }

        return yNetForce;
    }

    public void update(double dt, double fX, double fY) {
        double xAccel = fX / this.mass;
        double yAccel = fY / this.mass;

        this.xxVel = this.xxVel + xAccel * dt;
        this.yyVel = this.yyVel + yAccel * dt;

        this.xxPos = this.xxPos + this.xxVel * dt;
        this.yyPos = this.yyPos + this.yyVel * dt;
    }

    public void draw() {
        StdDraw.picture(xxPos, yyPos, "images/" + imgFileName);
    }
}
