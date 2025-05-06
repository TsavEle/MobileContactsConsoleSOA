package gr.aueb.cf.mobilecontacts.service;

import gr.aueb.cf.mobilecontacts.dao.IMobileContactDAO;
import gr.aueb.cf.mobilecontacts.dto.MobileContactsInsertDTO;
import gr.aueb.cf.mobilecontacts.dto.MobileContactsUpdateDTO;
import gr.aueb.cf.mobilecontacts.exceptions.ContactNotFoundException;
import gr.aueb.cf.mobilecontacts.exceptions.PhoneNumberAlreadyExistsException;
import gr.aueb.cf.mobilecontacts.model.MobileContact;

import java.util.List;


public class MobileContactsServiceImpl implements IMobileContactsService {
    private final IMobileContactDAO dao;

    public MobileContactsServiceImpl(IMobileContactDAO dao) {
        this.dao = dao;
    }

    @Override
    public MobileContact insertMobileContact(MobileContactsInsertDTO dto)
            throws PhoneNumberAlreadyExistsException {
        MobileContact mobileContact;

        try {
            if (dao.phoneNumberExists(dto.getPhoneNumber())) {
                throw new
                        PhoneNumberAlreadyExistsException("Contact with phone number " + dto.getPhoneNumber() + " already exists");
            }
            mobileContact = mapInsertDTOToContact(dto);

            System.err.printf("MobileContactsServiceImpl Logger: %s was inserted\n", mobileContact);
            return dao.insert(mobileContact);
        } catch (PhoneNumberAlreadyExistsException e){
            System.err.printf("MobileContactsServiceImpl Logger: contact with phone number: %s already exists \n", dto.getPhoneNumber());
            throw e;
        }

    }

    @Override
    public MobileContact updateMobileContact(MobileContactsUpdateDTO dto) throws
            PhoneNumberAlreadyExistsException, ContactNotFoundException {
        MobileContact mobileContact;
        MobileContact newContact;

        try {
            if (!dao.userIdExists(dto.getId())) {
                throw new ContactNotFoundException("Error. Contact with id: " + dto.getId() + " not found.");
            }

            mobileContact = dao.getById(dto.getId());
            boolean isPhoneNumberOurOwn = mobileContact.getPhoneNumber().equals(dto.getPhoneNumber());
            boolean isPhoneNumberExists = dao.phoneNumberExists(dto.getPhoneNumber());

            if (isPhoneNumberExists && !isPhoneNumberOurOwn) {
                throw new PhoneNumberAlreadyExistsException("Phone number " + dto.getPhoneNumber() + " already exists but cannot be updated");
            }

            newContact = mapUpdateDTOToContact(dto);
            System.err.printf("MobileContactServiceImpl Logger: %s was updated to %s \n",mobileContact, newContact );
            return dao.update(dto.getId(), newContact);

        } catch (ContactNotFoundException | PhoneNumberAlreadyExistsException e) {
            System.err.printf("MobileContactServiceImpl Logger: %s\n",e.getMessage());
            throw e;
        }
    }

    @Override
    public void deleteMobileContactById(Long id) throws ContactNotFoundException {
        try {
            if (!dao.userIdExists(id)) {
                throw new ContactNotFoundException("Contact with id: " + id + " not found");
            }
            System.err.printf("MobileContactServiceImpl Logger: contact with id: %d was deleted \n", id);
            dao.deleteById(id);
        } catch (ContactNotFoundException e) {
            System.err.printf("MobileContactServiceImpl Logger: %s \n" ,e.getMessage());
        }

    }

    @Override
    public MobileContact getMobileContactById(Long id) throws ContactNotFoundException {
        MobileContact mobileContact;

        try {
            mobileContact = dao.getById(id);
            if (mobileContact == null) {
                throw new ContactNotFoundException("Contact with id: " + id + " not found");
            }
            mobileContact = dao.getById(id);
            System.err.printf("MobileContactServiceImpl Logger: %s \n", mobileContact);
            return mobileContact;
        } catch (ContactNotFoundException e) {
            System.err.printf("Contact with id: %d  was not found\n", id);
            throw e;
        }
    }

    @Override
    public List<MobileContact> getAllContacts() {
        return dao.getAll();
    }

    @Override
    public MobileContact getContactByPhoneNumber(String phoneNumber) throws ContactNotFoundException {
        MobileContact mobileContact;

        try {
            mobileContact = dao.getByPhoneNumber(phoneNumber);
            if (mobileContact == null) {
                throw new ContactNotFoundException("Contact with phone number: " + phoneNumber + " not found");
            }
            mobileContact = dao.getByPhoneNumber(phoneNumber);
            System.err.printf("MobileContactServiceImpl Logger: %s \n", mobileContact);
            return mobileContact;
        } catch (ContactNotFoundException e) {
            System.err.printf("Contact with phone number: %s  was not found\n", phoneNumber);
            throw e;
        }
    }

    @Override
    public void deleteByPhoneNumber(String phoneNumber) throws ContactNotFoundException {
        try {
            if (!dao.phoneNumberExists(phoneNumber)) {
                throw new ContactNotFoundException("Contact with phone number: " + phoneNumber + " not found");
            }
            System.err.printf("MobileContactServiceImpl Logger: contact with phone number: %s was deleted \n", phoneNumber);
            dao.deleteByPhoneNumber(phoneNumber);
        } catch (ContactNotFoundException e) {
            System.err.printf("MobileContactServiceImpl Logger: %s \n" ,e.getMessage());
        }

    }

    private MobileContact mapInsertDTOToContact(MobileContactsInsertDTO dto) {
        return new MobileContact(null, dto.getFirstname(), dto.getLastname(), dto.getPhoneNumber());
    }

    private MobileContact mapUpdateDTOToContact(MobileContactsUpdateDTO dto) {
        return new MobileContact(dto.getId(), dto.getFirstname(), dto.getLastname(), dto.getPhoneNumber());
    }
}
