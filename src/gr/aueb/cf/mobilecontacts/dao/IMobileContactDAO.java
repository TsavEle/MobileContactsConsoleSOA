package gr.aueb.cf.mobilecontacts.dao;



import gr.aueb.cf.mobilecontacts.model.MobileContact;

import java.util.List;

public interface IMobileContactDAO {
    // Οι βασικές CRUD υπηρεσίες

    MobileContact insert(MobileContact mobileContact);
    MobileContact update(Long id, MobileContact mobileContact);
    void deleteById(Long id);
    // Αντιστοιχεί στο select
    MobileContact getById(Long id);
    List<MobileContact> getAll();

    // Επιπλέων CRUD υπηρεσίες

    void deleteByPhoneNumber(String phoneNumber);
    MobileContact getByPhoneNumber(String phoneNumber);
    boolean userIdExists(Long id);
    boolean phoneNumberExists(String phoneNumber);
}
