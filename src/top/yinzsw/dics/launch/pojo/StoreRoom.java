package top.yinzsw.dics.launch.pojo;

public class StoreRoom {
    private int iSupplierId;
    private int iMedicineId;
    private String vMedicineName;
    private String vFacturerName;
    private int iMinimumStorage;
    private int iPresentQuantity;

    public StoreRoom() {
    }

    public StoreRoom(int iSupplierId, int iMedicineId, String vMedicineName, String vFacturerName, int iMinimumStorage, int iPresentQuantity) {
        this.iSupplierId = iSupplierId;
        this.iMedicineId = iMedicineId;
        this.vMedicineName = vMedicineName;
        this.vFacturerName = vFacturerName;
        this.iMinimumStorage = iMinimumStorage;
        this.iPresentQuantity = iPresentQuantity;
    }

    public int getiSupplierId() {
        return iSupplierId;
    }

    public void setiSupplierId(int iSupplierId) {
        this.iSupplierId = iSupplierId;
    }

    public int getiMedicineId() {
        return iMedicineId;
    }

    public void setiMedicineId(int iMedicineId) {
        this.iMedicineId = iMedicineId;
    }

    public String getvMedicineName() {
        return vMedicineName;
    }

    public void setvMedicineName(String vMedicineName) {
        this.vMedicineName = vMedicineName;
    }

    public String getvFacturerName() {
        return vFacturerName;
    }

    public void setvFacturerName(String vFacturerName) {
        this.vFacturerName = vFacturerName;
    }

    public int getiMinimumStorage() {
        return iMinimumStorage;
    }

    public void setiMinimumStorage(int iMinimumStorage) {
        this.iMinimumStorage = iMinimumStorage;
    }

    public int getiPresentQuantity() {
        return iPresentQuantity;
    }

    public void setiPresentQuantity(int iPresentQuantity) {
        this.iPresentQuantity = iPresentQuantity;
    }

    @Override
    public String toString() {
        return "StoreRoom{" +
                "iSupplierId=" + iSupplierId +
                ", iMedicineId=" + iMedicineId +
                ", vMedicineName='" + vMedicineName + '\'' +
                ", vFacturerName='" + vFacturerName + '\'' +
                ", iMinimumStorage=" + iMinimumStorage +
                ", iPresentQuantity=" + iPresentQuantity +
                '}';
    }
}