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
package org.snakeyaml.engine.events;

import org.snakeyaml.engine.common.FlowStyle;
import org.snakeyaml.engine.exceptions.Mark;

/**
 * Marks the beginning of a sequence node.
 * <p>
 * This event is followed by the elements contained in the sequence, and a
 * {@link SequenceEndEvent}.
 * </p>
 *
 * @see SequenceEndEvent
 */
public final class SequenceStartEvent extends CollectionStartEvent {
    public SequenceStartEvent(String anchor, String tag, boolean implicit, Mark startMark,
                              Mark endMark, FlowStyle flowStyle) {
        super(anchor, tag, implicit, startMark, endMark, flowStyle);
    }

    @Override
    public boolean is(Event.ID id) {
        return ID.SequenceStart == id;
    }
}