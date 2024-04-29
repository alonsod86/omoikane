package org.example.application.service;

import jakarta.annotation.PostConstruct;
import org.springframework.ai.reader.ExtractedTextFormatter;
import org.springframework.ai.reader.TextReader;
import org.springframework.ai.reader.pdf.PagePdfDocumentReader;
import org.springframework.ai.reader.pdf.config.PdfDocumentReaderConfig;
import org.springframework.ai.transformer.splitter.TokenTextSplitter;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

//@Component
public class TextFileReader {
    private final VectorStore vectorStore;

    @Value("classpath:Short.txt")
    private Resource resource;

    public TextFileReader(VectorStore vectorStore) {
        this.vectorStore = vectorStore;
    }

    @PostConstruct
    public void init() {
        TextReader textReader = new TextReader(resource);
        textReader.getCustomMetadata().put("filename", "Short.txt");
        var textSplitter = new TokenTextSplitter();
        vectorStore.accept(textSplitter.apply(textReader.get()));
    }
}