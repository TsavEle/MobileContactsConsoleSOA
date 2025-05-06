package gr.aueb.cf.mobilecontacts.validation;

import gr.aueb.cf.mobilecontacts.dto.MobileContactsInsertDTO;
import gr.aueb.cf.mobilecontacts.dto.MobileContactsUpdateDTO;

public class ValidationUtil {
    /**
     * No instances of this Class should be available.
     */
    private ValidationUtil () {

    }

    public static String validateDTO(MobileContactsInsertDTO insertDTO) {
        String errorResponse = "";

        if (insertDTO.getPhoneNumber().length() <= 5) {
            errorResponse += "Ο τηλεφωνικός αριθμός πρέπει να έχει πέντε ή περισσότερα ψηφία.\n";
        }
        if (insertDTO.getFirstname().length() <= 2) {
            errorResponse += "Το όνομα πρέπει να περιέχει δύο ή περισσότερους χαρακτήρες.\n";
        }
        if (insertDTO.getLastname().length() <= 2) {
            errorResponse += "Το επώνυμο πρέπει να περιέχει δύο ή περισσότερους χαρακτήρες.\n";
        }

        return errorResponse;
    }

    public static String updateDTO(MobileContactsUpdateDTO updateDTO) {
        String errorResponse = "";

        if (updateDTO.getPhoneNumber().length() <= 5) {
            errorResponse += "Ο τηλεφωνικός αριθμός πρέπει να έχει πέντε ή περισσότερα ψηφία.\n";
        }
        if (updateDTO.getFirstname().length() <= 2) {
            errorResponse += "Το όνομα πρέπει να περιέχει δύο ή περισσότερους χαρακτήρες.\n";
        }
        if (updateDTO.getLastname().length() <= 2) {
            errorResponse += "Το επώνυμο πρέπει να περιέχει δύο ή περισσότερους χαρακτήρες.\n";
        }

        return errorResponse;
    }
}
