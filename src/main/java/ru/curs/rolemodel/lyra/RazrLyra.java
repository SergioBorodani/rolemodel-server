package ru.curs.rolemodel.lyra;

import ru.curs.celesta.CallContext;
import ru.curs.lyra.dto.FormInstantiationParams;
import ru.curs.lyra.kernel.BasicGridForm;
import ru.curs.lyra.kernel.GridRefinementHandler;
import ru.curs.lyra.kernel.LyraFormField;
import ru.curs.lyra.kernel.annotations.FormParams;
import ru.curs.lyra.kernel.annotations.LyraForm;
import ru.curs.rolemodel.data.RazrCursor;
import ru.curs.rolemodel.data.RoljCursor;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@LyraForm(gridWidth = "100%", gridHeight = "500px")
public class RazrLyra extends BasicGridForm<RazrCursor> {

    @FormParams
    private FormInstantiationParams params = null;

    private String oldSort;

    //Constructor will be run only once: each form is a Spring's singleton Component
    public RazrLyra(CallContext c, GridRefinementHandler changeNotifier) {
        super(c, changeNotifier);

        LyraFormField f = createField("razr");
        f.setCaption("Разрешение");
        f.setSortable(false);

        f = createField("nazvanie");
        f.setCaption("Название");
        f.setSortable(false);
    }

    @Override
    public RazrCursor getCursor(CallContext callContext) {
        //sorting and filtering can also be performed here

        RazrCursor razrCursor = new RazrCursor(callContext);

        System.out.println("LLLLLLLLLLLLLLL44.getCursor");
        if (params != null) {
            System.out.println(params.getClientParams());

            Map<String, Object> refreshParams = (Map<String, Object>) params.getClientParams().get("refreshParams");
            ArrayList<String> sort = (ArrayList<String>) refreshParams.get("sort");
            sort.forEach(System.out::print);
            System.out.println();

            razrCursor.orderBy("razr");

            Map<String,String> filter = (Map<String,String>) refreshParams.get("filter");
            String filter1 = filter.get("filter1");
            System.out.println("filter1: " + filter1);

            if (filter1 != null && !filter1.isEmpty()) {
                StringBuilder sb = new StringBuilder();
                sb.append("nazvanie LIKE '%").append(filter1).append("%'");

                razrCursor.setComplexFilter(sb.toString());
            }
        }

        return razrCursor;
    }

}

