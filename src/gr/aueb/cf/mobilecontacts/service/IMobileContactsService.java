package gr.aueb.cf.mobilecontacts.service;

import gr.aueb.cf.mobilecontacts.dto.MobileContactsInsertDTO;
import gr.aueb.cf.mobilecontacts.dto.MobileContactsUpdateDTO;
import gr.aueb.cf.mobilecontacts.exceptions.ContactNotFoundException;
import gr.aueb.cf.mobilecontacts.exceptions.PhoneNumberAlreadyExistsException;
import gr.aueb.cf.mobilecontacts.model.MobileContact;

import java.util.List;

public interface IMobileContactsService {

    MobileContact insertMobileContact(MobileContactsInsertDTO dto)
            throws PhoneNumberAlreadyExistsException;
    MobileContact updateMobileContact(MobileContactsUpdateDTO dto)
            throws PhoneNumberAlreadyExistsException, ContactNotFoundException;
    void deleteMobileContactById(Long id) throws ContactNotFoundException;
    MobileContact getMobileContactById(Long id) throws ContactNotFoundException;
    List<MobileContact> getAllContacts();
}
