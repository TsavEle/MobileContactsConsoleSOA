package gr.aueb.cf.mobilecontacts.dao;



import gr.aueb.cf.mobilecontacts.model.MobileContact;

import java.util.ArrayList;
import java.util.List;

public class MobileContactDAOImpl implements IMobileContactDAO {
    // Χρησιμοποιώ το List contacts ως data source για να υλοποιήσω τα CRUD services

    private static final List<MobileContact> contacts = new ArrayList<MobileContact>(); // Constructor που δημιουργεί μία κλάση
    // που μέσα της έχει έναν πίνακα, το Array List

    @Override
    public MobileContact insert(MobileContact mobileContact) {
        contacts.add(mobileContact);
        return mobileContact;
    }

    @Override
    public MobileContact update(Long id, MobileContact mobileContact) {
        contacts.set(getIndexById(id), mobileContact);
        return mobileContact;
    }

    @Override
    public void deleteById(Long id) {

    }

    @Override
    public MobileContact getById(Long id) {
        return null;
    }

    @Override
    public List<MobileContact> getAll() {
        return List.of();
    }

    @Override
    public void deleteByPhoneNumber(String phoneNumber) {

    }

    @Override
    public MobileContact getByPhoneNumber(String phoneNumber) {
        return null;
    }

    @Override
    public boolean userIdExists(Long id) {
        return false;
    }

    @Override
    public boolean phoneNumberExists(String phoneNumber) {
        return false;
    }

    private int getIndexById(Long id) {
        int positionToReturn = -1;
        for (int i = 0; i < contacts.size(); i++) {

            // το contacts.get(i) δίνει το mobileContact που υπάρχει στη θέση i
            // παίρνω το id του mobileContact με getId
            // Επειδή η getId επιστρέφει Long, μπορώ να χρησιμοποιήσω .equals()
            // το συγκρίνω με το id που δίνει ο χρήστης
            // Αν υπάρχει id κάπου στη λίστα που ισούται με το id που δίνει ο χρήστης
            // τότε το id που δίνει ο χρήστης χρησιμοποιείται
            // Επιστρέφω τη θέση στη λίστα που υπάρχει το id που δίνει ο χρήστης

            if (contacts.get(i).getId().equals(id)) {
                positionToReturn = i;
                break;
            }
        } return positionToReturn;
    }
}
