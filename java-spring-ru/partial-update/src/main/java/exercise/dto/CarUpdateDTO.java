package exercise.dto;

import org.openapitools.jackson.nullable.JsonNullable;

import jakarta.validation.constraints.NotNull;

// BEGIN
public final class CarUpdateDTO {

    @NotNull
    private JsonNullable<String> model;

    @NotNull
    private JsonNullable<String> manufacturer;

    @NotNull
    private JsonNullable<Integer> enginePower;

    public CarUpdateDTO() {
    }

    public JsonNullable<String> getModel() {
        return model;
    }

    public void setModel(JsonNullable<String> model) {
        this.model = model;
    }

    public JsonNullable<String> getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(JsonNullable<String> manufacturer) {
        this.manufacturer = manufacturer;
    }

    public JsonNullable<Integer> getEnginePower() {
        return enginePower;
    }

    public void setEnginePower(JsonNullable<Integer> enginePower) {
        this.enginePower = enginePower;
    }

}
// END
