package sa42.uno.model;

import javax.json.Json;
import javax.json.JsonObject;

public class Card {

    private String color;
    private String type;
    private int value;
    private String image;

    public String getColor() {
        return color;
    }

    void setColor(String color) {
        this.color = color;
    }

    public String getType() {
        return type;
    }

    void setType(String type) {
        this.type = type;
    }

    public int getValue() {
        return value;
    }

    void setValue(int value) {
        this.value = value;
    }

    public String getImage() {
        return image;
    }

    void setImage(String image) {
        this.image = image;
    }

    @Override
    public String toString() {
        return "Card: " + "color=" + color + ", type=" + type
                + ", value=" + value + ", image=" + image;
    }
    
    public JsonObject toJson() {
		return (Json.createObjectBuilder()
				.add("color", color)
				.add("type", type)
				.add("value", value)
				.add("image", image)
				.build());
	}

}
