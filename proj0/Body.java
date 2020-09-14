public class Body {
    private double G = 6.67e-11;
    public double xxPos;
    public double yyPos;
    public double xxVel;
    public double yyVel;
    public double mass;
    public String imgFileName;

    public Body(double xP, double yP, double xV, double yV, double m, String img){
        this.xxPos = xP;
        this.yyPos = yP;
        this.xxVel = xV;
        this.yyVel = yV;
        this.mass = m;
        this.imgFileName = img;
    }
    public Body(Body b){
        this.xxPos = b.xxPos;
        this.yyPos = b.yyPos;
        this.xxVel = b.xxVel;
        this.yyVel = b.yyVel;
        this.mass  = b.mass;
        this.imgFileName = b.imgFileName;
    }
    public double calcDistance(Body a){
        return Math.sqrt(Math.pow(this.xxPos-a.xxPos,2) + Math.pow(this.yyPos-a.yyPos,2));
    }

    public double calcForceExertedBy(Body a){
        double f;
        f = (G*this.mass*a.mass)/Math.pow(this.calcDistance(a),2) ;
        return f;


    }

    public double calcForceExertedByX(Body a){
        if (this.xxPos == a.xxPos){
            return 0;
        }
        else {
            return this.calcForceExertedBy(a)*(a.xxPos-this.xxPos)/this.calcDistance(a);
        }    
        
    }

    public double calcForceExertedByY(Body a){

        if (this.yyPos == a.yyPos){
            return 0;
        }
        else {
            return this.calcForceExertedBy(a)*(a.yyPos-this.yyPos)/this.calcDistance(a);
        }
        

    }

    public double calcNetForceExertedByX(Body [] array){
        double sumNetForceX;
        sumNetForceX = 0;
        for (Body planet: array){
            if (this.equals(planet)){
                sumNetForceX += 0;
            } else {
                sumNetForceX += this.calcForceExertedByX(planet);
            }
        
        }
        return sumNetForceX;
    }

    public double calcNetForceExertedByY(Body [] array){
        double sumNetForceY;
        sumNetForceY = 0; 
        for (Body planet: array){

            if (this.equals(planet)){
                sumNetForceY += 0;
            } else {
                sumNetForceY += this.calcForceExertedByY(planet);
            }
        
        }
        return sumNetForceY;
    }

    public void update(double dt, double xForce, double yForce){
        double aX;
        double aY;
        aX = xForce/this.mass;
        aY = yForce/this.mass;
        this.xxVel += dt*aX;
        this.yyVel += dt*aY; 
        this.xxPos += dt*this.xxVel;
        this.yyPos += dt*this.yyVel;
    }

    public void draw(){
        StdDraw.picture(this.xxPos, this.yyPos, "images/"+ this.imgFileName);
    }



}


