package principal;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import methods.Conversion;
import models.Moneda;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException, InterruptedException {
        int realizarOtraPeticion=1;

        while (realizarOtraPeticion==1){
            Scanner lectura = new Scanner(System.in);
            System.out.println("Sea bienvenido/a al Conversor de Moneda =]\n" +
                    "1) Dólar =>> Peso argentino\n" +
                    "2) Peso argentino =>> Dólar\n" +
                    "3) Dólar =>> Real brasileño\n" +
                    "4) Real brasileño =>> Dólar\n" +
                    "5) Dólar =>> Peso colombiano\n" +
                    "6) Peso colombiano =>> Dólar\n");

            int opcion = lectura.nextInt();
            lectura.nextLine();
            String moneda="";
            String otraMoneda = "";
            switch (opcion){
                case 1:
                    moneda="USD";
                    otraMoneda="ARS";
                    break;
                case 2:
                    moneda="ARS";
                    otraMoneda="USD";
                    break;
                case 3:
                    moneda="USD";
                    otraMoneda="BRL";
                    break;
                case 4:
                    moneda="BRL";
                    otraMoneda="USD";
                    break;
                case 5:
                    moneda="USD";
                    otraMoneda="COP";
                    break;
                case 6:
                    moneda="COP";
                    otraMoneda="USD";
                    break;
                default:
                    System.out.println("Opción no válida");
                    break;
            }

            String apiKey = "0a4f4e1f04d728c5ccbd269c";
            String direccion = "https://v6.exchangerate-api.com/v6/"+apiKey+"/latest/"+moneda;



            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder().uri(URI.create(direccion)).build();
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            String json = response.body();


            Gson gson = new GsonBuilder().setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES).create();
            Moneda conversion = gson.fromJson(json, Moneda.class);


            conversion.setValorEnOtraMoneda(otraMoneda);
            Conversion cambioValor = new Conversion();

            System.out.println("Ingrese el monto en "+moneda+" que desea convertir a "+otraMoneda);
            double dineroTotal= lectura.nextDouble();
            double valorEnOtraMoneda = cambioValor.conversionA(dineroTotal,conversion.getValorEnOtraMoneda());

            System.out.println(valorEnOtraMoneda);


            System.out.println("Desea realizar otra operación?\n" +
                    "1) Si\n" +
                    "2) Salir");
            realizarOtraPeticion = lectura.nextInt();

            while (realizarOtraPeticion>2){
                System.out.println("Respuesta no válida\n");
                System.out.println("Desea realizar otra operación?\n" +
                        "1) Si\n" +
                        "2) Salir");
                realizarOtraPeticion = lectura.nextInt();
            }
        }


    }
}