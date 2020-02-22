package com.dapan.nofindviewbyid;

import android.app.Activity;
import android.view.View;

import com.dapan.reflectlib.Reflect;

import java.lang.reflect.Field;

public class ViewUtils {
    public static void inject(final Activity activity) {

        Reflect.inject(activity.getClass()).set(activity, new Reflect.Callback<View>() {
            @Override
            public View getValue(Field field) {
                View result = null;
                ViewById viewById = field.getAnnotation(ViewById.class);
                if (viewById != null) {
                    int id = viewById.value();
                    result = activity.findViewById(id);
                }
                return result;
            }
        });
    }
}
