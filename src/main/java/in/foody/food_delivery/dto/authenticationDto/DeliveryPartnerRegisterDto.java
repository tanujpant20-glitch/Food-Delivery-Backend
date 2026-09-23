package in.foody.food_delivery.dto.authenticationDto;

import in.foody.food_delivery.entity.enums.VehicleType;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DeliveryPartnerRegisterDto {

        @NotBlank(message = "Name is required")
        private String name;

        @NotBlank(message = "Email is required")
        @Email(message = "Invalid email format")
        private String email;

        private int age;

        @NotBlank(message = "Phone number is required")
        @Size(min = 10,max = 10, message = "check the number")
        private String phoneNumber;

        @NotBlank(message = "Password is required")
        private String password;

        @NotBlank(message = "Confirm password is required")
        private String samePassword;

        // Delivery Specific Fields
        @NotNull(message = "Vehicle type is required")
        private VehicleType vehicleType; // Enum: BICYCLE, MOTORCYCLE, SCOOTER, ELECTRIC_VEHICLE

        @NotBlank(message = "Driving license number is required")
        private String drivingLicenseNumber;

        @NotBlank(message = "Aadhaar number is required")
        @Size(min = 12, max = 12, message = "check aadhaar number again")
        private String aadhaarNumber;

        @NotBlank(message = "PAN number is required")
        @Pattern(regexp = "[A-Z]{5}[0-9]{4}[A-Z]{1}", message = "Invalid PAN number format")
        private String panNumber;

        // Bank Details for Payouts
        @NotBlank(message = "Bank account number is required")
        private String bankAccountNumber;

        @NotBlank(message = "IFSC code is required")
        private String ifscCode;

}
