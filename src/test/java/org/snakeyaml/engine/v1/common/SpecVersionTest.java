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
package org.snakeyaml.engine.v1.common;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;
import org.snakeyaml.engine.v1.api.LoadSettings;
import org.snakeyaml.engine.v1.api.LoadSettingsBuilder;
import org.snakeyaml.engine.v1.api.lowlevel.Compose;
import org.snakeyaml.engine.v1.exceptions.YamlVersionException;
import org.snakeyaml.engine.v1.nodes.ScalarNode;

@Tag("fast")
class SpecVersionTest {

    @Test
    @DisplayName("Version 1.2 is accepted")
    void version12(TestInfo testInfo) {
        LoadSettings settings = new LoadSettingsBuilder().setLabel("spec 1.2").build();
        ScalarNode node = (ScalarNode) new Compose(settings).composeString("%YAML 1.2\n---\nfoo").get();
        assertEquals("foo", node.getValue());
    }

    @Test
    @DisplayName("Version 1.3 is accepted by default")
    void version13(TestInfo testInfo) {
        LoadSettings settings = new LoadSettingsBuilder().setLabel("spec 1.3").build();
        ScalarNode node = (ScalarNode) new Compose(settings).composeString("%YAML 1.3\n---\nfoo").get();
        assertEquals("foo", node.getValue());
    }

    @Test
    @DisplayName("Version 1.3 is rejected if configured")
    void version13rejected(TestInfo testInfo) {
        LoadSettings settings = new LoadSettingsBuilder().setLabel("spec 1.3")
                .setVersionFunction(version -> {
                    if (version.getMinor() > 2) throw new IllegalArgumentException("Too high.");
                    else return version;
                })
                .build();
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () ->
                new Compose(settings).composeString("%YAML 1.3\n---\nfoo").get());
        assertEquals("Too high.", exception.getMessage());
    }

    @Test
    @DisplayName("Version 2.0 is rejected")
    void version20(TestInfo testInfo) {
        LoadSettings settings = new LoadSettingsBuilder().setLabel("spec 2.0").build();
        YamlVersionException exception = assertThrows(YamlVersionException.class, () ->
                new Compose(settings).composeString("%YAML 2.0\n---\nfoo").get());
        assertEquals("Version{major=2, minor=0}", exception.getMessage());
    }
}
