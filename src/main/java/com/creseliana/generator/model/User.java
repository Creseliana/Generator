package com.creseliana.generator.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "users")
public class User extends Model {
    @NotNull
    private long userId;
    @NotEmpty
    @NotBlank
    private String username;
    @NotNull
    private LocalDateTime creationDate;
    @Positive
    private int luckyNumber;

    @Override
    public String toString() {
        return "User{" +
                "id='" + id + '\'' +
                ", userId=" + userId +
                ", username='" + username + '\'' +
                ", creationDate=" + creationDate +
                ", luckyNumber=" + luckyNumber +
                '}';
    }
}
