package rbsoftskkm.dto.marking;

import java.util.ArrayList;
import java.util.List;

/** Результат проверки кода маркировки. */
public class MarkingVerifyResult {
    /** Код результата проверки. */
    public int Code;

    /** Описание результата проверки. */
    public String Description = "";

    /** Данные проверки кодов маркировки. */
    public List<CodeMarkInfo> Codes = new ArrayList<>();

    /** Идентификатор операции проверки. */
    public String ReqId = "";

    /** Временная метка операции проверки. */
    public long ReqTimestamp;

    /** Признак офлайн-проверки. */
    public boolean IsCheckedOffline;
}
