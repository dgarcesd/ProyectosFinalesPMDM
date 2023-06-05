package com.example.proyectofinal1ev;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class OKHttpClient {

    OkHttpClient client = new OkHttpClient();
    Request request = new Request.Builder()
            .url("http://mockapi.io/projects/63cd65a1d4d47898e39822ff/videojuegos")
            .get()
            .build();

    Response response = null;
    {
        try

        {
            try {
                response = client.newCall(request).execute();
            } catch (IOException e) {
                e.printStackTrace();
            }
            if (response.code() == 200) {
                // La respuesta es válida, procese los datos aquí
                String jsonData = null;
                try {
                    jsonData = response.body().string();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                JSONArray objectsArray = null;
                try {
                    objectsArray = new JSONArray(jsonData);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                for (int i = 0; i < objectsArray.length(); i++) {
                    JSONObject object = null;
                    try {
                        object = objectsArray.getJSONObject(i);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    // Aquí puedes acceder a los campos del objeto como object.getString("field_name")
                    object.getString("id");
                    object.getString("Nombre");
                    object.getString("Imagen");
                    object.getString("Valoracion");
                    object.getString("Categoria");
                    object.getString("Desarrollador");
                    object.getString("Anyo");
                }
            } else {
                // La respuesta no es válida, maneje el error aquí
            }
        } catch (JSONException e) {
            e.printStackTrace();
        } finally {
            if (response != null) {
                response.close();
            }
        }
    }



}
