package com.tohsoft.airquality.aqicnultis;

import com.tohsoft.airquality.data.models.aqicn.Pair;

import java.util.ArrayList;

public class Decoder {

    public static ArrayList<Pair<Integer, Double>> decodeWind(String string,  int ft) {
        return decode(string,1,ft,3600);
    }

    public static ArrayList<Pair<Integer, Double>> decodePm25(String string, int ft) {
        return decode(string,3,ft,3600);
    }

    public static ArrayList<Pair<Integer, Double>> decodeHistory(String data) {
        return decode(data);
    }

    static class Parser {
        int i = 0;
        int l;
        String s;

        Parser(String s2) {
            this.s = s2;
            this.l = s2.length();
        }

        /* access modifiers changed from: package-private */
        public int readInt() {
            char c;
            int v = 0;
            while (this.i < this.l && (c = this.s.charAt(this.i)) >= '0' && c <= '9') {
                v = (v * 10) + (c - '0');
                this.i++;
            }
            return v;
        }

        /* access modifiers changed from: package-private */
        public char getChar() {
            return this.s.charAt(this.i);
        }

        /* access modifiers changed from: package-private */
        public char readChar() {
            String str = this.s;
            int i2 = this.i;
            this.i = i2 + 1;
            return str.charAt(i2);
        }

        /* access modifiers changed from: package-private */
        public boolean eos() {
            return this.i >= this.l;
        }

        /* access modifiers changed from: package-private */
        public void back() {
            this.i--;
        }
    }

    /**
     *
     * @param s string for decode
     * @return
     */
    public static ArrayList<Pair<Integer, Double>> decode(String s) {
        if (s == null || s.length() == 0) {
            return null;
        }
        return decode(s, 1, 0, 1);
    }

    public static void main(String[] args) {
        System.out.println(decode("decdkuskaaEHIHHHHHIFaafgfeddbaaaaaehhCKLICDAdbccbcaacccbaaACBCBBABBDBCadccaaaaaaaaCEFECCBaabddfgfeaababcdcABCCaaaaaaaaaaaaaabaaaaaaaaCBCadcdaabdbccbcaaaaaCCDCaaaaadededdcccaBCBBCCCDDaaaaaaaadedfffcaaaaaBEEDABBBBeBB",3,1587618000,3600));
    }

    /**
     *
     * @param s string for decode
     * @param dt khoang thoi gian giua 2 lan so lieu
     * @param ft thoi gian bat dau quan trac
     * @param st thoi gian gio (3600)
     * @return
     */
    public static ArrayList<Pair<Integer, Double>> decode(String s, int dt, int ft, int st) {
        int v = 0;
        try {
            ArrayList<Pair<Integer, Double>> m = new ArrayList<>();
            int t = -dt;
            int av = 0;
            int scale = 1;
            Parser p = new Parser(s);
            if (p.getChar() == '#') {
                p.readChar();
                scale = p.readInt();
                p.readChar();
            }
            while (!p.eos()) {
                char c = p.readChar();
                if (c >= 'a') {
                    v = c - 'a';
                } else if (c >= 'A') {
                    v = (-(c - 'A')) - 1;
                } else if (c == '!') {
                    v = p.readInt();
                } else if (c == '$') {
                    v = -p.readInt();
                } else if (c == '#') {
                    t = (p.readInt() + t) - dt;
                } else if (c < '0' || c > '9') {
                    return null;
                } else {
                    p.back();
                    t = (p.readInt() + t) - dt;
                }
                t += dt;
                av += v;
                m.add(new Pair<>((t * st) + ft, Double.valueOf(((double) av) / ((double) scale))));
            }
            System.out.println("number days :"+m.size());
            return m;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}