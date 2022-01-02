package top.yinzsw.dics.launch.pojo;

import java.util.Date;

public class MedicineAppList {
    private int iMedicineId;
    private int iQuantityOfApplication;
    private Date dDateOfApplication;
    private int iPresentQuantity;
    private String vMedicineName;

    public MedicineAppList() {
    }

    public MedicineAppList(int iMedicineId, int iQuantityOfApplication, Date dDateOfApplication, int iPresentQuantity, String vMedicineName) {
        this.iMedicineId = iMedicineId;
        this.iQuantityOfApplication = iQuantityOfApplication;
        this.dDateOfApplication = dDateOfApplication;
        this.iPresentQuantity = iPresentQuantity;
        this.vMedicineName = vMedicineName;
    }

    public int getiMedicineId() {
        return iMedicineId;
    }

    public void setiMedicineId(int iMedicineId) {
        this.iMedicineId = iMedicineId;
    }

    public int getiQuantityOfApplication() {
        return iQuantityOfApplication;
    }

    public void setiQuantityOfApplication(int iQuantityOfApplication) {
        this.iQuantityOfApplication = iQuantityOfApplication;
    }

    public Date getdDateOfApplication() {
        return dDateOfApplication;
    }

    public void setdDateOfApplication(Date dDateOfApplication) {
        this.dDateOfApplication = dDateOfApplication;
    }

    public int getiPresentQuantity() {
        return iPresentQuantity;
    }

    public void setiPresentQuantity(int iPresentQuantity) {
        this.iPresentQuantity = iPresentQuantity;
    }

    public String getvMedicineName() {
        return vMedicineName;
    }

    public void setvMedicineName(String vMedicineName) {
        this.vMedicineName = vMedicineName;
    }

    @Override
    public String toString() {
        return "MedicineOutput{" +
                "iMedicineId=" + iMedicineId +
                ", iQuantityOfApplication=" + iQuantityOfApplication +
                ", dDateOfApplication=" + dDateOfApplication +
                ", iPresentQuantity=" + iPresentQuantity +
                ", vMedicineName='" + vMedicineName + '\'' +
                '}';
    }
}
