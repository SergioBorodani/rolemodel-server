package ru.curs.rolemodel.lyra;

import ru.curs.celesta.CallContext;
import ru.curs.lyra.dto.FormInstantiationParams;
import ru.curs.lyra.kernel.BasicGridForm;
import ru.curs.lyra.kernel.GridRefinementHandler;
import ru.curs.lyra.kernel.LyraFormField;
import ru.curs.lyra.kernel.annotations.FormField;
import ru.curs.lyra.kernel.annotations.FormParams;
import ru.curs.lyra.kernel.annotations.LyraForm;
import ru.curs.rolemodel.data.RoljCursor;

import java.util.ArrayList;
import java.util.Map;

@LyraForm(gridWidth = "100%", gridHeight = "500px")
public class RoljLyra extends BasicGridForm<RoljCursor> {

    @FormParams
    private FormInstantiationParams params = null;

    private String oldSort;

    //Constructor will be run only once: each form is a Spring's singleton Component
    public RoljLyra(CallContext c, GridRefinementHandler changeNotifier) {
        super(c, changeNotifier);

        LyraFormField f = createField("rolj");
        f.setCaption("Роль");
        f.setSortable(false);

        f = createField("nazvanie");
        f.setCaption("Название");
        f.setSortable(false);
    }

    @Override
    public RoljCursor getCursor(CallContext callContext) {
        //sorting and filtering can also be performed here

        RoljCursor roljCursor = new RoljCursor(callContext);

        System.out.println("LLLLLLLLLLLLLLL44.getCursor");
        if (params != null) {
            System.out.println(params.getClientParams());

            Map<String, Object> refreshParams = (Map<String, Object>) params.getClientParams().get("refreshParams");
            ArrayList<String> sort = (ArrayList<String>) refreshParams.get("sort");
            sort.forEach(System.out::print);
            System.out.println();

            roljCursor.orderBy("rolj");

            Map<String,String> filter = (Map<String,String>) refreshParams.get("filter");
            String filter1 = filter.get("filter1");
            System.out.println("filter1: " + filter1);

            if (filter1 != null && !filter1.isEmpty()) {
                StringBuilder sb = new StringBuilder();
                sb.append("nazvanie LIKE '%").append(filter1).append("%'");

                roljCursor.setComplexFilter(sb.toString());
            }        }

        return roljCursor;
    }

//    @FormField(visible = false)
//    public String getRolj(CallContext ctx) {
//        String rolj = rec(ctx).getRolj();
//
//        return rolj;
//    }
//
//    @FormField
//    public String getNazvanie(CallContext ctx) {
//        String nazvanie = rec(ctx).getNazvanie();
//
//        return nazvanie;
//    }

}

