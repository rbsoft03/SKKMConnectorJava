package rbsoftskkm.data.contracts;

import com.fasterxml.jackson.annotation.JsonProperty;
import rbsoftskkm.dto.*;
import rbsoftskkm.dto.admin.DeviceSettings;
import rbsoftskkm.dto.admin.ServiceSettings;
import rbsoftskkm.dto.admin.ServiceUser;
import rbsoftskkm.dto.positions.Industry;
import java.util.ArrayList;
import java.util.List;

public final class AdminContracts {

    private AdminContracts() {
    }

    public static class DeviceSettingsRequest {
        @JsonProperty("DeviceName")
        public String DeviceName;

        @JsonProperty("Settings")
        public DeviceSettings Settings;
    }

    public static class ServiceSettingsRequest {
        @JsonProperty("ServiceSettings")
        public ServiceSettings ServiceSettings;
    }

    public static class UserProfileRequest {
        @JsonProperty("User")
        public ServiceUser User;
    }

    public static class DeviceFontSettingsRequest {
        @JsonProperty("DeviceName")
        public String DeviceName;

        @JsonProperty("TemplateSettingH1")
        public String TemplateSettingH1;

        @JsonProperty("TemplateSettingH2")
        public String TemplateSettingH2;

        @JsonProperty("TemplateSettingH3")
        public String TemplateSettingH3;

        @JsonProperty("TemplateSettingH4")
        public String TemplateSettingH4;

        @JsonProperty("TemplateSettingH5")
        public String TemplateSettingH5;
    }

    public static class CheckTemplateRequest {
        @JsonProperty("Name")
        public String Name;

        @JsonProperty("Document")
        public CheckTemplateDocumentRequest Document;
    }

    public static class CheckTemplateDocumentRequest {
        @JsonProperty("PaymentType")
        public int PaymentType;

        @JsonProperty("TaxVariant")
        public int TaxVariant;

        @JsonProperty("Customer")
        public Customer Customer;

        @JsonProperty("SenderEmail")
        public String SenderEmail;

        @JsonProperty("SaleAddress")
        public String SaleAddress;

        @JsonProperty("SaleLocation")
        public String SaleLocation;

        @JsonProperty("Positions")
        public ApiPosition[] Positions;

        @JsonProperty("Payments")
        public Payments Payments;

        @JsonProperty("ElectronicPaymentInfo")
        public List<ElectronicPayment> ElectronicPaymentInfo;

        @JsonProperty("Electronically")
        public boolean Electronically;

        @JsonProperty("OperationalAttribute")
        public OperationalAttribute OperationalAttribute;

        @JsonProperty("IndustryAttribute")
        public Industry IndustryAttribute;

        @JsonProperty("UserAttribute")
        public UserAttribute UserAttribute;

        @JsonProperty("TimeZone")
        public Integer TimeZone;

        @JsonProperty("OperationOnline")
        public boolean OperationOnline;

        @JsonProperty("AdditionalAttribute")
        public String AdditionalAttribute;

        @JsonProperty("CorrectionData")
        public CorrectionData CorrectionData;
    }

    public static class CheckCopyFnParameters {
        @JsonProperty("DeviceName")
        public String DeviceName;

        @JsonProperty("FnNumber")
        public String FnNumber;

        @JsonProperty("FiscalSign")
        public String FiscalSign;

        @JsonProperty("DocNumber")
        public int DocNumber;
    }

    public static class MarkingCodesRequest {
        @JsonProperty("DeviceName")
        public String DeviceName;

        @JsonProperty("Codes")
        public List<String> Codes = new ArrayList<>();
    }

}
