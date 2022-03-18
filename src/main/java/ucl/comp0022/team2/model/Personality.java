package ucl.comp0022.team2.model;

public class Personality {
    private double openness;
    private double agreeableness;
    private double emotional_stability;
    private double conscientiousness;
    private double extraversion;

    public Personality(double openness, double agreeableness, double emotional_stability, double conscientiousness, double extraversion) {
        this.openness = openness;
        this.agreeableness = agreeableness;
        this.emotional_stability = emotional_stability;
        this.conscientiousness = conscientiousness;
        this.extraversion = extraversion;
    }

    public double getOpenness() {
        return openness;
    }

    public void setOpenness(double openness) {
        this.openness = openness;
    }

    public double getAgreeableness() {
        return agreeableness;
    }

    public void setAgreeableness(double agreeableness) {
        this.agreeableness = agreeableness;
    }

    public double getEmotional_stability() {
        return emotional_stability;
    }

    public void setEmotional_stability(double emotional_stability) {
        this.emotional_stability = emotional_stability;
    }

    public double getConscientiousness() {
        return conscientiousness;
    }

    public void setConscientiousness(double conscientiousness) {
        this.conscientiousness = conscientiousness;
    }

    public double getExtraversion() {
        return extraversion;
    }

    public void setExtraversion(double extraversion) {
        this.extraversion = extraversion;
    }
}
