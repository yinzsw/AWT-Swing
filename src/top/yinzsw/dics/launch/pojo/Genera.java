package top.yinzsw.dics.launch.pojo;

public class Genera {
    private int iMedicineGeneraId;
    private String vMedicineGeneraName;
    private String vMedicineDescription;

    public Genera() {
    }

    public Genera(int iMedicineGeneraId, String vMedicineGeneraName, String vMedicineDescription) {
        this.iMedicineGeneraId = iMedicineGeneraId;
        this.vMedicineGeneraName = vMedicineGeneraName;
        this.vMedicineDescription = vMedicineDescription;
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

    public String getvMedicineDescription() {
        return vMedicineDescription;
    }

    public void setvMedicineDescription(String vMedicineDescription) {
        this.vMedicineDescription = vMedicineDescription;
    }

    @Override
    public String toString() {
        return "Genera{" +
                "iMedicineGeneraId=" + iMedicineGeneraId +
                ", vMedicineGeneraName='" + vMedicineGeneraName + '\'' +
                ", vMedicineDescription='" + vMedicineDescription + '\'' +
                '}';
    }
}
