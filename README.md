# noFindViewById

使用 [reflectLib](https://github.com/droid4j/reflectLib) 库重构 ViewUtils.inject() 方法。

```java
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
```
