package com.bloomtech.socialfeed.helpers;

import com.google.gson.*;

import java.lang.reflect.Type;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class LocalDateTimeAdapter implements JsonSerializer<LocalDateTime>, JsonDeserializer<LocalDateTime> {

    @Override
    public JsonElement serialize(LocalDateTime src, Type typeOfSrc, JsonSerializationContext context) {
        JsonObject jsonObject = new JsonObject();

        JsonObject dateObject = new JsonObject();
        dateObject.addProperty("year", src.getYear());
        dateObject.addProperty("month", src.getMonthValue());
        dateObject.addProperty("day", src.getDayOfMonth());

        JsonObject timeObject = new JsonObject();
        timeObject.addProperty("hour", src.getHour());
        timeObject.addProperty("minute", src.getMinute());
        timeObject.addProperty("second", src.getSecond());
        timeObject.addProperty("nano", src.getNano());

        jsonObject.add("date", dateObject);
        jsonObject.add("time", timeObject);

        return jsonObject;
    }

    @Override
    public LocalDateTime deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        JsonObject jsonObject = json.getAsJsonObject();

        int year = jsonObject.getAsJsonObject("date").get("year").getAsInt();
        int month = jsonObject.getAsJsonObject("date").get("month").getAsInt();
        int day = jsonObject.getAsJsonObject("date").get("day").getAsInt();

        int hour = jsonObject.getAsJsonObject("time").get("hour").getAsInt();
        int minute = jsonObject.getAsJsonObject("time").get("minute").getAsInt();
        int second = jsonObject.getAsJsonObject("time").get("second").getAsInt();
        int nano = jsonObject.getAsJsonObject("time").get("nano").getAsInt();

        return LocalDateTime.of(year, month, day, hour, minute, second, nano);
    }
}
