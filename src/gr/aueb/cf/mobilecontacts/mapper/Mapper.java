package gr.aueb.cf.mobilecontacts.mapper;

import gr.aueb.cf.mobilecontacts.dto.MobileContactsInsertDTO;
import gr.aueb.cf.mobilecontacts.dto.MobileContactsReadOnlyDTO;
import gr.aueb.cf.mobilecontacts.dto.MobileContactsUpdateDTO;
import gr.aueb.cf.mobilecontacts.model.MobileContact;

public class Mapper {

    /**
     * Util Class.
     */

    private Mapper () {

    }

    public static MobileContact mapInsertDTOToContact(MobileContactsInsertDTO dto) {
        return new MobileContact(null, dto.getFirstname(), dto.getLastname(), dto.getPhoneNumber());
    }

    public static MobileContact mapUpdateDTOToContact(MobileContactsUpdateDTO dto) {
        return new MobileContact(dto.getId(), dto.getFirstname(), dto.getLastname(), dto.getPhoneNumber());
    }

    public static MobileContactsReadOnlyDTO mapMobileContactToDTO(MobileContact mobileContact) {
        return new MobileContactsReadOnlyDTO(mobileContact.getId(), mobileContact.getFirstname(), mobileContact.getLastname(), mobileContact.getPhoneNumber());
    }
}
