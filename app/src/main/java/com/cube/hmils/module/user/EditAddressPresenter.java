package com.cube.hmils.module.user;

import com.cube.hmils.model.bean.Address;
import com.cube.hmils.model.bean.City;
import com.cube.hmils.model.bean.Dist;
import com.cube.hmils.model.bean.Province;
import com.cube.hmils.model.services.DefaultTransform;
import com.cube.hmils.model.services.ServicesClient;
import com.dsk.chain.expansion.data.BaseDataActivityPresenter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Carol on 2017/10/29.
 */

public class EditAddressPresenter extends BaseDataActivityPresenter<EditAddressActivity, Address> {

    List<Province> mOptions1Items = new ArrayList<>();
    List<List<City>> mOptions2Items = new ArrayList<>();
    List<List<List<Dist>>> mOptions3Items = new ArrayList<>();

    @Override
    protected void onCreateView(EditAddressActivity view) {
        super.onCreateView(view);
        load();
    }

    private void load() {
        ServicesClient.getServices().areaList().compose(new DefaultTransform<>())
                .doOnNext(address -> {
                    if (mOptions1Items == null || mOptions1Items.size() <= 0) {
                        List<Province> provinceList = address.getProvince();

                        mOptions1Items = provinceList;

                        for (int i = 0; i < provinceList.size(); i++) {//遍历省份
                            List<City> CityList = new ArrayList<>();//该省的城市列表（第二级）
                            List<List<Dist>> Province_AreaList = new ArrayList<>();//该省的所有地区列表（第三极）

                            Province province = provinceList.get(i);
                            for (City city : address.getCity()) {
                                if (city.getParentCode() == province.getProvinceCode()) {
                                    CityList.add(city);//添加城市

                                    ArrayList<Dist> City_AreaList = new ArrayList<>();//该城市的所有地区列表
                                    //如果无地区数据，建议添加空字符串，防止数据为null 导致三个选项长度不匹配造成崩溃
//                        if (jsonBean.get(i).getCityList().get(c).getCityName() == null
//                                || jsonBean.get(i).getCityList().get(c).getDistList().size() == 0) {
//                            City_AreaList.add("");
//                        } else {
                                    for (Dist dist : address.getDist()) {
                                        if (dist.getParentCode() == city.getCityCode()) {
                                            City_AreaList.add(dist);//添加该城市所有地区数据
                                        }
                                    }
//                        }
                                    if (City_AreaList.size() == 0) City_AreaList.add(new Dist());
                                    Province_AreaList.add(City_AreaList);//添加该省所有地区数据
                                }
                            }
                            if (CityList.size() == 0) CityList.add(new City());

                            mOptions2Items.add(CityList);
                            mOptions3Items.add(Province_AreaList);
                        }

                    }
                })
                .unsafeSubscribe(getDataSubscriber());
    }

}
