package com.kavaliou.anno.processor

import com.google.auto.service.AutoService
import com.kavaliou.anno.annotation.VilniusKugPrinter
import javax.annotation.processing.*
import javax.lang.model.SourceVersion
import javax.lang.model.element.TypeElement
import javax.lang.model.util.Elements
import javax.lang.model.util.Types

@AutoService(Processor::class)
class VilniusKugPrinterProcessor : AbstractProcessor() {

    private lateinit var elementUtils: Elements
    private lateinit var typeUtils: Types
    private lateinit var filer: Filer
    private lateinit var messager: Messager

    override fun init(processingEnv: ProcessingEnvironment) {
        super.init(processingEnv)
        elementUtils = processingEnv.elementUtils
        typeUtils = processingEnv.typeUtils
        filer = processingEnv.filer
        messager = processingEnv.messager
    }

    override fun getSupportedAnnotationTypes(): MutableSet<String> {
        return mutableSetOf(VilniusKugPrinter::class.java.canonicalName)
    }

    override fun getSupportedSourceVersion(): SourceVersion {
        return SourceVersion.latestSupported()
    }

    override fun process(annotations: MutableSet<out TypeElement>, roundEnv: RoundEnvironment): Boolean {
        for (annotated in roundEnv.getElementsAnnotatedWith(VilniusKugPrinter::class.java)) {
            print(annotated.simpleName)
        }

        return true
    }
}