package com.zombispormedio.assemble.utils;

import com.amulyakhare.textdrawable.util.ColorGenerator;
import com.orhanobut.logger.Logger;

import java.lang.reflect.Field;

import java.util.LinkedList;


/**
 * Created by Xavier Serrano on 31/07/2016.
 */
public class Utils {

    public static boolean presenceOf(Object obj) {
        return obj != null;
    }

    public static boolean presenceOf(String obj) {
        boolean it_is = true;

        if (obj == null) {
            it_is = false;
        } else {
            if (obj.isEmpty()) {
                it_is = false;
            }
        }
        return it_is;
    }

    public interface IConversion{
        Object doIt(Object object);

    }


    public static class MergeBuilder<E, R> {

        private Class<? extends E> emissorClass;

        private E emissor;

        private Class<? extends R> recipientClass;

        private IConversion conversion;

        private R recipient;

        public MergeBuilder emite(E emissor) {
            this.emissor = emissor;
            setEmissorClass();
            return this;
        }

        public MergeBuilder receive(R recipient) {
            this.recipient = recipient;
            setRecipientClass();
            return this;
        }

        public MergeBuilder convert(IConversion conversion){
            this.conversion=conversion;
            return this;
        }

        public MergeBuilder() {
            this.recipient = null;
            this.emissor = null;
            this.conversion=null;
        }

        private void setEmissorClass() {
            if (emissor != null) {
                this.emissorClass = (Class<? extends E>) emissor.getClass();
            }
        }

        private void setRecipientClass() {
            if (recipient != null) {
                this.recipientClass = (Class<? extends R>) recipient.getClass();
            }
        }

        public MergeBuilder(E emissor, R recipient) {
            this.emissor = emissor;
            setEmissorClass();
            this.recipient = recipient;
            setRecipientClass();
        }

        public R merge() {

            if (emissor != null && recipient != null) {

                for (Field field : recipientClass.getFields()) {
                    String name = field.getName();
                    Field thisField;
                    try {
                        thisField = emissorClass.getField(name);
                        Object value = thisField.get(emissor);

                        if(conversion!=null){
                            Object preValue=conversion.doIt(value);
                            if(preValue!=null){
                                value=preValue;
                            }
                        }

                        field.set(recipient, value);
                    } catch (Exception e) {
                        Logger.d(e.getMessage());
                    }

                }

            }

            return recipient;
        }
    }

    public static int getRandomColor(){
        ColorGenerator generator = ColorGenerator.MATERIAL;
        return generator.getRandomColor();
    }

    public static int getColorByString(String str){
        ColorGenerator generator = ColorGenerator.MATERIAL;
        return generator.getColor(str.hashCode());

    }

    public static int[] toArray(LinkedList<Integer> in){
        int len=in.size();
        int[] out= new int[len];

        for(int i=0; i< len; i++){
            out[i]=in.get(i);
        }

        return out;
    }


    public static Integer[] toInteger(int[] in){
        int len=in.length;
        Integer[] out=new Integer[len];
        for(int i=0; i< len; i++){
            out[i]=in[i];
        }

        return out;
    }

    public static class Pair<X, Y>{
        public final X first;
        public final Y second;

        public Pair(X first, Y second) {
            this.first = first;
            this.second = second;
        }
    }

    public static class IntPair extends Pair<Integer, Integer>{

        public IntPair(Integer first, Integer second) {
            super(first, second);
        }
    }


    public static class IntPairBuilder{

        private int first;
        private int second;

        public IntPairBuilder setFirst(int first) {
            this.first = first;
            return this;
        }

        public IntPairBuilder setSecond(int second) {
            this.second = second;
            return this;
        }

        public IntPair build(){
            return new IntPair(first, second);
        }

    }


}
