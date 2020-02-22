package com.dapan.nofindviewbyid;

import android.app.Activity;
import android.view.View;

import java.lang.reflect.Field;

public class ViewUtils {
    public static void inject(final Activity activity) {

        Field[] fields = activity.getClass().getDeclaredFields();
        for (Field field : fields) {
            View result = null;
            ViewById viewById = field.getAnnotation(ViewById.class);
            if (viewById != null) {
                int id = viewById.value();
                result = activity.findViewById(id);
            }
            if (result != null) {
                try {
                    if (!field.isAccessible()) field.setAccessible(true);
                    field.set(activity, result);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
