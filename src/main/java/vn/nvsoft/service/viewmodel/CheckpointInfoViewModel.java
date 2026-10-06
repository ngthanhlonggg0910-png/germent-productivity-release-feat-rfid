package vn.nvsoft.service.viewmodel;

public interface CheckpointInfoViewModel {

    Long getCheckpointId();
    String getCheckpointName();
    Long getCheckpointCongDoanId();

    Long getBandId();
    String getBandName();
    Long getBandIndex();
    Long getBandTypeName();
    Long getBandTypeId();

    Long getFactoryId();
    Long getFactoryIndex();
    String getFactoryName();

    Long getCompanyId();
    String getCompanyAddr();
    String getCompanyName();
    String getCompanyTaxCode();

}
