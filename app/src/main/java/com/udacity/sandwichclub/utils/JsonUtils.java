package com.udacity.sandwichclub.utils;

import android.util.Log;

import com.udacity.sandwichclub.model.Sandwich;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class JsonUtils {

    private static final String TAG = JsonUtils.class.getSimpleName();

    private static final String NAME_NODE = "name";
    private static final String MAIN_NAME_NODE = "mainName";
    private static final String KNOWN_AS_NODE = "alsoKnownAs";
    private static final String PLACE_OF_ORIGIN_NODE = "placeOfOrigin";
    private static final String DESCRIPTION_NODE = "description";
    private static final String IMAGE_NODE = "image";
    private static final String INGREDIENTS_NODE = "ingredients";

    public static Sandwich parseSandwichJson(final String json) {
        try {
            JSONObject jsonSandwichObject = new JSONObject(json);
            Sandwich sandwich = new Sandwich();

            JSONObject nameJson = jsonSandwichObject.getJSONObject(NAME_NODE);
            sandwich.setMainName(nameJson.getString(MAIN_NAME_NODE));
            sandwich.setAlsoKnownAs(parseStringArray(nameJson, KNOWN_AS_NODE));

            sandwich.setPlaceOfOrigin(jsonSandwichObject.getString(PLACE_OF_ORIGIN_NODE));
            sandwich.setDescription(jsonSandwichObject.getString(DESCRIPTION_NODE));
            sandwich.setImage(jsonSandwichObject.getString(IMAGE_NODE));
            sandwich.setIngredients(parseStringArray(jsonSandwichObject, INGREDIENTS_NODE));
            return sandwich;
        } catch (JSONException e) {
            Log.e(TAG, e.getMessage());
        }
        return null;
    }

    private static List<String> parseStringArray(final JSONObject jsonObj, final String nodeName) {
        ArrayList<String> values = new ArrayList<>();
        try {
            JSONArray jsonStringArray = jsonObj.getJSONArray(nodeName);
            values.ensureCapacity(jsonStringArray.length());
            for (int i = 0; i < jsonStringArray.length(); i++) {
                values.add(jsonStringArray.getString(i));
            }
        } catch (JSONException e) {
            Log.e(TAG, e.getMessage());
        }
        return values;
    }
}
