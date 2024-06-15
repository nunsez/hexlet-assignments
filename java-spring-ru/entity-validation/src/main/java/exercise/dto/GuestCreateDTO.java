package exercise.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

import java.time.LocalDate;

// BEGIN
public final class GuestCreateDTO {

    @NotBlank
    private String name;

    @Email
    private String email;

    @Pattern(regexp = "\\+\\d{11,13}")
    private String phoneNumber;

    @Pattern(regexp = "\\d{4}")
    private String clubCard;

    @Future
    private LocalDate cardValidUntil;

    public GuestCreateDTO() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getClubCard() {
        return clubCard;
    }

    public void setClubCard(String clubCard) {
        this.clubCard = clubCard;
    }

    public LocalDate getCardValidUntil() {
        return cardValidUntil;
    }

    public void setCardValidUntil(LocalDate cardValidUntil) {
        this.cardValidUntil = cardValidUntil;
    }

}
// END
