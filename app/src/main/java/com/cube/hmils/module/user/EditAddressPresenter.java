package com.cube.hmils.module.user;

import com.cube.hmils.model.bean.Province;
import com.cube.hmils.model.services.DefaultTransform;
import com.cube.hmils.utils.FileUtil;
import com.dsk.chain.bijection.Presenter;
import com.google.gson.Gson;

import org.json.JSONArray;

import java.util.ArrayList;

import rx.Observable;
import rx.Subscriber;

/**
 * Created by Carol on 2017/10/29.
 */

public class EditAddressPresenter extends Presenter<EditAddressActivity> {

    private ArrayList<Province> mOptions1Items = new ArrayList<>();
    private ArrayList<ArrayList<String>> mOptions2Items = new ArrayList<>();
    private ArrayList<ArrayList<ArrayList<String>>> mOptions3Items = new ArrayList<>();

    public void parseData() {
        Observable.create(new Observable.OnSubscribe<String>() {
            @Override
            public void call(Subscriber<? super String> subscriber) {
                if (mOptions1Items == null || mOptions1Items.size() <= 0) {
                    String JsonData = new FileUtil().getStringFromFile(getView(), "province.json");

                    ArrayList<Province> jsonBean = parseData(JsonData);

                    mOptions1Items = jsonBean;

                    for (int i = 0; i < jsonBean.size(); i++) {//遍历省份
                        ArrayList<String> CityList = new ArrayList<>();//该省的城市列表（第二级）
                        ArrayList<ArrayList<String>> Province_AreaList = new ArrayList<>();//该省的所有地区列表（第三极）

                        for (int c = 0; c < jsonBean.get(i).getCityList().size(); c++) {//遍历该省份的所有城市
                            String CityName = jsonBean.get(i).getCityList().get(c).getName();
                            CityList.add(CityName);//添加城市

                            ArrayList<String> City_AreaList = new ArrayList<>();//该城市的所有地区列表

                            //如果无地区数据，建议添加空字符串，防止数据为null 导致三个选项长度不匹配造成崩溃
                            if (jsonBean.get(i).getCityList().get(c).getArea() == null
                                    || jsonBean.get(i).getCityList().get(c).getArea().size() == 0) {
                                City_AreaList.add("");
                            } else {

                                for (int d = 0; d < jsonBean.get(i).getCityList().get(c).getArea().size(); d++) {//该城市对应地区所有数据
                                    String AreaName = jsonBean.get(i).getCityList().get(c).getArea().get(d);

                                    City_AreaList.add(AreaName);//添加该城市所有地区数据
                                }
                            }
                            Province_AreaList.add(City_AreaList);//添加该省所有地区数据
                        }

                        mOptions2Items.add(CityList);

                        mOptions3Items.add(Province_AreaList);
                    }
                }
                subscriber.onNext("");
            }
        })
                .compose(new DefaultTransform<>())
                .subscribe(new Subscriber<String>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(String s) {
                        getView().showPickerView(mOptions1Items, mOptions2Items, mOptions3Items);
                    }
                });
    }

    private ArrayList<Province> parseData(String result) {//Gson 解析
        ArrayList<Province> detail = new ArrayList<>();
        try {
            JSONArray data = new JSONArray(result);
            Gson gson = new Gson();
            for (int i = 0; i < data.length(); i++) {
                Province entity = gson.fromJson(data.optJSONObject(i).toString(), Province.class);
                detail.add(entity);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return detail;
    }

}
