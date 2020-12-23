package com.rmurugaian.spring.sftp.config;

import com.rmurugaian.spring.sftp.processor.FileProcessor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.integration.config.EnableIntegration;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.dsl.IntegrationFlows;
import org.springframework.integration.dsl.Pollers;
import org.springframework.integration.file.dsl.Files;

import java.io.File;
import java.time.Duration;

import static java.nio.charset.StandardCharsets.UTF_8;

@Configuration
@EnableIntegration
public class FileIntegrationConfig {

    private final FileProcessor fileProcessor;

    public FileIntegrationConfig(final FileProcessor fileProcessor) {
        this.fileProcessor = fileProcessor;
    }

    @Bean
    public IntegrationFlow fileReadingFlow(
        @Value("${file.input.dir}") final String inputDir) {

        return IntegrationFlows
            .from(
                Files.inboundAdapter(new File(inputDir))
                    .autoCreateDirectory(true)
                    .preventDuplicates(true)
                    .ignoreHidden(true)
                    .useWatchService(true)
                    .patternFilter("*.xml"),
                e -> e.id("fileInboundAdapter")
                    .poller(Pollers.fixedRate(Duration.ofSeconds(2))))
            .transform(Files.toStringTransformer(UTF_8.name(), true))
            .channel(processFileChannel())
            .get();
    }

    @Bean
    public DirectChannel processFileChannel() {
        return new DirectChannel();
    }

    @Bean
    public IntegrationFlow orderUpdateFlow() {
        return IntegrationFlows.from(processFileChannel())
            .handle(fileProcessor, "processFile")
            .get();
    }
}
