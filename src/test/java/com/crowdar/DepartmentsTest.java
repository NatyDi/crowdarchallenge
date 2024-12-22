package com.crowdar;

import org.testng.Assert;
import org.testng.annotations.Test;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import org.json.JSONArray;
import org.json.JSONObject;

public class DepartmentsTest {

    @Test
    public void testDepartmentsAreNotEmpty() throws Exception {

            // URL de la API
            String url = "https://www.mercadolibre.com.ar/menu/departments";

            // Configuración de la conexión
            HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();
            connection.setRequestMethod("GET");

            // Leer la respuesta
            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            StringBuilder response = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                response.append(line);
            }
            reader.close();

            // Parsear la respuesta JSON
            JSONObject jsonResponse = new JSONObject(response.toString());

            // Obtener el array "departments"
            JSONArray departments = jsonResponse.getJSONArray("departments");

            // Verificar que el array no esté vacío
            Assert.assertTrue(departments.length() > 0, "El array de departamentos está vacío");

    }
}
