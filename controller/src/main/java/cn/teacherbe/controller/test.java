package cn.teacherbe.controller;

import cn.teacherbe.entity.SeplenishmentConfirm1;
import com.alibaba.fastjson.JSONArray;

import java.util.ArrayList;
import java.util.List;

public class test {
    public static void main(String[] args){
        /*String a = "1031-BL2A1-305GF";
        System.out.println(a.substring(0,a.length()-1));
        System.out.println(a.substring(a.length()-1,a.length()));
        String b = "1031-BL2A1-305GF";
        if(a.substring(0,a.length()-1).equals(b.substring(0,b.length()-1))){
            if(a.substring(a.length()-1,a.length()).equals("F")){
                if(b.substring(b.length()-1,b.length()).equals("W")){
                    System.out.println(a + "/" + b);
                }
            } else if(a.substring(a.length()-1,a.length()).equals("W")){
                if(b.substring(b.length()-1,b.length()).equals("F")){
                    System.out.println(a + "/" + b);
                }
            }
            System.out.println(1);
        } else {
            System.out.println(2);
        }*/

        com.alibaba.fastjson.JSONArray json = new JSONArray();
        List<SeplenishmentConfirm1> b = new ArrayList<SeplenishmentConfirm1>();
        SeplenishmentConfirm1 a = new SeplenishmentConfirm1();
        SeplenishmentConfirm1 a1 = new SeplenishmentConfirm1();
        a.setId(1);
        a.setNumberPlate("12345");
        b.add(a);
        a1.setId(2);
        a1.setNumberPlate("32131");
        b.add(a1);
        json.add(b);
        System.out.println(json);
    }
}
