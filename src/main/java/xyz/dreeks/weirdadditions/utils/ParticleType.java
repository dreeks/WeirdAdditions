package xyz.dreeks.weirdadditions.utils;

public enum ParticleType {

    PARTICLE_GREEN_THING(0);

    int type;
    ParticleType (int type) {
        this.type = type;
    }

    public int getType() {
        return this.type;
    }

}
