package de.embl.cba.n5.ome.zarr.util;

import com.google.gson.*;

import java.lang.reflect.Type;
import java.util.List;

public class ZarrAxesAdapter implements JsonDeserializer<ZarrAxes>, JsonSerializer<ZarrAxes> {

    @Override
    public ZarrAxes deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        JsonArray array = json.getAsJsonArray();
        if (array.size() > 0) {
            StringBuilder axisString = new StringBuilder("[");
            for (int i = 0; i < array.size(); i++) {
                String element = array.get(i).getAsString();
                if (i != 0) {
                    axisString.append(",");
                }
                axisString.append("\"");
                axisString.append(element);
                axisString.append("\"");
            }
            axisString.append("]");
            return ZarrAxes.decode(axisString.toString());
        } else {
            return null;
        }
    }

    @Override
    public JsonElement serialize(ZarrAxes axes, Type typeOfSrc, JsonSerializationContext context) {
        List<String> axisList = axes.getAxesList();
        JsonArray jsonArray = new JsonArray();
        for (String axis : axisList) {
            jsonArray.add(axis);
        }
        return jsonArray;
    }
}