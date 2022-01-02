package top.yinzsw.dics.launch.dao;

import top.yinzsw.dics.launch.pojo.*;

import java.util.ArrayList;

public interface DaoStore {
    ArrayList<User> getUserType();

    int addUserType(String typeName);

    int deleteUserType(int id);

    int updateUserType(int id, String typeName);


    ArrayList<User> getUser();

    ArrayList<User> getUser(int id);

    ArrayList<User> getUser(String username);

    ArrayList<User> getUser(String username, String password);

    int addUser(User user);

    int deleteUser(int username);

    int updateUser(User user);


    ArrayList<Manufacturer> getManufacturer();

    ArrayList<Manufacturer> getManufacturer(int id, String name, String shortCode);

    int addManufacturer(Manufacturer manufacturer);

    int deleteManufacturer(Object... ids);

    int updateManufacturer(Manufacturer manufacturer);


    ArrayList<Supplier> getSupplier();

    ArrayList<Supplier> getSupplier(int id, String name, String shortCode);

    int addSupplier(Supplier supplier);

    int deleteSupplier(Object... ids);

    int updateSupplier(Supplier supplier);


    ArrayList<Medicine> getMedicine();

    ArrayList<Medicine> getMedicine(Object... ids);

    ArrayList<Medicine> getMedicine(int id, String vendor, String name, String type);

    int addMedicine(Medicine medicine);

    int deleteMedicine(int id);

    int updateMedicine(Medicine medicine);


    ArrayList<Genera> getGenera();

    ArrayList<Genera> getGenera(Object... ids);

    ArrayList<Genera> getGenera(int id, String name);

    int addGenera(Genera genera);

    int deleteGenera(int id);

    int updateGenera(Genera genera);


    ArrayList<Pharmacy> getPharmacy();

    ArrayList<Pharmacy> getPharmacy(Object... ids);

    ArrayList<Pharmacy> getPharmacy(int id, String name);

    int addPharmacy(Pharmacy pharmacy);

    int deletePharmacy(int id);

    int updatePharmacy(Pharmacy pharmacy);


    ArrayList<ApplicationList> getApplicationList();

    int updateApplicationList(ApplicationList applicationList);

    int deleteApplicationList(int id);


    ArrayList<StoreRoom> getStoreRoom();

    int updateStoreRoom(int mid, int sid);

    ArrayList<ExpiredDrug> getExpiredDrug();

    ArrayList<MedicineAppList> getMedicineAppList();


    ArrayList<MedicineOutput> getMedicineOutput();

    int addMedicineOutput(MedicineOutput medicineOutput);

    int deleteMedicineOutput(int oid, String mName);

    int updateMedicineOutput(MedicineOutput medicineOutput);


    ArrayList<MedicineInput> getMedicineInput();

    int addMedicineInput(MedicineInput medicineInput);

    int deleteMedicineInput(int oid, String mName);

    int updateMedicineInput(MedicineInput medicineInput);
}
