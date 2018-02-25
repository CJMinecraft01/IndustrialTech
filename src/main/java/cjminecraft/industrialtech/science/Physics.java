package cjminecraft.industrialtech.science;

/**
 * Assumes all variables are in standard index (SI) units
 */
public class Physics {

    public static long getWaveSpeed(long wavelength, long frequency) {
        return wavelength * frequency;
    }

    public static double getWaveSpeed(double wavelength, double frequency) {
        return wavelength * frequency;
    }

    public static long getWaveLength(long waveSpeed, long frequency) {
        return waveSpeed / frequency;
    }

    public static double getWaveLength(double waveSpeed, double frequency) {
        return waveSpeed / frequency;
    }

    public static long getFrequency(long waveSpeed, long wavelength) {
        return waveSpeed / wavelength;
    }

    public static double getFrequency(double waveSpeed, double wavelength) {
        return waveSpeed / wavelength;
    }

    public static long getFrequency(long photonEnergy) {
        return (long) (photonEnergy / Constants.PLANKS_CONSTANT);
    }

    public static double getFrequency(double photonEnergy) {
        return photonEnergy / Constants.PLANKS_CONSTANT;
    }

    public static double getPhotonEnergy(long frequency) {
        return frequency * Constants.PLANKS_CONSTANT;
    }

    public static double getPhotonEnergy(double frequency) {
        return frequency * Constants.PLANKS_CONSTANT;
    }

}
