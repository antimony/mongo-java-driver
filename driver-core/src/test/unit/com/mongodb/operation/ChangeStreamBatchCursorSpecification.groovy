/*
 * Copyright 2008-present MongoDB, Inc.
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

package com.mongodb.operation


import com.mongodb.binding.ReadBinding
import spock.lang.Specification

class ChangeStreamBatchCursorSpecification extends Specification {

    def 'should call the underlying QueryBatchCursor'() {
        given:
        def changeStreamOperation = Stub(ChangeStreamOperation)
        def binding = Stub(ReadBinding)
        def wrapped = Mock(QueryBatchCursor)
        def cursor = new ChangeStreamBatchCursor(changeStreamOperation, wrapped, binding)

        when:
        cursor.setBatchSize(10)

        then:
        1 * wrapped.setBatchSize(10)

        when:
        cursor.tryNext()

        then:
        1 * wrapped.tryNext()

        when:
        cursor.next()

        then:
        1 * wrapped.next()

        when:
        cursor.close()

        then:
        1 * wrapped.close()
    }

}
