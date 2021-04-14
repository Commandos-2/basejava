package com.resume.webapp;

class Generic<T> {
    T generic1;
    T generic2;

    public Generic(T generic1, T generic2) {
        this.generic1 = generic1;
        this.generic2 = generic2;
    }

    public T getGeneric2() {
        return generic2;
    }

    public void setGeneric2(T generic2) {
        this.generic2 = generic2;
    }

    public T getgeneric1() {
        return generic1;
    }

    public void setgeneric1(T generic1) {
        this.generic1 = generic1;
    }

    @Override
    public String toString() {
        return "Generic{" +
                "generic1=" + generic1 +
                ", generic2=" + generic2 +
                '}';
    }

}

class Genericsun<N> extends Generic {
    N genericsun;

    public Genericsun(Object generic1, Object generic2, N genericsun) {
        super(generic1, generic2);
        this.genericsun = genericsun;

    }

    public N getgenericsun() {
        return genericsun;
    }

    public void setgenericsun(N genericsun) {
        this.genericsun = genericsun;
    }

    @Override
    public String toString() {
        return "Genericsun{" +
                "generic1=" + generic1 +
                ", generic2=" + generic2 +
                ", genericsun=" + genericsun +
                '}';
    }
}

class Genericsun2<T extends Number> extends Genericsun
{
T genericsun2;

    public Genericsun2(Object generic1, Object generic2, Object genericsun, T genericsun2) {
        super(generic1, generic2, genericsun);
        this.genericsun2 = genericsun2;
    }

    @Override
    public String toString() {
        return "Genericsun2{" +
                "generic1=" + generic1 +
                ", generic2=" + generic2 +
                ", genericsun=" + genericsun +
                ", genericsun2=" + genericsun2 +
                '}';
    }
}

public class MainGeneric {

    public static void main(String[] args) {
        Generic<Integer> example = new Generic<>(12, 23);
        String result = example.toString();
        System.out.println(result);

        example.setgeneric1(5);
        result = example.toString();

        System.out.println(result);
        Generic<String> example2 = new Generic<>("Parametr1", "23");
        example2.setgeneric1("Parametr1 - Changed");
        result = example2.toString();
        System.out.println(result);

        Genericsun<String> examplesun = new Genericsun<>(12, 23, "String");
        result = examplesun.toString();
        System.out.println(result);

        examplesun.setgeneric1(800);
        examplesun.setgenericsun("String_Changed");
        result = examplesun.toString();
        System.out.println(result);

        Genericsun2<Double> examplesun2 = new Genericsun2<>(12, 23, "String", 65.3);
        result = examplesun2.toString();
        System.out.println(result);
        //System.out.println();
    }
}
