package org.example.vklab4.dto;

public class DrinkDto {
    private Long id;
    private String name;
    private String type;
    private Double volume;
    private Double price;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getType() { return type; }
    public void setType(String type) { this.type = type; }
    public Double getVolume() { return volume; }
    public void setVolume(Double volume) { this.volume = volume; }
    public Double getPrice() { return price; }
    public void setPrice(Double price) { this.price = price; }
}
