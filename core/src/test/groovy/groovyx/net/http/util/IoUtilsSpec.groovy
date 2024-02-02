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
package groovyx.net.http.util


import org.apache.commons.lang3.RandomUtils
import spock.lang.Specification

class IoUtilsSpec extends Specification {

    def 'streamToBytes'() {
        expect:
        IoUtils.streamToBytes(new ByteArrayInputStream(bytes)) == bytes

        where:
        bytes << [
                RandomUtils.nextBytes(10),
                RandomUtils.nextBytes(1000),
                RandomUtils.nextBytes(10000),
        ]
    }

    @SuppressWarnings('GrDeprecatedAPIUsage')
    def 'copyAsString'() {
        expect:
        IoUtils.copyAsString(stream) == string

        and: 'make sure stream is still readable'
        stream?.text == string

        where:
        stream                                                            || string
        new BufferedInputStream(new StringBufferInputStream('something')) || 'something'
        new BufferedInputStream(new StringBufferInputStream(''))          || ''
        null                                                              || null
    }

    def 'transfer'() {
        setup:
        InputStream inputStream = new ByteArrayInputStream('something interesting'.bytes)
        OutputStream outputStream = new ByteArrayOutputStream()

        when:
        IoUtils.transfer(inputStream, outputStream, true)

        then:
        outputStream.toByteArray() == 'something interesting'.bytes
    }
}
