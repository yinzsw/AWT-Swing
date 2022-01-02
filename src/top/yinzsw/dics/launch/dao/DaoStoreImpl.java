package top.yinzsw.dics.launch.dao;

import top.yinzsw.dics.launch.pojo.*;

import java.util.ArrayList;

public class DaoStoreImpl implements DaoStore {
    /*
     * 这个类有点大了,可以重构; 细分类 如: UserDao,ManufacturerDao等
     * sql语句可以用文件(xml)单独存放
     * (我不想动了) :<
     * */
    private static final DaoStoreImpl DAO_STORE = new DaoStoreImpl();
    private static final DBUtils DB = DBUtils.getInstance();

    private static final String SELECT_USER_TYPE = "SELECT * FROM musertype WHERE ";
    private static final String INSERT_USER_TYPE = "INSERT INTO musertype (cTypeName) VALUES (?)";
    private static final String DELETE_USER_TYPE = "DELETE FROM musertype WHERE ";
    private static final String UPDATE_USER_TYPE = "UPDATE musertype SET cTypeName=? WHERE iTypeId=?";


    private static final String SELECT_USER = "SELECT a.cTypeName,b.* " +
            "FROM musertype a INNER " +
            "JOIN muser b ON a.iTypeId = b.iTypeId " +
            "WHERE ";
    private static final String INSERT_USER = "INSERT INTO " +
            "muser(vUserName, vUserPass, vUserRealName, iTypeId) " +
            "VALUES(?, ?, ?, (SELECT iTypeId FROM musertype WHERE cTypeName=?))";
    private static final String DELETE_USER = "DELETE FROM muser WHERE ";
    private static final String UPDATE_USER = "UPDATE muser " +
            "SET vUserName=?, vUserPass=?, vUserRealName=?, iTypeId=(SELECT iTypeId FROM musertype WHERE cTypeName=?) " +
            "WHERE iUserId=?";


    private static final String SELECT_MANUFACTURER = "SELECT * FROM manufacturer WHERE ";
    private static final String INSERT_MANUFACTURER = "INSERT INTO " +
            "manufacturer(vFacturerName, cContanctMan, cContanctPhone, cContanctAddress, vDescription, cPostalCode, cSimplifiedCode, vBusinessScope) " +
            "VALUES(?, ?, ?, ?, ?, ?, ?, ?)";
    private static final String DELETE_MANUFACTURER = "DELETE FROM manufacturer WHERE iFacturerId IN ";
    private static final String UPDATE_MANUFACTURER = "UPDATE manufacturer " +
            "SET vFacturerName=?, cContanctMan=?, cContanctPhone=?, cContanctAddress=?, vDescription=?, cPostalCode=?, cSimplifiedCode=?, vBusinessScope=? " +
            "WHERE iFacturerId=?";


    private static final String SELECT_SUPPLIER = "SELECT * FROM supplier WHERE ";
    private static final String INSERT_SUPPLIER = "INSERT INTO " +
            "supplier(vSupplierName, cContanctMan, cContanctPhone, vContanctAddress, vDescription, cPostalCode, cSimplifiedCode, vBusinessScope) " +
            "VALUES(?, ?, ?, ?, ?, ?, ?, ?)";
    private static final String DELETE_SUPPLIER = "DELETE FROM supplier WHERE iSupplierId IN ";
    private static final String UPDATE_SUPPLIER = "UPDATE supplier " +
            "SET vSupplierName=?, cContanctMan=?, cContanctPhone=?, vContanctAddress=?, vDescription=?, cPostalCode=?, cSimplifiedCode=?, vBusinessScope=? " +
            "WHERE iSupplierId=?";


    private static final String SELECT_MEDICINE = "SELECT med.*, man.vFacturerName, gen.vMedicineGeneraName " +
            "FROM medicine med " +
            "JOIN manufacturer man ON med.iFacturerId = man.iFacturerId " +
            "JOIN genera gen ON med.iMedicineGeneraId = gen.iMedicineGeneraId " +
            "WHERE ";
    private static final String INSERT_MEDICINE = "INSERT INTO " +
            "medicine(vMedicineName, vMedicineDescription, mRetailPrices, vMedicineStandard, vMedicineDosage, iFacturerId, iMedicineGeneraId) " +
            "VALUES(?, ?, ?, ?, ?, (SELECT iFacturerId FROM manufacturer WHERE vFacturerName=?), (SELECT iMedicineGeneraId FROM genera WHERE vMedicineGeneraName=?))";
    private static final String DELETE_MEDICINE = "DELETE FROM medicine WHERE iMedicineId=?";
    private static final String UPDATE_MEDICINE = "UPDATE medicine " +
            "SET vMedicineName=?, vMedicineDescription=?, mRetailPrices=?, vMedicineStandard=?, vMedicineDosage=?, iFacturerId=(SELECT iFacturerId FROM manufacturer WHERE vFacturerName=?), iMedicineGeneraId=(SELECT iMedicineGeneraId FROM genera WHERE vMedicineGeneraName=?) " +
            "WHERE iMedicineId=?";


    private static final String SELECT_GENERA = "SELECT * FROM genera WHERE ";
    private static final String INSERT_GENERA = "INSERT INTO genera(vMedicineGeneraName, vMedicineDescription) VALUES (?, ?)";
    private static final String DELETE_GENERA = "DELETE FROM genera WHERE iMedicineGeneraId=?";
    private static final String UPDATE_GENERA = "UPDATE genera SET vMedicineGeneraName=?, vMedicineDescription=? WHERE iMedicineGeneraId=?";

    private static final String SELECT_PHARMACY = "SELECT * FROM pharmacy WHERE ";
    private static final String INSERT_PHARMACY = "INSERT INTO pharmacy(vPharmacyName, vPharmacyDescription) VALUES (?, ?)";
    private static final String DELETE_PHARMACY = "DELETE FROM pharmacy WHERE iPharmacyId=?";
    private static final String UPDATE_PHARMACY = "UPDATE pharmacy SET vPharmacyName=?, vPharmacyDescription=? WHERE iPharmacyId=?";


    private static final String SELECT_APPLICATION_LIST = "SELECT * FROM `applicationlist` WHERE ";
    private static final String UPDATE_APPLICATION_LIST = "UPDATE applicationlist SET bStateOfApplication=? WHERE iApplicationId=?";
    private static final String DELETE_APPLICATION_LIST = "DELETE FROM applicationlist WHERE iApplicationId=?";


    private static final String SELECT_STOREROOM = "SELECT st.*, me.vMedicineName, ma.vFacturerName " +
            "FROM storeroom st " +
            "JOIN medicine me ON st.iMedicineId = me.iMedicineId " +
            "JOIN manufacturer ma ON me.iFacturerId = ma.iFacturerId " +
            "WHERE ";
    private static final String UPDATE_STOREROOM = "UPDATE storeroom SET iPresentQuantity=0 WHERE iMedicineId=? AND iSupplierId=?";

    private static final String SELECT_EXPIRE_DRUG = "SELECT me.iMedicineId, me.iSupplierId, me.dDateOfProduction, me.dDateOfExpiry, st.iPresentQuantity, m.vMedicineName,s.vSupplierName " +
            "FROM medicineinput me " +
            "JOIN storeroom st ON me.iMedicineId = st.iMedicineId AND me.iSupplierId = st.iSupplierId " +
            "JOIN medicine m ON me.iMedicineId = m.iMedicineId " +
            "JOIN supplier s ON me.iSupplierId = s.iSupplierId";
    private static final String SELECT_MEDICINE_APP_LIST = "SELECT ap.iMedicineId, ap.iQuantityOfApplication, ap.dDateOfApplication, st.iPresentQuantity, me.vMedicineName " +
            "FROM applicationlist ap " +
            "JOIN storeroom st ON ap.iMedicineId = st.iMedicineId " +
            "JOIN medicine me ON ap.iMedicineId = me.iMedicineId";

    private static final String SELECT_MEDICINE_OUTPUT = "SELECT mo.iOutputId, mo.iMedicineId, mo.iQuantity, mo.iPharmacyId, ol.dOutputDate, m.vMedicineName, p.vPharmacyName " +
            "FROM medicineoutput mo " +
            "JOIN outputlist ol ON mo.iOutputId = ol.iOutputListId " +
            "JOIN medicine m ON mo.iMedicineId = m.iMedicineId " +
            "JOIN pharmacy p ON mo.iPharmacyId = p.iPharmacyId";
    private static final String INSERT_MEDICINE_OUTPUT = "INSERT INTO " +
            "medicineoutput(iOutputId, iMedicineId, iQuantity, iPharmacyId) " +
            "VALUES ((SELECT MAX(iOutputListId) max FROM outputlist),(SELECT iMedicineId FROM medicine WHERE vMedicineName=?),?,(SELECT iPharmacyId FROM pharmacy WHERE vPharmacyName=?))";
    private static final String DELETE_MEDICINE_OUTPUT = "DELETE FROM medicineoutput WHERE iOutputId=? AND iMedicineId=(SELECT iMedicineId FROM medicine WHERE vMedicineName=?)";
    private static final String UPDATE_MEDICINE_OUTPUT = "UPDATE medicineoutput " +
            "SET iQuantity=?,iPharmacyId=(SELECT iPharmacyId FROM pharmacy WHERE vPharmacyName=?) " +
            "WHERE iOutputId=? AND iMedicineId=(SELECT iMedicineId FROM medicine WHERE vMedicineName=?)";
    private static final String INSERT_MEDICINE_OUTPUT_LIST = "INSERT INTO outputlist(dOutputDate) VALUES (?)";
    private static final String DELETE_MEDICINE_OUTPUT_LIST = "DELETE FROM outputlist WHERE iOutputListId=?";

    private static final String SELECT_MEDICINE_INPUT = "SELECT mi.*, il.dInputDate, " +
            "(SELECT vMedicineName FROM medicine me WHERE mi.iMedicineId = me.iMedicineId) vMedicineName, " +
            "(SELECT vFacturerName FROM manufacturer ma WHERE mi.iFacturerId = ma.iFacturerId) vFacturerName, " +
            "(SELECT vSupplierName FROM supplier su WHERE mi.iSupplierId = su.iSupplierId) vSupplierName " +
            "FROM medicineinput mi " +
            "JOIN inputlist il ON mi.iInputListId = il.iInputListId";
    private static final String INSERT_MEDICINE_INPUT = "INSERT INTO " +
            "medicineinput(iInputListId, iMedicineId, inputQuantity, iFacturerId, iSupplierId, dDateOfProduction, dDateOfExpiry, mInputPrice) " +
            "VALUES (" +
            "   (SELECT MAX(iInputListId) max FROM inputlist), " +
            "   (SELECT iMedicineId FROM medicine WHERE vMedicineName=?), " +
            "   ?, " +
            "   (SELECT iFacturerId FROM manufacturer WHERE vFacturerName=?), " +
            "   (SELECT iSupplierId FROM supplier WHERE vSupplierName=?), ?, ?, ?)";
    private static final String DELETE_MEDICINE_INPUT = "DELETE FROM medicineinput WHERE iInputListId=? AND iMedicineId=(SELECT iMedicineId FROM medicine WHERE vMedicineName=?)";
    private static final String UPDATE_MEDICINE_INPUT = "UPDATE medicineinput " +
            "SET inputQuantity=?, " +
            "   iFacturerId=(SELECT iFacturerId FROM manufacturer WHERE vFacturerName=?), " +
            "   iSupplierId=(SELECT iSupplierId FROM supplier WHERE vSupplierName=?), " +
            "   dDateOfProduction=?, dDateOfExpiry=?, mInputPrice=? " +
            "WHERE iInputListId=? AND iMedicineId=(SELECT iMedicineId FROM medicine WHERE vMedicineName=?)";
    private static final String INSERT_MEDICINE_INPUT_LIST = "INSERT INTO inputlist(dInputDate) VALUES (?)";
    private static final String DELETE_MEDICINE_INPUT_LIST = "DELETE FROM inputlist WHERE iInputListId=?";

    private DaoStoreImpl() {
    }

    public static DaoStoreImpl getInstance() {
        return DAO_STORE;
    }

    @Override
    public ArrayList<User> getUserType() {
        return DB.queryList(User.class, SELECT_USER_TYPE.concat("1"));
    }

    @Override
    public int addUserType(String typeName) {
        return DB.update(INSERT_USER_TYPE, typeName);
    }

    @Override
    public int deleteUserType(int id) {
        DB.update(DELETE_USER.concat("iTypeId=?"), id);
        return DB.update(DELETE_USER_TYPE.concat("iTypeId=?"), id);
    }

    @Override
    public int updateUserType(int id, String typeName) {
        return DB.update(UPDATE_USER_TYPE, typeName, id);
    }


    @Override
    public ArrayList<User> getUser() {
        return DB.queryList(User.class, SELECT_USER.concat("1"));
    }

    @Override
    public ArrayList<User> getUser(int id) {
        return DB.queryList(User.class, SELECT_USER.concat("iUserId=?"), id);
    }

    @Override
    public ArrayList<User> getUser(String username) {
        return DB.queryList(User.class, SELECT_USER.concat("vUserName=?"), username);
    }

    @Override
    public ArrayList<User> getUser(String username, String password) {
        return DB.queryList(User.class, SELECT_USER.concat("vUserName=? AND vUserPass=?"), username, password);
    }

    @Override
    public int addUser(User user) {
        return DB.update(INSERT_USER,
                user.getvUserName(), user.getvUserPass(), user.getvUserRealName(), user.getcTypeName());
    }

    @Override
    public int deleteUser(int username) {
        return DB.update(DELETE_USER.concat("iUserId=?"), username);
    }

    @Override
    public int updateUser(User user) {
        return DB.update(UPDATE_USER, user.getvUserName(), user.getvUserPass(), user.getvUserRealName(), user.getcTypeName(), user.getiUserId());
    }


    @Override
    public ArrayList<Manufacturer> getManufacturer() {
        return DB.queryList(Manufacturer.class, SELECT_MANUFACTURER.concat("1"));
    }

    @Override
    public ArrayList<Manufacturer> getManufacturer(int id, String name, String shortCode) {
        if (id == 0) {
            return DB.queryList(Manufacturer.class, SELECT_MANUFACTURER.concat("vFacturerName LIKE ? AND cSimplifiedCode LIKE ?"), "%" + name + "%", "%" + shortCode + "%");
        }
        return DB.queryList(Manufacturer.class, SELECT_MANUFACTURER.concat("iFacturerId=?"), id);
    }

    @Override
    public int addManufacturer(Manufacturer manufacturer) {
        return DB.update(INSERT_MANUFACTURER,
                manufacturer.getvFacturerName(), manufacturer.getcContanctMan(), manufacturer.getcContanctPhone(),
                manufacturer.getcContanctAddress(), manufacturer.getvDescription(), manufacturer.getcPostalCode(),
                manufacturer.getcSimplifiedCode(), manufacturer.getvBusinessScope());
    }

    @Override
    public int deleteManufacturer(Object... ids) {
        String[] idsStr = new String[ids.length];
        for (int i = 0; i < idsStr.length; i++) {
            idsStr[i] = ids[i].toString();
        }
        String arg = String.join(",", idsStr);
        return DB.update(DELETE_MANUFACTURER + String.format("(%s)", arg));
    }

    @Override
    public int updateManufacturer(Manufacturer manufacturer) {
        return DB.update(UPDATE_MANUFACTURER, manufacturer.getvFacturerName(), manufacturer.getcContanctMan(),
                manufacturer.getcContanctPhone(), manufacturer.getcContanctAddress(), manufacturer.getvDescription(),
                manufacturer.getcPostalCode(), manufacturer.getcSimplifiedCode(), manufacturer.getvBusinessScope(), manufacturer.getiFacturerId());
    }


    @Override
    public ArrayList<Supplier> getSupplier() {
        return DB.queryList(Supplier.class, SELECT_SUPPLIER.concat("1"));
    }

    @Override
    public ArrayList<Supplier> getSupplier(int id, String name, String shortCode) {
        if (id == 0) {
            return DB.queryList(Supplier.class, SELECT_SUPPLIER.concat("vSupplierName LIKE ? AND cSimplifiedCode LIKE ?"), "%" + name + "%", "%" + shortCode + "%");
        }
        return DB.queryList(Supplier.class, SELECT_SUPPLIER.concat("iSupplierId=?"), id);
    }

    @Override
    public int addSupplier(Supplier supplier) {
        return DB.update(INSERT_SUPPLIER,
                supplier.getvSupplierName(), supplier.getcContanctMan(), supplier.getcContanctPhone(),
                supplier.getvContanctAddress(), supplier.getvDescription(), supplier.getcPostalCode(),
                supplier.getcSimplifiedCode(), supplier.getvBusinessScope());
    }

    @Override
    public int deleteSupplier(Object... ids) {
        String[] idsStr = new String[ids.length];
        for (int i = 0; i < idsStr.length; i++) {
            idsStr[i] = ids[i].toString();
        }
        String arg = String.join(",", idsStr);
        return DB.update(DELETE_SUPPLIER + String.format("(%s)", arg));
    }

    @Override
    public int updateSupplier(Supplier supplier) {
        return DB.update(UPDATE_SUPPLIER, supplier.getvSupplierName(), supplier.getcContanctMan(),
                supplier.getcContanctPhone(), supplier.getvContanctAddress(), supplier.getvDescription(),
                supplier.getcPostalCode(), supplier.getcSimplifiedCode(), supplier.getvBusinessScope(), supplier.getiSupplierId());
    }


    @Override
    public ArrayList<Medicine> getMedicine() {
        return DB.queryList(Medicine.class, SELECT_MEDICINE.concat("1"));
    }

    @Override
    public ArrayList<Medicine> getMedicine(Object... ids) {
        String[] idsStr = new String[ids.length];
        for (int i = 0; i < idsStr.length; i++) {
            idsStr[i] = ids[i].toString();
        }
        String arg = String.join(",", idsStr);
        return DB.queryList(Medicine.class, SELECT_MEDICINE + String.format("iMedicineId IN (%s)", arg));
    }

    @Override
    public ArrayList<Medicine> getMedicine(int id, String vendor, String name, String type) {
        if (id == 0) {
            return DB.queryList(Medicine.class, SELECT_MEDICINE.concat("vMedicineName LIKE ? AND vFacturerName LIKE ? AND vMedicineGeneraName LIKE ?"), "%" + name + "%", "%" + vendor + "%", "%" + type + "%");
        }
        return DB.queryList(Medicine.class, SELECT_MEDICINE.concat("iMedicineId=?"), id);
    }

    @Override
    public int addMedicine(Medicine medicine) {
        return DB.update(INSERT_MEDICINE,
                medicine.getvMedicineName(), medicine.getvMedicineDescription(), medicine.getmRetailPrices(),
                medicine.getvMedicineStandard(), medicine.getvMedicineDosage(), medicine.getvFacturerName(),
                medicine.getvMedicineGeneraName());
    }

    @Override
    public int deleteMedicine(int id) {
        return DB.update(DELETE_MEDICINE, id);
    }

    @Override
    public int updateMedicine(Medicine medicine) {
        return DB.update(UPDATE_MEDICINE,
                medicine.getvMedicineName(), medicine.getvMedicineDescription(), medicine.getmRetailPrices(),
                medicine.getvMedicineStandard(), medicine.getvMedicineDosage(), medicine.getvFacturerName(),
                medicine.getvMedicineGeneraName(), medicine.getiMedicineId());
    }


    @Override
    public ArrayList<Genera> getGenera() {
        return DB.queryList(Genera.class, SELECT_GENERA.concat("1"));
    }

    @Override
    public ArrayList<Genera> getGenera(Object... ids) {
        String[] idsStr = new String[ids.length];
        for (int i = 0; i < idsStr.length; i++) {
            idsStr[i] = ids[i].toString();
        }
        String arg = String.join(",", idsStr);
        return DB.queryList(Genera.class, SELECT_GENERA + String.format("iMedicineGeneraId IN (%s)", arg));
    }

    @Override
    public ArrayList<Genera> getGenera(int id, String name) {
        if (id == 0) {
            return DB.queryList(Genera.class, SELECT_GENERA.concat("vMedicineGeneraName LIKE ?"), "%" + name + "%");
        }
        return DB.queryList(Genera.class, SELECT_GENERA.concat("iMedicineGeneraId=?"), id);
    }

    @Override
    public int addGenera(Genera genera) {
        return DB.update(INSERT_GENERA, genera.getvMedicineGeneraName(), genera.getvMedicineDescription());
    }

    @Override
    public int deleteGenera(int id) {
        return DB.update(DELETE_GENERA, id);
    }

    @Override
    public int updateGenera(Genera genera) {
        return DB.update(UPDATE_GENERA, genera.getvMedicineGeneraName(), genera.getvMedicineDescription(), genera.getiMedicineGeneraId());
    }


    @Override
    public ArrayList<Pharmacy> getPharmacy() {
        return DB.queryList(Pharmacy.class, SELECT_PHARMACY.concat("1"));
    }

    @Override
    public ArrayList<Pharmacy> getPharmacy(Object... ids) {
        String[] idsStr = new String[ids.length];
        for (int i = 0; i < idsStr.length; i++) {
            idsStr[i] = ids[i].toString();
        }
        String arg = String.join(",", idsStr);
        return DB.queryList(Pharmacy.class, SELECT_PHARMACY + String.format("iPharmacyId IN (%s)", arg));
    }

    @Override
    public ArrayList<Pharmacy> getPharmacy(int id, String name) {
        if (id == 0) {
            return DB.queryList(Pharmacy.class, SELECT_PHARMACY.concat("vPharmacyName LIKE ?"), "%" + name + "%");
        }
        return DB.queryList(Pharmacy.class, SELECT_PHARMACY.concat("iPharmacyId=?"), id);
    }

    @Override
    public int addPharmacy(Pharmacy pharmacy) {
        return DB.update(INSERT_PHARMACY, pharmacy.getvPharmacyName(), pharmacy.getvPharmacyDescription());
    }

    @Override
    public int deletePharmacy(int id) {
        return DB.update(DELETE_PHARMACY, id);
    }

    @Override
    public int updatePharmacy(Pharmacy pharmacy) {
        return DB.update(UPDATE_PHARMACY, pharmacy.getvPharmacyName(), pharmacy.getvPharmacyDescription(), pharmacy.getiPharmacyId());
    }


    @Override
    public ArrayList<ApplicationList> getApplicationList() {
        return DB.queryList(ApplicationList.class, SELECT_APPLICATION_LIST.concat("1"));
    }

    @Override
    public int updateApplicationList(ApplicationList applicationList) {
        return DB.update(UPDATE_APPLICATION_LIST, applicationList.getbStateOfApplication(), applicationList.getiApplicationId());
    }

    @Override
    public int deleteApplicationList(int id) {
        return DB.update(DELETE_APPLICATION_LIST, id);
    }

    @Override
    public ArrayList<StoreRoom> getStoreRoom() {
        return DB.queryList(StoreRoom.class, SELECT_STOREROOM.concat("1"));
    }

    @Override
    public int updateStoreRoom(int mid, int sid) {
        return DB.update(UPDATE_STOREROOM, mid, sid);
    }

    @Override
    public ArrayList<ExpiredDrug> getExpiredDrug() {
        return DB.queryList(ExpiredDrug.class, SELECT_EXPIRE_DRUG);
    }

    @Override
    public ArrayList<MedicineAppList> getMedicineAppList() {
        return DB.queryList(MedicineAppList.class, SELECT_MEDICINE_APP_LIST);
    }

    @Override
    public ArrayList<MedicineOutput> getMedicineOutput() {
        return DB.queryList(MedicineOutput.class, SELECT_MEDICINE_OUTPUT);
    }

    @Override
    public int addMedicineOutput(MedicineOutput medicineOutput) {
        DB.update(INSERT_MEDICINE_OUTPUT_LIST, medicineOutput.getdOutputDate());
        return DB.update(INSERT_MEDICINE_OUTPUT, medicineOutput.getvMedicineName(), medicineOutput.getiQuantity(), medicineOutput.getvPharmacyName());
    }

    @Override
    public int deleteMedicineOutput(int oid, String mName) {
        DB.update(DELETE_MEDICINE_OUTPUT_LIST, oid);
        return DB.update(DELETE_MEDICINE_OUTPUT, oid, mName);
    }

    @Override
    public int updateMedicineOutput(MedicineOutput medicineOutput) {
        return DB.update(UPDATE_MEDICINE_OUTPUT,
                medicineOutput.getiQuantity(), medicineOutput.getvPharmacyName(),
                medicineOutput.getiOutputId(), medicineOutput.getvMedicineName());
    }


    @Override
    public ArrayList<MedicineInput> getMedicineInput() {
        return DB.queryList(MedicineInput.class, SELECT_MEDICINE_INPUT);
    }

    @Override
    public int addMedicineInput(MedicineInput medicineInput) {
        DB.update(INSERT_MEDICINE_INPUT_LIST, medicineInput.getdInputDate());
        return DB.update(INSERT_MEDICINE_INPUT,
                medicineInput.getvMedicineName(), medicineInput.getInputQuantity(), medicineInput.getvFacturerName(),
                medicineInput.getvSupplierName(), medicineInput.getdDateOfProduction(), medicineInput.getdDateOfExpiry(),
                medicineInput.getmInputPrice());
    }

    @Override
    public int deleteMedicineInput(int oid, String mName) {
        DB.update(DELETE_MEDICINE_INPUT_LIST, oid);
        return DB.update(DELETE_MEDICINE_INPUT, oid, mName);
    }

    @Override
    public int updateMedicineInput(MedicineInput medicineInput) {
        return DB.update(UPDATE_MEDICINE_INPUT,
                medicineInput.getInputQuantity(), medicineInput.getvFacturerName(), medicineInput.getvSupplierName(),
                medicineInput.getdDateOfProduction(), medicineInput.getdDateOfExpiry(), medicineInput.getmInputPrice(),
                medicineInput.getiInputListId(), medicineInput.getvMedicineName());
    }
}