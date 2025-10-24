package ordenacao;

public class ComparaTrocas {

    static int[] copia(int[] v) {
        int[] r = new int[v.length];
        for (int i = 0; i < v.length; i++) r[i] = v[i];
        return r;
    }

    static void troca(int[] a, int i, int j) {
        int t = a[i];
        a[i] = a[j];
        a[j] = t;
    }

    // bubble com flag
    static long bubble_trocas(int[] a) {
        long trocas = 0;
        boolean mudou = true;
        int n = a.length;
        while (mudou) {
            mudou = false;
            for (int i = 0; i < n - 1; i++) {
                if (a[i] > a[i + 1]) {
                    troca(a, i, i + 1);
                    trocas++;
                    mudou = true;
                }
            }
            n--;
        }
        return trocas;
    }

    // selection
    static long selection_trocas(int[] a) {
        long trocas = 0;
        int n = a.length;
        for (int i = 0; i < n - 1; i++) {
            int min = i;
            for (int j = i + 1; j < n; j++) if (a[j] < a[min]) min = j;
            if (min != i) { troca(a, i, min); trocas++; }
        }
        return trocas;
    }

    // cocktail
    static long cocktail_trocas(int[] a) {
        long trocas = 0;
        int ini = 0, fim = a.length - 1;
        boolean mudou = true;
        while (mudou) {
            mudou = false;
            for (int i = ini; i < fim; i++) {
                if (a[i] > a[i + 1]) { troca(a, i, i + 1); trocas++; mudou = true; }
            }
            if (!mudou) break;
            mudou = false;
            fim--;
            for (int i = fim; i > ini; i--) {
                if (a[i - 1] > a[i]) { troca(a, i - 1, i); trocas++; mudou = true; }
            }
            ini++;
        }
        return trocas;
    }

    // gnome
    static long gnome_trocas(int[] a) {
        long trocas = 0;
        int i = 0, n = a.length;
        while (i < n) {
            if (i == 0 || a[i] >= a[i - 1]) i++;
            else { troca(a, i, i - 1); trocas++; i--; }
        }
        return trocas;
    }

    // comb
    static long comb_trocas(int[] a) {
        long trocas = 0;
        int n = a.length, gap = n;
        boolean mudou = true;
        while (gap > 1 || mudou) {
            gap = (gap * 10) / 13;
            if (gap < 1) gap = 1;
            mudou = false;
            for (int i = 0; i + gap < n; i++) {
                int j = i + gap;
                if (a[i] > a[j]) { troca(a, i, j); trocas++; mudou = true; }
            }
        }
        return trocas;
    }

    // bucket (com gnome dentro)
    static long bucket_trocas(int[] a) {
        int n = a.length;
        if (n <= 1) return 0;

        int min = a[0], max = a[0];
        for (int i = 1; i < n; i++) {
            if (a[i] < min) min = a[i];
            if (a[i] > max) max = a[i];
        }

        if (min == max) return 0;

        int bcount = 10;
        int[][] buckets = new int[bcount][n];
        int[] sizes = new int[bcount];

        for (int i = 0; i < n; i++) {
            int idx = (int) (((long)(a[i] - min) * (bcount - 1)) / (max - min));
            int pos = sizes[idx];
            buckets[idx][pos] = a[i];
            sizes[idx] = pos + 1;
        }

        long trocas = 0;
        int k = 0;

        for (int b = 0; b < bcount; b++) {
            int m = sizes[b];
            if (m <= 1) {
                if (m == 1) a[k++] = buckets[b][0];
                continue;
            }
            int i = 0;
            while (i < m) {
                if (i == 0 || buckets[b][i] >= buckets[b][i - 1]) i++;
                else {
                    int t = buckets[b][i];
                    buckets[b][i] = buckets[b][i - 1];
                    buckets[b][i - 1] = t;
                    trocas++;
                    i--;
                }
            }
            for (int j = 0; j < m; j++) a[k++] = buckets[b][j];
        }

        return trocas;
    }

    static void comparar(String nome, int[] base) {
        int[] a1 = copia(base);
        int[] a2 = copia(base);
        int[] a3 = copia(base);
        int[] a4 = copia(base);
        int[] a5 = copia(base);
        int[] a6 = copia(base);

        long ini, fim;

        ini = System.nanoTime();
        long tb = bubble_trocas(a1);
        fim = System.nanoTime();
        long tb_tempo = fim - ini;

        ini = System.nanoTime();
        long ts = selection_trocas(a2);
        fim = System.nanoTime();
        long ts_tempo = fim - ini;

        ini = System.nanoTime();
        long tc = cocktail_trocas(a3);
        fim = System.nanoTime();
        long tc_tempo = fim - ini;

        ini = System.nanoTime();
        long tcomb = comb_trocas(a4);
        fim = System.nanoTime();
        long tcomb_tempo = fim - ini;

        ini = System.nanoTime();
        long tg = gnome_trocas(a5);
        fim = System.nanoTime();
        long tg_tempo = fim - ini;

        ini = System.nanoTime();
        long tbucket = bucket_trocas(a6);
        fim = System.nanoTime();
        long tbucket_tempo = fim - ini;

        System.out.println("começo caso " + nome + ":");
        System.out.println("bubble: trocas: " + tb + " tempo(ns): " + tb_tempo);
        System.out.println("selection: trocas: " + ts + " e tempo(ns): " + ts_tempo);
        System.out.println("cocktail: trocas: " + tc + " e tempo(ns): " + tc_tempo);
        System.out.println("comb: trocas: " + tcomb + " e tempo(ns): " + tcomb_tempo);
        System.out.println("gnome: trocas: " + tg + " e tempo(ns): " + tg_tempo);
        System.out.println("bucket: trocas: " + tbucket + " e tempo(ns): " + tbucket_tempo);
        System.out.println("fim caso " + nome);
    }

    public static void main(String[] args) {
        int[] vetor1 = {12, 18, 9, 25, 17, 31, 22, 27, 16, 13, 19, 23, 20, 30, 14, 11, 15, 24, 26, 28};
        int[] vetor2 = {5, 7, 9, 10, 12, 14, 15, 17, 19, 21, 22, 23, 24, 25, 27, 28, 29, 30, 31, 32};
        int[] vetor3 = {99, 85, 73, 60, 50, 40, 35, 30, 25, 20, 15, 14, 13, 12, 11, 10, 9, 8, 7, 6};

        comparar("vetor 1", vetor1);
        comparar("vetor 2", vetor2);
        comparar("vetor 3", vetor3);
    }
}
