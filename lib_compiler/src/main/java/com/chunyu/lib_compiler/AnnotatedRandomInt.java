package com.chunyu.lib_compiler;

import com.chunyu.annotations.RandomInt;

import java.util.Random;

import javax.lang.model.element.Element;
import javax.lang.model.type.TypeKind;
import javax.lang.model.util.Elements;
import javax.lang.model.util.Types;

/**
 * Created by 雨时光 on 2019-06-18 19:19
 */
class AnnotatedRandomInt extends AnnotatedRandomElement {

    private int minValue;
    private int maxValue;

    AnnotatedRandomInt(Element element) {
        super(element);
        minValue = element.getAnnotation(RandomInt.class).minValue();
        maxValue = element.getAnnotation(RandomInt.class).maxValue();
    }

    @Override
    public boolean isTypeValid(Elements elements, Types types) {
        return element.asType().getKind().equals(TypeKind.INT);
    }

    @Override
    public String getRandomValue() {
        Random random = new Random();
        return "" + (minValue + random.nextInt(maxValue - minValue + 1));
    }

}

