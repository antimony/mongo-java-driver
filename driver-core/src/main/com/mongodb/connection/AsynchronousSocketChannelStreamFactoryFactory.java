/*
 * Copyright 2015 MongoDB, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.mongodb.connection;

import java.nio.channels.AsynchronousChannelGroup;

/**
 * A {@code StreamFactoryFactory} implementation for AsynchronousSocketChannel-based streams.
 *
 * @see java.nio.channels.AsynchronousSocketChannel
 * @since 3.1
 */
public class AsynchronousSocketChannelStreamFactoryFactory implements StreamFactoryFactory {
    private final AsynchronousChannelGroup group;

    /**
     * Construct an instance with the default {@code BufferProvider} and {@code AsynchronousChannelGroup}.
     *
     * @deprecated Use {@link AsynchronousSocketChannelStreamFactoryFactory#builder()} instead to construct the
     * {@code AsynchronousSocketChannelStreamFactoryFactory}.
     */
    @Deprecated
    public AsynchronousSocketChannelStreamFactoryFactory() {
        this(builder());
    }

    /**
     * Gets a builder for an instance of {@code AsynchronousSocketChannelStreamFactoryFactory}.
     * @return the builder
     * @since 3.6
     */
    public static Builder builder() {
        return new Builder();
    }

    /**
     * A builder for an instance of {@code AsynchronousSocketChannelStreamFactoryFactory}.
     *
     * @since 3.6
     */
    public static final class Builder {
        private AsynchronousChannelGroup group;

        /**
         * Sets the {@code AsynchronousChannelGroup}
         *
         * @param group the {@code AsynchronousChannelGroup}
         * @return this
         */
        public Builder group(final AsynchronousChannelGroup group) {
            this.group = group;
            return this;
        }

        /**
         * Build an instance of {@code AsynchronousSocketChannelStreamFactoryFactory}.
         * @return the AsynchronousSocketChannelStreamFactoryFactory
         */
        public AsynchronousSocketChannelStreamFactoryFactory build() {
            return new AsynchronousSocketChannelStreamFactoryFactory(this);
        }
    }

    @Override
    public StreamFactory create(final SocketSettings socketSettings, final SslSettings sslSettings) {
        return new AsynchronousSocketChannelStreamFactory(socketSettings, sslSettings,  group);
    }

    private AsynchronousSocketChannelStreamFactoryFactory(final Builder builder) {
        group = builder.group;
    }
}
