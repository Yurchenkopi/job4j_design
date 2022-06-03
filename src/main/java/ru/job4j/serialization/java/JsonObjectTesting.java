package ru.job4j.serialization.java;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class JsonObjectTesting {
    public static void main(String[] args) {
        final Auto vaz2104 = new Auto(9, "A555AA777", false, new CarModel(1.6f, 77, "sedan"), new String[]{"7733 556644", "7744 332211"});

        /* JSONObject из json-строки строки */
        JSONObject jsonCarModel = new JSONObject("{\"engineVolume\":1.6,\"power\":77,\"bodywork\":\"sedan\"}");

        /* JSONArray из ArrayList */
        List<String> list = new ArrayList<>();
        list.add("7733 556644");
        list.add("7744 332211");
        JSONArray jsonAcceptedDriverLic = new JSONArray(list);

        /* JSONObject напрямую методом put */
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("numsOfAccidents", vaz2104.getNumsOfAccidents());
        jsonObject.put("regNum", vaz2104.getRegNum());
        jsonObject.put("regLimits", vaz2104.isRegLimits());
        jsonObject.put("model", jsonCarModel);
        jsonObject.put("AcceptedDriverLic", jsonAcceptedDriverLic);

        /* Выведем результат в консоль */
        System.out.println(jsonObject.toString());

        /* Преобразуем объект person в json-строку */
        System.out.println(new JSONObject(vaz2104).toString());
    }
}
