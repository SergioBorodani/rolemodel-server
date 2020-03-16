package ru.curs.rolemodel.lyra;

import ru.curs.celesta.CallContext;
import ru.curs.lyra.dto.FormInstantiationParams;
import ru.curs.lyra.kernel.BasicGridForm;
import ru.curs.lyra.kernel.GridRefinementHandler;
import ru.curs.lyra.kernel.LyraFormField;
import ru.curs.lyra.kernel.annotations.FormField;
import ru.curs.lyra.kernel.annotations.FormParams;
import ru.curs.lyra.kernel.annotations.LyraForm;
import ru.curs.rolemodel.data.PoljzovateljCursor;

import java.util.*;

@LyraForm(gridWidth = "100%", gridHeight = "500px")
public class PoljzovateljLyra extends BasicGridForm<PoljzovateljCursor> {

    @FormParams
    private FormInstantiationParams params = null;

    private String oldSort;

    //Constructor will be run only once: each form is a Spring's singleton Component
    public PoljzovateljLyra(CallContext c, GridRefinementHandler changeNotifier) {
        super(c, changeNotifier);

        LyraFormField f = createField("sid");
        f.setCaption("Сид");
        f.setSortable(false);

        f = createField("login");
        f.setCaption("Логин");
        f.setSortable(false);
    }

    @Override
    public PoljzovateljCursor getCursor(CallContext callContext) {
        //sorting and filtering can also be performed here

        PoljzovateljCursor poljzovateljCursor = new PoljzovateljCursor(callContext);

        System.out.println("LLLLLLLLLLLLLLL44.getCursor");
        if (params != null) {
            System.out.println(params.getClientParams());

            Map<String, Object> refreshParams = (Map<String, Object>) params.getClientParams().get("refreshParams");
            ArrayList<String> sort = (ArrayList<String>) refreshParams.get("sort");
            sort.forEach(System.out::print);
            System.out.println();

            poljzovateljCursor.orderBy("sid");

            Map<String,String> filter = (Map<String,String>) refreshParams.get("filter");
            String filter1 = filter.get("filter1");
            System.out.println("filter1: " + filter1);

            if (filter1 != null && !filter1.isEmpty()) {
                StringBuilder sb = new StringBuilder();
                sb.append("login LIKE '%").append(filter1).append("%'");

                poljzovateljCursor.setComplexFilter(sb.toString());
            }        }

        return poljzovateljCursor;
    }

//    @FormField
//    public String getSid(CallContext ctx) {
//        String sid = rec(ctx).getSid();
//
//        return sid;
//    }
//
//    @FormField
//    public String getLogin(CallContext ctx) {
//        String login = rec(ctx).getLogin();
//
//        return login;
//    }

}

