package org.example;
import soapclient.MNBArfolyamServiceSoap;
import soapclient.MNBArfolyamServiceSoapGetExchangeRatesStringFaultFaultMessage;
import soapclient.MNBArfolyamServiceSoapImpl;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws MNBArfolyamServiceSoapGetExchangeRatesStringFaultFaultMessage {
        MNBArfolyamServiceSoapImpl impl = new MNBArfolyamServiceSoapImpl();
        MNBArfolyamServiceSoap service = impl.getCustomBindingMNBArfolyamServiceSoap();

        System.out.println("Kérlek add meg, hogy milyen adatokat szeretnél lekérni a Magyar Nemzeti Bank adatbázisában elmentett árfolyamokról!(https://www.mnb.hu/arfolyam-lekerdezes)");
        Scanner scanner = new Scanner(System.in);
        System.out.println();
        System.out.println("1. Valuta megadása (Az alábbi módon érvényes: EUR, USD, JPY, stb...):");

        String valuta;
        while (true) {
            System.out.println();
            System.out.print("Add meg kérlek, hogy milyen valutát szeretnél letölteni: ");
            valuta = scanner.nextLine().trim().toUpperCase();

            if (valuta.matches("^[A-Z]{3}$")) {
                break;
            } else {
                System.out.println();
                System.out.println("Hibás formátum! A valuta kódnak három nagybetűből kell állnia (pl.: EUR, USD). Próbáld újra!");
            }
        }

        System.out.println();
        System.out.println("2. Időintervallum beállítása (Kérlek itt használd az alábbi formát: év-hónap-nap! Pl.: 2022-08-14)");
        String kezdes;
        while (true) {
            System.out.println();
            System.out.print("Add meg, hogy milyen időponttól szeretnéd megnézni a deviza árfolyamát (" + valuta + "): ");
            kezdes = scanner.nextLine().trim();

            if (kezdes.matches("^\\d{4}-\\d{2}-\\d{2}$")) {
                break;
            } else {
                System.out.println();
                System.out.println("Hibás formátum! Az időpontot az év-hónap-nap formátumban kell megadni (pl.: 2022-08-14). Próbáld újra!");
            }
        }

        // Validate end date input
        String vege;
        while (true) {
            System.out.println();
            System.out.print("Add meg, hogy milyen időpontig szeretnéd megnézni a deviza árfolyamát (" + valuta + "): ");
            vege = scanner.nextLine().trim();

            if (vege.matches("^\\d{4}-\\d{2}-\\d{2}$")) { // Ensures YYYY-MM-DD format
                break;
            } else {
                System.out.println();
                System.out.println("Hibás formátum! Az időpontot az év-hónap-nap formátumban kell megadni (pl.: 2022-08-14). Próbáld újra!");
            }
        }

        System.out.println();
        System.out.println("A kért eredmény:");
        try {
            System.out.println(service.getExchangeRates(kezdes, vege, valuta));
        } catch (Exception e) {
            System.err.println("Hiba történt az árfolyamok lekérése közben: " + e.getMessage());
        }
    }
}