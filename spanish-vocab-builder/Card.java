class Card {
    String english;
    String spanish;

    public Card(String english, String spanish) {
        this.english = english;
        this.spanish = spanish;
    }

    public String getEnglish() {
        return english;
    }

    public String getSpanish() {
        return spanish;
    }

    public String toString() {
        return english + ": " + spanish;
    }
}
