package com.example.ruofan.meituan.bean;



import java.util.List;

/**
 * Created by ruofan on 2019/2/14.
 */
public class MeituanConfig {

    private List<HotelConfig> list;

    private List<NormalConfig> normal_list;


    public List<HotelConfig> getList() {
        return list;
    }

    public void setList(List<HotelConfig> list) {
        this.list = list;
    }

    public List<NormalConfig> getNormal_list() {
        return normal_list;
    }

    public void setNormal_list(List<NormalConfig> normal_list) {
        this.normal_list = normal_list;
    }
}
