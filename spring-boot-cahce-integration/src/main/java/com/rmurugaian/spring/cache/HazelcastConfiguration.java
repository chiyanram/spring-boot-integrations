package com.rmurugaian.spring.cache;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableSet;
import com.hazelcast.config.*;
import com.hazelcast.nio.serialization.ByteArraySerializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;

@Configuration
public class HazelcastConfiguration {

    @Bean
    public Config hazelCastConfig(final ObjectMapper objectMapper) {
        final Config config = new Config()
            .setInstanceName("hazelcast-instance")
            .addMapConfig(
                new MapConfig()
                    .setName("instruments")
                    .setMaxSizeConfig(new MaxSizeConfig(200, MaxSizeConfig.MaxSizePolicy.FREE_HEAP_SIZE))
                    .setEvictionPolicy(EvictionPolicy.LRU)
                    .setTimeToLiveSeconds(20000));
        config.getSerializationConfig()
            .addSerializerConfig(new SerializerConfig()
                .setTypeClass(ImmutableSet.class)
                .setImplementation(new JacksonSerializer<>(1, ImmutableSet.class, objectMapper)));

        config.getSerializationConfig()
            .addSerializerConfig(new SerializerConfig()
                .setTypeClass(ImmutableList.class)
                .setImplementation(new JacksonSerializer<>(2, ImmutableList.class, objectMapper)));


        return config;
    }

    private static class JacksonSerializer<T> implements ByteArraySerializer<T> {

        private final int typeId;
        private final Class<T> clazz;
        private final ObjectMapper mapper;

        JacksonSerializer(final int typeId, final Class<T> clazz, final ObjectMapper mapper) {
            this.typeId = typeId;
            this.clazz = clazz;
            this.mapper = mapper;
        }

        @Override
        public byte[] write(final T object) throws IOException {
            return mapper.writeValueAsBytes(object);
        }

        @Override
        public T read(final byte[] buffer) throws IOException {
            return mapper.readValue(buffer, clazz);
        }

        @Override
        public int getTypeId() {
            return typeId;
        }

        @Override
        public void destroy() {

        }
    }
}