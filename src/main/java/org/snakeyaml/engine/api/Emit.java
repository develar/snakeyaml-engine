/**
 * Copyright (c) 2018, http://www.snakeyaml.org
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.snakeyaml.engine.api;

import java.io.IOException;
import java.io.StringWriter;
import java.util.List;
import java.util.Objects;

import org.snakeyaml.engine.emitter.Emitter;
import org.snakeyaml.engine.events.Event;

public class Emit {

    private final DumpSettings settings;

    /**
     * Create
     *
     * @param settings - configuration
     */
    public Emit(DumpSettings settings) {
        Objects.requireNonNull(settings, "DumpSettings cannot be null");
        this.settings = settings;
    }

    //TODO iterator
    public String emit(List<Event> events) {
        StringWriter writer = new StringWriter();
        final Emitter emitter = new Emitter(settings, writer);
//        for (Event event : events) {
//            emitter.emit(event);
//        }
        return writer.toString();
    }
}

