package model;

public enum  SHIP {

    YELLOW("view/resources/ships/yellow_ship.png", "view/resources/life.png"),
    BLACK("view/resources/ships/black_ship.png", ""),
    GREEN("view/resources/ships/green_ship.png", "view/resources/life.png");

    String urlShip;
    String lifeShip;

    private SHIP(String urlShip, String lifeShip) {
        this.urlShip = urlShip;
        this.lifeShip = lifeShip;
    }

    public String getUrlShip() {
        return urlShip;
    }

    public String getLifeShip() {
        return lifeShip;
    }
}
