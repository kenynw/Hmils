package com.dsk.chain.bijection;


/**
 * Created by rharter on 4/26/15.
 */
public class PresenterBuilder {

    public static <PresenterType extends Presenter> PresenterType fromViewClass(Class<?> viewClass) {
        RequiresPresenter annotation = viewClass.getAnnotation(RequiresPresenter.class);
        //noinspection unchecked
        if (annotation == null){
//            throw new RuntimeException("You must declaration @RequiresPresenter for your Activity");
            return null;
        }

        Class<PresenterType> presenterClass =  (Class<PresenterType>)annotation.value();

        PresenterType presenter;
        try {
            presenter = presenterClass.newInstance();
        } catch (InstantiationException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
        return presenter;
    }

}
