package graf;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Main {

    static ArrayList hesap(ArrayList<Integer> array) {
        ArrayList<Integer> gecici = new ArrayList();//tn-1 değerlerini tutar
        Collections.sort(array, Collections.reverseOrder());//dizimizi sıralama
        int toplam = 0;
        for (int i = 0; i <= array.size() - 1; i++) {
            toplam += array.get(i);
        }
        if (array.isEmpty()) { //array tamamen boş olursa graf belirtir 
            System.out.println();
            return array;
        }

        if (toplam % 2 == 1 || array.size() <= array.get(0)) { //eğer dereceler toplamı tek ise ya da en büyük derece düğümler sayısından büyükse graf belirtmez
            System.out.println("Graf Belirtmez!");
            System.exit(0);
        }

        int s = array.get(0);
        array.remove(0);//ilk elemanı s yapıp sildik
        int i = 0;
        while (i < s) {//tn-1 değerlerinin işlemi
            if (array.get(0) > 0) {//ilgili düğüm derecesi 0 dan büyük olmalı
                gecici.add(array.get(0) - 1);//tn-1 işlemini gerçekleştir
                array.remove(0);//d dizisini tutması için her adımda t leri sil
                i++;
            } else {//aksi halde graf belirtmez
                System.out.println("Graf Belirtmez!");
                System.exit(0);
            }
        }
        array.addAll(gecici);//t ve d dizilerini birleştir
        gecici.clear();//t dizisini temizle
        return hesap(array);//recursive olarak tekrarla
    }

    public static void main(String[] args) {
        Scanner k = new Scanner(System.in);
        System.out.println("Düğüm Sayısını Giriniz...");
        int dugumSayisi = k.nextInt();
        if (dugumSayisi <= 0) {
            System.out.println("Düğüm sayısı 0 ve negatif olamaz!");
            System.exit(0);
        }
        ArrayList<Integer> dugumDereceleri = new ArrayList();
        for (int i = 0; i < dugumSayisi;) {
            System.out.println(i + 1 + ". dereceyi giriniz...");
            int sayi = k.nextInt();
            if (sayi < 0) {
                System.out.println("Derece negatif bir değer olamaz! Tekrar giriniz...");
            } else if(sayi >= dugumSayisi){
                System.out.println("Derece, düğüm sayısından büyük ya da eşit bir değer olamaz! Tekrar giriniz...");
            }
            else {
                dugumDereceleri.add(sayi);
                i++;
            }
        }
        hesap(dugumDereceleri);
        System.out.println("Graf Belirtir.");
    }

}
