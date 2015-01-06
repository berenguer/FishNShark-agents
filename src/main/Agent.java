package main;

public abstract class Agent {
    
    public int posX;
    
    public int posY;
        
    /**
     * How much turns have to be waited for making a child.
     * It is set once. 
     */
    public int birthDelay;
    
    /**
     * Number of turns remaining before the agent make a child.
     * The value has to be decremented until it reachs 0.
     * Then, the variable get back to the value of the birthDelay.
     */
    public int birthRemainingTurns;
    
    /**
     * Represents the number of turns alive in the game.
     * Age has to be <b>incremented</b> every turns
     */
    public int age = 0;

    /**
     * Environnement contains every agents.
     * Usefull for analyze neighbours of this agent in the grid with other agents.
     */
    public Environnement env;

    public Agent(int posX, int posY, int birthDelay, Environnement env) {
        super();
        this.posX = posX;
        this.posY = posY;
        this.birthDelay = birthDelay;
        this.env = env;
    }

    public Environnement getEnv() {
        return env;
    }

    public void setEnv(Environnement env) {
        this.env = env;
    }

    public int getPosX() {
        return posX;
    }

    public void setPosX(int posX) {
        this.posX = posX;
    }

    public int getPosY() {
        return posY;
    }

    public void setPosY(int posY) {
        this.posY = posY;
    }
    
    public abstract void action();
    
    @Override
    public abstract String toString();

}
