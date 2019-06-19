package com.chunyu.lib_compiler;

import com.chunyu.annotations.RandomInt;
import com.chunyu.annotations.RandomString;

import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.Filer;
import javax.annotation.processing.Messager;
import javax.annotation.processing.ProcessingEnvironment;
import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.TypeElement;
import javax.lang.model.util.Elements;
import javax.lang.model.util.Types;
import javax.tools.Diagnostic;

public class RandomProcessor extends AbstractProcessor {

    private static final String RANDOM_SUFFIX = "_Random";
    private static final String TARGET_STATEMENT_FORMAT = "target.%1$s = %2$s";
    private static final String CONST_PARAM_TARGET_NAME = "target";
    private static final char CHAR_DOT = '.';

    private static final List<Class<? extends Annotation>> RANDOM_TYPES = Arrays.asList(RandomInt.class, RandomString.class);

    private Messager messager;
    private Types typesUtil;
    private Elements elementsUtil;
    private Filer filer;

    @Override
    public synchronized void init(ProcessingEnvironment processingEnvironment) {
        super.init(processingEnvironment);
        messager = processingEnv.getMessager();
        typesUtil = processingEnv.getTypeUtils();
        elementsUtil = processingEnv.getElementUtils();
        filer = processingEnv.getFiler();
    }

    @Override
    public SourceVersion getSupportedSourceVersion() {
        return SourceVersion.latestSupported();
    }

    @Override
    public Set<String> getSupportedAnnotationTypes() {
        Set<String> annotations = new LinkedHashSet<>();
        for (Class<? extends Annotation> annotation : RANDOM_TYPES) {
            annotations.add(annotation.getCanonicalName());
        }
        return annotations;
    }

    @Override
    public boolean process(Set<? extends TypeElement> set, RoundEnvironment roundEnvironment) {

        Map<String, List<AnnotatedRandomElement>> annotatedElementmap = new LinkedHashMap<>();
        for (Element element : roundEnvironment.getElementsAnnotatedWith(RandomInt.class)) {
            AnnotatedRandomInt randomElement = new AnnotatedRandomInt(element);
            messager.printMessage(Diagnostic.Kind.NOTE, randomElement.toString());
            if (!randomElement.isTypeValid(elementsUtil, typesUtil)) {
                messager.printMessage(Diagnostic.Kind.ERROR, randomElement.getSimpleClassName().toString() + "#"
                        + randomElement.getElementName().toString() + "is not in valid type int");
            }

            addAnnotatedElement(annotatedElementmap, randomElement);
        }

        for (Element element : roundEnvironment.getElementsAnnotatedWith(RandomString.class)) {
            AnnotatedRandomString randomElement = new AnnotatedRandomString(element);
            messager.printMessage(Diagnostic.Kind.NOTE, randomElement.toString());
            if (!randomElement.isTypeValid(elementsUtil, typesUtil)) {
                messager.printMessage(Diagnostic.Kind.ERROR, randomElement.getSimpleClassName().toString() + "#"
                        + element.getSimpleName() + " is not in valid type String");
            }
            addAnnotatedElement(annotatedElementmap, randomElement);
        }



        return true;
    }

    private void addAnnotatedElement(Map<String, List<AnnotatedRandomElement>> map, AnnotatedRandomElement randomElement) {
        String qualifier = randomElement.getQualifiedClassName().toString();
        if (map.get(qualifier) == null) {
            map.put(qualifier, new ArrayList<AnnotatedRandomElement>());
        }
        map.get(qualifier).add(randomElement);
    }
}