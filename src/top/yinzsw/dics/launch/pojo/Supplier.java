package top.yinzsw.dics.launch.pojo;

public class Supplier {
    private int iSupplierId;
    private String vSupplierName;
    private String cContanctMan;
    private String cContanctPhone;
    private String vContanctAddress;
    private String vDescription;
    private String cPostalCode;
    private String cSimplifiedCode;
    private String vBusinessScope;

    public Supplier() {
    }

    public Supplier(int iSupplierId, String vSupplierName, String cContanctMan, String cContanctPhone, String vContanctAddress, String vDescription, String cPostalCode, String cSimplifiedCode, String vBusinessScope) {
        this.iSupplierId = iSupplierId;
        this.vSupplierName = vSupplierName;
        this.cContanctMan = cContanctMan;
        this.cContanctPhone = cContanctPhone;
        this.vContanctAddress = vContanctAddress;
        this.vDescription = vDescription;
        this.cPostalCode = cPostalCode;
        this.cSimplifiedCode = cSimplifiedCode;
        this.vBusinessScope = vBusinessScope;
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

    public String getcContanctMan() {
        return cContanctMan;
    }

    public void setcContanctMan(String cContanctMan) {
        this.cContanctMan = cContanctMan;
    }

    public String getcContanctPhone() {
        return cContanctPhone;
    }

    public void setcContanctPhone(String cContanctPhone) {
        this.cContanctPhone = cContanctPhone;
    }

    public String getvContanctAddress() {
        return vContanctAddress;
    }

    public void setvContanctAddress(String vContanctAddress) {
        this.vContanctAddress = vContanctAddress;
    }

    public String getvDescription() {
        return vDescription;
    }

    public void setvDescription(String vDescription) {
        this.vDescription = vDescription;
    }

    public String getcPostalCode() {
        return cPostalCode;
    }

    public void setcPostalCode(String cPostalCode) {
        this.cPostalCode = cPostalCode;
    }

    public String getcSimplifiedCode() {
        return cSimplifiedCode;
    }

    public void setcSimplifiedCode(String cSimplifiedCode) {
        this.cSimplifiedCode = cSimplifiedCode;
    }

    public String getvBusinessScope() {
        return vBusinessScope;
    }

    public void setvBusinessScope(String vBusinessScope) {
        this.vBusinessScope = vBusinessScope;
    }

    @Override
    public String toString() {
        return "Supplier{" +
                "iSupplierId=" + iSupplierId +
                ", vSupplierName='" + vSupplierName + '\'' +
                ", cContanctMan='" + cContanctMan + '\'' +
                ", cContanctPhone='" + cContanctPhone + '\'' +
                ", vContanctAddress='" + vContanctAddress + '\'' +
                ", vDescription='" + vDescription + '\'' +
                ", cPostalCode='" + cPostalCode + '\'' +
                ", cSimplifiedCode='" + cSimplifiedCode + '\'' +
                ", vBusinessScope='" + vBusinessScope + '\'' +
                '}';
    }
}
