package gr.aueb.cf.mobilecontacts.controller;

import gr.aueb.cf.mobilecontacts.core.serializer.Serializer;
import gr.aueb.cf.mobilecontacts.dao.IMobileContactDAO;
import gr.aueb.cf.mobilecontacts.dao.MobileContactDAOImpl;
import gr.aueb.cf.mobilecontacts.dto.MobileContactsInsertDTO;
import gr.aueb.cf.mobilecontacts.dto.MobileContactsReadOnlyDTO;
import gr.aueb.cf.mobilecontacts.dto.MobileContactsUpdateDTO;
import gr.aueb.cf.mobilecontacts.exceptions.ContactNotFoundException;
import gr.aueb.cf.mobilecontacts.exceptions.PhoneNumberAlreadyExistsException;
import gr.aueb.cf.mobilecontacts.mapper.Mapper;
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
            mobileContactsReadOnlyDTO = Mapper.mapMobileContactToDTO(mobileContact);

            return "OK\n" + Serializer.serializeDTO(mobileContactsReadOnlyDTO);

        } catch (PhoneNumberAlreadyExistsException e) {
            return "Error\n" + e.getMessage() + "\n";

        }
    }

    public String updateContact(MobileContactsUpdateDTO updateDTO) {
        MobileContact mobileContact;
        MobileContactsReadOnlyDTO mobileContactsReadOnlyDTO;

        try {
            // Validate input data
            String errorVector = ValidationUtil.updateDTO(updateDTO);

            if (!errorVector.isEmpty()) {
                return "Error.\n" + "Validation error\n" + errorVector;
            }

            // if validation is ok, then update contact
            mobileContact = service.updateMobileContact(updateDTO);
            mobileContactsReadOnlyDTO = Mapper.mapMobileContactToDTO(mobileContact);

            return "OK\n" + Serializer.serializeDTO(mobileContactsReadOnlyDTO);

        } catch (PhoneNumberAlreadyExistsException e) {
            return "Error\n" + e.getMessage() + "\n";

        } catch (ContactNotFoundException e) {
            return "Error\n" + e.getMessage() + "\n";
        }
    }

    public String deleteContactById(Long id) {
        try {
            service.deleteMobileContactById(id);
            return "OK.\n Contacted deleted successfully\n";
        } catch (ContactNotFoundException e) {
            return "Error.\n Contact not found.\n";
        }
    }

    public String getContactById(Long id) {
        MobileContact mobileContact;
        MobileContactsReadOnlyDTO readOnlyDTO;
        try {
            mobileContact = service.getMobileContactById(id);
            readOnlyDTO = Mapper.mapMobileContactToDTO(mobileContact);
            return "OK\n" + Serializer.serializeDTO(readOnlyDTO);
        } catch (ContactNotFoundException e) {
            return "Error.\n Contact not found.\n";
        }
    }


}
