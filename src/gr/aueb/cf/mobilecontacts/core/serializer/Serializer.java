package gr.aueb.cf.mobilecontacts.core.serializer;

import gr.aueb.cf.mobilecontacts.dto.MobileContactsReadOnlyDTO;

/**
 * Util class.
 */
public class Serializer {

    private Serializer () {

    }

    public static String serializeDTO(MobileContactsReadOnlyDTO readOnlyDTO) {
        return "ID: " + readOnlyDTO.getId() + ", Όνομα: " + readOnlyDTO.getFirstname()
                + ", Επώνυμο: " + readOnlyDTO.getLastname() + ", Τηλέφωνο: " + readOnlyDTO.getPhoneNumber();
    }

}
