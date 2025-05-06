package gr.aueb.cf.mobilecontacts.controller;

import gr.aueb.cf.mobilecontacts.dao.IMobileContactDAO;
import gr.aueb.cf.mobilecontacts.dao.MobileContactDAOImpl;
import gr.aueb.cf.mobilecontacts.dto.MobileContactsInsertDTO;
import gr.aueb.cf.mobilecontacts.dto.MobileContactsReadOnlyDTO;
import gr.aueb.cf.mobilecontacts.exceptions.PhoneNumberAlreadyExistsException;
import gr.aueb.cf.mobilecontacts.model.MobileContact;
import gr.aueb.cf.mobilecontacts.service.IMobileContactsService;
import gr.aueb.cf.mobilecontacts.service.MobileContactsServiceImpl;
import gr.aueb.cf.mobilecontacts.validation.ValidationUtil;

public class MobileContactController {

    private final IMobileContactDAO dao = new MobileContactDAOImpl();
    private final IMobileContactsService service = new MobileContactsServiceImpl(dao);

    public String insertContact(MobileContactsInsertDTO insertDTO) {
        MobileContact mobileContact;
        MobileContactsReadOnlyDTO mobileContactsReadOnlyDTO;

        try {
            // Validate input data
            String errorVector = ValidationUtil.validateDTO(insertDTO);

            if (!errorVector.isEmpty()) {
                return "Error." + "Validation error\n" + errorVector;
            }

            // if validation is ok, then insert contact
            mobileContact = service.insertMobileContact(insertDTO);
            mobileContactsReadOnlyDTO = mapMobileContactToDTO(mobileContact);

            return "OK\n" + serializeDTO(mobileContactsReadOnlyDTO);

        } catch (PhoneNumberAlreadyExistsException e) {
            return "Error\n" + e.getMessage() + "\n";

        }
    }

    private MobileContactsReadOnlyDTO mapMobileContactToDTO(MobileContact mobileContact) {
        return new MobileContactsReadOnlyDTO(mobileContact.getId(), mobileContact.getFirstname(), mobileContact.getLastname(), mobileContact.getPhoneNumber());
    }

    private String serializeDTO(MobileContactsReadOnlyDTO readOnlyDTO) {
        return "ID: " + readOnlyDTO.getId() + ", Όνομα: " + readOnlyDTO.getFirstname()
                + ", Επώνυμο: " + readOnlyDTO.getLastname() + ", Τηλέφωνο: " + readOnlyDTO.getPhoneNumber();
    }
}
