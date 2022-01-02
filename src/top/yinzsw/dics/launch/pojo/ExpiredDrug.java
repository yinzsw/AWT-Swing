package top.yinzsw.dics.launch.pojo;

import java.util.Date;

public class ExpiredDrug {
    private int iMedicineId;
    private String vMedicineName;
    private int iSupplierId;
    private String vSupplierName;
    private Date dDateOfProduction;
    private Date dDateOfExpiry;
    private int iPresentQuantity;

    public ExpiredDrug() {
    }

    public ExpiredDrug(int iMedicineId, String vMedicineName, int iSupplierId, String vSupplierName, Date dDateOfProduction, Date dDateOfExpiry, int iPresentQuantity) {
        this.iMedicineId = iMedicineId;
        this.vMedicineName = vMedicineName;
        this.iSupplierId = iSupplierId;
        this.vSupplierName = vSupplierName;
        this.dDateOfProduction = dDateOfProduction;
        this.dDateOfExpiry = dDateOfExpiry;
        this.iPresentQuantity = iPresentQuantity;
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

    public int getiSupplierId() {
        return iSupplierId;
    }

    public void setiSupplierId(int iSupplierId) {
        this.iSupplierId = iSupplierId;
    }

    public String getvSupplierName() {
        return vSupplierName;
    }

    public void setvSupplierName(String vSupplierName) {
        this.vSupplierName = vSupplierName;
    }

    public Date getdDateOfProduction() {
        return dDateOfProduction;
    }

    public void setdDateOfProduction(Date dDateOfProduction) {
        this.dDateOfProduction = dDateOfProduction;
    }

    public Date getdDateOfExpiry() {
        return dDateOfExpiry;
    }

    public void setdDateOfExpiry(Date dDateOfExpiry) {
        this.dDateOfExpiry = dDateOfExpiry;
    }

    public int getiPresentQuantity() {
        return iPresentQuantity;
    }

    public void setiPresentQuantity(int iPresentQuantity) {
        this.iPresentQuantity = iPresentQuantity;
    }

    @Override
    public String toString() {
        return "ExpiredDrug{" +
                "iMedicineId=" + iMedicineId +
                ", vMedicineName='" + vMedicineName + '\'' +
                ", iSupplierId=" + iSupplierId +
                ", vSupplierName='" + vSupplierName + '\'' +
                ", dDateOfProduction=" + dDateOfProduction +
                ", dDateOfExpiry=" + dDateOfExpiry +
                ", iPresentQuantity=" + iPresentQuantity +
                '}';
    }
}

