package Ants;

import framework.SimulationBody;
import framework.SimulationFrame;
import org.dyn4j.geometry.Geometry;
import org.dyn4j.geometry.MassType;
import org.dyn4j.geometry.Vector2;
import org.dyn4j.world.World;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.Ellipse2D;
import java.awt.image.BufferStrategy;
import java.util.ArrayList;

/**
 * Beginning of an echo-based version of ant-like agents.  I'm going to start with a simple shape, e.g. a circle,
 * and build out from there.  Obviously, all blueprinted off of Holland's base echo model.
 *
 * Created:  25 April 2022
 *
 */

public class AntWorld extends SimulationFrame {
    public ArrayList<Ant> antColonies = new ArrayList<>();
    public ArrayList<SimulationBody> toRemove = new ArrayList<>();
    public ArrayList<Resource> resources = new ArrayList<Resource>();

    // Init variables, also used for testing
    int numAnts = 100;
    int numResources = 100;
    int scale;

    /**
     * Constructor.
     * <p>
     * By default creates a 800x600 canvas.
     *
     */
    public AntWorld() {
        super("Ant World", 20);
        this.scale = 20;
        // Add ants here
        for(int i = 0; i < numAnts; i++) {
            Ant testAnt = new Ant(world);
            world.addBody(testAnt);
            antColonies.add(testAnt);
        }

        // Let's add a resource or two
        for(int i = 0; i < numResources; i++) {
            Resource res = new Resource();
            resources.add(res);
        }
    }

    @Override
    protected void initializeWorld() {
        this.world.setGravity(World.ZERO_GRAVITY);
        int scale = 20; // this doesn't appear to be something you can pull from the world at this point.
        addWorldObjects(scale);
    }

    private void addWorldObjects(int scale) {
        // add bounding shapes to the world, these are the walls
        SimulationBody right = new SimulationBody();
        right.setColor(Color.black);
        right.addFixture(Geometry.createRectangle(0.2, 40+scale));
        right.setMass(MassType.INFINITE);
        right.translate(16.65+scale*1.16, 7);
        right.setUserData(new String("Obstacle"));
        this.world.addBody(right);

        SimulationBody left = new SimulationBody();
        left.setColor(Color.black);
        left.addFixture(Geometry.createRectangle(0.2, 40+scale));
        left.setMass(MassType.INFINITE);
        left.translate(-16.65-scale*1.16, 7);
        left.setUserData(new String("Obstacle"));
        this.world.addBody(left);

        SimulationBody top = new SimulationBody();
        top.setColor(Color.black);
        top.addFixture(Geometry.createRectangle(40+scale*2, 0.2));
        top.setMass(MassType.INFINITE);
        top.translate(0, 8.25+scale*0.58);
        top.setUserData(new String("Obstacle"));
        this.world.addBody(top);

        SimulationBody bottom = new SimulationBody();
        bottom.setColor(Color.black);
        bottom.addFixture(Geometry.createRectangle(40+scale*2, 0.2));
        bottom.setMass(MassType.INFINITE);
        bottom.translate(0, -8.25-scale*0.58);
        bottom.setUserData(new String("Obstacle"));
        this.world.addBody(bottom);

        // Light (a polygon)
        SimulationBody newLight = new SimulationBody();
        newLight.setColor(Color.yellow);
        newLight.addFixture(Geometry.createUnitCirclePolygon(5, 0.5));
        newLight.translate(new Vector2(-8.0-scale*.5, -5-scale*.5));
        newLight.setMass(MassType.INFINITE);
        this.world.addBody(newLight);

        // Extra light
        SimulationBody extraLight = new SimulationBody();
        extraLight.setColor(Color.yellow);
        extraLight.addFixture(Geometry.createUnitCirclePolygon(5, 0.5));
        extraLight.translate(new Vector2(8.0+scale*.5, 5+scale*0.5));
        extraLight.setMass(MassType.INFINITE);
        extraLight.setUserData(new String("Light"));
        this.world.addBody(extraLight);

        // Obstacle
        SimulationBody polygon = new SimulationBody();
        polygon.setColor(Color.CYAN);
        polygon.addFixture(Geometry.createUnitCirclePolygon(5, 0.5));
        polygon.translate(new Vector2(-2.0, 0));
        polygon.setMass(MassType.INFINITE);
        polygon.setUserData(new String("UNKNOWN"));
        this.world.addBody(polygon);
    }

    private void start() {
        // initialize the last update time
        this.last = System.nanoTime();
        // don't allow AWT to paint the canvas since we are
        this.canvas.setIgnoreRepaint(true);
        // enable double buffering (the JFrame has to be
        // visible before this can be done)
        this.canvas.createBufferStrategy(2);
        // run a separate thread to do active rendering
        // because we don't want to do it on the EDT
        Thread thread = new Thread() {
            public void run() {
                // perform an infinite loop stopped
                // render as fast as possible
                while (!isStopped()) {
                    gameLoop();
                    // you could add a Thread.yield(); or
                    // Thread.sleep(long) here to give the
                    // CPU some breathing room
                    try {
                        Thread.sleep(5);
                    } catch (InterruptedException e) {}
                }
            }
        };
        // set the game loop thread to a daemon thread so that
        // it cannot stop the JVM from exiting
        thread.setDaemon(true);
        // start the game loop
        thread.start();
    }

    public void run() {
        // set the look and feel to the system look and feel
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }

        // show it
        this.setVisible(true);

        // start it
        this.start();
    }

    private void gameLoop() {
        // get the graphics object to render to
        Graphics2D g = (Graphics2D)this.canvas.getBufferStrategy().getDrawGraphics();

        // by default, set (0, 0) to be the center of the screen with the positive x axis
        // pointing right and the positive y axis pointing up
        this.transform(g);

        // reset the view
        this.clear(g);

        // get the current time
        long time = System.nanoTime();
        // get the elapsed time from the last iteration
        long diff = time - this.last;
        // set the last time
        this.last = time;
        // convert from nanoseconds to seconds
        double elapsedTime = (double)diff / NANO_TO_BASE;
//    	System.out.println(elapsedTime);

        // render anything about the simulation (will render the World objects)
        AffineTransform tx = g.getTransform();
        g.translate(this.camera.offsetX, this.camera.offsetY);
        this.render(g, elapsedTime);
        g.setTransform(tx);

        // update the World
        if (!this.paused.isActive()) {
            // Now that we've moved all of this into AntWorld, let's see if we can
            // remove objects without crashing the whole simulation
            // Remove from the colonies array
            for(SimulationBody dead: toRemove) {
                antColonies.remove(dead);
            }
            // Right now, this is the only way I could get the remove body to work...
            // Direct calls to removeBody result in null pointer exceptions in the ConstraintGraph class
            // that I haven't been able to avoid.
            this.world.removeAllBodies();
            addWorldObjects(scale); // add objects back in because we erase everything
            for(SimulationBody alive: antColonies) {
                Ant temp = new Ant((Ant)alive);
                this.world.addBody(temp);
            }
            this.world.update(elapsedTime);
        } else if (this.step.isActive()) {
            System.out.println("inside else");
            this.world.step(1);
            this.stepNumber++;
            this.step.setActive(false);
        }
        this.handleEvents();

        // dispose of the graphics object
        g.dispose();

        // blit/flip the buffer
        BufferStrategy strategy = this.canvas.getBufferStrategy();
        if (!strategy.contentsLost()) {
            strategy.show();
        }

        // Sync the display on some systems.
        // (on Linux, this fixes event queue problems)
        Toolkit.getDefaultToolkit().sync();
    }

    /* (non-Javadoc)
     * @see org.dyn4j.samples.SimulationFrame#render(java.awt.Graphics2D, double)
     */
    protected void render(Graphics2D g, double elapsedTime) {
        double r = 4.0;

        // Now move vehicles
        for(SimulationBody v : antColonies) {
            if((((Ant)v).isAlive())) {
                ((Ant) v).decide(antColonies, resources);
                v.render(g, elapsedTime);
                ((Ant) v).decLife();
            }
            else {
                toRemove.add(v);
            }
            // Testing area, paint their home
            Vector2 homePoint = ((Ant)v).getHome();
            g.setColor(Color.red);
            g.fill(new Ellipse2D.Double(
                    homePoint.x * scale - r * 0.5,
                    homePoint.y * scale - r * 0.5,
                    r,
                    r));
        }

        // Paint resources as dots on the screen
        for(Resource res : resources) {
            Vector2 point = res.location;
            g.setColor(Color.GREEN);
            g.fill(new Ellipse2D.Double(
                    point.x * scale - r * 0.5,
                    point.y * scale - r * 0.5,
                    r,
                    r));
        }
        super.render(g, elapsedTime);
    }

    /**
     * Entry point for the example application.
     * @param args command line arguments
     */
    public static void main(String[] args) {
        AntWorld simulation = new AntWorld();
        simulation.run();
    }
}
