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
package org.snakeyaml.engine.nodes;

public class AnchorNode extends Node {

    private Node realNode;

    public AnchorNode(Node realNode) {
        super(realNode.getTag(), realNode.getStartMark(), realNode.getEndMark());
        this.realNode = realNode;
    }

    @Override
    public NodeId getNodeId() {
        return NodeId.anchor;
    }

    public Node getRealNode() {
        return realNode;
    }
}
