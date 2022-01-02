package top.yinzsw.dics.launch.pojo;

import java.util.Date;

public class ApplicationList {
    private int iApplicationId;
    private int iMedicineId;
    private int iQuantityOfApplication;
    private Date dDateOfApplication;
    private String bStateOfApplication;
    private int iPresentQuantity;

    public ApplicationList() {
    }

    public ApplicationList(int iApplicationId, int iMedicineId, int iQuantityOfApplication, Date dDateOfApplication, String bStateOfApplication, int iPresentQuantity) {
        this.iApplicationId = iApplicationId;
        this.iMedicineId = iMedicineId;
        this.iQuantityOfApplication = iQuantityOfApplication;
        this.dDateOfApplication = dDateOfApplication;
        this.bStateOfApplication = bStateOfApplication;
        this.iPresentQuantity = iPresentQuantity;
    }

    public int getiApplicationId() {
        return iApplicationId;
    }

    public void setiApplicationId(int iApplicationId) {
        this.iApplicationId = iApplicationId;
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

    public String getbStateOfApplication() {
        return bStateOfApplication;
    }

    public void setbStateOfApplication(String bStateOfApplication) {
        this.bStateOfApplication = bStateOfApplication;
    }

    public int getiPresentQuantity() {
        return iPresentQuantity;
    }

    public void setiPresentQuantity(int iPresentQuantity) {
        this.iPresentQuantity = iPresentQuantity;
    }

    @Override
    public String toString() {
        return "ApplicationList{" +
                "iApplicationId=" + iApplicationId +
                ", iMedicineId=" + iMedicineId +
                ", iQuantityOfApplication=" + iQuantityOfApplication +
                ", dDateOfApplication=" + dDateOfApplication +
                ", bStateOfApplication=" + bStateOfApplication +
                ", iPresentQuantity=" + iPresentQuantity +
                '}';
    }
}


