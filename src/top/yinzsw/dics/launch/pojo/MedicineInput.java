package top.yinzsw.dics.launch.pojo;

import java.util.Date;

public class MedicineInput {
    private int iInputListId;
    private int iMedicineId;
    private String vMedicineName;
    private int iFacturerId;
    private String vFacturerName;
    private int iSupplierId;
    private String vSupplierName;
    private int inputQuantity;
    private Date dDateOfProduction;
    private Date dDateOfExpiry;
    private Date dInputDate;
    private double mInputPrice;

    public MedicineInput() {
    }

    public MedicineInput(int iInputListId, int iMedicineId, String vMedicineName, int iFacturerId, String vFacturerName, int iSupplierId, String vSupplierName, int inputQuantity, Date dDateOfProduction, Date dDateOfExpiry, Date dInputDate, double mInputPrice) {
        this.iInputListId = iInputListId;
        this.iMedicineId = iMedicineId;
        this.vMedicineName = vMedicineName;
        this.iFacturerId = iFacturerId;
        this.vFacturerName = vFacturerName;
        this.iSupplierId = iSupplierId;
        this.vSupplierName = vSupplierName;
        this.inputQuantity = inputQuantity;
        this.dDateOfProduction = dDateOfProduction;
        this.dDateOfExpiry = dDateOfExpiry;
        this.dInputDate = dInputDate;
        this.mInputPrice = mInputPrice;
    }

    public int getiInputListId() {
        return iInputListId;
    }

    public void setiInputListId(int iInputListId) {
        this.iInputListId = iInputListId;
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

    public int getiFacturerId() {
        return iFacturerId;
    }

    public void setiFacturerId(int iFacturerId) {
        this.iFacturerId = iFacturerId;
    }

    public String getvFacturerName() {
        return vFacturerName;
    }

    public void setvFacturerName(String vFacturerName) {
        this.vFacturerName = vFacturerName;
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

    public int getInputQuantity() {
        return inputQuantity;
    }

    public void setInputQuantity(int inputQuantity) {
        this.inputQuantity = inputQuantity;
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

    public Date getdInputDate() {
        return dInputDate;
    }

    public void setdInputDate(Date dInputDate) {
        this.dInputDate = dInputDate;
    }

    public double getmInputPrice() {
        return mInputPrice;
    }

    public void setmInputPrice(double mInputPrice) {
        this.mInputPrice = mInputPrice;
    }

    @Override
    public String toString() {
        return "MedicineInput{" +
                "iInputListId=" + iInputListId +
                ", iMedicineId=" + iMedicineId +
                ", vMedicineName='" + vMedicineName + '\'' +
                ", iFacturerId=" + iFacturerId +
                ", vFacturerName='" + vFacturerName + '\'' +
                ", iSupplierId=" + iSupplierId +
                ", vSupplierName='" + vSupplierName + '\'' +
                ", inputQuantity=" + inputQuantity +
                ", dDateOfProduction=" + dDateOfProduction +
                ", dDateOfExpiry=" + dDateOfExpiry +
                ", dInputDate=" + dInputDate +
                ", mInputPrice=" + mInputPrice +
                '}';
    }
}
