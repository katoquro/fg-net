/*
 * Copyright (C) 2017 HttpBuilder-NG Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *         http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package groovyx.net.http

import groovyx.net.http.tk.EncoderTestKit

import java.util.function.BiConsumer
import java.util.function.Function

class ApacheEncodersSpec extends EncoderTestKit {

    @Override
    Function getClientFactory() {
        return { c -> new ApacheHttpBuilder(c) } as Function
    }

    @Override
    BiConsumer<ChainedHttpConfig, ToServer> getEncoder() {
        ApacheEncoders.&multipart
    }
}
