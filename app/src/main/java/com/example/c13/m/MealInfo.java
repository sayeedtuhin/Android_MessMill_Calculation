package com.example.c13.m;

import android.content.Context;
import android.widget.Toast;

public class MealInfo {

    private int id;
    private float totalBazar;
    private float totalExtra;
    private float eachPersonExtra;
    private String name;
    private float deposit;
    private float meal;
    private float totalMeal;
    private float mealCost;
    private float totalCost;
    private float mealRet;
    private float RestMony;
    private int totalMassMember;
    Context context;

    public MealInfo(int id, String name, float deposit, float meal, float MealCost, float eachPersonExtra, float totalCost, float restMony) {
        this.id = id;
        this.name = name;
        this.deposit = deposit;
        this.meal = meal;
        this.mealCost = MealCost;
        this.eachPersonExtra = eachPersonExtra;
        this.totalCost = totalCost;
        RestMony = restMony;
    }

    public MealInfo(int id, String name, float deposit, float meal) {
        this.id = id;
        this.name = name;
        this.deposit = deposit;
        this.meal = meal;
    }

    public MealInfo(String name, float deposit, float meal) {
        this.name = name;
        this.deposit = deposit;
        this.meal = meal;
    }


    public MealInfo(int id, float totalBazar, float totalExtra) {
        this.id = id;
        this.totalBazar = totalBazar;
        this.totalExtra = totalExtra;
    }

    public MealInfo(float totalBazar, float totalExtra) {
        this.totalBazar = totalBazar;
        this.totalExtra = totalExtra;
    }

    public MealInfo(int id, String name, float meal) {
        this.id = id;
        this.name = name;
        this.meal = meal;
    }

    public MealInfo(String name, float meal) {

        this.name = name;
        this.meal = meal;
    }

    public MealInfo() {

    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public float getTotalBazar() {
        return totalBazar;
    }

    public void setTotalBazar(float totalBazar) {
        try {
            this.totalBazar = totalBazar;
        } catch (Exception e) {
            Toast.makeText(getContext(), "Please give Total Bazar", Toast.LENGTH_SHORT).show();
        }

    }

    public float getTotalExtra() {
        return totalExtra;
    }

    public void setTotalExtra(float totalExtra) {

        try {
            this.totalExtra = totalExtra;
        } catch (Exception e) {
            Toast.makeText(getContext(), "Please give Total Extra", Toast.LENGTH_SHORT).show();

        }
    }

    public float getEachPersonExtra() {
        return eachPersonExtra;
    }

    public void setEachPersonExtra(float eachPersonExtra) {
        this.eachPersonExtra = eachPersonExtra;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        try {
            String newName = name.toUpperCase();
            this.name = newName;
        } catch (Exception e) {

        }
    }

    public float getDeposit() {
        return deposit;
    }

    public void setDeposit(float deposit) {
        try {

            this.deposit = deposit;
        } catch (Exception e) {
            Toast.makeText(getContext(), "Please give Deposit", Toast.LENGTH_SHORT).show();
        }

    }

    public float getMeal() {
        return meal;
    }

    public void setMeal(float meal) {
        try {

            this.meal = meal;
        } catch (Exception e) {
            Toast.makeText(getContext(), "Please give Meal", Toast.LENGTH_SHORT).show();
        }
    }

    public float getTotalMeal() {
        return totalMeal;
    }

    public void setTotalMeal(float totalMeal) {
        this.totalMeal = totalMeal;
    }

    public float getMealCost() {
        return mealCost;
    }

    public void setMealCost(float MealCost) {
        this.mealCost = MealCost;
    }

    public float getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(float totalCost) {
        this.totalCost = totalCost;
    }

    public float getMealRet() {
        return mealRet;
    }

    public void setMealRet(float mealRet) {
        this.mealRet = mealRet;
    }

    public float getRestMony() {
        return RestMony;
    }

    public void setRestMony(float restMony) {
        RestMony = restMony;
    }

    public int getTotalMassMember() {
        return totalMassMember;
    }

    public void setTotalMassMember(int totalMassMember) {
        this.totalMassMember = totalMassMember;
    }

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public String checkInteger(float value) {
        String intResult = "";
        String resultString = String.valueOf(value);
        String strArray[] = resultString.split("");
        if (strArray[resultString.length()].equals("0") && strArray[resultString.length() - 1].equals(".")) {
            String[] newArray = new String[resultString.length() - 1];
            for (int i = 0; i < newArray.length; i++) {
                newArray[i] = strArray[i];
            }
            for (String i : newArray) {
                intResult += i;
            }

        } else {
            intResult = String.valueOf(value);
        }
        return intResult;
    }


}
