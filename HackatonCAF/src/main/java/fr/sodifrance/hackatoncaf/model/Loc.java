package fr.sodifrance.hackatoncaf.model;

public class Loc {

	private final double lat;
	private final double lng;

	public Loc(double lat, double lng) {
		this.lat = lat;
		this.lng = lng;
	}

	public double getLat() {
		return lat;
	}

	public double getLng() {
		return lng;
	}
}
