public class NBody {

    private static int num;
    private static double radius;
    public static double readRadius(String s){
        In in = new In(s);
        num = in.readInt();
        double radius = in.readDouble();
        return radius;
    }
    public static Body[] readBodies(String s){
        In in = new In(s);
        int num = in.readInt();
        double radius = in.readDouble();
        Body[] bodylist = new Body[num];
        for (int i=0; i<num; i++){
            double xxPos = in.readDouble();
            double yyPos = in.readDouble();
            double xxVel = in.readDouble();
            double yyVel = in.readDouble();
            double mass = in.readDouble();
            String imgFileName = in.readString();
            bodylist[i] = new Body(xxPos,yyPos,xxVel,yyVel,mass,imgFileName);
        }
        return bodylist;
    }
    public static void main(String[] args) {
        //Input
        double T = Double.parseDouble(args[0]);
        double dt = Double.parseDouble(args[1]);
        String filename = args[2];
        //Read the universe radius 
        double r = readRadius(filename);
        // Set scale
        StdDraw.setScale(-r,r);
        //Draw background
        StdDraw.picture(0,0,"images/starfield.jpg");

        Body[] bodies = readBodies(filename);

        //Draw Planets
       
        for (int i = 0; i<num;i++){

            bodies[i].draw();
        }

        //Enable double buffering
        StdDraw.enableDoubleBuffering();

        //Showing it on screen
        StdDraw.show();
        
        //Create a variable that represents time and set the loop
        

        for (double time=0; time<=T; time=time+dt){

            // Create xForces and yForces
            double[] xForces = new double[num];
            double[] yForces = new double[num];
            
            //Store value to xForces and yForces array
            for (int j = 0; j<num;j++){
                
                
                xForces[j] = bodies[j].calcNetForceExertedByX(bodies);
                
                yForces[j] = bodies[j].calcNetForceExertedByY(bodies);
                
                //Update each body
                bodies[j].update(dt,xForces[j],yForces[j]);
                
            }


  
            //Draw background
            StdDraw.picture(0,0,"images/starfield.jpg");

            //Draw all planets
            for (int i = 0; i<num;i++){

                bodies[i].draw();
            }

            StdDraw.enableDoubleBuffering();
            StdDraw.show();
            StdDraw.pause(10);
        
       
        }
        StdOut.printf("%d\n", bodies.length);
        StdOut.printf("%.2e\n", radius);
        for (int i = 0; i < bodies.length; i++) {
        StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
                  bodies[i].xxPos, bodies[i].yyPos, bodies[i].xxVel,
                  bodies[i].yyVel, bodies[i].mass, bodies[i].imgFileName);   
}
    }
}