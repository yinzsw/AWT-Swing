package top.yinzsw.dics.launch.pojo;

public class Medicine {
    private int iMedicineId;
    private String vMedicineName;
    private String vMedicineDescription;
    private double mRetailPrices;
    private String vMedicineStandard;
    private String vMedicineDosage;
    private int iFacturerId;
    private String vFacturerName;
    private int iMedicineGeneraId;
    private String vMedicineGeneraName;

    public Medicine() {
    }

    public Medicine(int iMedicineId, String vMedicineName, String vMedicineDescription, double mRetailPrices, String vMedicineStandard, String vMedicineDosage, int iFacturerId, String vFacturerName, int iMedicineGeneraId, String vMedicineGeneraName) {
        this.iMedicineId = iMedicineId;
        this.vMedicineName = vMedicineName;
        this.vMedicineDescription = vMedicineDescription;
        this.mRetailPrices = mRetailPrices;
        this.vMedicineStandard = vMedicineStandard;
        this.vMedicineDosage = vMedicineDosage;
        this.iFacturerId = iFacturerId;
        this.vFacturerName = vFacturerName;
        this.iMedicineGeneraId = iMedicineGeneraId;
        this.vMedicineGeneraName = vMedicineGeneraName;
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

    public String getvMedicineDescription() {
        return vMedicineDescription;
    }

    public void setvMedicineDescription(String vMedicineDescription) {
        this.vMedicineDescription = vMedicineDescription;
    }

    public double getmRetailPrices() {
        return mRetailPrices;
    }

    public void setmRetailPrices(double mRetailPrices) {
        this.mRetailPrices = mRetailPrices;
    }

    public String getvMedicineStandard() {
        return vMedicineStandard;
    }

    public void setvMedicineStandard(String vMedicineStandard) {
        this.vMedicineStandard = vMedicineStandard;
    }

    public String getvMedicineDosage() {
        return vMedicineDosage;
    }

    public void setvMedicineDosage(String vMedicineDosage) {
        this.vMedicineDosage = vMedicineDosage;
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

    public int getiMedicineGeneraId() {
        return iMedicineGeneraId;
    }

    public void setiMedicineGeneraId(int iMedicineGeneraId) {
        this.iMedicineGeneraId = iMedicineGeneraId;
    }

    public String getvMedicineGeneraName() {
        return vMedicineGeneraName;
    }

    public void setvMedicineGeneraName(String vMedicineGeneraName) {
        this.vMedicineGeneraName = vMedicineGeneraName;
    }

    @Override
    public String toString() {
        return "Medicine{" +
                "iMedicineId=" + iMedicineId +
                ", vMedicineName='" + vMedicineName + '\'' +
                ", vMedicineDescription='" + vMedicineDescription + '\'' +
                ", mRetailPrices=" + mRetailPrices +
                ", vMedicineStandard='" + vMedicineStandard + '\'' +
                ", vMedicineDosage='" + vMedicineDosage + '\'' +
                ", iFacturerId=" + iFacturerId +
                ", vFacturerName='" + vFacturerName + '\'' +
                ", iMedicineGeneraId=" + iMedicineGeneraId +
                ", vMedicineGeneraName='" + vMedicineGeneraName + '\'' +
                '}';
    }
}
