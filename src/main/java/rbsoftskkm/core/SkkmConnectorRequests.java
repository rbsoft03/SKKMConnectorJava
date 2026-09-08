package rbsoftskkm.core;

import rbsoftskkm.data.*;
import rbsoftskkm.data.contracts.*;
import rbsoftskkm.dto.*;
import rbsoftskkm.dto.fiscalization.*;
import rbsoftskkm.dto.positions.*;
import rbsoftskkm.dto.templates.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Порт SkkmConnector.Requests.cs
 */
public abstract class SkkmConnectorRequests extends SkkmConnectorInternals {

    /** Касса и кассир. */
    protected void fillBase(CheckbaseParameters check) {
        check.DeviceName = this.DeviceName;
        check.Cashier = this.Cashier;
    }

    /** Смена, X/Z-отчет, отчет о расчетах, денежный ящик. */
    protected CheckbaseParameters checkBase() {
        CheckbaseParameters check = new CheckbaseParameters();
        fillBase(check);
        return check;
    }

    /** Обычный чек. */
    protected CheckParameters checkBody() {
        CheckParameters check = new CheckParameters();
        fillCheck(check);
        return check;
    }

    /** Чек коррекции ФФД 1.2. */
    protected Correction120Parameters correction120Body() {
        Correction120Parameters check = new Correction120Parameters();
        check.CorrectionData = this.CorrectionData;
        fillCheck(check);
        return check;
    }

    /** Заполнение полей чека. */
    protected void fillCheck(CheckParameters check) {
        fillBase(check);
        check.PaymentType = this.PaymentType.value;
        check.TaxVariant = this.TaxVariant.value;
        check.Customer = this.Customer;
        check.SenderEmail = this.SenderEmail;
        check.SaleAddress = this.SaleAddress;
        check.SaleLocation = this.SaleLocation;
        check.AgentSign = this.AgentSign == null ? null : this.AgentSign.value;
        check.AgentData = this.Agent;
        check.Vendor = this.Vendor;
        check.Positions = buildPositions();
        check.Payments = this.Payments;
        check.ElectronicPaymentInfo = this.ElectronicPayments.isEmpty()
                ? null : new ArrayList<>(this.ElectronicPayments);
        check.TextBefore = this.TextBefore;
        check.TextAfter = this.TextAfter;
        check.Electronically = this.Electronically;
        check.OperationalAttribute = this.OperationalAttribute;
        check.IndustryAttribute = this.IndustryAttribute;
        check.UserAttribute = this.UserAttribute;
        check.TimeZone = this.TimeZone == null ? null : this.TimeZone.value;
        check.OperationOnline = this.OperationOnline ? Boolean.TRUE : null;
        check.AdditionalAttribute = this.AdditionalAttribute;
    }

    /** Чек коррекции ФФД 1.05. */
    protected Correction105Parameters correction105Body() {
        Correction105Taxes taxes = this.Correction105Taxes;
        Correction105Parameters check = new Correction105Parameters();
        check.CorrectionData = this.CorrectionData;
        check.PaymentType = this.PaymentType.value;
        check.TaxVariant = this.TaxVariant.value;
        check.Payments = this.Payments;
        if (taxes != null) {
            check.SumTaxNone = taxes.SumTaxNone;
            check.SumTax0 = taxes.SumTax0;
            check.SumTax5 = taxes.SumTax5;
            check.SumTax7 = taxes.SumTax7;
            check.SumTax10 = taxes.SumTax10;
            check.SumTax105 = taxes.SumTax105;
            check.SumTax107 = taxes.SumTax107;
            check.SumTax110 = taxes.SumTax110;
            check.SumTax118 = taxes.SumTax118;
            check.SumTax18 = taxes.SumTax18;
            check.SumTax20 = taxes.SumTax20;
            check.SumTax120 = taxes.SumTax120;
            check.SumTax22 = taxes.SumTax22;
            check.SumTax122 = taxes.SumTax122;
        }
        check.AdditionalAttribute = this.AdditionalAttribute;
        fillBase(check);
        return check;
    }

    /** Слип. */
    protected DocumentParameters slipBody() {
        DocumentParameters check = new DocumentParameters();
        check.Positions = SlipTextParser.Parse(this.TextForPrint);
        fillBase(check);
        return check;
    }

    /** Внесение / выемка. */
    protected CashdrawParameters cashBody() {
        CashdrawParameters check = new CashdrawParameters();
        check.Sum = this.CashAmount;
        fillBase(check);
        return check;
    }

    /** Позиции чека в модель запроса. */
    protected ApiPosition[] buildPositions() {
        return toApiPositions(this.Positions);
    }

    /** Тело POST/PUT checkTemplate: позиции в обертке FiscalString, как у печати чека. */
    protected AdminContracts.CheckTemplateRequest checkTemplateBody() {
        CheckTemplateParameters source = this.CheckTemplateParameters == null
                ? new CheckTemplateParameters() : this.CheckTemplateParameters;
        CheckTemplateDocument document = source.Document;

        AdminContracts.CheckTemplateRequest request = new AdminContracts.CheckTemplateRequest();
        request.Name = source.Name;
        if (document == null) {
            return request;
        }

        AdminContracts.CheckTemplateDocumentRequest body = new AdminContracts.CheckTemplateDocumentRequest();
        body.PaymentType = document.PaymentType.value;
        body.TaxVariant = document.TaxVariant.value;
        body.Customer = document.Customer;
        body.SenderEmail = blank(document.SenderEmail) ? null : document.SenderEmail;
        body.SaleAddress = blank(document.SaleAddress) ? null : document.SaleAddress;
        body.SaleLocation = blank(document.SaleLocation) ? null : document.SaleLocation;
        body.Positions = toApiPositions(document.Positions != null && !document.Positions.isEmpty()
                ? document.Positions : this.Positions);
        body.Payments = document.Payments;
        body.ElectronicPaymentInfo = document.ElectronicPayments == null
                || document.ElectronicPayments.isEmpty() ? null : document.ElectronicPayments;
        body.Electronically = document.Electronically;
        body.OperationalAttribute = document.OperationalAttribute;
        body.IndustryAttribute = document.IndustryAttribute;
        body.UserAttribute = document.UserAttribute;
        body.TimeZone = document.TimeZone == null ? null : document.TimeZone.value;
        body.OperationOnline = document.OperationOnline;
        body.AdditionalAttribute = blank(document.AdditionalAttribute)
                ? null : document.AdditionalAttribute;
        body.CorrectionData = document.CorrectionData;
        request.Document = body;
        return request;
    }

    protected static ApiPosition[] toApiPositions(List<Position> positions) {
        if (positions == null) {
            return new ApiPosition[0];
        }
        List<ApiPosition> result = new ArrayList<>();
        for (Position position : positions) {
            result.add(toApi(position));
        }
        return result.toArray(new ApiPosition[0]);
    }

    /** Одна позиция чека в модель запроса по ее типу. */
    protected static ApiPosition toApi(Position position) {
        ApiPosition api = new ApiPosition();
        if (position instanceof FiscalLine fiscal) {
            api.FiscalString = fiscal;
        } else if (position instanceof TextLine text) {
            return textToApi(text);
        } else if (position instanceof BarcodeLine barcode) {
            api.Barcode = barcode;
        } else if (position instanceof SeparatorLine separator) {
            api.SeparatorLine = separator;
        } else if (position instanceof PictureLine picture) {
            api.Picture = picture;
        } else {
            throw new IllegalStateException("Неизвестный тип позиции «"
                    + position.getClass().getSimpleName() + "». "
                    + "Допустимы FiscalLine, TextLine, BarcodeLine, SeparatorLine, PictureLine.");
        }
        return api;
    }

    /**
     * Текст с префиксом стиля линии ([dotted], [line], [line,dashed]) уходит как SeparatorLine.
     */
    protected static ApiPosition textToApi(TextLine text) {
        DocPosition parsed = SlipTextParser.ParseLine(text.Text, text.Font, text.Alignment);
        ApiPosition api = new ApiPosition();
        api.TextString = parsed.TextString;
        api.Barcode = parsed.Barcode;
        api.SeparatorLine = parsed.SeparatorLine;
        api.Picture = parsed.Picture;
        return api;
    }

    /** Тело запроса фискализации. */
    protected FiscalizationRequest fiscalizationBody() {
        FiscalizationParameters source = this.FiscalizationParameters;
        FiscalizationRequest body = new FiscalizationRequest();
        body.DeviceName = this.DeviceName;
        body.Cashier = this.Cashier;
        if (source != null) {
        body.RnNumber = source.RnNumber;
        body.TaxationSystems = source.TaxationSystems;
        body.Vatin = source.Vatin;
        body.CompanyName = source.CompanyName;
        body.Fn = source.Fn;
        body.FfdVersionKkt = source.FfdVersionKkt;
        body.FfdVersionFn = source.FfdVersionFn;
        body.RegistrationLabelCodes = source.RegistrationLabelCodes;
        body.OfdAddress = source.OfdAddress;
        body.OfdPort = source.OfdPort;
        body.AutomaticNumber = source.AutomaticNumber;
        body.SenderEmail = source.SenderEmail;
        body.ReasonCode = source.ReasonCode;
        body.IsmHost = source.IsmHost;
        body.IsmPort = source.IsmPort;
        body.FnsUrl = source.FnsUrl;
        body.OfdVatin = source.OfdVatin;
        body.OfdName = source.OfdName;
        body.AgentTypes = source.AgentTypes;
        body.IsBsoSign = source.IsBsoSign;
        body.IsMarking = source.IsMarking;
        body.IsPawnshop = source.IsPawnshop;
        body.IsAssurance = source.IsAssurance;
        body.IsAutomatic = source.IsAutomatic;
        body.IsVending = source.IsVending;
        body.IsAutomaticPrinter = source.IsAutomaticPrinter;
        body.IsOnline = source.IsOnline;
        body.IsLottery = source.IsLottery;
        body.IsGambling = source.IsGambling;
        body.IsExcisable = source.IsExcisable;
        body.IsService = source.IsService;
        body.IsEncrypted = source.IsEncrypted;
        body.IsOffline = source.IsOffline;
        body.IsCateringServices = source.IsCateringServices;
        body.IsWholesaleTrade = source.IsWholesaleTrade;
        body.SaleAddress = source.SaleAddress;
        body.SaleLocation = source.SaleLocation;
        }
        fillBase(body);
        return body;
    }
}
