package main;

public abstract class Agent {
    
    public int posX;
    
    public int posY;
        
    public int birthDelay;
    
    public int age = 0;

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

}
