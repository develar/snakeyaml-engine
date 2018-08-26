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
package org.snakeyaml.engine.composer;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Optional;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;
import org.snakeyaml.engine.api.LoadSettingsBuilder;
import org.snakeyaml.engine.api.lowlevel.Compose;
import org.snakeyaml.engine.exceptions.ComposerException;
import org.snakeyaml.engine.nodes.Node;
import org.snakeyaml.engine.utils.TestUtils;

@Tag("fast")
class ComposerTest {

    @Test
    @DisplayName("Fail to Compose one document when more documents are provided.")
    void composeOne(TestInfo testInfo) {
        ComposerException exception = assertThrows(ComposerException.class, () ->
                new Compose(new LoadSettingsBuilder().build()).composeString("a\n---\nb\n"));
        assertTrue(exception.getMessage().contains("expected a single document in the stream"));
        assertTrue(exception.getMessage().contains("but found another document"));
    }

    @Test
    void failToComposeUnknownAlias(TestInfo testInfo) {
        ComposerException exception = assertThrows(ComposerException.class, () ->
                new Compose(new LoadSettingsBuilder().build()).composeString("[a, *id b]"));
        assertTrue(exception.getMessage().contains("found undefined alias id"), exception.getMessage());
    }

    @Test
    void composeMergeExample(TestInfo testInfo) {
        Compose compose = new Compose(new LoadSettingsBuilder().build());
        Optional<Node> node = compose.composeString(TestUtils.getResource("merge/example.yaml"));
        assertTrue(node.isPresent());
    }
}
